package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import webHtml.regExHtml;

public class webContent {// the function in this method may return null value.
	public ArrayList<String> getOneHtml(final String htmlUrl) {// only special
																// for the
																// earnning
																// report, can
																// be modified
																// better!
		URL url;
		String temp = null;
		// StringBuffer content = new StringBuffer();
		ArrayList<String> result = new ArrayList<String>();
		try {
			url = new URL(htmlUrl);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String regEx = "Normal Earnings Time";
			String regEx1 = "\\d*/\\d*/\\d\\d";
			regExHtml reg = new regExHtml();

			while ((temp = in.readLine()) != null) {
				String tempString = reg.groupSingle(regEx, temp);
				if (tempString.equals(regEx)) {
					temp = in.readLine().substring(5, 10);
					result.add(temp);
					break;
				}
			}
			while ((temp = in.readLine()) != null) {
				Pattern n = Pattern.compile(regEx1);
				Matcher m = n.matcher(temp);
				String temp1 = null;

				if (m.find()) {
					temp1 = m.group();
					in.readLine();
					in.readLine();
					String temp2 = in.readLine();
					String temp3 = in.readLine();
					temp2 = temp2.substring(6, 10);
					temp2 = temp1 + "," + temp2;
					String regEx2 = "\\d+.?\\d{1,2}";
					Pattern tempP = Pattern.compile(regEx2);
					Matcher tempM = tempP.matcher(temp3);
					// System.out.println(tempM.groupCount());

					// test if it has data or not Q114 without data
					int index1 = 0;
					while (tempM.find()) {
						index1++;
						// System.out.println(tempM.group());
						temp2 += "," + tempM.group();
					}
					if (index1 >= 2) {
						result.add(temp2);
					}

					// System.out.println(temp2);
					// System.out.println(temp3);
				}
			}

			in.close();

		} catch (MalformedURLException me) {
			// TODO Auto-generated catch block
			System.out
					.println("The URL format you input is wrong, please try it again!");
			me.getMessage();
			me.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result; // frist line is after or before judgement; afterthen:
						// date, q113,realPrice,pridictPrice

		// http://download.finance.yahoo.com/d/quotes.csv?s=GOOG&f=d1snl1c1p2h0g0v0p0
	}

	// only for test
	// public static void main(String [] args){
	// webContent wc=new webContent();
	// String symbol="axp";
	// String url="http://www.streetinsider.com/ec_earnings.php?q=";
	// String wholeUrl=url+symbol;
	// ArrayList array=wc.getOneHtml(wholeUrl);
	// for(int i=0;i<array.size();i++){
	// System.out.println(array.get(i));
	// }
	// System.out.println(result);
	// Pattern p=Pattern.compile(regEx);
	// Matcher m=p.matcher(result);
	// System.out.println(m.find());
	// String answer=null;
	// if(m.find()==true){
	// System.out.println(m.start());
	// answer=m.group();
	// System.out.println(result.substring(m.start()+30, m.start()+35));
	// }
	// String regEx1="\\d*/\\d*/\\d\\d";
	// p=Pattern.compile(regEx1);
	// m=p.matcher(result);
	// System.out.println(m.find());
	// while(m.find()==true){
	// answer=m.group();
	// System.out.println(answer);
	// }
	//
	// String regEx2="Q\\d\\d\\d";
	// p=Pattern.compile(regEx2);
	// m=p.matcher(result);
	// while(m.find()==true){
	// answer=m.group();
	// System.out.println(answer);
	// }

}
