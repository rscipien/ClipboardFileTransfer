package com.rsc.cliboard;

import java.io.File;

public class CliboardFileReader {
	private StringBuilder fileBase64Content = new StringBuilder();
	
	public void addPart(String part) {
		fileBase64Content.append(part);
	} 
	
	public void createFile() {
		byte[] arr = FileUtil.base64Encode(fileBase64Content.toString());
		FileUtil.writeToFile(arr);
	}
}
