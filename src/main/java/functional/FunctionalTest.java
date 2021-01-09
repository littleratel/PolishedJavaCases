package functional;

public class FunctionalTest {
    //
    static String word = "Hello Java";

    public static void main(String[] args) {

        String word = "Hello Java";

        new Test() {  // 匿名方法类
            @Override
            public String say(String wd) {
                String word = "new word";  //
                System.out.println(word);
                return wd;
            }
        };

        Test t = (String wd) -> { // lambda
            // word = "new word";  // 不能声明相同名字变量
            System.out.println(word);
            return wd;
        };

        System.out.println(t.say(word));
    }
}

interface Test {

    static int call() {
        System.out.println("Test.class Call");
        return 0;
    }

    String say(String wd);
}

