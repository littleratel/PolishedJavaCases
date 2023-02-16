package cn.bin.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class QueueTest {

    public static void main(String[] args) {
        findKthValue();
//        testPriorityQueue();
//        testPriorityQueueBaseComparator();
    }

    private static void findKthValue() {
        int k = 3;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(5);
        q.add(6);

        System.out.println(q);
        System.out.println(q);
    }

    private static void testPriorityQueue() {
        Random rand = new Random(100);
        PriorityQueue<Integer> q = new PriorityQueue<>();
        System.out.print("Add: ");
        int randomNum;
        for (int i = 0; i < 10; i++) {
            randomNum = rand.nextInt(100);
            System.out.print(randomNum + " ");
            q.add(randomNum);
        }
        System.out.println();
        while (!q.isEmpty()) {
            System.out.print(q.poll() + " ");
        }
    }

    private static void testPriorityQueueBaseComparator() {
        @Data
        @AllArgsConstructor
        class Student {
            private String name;
            private int score;
        }

        PriorityQueue<Student> q = new PriorityQueue<>(new Comparator<Student>() {
            public int compare(Student o1, Student o2) {
                //按照分数低到高，分数相等按名字
                if (o1.getScore() == o2.getScore()) {
                    return o1.getName().compareTo(o2.getName());
                }
                return o1.getScore() - o2.getScore();
            }
        });

        //入列
        q.offer(new Student("dafei", 20));
        q.offer(new Student("will", 17));
        q.offer(new Student("setf", 30));
        q.offer(new Student("bunny", 20));

        //出列
        System.out.println(q.poll());  //Student{name='will', score=17}
        System.out.println(q.poll());  //Student{name='bunny', score=20}
        System.out.println(q.poll());  //Student{name='dafei', score=20}
        System.out.println(q.poll());  //Student{name='setf', score=30}
    }
}
