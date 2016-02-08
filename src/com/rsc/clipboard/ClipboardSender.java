package com.rsc.clipboard;


public class ClipboardSender {

	private ClipboradHelper clipHelper;
	
	public ClipboardSender(ClipboradHelper clipHelper) {
		this.clipHelper = clipHelper;
	}
	
	public void acceptTansmision(String id) {
		clipHelper.writeToCliboard(ClipboardHeders.TRANSMISION_ACK + id);
		Logger.log("Send: " + ClipboardHeders.TRANSMISION_ACK); 
	}
	
	public void endTansmision() {
		clipHelper.writeToCliboard("");
		Logger.log("End Transmision"); 
	}
}
