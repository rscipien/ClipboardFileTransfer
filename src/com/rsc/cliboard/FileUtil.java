package com.rsc.cliboard;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.DatatypeConverter;

public class FileUtil {
	public static final int MAX_LENGTH = 4096;
	public static final int MAX_LENGTH_B64 = 10000;
	
	public static byte[] read(File file) throws IOException {
		
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
	
	public static void writeToFile(byte[] arr) {
		String strFilePath = "C://demo.zip";
	    try {
	      FileOutputStream fos = new FileOutputStream(strFilePath);
	      fos.write(arr);
	      fos.close();
	     
	     } catch(FileNotFoundException ex) {
	    	 ex.printStackTrace();
	     } catch(IOException ioe) {
	    	 ioe.printStackTrace();
	     }
	}
	
	
	public static String base64Decode(byte[] arr) {
		 String b64 = DatatypeConverter.printBase64Binary(arr);
		 return b64;
	}
	
	public static byte[] base64Encode(String base64) {
		 byte[] arr = DatatypeConverter.parseBase64Binary(base64);
		 return arr;
	}
}
