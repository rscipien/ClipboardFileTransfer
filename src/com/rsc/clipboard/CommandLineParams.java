package com.rsc.clipboard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CommandLineParams {
	private ClipboardFileSender fileSender;
	private ClipboardFileReader fileReader;
	private String[] args;
	private FileUtil fileUtil;
	private boolean dump = false;
	
	public CommandLineParams(String[] args, FileUtil fileUtil) {
		this.args = args;
		this.fileUtil = fileUtil;
	}
	
	public void parse() throws Exception {
		prepareReader();
		prepareDump();
		prepareSender();
	}
	
	private void prepareReader() throws FileNotFoundException {
		if (args.length == 0) {
			BufferedOutputStream bos = fileUtil.prepareBufferedOutputStream();
			fileReader = new ClipboardFileReader(bos, fileUtil);
		}
	}
	
	private void prepareDump() throws IOException {
		if (args.length > 1) {
			String arg = args[0];
			if (arg.equals("-d")) {
				String file = args[1];
				Logger.log("Base64 dump " + file);
				byte[] arr = fileUtil.read(new File(file));
				String b64 = fileUtil.base64Decode(arr);
				fileUtil.writeToFile(b64.getBytes(), ".\\base64.txt");
				dump = true;
			}
		}
	}
	
	private void prepareSender() throws FileNotFoundException {
		if (args.length > 1) {
			String arg = args[0];
	
			if (arg.equals("-f")) {
				String file = args[1];
				System.out.println(file);
				BufferedInputStream bis = fileUtil.prepareBufferedInputStream(file);
				Base64Part base64Part = new Base64Part(bis, 1024*50, fileUtil); 
				fileSender = new ClipboardFileSender(base64Part, new HashGenerator());
			}
		}
	}
	
	public ClipboardFileSender getFileSender() {
		return fileSender;
	}
	public ClipboardFileReader getFileReader() {
		return fileReader;
	}

	public boolean isDump() {
		return dump;
	}
}
