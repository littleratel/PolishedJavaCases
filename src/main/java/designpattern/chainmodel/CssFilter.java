package designpattern.chainmodel;

class CssFilter extends AbstractFilter {

    @Override
    void doService(String msg) {
        System.out.println("CssFilter doFilter: " + msg);
    }
}
