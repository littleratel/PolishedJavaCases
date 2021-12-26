package thread.threadlocal;

import com.rabbitmq.tools.json.JSONUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

        List lst = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            nextHashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            lst.add(nextHashCode & (n - 1));
        }

        Arrays.sort(lst.toArray());
        System.out.println(lst.stream().sorted().collect(Collectors.toList()));
    }
}
