package juc.atomic.integer;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestAtomicIntegerFieldUpdater {
	private static final AtomicIntegerFieldUpdater<Student> scoreUpdate = AtomicIntegerFieldUpdater
			.newUpdater(Student.class, "Socre");

	public static void main(String[] args) throws InterruptedException {
		Thread[] ts = new Thread[9];
		final Student stu = new Student();
		
		for (int j = 0; j < 9; j++) {
			ts[j] = new Thread(new Runnable() {
				@Override
				public void run() {
					scoreUpdate.getAndAdd(stu, 10);
				}
			});
			ts[j].start();
		}
		
		for (int i = 0; i < 9; i++) {
			ts[i].join();
		}
		
		System.out.println(stu.getSocre());
	}
}

class Student {
	int id;
	volatile int Socre;
	String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSocre() {
		return Socre;
	}

	public void setSocre(int socre) {
		Socre = socre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
