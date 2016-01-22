package com.rsc.cliboard;

import java.util.UUID;

public class ClipboardListener implements Runnable {

	private ClipboradHelper helper;
	private ClipboardParser parser;
	private ClipboardSender sender;
	private ClipboardFileSender fileSender;
	private ClipboardFileReader fileReader;
	private String id = UUID.randomUUID().toString();
	// if nothing is send or receiver sleep for 1 second 
	private boolean standBy = true;
	
	public ClipboardListener(ClipboradHelper helper, ClipboardParser parser, ClipboardSender sender, ClipboardFileSender fileSender, ClipboardFileReader fileReader) {
		this.helper = helper;
		this.parser = parser;
		this.sender = sender;
		this.fileSender = fileSender;
		this.fileReader = fileReader;
		if (fileSender != null) {
			fileSender.sendFile(id);
		} else {
			Logger.log("Listen Mode");
		}
	}
	
	@Override
	public void run() {
		Logger.log("Start Listening clipboard");
		while (true) {
			String content = helper.getCliboarContent();
			if(parser.checkIfMessageArrived(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_START);
				sender.acceptTansmision(id);
			} else if (parser.checkIfMessageAccepted(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_ACK);
				fileSender.sendFile(id);
				standBy = false;
			} else if (parser.checkIfFileArrived(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART);
				String b64 = parser.getBase64(content);
				fileReader.addPart(b64);
				sender.acceptTansmision(id);
				standBy = false;
			} else if (parser.checkIfEndOfFile(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART_END);
				String b64 = parser.getBase64(content);
				fileReader.addPart(b64);
				byte[] arr = fileReader.mergeAndCovert();
				FileUtil.writeToFile(arr, fileReader.getOutFile());
				sender.endTansmision();
				standBy = true;
			} else {
				//Logger.log("Brak Akcji");
				try {
					if (standBy) 
						Thread.sleep(1000); 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
