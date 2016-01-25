package com.rsc.cliboard;

import org.junit.Test;

import static org.junit.Assert.*;

public class CliboarParserTest {

	String fakeHeader = "|FAKE|";
	
	@Test
	public void diffIdTest() {
		String id = "1111";
		String content = ClipboardHeders.TRANSMISION_FILE + id;
		ClipboardParser parser = new ClipboardParser();
		assertEquals(false, parser.diffId(content, id));
	}
	
	@Test
	public void diffIdTest2() {
		String id = "1111";
		String id2 = "2222";
		String content = ClipboardHeders.TRANSMISION_FILE + id;
		ClipboardParser parser = new ClipboardParser();
		assertEquals(true, parser.diffId(content, id2));
	}
	
	@Test
	public void getBase64Test() {
		String myId = "1111";
		//String remoteId = "2222";
		String content = "Content";
		String msg = ClipboardHeders.TRANSMISION_FILE + myId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		String resp = parser.getBase64(msg);
		
		assertEquals(content, resp);
	}
	
	
	@Test
	public void getBase64NoContentTest() {
		String myId = "1111";
		//String remoteId = "2222";
		String content = "";
		String msg = ClipboardHeders.TRANSMISION_FILE + myId + "|"; 
		ClipboardParser parser = new ClipboardParser();
		String resp = parser.getBase64(msg);
		
		assertEquals(content, resp);
	}
	
	@Test
	public void messageArrivedTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = ClipboardHeders.TRANSMISION_START + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfMessageArrived(msg, myId);
		
		assertTrue(ret);
	}
	
	@Test
	public void messageNotArrivedTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = fakeHeader + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfMessageArrived(msg, myId);
		
		assertFalse(ret);
	}
	
	@Test
	public void messageArrivedIgnoreOwmMessagesTest() {
		String myId = "1111";
		String content = "Content";
		String msg = ClipboardHeders.TRANSMISION_START + myId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfMessageArrived(msg, myId);
		
		assertFalse(ret);
	}
	
	@Test
	public void messageAcceptedTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = ClipboardHeders.TRANSMISION_ACK + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfMessageAccepted(msg, myId);
		
		assertTrue(ret);
	}
	
	@Test
	public void messageNotAcceptedTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = fakeHeader + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfMessageAccepted(msg, myId);
		
		assertFalse(ret);
	}
	
	
	@Test
	public void fileArrivedTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = ClipboardHeders.TRANSMISION_PART + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfFileArrived(msg, myId);
		
		assertTrue(ret);
	}
	
	@Test
	public void fileNotArrivedTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = fakeHeader + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfFileArrived(msg, myId);
		
		assertFalse(ret);
	}
	
	@Test
	public void endOfFileTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = ClipboardHeders.TRANSMISION_PART_END + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfEndOfFile(msg, myId);
		
		assertTrue(ret);
	}
	
	@Test
	public void notEndOfFileTest() {
		String myId = "1111";
		String remoteId = "2222";
		String content = "Content";
		String msg = fakeHeader + remoteId + "|" + content; 
		ClipboardParser parser = new ClipboardParser();
		boolean ret = parser.checkIfEndOfFile(msg, myId);
		
		assertFalse(ret);
	}
}
