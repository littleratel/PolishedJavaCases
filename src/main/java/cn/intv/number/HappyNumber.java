package cn.intv.number;

import org.junit.Test;

import java.util.HashSet;

public class HappyNumber {

    @Test
    public void main() {
        System.out.println("IsHappy:" + isHappy(13));
        System.out.println("IsHappy:" + isHappy(2));
        System.out.println("IsHappy:" + isHappy(39));
    }

    public boolean isHappy(int cnt) {
        HashSet<Integer> set = new HashSet<>();
        set.add(cnt);
        while (true) {
            cnt = sumOfSquares(cnt);
            if (cnt == 1) {
                System.out.println("HAPPY NUMBER!");
                return true;
            } else if (!set.add(cnt)) {
                System.out.println("NOT A HAPPY NUMBER!");
                return false;
            }
        }
    }

    private int sumOfSquares(int n) {
        int sum = 0, tmp;
        while (n != 0) {
            tmp = n % 10;
            sum += Math.pow(tmp, 2);
            n /= 10;
        }

        return sum;
    }
}