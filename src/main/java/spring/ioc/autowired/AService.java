package spring.ioc.autowired;

public class AService {

    @MyAutowired
    private BService bService;
}
