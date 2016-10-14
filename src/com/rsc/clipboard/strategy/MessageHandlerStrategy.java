package com.rsc.clipboard.strategy;

public interface MessageHandlerStrategy {

	public boolean handle(String content, String id);
	public boolean isEnd();
}
