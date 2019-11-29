package com.zjw.jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

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
                    Object o = jarray.get(i);
                    if (o instanceof JSONObject) {
                        JSONObject jsonobject = jarray.getJSONObject(i);
                        jsonToXmlstr(jsonobject, buffer);
                        buffer.append("</" + en.getKey() + ">");
                    } else {
                        buffer.append(o);
                        buffer.append("</" + en.getKey() + ">");

                    }
                }
            } else if (en.getValue().getClass().getName().equals("java.lang.String")) {
                buffer.append("<" + en.getKey() + ">" + en.getValue());
                buffer.append("</" + en.getKey() + ">");
            }
        }
        return buffer.toString();
    }


    /**
     * 将Map转换为XML,Map可以多层转
     *
     * @param params     需要转换的map。
     * @param parentName 就是map的根key,如果map没有根key,就输入转换后的xml根节点。
     * @return String-->XML
     */
    @SuppressWarnings("unchecked")
    public static String createXmlByMap(Map<String, Object> map,
                                        String parentName) {
        //获取map的key对应的value
        Map<String, Object> rootMap = (Map<String, Object>) map.get(parentName);
        if (rootMap == null) {
            rootMap = map;
        }
        Document doc = DocumentHelper.createDocument();
        //设置根节点
        doc.addElement(parentName);
        String xml = iteratorXml(doc.getRootElement(), parentName, rootMap);
        return formatXML(xml);
    }

    /**
     * 循环遍历params创建xml节点
     *
     * @param element    根节点
     * @param parentName 子节点名字
     * @param params     map数据
     * @return String-->Xml
     */
    @SuppressWarnings("unchecked")
    public static String iteratorXml(Element element, String parentName,
                                     Map<String, Object> params) {
        Element e = element.addElement(parentName);
        Set<String> set = params.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            if (params.get(key) instanceof Map) {
                iteratorXml(e, key, (Map<String, Object>) params.get(key));
            } else if (params.get(key) instanceof List) {
                List<Object> list = (List) params.get(key);
                for (int i = 0; i < list.size(); i++) {
                    iteratorXml(e, key, (Map<String, Object>) list.get(i));
                }
            } else {
                String value = params.get(key) == null ? "" : params.get(key)
                        .toString();
                e.addElement(key).addText(value);
                // e.addElement(key).addCDATA(value);
            }
        }
        return e.asXML();
    }

    /**
     * 格式化xml
     *
     * @param xml
     * @return
     */
    public static String formatXML(String xml) {
        String requestXML = null;
        XMLWriter writer = null;
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = reader.read(new StringReader(xml));
            if (document != null) {
                StringWriter stringWriter = new StringWriter();
                OutputFormat format = new OutputFormat(" ", true);// 格式化，每一级前的空格
                format.setNewLineAfterDeclaration(false); // xml声明与内容是否添加空行
                format.setSuppressDeclaration(false); // 是否设置xml声明头部 false：添加
                format.setNewlines(true); // 设置分行
                writer = new XMLWriter(stringWriter, format);
                writer.write(document);
                writer.flush();
                requestXML = stringWriter.getBuffer().toString();
            }
            return requestXML;
        } catch (Exception e1) {
            e1.printStackTrace();
            return null;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {

                }
            }
        }
    }

    /**
     * 第二种
     * 手动组装map转为string
     *
     * @param map
     * @return
     */
    public static String moveMapToXML(Map<?, ?> map) {
        StringBuffer sb = new StringBuffer();
        mapToXML(map, sb);
        return formatXML(sb.toString());
    }

    /**
     * @param map 循环遍历map节点和value
     * @param sb  输出xml
     */
    private static void mapToXML(Map<?, ?> map, StringBuffer sb) {
        Set<?> set = map.keySet();
        for (Iterator<?> it = set.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            Object value = map.get(key);
            if (value instanceof Map) {
                sb.append("<" + key + ">");
                mapToXML((Map<?, ?>) value, sb);
                sb.append("</" + key + ">");
            } else if (value instanceof List) {
                List<?> list = (List<?>) map.get(key);
                for (int i = 0; i < list.size(); i++) {
                    sb.append("<" + key + ">");
                    Map<?, ?> hm = (Map<?, ?>) list.get(i);
                    mapToXML(hm, sb);
                    sb.append("</" + key + ">");
                }
            } else {
                sb.append("<" + key + ">" + value + "</" + key + ">");
            }

        }
    }

    /**
     * 通过XML转换为Map<String,Object>
     *
     * @param xml 为String类型的Xml
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
    public static Map<String, Object> createMapByXml(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (doc == null)
            return map;
        Element rootElement = doc.getRootElement();
        elementTomap(rootElement, map);
        return map;
    }

    /***
     * XmlToMap核心方法，里面有递归调用
     * @param map
     * @param ele
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> elementTomap(Element outele,
                                                   Map<String, Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if (size == 0) {
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            Map<String, Object> innermap = new HashMap<String, Object>();
            for (Element ele1 : list) {
                String eleName = ele1.getName();
                Object obj = innermap.get(eleName);
                if (obj == null) {
                    elementTomap(ele1, innermap);
                } else {
                    if (obj instanceof java.util.Map) {
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap
                                .remove(eleName));
                        elementTomap(ele1, innermap);
                        list1.add((Map<String, Object>) innermap
                                .remove(eleName));
                        innermap.put(eleName, list1);
                    } else {
                        elementTomap(ele1, innermap);
                        ((List<Map<String, Object>>) obj).add(innermap);
                    }
                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
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