package designpattern.chainmodel;

public class FilterTest {

    public static void main(String[] args) {

        Filter chain = new CssFilter().setNext(new AbstractFilter() {
            @Override
            void doService(String msg) {
                System.out.println("This is a inner class.");
            }
        });

        chain =  new JsFilter().setNext(chain);
        chain =  new HTMLFilter().setNext(chain);

        chain.doFilter("Hello Filters");
    }


}
