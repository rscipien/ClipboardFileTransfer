package com.rsc.clipboard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.bind.DatatypeConverter;

public class FileUtil {
	public static final int MAX_LENGTH = 4096;
	public static final int MAX_LENGTH_B64 = 10000;
	
	public byte[] read(File file) throws IOException {
		
		    ByteArrayOutputStream ous = null;
		    InputStream ios = null;
		    try {
		        byte[] buffer = new byte[MAX_LENGTH];
		        ous = new ByteArrayOutputStream();
		        ios = new FileInputStream(file);
		        int read = 0;
		        while ((read = ios.read(buffer, 0, MAX_LENGTH)) != -1) {
		            ous.write(buffer, 0, read);
		        }
		    }finally {
		        try {
		            if (ous != null)
		                ous.close();
		        } catch (IOException e) {
		        }

		        try {
		            if (ios != null)
		                ios.close();
		        } catch (IOException e) {
		        }
		    }
		    return ous.toByteArray();
	}
	
	public void writeToFile(byte[] arr, String fileName) {
	    try {
	      FileOutputStream fos = new FileOutputStream(fileName);
	      fos.write(arr);
	      fos.close();
	     
	     } catch(FileNotFoundException ex) {
	    	 ex.printStackTrace();
	     } catch(IOException ioe) {
	    	 ioe.printStackTrace();
	     }
	}
	
	public boolean isFileExists(String name) {
		File fileToTransfer = new File(name);
		if (!fileToTransfer.exists()) {
			Logger.log("File doesn't exists");
			return false;
		}
		
		return true;
	}
	
	public BufferedInputStream prepareBufferedInputStream(String file) throws FileNotFoundException {
		BufferedInputStream bis = null;
		if (isFileExists(file)) {
			FileInputStream fis  = new FileInputStream(new File(file));
			bis = new BufferedInputStream(fis);
		}
		
		return bis;
	}
	
	public BufferedOutputStream prepareBufferedOutputStream() throws FileNotFoundException {
		return new BufferedOutputStream(new FileOutputStream("out"));
	} 
	
	public String base64Decode(byte[] arr) {
		//String b64 = DatatypeConverter.printBase64Binary(arr);
		long startTime = System.currentTimeMillis();
		BASE64Encoder encoder = new BASE64Encoder();
		String b64 = encoder.encodeBuffer(arr);
		long endTime = System.currentTimeMillis();
		Logger.log("Base64 decode time(ms): " + (endTime - startTime));
		return b64;
	}
	
	public byte[] base64Encode(String base64) {
		//byte[] arr = DatatypeConverter.parseBase64Binary(base64);
		long startTime = System.currentTimeMillis();
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] arr = null;
		try {
			arr = decoder.decodeBuffer(base64);
			long endTime = System.currentTimeMillis();
			Logger.log("Base64 encode time(ms): " + (endTime - startTime));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;
	}
}
