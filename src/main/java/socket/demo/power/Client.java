package socket.demo.power;

public class Client {

    public static void main(String[] args) {
        String host = "127.0.0.1";
        int consPort = 5209;
        int port = 1;
        TS16PowerControl powers = new TS16PowerControl(host, consPort, port);

        boolean result;
//        result = powers.powerOff();
//        Thread.sleep(4000);
        result = powers.powerOnImp();
        System.out.println(result);
    }

}
