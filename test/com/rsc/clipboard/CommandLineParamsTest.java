package com.rsc.clipboard;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class CommandLineParamsTest {
	@Test
	public void testNoParams() throws Exception {
		String[] args = new String[0];
		FileUtil fileUtil = mock(FileUtil.class);
		CommandLineParams cmd = new CommandLineParams(args, fileUtil);
		cmd.parse();
		ClipboardFileReader fileReader = cmd.getFileReader();
		ClipboardFileSender fileSender = cmd.getFileSender();
		
		assertTrue(fileReader != null);
		assertTrue(fileSender == null);
	}
	
	
	@Test
	public void testDump() throws Exception {
		String[] args = {"-d", "d:\\aa"};
		FileUtil fileUtil = mock(FileUtil.class);
		doReturn("AAA").when(fileUtil).base64Decode(any(byte[].class));
		CommandLineParams cmd = new CommandLineParams(args, fileUtil);
		cmd.parse();
		
		assertTrue(cmd.isDump());
	}
	@Test
	public void testSender() throws Exception {
		String[] args = {"-f", "d:\\aa"};
		FileUtil fileUtil = mock(FileUtil.class);
		CommandLineParams cmd = new CommandLineParams(args, fileUtil);
		cmd.parse();
		ClipboardFileReader fileReader = cmd.getFileReader();
		ClipboardFileSender fileSender = cmd.getFileSender();
		
		assertTrue(fileReader == null);
		assertTrue(fileSender != null);
	}
}
