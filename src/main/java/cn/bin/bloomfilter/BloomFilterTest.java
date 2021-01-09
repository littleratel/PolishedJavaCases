package cn.bin.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

public class BloomFilterTest {

    public static void main(String[] args) {

    	//1%，有个概率问题，布隆越大，占用的空间越多，但是错误概率减小了
        BloomFilter bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 1000000, 0.001);
        bloomFilter.put("maa");

		//为true表示在布隆过滤器里
        System.out.println(bloomFilter.mightContain("ma"));
    }
}
