package spring.security.ssl.httpserver.basic;

import spring.security.ssl.settings.ServerSettings;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.Request;
import org.glassfish.grizzly.http.server.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by ezuoyin on 3/7/17.
 */
public class ServerMain {
    public static void main(String[] args) {
        HttpServer httpServer = HttpServer.createSimpleServer(null, ServerSettings.HTTP_PORT);
        httpServer.getServerConfiguration().addHttpHandler(new HttpHandler() {
            @Override
            public void service(Request request, Response response) throws Exception {
                System.out.println("***************Header********************");
                for (String headerName : request.getHeaderNames()) {
                    System.out.println(headerName + ": " + request.getHeader(headerName));
                }
                System.out.println("*************Header end******************");

                System.out.println("****************content***************");
                System.out.println("Method: " + request.getMethod());
//                response.setStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED_505);
                if (request.getMethod().matchesMethod("GET")) {
                    printOutparameters(request);
                    Thread.sleep(1000);
                    Writer writer = response.getWriter();
                    writer.write("hello world!!!Get!!!");
                    writer.close();
                } else if (request.getMethod().matchesMethod("POST")) {
                    printOutContent(request);
                    Writer writer = response.getWriter();
                    writer.write("hello world!!!Post!!!");
                    writer.close();
                }
                System.out.println("*************content end******************");
            }
        }, ServerSettings.SIMPLE_TEST_PATH);

        try {
            httpServer.start();
            System.out.println("Press any key to stop...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOutparameters(Request request) {
        for (String parameterName : request.getParameterNames()) {
            System.out.println(parameterName + ":" + request.getParameter(parameterName));
        }
    }

    private static void printOutContent(Request request) {
        try (BufferedReader bi = new BufferedReader(request.getReader())) {
            String content;
            while ((content = bi.readLine()) != null) {
                System.out.println(content);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
