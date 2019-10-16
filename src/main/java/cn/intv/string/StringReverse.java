package cn.intv.string;

public class StringReverse {

	public static void main(String[] args) {
		String str = "ac---cde--d-bf";
		System.out.println("原串为: " + str);
		String result = reverse1(str);
		System.out.println("反转后: " + result);
		result = stringReverse(str);
		System.out.println("反转后: " + result);
	}

	/**
	 * 实现字符串反转，如: 原串为: ac---cde--d-bf 反转后: bf-d--cde---ac
	 */
	private static String reverse1(String str) {
		int len = str.length();
		StringBuilder sb = new StringBuilder(len);

		int begin;
		for (int i = len - 1; i >= 0; i--) {
			begin = i;
			if (str.charAt(i) == '-') {
				do {
					i--;
				} while (i >= 0 && str.charAt(i) == '-');
			} else {
				do {
					i--;
				} while (i >= 0 && str.charAt(i) >= 'a' && str.charAt(i) <= 'z');
			}
			sb.append(str.substring(++i, begin + 1));
		}

		return sb.toString();
	}

	/**
	 * 字符串输入：str = “abcdef” 输出：fedcba
	 */
	private static String stringReverse(String str) {
		char[] arr = str.toCharArray();
		int len = str.length() - 1;
		int mid = len / 2;
		char tmp = ' ';
		for (int i = 0; i <= mid; i++) {
			tmp = arr[i];
			arr[i] = arr[len - i];
			arr[len - i] = tmp;
		}

		return String.valueOf(arr);
	}

}
