package com.rsc.cliboard;

import java.io.File;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClipboardFileReaderTest {
	
	@Test
	public void testDefaulOutFile() {
		String expectedFile = "." + File.separator + "out";
		ClipboardFileReader reader = new ClipboardFileReader();
		String file = reader.getOutFile();
		
		assertEquals(expectedFile, file);
	}
	
	
	@Test
	public void testSpecificOutFile() {
		String expectedFile = "/home/out.jar";
		ClipboardFileReader reader = new ClipboardFileReader();
		reader.setFileLocation("/home/out.jar");
		String file = reader.getOutFile();
		
		assertEquals(expectedFile, file);
	}
	
	@Test
	public void testemptyOutFile() {
		String expectedFile = "." + File.separator + "out";
		ClipboardFileReader reader = new ClipboardFileReader();
		reader.setFileLocation("");
		String file = reader.getOutFile();
		
		assertEquals(expectedFile, file);
	}
}
