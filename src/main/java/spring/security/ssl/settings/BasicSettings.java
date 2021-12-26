package spring.security.ssl.settings;

public interface BasicSettings {
    String HOST = "localhost";//127.0.0.1
    int PORT = 21245;
    String URL = "http://" + HOST + ":" + PORT;
    int SECURE_PORT = 21244;
    String SECURE_URL = "https://" + HOST + ":" + SECURE_PORT;

    String SIMPLE_TEST_RESOURCE = URL + "/test";
}
