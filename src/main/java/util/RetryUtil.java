package util;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public class RetryUtil {

    public static <T> T retry(int maxRetries, long delayMillis, Supplier<T> function, Predicate<T> condition) {

        for (int i = 1; i <= maxRetries; i++) {
            System.out.println("【延时重试】开始第{}次尝试!" + i);
            try {
                T t = function.get();
                if (condition.test(t)) {
                    return t;
                }
            } catch (Exception e) {
                System.out.println("【延时重试】忽略第{}次异常:" + e);
            }

            if (i >= maxRetries) {
                break;
            }

            try {
                Thread.sleep(delayMillis);
            } catch (Exception e) {
                // ignore
            }
        }

        System.out.println("【延时重试】重试{}次数未成功!" + maxRetries);
        throw new RuntimeException("重试未成功");
    }
}
