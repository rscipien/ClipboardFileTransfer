package com.rsc.clipboard;

public class ClipboardMessageHandler {
	private ClipboradHelper helper;
	private ClipboardFileSender fileSender;
	private ClipboardFileReader fileReader;
	private ClipboardSender sender;
	private ClipboardParser parser;
	private int doNothingCount = 0;
	// if nothing is send or receiver sleep for 1 second
	private boolean standBy;
	private boolean endListening;
	
	public ClipboardMessageHandler(ClipboradHelper helper, ClipboardFileSender fileSender,
			ClipboardFileReader fileReader, ClipboardSender sender,
			ClipboardParser parser) {
		this.helper = helper;
		this.fileSender = fileSender;
		this.fileReader = fileReader;
		this.sender = sender;
		this.parser = parser;
	}



	public boolean  handle(String content, String id) {
		boolean handled = true;
		
		if(parser.checkIfMessageArrived(content, id)) {
			long startTime = System.currentTimeMillis();
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_START);
			
			sender.acceptTansmision(id);
			
			long endTime = System.currentTimeMillis();
			Logger.log("Message arrived time(ms): " + (endTime - startTime) + " Do notingh count: " + doNothingCount);
			doNothingCount = 0;
		} else if (parser.checkIfMessageAccepted(content, id)) {
			long startTime = System.currentTimeMillis();
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_ACK);
			
			String msg = fileSender.prepareFilePart(id);
			helper.writeToCliboard(msg);
			
			long endTime = System.currentTimeMillis();
			Logger.log("Message accepted time(ms): " + (endTime - startTime) + " Do notingh count: " + doNothingCount);
			doNothingCount = 0;
			standBy = false;
		} else if (parser.checkIfFileArrived(content, id)) {
			long startTime = System.currentTimeMillis();
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART);
			
			String b64 = parser.getBase64(content);
			fileReader.addPart(b64);
			sender.acceptTansmision(id);
			
			long endTime = System.currentTimeMillis();
			Logger.log("Part file arrived time(ms): " + (endTime - startTime) + " Do notingh count: " + doNothingCount);
			doNothingCount = 0;
			standBy = false;
		} else if (parser.checkIfEndOfFile(content, id)) {
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART_END);
			String b64 = parser.getBase64(content);
			
			fileReader.addPart(b64);
			fileReader.createFile();
			sender.endTansmision();
			standBy = true;
			endListening = true;
		} else {
			handled = false;
		}
		
		return handled;
	}

	public boolean startSending(String id) {
		if (fileSender != null) {
			String msg = fileSender.prepareFilePart(id);
			helper.writeToCliboard(msg);
			return true;
		}
		
		return false;
	}
	
	public boolean isStandBy() {
		return standBy;
	}

	public boolean isEndListening() {
		return endListening;
	}
	
}
