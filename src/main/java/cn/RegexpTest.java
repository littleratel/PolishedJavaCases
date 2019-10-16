package cn;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpTest {

	public static void main(String[] args) {
		String PATTERN = "(\\d)\\s+\\w+\\s+\\w+\\s+\\d+(\\s+\\w+\\s+\\d+-\\d+-\\d+\\s+\\d+:\\d+\\s+-?\\d+\\s+\\w+\\s+\\d+\\s+(.*))?";
		String PATTERN_1 = "(\\d)\\s+\\w+\\s+\\w+\\s+\\d+(\\s+\\w+\\s+\\d+-\\d+-\\d+\\s+\\d+:\\d+\\s+-?(\\d+\\s+)?\\w+\\s+\\d+\\s+(CXP.*))?";
		String PATTERN_2 = "(\\d)\\s+\\w+\\s+\\w+\\s+\\d+\\s+\\w+\\s+\\d+-\\d+-\\d+\\s+\\d+:\\d+\\s+-?(\\d+\\s+)?\\w+\\s+\\d+\\s+(.*)";
		ArrayList<String> lst = new ArrayList<String>();
		lst.add("0 Unlocked Working 55050240 No 2017-11-07 19:00 0 Boot 5 CXP9034469%20_R5KG");
		lst.add("1 Unlocked Working  775864320  No   2018-12-16 21:00 1460 Applic 10  CXP2030020%4_P36N673");
		lst.add("2 Unlocked Working  775864320  No   2018-12-17 13:00 1461Applic 10  CXP2030020%4_R13A51");
		lst.add("3 Unlocked Erased   775864320");
		Pattern p = Pattern.compile(PATTERN_2);
		for (String st : lst) {
			Matcher m = p.matcher(st);
			if (m.find()) {
				System.out.println(m.group(1) + " <==> " + m.group(3));
			} else {
				System.out.println("Not matched, with str: " + st);
			}
		}
	}

}
