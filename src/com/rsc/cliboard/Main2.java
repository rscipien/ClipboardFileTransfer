package com.rsc.cliboard;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Main2 {

	public static void main(String[] args) throws UnsupportedFlavorException, IOException {
		
		String file = "";
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
//		String result = (String) clipboard.getData(DataFlavor.stringFlavor);
//		System.out.println("String from Clipboard:" + result);
		CliboradHelper helper = new CliboradHelper(clipboard);
		CliboardParser parser = new CliboardParser();
		ClipboardSender sender =  new ClipboardSender(helper);
		ClipboardFileSender fileSender = null;
		CliboardFileReader fileReader = new CliboardFileReader();
		
		
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			System.out.println(arg);
			if (arg.equals("-f")) {
				file = args[++i];
				System.out.println(file);
				File fileToTransfer = new File(file);
				if (!fileToTransfer.exists()) {
					Logger.log("File doesn't exists");
				}
				byte[] arr = FileUtil.read(new File(file));
				String b64 = FileUtil.base64Decode(arr);
				//Files.write(Paths.get("c:\\before64.txt"), b64.getBytes());
				Base64Part base64Part = new Base64Part(b64, 1000000); 
				fileSender = new ClipboardFileSender(helper, base64Part);
			}
		}
		
		ClipboardListener listener = new ClipboardListener(helper, parser, sender, fileSender, fileReader);
		Thread thread =  new Thread(listener);
		thread.run();
	}

}
