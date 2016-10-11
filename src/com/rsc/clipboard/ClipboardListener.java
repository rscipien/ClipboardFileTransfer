package com.rsc.clipboard;

import java.util.UUID;

public class ClipboardListener implements Runnable {
	private ClipboradHelper helper;
	private ClipboardMessageHandler messageHandler;
	private String id = UUID.randomUUID().toString();
//	if nothing is send or receiver sleep for 1 second 
//	private boolean standBy = true;
	
	public ClipboardListener(ClipboardMessageHandler messageHandler, ClipboradHelper helper) {
		this.messageHandler = messageHandler;
		this.helper = helper;
		if (!messageHandler.startSending(id)) {
			Logger.log("Listen Mode");
		}
	}
	
	@Override
	public void run() {
		Logger.info("Start Listening clipboard");
		while (true) {
			String content = helper.getCliboarContent();
			boolean handled = messageHandler.handle(content, id);
			
			if (messageHandler.isEndListening()) {
				return;
			}
			
			if (!handled) {
				try {
					if (messageHandler.isStandBy()) 
						Thread.sleep(1000); 
					else 
						Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
