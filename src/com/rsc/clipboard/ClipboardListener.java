package com.rsc.clipboard;

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
		int doNothingCount = 0;
		while (true) {
			String content = helper.getCliboarContent();
			if(parser.checkIfMessageArrived(content, id)) {
				long startTime = System.currentTimeMillis();
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_START);
				
				sender.acceptTansmision(id);
				
				long endTime = System.currentTimeMillis();
				Logger.log("Message arrived time(ms): " + (endTime - startTime) + " Do notinhg count: " + doNothingCount);
				doNothingCount = 0;
			} else if (parser.checkIfMessageAccepted(content, id)) {
				long startTime = System.currentTimeMillis();
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_ACK);
				
				fileSender.sendFile(id);
				
				long endTime = System.currentTimeMillis();
				Logger.log("Message accepted time(ms): " + (endTime - startTime) + " Do notinhg count: " + doNothingCount);
				doNothingCount = 0;
				standBy = false;
			} else if (parser.checkIfFileArrived(content, id)) {
				long startTime = System.currentTimeMillis();
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART);
				
				String b64 = parser.getBase64(content);
				fileReader.addPart(b64);
				sender.acceptTansmision(id);
				
				long endTime = System.currentTimeMillis();
				Logger.log("Part file arrived time(ms): " + (endTime - startTime) + " Do notinhg count: " + doNothingCount);
				doNothingCount = 0;
				standBy = false;
			} else if (parser.checkIfEndOfFile(content, id)) {
				Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART_END);
				String b64 = parser.getBase64(content);
				
				fileReader.addPart(b64);
				fileReader.createFile();
				sender.endTansmision();
				standBy = true;
				return;
			} else {
				doNothingCount++;
				try {
					if (standBy) 
						Thread.sleep(1000); 
					else 
						Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
