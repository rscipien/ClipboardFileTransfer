package com.rsc.clipboard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	public static boolean debug = true;
	
	public static void log(String mes) {
		if (debug) {
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			StringBuilder msg = new StringBuilder();
			msg.append(dt.format(new Date()));
			msg.append(" ");
			msg.append(mes);
			
			System.out.println(msg.toString());
		}
	}

	public static void info(String mes) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		StringBuilder msg = new StringBuilder();
		msg.append(dt.format(new Date()));
		msg.append(" ");
		msg.append(mes);
		
		System.out.println(msg.toString());
	}
}
