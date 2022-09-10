package com.lucascram.tilegraphicsgame.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWriter {
	
	private PrintWriter writer;
	
	public FileWriter() {
		
	}
	
	public void openFile(String filePath) throws FileNotFoundException {
		try {
			writer = new PrintWriter(filePath, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			;
		}
	}
	
	public void writeLineToFile(String line) {
		writer.println(line);
	}
	
	public void closeFile() throws IOException {
		if(writer != null) {
			writer.close();
		}
		writer = null;
	}
}
