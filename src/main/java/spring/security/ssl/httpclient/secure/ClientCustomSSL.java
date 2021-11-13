package spring.security.ssl.httpclient.secure;

import spring.security.ssl.settings.BasicSettings;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public class ClientCustomSSL {
    public final static void main(String[] args) {
        try {
            //Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(new File("./src/main/resources/truststore_client"),
                            "truststoreclient".toCharArray(), new TrustSelfSignedStrategy())
                    .loadKeyMaterial(new File("./src/main/resources/keystore_client"), "keystoreclient".toCharArray(),
                            "keystoreclient".toCharArray())
                    .build();
            //Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
                    null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            try {
                HttpGet httpget = new HttpGet(BasicSettings.SECURE_URL);

                System.out.println("Executing request " + httpget.getRequestLine());

                CloseableHttpResponse response = httpclient.execute(httpget);
                try {
                    HttpEntity entity = response.getEntity();

                    System.out.println("------------------------");
                    System.out.println(response.getStatusLine());
                    EntityUtils.consume(entity);
                } finally {
                    response.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException | CertificateException
                | IOException | UnrecoverableKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
