package cn.intv.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateParenthesis {

    @Test
    public void main() {
        System.out.println(generateParenthesis(3));
    }

    /**
     * For example:
     * ()(), length = 4
     * inert () from index = 0,1,2,3 
     * */
    public List<String> generateParenthesis(int n) {
        if (n == 1) {
            return Arrays.asList("()");
        }

        Set<String> hs = new HashSet<>();
        for (String s : generateParenthesis(n - 1)) {
            for (int i = 0; i < s.length(); i++) {
                String str1 = s.substring(0, i);
                String str2 = s.substring(i);
                hs.add(str1 + "()" + str2);
            }
        }

        return new ArrayList(hs);
    }
}
