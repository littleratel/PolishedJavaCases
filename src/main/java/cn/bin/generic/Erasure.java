package cn.bin.generic;

/**
 * 泛型类中的类型参数 如果没有指定上限，如<T>则会被转译成普通的 Object 类型，
 * 如果指定了上限如 <T extends String>则类型参数就被替换成类型上限。
 */
//public class Erasure<T extends String> {
public class Erasure<T> {
    T name;

    public Erasure(T name) {
        this.name = name;
    }

    public void add(T name) {

    }
}