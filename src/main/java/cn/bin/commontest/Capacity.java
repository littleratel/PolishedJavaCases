package cn.bin.commontest;

/**
 * java JDK 中各种计算容量的算法
 * 
 **/
public class Capacity {

	public static void main(String[] args) {
		initArrayDequeCapacity(11);
		initArrayListCapacity();
	}

    private static void initArrayDequeCapacity(int numElements) {
    	int MIN_INITIAL_CAPACITY = 8;
    	
        int initialCapacity = MIN_INITIAL_CAPACITY;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        
        System.out.println(initialCapacity);
    }
	
    private static void initArrayListCapacity() {
        int c = 6;
        int n = c - 1;

        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;

        int minResult = (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE: n + 1;
        System.out.println(minResult);

        int maxResult = n-(n>> 1);
        System.out.println(maxResult);
    }
}
