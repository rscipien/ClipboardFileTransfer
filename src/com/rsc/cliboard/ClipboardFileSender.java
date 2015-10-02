package com.rsc.cliboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;


public class ClipboardFileSender {

	private Clipboard clipboard;
	private Base64Part bas64part;
	
	public ClipboardFileSender(Clipboard clipboard, Base64Part bas64part) {
		this.clipboard = clipboard;
		this.bas64part = bas64part;
	}
	
	public void sendFile(boolean start, String id) {
		Logger.log("Start send file");
		String b64Part = bas64part.getPart();
		StringBuilder msg = new StringBuilder();
		if (bas64part.isEnd()) {
			msg.append(ClipboardHeders.TRANSMISION_PART_END);
		} else {
			msg.append(ClipboardHeders.TRANSMISION_PART); 
		}
		msg.append(id).append("|");
		msg.append(b64Part);
			
			
		clipboard.setContents(new StringSelection(msg.toString()), null);
	}
}
