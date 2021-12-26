package designpattern.chainmodel;

interface Filter {

    Filter setNext(Filter next);

    void doFilter(String msg);
}

