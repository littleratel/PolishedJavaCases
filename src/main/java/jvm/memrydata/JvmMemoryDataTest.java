package jvm.memrydata;

import org.junit.Test;

public class JvmMemoryDataTest {

    Runtime rt = Runtime.getRuntime();

    @Test
    public void testJvmMemortData() {
        long mem = rt.maxMemory();
        System.out.println("MaxMemory:" + mem);
        System.out.println("MaxMemory:" + mem / (1024 * 1024));

        mem = rt.totalMemory();
        System.out.println("TotalMemory:" + mem);
        System.out.println("TotalMemory:" + mem / (1024 * 1024));

        mem = rt.freeMemory();
        System.out.println("FreeMemory:" + mem);
        System.out.println("FreeMemory:" + mem / (1024 * 1024));
    }
}
