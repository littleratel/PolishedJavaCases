package cn.intv.skiplist;

import lombok.Data;

@Data
public class SkipListNode implements Comparable {
    private int value;
    private SkipListNode next = null;
    private SkipListNode downNext = null;

    @Override
    public int compareTo(Object o) {
        return this.value > ((SkipListNode) o).value ? 1 : -1;
    }
}
