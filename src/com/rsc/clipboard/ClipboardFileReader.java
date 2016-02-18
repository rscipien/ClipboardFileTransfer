package com.rsc.clipboard;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;


public class ClipboardFileReader {
	private String fileLocation = "";
	private String defaultFile = "." + File.separator + "out";
	private BufferedOutputStream bos;
	private FileUtil fileUtil;
	
	public ClipboardFileReader(BufferedOutputStream bos, FileUtil fileUtil) {
		this.bos = bos;
		this.fileUtil = fileUtil;
	}
	
	public void addPart(String part) {
		byte[] buffor = fileUtil.base64Encode(part);
		try {
			bos.write(buffor);
			bos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public void createFile() {
		try {
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
