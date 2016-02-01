package com.rsc.cliboard;

import java.io.BufferedInputStream;
import java.util.Arrays;

public class Base64Part {

	private BufferedInputStream bis;
	private int part = 0;
	private int partsize;
	private boolean endString;
	byte[] buffor;
	
	public Base64Part(BufferedInputStream bis, int partSize) {
		Logger.log("Part size: " + partSize / 1014 + " kb");
		this.bis = bis;
		this.partsize = partSize;
		this.buffor = new byte[partSize];
	}
	
	public String getPart() {
		String b64 = "";
		try {
			int n = bis.read(buffor);
			if (n == -1) {
				endString = true;
			} else if (n == partsize) {
				b64 = FileUtil.base64Decode(buffor);
			} else if (n < partsize) {
				buffor = Arrays.copyOfRange(buffor, 0, n);
				b64 = FileUtil.base64Decode(buffor);
			} else {
				System.out.println("!!!!!!!!!!!!!!!!!!!!! A TO CO !!!!!!!!!!!!!!!!!!!");
			}
		} catch (Exception e) {
			System.out.println("OFFSET " + part * buffor.length);
			e.printStackTrace();
		}
		//Logger.log("" + (float)(part * partsize)/b64.length());
//		try {
//			String aa = part + "|" + b64Part + "\n";
//			Files.write(Paths.get("c:\\before64.txt"), aa.getBytes(), StandardOpenOption.APPEND);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		part++;
		return b64;
	}
	
	public boolean isEnd() {
		return endString;
	}
}
