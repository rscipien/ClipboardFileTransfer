package com.rsc.clipboard;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Main {

	public static void main(String[] args) throws UnsupportedFlavorException, IOException {
		
		String file = "";
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
//		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
//		System.out.println("String from Clipboard:" + result);
		ClipboradHelper helper = new ClipboradHelper(clipboard);
		ClipboardParser parser = new ClipboardParser();
		ClipboardSender sender =  new ClipboardSender(helper);
		ClipboardFileSender fileSender = null;
		ClipboardFileReader fileReader = null;
		
		
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			System.out.println(arg);
			if (arg.equals("-d")) {
				file = args[++i];
				Logger.log("Base64 dump " + file);
				byte[] arr = FileUtil.read(new File(file));
				String b64 = FileUtil.base64Decode(arr);
				FileUtil.writeToFile(b64.getBytes(), ".\\base64.txt");
				return;
			}
			
			if (arg.equals("-f")) {
				file = args[++i];
				System.out.println(file);
				File fileToTransfer = new File(file);
				if (!fileToTransfer.exists()) {
					Logger.log("File doesn't exists");
				}
				
				FileInputStream fis  = new FileInputStream(new File(file));
				BufferedInputStream bis = new BufferedInputStream(fis);
				Base64Part base64Part = new Base64Part(bis, 1024*50); 
				fileSender = new ClipboardFileSender(base64Part);
			} else if (arg.equals("-o")) {
					//String outFile = args[++i];
					//fileReader.setFileLocation(outFile);
			}
		}
		
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("out"));
		fileReader = new ClipboardFileReader(bos);
		
		ClipboardListener listener = new ClipboardListener(helper, parser, sender, fileSender, fileReader);
		Thread thread =  new Thread(listener);
		thread.run();
	}

}
