package com.rsc.clipboard;

import java.security.MessageDigest;

public class HashGenerator {

	public String md5(String original) {
		if (original == null)
			return "";
		
		StringBuffer sb = new StringBuffer();
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte[] digest = md.digest();
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
}
