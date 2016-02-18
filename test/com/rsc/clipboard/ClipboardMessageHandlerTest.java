package com.rsc.clipboard;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;


public class ClipboardMessageHandlerTest {

	
	@Test
	public void messageArrivedTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();
		
		String content = ClipboardHeders.TRANSMISION_START + "ID";
		ClipboardMessageHandler handler = new ClipboardMessageHandler(null, null, null, sender, parser);
		boolean handled = handler.handle(content, "AA");
		
		verify(sender).acceptTansmision(anyString());
		assertTrue(handled);
		assertFalse(handler.isStandBy());
	}
	
	@Test
	public void messageAcceptedTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		ClipboardParser parser = new ClipboardParser();
		
		String content = ClipboardHeders.TRANSMISION_ACK + "ID";
		ClipboardMessageHandler handler = new ClipboardMessageHandler(helper, fileSender, null, null, parser);
		boolean handled = handler.handle(content, "AA");
		
		verify(fileSender).prepareFilePart(anyString());
		assertTrue(handled);
		assertFalse(handler.isStandBy());
	}

	
	@Test
	public void messageFileArrivedTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();
		
		String content = ClipboardHeders.TRANSMISION_PART + "ID";
		ClipboardMessageHandler handler = new ClipboardMessageHandler(helper, fileSender, fileReader, sender, parser);
		boolean handled = handler.handle(content, "AA");
		
		verify(fileReader).addPart(anyString());
		assertTrue(handled);
		assertFalse(handler.isStandBy());
	}
	
	@Test
	public void messageEndOfFileTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();
		
		String content = ClipboardHeders.TRANSMISION_PART_END + "ID";
		ClipboardMessageHandler handler = new ClipboardMessageHandler(helper, fileSender, fileReader, sender, parser);
		boolean handled = handler.handle(content, "AA");
		
		verify(fileReader).createFile();
		assertTrue(handled);
		assertTrue(handler.isEndListening());
		assertTrue(handler.isStandBy());
	}

	@Test
	public void messageNotHandledTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();

		String content = "";
		ClipboardMessageHandler handler = new ClipboardMessageHandler(helper, fileSender, fileReader, sender, parser);
		boolean handled = handler.handle(content, "AA");
		
		assertFalse(handled);
	}
	
	@Test
	public void startSendingTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		
		ClipboardMessageHandler handler = new ClipboardMessageHandler(helper, fileSender, null, null, null);
		boolean start = handler.startSending("AA");
		
		assertTrue(start);
	}
	
	@Test
	public void startSendingTest2() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		
		ClipboardMessageHandler handler = new ClipboardMessageHandler(helper, null, null, null, null);
		boolean start = handler.startSending("AA");
		
		assertFalse(start);
	}
}
