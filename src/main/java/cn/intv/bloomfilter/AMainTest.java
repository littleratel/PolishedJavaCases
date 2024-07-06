package cn.intv.bloomfilter;

public class AMainTest {
    public static void main(String[] args) {
        String[] urls = {"url1", "url2", "url3", "url5", "url7", "url3"};

        BloomFilter<String> bf = new BloomFilter<>(1_00_0000, 0.01);

        for (String url : urls) {
            if (!bf.put(url)) {
                System.out.println("URL is duplicated:" + url);
            }
        }
    }
}
