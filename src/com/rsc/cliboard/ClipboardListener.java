package com.rsc.cliboard;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.UUID;

public class ClipboardListener implements Runnable {

	private Clipboard clipboard;
	private CliboardParser parser;
	private ClipboardSender sender;
	private ClipboardFileSender fileSender;
	private CliboardFileReader fileReader;
	private String id = UUID.randomUUID().toString();
	private boolean file = false;
	
	public ClipboardListener(CliboardParser parser, ClipboardSender sender, ClipboardFileSender fileSender, CliboardFileReader fileReader) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		clipboard = toolkit.getSystemClipboard();
		clipboard.addFlavorListener(new L());
		this.parser = parser;
		this.sender = sender;
		this.fileSender = fileSender;
		this.fileReader = fileReader;
		if (fileSender != null)
			fileSender.sendFile(true, id);
		else
			Logger.log("sender jest nullem");
	}
	
	@Override
	public void run() {
		Logger.log("Start Listening cliboard");
		while (true) {
			String content = getCliboarContent();
			if(parser.checkIfMeaasgeArrived(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_START);
				sender.acceptTansmision(id);
			} else if (parser.checkIfMessageAccepted(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_ACK);
				fileSender.sendFile(false, id);
				file = true;
			} else if (parser.checkIfFileArrived(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART);
				String b64 = parser.getBase64(content);
				fileReader.addPart(b64);
				sender.acceptTansmision(id);
				file = true;
			} else if (parser.checkIfEndOfFile(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART_END);
				fileReader.createFile();
				file = false;
			} else {
				Logger.log("Brak Akcji");
				try {
					if (file) 
						Thread.sleep(100); 
					else
						Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getCliboarContent() {
		String ret = "";
		CliboardRespons respons = null;
		try {
			do {
				respons = read();
				if (!respons.isValid()) {
					Logger.log("Sprobuj jeszcze raz");
					Thread.sleep(100);
				} else {
					ret = respons.getContent();
					//Logger.log("Content " + ret);
				}
				
				
			}
			while (!respons.isValid());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public CliboardRespons read() {
		CliboardRespons res = new CliboardRespons();
		try {
			String bRet = (String) clipboard.getData(DataFlavor.stringFlavor);
			res.setContent(bRet);
			res.setValid(true);
		} catch (Exception e) {
			res.setValid(false);
		}
		return res;
	}

}
