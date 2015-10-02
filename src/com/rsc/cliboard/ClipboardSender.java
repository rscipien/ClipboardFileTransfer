package com.rsc.cliboard;


public class ClipboardSender {

	private CliboradHelper clipHelper;
	
	public ClipboardSender(CliboradHelper clipHelper) {
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
