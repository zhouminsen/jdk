package com.zjw.jdk.util;

import com.alibaba.fastjson.JSON;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 封装了XML转换成object，object转换成XML的代码
 *
 * @author Steven
 */
public class XMLUtil {
    /**
     * 将对象直接转换成String类型的 XML输出
     *
     * @param obj
     * @return
     */
    public static String convertToXml(Object obj) {
        // 创建输出流
        StringWriter sw = new StringWriter();
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            marshaller.marshal(obj, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }

    /**
     * 将对象根据路径转换成xml文件
     *
     * @param obj
     * @param path
     * @return
     */
    public static void convertToXml(Object obj, String path) {
        try {
            // 利用jdk中自带的转换类实现
            JAXBContext context = JAXBContext.newInstance(obj.getClass());

            Marshaller marshaller = context.createMarshaller();
            // 格式化xml输出的格式
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            // 将对象转换成输出流形式的xml
            // 创建输出流
            FileWriter fw = null;
            try {
                fw = new FileWriter(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            marshaller.marshal(obj, fw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    /**
     * 将String类型的xml转换成对象
     */
    public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
        Object xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            // 进行将Xml转成对象的核心接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            StringReader sr = new StringReader(xmlStr);
            xmlObject = unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    /**
     * 将file类型的xml转换成对象
     */
    public static <T> T convertXmlFileToObject(Class<T> clazz, String xmlPath) {
        T xmlObject = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FileReader fr = null;
            try {
                fr = new FileReader(xmlPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            xmlObject = (T) unmarshaller.unmarshal(fr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlObject;
    }

    public static String map2Xml(Map<String, Object> paramMap) {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("<REQ_PARAM>");
        Set<String> objSet = paramMap.keySet();
        for (String key : objSet) {
            if (key == null) {
                continue;
            }
            strBuilder.append("\n");
            strBuilder.append("<").append(key).append(">\n");
            Object value = paramMap.get(key);
            strBuilder.append(convert(value));
            strBuilder.append("</").append(key).append(">");
        }
        strBuilder.append("\n</REQ_PARAM>");
        return strBuilder.toString();
    }

    public static String convert(Map map) {
        StringBuilder strBuilder = new StringBuilder();
        for (Object o : map.keySet()) {
            strBuilder.append("<").append(o).append(">");
            strBuilder.append(convert(map.get(o)));
            strBuilder.append("</").append(o).append(">\n");
        }
        return strBuilder.toString();
    }

    public static String convert(Collection<?> objects) {
        StringBuilder strBuilder = new StringBuilder("\n");
        for (Object obj : objects) {
            strBuilder.append("<").append(obj).append(">");
            strBuilder.append(convert(obj));
            strBuilder.append("</").append(obj).append(">\n");
        }
        return strBuilder.toString();
    }

    /**
     * 描述：递归进行转换
     * Created by zjw on 2018-12-11 11:21:37
     *
     * @param object
     * @return String
     */
    public static String convert(Object object) {
        if (object instanceof Map) {
            return convert((Map) object);
        }
        if (object instanceof Collection) {
            return convert((Collection<?>) object);
        }
        StringBuilder strBuilder = new StringBuilder();
        if (isObject(object)) {
            Class<?> clz = object.getClass();
            Field[] fields = clz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value;
                try {
                    value = field.get(object);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    continue;
                }
                strBuilder.append("<").append(fieldName).append("\">");
                if (isObject(value)) {
                    strBuilder.append(convert(value));
                } else {
                    strBuilder.append(value.toString());
                }
                strBuilder.append("</").append(fieldName).append(">");
            }
        } else if (object == null) {
            strBuilder.append("null");
        } else {
            strBuilder.append(object.toString());
        }
        return strBuilder.toString();
    }

    /**
     * 描述：判断是否是对象
     * Created by zjw on 2018-12-11 11:20:48
     *
     * @param obj
     * @return boolean
     */
    private static boolean isObject(Object obj) {
        if (obj == null) return false;
        if (obj instanceof String) return false;
        if (obj instanceof Integer) return false;
        if (obj instanceof Double) return false;
        if (obj instanceof Float) return false;
        if (obj instanceof Byte) return false;
        if (obj instanceof Long) return false;
        if (obj instanceof Character) return false;
        if (obj instanceof Short) return false;
        return !(obj instanceof Boolean);
    }


    /**
     * xml转为map,map中有list（节点相同时候)，list中有map
     *
     * @param xml
     * @return
     * @throws DocumentException
     */
    public static Map<String, Object> parseXml(String xml) throws DocumentException {
        Map map = new HashMap();
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));//xml串第一行不能有空格，否则报错
            Element root = document.getRootElement();//得到xml文档根节点元素，即最上层的"<xml>"
            elementTomap(root, map);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> elementTomap(Element outele, Map<String, Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if (size == 0) {
            outmap.put(outele.getName(), outele.getTextTrim());
        } else {
            Map<String, Object> innermap = new HashMap<String, Object>();
            int i = 1;

            for (Element ele1 : list) {
                String eleName = ele1.getName();

                String value = ele1.getText();
                Object obj = innermap.get(eleName);
                if (obj == null) {
                    elementTomap(ele1, innermap);
                } else {
                    if (obj instanceof java.util.Map) {
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        elementTomap(ele1, innermap);
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        innermap.put(eleName, list1);
                    } else if (obj instanceof String) {

                        innermap.put(eleName + i, value);
                        i++;
                    } else {
                        elementTomap(ele1, innermap);
                        Map<String, Object> listValue = (Map<String, Object>) innermap.get(eleName);
                        ((List<Map<String, Object>>) obj).add(listValue);
                        innermap.put(eleName, obj);
                    }

                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
    }

    public static void main(String[] args) throws DocumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Map<String, Object> reqParamMap = new HashMap<>();
        Map<String, Object> pubInfoMap = new HashMap<>();
        Map<String, Object> busInfoMap = new HashMap<>();
        reqParamMap.put("PUB_INFO", pubInfoMap);
        reqParamMap.put("BUSI_INFO", busInfoMap);
        pubInfoMap.put("REQ_TIME", sdf.format(new Date()));
        pubInfoMap.put("CHANNLE_ID", "789");
        pubInfoMap.put("REGION_ID", "123");
        pubInfoMap.put("REQ_SERIAL_NO", "20181211");
        pubInfoMap.put("PROCESS_CODE", "TokenCheck");
        busInfoMap.put("TOKEN", "testToken");
        // busInfoMap.put("list", Arrays.asList("abc", "def"));
        String s = "<request>" + convert(reqParamMap) + "</request>";
        System.out.println(s);
        System.out.println(JSON.toJSONString(parseXml(s)));

//        Map<String, Object> map = new HashMap<>();
//        map.put("11", 22);
//        System.out.println(convertToXml(map));
    }


}