package com.zjw.jdk.xml;

import com.zjw.jdk.xml.convert.utils.XML;
import org.dom4j.DocumentException;

/**
 * Created by Administrator on 2019-10-24.
 */
public class Main {
    public static void main(String[] args) throws DocumentException {
        String s = "{\n" +
                "  \"orderLines\": [\n" +
                "    {\n" +
                "      \"orderLine\": {\n" +
                "        \"snList\": {\n" +
                "          \"sn\": [\n" +
                "            \"商品序列号, string(40)\",\n" +
                "            \"商品序列号, string(50)\"\n" +
                "          ]\n" +
                "        },\n" +
                "        \"cc\": {\n" +
                "          \"zjw\": {\n" +
                "            \"zjw2\": \"周家伟\"\n" +
                "          },\n" +
                "          \"zjw3\": [\n" +
                "            {\n" +
                "              \"zjw6_s\": {\n" +
                "                \"zjw6\": [\n" +
                "                  1,\n" +
                "                  2,\n" +
                "                  3,\n" +
                "                  4,\n" +
                "                  5\n" +
                "                ]\n" +
                "              },\n" +
                "              \"zjw7\": {\n" +
                "                \"zjw8\": \"周家伟1\",\n" +
                "                \"zjw9\": \"周家伟2\",\n" +
                "                \"zjw10\": {\n" +
                "                  \"zjw11\": \"周家伟3\",\n" +
                "                  \"zjw12\": \"周家伟4\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            {\n" +
                "              \"zjw6_s\": {\n" +
                "                \"zjw6\": [\n" +
                "                  1,\n" +
                "                  2,\n" +
                "                  3,\n" +
                "                  4,\n" +
                "                  5\n" +
                "                ]\n" +
                "              },\n" +
                "              \"zjw7\": {\n" +
                "                \"zjw8\": \"周家伟\",\n" +
                "                \"zjw9\": \"周家伟\",\n" +
                "                \"zjw10\": {\n" +
                "                  \"zjw11\": \"周家伟\",\n" +
                "                  \"zjw12\": \"周家伟\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
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
                "        },\n" +
                "        \"cc\": {\n" +
                "          \"zjw\": {\n" +
                "            \"zjw2\": \"周家伟\"\n" +
                "          },\n" +
                "          \"zjw3\": [\n" +
                "            {\n" +
                "              \"zjw6_s\": {\n" +
                "                \"zjw6\": [\n" +
                "                  1,\n" +
                "                  2,\n" +
                "                  3,\n" +
                "                  4,\n" +
                "                  5\n" +
                "                ]\n" +
                "              },\n" +
                "              \"zjw7\": {\n" +
                "                \"zjw8\": \"周家伟1\",\n" +
                "                \"zjw9\": \"周家伟2\",\n" +
                "                \"zjw10\": {\n" +
                "                  \"zjw11\": \"周家伟3\",\n" +
                "                  \"zjw12\": \"周家伟4\"\n" +
                "                }\n" +
                "              }\n" +
                "            },\n" +
                "            {\n" +
                "              \"zjw6_s\": {\n" +
                "                \"zjw6\": [\n" +
                "                  1,\n" +
                "                  2,\n" +
                "                  3,\n" +
                "                  4,\n" +
                "                  5\n" +
                "                ]\n" +
                "              },\n" +
                "              \"zjw7\": {\n" +
                "                \"zjw8\": \"周家伟\",\n" +
                "                \"zjw9\": \"周家伟\",\n" +
                "                \"zjw10\": {\n" +
                "                  \"zjw11\": \"周家伟\",\n" +
                "                  \"zjw12\": \"周家伟\"\n" +
                "                }\n" +
                "              }\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";

        System.out.println(s);

        s = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<request>\n" +
                "    <entryOrder>\n" +
                "        <name>伟哥</name>\n" +
                "        <age>16</age>\n" +
                "    </entryOrder>\n" +
                "    <entryOrder2>\n" +
                "        <name>伟哥</name>\n" +
                "        <age>16</age>\n" +
                "        <haha>\n" +
                "            <haha1>111</haha1>\n" +
                "            <haha2>111</haha2>\n" +
                "            <haha3>111</haha3>\n" +
                "        </haha>\n" +
                "    </entryOrder2>\n" +
                "    <sn>商品序列号,string(50)</sn>\n" +
                "    <sn>商品序列号,string(50)</sn>\n" +
                "    <zjw>周家伟</zjw>\n" +
                "</request>";
        System.out.println(XML.toJSONObject(s));
    }
}
