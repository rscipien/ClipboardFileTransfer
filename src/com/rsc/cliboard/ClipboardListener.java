package com.rsc.cliboard;

import java.util.UUID;

public class ClipboardListener implements Runnable {

	private CliboradHelper helper;
	private CliboardParser parser;
	private ClipboardSender sender;
	private ClipboardFileSender fileSender;
	private CliboardFileReader fileReader;
	private String id = UUID.randomUUID().toString();
	private boolean file = false;
	
	public ClipboardListener(CliboradHelper helper, CliboardParser parser, ClipboardSender sender, ClipboardFileSender fileSender, CliboardFileReader fileReader) {
		this.helper = helper;
		this.parser = parser;
		this.sender = sender;
		this.fileSender = fileSender;
		this.fileReader = fileReader;
		if (fileSender != null)
			fileSender.sendFile(id);
		else
			Logger.log("sender jest nullem");
	}
	
	@Override
	public void run() {
		Logger.log("Start Listening cliboard");
		while (true) {
			String content = helper.getCliboarContent();
			if(parser.checkIfMeaasgeArrived(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_START);
				sender.acceptTansmision(id);
			} else if (parser.checkIfMessageAccepted(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_ACK);
				fileSender.sendFile(id);
				file = true;
			} else if (parser.checkIfFileArrived(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART);
				String b64 = parser.getBase64(content);
				fileReader.addPart(b64);
				sender.acceptTansmision(id);
				file = true;
			} else if (parser.checkIfEndOfFile(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART_END);
				String b64 = parser.getBase64(content);
				fileReader.addPart(b64);
				fileReader.createFile();
				sender.endTansmision();
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
	
	
}
