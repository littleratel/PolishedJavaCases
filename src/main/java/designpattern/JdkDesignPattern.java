package designpattern;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class JdkDesignPattern {

    public static void main(String[] args) {
        decoratorDemo();
        // bulidDemo();
    }

    private static void decoratorDemo() {
        String filePath = "src/main/resources/DesignPatternFile/decoratordemo.txt";
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
            String content = "";
            byte[] buffer = new byte[10240];
            int flag = bis.read(buffer);
            while (flag != -1) {
                content += new String(buffer, 0, flag);
                flag = bis.read(buffer);
            }
            System.out.println(content);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            String content = "New data is written to the file..";
            bos.write(content.getBytes(), 0, content.getBytes().length);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bulidDemo() {
        StringBuilder sb = new StringBuilder();
        sb.append("key-value");
    }
}
