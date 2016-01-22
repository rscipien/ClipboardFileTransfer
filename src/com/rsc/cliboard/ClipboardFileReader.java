package com.rsc.cliboard;

import java.io.File;


public class ClipboardFileReader {
	private StringBuilder fileBase64Content = new StringBuilder();
	private String fileLocation = "";
	private String defaultFile = "." + File.separator + "out";
	
	public void addPart(String part) {
		fileBase64Content.append(part);
	} 
	
	public byte[] mergeAndCovert() {
		byte[] arr = FileUtil.base64Encode(fileBase64Content.toString());
		return arr;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	
	public String getOutFile() {
		if (fileLocation == null || "".equals(fileLocation.trim())) {
			fileLocation = defaultFile;
		}
		return fileLocation;
	}
}
