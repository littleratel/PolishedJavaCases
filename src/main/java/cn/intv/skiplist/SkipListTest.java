package cn.intv.skiplist;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        for (int i = 1; i < 100000; i++) {
            skipList.insert(i);
        }
    }
}
