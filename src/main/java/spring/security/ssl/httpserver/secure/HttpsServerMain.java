package spring.security.ssl.httpserver.secure;

import spring.security.ssl.settings.ServerSettings;
import org.glassfish.grizzly.http.server.*;
import org.glassfish.grizzly.http.util.HttpStatus;
import org.glassfish.grizzly.ssl.SSLContextConfigurator;
import org.glassfish.grizzly.ssl.SSLEngineConfigurator;

import java.io.IOException;

public class HttpsServerMain {
    public static void main(String[] args){
        SSLContextConfigurator sslContextConfigurator = new SSLContextConfigurator();
        sslContextConfigurator.setKeyStoreFile("./src/main/resources/keystore_server");
        sslContextConfigurator.setKeyStorePass("keystoreserver");
//        sslContextConfigurator.setKeyPass("keystoreserver");

        sslContextConfigurator.setTrustStoreFile("./src/main/resources/truststore_server");
        sslContextConfigurator.setTrustStorePass("truststoreserver");

        SSLEngineConfigurator sslEngineConfigurator = new SSLEngineConfigurator(sslContextConfigurator);
        sslEngineConfigurator.setClientMode(false);
        sslEngineConfigurator.setNeedClientAuth(false);
//        sslEngineConfigurator.setEnabledCipherSuites(new String[]{"TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 for TLSv1"});


        NetworkListener networkListener = new NetworkListener("mListener", "localhost", ServerSettings.HTTPS_PORT);
        networkListener.setSecure(true);
        networkListener.setSSLEngineConfig(sslEngineConfigurator);

        HttpServer httpServer = new HttpServer();
        httpServer.addListener(networkListener);
        httpServer.getServerConfiguration().addHttpHandler(new HttpHandler() {
            @Override
            public void service(Request request, Response response) throws Exception {
                System.out.println("***************Header********************");
                for (String headerName : request.getHeaderNames()) {
                    System.out.println(headerName + ": " + request.getHeader(headerName));
                }
                System.out.println("*************Header end******************");
                System.out.println("isSecured:" + request.isSecure());
                System.out.println("authType:" + request.getAuthType() + "  authorization:" + request.getAuthorization());
                System.out.println("****************content***************");
                System.out.println("Method: " + request.getMethod());

                response.setStatus(HttpStatus.ACCEPTED_202);
            }
        });

        try {
            httpServer.start();
            System.out.println("Press any key to stop...");
            System.in.read();
//            Thread.currentThread().join();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
