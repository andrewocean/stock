package prac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class orginizedData {
	public ObjectHashMapArray getDate(String urltemp) {
		ObjectHashMapArray Ob = new ObjectHashMapArray();

		HashMap<String, Integer> dateArray = new HashMap<String, Integer>();
		ArrayList<dateDoubleArray> stockData = new ArrayList<dateDoubleArray>();

		URL url;
		String temp = null;
		int index = 0;
		try {
			url = new URL(urltemp);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			in.readLine();
			while ((temp = in.readLine()) != null) {
				String[] lineArray = temp.split(",");

				// add method dateTrans to transfer the date format
				String newDate = dateTrans.dateTran(lineArray[0]);
				dateArray.put(newDate, index); // put date and index to the
												// Map,So call date to get index
												// of ArrayList.
				// System.out.println(newDate);
				index++;

				double[] fourData = new double[4];// setting the fixed value for
													// close price
				// add Open,High,Low,Close to the double[]
				fourData[0] = Double.parseDouble(lineArray[1]);
				fourData[1] = Double.parseDouble(lineArray[2]);
				fourData[2] = Double.parseDouble(lineArray[3]);
				fourData[3] = Double.parseDouble(lineArray[4]);
				// add (data and double[]) = to ArrayList
				stockData.add(new dateDoubleArray(newDate, fourData));
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
		Ob.setData(dateArray, stockData);
		return Ob;
	}

	// test

//	public static void main(String[] args) {
//		String symbol = "gs";
//		String urltemp = "http://ichart.yahoo.com/table.csv?s=" + symbol
//				+ "&a=02&b=26&c=2009&d=08&e=30&f=2015&g=d&ignore=.csv";
//		orginizedData test = new orginizedData();
//		ObjectHashMapArray ob = test.getDate(urltemp);
//		HashMap<String, Integer> hash = ob.getHash();
//		ArrayList<dateDoubleArray> array = ob.getArray();
//		System.out.println(hash.get("11/13/12"));
//		dateDoubleArray xx = array.get(296);
//		System.out.println(xx.getDate());
//	}

}
