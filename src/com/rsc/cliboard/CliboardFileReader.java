package com.rsc.cliboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class CliboardFileReader {
	private StringBuilder fileBase64Content = new StringBuilder();
	private int cnt = 0;
	
	public void addPart(String part) {
//		try {
//			String aa = cnt + "|" + part + "\n";
//			Files.write(Paths.get("c:\\after64.txt"), aa.getBytes(), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		fileBase64Content.append(part);
		cnt++;
	} 
	
	public void createFile() {
//		try {
//			Files.write(Paths.get("c:\\after64.txt"), fileBase64Content.toString().getBytes());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		byte[] arr = FileUtil.base64Encode(fileBase64Content.toString());
		FileUtil.writeToFile(arr);
	}
}
