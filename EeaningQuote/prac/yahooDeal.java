package prac;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import webHtml.postDay;

public class yahooDeal {
	public ArrayList<String> getYahooCloseOpen(String symbol) {
		webContent wc = new webContent();
		// url for earning
		String urlEarn = "http://www.streetinsider.com/ec_earnings.php?q=";
		urlEarn += symbol;

		// url for Yahoo
		// 2010-03-30 2013-09-30
		String urlYahoo = "http://ichart.yahoo.com/table.csv?s=" + symbol
				+ "&a=02&b=26&c=2009&d=08&e=30&f=2015&g=d&ignore=.csv";
		orginizedData oDY = new orginizedData();
		ObjectHashMapArray oh = oDY.getDate(urlYahoo);

		// whole for the final return
		ArrayList<String> whole = new ArrayList<String>();

		// earnInfo get the info from earning website;
		ArrayList<String> earnInfo = wc.getOneHtml(urlEarn);

		// for(int i=0;i<earnInfo.size();i++){
		// System.out.println(earnInfo.get(i));
		// }

		// System.out.println(earnInfo.size());

		if (earnInfo.size() == 0) {// judge if earnInfo is empty or not----
			System.err.println("getOneHtml return nothing for:" + symbol);
			return whole;
		}

		if (earnInfo.get(0).equals("After")) {
			// System.out.println("aaaaaaaaa");
			// introduce ArrayList
			whole = dateToData(symbol, oh, earnInfo, 1);
		} else if (earnInfo.get(0).equals("Befor")) {
			// System.out.println("bbbbbbbbb");
			whole = dateToData(symbol, oh, earnInfo, 0);
		} else {
			//System.err.println("after and before judgement have problems for:"+ symbol);
			return whole;
		}
		return whole;
	}

	// input date, output two days price info
	public ArrayList<String> dateToData(String symbol, ObjectHashMapArray oh,
			ArrayList<String> earn, int flag) {
		// get date first:
		ArrayList<String> afterWhole = new ArrayList<String>();
		// System.out.println("flag:"+flag);
		for (int i = 1; i < earn.size(); i++) {

			String[] arrayStrSpl = earn.get(i).split(",");// earn: date,
															// q113,realPrice,pridictPrice
			String date = arrayStrSpl[0];
			HashMap<String, Integer> hash = oh.getHash();
			ArrayList<dateDoubleArray> array = oh.getArray();
			// System.out.println(date);

			String startDate = array.get(array.size() - 1).getDate();
			String endDate = array.get(0).getDate();
			if (postDay.compareDate(date, startDate) <= 0
					|| postDay.compareDate(date, endDate) >= 0) {
				//System.err.println(symbol+ ": this one has problem, it continues:" + date+","+ startDate +",s"+ endDate);
				continue;
			}
			while (hash.get(date) == null) {
				date = postDay.PostDay(date);
			}
			int index = hash.get(date);// may return null

			String[] priceStr = new String[2];
			String whole = null;
			// add Open,High,Low,Close to the double[]
			if (flag == 0) {// before

				double[] douArrayPre = array.get(index + 1).getNumArray();
				// System.out.println(array.get(index+1).getDate());
				double[] douArrayPost = array.get(index).getNumArray();
				// System.out.println(array.get(index).getDate());
				priceStr[0] = douArrayPre[0] + "," + douArrayPre[1] + ","
						+ douArrayPre[2] + "," + douArrayPre[3];
				priceStr[1] = douArrayPost[0] + "," + douArrayPost[1] + ","
						+ douArrayPost[2] + "," + douArrayPost[3];
				whole = earn.get(i) + "," + priceStr[0] + "," + priceStr[1];

			} else if (flag == 1) {// after
				double[] douArrayPre = array.get(index).getNumArray();
				// System.out.println(array.get(index).getDate());
				double[] douArrayPost = array.get(index - 1).getNumArray();
				// System.out.println(array.get(index-1).getDate());
				priceStr[0] = douArrayPre[0] + "," + douArrayPre[1] + ","
						+ douArrayPre[2] + "," + douArrayPre[3];
				priceStr[1] = douArrayPost[0] + "," + douArrayPost[1] + ","
						+ douArrayPost[2] + "," + douArrayPost[3];
				whole = earn.get(i) + "," + priceStr[0] + "," + priceStr[1];
			} else {
				System.err
						.println("dateToData has problems, wrong flag setting");
			}
			afterWhole.add(whole);
		}
		return afterWhole;
	}

