package jvm.jit;

public class EscapeAnalysis {

    public static void main(String[] args) {
        System.out.println("a+b=");
    }

    public int add(int a, int b) {
        return a + b;
    }
}
