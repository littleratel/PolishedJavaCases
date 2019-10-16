package thread.threadlocal;

public class MagicNumber {

	/**
	 * HASH_INCREMENT = 0x61c88647 
	 * 
	 * def magic_hash(n): 
	 * 	for i in range(n):
	 * 		nextHashCode = i * HASH_INCREMENT + HASH_INCREMENT ... 
	 * 		print nextHashCode & (n - 1), 
	 *  print
	 */
	public static void main(String[] args) {
		magicNum(32);
	}

	public static void magicNum(int n) {
		long HASH_INCREMENT = 0x61c88647;
		long nextHashCode = 0L;

		for (int i = 0; i < n; i++) {
			nextHashCode = i * HASH_INCREMENT + HASH_INCREMENT;
			System.out.print((nextHashCode & (n - 1)) + ", ");
		}
	}
}
