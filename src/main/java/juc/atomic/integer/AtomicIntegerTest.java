package juc.atomic.integer;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

	public static void main(String[] args) {

		AtomicInteger cnt = new AtomicInteger(0);
		cnt.incrementAndGet();
		int i = cnt.get(); // 获得值
		System.out.println(i);
		i = cnt.incrementAndGet(); // 先+1,然后在返回值,相当于++i
		System.out.println(i);
		i = cnt.getAndIncrement();// 先返回值,然后在+1,相当于i++
		System.out.println(i);
		i = cnt.get();
		System.out.println(i);
//		i = cnt.decrementAndGet();// 先-1,然后在返回值,相当于--i
//		System.out.println(i);
//		i = cnt.getAndDecrement();// 先返回值,然后在-1,相当于i--
//		System.out.println(i);
//		i = cnt.get();
//		System.out.println(i);// 1
//		i = cnt.addAndGet(1);// 先+n,然后在返回值,
//		System.out.println(i);
//		i = cnt.getAndAdd(1);// 先返回,然后+n
//		System.out.println(i);
		// TODO Auto-generated method stub
//		cnt.compareAndSet(i, 0);
	}

}
