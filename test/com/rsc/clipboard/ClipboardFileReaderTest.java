package com.rsc.clipboard;

import java.io.BufferedOutputStream;
import java.io.File;

import org.junit.Test;

import com.rsc.clipboard.ClipboardFileReader;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ClipboardFileReaderTest {
	
	@Test
	public void testDefaulOutFile() {
		BufferedOutputStream bos = mock(BufferedOutputStream.class);
		String expectedFile = "." + File.separator + "out";
		ClipboardFileReader reader = new ClipboardFileReader(bos);
		String file = reader.getOutFile();
		
		assertEquals(expectedFile, file);
	}
	
	
	@Test
	public void testSpecificOutFile() {
		BufferedOutputStream bos = mock(BufferedOutputStream.class);
		String expectedFile = "/home/out.jar";
		ClipboardFileReader reader = new ClipboardFileReader(bos);
		reader.setFileLocation("/home/out.jar");
		String file = reader.getOutFile();
		
		assertEquals(expectedFile, file);
	}
	
	@Test
	public void testemptyOutFile() {
		BufferedOutputStream bos = mock(BufferedOutputStream.class);
		String expectedFile = "." + File.separator + "out";
		ClipboardFileReader reader = new ClipboardFileReader(bos);
		reader.setFileLocation("");
		String file = reader.getOutFile();
		
		assertEquals(expectedFile, file);
	}
}
