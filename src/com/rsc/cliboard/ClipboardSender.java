package com.rsc.cliboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipboardSender {

	private Clipboard clipboard;
	
	public ClipboardSender(Clipboard clipboard) {
		this.clipboard = clipboard;
	}
	
	public void acceptTansmision(String id) {
		clipboard.setContents(new StringSelection(ClipboardHeders.TRANSMISION_ACK + id), null);
		Logger.log("Send: " + ClipboardHeders.TRANSMISION_ACK); 
	}
}
