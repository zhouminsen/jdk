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
                for (int i = 0; i < jarray.size(); i++) {
                    buffer.append("<" + en.getKey() + ">");
                    JSONObject jsonobject = jarray.getJSONObject(i);
                    jsonToXmlstr(jsonobject, buffer);
                    buffer.append("</" + en.getKey() + ">");
                }
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
        String xmlstr = jsonToXml("[{\"name2\":[{\"name6\":[{\"name15\":\"111\"},{\"name15\":\"111\"}]},{\"name8\":\"111\"}]},{\"name3\":[{\"name9\":\"111\"},{\"name11\":\"111\"}]},{\"name\":[{\"name4\":[{\"name12\":\"111\"},{\"name13\":\"111\"}]},{\"name7\":\"111\"},{\"name5\":[{\"name14\":\"111\"}]},{\"name10\":\"111\"}]}]");
        System.out.println(xmlstr);
    }
}