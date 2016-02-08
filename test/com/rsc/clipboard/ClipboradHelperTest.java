package com.rsc.clipboard;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.junit.Test;

import com.rsc.clipboard.ClipboardRespons;
import com.rsc.clipboard.ClipboradHelper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClipboradHelperTest {

	@Test
	public void writeSuccesTest() {
		Clipboard clip = mock(Clipboard.class);
		doNothing().when(clip).setContents(any(Transferable.class), any(ClipboardOwner.class));
		ClipboradHelper helper = new ClipboradHelper(clip);
		
		ClipboardRespons resp = helper.writeToCliboard("AAA");
		assertTrue(resp.isValid());
	}
	
	
	@Test
	public void writeLockSuccesTest() {
		Clipboard clip = mock(Clipboard.class);
		doThrow(new IllegalStateException()).doNothing().when(clip).setContents(any(Transferable.class), any(ClipboardOwner.class));
		ClipboradHelper helper = new ClipboradHelper(clip);
		
		ClipboardRespons resp = helper.writeToCliboard("AAA");
		assertTrue(resp.isValid());
	}
	
	@Test
	public void readSuccesTest() throws UnsupportedFlavorException, IOException {
		String clipContent = "AAA";
		Clipboard clip = mock(Clipboard.class);
		doReturn(clipContent).when(clip).getData(DataFlavor.stringFlavor);
		ClipboradHelper helper = new ClipboradHelper(clip);
		
		String resp = helper.getCliboarContent();
		assertEquals(clipContent, resp);
	}

	@Test
	public void lockAndReadSuccesTest() throws UnsupportedFlavorException, IOException {
		String clipContent = "AAA";
		Clipboard clip = mock(Clipboard.class);
		doThrow(new IllegalStateException()).doReturn(clipContent).when(clip).getData(DataFlavor.stringFlavor);
		ClipboradHelper helper = new ClipboradHelper(clip);
		
		String resp = helper.getCliboarContent();
		assertEquals(clipContent, resp);
	}

}
