package cn.intv;

//利用Unicode编码是否在 0x4E00–0x9FCC 来判断某字符是否为汉字
public class ChineseSegregation {

	public static void main(String[] args) {
		String str = "我ABC汗DEF";
		printSubStrbyNum(str, 7);
	}

	private static void printSubStrbyNum(String str, final int num) {
		char leftBoundary = '\u4E00';
		char rightBoundary = '\u9FBF';

		char ch;
		int index = 0, numBk = num;
		StringBuilder sb = new StringBuilder();
		while (numBk > 0) {
			numBk--;
			if ((ch = str.charAt(index++)) <= rightBoundary && ch >= leftBoundary) {
				numBk--;
			}
			sb.append(ch);
		}
		System.out.println(sb.toString());
	}

	public boolean isChinesePunctuation(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION 
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
				|| ub == Character.UnicodeBlock.VERTICAL_FORMS) {
			return true;
		} else {
			return false;
		}
	}
}
