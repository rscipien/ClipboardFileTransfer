package com.rsc.clipboard.strategy;


import com.rsc.clipboard.ClipboardFileReader;
import com.rsc.clipboard.ClipboardHeders;
import com.rsc.clipboard.ClipboardParser;
import com.rsc.clipboard.ClipboardSender;
import com.rsc.clipboard.HashGenerator;
import com.rsc.clipboard.Logger;

public class ReciverFileStrategy implements MessageHandlerStrategy {

	// private List<String> status = Arrays.asList(new String[] {"FILE_PART",
	// "FILE_END"});
	private ClipboardParser parser;
	private ClipboardSender sender;
	private ClipboardFileReader fileReader;
	private HashGenerator generator;
	private boolean end;

	public ReciverFileStrategy(ClipboardParser parser, ClipboardSender sender,
			ClipboardFileReader fileReader, HashGenerator generator) {
		this.parser = parser;
		this.sender = sender;
		this.fileReader = fileReader;
		this.generator = generator;
		Logger.log("Reciver file mode");
	}

	@Override
	public boolean handle(String content, String id) {
		boolean handled = true;
		
		if (parser.checkIfFileArrived(content, id)) {
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART);

			String b64 = parser.getBase64(content);
			checkHash(content, b64);
			
			fileReader.addPart(b64);
			sender.acceptTansmision(id);
		} else if (parser.checkIfEndOfFile(content, id)) {
			Logger.log("Read: " + ClipboardHeders.TRANSMISION_PART_END);
			String b64 = parser.getBase64(content);
			checkHash(content, b64);

			fileReader.addPart(b64);
			fileReader.createFile();
			sender.endTansmision();
			end = true;
		} else {
			handled = false;
		}

		return handled;
	}

	@Override
	public boolean isEnd() {
		return end;
	}
	
	private void checkHash(String content, String b64) {
		String hash = parser.getPartHash(content);
		String hashLocal = generator.md5(b64);
		if (!hash.equals(hashLocal)) {
			Logger.info("ERROR different hash between server and client");
		}
	}

}
