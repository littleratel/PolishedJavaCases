package designpattern.chainmodel;

class JsFilter extends AbstractFilter {

    @Override
    void doService(String msg) {
        System.out.println("JsFilter doFilter: " + msg);
    }
}
