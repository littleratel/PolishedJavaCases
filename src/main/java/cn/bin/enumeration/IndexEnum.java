package cn.bin.enumeration;

import java.util.Calendar;

public enum IndexEnum {
	INDEX_JENKINS("yosemite-jenkins"),
	INDEX_REPORT("yosemite-report"), 
	INDEX_JCAT("yosemite-jcat");

	private String indexSuffixRegex = "\\s*(\\d{4}-\\d{2})$";
	private final String INDEX_PPREFIX;

	IndexEnum(String indexPrefix) {
		this.INDEX_PPREFIX = indexPrefix + "-";
	}

	public String get() {
		return INDEX_PPREFIX + getIndexSuffix();
	}

	// like 2019-05, 2019-12
	private String getIndexSuffix() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		return year + "-" + (month > 9 ? "" : "0") + month;
	}

	/**
	 * Return the number of months from the current time based on the input index.
	 *
	 * @param index index string, like Yousmite-Jcat-2019-05
	 * @return number of months from the current time
	 */
	public int months(String index) {
		if (index.matches(indexSuffixRegex)) {
			return Integer.MAX_VALUE;
		}
		
		int len = index.length();
		int indexMonth = Integer.valueOf(index.substring(len - 2, len));
		int indexYear = Integer.valueOf(index.substring(len - 7, len - 3));

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		return (year - indexYear) * 12 + month - indexMonth - 1;
	}

}
