package cn.intv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Serialize {

    public static void main(String[] args) {
        List<Map<String, String>> lst = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        map1.put("m1key1", "m1value1");
        map1.put("m1key2", "m1value2");
        map1.put("m1key3", "m1value3");
        map2.put("m2key1", "m2value1");
        map2.put("m2key2", "m2value2");
        lst.add(map1);
        lst.add(map2);

        test1(lst);
//		test2(lst);
    }

    private static void test1(List<Map<String, String>> lst) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, String> map : lst) {
            sb.append("{");
            for (String key : map.keySet()) {
                sb.append(key.length()).append(":").append(key).append(map.get(key)).append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        System.out.println(sb.toString());
    }

    private static void test2(List<Map<String, String>> lst) {
        JSON json = (JSON) JSON.toJSON(lst);
        System.out.println(json.toString());
        String serializeString = JSON.toJSONString(lst, true);
        System.out.println(serializeString);
    }

    public static void writeFile(String filePath, String input) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        PrintWriter out = new PrintWriter(fw);
        out.write(input);
        out.println();
        fw.close();
        out.close();
    }

    // 读取文本文件内容
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        readToBuffer(sb, filePath);
        return sb.toString();
    }

    // 将文本文件中的内容读入到buffer中
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }

}
