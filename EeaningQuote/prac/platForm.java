package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class platForm {
	public static void main(String [] args){
		readFile getFile=new readFile("sp500list.txt");
		BufferedReader reader = getFile.getBuffer();
		String line=null;
		try {
			while((line=reader.readLine())!=null){
				yahooDeal y=new yahooDeal();
				ArrayList<String> whole = y.callYahooEarnEx(line);
				for(int i=0;i<whole.size();i++){
					System.out.println(whole.get(i));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
