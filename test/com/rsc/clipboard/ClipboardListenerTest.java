package com.rsc.clipboard;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ClipboardListenerTest {

	// thread should end immediately  
	@Test(timeout=1000)
	public void testListenerEnd() {
		ClipboardMessageHandler handler = mock(ClipboardMessageHandler.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		doReturn(true).when(handler).isEndListening();
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
	
	// thread should end immediately
	@Test(timeout=1000)
	public void testOneMessageHandled() {
		ClipboardMessageHandler handler = mock(ClipboardMessageHandler.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		when(handler.isEndListening()).thenReturn(false).thenReturn(true);
		when(handler.handle(anyString(), anyString())).thenReturn(true);
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
	
	// thread should end in 10ms
	@Test(timeout=1000)
	public void testNotHandledMessage() {
		ClipboardMessageHandler handler = mock(ClipboardMessageHandler.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		when(handler.isEndListening()).thenReturn(false).thenReturn(true);
		when(handler.handle(anyString(), anyString())).thenReturn(false);
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
	
	// thread should end in 1s
	@Test(timeout=2000)
	public void testStendBy() {
		ClipboardMessageHandler handler = mock(ClipboardMessageHandler.class);
		ClipboradHelper helper = mock(ClipboradHelper.class);
		when(handler.isEndListening()).thenReturn(false).thenReturn(true);
		when(handler.handle(anyString(), anyString())).thenReturn(false);
		when(handler.isStandBy()).thenReturn(true);
		
		ClipboardListener listener = new ClipboardListener(handler, helper);
		listener.run();
	}
}
