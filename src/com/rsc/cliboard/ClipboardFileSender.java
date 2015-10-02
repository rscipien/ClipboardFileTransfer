package com.rsc.cliboard;


public class ClipboardFileSender {

	private CliboradHelper clipHelper;
	private Base64Part bas64part;
	
	public ClipboardFileSender(CliboradHelper clipHelper, Base64Part bas64part) {
		this.clipHelper = clipHelper;
		this.bas64part = bas64part;
	}
	
	public void sendFile(String id) {
		Logger.log("Start send file");
		String b64Part = bas64part.getPart();
		StringBuilder msg = new StringBuilder();
		if (bas64part.isEnd()) {
			msg.append(ClipboardHeders.TRANSMISION_PART_END);
		} else {
			msg.append(ClipboardHeders.TRANSMISION_PART); 
		}
		msg.append(id).append("|");
		msg.append(b64Part);
			
			
		clipHelper.writeToCliboard(msg.toString());
	}
}