	public arrayListPair callYahooEarn(String symbol) {
		yahooDeal y = new yahooDeal();
		ArrayList<String> whole = y.getYahooCloseOpen(symbol);
		ArrayList<String[]> dateQ = new ArrayList<String[]>();
		ArrayList<double[]> priceR = new ArrayList<double[]>();

		for (int i = 0; i < whole.size(); i++) {
			System.out.println(symbol + "," + whole.get(i));

			String[] array = whole.get(i).split(",");
			String[] dateQStr = new String[2];
			double[] priceD = new double[10];
			dateQStr[0] = array[0];
			dateQStr[1] = array[1].substring(2, 4) + array[1].substring(0, 2);
			for (int j = 0; j < 10; j++) {
				priceD[j] = Double.valueOf(array[j + 2]);
			}

			// calculation part //% between

			dateQ.add(dateQStr);
			priceR.add(priceD);
		}
		arrayListPair pair=new arrayListPair(dateQ,priceR);
		return pair;
	}
	
	public ArrayList<String> callYahooEarnEx(String symbol) {
		yahooDeal y = new yahooDeal();
		ArrayList<String> whole = y.getYahooCloseOpen(symbol);
//		ArrayList<String[]> dateQ = new ArrayList<String[]>();
//		ArrayList<double[]> priceR = new ArrayList<double[]>();
		ArrayList<String> result=new ArrayList<String>();
		for (int i = 0; i < whole.size(); i++) {

			String[] array = whole.get(i).split(",");
			String[] dateQStr = new String[2];
			double[] priceD = new double[10];
			dateQStr[0] = array[0];
			dateQStr[1] = array[1].substring(2, 4) + array[1].substring(0, 2);
			for (int j = 0; j < 10; j++) {
				priceD[j] = Double.valueOf(array[j + 2]);
			}
			//double format
			DecimalFormat df= new DecimalFormat("0.0000");
			
			// earning change percent
			double percent = (priceD[0]-priceD[1])/priceD[1];
			
			//second day open (priceD[6]) - first day close (priceD[5])
			double closeOpen=(priceD[6]-priceD[5])/priceD[5];
			
			//second day (high + low)/2 {(priceD[7]+priceD[8])/2} - first day (high+low)/2 (priceD[3]+priceD[4])/2
			double sec1=(priceD[7]+priceD[8])/2;
			double fir1=(priceD[3]+priceD[4])/2;
			double highLow=(sec1-fir1)/fir1;
			
			//second day (open+close)/2 {(priceD[6]+priceD[9])/2}   -   first day (open+close)/2 {(priceD[2]+priceD[5])/2} 
			double sec2=(priceD[6]+priceD[9])/2;
			double fir2=(priceD[2]+priceD[5])/2;
			double opClose=(sec2-fir2)/fir2;
			
			//second day (open+close+high+low)/4 {(priceD[6]+priceD[7]+priceD[8]+priceD[9])/4}   -   first day  (open+close+high+low)/4 {(priceD[2]+priceD[3]+priceD[4]+priceD[5])/4} 
			double sec3=(priceD[6]+priceD[7]+priceD[8]+priceD[9])/4;
			double fir3=(priceD[2]+priceD[3]+priceD[4]+priceD[5])/4;
			double wholePrice=(sec3-fir3)/fir3;
			
			
			//StringBuilder wholeStr=new StringBuilder(dateQStr[0]).append(dateQStr[1]);
			String wholeStr=symbol+","+dateQStr[0]+","+dateQStr[1];
			for(int j=0;j<priceD.length;j++){
				wholeStr=wholeStr+","+priceD[j];
			}
			wholeStr=wholeStr+","+df.format(percent)+","+df.format(closeOpen)+","+df.format(highLow) +","+ df.format(opClose)+","+df.format(wholePrice);
			result.add(wholeStr);
		}
		
		return result;
	}

	// test
	// public static void main(String[] args) {
	// yahooDeal y = new yahooDeal();
	// ArrayList<String> whole = y.getYahooCloseOpen("gs");
	// ArrayList<String[]> dateQ = new ArrayList<String[]>();
	// ArrayList<double[]> priceR = new ArrayList<double[]>();
	// for (int i = 0; i < whole.size(); i++) {
	// String[] array = whole.get(i).split(",");
	// String[] dateQStr = new String[2];
	// double[] priceD = new double[10];
	// dateQStr[0] = array[0];
	// dateQStr[1] = array[1].substring(2, 4) + array[1].substring(0, 2);
	// for (int j = 0; j < 10; j++) {
	// priceD[j] = Double.valueOf(array[j + 2]);
	// }
	// // calculation part //% between
	//
	// dateQ.add(dateQStr);
	// priceR.add(priceD);
	// }
	// test
	// for (int k = 0; k < dateQ.size(); k++) { //
	// String total = dateQ.get(k)[0] + "," + dateQ.get(k)[1] + ","
	// + priceR.get(k)[0] + "," + priceR.get(k)[9]; //
	// System.out.println(total);
	// }
	// }
}
