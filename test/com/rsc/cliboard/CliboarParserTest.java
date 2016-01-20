package com.rsc.cliboard;

import org.junit.Test;
import static org.junit.Assert.*;

public class CliboarParserTest {

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
}
