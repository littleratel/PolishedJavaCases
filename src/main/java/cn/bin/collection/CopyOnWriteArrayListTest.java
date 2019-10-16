package cn.bin.collection;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopyOnWriteArrayListTest {

	public static void main(String[] args) {
		// 1、初始化CopyOnWriteArrayList
		List<Integer> tempList = Arrays.asList(new Integer[] { 1, 2 });
		CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<Integer>(tempList);

		// 2、模拟多线程对list进行读和写
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new ReadThread("Tread-Read-1",copyList));
		executorService.execute(new WriteThread("Tread-Write-1", copyList));
		executorService.execute(new WriteThread("Tread-Write-2", copyList));
		executorService.execute(new ReadThread("Tread-Read-2",copyList));
		executorService.execute(new WriteThread("Tread-Write-3", copyList));

//		executorService.shutdown();
		System.out.println("copyList size:" + copyList.size());
	}

	public static class ReadThread implements Runnable {
		private List<Integer> list;

		public ReadThread(String name,List<Integer> list) {
			this.list = list;
			Thread.currentThread().setName(name);
		}

//		@Override
		public void run() {
			for (Integer ele : list) {
				System.out.println(Thread.currentThread().getName() +":" + ele);
			}
		}
	}

	public static class WriteThread implements Runnable {
		private List<Integer> list;

		public WriteThread(String name, List<Integer> list) {
			this.list = list;
			Thread.currentThread().setName(name);
		}

//		@Override
		public void run() {
			this.list.add(9);
		}
	}
}
