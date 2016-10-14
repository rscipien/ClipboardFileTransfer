package com.rsc.clipboard;

import org.junit.Test;

import com.rsc.clipboard.strategy.MessageHandlerStrategy;
import com.rsc.clipboard.strategy.ReciverFileStrategy;

import static org.mockito.Mockito.*;

public class ClipboardListenerTest {

	@Test(timeout=1000)
	public void testListenerEnd() {
		MessageHandlerStrategy handler = mock(ReciverFileStrategy.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		doReturn(true).when(handler).isEnd();
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
	
	@Test(timeout=1000)
	public void testOneMessageHandled() {
		MessageHandlerStrategy handler = mock(ReciverFileStrategy.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		when(handler.isEnd()).thenReturn(false).thenReturn(true);
		when(handler.handle(anyString(), anyString())).thenReturn(true);
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
	
	@Test(timeout=2000)
	public void testNotHandledMessage() {
		MessageHandlerStrategy handler = mock(ReciverFileStrategy.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		when(handler.isEnd()).thenReturn(false).thenReturn(true);
		when(handler.handle(anyString(), anyString())).thenReturn(false);
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
}
