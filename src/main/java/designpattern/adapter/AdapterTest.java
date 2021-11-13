package designpattern.adapter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

class AdapterTest {
    @Test
    void test() {
        String HttpServletRequest = "", HttpServletResponse = "";
        new DispatchServlet().doDispatch(HttpServletRequest, HttpServletResponse);
    }
}


// 模拟SpringMVC从request取handler的对象，仅仅new出，可以出，
// 不论实现何种Controller，适配器总能经过适配以后得到想要的结果
class DispatchServlet {

    public static List<HandlerAdapter> handlerAdapters = new ArrayList<HandlerAdapter>();

    public DispatchServlet() {
        handlerAdapters.add(new AnnotationHandlerAdapter());
        handlerAdapters.add(new HttpHandlerAdapter());
        handlerAdapters.add(new SimpleHandlerAdapter());
    }

    public void doDispatch(String HttpServletRequest, String HttpServletResponse) {
        // Request --> Handler --> HandlerAdapter --> HandlerAdapter.handle(handler)

        HttpController controller = new HttpController();
//      AnnotationController controller = new AnnotationController();
//      SimpleController controller = new SimpleController();

        HandlerAdapter adapter = getHandler(controller);

        adapter.handle(controller);
    }

    public HandlerAdapter getHandler(Controller controller) {
        for (HandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(controller)) {
                return adapter;
            }
        }

        return null;
    }
}


// Controller Implementers
class HttpController implements Controller {
    void doHttpHandler() {
        System.out.println(this.getClass().getSimpleName());
    }
}

class SimpleController implements Controller {
    void doSimplerHandler() {
        System.out.println(this.getClass().getSimpleName());
    }
}

class AnnotationController implements Controller {
    void doAnnotationHandler() {
        System.out.println(this.getClass().getSimpleName());
    }
}


// HandlerAdapter Implementers
class SimpleHandlerAdapter implements HandlerAdapter {
    public void handle(Object handler) {
        ((SimpleController) handler).doSimplerHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof SimpleController);
    }
}


class HttpHandlerAdapter implements HandlerAdapter {
    public void handle(Object handler) {
        ((HttpController) handler).doHttpHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof HttpController);
    }
}


class AnnotationHandlerAdapter implements HandlerAdapter {
    public void handle(Object handler) {
        ((AnnotationController) handler).doAnnotationHandler();
    }

    public boolean supports(Object handler) {
        return (handler instanceof AnnotationController);
    }
}

