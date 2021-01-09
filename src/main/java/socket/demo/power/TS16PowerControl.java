package socket.demo.power;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * A implementation class for TS16PowerControl
 */
public class TS16PowerControl {

    private static final int MAX_RCV_TRY = 10;
    private static final int DELAY_TIME = 2000;
    private static final int MAX_TRY_SENT_CMD_TIMES = 2;
    private static final int SUM_NUM = 255;

    private static final String POWER_ON = "POWER_ON";
    private static final String POWER_OFF = "POWER_OFF";
    private static final String READ_STATUS_IS_POWER_ON = "READ_STATUS_IS_POWER_ON";

    private final String ip;
    private final int consPort;
    private final int port;

    private String responsePowerOn;
    private String responsePowerOff;
    private String statusPowerOff;

    private void init() {
        String powerPrefix = "EA0101002008";
        String powerOnSuffix = "2070101";
        String powerOffSuffix = "2070100";
        String statusPowerOnPrefix = "EB0101002008";
        String statusPowerOffSuffix = "207000201";
        // EA010100200812070101D0
        responsePowerOn = powerPrefix + port + powerOnSuffix;
        // EA010100200812070100D1
        responsePowerOff = powerPrefix + port + powerOffSuffix;
        // EB01010020081207000201CE
        statusPowerOff = statusPowerOnPrefix + port + statusPowerOffSuffix;
    }

    TS16PowerControl(String ip, int consPort, int port) {
        super();
        this.ip = ip;
        this.consPort = consPort;
        this.port = port;
        init();
    }

    protected boolean powerOnImp() {
        return doPowerControl(POWER_ON);
    }

    protected boolean powerOffImp() {
        return doPowerControl(POWER_OFF);
    }

    protected boolean isPowerOnImp() {
        return doPowerControl(READ_STATUS_IS_POWER_ON);
    }

    private boolean doPowerControl(String cmdFlag) {
        try (
                Socket socket = new Socket(ip, consPort);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream()) {

            String response;
//            response = getResponse(in);
//            System.out.println("flush before send cmd, get response:" + response);

            String cmd = getPowerCmd(cmdFlag, this.port);
//            for (int i = 0; i < MAX_TRY_SENT_CMD_TIMES; i++) {
//                System.out.println("Send commend [" + cmd + "], index is" + i);
//                sendCommend(out, cmd);
//                response = getResponse(in);
//                System.out.println("Get response:" + response);
//            }

            System.out.println("Send the final power commend:" + cmd);
            sendCommend(out, cmd);
            response = getResponse(in);
            System.out.println("Get response :" + response);
            if (response.toUpperCase().contains(getPowerResult(cmdFlag))) {
                return true;
            }
        } catch (Exception e) {//NOSONAR
            System.out.println("Execute power cmd throw the exception :" + e); //NOSONAR
        }

        return false;//NOSONAR
    }

    private static void sendCommend(OutputStream out, String cmd) {
        try {
            byte[] somevar = javax.xml.bind.DatatypeConverter.parseHexBinary(cmd);
            out.write(somevar);
            out.flush();
        } catch (Exception e) {//NOSONAR
            System.out.println("Send power cmd throw the exception: " + e);//NOSONAR
        }
    }

    private static String getResponse(InputStream in) {

        StringBuilder printx = new StringBuilder();
        try {
            for (int idx = 0; in.available() <= 0 && idx < MAX_RCV_TRY; idx++) {
                System.out.println("No message from power controller, sleep 2000 ms and try again...");
                Thread.sleep(DELAY_TIME);
            }

            while (in.available() > 0) {
                int i = in.read();
                printx.append(hex(i));
            }
        } catch (Exception e) {//NOSONAR
            System.out.println("Get power response throw the exception:" + e);//NOSONAR
        }

        return printx.toString();
    }

    private static String getPowerCmd(String cmdFlag, int port) {
        int[] ret;
        if (POWER_ON.equals(cmdFlag)) {
            // like EA0141210008120701018f
            ret = new int[]{0xEA, 1, 0x41, 0x21, 0, 8, port * 16 + 2, 7, 1, 1, 0};//NOSONAR
        } else if (POWER_OFF.equals(cmdFlag)) {
            // like EA01412100081207010090
            ret = new int[]{0xEA, 1, 0x41, 0x21, 0, 8, port * 16 + 2, 7, 1, 0, 0};//NOSONAR
        } else if (READ_STATUS_IS_POWER_ON.equals(cmdFlag)) {
            // like E9014121000812070092
            ret = new int[]{0xE9, 1, 0x41, 0x21, 0, 8, port * 16 + 2, 7, 0, 0};//NOSONAR
        } else {
            System.out.println("Cannot get cmd string base on " + cmdFlag);
            return "ERROR";
        }

        computeCrc(ret);

        StringBuilder cmd = new StringBuilder();
        for (int i : ret) {
            cmd.append(hex(i));
        }

        return cmd.toString();
    }

    private static void computeCrc(int... ret) {
        int sum = 0;
        for (int item : ret) {
            sum += item;
        }

        ret[ret.length - 1] = SUM_NUM ^ (sum & SUM_NUM);
    }

    /**
     * Convert byte to hexadecimal string, keep the last two bit.
     * such as:
     * 1 -> 01, 234 -> ea
     */
    private static String hex(int x) {
        String hx = "0" + Integer.toHexString(x);
        return hx.substring(hx.length() - 2);
    }

    private String getPowerResult(String cmd) {
        if (POWER_ON.equals(cmd)) {
            return responsePowerOn;
        } else if (POWER_OFF.equals(cmd)) {
            return responsePowerOff;
        } else if (READ_STATUS_IS_POWER_ON.equals(cmd)) {
            return statusPowerOff;
        }

        System.out.println("Unable to match operation base on " + cmd);
        return "";
    }
}
