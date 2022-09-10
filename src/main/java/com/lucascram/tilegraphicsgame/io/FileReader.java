package com.lucascram.tilegraphicsgame.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileReader {
	
	InputStream 		fileInputStream;
	BufferedReader		bufferedReader;
	
	public FileReader() {
		
	}
	
	public void openFile(String filePath) throws FileNotFoundException {
		fileInputStream = new FileInputStream(filePath);
		bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, Charset.forName("UTF-8")));
	}
	
	public String getNextLine() {
		if(bufferedReader != null) {
			try {
				return bufferedReader.readLine();
			} catch(IOException e) {
				return null;
			}
		}
		return null;
	}
	
	public void closeFile() throws IOException {
		if(bufferedReader != null) {
			bufferedReader.close();
		}
		bufferedReader = null;
		fileInputStream = null;
	}
}
