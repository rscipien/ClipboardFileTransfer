package com.rsc.clipboard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HashGeneratorTest {

	@Test
	public void hashTest() {
		String original = "123qweASD";
		HashGenerator gen = new HashGenerator();
		String hash = gen.md5(original);
		
		assertEquals("46e44aa0bc21d8a826d79344df38be4b", hash);
	}
	
	@Test
	public void hashNullTest() {
		HashGenerator gen = new HashGenerator();
		String hash = gen.md5(null);
		
		assertEquals("", hash);
	}
}
