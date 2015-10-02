package com.rsc.cliboard;

import java.awt.datatransfer.DataFlavor;

public class CliboardParser {

	
	public boolean diffId(String content, String id) {
		String arr [] = content.split("\\|");
		String idMsg = arr[2];
		if (id.equals(idMsg))
			return false;
		else
			return true;
	}
	
	public String getBase64(String content) {
		String arr [] = content.split("\\|");
		return arr[3];
	}
	
	public boolean checkIfMeaasgeArrived(String content, String id) {
		if (content.startsWith(ClipboardHeders.TRANSMISION_START)) {
			return diffId(content, id);
		} else {
			return false;
		}
	}
	
	public boolean checkIfMessageAccepted(String content, String id) {
		if (content.startsWith(ClipboardHeders.TRANSMISION_ACK)) {
			return diffId(content, id);
		} else {
			return false;
		}
	}
	
	public boolean checkIfFileArrived(String content, String id) {
		if (content.startsWith(ClipboardHeders.TRANSMISION_PART)) {
			return diffId(content, id);
		} else {
			return false;
		}
	}
	
	public boolean checkIfEndOfFile(String content, String id) {
		if (content.startsWith(ClipboardHeders.TRANSMISION_PART_END)) {
			return diffId(content, id);
		} else {
			return false;
		}
	}
}
