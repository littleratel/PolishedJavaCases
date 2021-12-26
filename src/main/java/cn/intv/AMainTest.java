package cn.intv;

public class AMainTest {
    public static void main(String[] args) {
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();
        System.out.println(dog);
        System.out.println(dog.equals("Animals are equal: " + pig));
    }
}