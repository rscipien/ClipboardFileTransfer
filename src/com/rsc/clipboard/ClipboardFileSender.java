package com.rsc.clipboard;


public class ClipboardFileSender {

	private Base64Part base64part;
	
	public ClipboardFileSender(Base64Part base64part) {
		this.base64part = base64part;
	}
	
	public String prepareFilePart(String id) {
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
			
			
		return msg.toString();
	}
}
