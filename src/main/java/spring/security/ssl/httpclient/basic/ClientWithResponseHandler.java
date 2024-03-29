package spring.security.ssl.httpclient.basic;

import spring.security.ssl.settings.BasicSettings;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ClientWithResponseHandler {
    public static void main(String[] args){
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(BasicSettings.SIMPLE_TEST_RESOURCE);

            System.out.println("Executing request " + httpget.getRequestLine());

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;

                    } else {
                        throw new ClientProtocolException("Unexpected response status:" + status);
                    }
                }

            };
            String responseBody = httpClient.execute(httpget, responseHandler);
            System.out.println("------------------------------------");
            System.out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
