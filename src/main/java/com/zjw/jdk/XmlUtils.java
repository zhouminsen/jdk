package com.zjw.jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * The type XmlUtils.
 */
public class XmlUtils {


    /**
     * Json to xml string.
     *
     * @param json the json
     * @return the string
     */
    public static String jsonToXml(String json) {
        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            Object jObj = JSON.parse(json);
            JSONArray jsonArray = new JSONArray();
            if (jObj instanceof JSONArray) {
                jsonArray = (JSONArray) jObj;
            } else {
                jsonArray.add(jObj);
            }
            for (Object item : jsonArray) {
                jsonToXmlstr((JSONObject) item, buffer);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * Json to xmlstr string.
     *
     * @param jObj   the j obj
     * @param buffer the buffer
     * @return the string
     */
    public static String jsonToXmlstr(JSONObject jObj, StringBuilder buffer) {
        Set<Map.Entry<String, Object>> se = jObj.entrySet();
        for (Iterator<Map.Entry<String, Object>> it = se.iterator(); it.hasNext(); ) {
            Map.Entry<String, Object> en = it.next();
            if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")) {
                buffer.append("<" + en.getKey() + ">");
                JSONObject jo = jObj.getJSONObject(en.getKey());
                jsonToXmlstr(jo, buffer);
                buffer.append("</" + en.getKey() + ">");
            } else if (en.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")) {
                JSONArray jarray = jObj.getJSONArray(en.getKey());
                int type = 0;
                for (Object item : jarray) {
                    if (!(item instanceof JSONObject || item instanceof JSONArray)) {
                        type = 2;
                        buffer.append("<" + en.getKey() + ">");
                        buffer.append(item);
                        buffer.append("</" + en.getKey() + ">");
                    }
                }
                if (type == 2) {
                    return buffer.toString();
                }
                buffer.append("<" + en.getKey() + ">");
                for (int i = 0; i < jarray.size(); i++) {

                    JSONObject jsonobject = jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject, buffer);
                }
                buffer.append("</" + en.getKey() + ">");
            } else if (en.getValue().getClass().getName().equals("java.lang.String")) {
                buffer.append("<" + en.getKey() + ">" + en.getValue());
                buffer.append("</" + en.getKey() + ">");
            }
        }
        return buffer.toString();

    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String xmlstr = jsonToXml("{\n" +
                "  \"orderLines\": [\n" +
                "    {\n" +
                "      \"orderLine\": {\n" +
                "        \"snList\": {\n" +
                "          \"sn\": [\n" +
                "            \"商品序列号, string(40)\",\n" +
                "            \"商品序列号, string(50)\"\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"orderLine\": {\n" +
                "        \"snList\": {\n" +
                "          \"sn\": [\n" +
                "            \"商品序列号, string(40)\",\n" +
                "            \"商品序列号, string(50)\"\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        System.out.println(xmlstr);
    }
}