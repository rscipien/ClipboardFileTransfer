package com.rsc.clipboard;


public class ClipboardFileSender {

	private Base64Part base64part;
	private HashGenerator generator;
	
	public ClipboardFileSender(Base64Part base64part, HashGenerator generator) {
		this.base64part = base64part;
		this.generator = generator;
	}
	
	public String prepareFilePart(String id) {
		Logger.info("Start send file");
		String b64Part = base64part.getPart();
		StringBuilder msg = new StringBuilder();
		if (base64part.isEnd()) {
			msg.append(ClipboardHeders.TRANSMISION_PART_END);
		} else {
			msg.append(ClipboardHeders.TRANSMISION_PART); 
		}
		msg.append(id).append("|");
		msg.append(generator.md5(b64Part)).append("|");
		msg.append(b64Part);
			
			
		return msg.toString();
	}
}
