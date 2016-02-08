package com.rsc.clipboard;

import java.io.File;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class FileSenderTest {
	
	@Test
	public void test() {
		File file = mock(File.class);
		when(file.length()).thenReturn(1000L);
	}

}
