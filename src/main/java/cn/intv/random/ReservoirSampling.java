package cn.intv.random;

import java.util.Random;

/**
 * 为随机算法
 * <p>
 * 对于不知道大小的数据流，从中取出100个数，并希望这100个数尽可能地随机
 * 可以使用蓄水池抽样算法, 适用于无法提前知道数据总量的情况。
 */
public class ReservoirSampling {
    public static void main(String[] args) {
        int len = 1000;
        int[] stream = new int[len];
        for (int i = 0; i < len; i++) {
            stream[i] = i;
        }

        int k = 10;
        int[] result = reservoirSampling(stream, k);

        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    static int[] reservoirSampling(int[] stream, int k) {
        int[] reservoir = new int[k];
        Random random = new Random();

        // 填充蓄水池数组
        for (int i = 0; i < k; i++) {
            reservoir[i] = stream[i];
        }

        // 对剩余的数据进行抽样
        for (int i = k; i < stream.length; i++) {
            int j = random.nextInt(i + 1); // 取值范围: [0, i]

            if (j < k) {
                reservoir[j] = stream[i];
            }
        }

        return reservoir;
    }
}
