package com.zjw.jdk;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 加入matchtype
 * Created by Administrator on 2019-10-12.
 */
public class JsonToApi4 {

    private List<TemplateNode> getTemplateNodes(List<IfmPlatformTemplateDetailDTO> sources) {
        List<TemplateNode> sources2 = new ArrayList<>();
        for (int i = 0; i < sources.size(); i++) {
            IfmPlatformTemplateDetailDTO item = sources.get(i);
            TemplateNode templateNode = new TemplateNode();
            BeanUtils.copyProperties(item, templateNode);
            sources2.add(templateNode);
        }
        return sources2;
    }

    String jsonStr = "{\n" +
            "  \"entryOrder\": {\n" +
            "    \"totalOrderLines\": \"\\n            单据总行数，int，当单据需要分多个请求发送时，发送方需要将totalOrderLines填入，接收方收到后，根据实际接收到的条数和totalOrderLines进行比对，如果小于，则继续等待接收请求。如果等于，则表示该单据的所有请求发送完成。\\n        \",\n" +
            "    \"entryOrderCode\": \"入库单编码, string (50) , 必填\",\n" +
            "    \"ownerCode\": \"货主编码, string (50)\",\n" +
            "    \"warehouseCode\": \"仓库编码, string (50)，必填\",\n" +
            "    \"entryOrderId\": \"仓储系统入库单ID, string (50) , 条件必填\",\n" +
            "    \"entryOrderType\": \"入库单类型 ，SCRK=生产入库，LYRK=领用入1库，CCRK=残次品入库，CGRK=采购入库, DBRK=调拨入库, QTRK=其他入库，B2BRK=B2B入库\\n        \",\n" +
            "    \"outBizCode\": \"外部业务编码, 消息ID, 用于去重, ISV对于同一请求，分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，请求不会被重复处理, ,必填\",\n" +
            "    \"confirmType\": \"支持出入库单多次收货, int，\\n            多次收货后确认时\\n            0 表示入库单最终状态确认；\\n            1 表示入库单中间状态确认；\\n            每次入库传入的数量为增量，特殊情况，同一入库单，如果先收到0，后又收到1，允许修改收货的数量。\\n        \",\n" +
            "    \"status\": \"入库单状态, string (50) , 必填 (NEW-未开始处理, ACCEPT-仓库接单 , PARTFULFILLED-部分收货完成, FULFILLED-收货完成, EXCEPTION-异常,\\n            CANCELED-取消, CLOSED-关闭, REJECT-拒单, CANCELEDFAIL-取消失败) , (只传英文编码)\\n        \",\n" +
            "    \"freight\": \"快递费用 (元) , double (18, 2)\",\n" +
            "    \"operateTime\": \"操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)\",\n" +
            "    \"remark\": \"备注, string (500)\"\n" +
            "  },\n" +
            "  \"orderLines\": [\n" +
            "    {\n" +
            "      \"outBizCode\": \"外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用\",\n" +
            "      \"orderLineNo\": \"单据行号，string（50）\",\n" +
            "      \"ownerCode\": \"货主编码, string (50)\",\n" +
            "      \"itemCode\": \"商品编码, string (50) , 必填\",\n" +
            "      \"itemId\": \"仓储系统商品ID, string (50) , 条件必填\",\n" +
            "      \"snList\": [\n" +
            "        \"商品序列号, string(40)\",\n" +
            "        \"商品序列号, string(50)\"\n" +
            "      ],\n" +
            "      \"itemName\": \"商品名称, string (200)\",\n" +
            "      \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\",\n" +
            "      \"planQty\": \"应收数量, int\",\n" +
            "      \"actualQty\": \"实收数量, int，必填\",\n" +
            "      \"batchCode\": \"批次编码, string (50)\",\n" +
            "      \"productDate\": \"商品生产日期，string（10）， YYYY-MM-DD\",\n" +
            "      \"expireDate\": \"商品过期日期，string（10），YYYY-MM-DD\",\n" +
            "      \"produceCode\": \"生产批号, string (50)\",\n" +
            "      \"batchs\": [\n" +
            "        {\n" +
            "          \"batchCode\": \"批次编号，string(50)\",\n" +
            "          \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"produceCode\": \"生产批号，string(50)，\",\n" +
            "          \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "          \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"batchCode\": \"批次编号，string(50)\",\n" +
            "          \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"produceCode\": \"生产批号，string(50)，\",\n" +
            "          \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "          \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"remark\": \"备注, string (500)\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"outBizCode\": \"外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用\",\n" +
            "      \"orderLineNo\": \"单据行号，string（50）\",\n" +
            "      \"ownerCode\": \"货主编码, string (50)\",\n" +
            "      \"itemCode\": \"商品编码, string (50) , 必填\",\n" +
            "      \"itemId\": \"仓储系统商品ID, string (50) , 条件必填\",\n" +
            "      \"snList\": [\n" +
            "        \"商品序列号, string(40)\",\n" +
            "        \"商品序列号, string(50)\"\n" +
            "      ],\n" +
            "      \"itemName\": \"商品名称, string (200)\",\n" +
            "      \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\",\n" +
            "      \"planQty\": \"应收数量, int\",\n" +
            "      \"actualQty\": \"实收数量, int，必填\",\n" +
            "      \"batchCode\": \"批次编码, string (50)\",\n" +
            "      \"productDate\": \"商品生产日期，string（10）， YYYY-MM-DD\",\n" +
            "      \"expireDate\": \"商品过期日期，string（10），YYYY-MM-DD\",\n" +
            "      \"produceCode\": \"生产批号, string (50)\",\n" +
            "      \"batchs\": [\n" +
            "        {\n" +
            "          \"batchCode\": \"批次编号，string(50)\",\n" +
            "          \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"produceCode\": \"生产批号，string(50)，\",\n" +
            "          \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "          \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"batchCode\": \"批次编号，string(50)\",\n" +
            "          \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "          \"produceCode\": \"生产批号，string(50)，\",\n" +
            "          \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "          \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"remark\": \"备注, string (500)\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void json() {
        List<IfmPlatformTemplateDetailDTO> sources = new ArrayList<>();
        sources.add(new IfmPlatformTemplateDetailDTO(-1, -2, "params", 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(1, -1, "entryOrder", "entryOrder2", 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(3, 1, "totalOrderLines", "totalOrderLines2", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(4, 1, "entryOrderCode", "entryOrderCode2", 0, 0, 0));

        sources.add(new IfmPlatformTemplateDetailDTO(5, -1, "orderLines", "orderLinesl2", 0, 1));
        sources.add(new IfmPlatformTemplateDetailDTO(6, 5, "outBizCode", "outBizCode2", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(7, 5, "remark", "remark2", 0, 0, 0));

//        sources.add(new IfmPlatformTemplateDetailDTO(8, 5, "snList", "snList2", 0, 2));
//
//        sources.add(new IfmPlatformTemplateDetailDTO(11, 5, "batchs", "batchs2", 0, 1));
//        sources.add(new IfmPlatformTemplateDetailDTO(16, 14, "batchCode", "batchCode2", 0, 1, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(101, 14, "productDate", "productDate_text2", 1, 0, "0", "2", 1, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(102, 14, "productDate", "productDate_text22", 1, 0, "2", "4", 1, 0));

//        sources.add(new IfmPlatformTemplateDetailDTO(18, 1, "id", 1, "t5", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(19, 18, "confirmType", 9, "t5", "confirmType", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(103, 18, "status", 9, "t5", "status", 0, 0, 0));

        //并集有外键
//        sources.add(new IfmPlatformTemplateDetailDTO(21, 13, "id", 1, "t7", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(22, 21, "foreign_id", 2, "t7", "order_id", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(104, 21, "inventoryType", 9, "t7", "confirmType", 0, 0, 0));

//        并集无外键
//        sources.add(new IfmPlatformTemplateDetailDTO(23, -1, "orderLines", 0, 0, 1));
//        sources.add(new IfmPlatformTemplateDetailDTO(24, 23, "id", 1, "t8", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(25, 24, "produceCode", 9, "t8", "produceCode", 0, 0, 0));

        List<TemplateNode> sources2 = getTemplateNodes(sources);
        List<TemplateNode> templateNodes = getNT(sources2, IfmApiParamsEnums.root_node.parentId);
        TemplateNode templateNode = templateNodes.get(0);
//        System.out.println(JSON.toJSONString(templateNode));
        JSONArray jsonArray = new JSONArray();
        Object read = JSON.parse(jsonStr);
        if (read instanceof JSONObject) {
            jsonArray.add(read);
        } else {
            jsonArray.addAll((Collection<?>) read);
        }
        cc(templateNode, jsonArray);
    }

    private void cc(TemplateNode nt, JSONArray jsonArray) {
        Map<String, Object> map = new HashMap<>();
        for (Object e : jsonArray) {
            JSONObject jsonObject = (JSONObject) e;
            //ifm的params下的子节点
            this.jsonToData(nt.getChildren(), map, jsonObject, "");

        }
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
    }

    private void jsonToData(List<TemplateNode> sources, Map<String, Object> map, JSONObject node, String nodeName) {
        for (int i = 0; i < sources.size(); i++) {
            TemplateNode item = sources.get(i);
            Object o = node.get(item.getNodeName());
            if (o == null) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s", nodeName,
                        node.keySet(), item.getFullNodeName(), item.getNodeType()));
            }
            if (StringUtils.isNotEmpty(item.getTargetName())) {
                if (item.getParentNode().getNodeName().equals("params")) {
                    if (item.getNodeType() == 0) {
                        Map<String, Object> childMap = new HashMap<>();
                        map.put(item.getTargetName(), childMap);
                        map = childMap;
                        System.out.println("aaa");
                    } else if (item.getNodeType() == 1) {
                        // 对象数组
                        List<Map<String, Object>> subMapList = new ArrayList<>();
                        map.put(item.getTargetName(), subMapList);
                        System.out.println("aaa");
                    }
                } else {
                    //对象
                    if (item.getNodeType() == 0) {
                        //父节点是对象
                        if (item.getParentNode().getNodeType() == 0) {
                            //父节点是对象
                            Map<String, Object> first = new HashMap<>();
                            map.put(item.getTargetName(), first);
//                        first.put("parent", map);
                            map = first;
                        } else {
                            List<Map<String, Object>> parentList = (List<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                            Map<String, Object> first = new HashMap<>();
                            parentList.add(first);
                            Map<String, Object> second = new HashMap<>();
                            first.put(item.getTargetName(), second);
//                        second.put("parent", map);
//                        map = second;
                        }
                        System.out.println("aaa");
                    } else if (item.getNodeType() == 1) {
                        //当前是对象数组
                        if (item.getParentNode().getNodeType() == 0) {
                            //父节点是对象
                            List<Map<String, Object>> subMapList = new ArrayList<>();
                            map.put(item.getTargetName(), subMapList);
                            System.out.println("aaa");
                        } else {
                            // 父节点是对象数组
                            List<Map<String, Object>> firstList = (List<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                            Map<String, Object> sub = new HashMap<>();
                            firstList.add(sub);
                            sub.put(item.getTargetName(), new ArrayList<>());
                            System.out.println("aaa");
//                        map = sub;
                        }
                    } else {
                        //数组
                        List<Object> subArray = new ArrayList<>();
                        map.put(item.getTargetName(), subArray);
                        System.out.println("aaa");
                    }
                }
            }

            if (CollectionUtils.isEmpty(item.getChildren())) {
                if (item.getParentNode().getNodeType() == 2) {
                    List<Object> list = (List<Object>) map.get(item.getParentNode().getTargetName());
                    list.add(o);
                } else if (item.getParentNode().getNodeType() == 1) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                    Map<String, Object> child = list.get(0);
                    child.put(item.getTargetName(), o);
//                    list.add(child);
                } else {
                    map.put(item.getTargetName(), o);
                }
                return;
            }

            JSONArray list = new JSONArray();
            if (o instanceof JSONObject) {
                list.add(o);
            } else {
                list.addAll((Collection<? extends Object>) o);
            }
            for (int j = 0; j < list.size(); j++) {
                Object e = list.get(j);
                JSONObject jsonObject = (JSONObject) e;
                validate(item, node, nodeName, o);
                this.jsonToData(item.getChildren(), map, jsonObject, nodeName);
            }
        }

    }

    private void validate(TemplateNode nt, JSONObject node, String nodeName, Object o) {
        //普通
        if (nt.getNodeType() == 0) {
            if (!(o instanceof JSONObject)) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s", nodeName,
                        node.keySet(), nt.getFullNodeName(), nt.getNodeType()));
            }
        } else if (nt.getNodeType() == 1) {
            //对象数组
            if (!(o instanceof JSONArray)) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s", nodeName,
                        node.keySet(), nt.getFullNodeName(), nt.getNodeType()));
            }
            JSONArray jsonArray = (JSONArray) o;
            for (Object item : jsonArray) {
                if (!(item instanceof JSONArray || item instanceof JSONObject)) {
                    throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s", nodeName,
                            node.keySet(), nt.getFullNodeName(), nt.getNodeType()));
                }
            }
        } else {
            //数组
            if (!(o instanceof JSONArray || o instanceof JSONObject)) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s", nodeName,
                        node.keySet(), nt.getFullNodeName(), nt.getNodeType()));
            }
        }
    }

