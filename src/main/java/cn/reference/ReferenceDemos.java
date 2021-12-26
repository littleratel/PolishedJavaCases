package cn.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class ReferenceDemos {
    @Test
    public void phantomReferenceTest() {
        Object obj = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> phanRef = new PhantomReference<>(obj, refQueue);
        System.out.println("Get phanRef: " + phanRef.get());
        System.out.println("isEnqueued: " + phanRef.isEnqueued());
        System.out.println(refQueue.poll());

        System.out.println("<=================================================>");

        obj = null;
        System.gc();

        if (phanRef.isEnqueued()) {
            if (phanRef.enqueue()) {
                System.out.println("PhantomReference re-enqueued success!");
                System.out.println("obj is: " + obj);
            } else {
                System.out.println("PhantomReference re-enqueued failed!");
            }
        }else {
            System.out.println("PhantomReference not enqueued!");
        }

        System.out.println("Get phanRef: " + phanRef.get());
        System.out.println("isEnqueued: " + phanRef.isEnqueued());
        System.out.println(refQueue.poll());

        System.out.println("<=================================================>");

        System.out.println("Get phanRef: " + phanRef.get());
        System.out.println("isEnqueued: " + phanRef.isEnqueued());
        System.out.println(refQueue.poll());
    }

}
