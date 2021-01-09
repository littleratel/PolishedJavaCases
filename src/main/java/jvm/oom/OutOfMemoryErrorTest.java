package jvm.oom;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryErrorTest {

    private static final long SIZE_1_M = 1024 * 1024;

    @Test
    public void testHsErrPidLog() {
        List lst = new ArrayList<>();

        for (int i = 1; i < 50; i++) {
            System.out.println("allocate 200M, index: " + i);
            lst.add(testAllocate(50));
        }
    }

    private byte[] testAllocate(int size) {
        return new byte[(int) (size * SIZE_1_M)];
    }
}