/*
    private void getApi3(List<TemplateNode> sources, Map<String, Object> map, JSONObject node, String nodeName) {
        for (int i = 0; i < sources.size(); i++) {
            TemplateNode item = sources.get(i);
            if (!CollectionUtils.isEmpty(item.getChildren2())) {
                if (item.getParentNode().getNodeType() == null) {
                    //对象
                    if (item.getNodeType() == 0) {
                        Map<String, Object> childMap = new HashMap<>();
                        map.put(item.getTargetName(), childMap);
                        getApi3(item.getChildren2(), childMap, node, nodeName);
                        System.out.println("aaa");
                    } else {
                        // 对象数组
                        List<Map<String, Object>> subMapList = new ArrayList<>();
                        map.put(item.getTargetName(), subMapList);
                        getApi3(item.getChildren2(), map, node, nodeName);
                        System.out.println("aaa");
                    }
                } else {
                    //对象
                    if (item.getNodeType() == 0) {
                        //父节点是对象
                        if (item.getParentNode().getNodeType() == 0) {
                            Map<String, Object> first = new HashMap<>();
                            map.put(item.getTargetName(), first);
                            getApi3(item.getChildren2(), first, node, nodeName);
                        } else {
                            List<Map<String, Object>> parentList = (List<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                            Map<String, Object> first = new HashMap<>();
                            parentList.add(first);
                            Map<String, Object> second = new HashMap<>();
                            first.put(item.getTargetName(), second);
                            getApi3(item.getChildren2(), second, node, nodeName);
                        }
                        System.out.println("aaa");
                    } else if (item.getNodeType() == 1) {
                        //当前是对象数组
                        if (item.getParentNode().getNodeType() == 0) {
                            //父节点是对象
                            List<Map<String, Object>> subMapList = new ArrayList<>();
                            map.put(item.getTargetName(), subMapList);
                            getApi3(item.getChildren2(), map, node, nodeName);
                            System.out.println("aaa");
                        } else {
                            // 父节点是对象数组
                            List<Map<String, Object>> firstList = (List<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                            Map<String, Object> sub = new HashMap<>();
                            firstList.add(sub);
                            sub.put(item.getTargetName(), new ArrayList<>());
                            getApi3(item.getChildren2(), sub, node, nodeName);
                            System.out.println("aaa");
                        }
                    } else {
                        //数组
                        List<Object> subArray = new ArrayList<>();
                        map.put(item.getTargetName(), subArray);
                        getApi3(item.getChildren2(), map, node, nodeName);
                        System.out.println("aaa");
                    }
                }
            } else {
                ApiRequestParamValueDTO e = (ApiRequestParamValueDTO) item;
                Object value = e.getValue();
//                Object value = "1111";
                if (item.getParentNodeType() == 2) {
                    List<Object> list = (List<Object>) map.get(item.getParentParameterName());
                    list.add(value);
                } else if (item.getParentNodeType() == 1) {
                    List<Map<String, Object>> list = (List<Map<String, Object>>) map.get(item.getParentParameterName());
                    Map<String, Object> child = new HashMap<>();
                    child.put(item.getParameterName(), value);
                    list.add(child);
                } else {
                    map.put(item.getParameterName(), value);
                }
            }
        }
    }*/

    public static List<TemplateNode> getNT(List<TemplateNode> sources, Integer innerId) {
        ArrayList<TemplateNode> result = new ArrayList<>();
        List<TemplateNode> target = sources.stream().filter
                (item -> Objects.equals(innerId, item.getParentId())).collect(Collectors.toList());
        for (TemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (innerId != IfmApiParamsEnums.root_node.parentId) {
                TemplateNode templateNode = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(templateNode);
            }
            if (b) {
                item.setChildren(getNT(sources, item.getInnerId()));
            }
            result.add(item);
        }
        return result;
    }

    public static String getSplit(String v, int selectType, String start, String end) {
        String s = null;
        if (v != null) {
            s = v.toString();
            //长度选择器
            if (selectType == 0) {
                if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
                    s = s.substring(Integer.parseInt(start), Integer.parseInt(end));
                } else if (StringUtils.isNotBlank(start)) {
                    s = s.substring(Integer.parseInt(start));
                } else if (StringUtils.isNotBlank(end)) {
                    s = s.substring(0, Integer.parseInt(end));
                }
            } else {
                //字符选择器
                if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
                    s = s.substring(s.indexOf(start) + 1, s.indexOf(end) + 1);
                } else if (StringUtils.isNotBlank(start)) {
                    s = s.substring(s.indexOf(start) + 1);
                } else if (StringUtils.isNotBlank(end)) {
                    s = s.substring(0, s.indexOf(end) + 1);
                }
            }
        }
        return s;
    }


    @Data
    public static class IfmPlatformTemplateDetailDTO implements Serializable {

        public IfmPlatformTemplateDetailDTO() {
        }

        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                            Integer matchType, Integer nodeType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.matchType = matchType;
            this.nodeType = nodeType;

        }

        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                            String targetName, Integer matchType, Integer nodeType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.matchType = matchType;
            this.nodeType = nodeType;
            this.targetName = targetName;

        }


        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                            String targetName, Integer matchType, Integer nodeType,
                                            Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.targetName = targetName;
            this.matchType = matchType;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
        }


        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                            String targetName, Integer matchType, Integer selectType, String selectStart, String selectEnd,
                                            Integer nodeType, Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.targetName = targetName;
            this.matchType = matchType;
            this.selectType = selectType;
            this.selectStart = selectStart;
            this.selectEnd = selectEnd;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
        }


        public Integer innerId;

        public Integer parentId;


        private String targetId;

        private String tempId;

        private String nodeName;

        private String targetName;

        private String remark;

        /**
         * 参数匹配类型
         */
        private Integer matchType;

        private String matchTypeStr;

        /**
         * 选择类型
         */
        private Integer selectType;
        private String selectTypeStr;

        /**
         * 选择起始值
         */
        private String selectStart;


        private String selectEnd;


        /**
         * 节点类型 0:普通，1：对象数组，2：数组
         */
        private Integer nodeType;

        /**
         * 节点类型 0:普通，1：对象数组，2：数组
         */
        private String nodeTypeStr;

        /**
         * 字段类型(0：字符串 1：时间 2：数字)
         */
        private Integer fieldType;

        private String fieldTypeStr;
    }

    @Data
    public static class FullTable {
        /**
         * 字段类型注释
         */
        private String parameterColumnTypeStr;
        /**
         * 父节点类型 0:普通，1：对象数组，2：数组 (字典类型是node_type)
         */
        private Integer parentNodeType;
        /**
         * 父节点名称
         */
        private String parentParameterName;

        List<FullTable> children2 = new ArrayList<>();

        /**
         * 传递参数
         */
        private Object value;

        public String id;
        public Integer innerId;
        public String targetName;
        public String nodeName;
        public Integer fieldType;

    }


    @Data
    private static class TemplateNode extends IfmPlatformTemplateDetailDTO implements Serializable {
        public TemplateNode() {
        }

        public String fullNodeName;
        public TemplateNode parentNode;
        public Object value;
        public List<TemplateNode> children;

        @Override
        public String toString() {
            return "TemplateNode{" +
                    "innerId=" + innerId +
                    ", parentId=" + parentId +
                    ", nodeName='" + this.getNodeName() + '\'' +
                    ", targetName='" + this.getNodeName() + '\'' +
                    ", nodeType=" + this.getNodeType() +
                    ", fieldType=" + this.getFieldType() +
                    ", fullNodeName='" + fullNodeName + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Getter
    public static enum IfmApiParamsEnums {


        /**
         * 根节点
         */
        root_node(-1, "params", -2);


        /**
         * api类型
         */
        private Integer innerId;

        private Integer parentId;


        /**
         *
         */
        private String name;


        IfmApiParamsEnums(Integer innerId, String name, Integer parentId) {
            this.innerId = innerId;
            this.name = name;
            this.parentId = parentId;
        }
    }
}
