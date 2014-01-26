package prac;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class webPrac {//the function in this method may return null value. 
	public String getOneHtml(final String htmlUrl){
		URL url;
		String temp=null;
		StringBuffer content=new StringBuffer();
		try {
			url=new URL(htmlUrl);
			BufferedReader in= new BufferedReader(new InputStreamReader(url.openStream()));
			while((temp=in.readLine())!=null) {
				
				content.append(temp);
			}
			in.close();
		} catch (MalformedURLException me) {
			// TODO Auto-generated catch block
			System.out.println("The URL format you input is wrong, please try it again!");
			me.getMessage();
			me.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content.toString();
		//http://download.finance.yahoo.com/d/quotes.csv?s=GOOG&f=d1snl1c1p2h0g0v0p0
	}
	public ArrayList<String> getCSVparameter(final String webUrl){
		URL url;
		ArrayList<String> array=new ArrayList<String>();
		String temp=null;
		try{
			url=new URL(webUrl);
			BufferedReader in= new BufferedReader(new InputStreamReader(url.openStream()));
			while((temp=in.readLine())!=null) array.add(temp);
			in.close();
		}catch (MalformedURLException me) {
			// TODO Auto-generated catch block
			System.out.println("The URL format you input is wrong, please try it again!");
			me.getMessage();
			me.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}
	
	//only for test
	public static void main(String [] args){
		webPrac wc=new webPrac();
		String symbol="gs";
		String url="http://www.streetinsider.com/ec_earnings.php?q=";
		String wholeUrl=url+symbol;
		String result = wc.getOneHtml(wholeUrl);
		//System.out.println(result);
		String regEx="Normal Earnings Time";
		Pattern p=Pattern.compile(regEx);
		Matcher m=p.matcher(result);
		//System.out.println(m.find());
		String answer=null;
		int a=m.groupCount();
		
		if(m.find()==true){
			 answer=m.group();
		}
		
		String regEx1="\\d*/\\d*/\\d{1,2}.*surprise";
		p=Pattern.compile(regEx1);
		m=p.matcher(result);
		while(m.find()==true){
			answer=m.group();
			System.out.println(answer);
//			int aa=m.start();
//			System.out.println(aa);
		}
		
//		
//		String regEx2="Q\\d\\d\\d";
//		p=Pattern.compile(regEx2);
//		m=p.matcher(result);
//		while(m.find()==true){
//			answer=m.group();
//			System.out.println(answer);
//		}
		
		
	}
}
