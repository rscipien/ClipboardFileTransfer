package com.rsc.clipboard;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ClipboardFileSenderTest {

	
	@Test
	public void checkHeaderEndPart() {
		Base64Part base64 = mock(Base64Part.class);
		doReturn(true).when(base64).isEnd();
		
		ClipboardFileSender sender = new ClipboardFileSender(base64);
		String msg = sender.prepareFilePart("AA");
		
		assertTrue("Should conating end part", msg.contains(ClipboardHeders.TRANSMISION_PART_END));
	}
	
	@Test
	public void checkHeaderPart() {
		Base64Part base64 = mock(Base64Part.class);
		doReturn(false).when(base64).isEnd();
		
		ClipboardFileSender sender = new ClipboardFileSender(base64);
		String msg = sender.prepareFilePart("AA");
		
		assertTrue("Should conating part header", msg.contains(ClipboardHeders.TRANSMISION_PART));
	}
	
	@Test
	public void checkMsgId() {
		String id = "12345AAAzZxa2w21";
		Base64Part base64 = mock(Base64Part.class);
		doReturn(false).when(base64).isEnd();
		
		ClipboardFileSender sender = new ClipboardFileSender(base64);
		String msg = sender.prepareFilePart(id);
		
		assertTrue("Should conating id", msg.contains(id));
	}
	
	@Test
	public void checkMsgPart() {
		String part = "aaaabbb3534534dsg";
		Base64Part base64 = mock(Base64Part.class);
		doReturn(part).when(base64).getPart();
		
		ClipboardFileSender sender = new ClipboardFileSender(base64);
		String msg = sender.prepareFilePart("id");
		
		assertTrue("Should conating part contnet", msg.contains(part));
	}
}
