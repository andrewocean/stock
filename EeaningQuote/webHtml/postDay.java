package webHtml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class postDay {
	public static String PostDay(String date){
		Calendar cal = Calendar.getInstance();
		
		
		
		SimpleDateFormat myFormatter = new SimpleDateFormat("M/d/yy");
		Date tempDate;
		try {
			tempDate = myFormatter.parse(date);
			cal.setTime(tempDate);
			cal.add(Calendar.DATE, 1);
			Date a=cal.getTime();
			SimpleDateFormat time=new SimpleDateFormat("M/d/yy");
			return time.format(a);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
	}
	public static int compareDate(String firstDate, String secondDate){
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yy");
		try {
			Date date = formatter.parse(firstDate);
			Date date2 =formatter.parse(secondDate);
			return date.compareTo(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 10;
		}
	}
}
