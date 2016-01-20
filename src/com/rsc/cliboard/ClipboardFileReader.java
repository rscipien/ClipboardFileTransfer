package com.rsc.cliboard;


public class ClipboardFileReader {
	private StringBuilder fileBase64Content = new StringBuilder();
	
	public void addPart(String part) {
		fileBase64Content.append(part);
	} 
	
	public byte[] mergeAndCovert() {
		byte[] arr = FileUtil.base64Encode(fileBase64Content.toString());
		return arr;
	}
}
