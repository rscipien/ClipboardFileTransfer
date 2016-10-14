package com.rsc.clipboard;

import java.util.UUID;

import com.rsc.clipboard.strategy.MessageHandlerStrategy;

public class ClipboardListener implements Runnable {
	private ClipboradHelper helper;
	private MessageHandlerStrategy messageHandler;
	private String id = UUID.randomUUID().toString();
//	if nothing is send or receiver sleep for 1 second 
//	private boolean standBy = true;
	
	public ClipboardListener(MessageHandlerStrategy messageHandler, ClipboradHelper helper) {
		this.messageHandler = messageHandler;
		this.helper = helper;
	}
	
	/**
	 * Mozna zast¹pic flage StanBy flaga handle
	 * Client konczy prace na endListening
	 * Serwer ca³y czas dzia³a
	 */
	@Override
	public void run() {
		Logger.info("Start Listening clipboard");
		while (true) {
			String content = helper.getCliboarContent();
			boolean handled = messageHandler.handle(content, id);
			
			if (messageHandler.isEnd()) {
				return;
			}
			
			try {
				Thread.sleep(handled ? 10 : 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
	
}
