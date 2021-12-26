package cn.bin.string;

import org.json.JSONObject;

import java.util.Map;


public class String2Json {

    public static void main(String[] args) {

       String str = "{\n" +
               "    \"match\": \"Yes\",\n" +
               "    \"match_all\": \"No\"\n" +
               "}";

        System.out.println(str);

        JSONObject json = new JSONObject(str);
        Map<String, Object> map = json.toMap();

        System.out.println(map.get("match").toString());
        System.out.println(map.get("match_all").toString());
    }

}
