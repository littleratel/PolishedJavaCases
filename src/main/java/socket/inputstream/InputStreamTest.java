package socket.inputstream;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class InputStreamTest {

    @Test
    public void testWrite() throws IOException {
        String file = "/home/ezfanbi/repo/github/PolishedJavaCases/src/main/resources/socket/text.txt";
        String charset = "GBK";   // UTF-8
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);

        writer.write("I am fucking busy just get little money.\r\n");
        writer.write("知止而有得，谋定而后动");
        writer.close();
    }

    @Test
    public void testRead() throws IOException {
        File file = new File("/home/ezfanbi/repo/github/PolishedJavaCases/src/main/resources/socket/text.txt");
        byte[] b = new byte[2];
        InputStream in = new FileInputStream(file);
        int cnt;
        while ((cnt = in.read(b)) != -1) {
            String str = new String(b, "GBK");
            System.out.println(cnt + " : " + str);
        }
    }

    @Test
    public void encode() {
        String name = "I am 君山";
        System.out.println(name.toCharArray());
        try {
            byte[] iso8859 = name.getBytes("ISO-8859-1");
            System.out.println(iso8859);
            byte[] gb2312 = name.getBytes("GB2312");
            System.out.println(gb2312);
            byte[] gbk = name.getBytes("GBK");
            System.out.println(gbk);
            byte[] utf16 = name.getBytes("UTF-16");
            System.out.println(utf16);
            byte[] utf8 = name.getBytes("UTF-8");
            System.out.println(utf8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
