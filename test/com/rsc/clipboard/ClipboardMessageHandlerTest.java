package com.rsc.clipboard;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import com.rsc.clipboard.strategy.MessageHandlerStrategy;
import com.rsc.clipboard.strategy.ReciverFileStrategy;
import com.rsc.clipboard.strategy.SendFileStrategy;


public class ClipboardMessageHandlerTest {

	
	@Test
	public void messageAcceptedTest() {
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		ClipboardParser parser = new ClipboardParser();
		
		String content = ClipboardHeders.TRANSMISION_ACK + "ID";
		MessageHandlerStrategy handler = new SendFileStrategy(parser, fileSender, helper);
		boolean handled = handler.handle(content, "AA");
		
		verify(fileSender).prepareFilePart(anyString());
		assertTrue(handled);
	}

	
	@Test
	public void messageFileArrivedTest() {
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();
		HashGenerator generator = new HashGenerator();
		
		String content = ClipboardHeders.TRANSMISION_PART + "ID";
		MessageHandlerStrategy handler = new ReciverFileStrategy(parser, sender, fileReader, generator);
		boolean handled = handler.handle(content, "AA");
		
		verify(fileReader).addPart(anyString());
		assertTrue(handled);
	}
	
	@Test
	public void messageEndOfFileTest() {
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();
		HashGenerator generator = new HashGenerator();
		
		String content = ClipboardHeders.TRANSMISION_PART_END + "ID";
		MessageHandlerStrategy handler = new ReciverFileStrategy(parser, sender, fileReader, generator);
		boolean handled = handler.handle(content, "AA");
		
		verify(fileReader).createFile();
		assertTrue(handled);
		assertTrue(handler.isEnd());
	}

	@Test
	public void messageNotHandledTest() {
		ClipboardFileReader fileReader = mock(ClipboardFileReader.class);
		ClipboardSender sender = mock(ClipboardSender.class);
		ClipboardParser parser = new ClipboardParser();
		HashGenerator generator = new HashGenerator();
		
		String content = "";
		MessageHandlerStrategy handler = new ReciverFileStrategy(parser, sender, fileReader, generator);
		boolean handled = handler.handle(content, "AA");
		
		assertFalse(handled);
	}
	
	@Test
	public void startSendingTest() {
		ClipboardParser parser = new ClipboardParser();
		ClipboradHelper helper = mock(ClipboradHelper.class);
		ClipboardFileSender fileSender = mock(ClipboardFileSender.class);
		
		MessageHandlerStrategy handler = new SendFileStrategy(parser, fileSender, helper);
		boolean handled = handler.handle("", "");
		
		assertTrue(handled);
	}
}
