package designpattern.visitor;

public interface Visitor {

//    void visit(Staff Staff);

    // 访问工程师类型
    void visit(Engineer engineer);

    // 访问经理类型
    void visit(Manager manager);
}