package com.rsc.cliboard;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

	public static void log(String mes) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
		StringBuilder msg = new StringBuilder();
		msg.append(dt.format(new Date()));
		msg.append(" ");
		msg.append(mes);
		
		System.out.println(msg.toString());
	}
}
