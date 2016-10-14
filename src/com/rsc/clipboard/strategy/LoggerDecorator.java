package com.rsc.clipboard.strategy;

import com.rsc.clipboard.Logger;

public class LoggerDecorator implements MessageHandlerStrategy {

	private MessageHandlerStrategy strategy;
	
	public LoggerDecorator(MessageHandlerStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public boolean handle(String content, String id) {
		long startTime = System.currentTimeMillis();
		
		boolean bRet = strategy.handle(content, id);
		
		long endTime = System.currentTimeMillis();
		Logger.info("Message arrived time(ms): " + (endTime - startTime) + " Do notingh count: " /*+ doNothingCount*/);
		//doNothingCount = 0;
		
		return bRet;
	}
	
	@Override
	public boolean isEnd() {
		return strategy.isEnd();
	}
}
