package cn.bin.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List lst = Arrays.asList("a1", "b1", "c1", "a2", "b2", "c2", "a3", "b3", "c2", "<->", "c1", "a2", "b3");
        long result = lst.stream()
                .skip(5)
                .limit(10)
                .distinct()
                .filter(ch -> !ch.toString().endsWith("1"))
                .count();
//                .anyMatch(ch -> ch.toString().equals("<->"));
        System.out.println(result);
    }

    public static void test1() {
        Supplier<Stream<String>> supplier = () -> Stream.of("a1", "b1", "c1", "d1", "a2", "b2", "c2", "d2", "a3", "b3", "c3", "d3", "<->", "c1", "a2", "b3");
        System.out.println(supplier.get().count());

        // unordered()
        supplier.get().unordered().map(s -> s + " ").forEach(System.out::print);
        System.out.println();
        supplier.get().parallel().map(s -> s + " ").forEachOrdered(System.out::print);
        System.out.println();
        supplier.get().parallel().unordered().map(s -> s + " ").forEach(System.out::print);
        System.out.println();

        // filter
        supplier.get().filter(s -> s.startsWith("a")).collect(Collectors.toList());
        System.out.println();

        // map  入参为 Function
        supplier.get().map(s -> s.length()).forEach(System.out::print);
        System.out.println();
        //mapToInt / mapToLong / mapToDouble
        supplier.get().mapToInt(s -> s.length()).forEachOrdered(System.out::print);
        System.out.println();

        // peek 接受一个Consumer，它的实现只有一个方法，且返回类型为void
        supplier.get().peek(e -> System.out.println("Peeked value: " + e)).map(String::toUpperCase).peek(e -> System.out.println("Mapped value: " + e)).collect(Collectors.toList());
    }

    // flat map  是把高纬度流变为低纬度流
    private static void test1_1() {
        String[] strs1 = {"a", "b", "c", "d"};
        String[] strs2 = {"b", "c", "d", "e"};
        String[] strs3 = {"f", "g", "h"};
        Arrays.asList(strs1, strs2, strs3).stream().flatMap(str -> Stream.of(str)).distinct().map(s -> s + ", ").forEach(System.out::print);
    }

    private static void test2() {
        //  non-short-circuiting:
        //  forEach()/forEachOrdered()/toArray()/reduce()/collect()/max()/min()/count()

        Supplier<Stream<String>> supplier = () -> Stream.of("a1", "bb1", "ccc1", "dddd1", "<->", "c1", "a2", "b3");

        //reduce(), reduce 返回的是Optional, 是一种容器，可以存储一些值和null
        supplier.get().map(s -> s.length()).reduce((sum, i) -> sum = sum + i).ifPresent(System.out::print);

        // max() / min()
        supplier.get().map(s -> s.length()).max((o1, o2) -> o1.compareTo(o2));

        // count()
        System.out.println(supplier.get().count());
    }

    private static void test3() {
        Supplier<Stream<String>> supplier = () -> Stream.of("a1", "b1", "c1", "abc", "bcde", "ca", "<->", "c1", "b1", "a3");

        // distinct()
        String output = supplier.get().distinct().collect(Collectors.joining(","));
        System.out.println(output);

        // sorted()
        output = supplier.get().sorted().collect(Collectors.joining(","));
        System.out.println(output);
        output = supplier.get().sorted(Comparator.reverseOrder()).collect(Collectors.joining(","));
        System.out.println(output);
        output = supplier.get().sorted((s1, s2) -> Integer.compare(s1.length(), s2.length())).collect(Collectors.joining(","));
        System.out.println(output);

        // limit()
        output = supplier.get().limit(3).collect(Collectors.joining(","));
        System.out.println(output);

        // skip()
        output = supplier.get().skip(3).collect(Collectors.joining(","));
        System.out.println(output);

        //concat()
        Stream<Integer> stream = Arrays.asList(1, 2, 3).stream();
        Stream.concat(supplier.get(), stream).forEach(System.out::print);
    }

    //Stream 复用
    public static void test4() {
        Stream<String> stream = Stream.of("d2", "a2", "a1", "b1", "a3").filter(s -> s.startsWith("a"));
        stream.anyMatch(s -> true); // ok
        stream.noneMatch(s -> true); // exception

        Supplier<Stream<String>> streamSupplier = () -> Stream.of("d2", "a2", "a1", "b1", "a3")
                .filter(s -> s.startsWith("a"));
        streamSupplier.get().anyMatch(s -> true); // ok
        streamSupplier.get().noneMatch(s -> true); // ok
    }
}
