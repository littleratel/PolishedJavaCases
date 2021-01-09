package designpattern.chainmodel;

abstract class AbstractFilter implements Filter {
    private Filter next;

    @Override
    public Filter setNext(Filter next) {
        this.next = next;
        return this;
    }

    @Override
    public void doFilter(String msg) {
        doService(msg);

        if (next != null) {
            next.doFilter(msg);
        }
    }

    abstract void doService(String msg);
}
