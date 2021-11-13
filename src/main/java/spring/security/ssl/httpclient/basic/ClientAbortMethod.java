package spring.security.ssl.httpclient.basic;

import spring.security.ssl.settings.BasicSettings;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ClientAbortMethod {
    public static void main(String[] args) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpGet httpget = new HttpGet(BasicSettings.SIMPLE_TEST_RESOURCE);

            System.out.println("Executing request: " + httpget.getURI());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("-----------------------------");
                System.out.println(response.getStatusLine());

                httpget.abort();
            } finally {
                response.close();
            }

        } finally {
            httpclient.close();
        }
    }
}
