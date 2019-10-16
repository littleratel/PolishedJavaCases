package spring.security.ssl.httpclient.basic;

import spring.security.ssl.settings.BasicSettings;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by ezuoyin on 3/8/17.
 */
public class SimpleHttpGet {
    public static void main(String[] args){
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(BasicSettings.SIMPLE_TEST_RESOURCE);

            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                System.out.println(response.getStatusLine());

                HttpEntity entity = response.getEntity();
//              System.out.println(EntityUtils.toString(entity));
                EntityUtils.consume(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
