package SpyPrice;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFile {
	private BufferedReader reader=null;
	public BufferedReader getBuffer(String fileName){
		try {
			reader = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reader;
	}
}
