package com.rsc.cliboard;

public class Base64Part {

	private String b64;
	private int part = 0;
	private int partsize;
	private boolean endString;
	
	public Base64Part(String b64, int partSize) {
		this.b64 = b64;
		this.partsize = partSize;
	}
	
	public String getPart() {
		if (b64 == null)
			return "";
		
		Logger.log("" + (float)(part * FileUtil.MAX_LENGTH_B64)/b64.length());
		String b64Part = "";
		int begin = part * partsize;
		int end = begin + partsize;
		
		if (end > b64.length()) {
			b64Part = b64.substring(begin);
			endString = true;
		} else {
			b64Part = b64.substring(begin, end);
		}
		part++;
		return b64Part;
	}
	
	public boolean isEnd() {
		return endString;
	}
}
