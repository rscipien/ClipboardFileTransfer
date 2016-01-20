package com.rsc.cliboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

public class ClipboradHelper {
	
	private Clipboard clipboard;
	
	public ClipboradHelper(Clipboard clipboard) {
		this.clipboard = clipboard;
	}
	
	public String getCliboarContent() {
		String ret = "";
		ClipboardRespons respons = null;
		try {
			do {
				respons = read();
				if (!respons.isValid()) {
					Logger.log("Try again read from clipboard locked");
					Thread.sleep(10);
				} else {
					ret = respons.getContent();
					//Logger.log("Content " + ret);
				}
				
				
			} while (!respons.isValid());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public ClipboardRespons writeToCliboard(String value) {
		ClipboardRespons respons = null;
		try {
			do {
				respons = write(value);
				if (!respons.isValid()) {
					Logger.log("Try again write to clipboard locked");
					Thread.sleep(10);
				}
			} while (!respons.isValid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return respons;
	}
	
	private ClipboardRespons read() {
		ClipboardRespons res = new ClipboardRespons();
		try {
			String bRet = (String) clipboard.getData(DataFlavor.stringFlavor);
			res.setContent(bRet);
			res.setValid(true);
		} catch (Exception e) {
			res.setValid(false);
		}
		return res;
	}
	
	private ClipboardRespons write(String value) {
		ClipboardRespons res = new ClipboardRespons();
		try {
			clipboard.setContents(new StringSelection(value), null);
			res.setValid(true);
		} catch (Exception e) {
			res.setValid(false);
		}
		
		return res;
	}

}
