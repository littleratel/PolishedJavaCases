package spring.security.ssl.httpclient.basic;

import spring.security.ssl.settings.BasicSettings;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ezuoyin on 3/8/17.
 */
public class SimpleHttpPost {
    public static void main(String[] Args) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(BasicSettings.SIMPLE_TEST_RESOURCE);
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "mUserName"));
            nvps.add(new BasicNameValuePair("password", "mSecret"));
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println(response.getStatusLine());
                HttpEntity entity = response.getEntity();

                try (InputStream content = entity.getContent()) {
                    byte[] readByte = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    System.out.println("Response:");
                    while ((len = content.read(readByte)) != -1) {
                        System.out.println(new String(readByte));
                    }
                }
                //System.out.println("EntitiyUtils:"+EntityUtils.toString(entity));

                //EntityUtils.consume(entity);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
