package com.rsc.cliboard;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.mockito.internal.matchers.Any;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

public class Base64PartTest {

	@Test
	public void getPartEndTest() throws IOException {
		BufferedInputStream bis = mock(BufferedInputStream.class);
		when(bis.read(any(byte[].class))).thenReturn(-1);
		
		Base64Part base64 = new Base64Part(bis, 1024);
		base64.getPart();
		
		assertTrue(base64.isEnd());
	}
	
	@Test
	public void getPartEndTest2() throws IOException {
		InputStream is = new ByteArrayInputStream("test data".getBytes());
		BufferedInputStream bis = new BufferedInputStream(is);
		
		Base64Part base64 = new Base64Part(bis, 5);
		assertEquals(FileUtil.base64Decode("test ".getBytes()), base64.getPart());
		assertFalse(base64.isEnd());
		assertEquals(FileUtil.base64Decode("data".getBytes()), base64.getPart());
		assertTrue(base64.isEnd());
	}
	
}
