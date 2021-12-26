package designpattern.chainmodel;

class HTMLFilter extends AbstractFilter {

    @Override
    void doService(String msg) {
        System.out.println("HTMLFilter doFilter: " + msg);
    }
}
