package SpyPrice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MarketWatchPriceSpy extends Thread {
	public String[] getRealTimePrice(String htmlUrl){
		//ReadFile file= new ReadFile();
		URL url;
		String line=new String();
		String regEx1="marketprice\">.*<";
		String regEx2="data bgLast\">.*<";
		String[] array=new String[4];
		int index=0;
		try {
			url = new URL(htmlUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			while((line=in.readLine())!=null){
				//System.out.println(line);
				RegExHtml reg=new RegExHtml();
				String result1=reg.groupSingle(regEx1, line);
				String result2=reg.groupSingle(regEx2, line);
				if(!result1.equals("")){
					index++;
					array[index]=result1;
					
				}
				if(!result2.equals("")){
					array[0]=result2;
				}
			}
		}
		 catch (IOException e1) {
			// TODO Auto-generated catch block
			System.err.println("BufferedReader return null error in marketWatchPrice");
			e1.printStackTrace();
		}
		return array;
	}
	
	//test
	public static void main(String[] args){
		MarketWatchPriceSpy prac=new MarketWatchPriceSpy();
		String [] tempStr=prac.getRealTimePrice("http://www.marketwatch.com/investing/fund/spy");
		tempStr[0]=tempStr[0].substring(13, tempStr[0].length()-1);
		tempStr[1]=tempStr[1].substring(13, tempStr[1].length()-1);
		tempStr[2]=tempStr[2].substring(13, tempStr[2].length()-1);
		tempStr[3]=tempStr[3].substring(13, tempStr[3].length()-1);
		//get time
		SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy/MM/dd/HH:mm:ss");  
		int num=0;
		ArrayList<String> whole=new ArrayList<String>();
		while(num<2500){
			num++;
			String tempOut = "";
			for(int i=0;i<tempStr.length;i++){
				tempOut+=tempStr[i]+";";
			}
			   
			 Date   curDate   =   new   Date(System.currentTimeMillis());//get time
			 tempOut=formatter.format(curDate)+";"+tempOut.substring(0, tempOut.length()-1);
			 whole.add(tempOut);
			 System.out.println(tempOut);
			try {
				sleep(10000);  //sleep for 10 second (every 10 second, query once real time data from MarketWatch.com)
			} catch(InterruptedException e){System.out.println("sleep also has problem?");}
		}
		
	}
}
