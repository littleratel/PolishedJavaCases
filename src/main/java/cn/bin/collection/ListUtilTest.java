package cn.bin.collection;

import com.google.common.collect.Lists;
import org.apache.commons.collections.ListUtils;

import java.util.List;

public class ListUtilTest {
    public static void main(String[] args) {
        ListUtilTest tool = new ListUtilTest();
        tool.getUnion();
    }

    /**
     * 求两个集合的交集 List
     */
    private void getUnion() {
        List<String> list1 = Lists.newArrayList("a", "b", "q");
        List<String> list2 = Lists.newArrayList("a", "b", "c", "d", "e");

        System.out.println(ListUtils.retainAll(list1, list2));
    }
}
