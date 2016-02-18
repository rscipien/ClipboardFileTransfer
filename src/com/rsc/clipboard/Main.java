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

	public static void main(String[] args) throws Exception {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Clipboard clipboard = toolkit.getSystemClipboard();
		ClipboradHelper helper = new ClipboradHelper(clipboard);
		ClipboardParser parser = new ClipboardParser();
		ClipboardSender sender =  new ClipboardSender(helper);
		ClipboardFileSender fileSender = null;
		ClipboardFileReader fileReader = null;
		
		FileUtil fileUtil = new FileUtil();
		CommandLineParams cmd = new CommandLineParams(args, fileUtil);
		cmd.parse();
		
		if (cmd.isDump()) {
			return;
		}
//			} else if (arg.equals("-o")) {
//					//String outFile = args[++i];
					//fileReader.setFileLocation(outFile);
//				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("out"));
//				fileReader = new ClipboardFileReader(bos);
		
		ClipboardMessageHandler messageHandler = new ClipboardMessageHandler(helper, cmd.getFileSender(), cmd.getFileReader(), sender, parser);
		ClipboardListener listener = new ClipboardListener(messageHandler, helper);
		Thread thread =  new Thread(listener);
		thread.run();
	}

}
