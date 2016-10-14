package com.rsc.clipboard.strategy;

import com.rsc.clipboard.ClipboardFileSender;
import com.rsc.clipboard.ClipboardHeders;
import com.rsc.clipboard.ClipboardParser;
import com.rsc.clipboard.ClipboradHelper;
import com.rsc.clipboard.Logger;

public class SendFileStrategy implements MessageHandlerStrategy {

	private ClipboardParser parser;
	private ClipboardFileSender fileSender;
	private ClipboradHelper helper;
	private boolean onlyOnce = true;
	
	public SendFileStrategy(ClipboardParser parser, ClipboardFileSender fileSender, ClipboradHelper helper) {
		this.parser = parser;
		this.fileSender = fileSender;
		this.helper = helper;
		Logger.log("Send file mode");
	}
	
	
	@Override
	public boolean handle(String content, String id) {
		boolean handled = true;
		
		if (parser.checkIfMessageAccepted(content, id)) {
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_ACK);
			
			String msg = fileSender.prepareFilePart(id);
			helper.writeToCliboard(msg);
		} else if (onlyOnce) {
				Logger.log("First part");
				String msg = fileSender.prepareFilePart(id);
				helper.writeToCliboard(msg);
				onlyOnce = false;
		} else {
			handled = false;;
		}
		
		return handled;
	}

	@Override
	public boolean isEnd() {
		return false;
	}

}
