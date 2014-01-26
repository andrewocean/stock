package prac;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTrans {
	public static String dateTran(String dateOri){
		
	   // formatter  =   new  SimpleDateFormat ( "yyyy-MM-dd" );
		DateFormat formatter1 ; 
		Date date ; 
		String newDate=null;
		formatter1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date=formatter1.parse(dateOri);
			SimpleDateFormat formatter  =   new  SimpleDateFormat ( "M/d/yy" );
			newDate = formatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}
	
	//test
	/*
	public static void main(String [] args){
		String testDate=dateTran("2013-1-12");
		if(testDate==null){
			System.err.println("wrong date input!");
			return;
		}
		System.out.println(testDate);
	}
	*/
}
