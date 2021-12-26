package thread.threadlocal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatFactoryTest {

	private static final Map<DatePattern, ThreadLocal<DateFormat>> map;

	static {
		DatePattern[] patterns = DatePattern.values();
		int len = patterns.length;
		map = new HashMap<DatePattern, ThreadLocal<DateFormat>>(len);

		for (int i = 0; i < len; i++) {
			DatePattern datePattern = patterns[i];
			final String pattern = datePattern.pattern;

			map.put(datePattern, new ThreadLocal<DateFormat>() {
				@Override
				protected DateFormat initialValue() {
					return new SimpleDateFormat(pattern);
				}
			});
		}
	}

	// 获取DateFormat
	public static DateFormat getDateFormat(DatePattern pattern) {
		ThreadLocal<DateFormat> threadDateFormat = map.get(pattern);
		// 不需要判断threadDateFormat是否为空
		return threadDateFormat.get();
	}

	public static void main(String[] args) {
		String dateStr = DateFormatFactoryTest.getDateFormat(DatePattern.TimePattern).format(new Date());
		System.out.println(dateStr);
	}
	
	public enum DatePattern {
		TimePattern("yyyy-MM-dd HH:mm:ss"), 
		DatePattern("yyyy-MM-dd");

		public String pattern;

		private DatePattern(String pattern) {
			this.pattern = pattern;
		}
	}
}