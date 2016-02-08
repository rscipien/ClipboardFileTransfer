package com.rsc.clipboard;


public class ClipboardFileSender {

	private ClipboradHelper clipHelper;
	private Base64Part base64part;
	
	public ClipboardFileSender(ClipboradHelper clipHelper, Base64Part base64part) {
		this.clipHelper = clipHelper;
		this.base64part = base64part;
	}
	
	public void sendFile(String id) {
		Logger.log("Start send file");
		String b64Part = base64part.getPart();
		StringBuilder msg = new StringBuilder();
		if (base64part.isEnd()) {
			msg.append(ClipboardHeders.TRANSMISION_PART_END);
		} else {
			msg.append(ClipboardHeders.TRANSMISION_PART); 
		}
		msg.append(id).append("|");
		msg.append(b64Part);
			
			
		clipHelper.writeToCliboard(msg.toString());
	}
}
