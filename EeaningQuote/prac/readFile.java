package prac;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class readFile {
	private BufferedReader reader;
	public readFile(String fileName){
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BufferedReader getBuffer(){
		return reader;
	}
}
