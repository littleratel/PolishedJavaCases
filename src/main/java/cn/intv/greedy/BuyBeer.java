package cn.intv.greedy;

import org.junit.Test;

import java.util.Arrays;


/**
 * 啤酒2块一瓶，3个酒瓶或者5个瓶盖可以再换一瓶啤酒。
 * 30块钱，最多能喝多少瓶?
 */
public class BuyBeer {

    @Test
    public void mainTest() {
        int beers = 30 / 2;
        int res = getMaxBeer(beers, beers, beers);
        System.out.println(res);
    }

    private int getMaxBeer(int beers, int bottle, int cap) {
        if (bottle < 3 && cap < 5) {
            return beers;
        }

        int newBeers = bottle / 3 + cap / 5;
        beers += newBeers;

        bottle = bottle % 3 + newBeers;
        cap = cap % 5 + newBeers;

        return getMaxBeer(beers, bottle, cap);
    }

}