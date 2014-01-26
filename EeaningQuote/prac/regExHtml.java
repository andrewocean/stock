package prac;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regExHtml {
	public String groupSingle(String pattern, String matcher) {  //""means did not find 
		Pattern p = Pattern.compile(pattern);// the regEx you write
		Matcher m = p.matcher(matcher);// the html words you get
		if (m.find())
			return m.group();
		else
			return "";
	}

	public ArrayList<String> groupMulti(String pattern, String matcher) {//if array size is 0, it did not find 
		Pattern p = Pattern.compile(pattern);// the regEx you write
		Matcher m = p.matcher(matcher);// the html words you get
		ArrayList<String> array = new ArrayList<String>();
		while (m.find()) {
			array.add(m.group());
		}
		return array;
	}
}
