package com.zjw.jdk.xml.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjw.jdk.util.UtilFuns;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 加入matchtype
 * Created by Administrator on 2019-10-12.
 */
public class JsonToData2 {


    private List<TemplateNode> getTemplateNodes(List<IfmPlatformTemplateDetailDTO> sources) {
        List<TemplateNode> sources2 = new ArrayList<>();
        for (int i = 0; i < sources.size(); i++) {
            IfmPlatformTemplateDetailDTO item = sources.get(i);
            if (item.getDataType() == null) {
                throw new RuntimeException(String.format("id：%s,平台字段：%s未设置字段类型", item.getInnerId(), item.getNodeName()));
            }
            if (item.getNodeType() == null) {
                throw new RuntimeException(String.format("id：%s,平台字段：%s未设置数组类型", item.getInnerId(), item.getNodeName()));
            }
            if (item.getMatchType() == null) {
                throw new RuntimeException(String.format("id：%s,平台字段：%s未设置匹配类型", item.getInnerId(), item.getNodeName()));
            }
            if (item.getDataType() == 0) {

            } else if (item.getDataType() == 1) {

            } else if (item.getDataType() == 2) {
                if (StringUtils.isEmpty(item.getForeignField())) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置外键字段", item.getInnerId(), item.getNodeName()));
                }

            } else if (item.getDataType() == 3) {
                if (item.getForeignId() == null) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置外键id", item.getInnerId(), item.getNodeName()));
                }
                int count = 0;
                // 循环当前索引前的对象，从中找当前对象的外键id，
                for (int j = 0; j < i; j++) {
                    TemplateNode e = sources2.get(j);
                    if (Objects.equals(item.getForeignId(), e.getInnerId())) {
                        if (e.getDataType() != 1) {
                            throw new RuntimeException(String.format("id：%s,平台字段：%s设置的外键id关联的对象字段类型非主键", item.getInnerId(), item.getNodeName()));
                        }
                        count++;
                    }
                }
                if (count == 0) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s设置的外键id没有找到", item.getInnerId(), item.getNodeName()));
                }
            }
            if (item.getDataType() != 0) {
                if (StringUtils.isEmpty(item.getTargetTable())) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置目标表", item.getInnerId(), item.getNodeName()));
                }
                if (StringUtils.isEmpty(item.getTargetName())) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置目标字段", item.getInnerId(), item.getNodeName()));
                }
                if (StringUtils.isEmpty(item.getNodeName())) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置节点", item.getInnerId(), item.getNodeName()));
                }
            }


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
        sources.add(new IfmPlatformTemplateDetailDTO(-1, -2, "params", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(1, -1, "entryOrder", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(2, 1, "id", 1, "t", "id", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(3, 1, "totalOrderLines", 9, "t", "totalOrderLines", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(4, 1, "entryOrderCode", 9, "t", "entryOrderCode", 0, 0, 0));

        sources.add(new IfmPlatformTemplateDetailDTO(5, -1, "orderLines", 0, 0, 1));
        sources.add(new IfmPlatformTemplateDetailDTO(6, 5, "id", 1, "t2", "id", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(7, 5, 2, "foreign_id", 3, "t2", "order_id", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(8, 5, "outBizCode", 9, "t2", "outBizCode", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(30, 5, "remark", 9, "t2", "remark", 0, 0, 0));

        sources.add(new IfmPlatformTemplateDetailDTO(9, 6, "snList", 0, 0, 2));
        sources.add(new IfmPlatformTemplateDetailDTO(10, 9, "id", 1, "t3", "id", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(11, 9, "foreign_id", 2, "t3", "sub_id", "id", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(12, 9, "sn", 9, "t3", "sn2", 0, 0, 0));
//
        sources.add(new IfmPlatformTemplateDetailDTO(13, 6, "batchs", 0, 0, 1));
        sources.add(new IfmPlatformTemplateDetailDTO(14, 13, "id", 1, "t4", "id", "id", 0, 1, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(15, 13, "foreign_id", 2, "t4", "sub_id", "id", 0, 1, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(16, 13, "batchCode", 9, "t4", "batchCode", 0, 1, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(101, 13, "productDate", 9, "t4", "productDate_text", 1, 0, "0", "2", 1, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(102, 13, "productDate", 9, "t4", "productDate_text2", 1, 0, "2", "4", 1, 0));
//        //并集无外键
//        sources.add(new IfmPlatformTemplateDetailDTO(18, 1, "id", 1, "t5", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(19, 18, "confirmType", 9, "t5", "confirmType", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(103, 18, "status", 9, "t5", "status", 0, 0, 0));
////
//////        //并集有外键
//        sources.add(new IfmPlatformTemplateDetailDTO(21, 13, "id", 1, "t7", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(22, 21, "foreign_id", 2, "t7", "order_id", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(104, 21, "inventoryType", 9, "t7", "confirmType", 0, 0, 0));
////
////        并集无外键
//        sources.add(new IfmPlatformTemplateDetailDTO(23, -1, "orderLines", 0, 0, 1));
//        sources.add(new IfmPlatformTemplateDetailDTO(24, 23, "id", 1, "t8", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(25, 24, "produceCode", 9, "t8", "produceCode", 0, 0, 0));

        List<TemplateNode> sources2 = getTemplateNodes(sources);
        List<TemplateNode> templateNodes = getNT(sources2, IfmApiParamsEnums.root_node.parentId, "");
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
        List<Table> params = new ArrayList<>();
        Set<FullTable> fullTableList = new HashSet<>();
        for (Object e : jsonArray) {
            JSONObject jsonObject = (JSONObject) e;
            //ifm的params下的子节点
            for (TemplateNode item : nt.getChildren()) {
                this.jsonToData(item, params, fullTableList, null, jsonObject, "");
            }

        }
        System.out.println(JSON.toJSONString(fullTableList, SerializerFeature.WriteMapNullValue));
    }

    private void jsonToData(TemplateNode nt, List<Table> params, Set<FullTable> fullTableList, FullTable ft, JSONObject node, String nodeName) {
        Table table = new Table();
        BeanUtils.copyProperties(nt, table);
        //主键
        if (nt.getDataType() == 1) {
            //如果是父父对象是空
            if (nt.getParentNode().getParentNode() == null) {
                ft = null;
            }
            if (ft == null) {
                ft = new FullTable();
                fullTableList.add(ft);
                params.add(table);
                Object v = UtilFuns.getRandomOfScope(1, 1000) + "";
                nt.setValue(v);
                table.setValue(v);
                ft.setTableName(nt.getTargetTable());
                ft.getList().add(table);
            } else {
                FullTable subFt = new FullTable();
//                subFt.setParent(ft);
                ft.getChildren().add(subFt);
                ft = subFt;
                params.add(table);
                Object v = UtilFuns.getRandomOfScope(1, 1000) + "";
                nt.setValue(v);
                table.setValue(v);
                subFt.setTableName(nt.getTargetTable());
                subFt.getList().add(table);
            }

        } else if (nt.getDataType() != 0) {
            //获取主键所属的value
            if (nt.getDataType() == 2) {
                //子集外键
                TemplateNode parentNode = getUp(nt, nt.getTargetTable(), nt.getForeignField());
                table.setValue(parentNode.getValue());
                ft.getList().add(table);
                return;
            } else if (nt.getDataType() == 3) {
                //并集外键
                Table t = params.stream().filter(item -> item.getInnerId() == nt.getForeignId()).findFirst().get();
                table.setValue(t.getValue());
                ft.getList().add(table);
                return;
            } else if (nt.getDataType() == 9) {
                //普通节点
                Object o = null;
                if (nt.getParentNode().getParentNode().getNodeType() == 2) {
                    o = node.get(nt.getParentNode().getNodeName());
                } else {
                    o = node.get(nt.getNodeName());
                }
                if (o == null) {
                    throw new RuntimeException(String.format("获取节点value，当前节点%s的子节点%s中查询不到节点%s，节点父父类型是%s",
                            nodeName, node.keySet(), nt.getFullNodeName(), nt.getParentNode().getParentNode().getNodeType()));
                }
                String value = o.toString();
                if (nt.getMatchType() == 1) {
                    value = getSplit(value, nt.getSelectType(), nt.getSelectStart(), nt.getSelectEnd());
                }
                table.setValue(value);
                ft.getList().add(table);
                return;
            }
        }
        if (CollectionUtils.isEmpty(nt.getChildren())) {
            return;
        }
        Object o = null;
        if (nt.getDataType() == 0) {
            o = node.get(nt.getNodeName());
            nodeName += nt.getNodeName() + ".";
            System.out.println("是什么" + nodeName);
        } else if (nt.getDataType() == 1) {
            //当前是主键，取当前节点
            o = node;
        }
        if (o == null) {
            throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s，数据类型%s", nodeName,
                    node.keySet(), nt.getFullNodeName(), nt.getNodeType(), nt.getDataType()));
        }

        JSONArray list = new JSONArray();
        if (o instanceof JSONObject) {
            list.add(o);
        } else {
            list.addAll((Collection<? extends Object>) o);
        }
        //查询节点是否是数组，不是数组，同节点不能重复出现||这里和xml转换不同
        if (list.size() > 1 && nt.getNodeType() == 0) {
            throw new RuntimeException(String.format("当前节点%s的子节点%s查询节点%s，实际数据为%s条，不符合当前节点标识，节点类型%s，数据类型%s",
                    nodeName, node.keySet(), nt.getFullNodeName(), list.size(), nt.getNodeType(), nt.getDataType()));
        }
        for (int i = 0; i < list.size(); i++) {
            Object item = list.get(i);
            for (TemplateNode e : nt.getChildren()) {
                if (item instanceof JSONObject) {
                    JSONObject element = (JSONObject) item;
                    jsonToData(e, params, fullTableList, ft, element, nodeName);
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(e.getNodeName(), item);
                    jsonToData(e, params, fullTableList, ft, jsonObject, nodeName);
                }
            }
//            String makeup = nodeName;
////                nodeName += item.getNodeName() + ".";
//            nodeName = makeup;
        }
    }

    public static List<TemplateNode> getNT(List<TemplateNode> sources, Integer innerId, String nodeName) {
        //验证节点重复
        Set<String> nodeSet = new HashSet<>();
        //验证数据表不一致
        Set<String> tableSet = new HashSet<>();
        //验证目标字段重复
        Set<String> targetNameSet = new HashSet<>();
        //验证节点类型不一致
        Set<Integer> nodeTypeSet = new HashSet<>();
        //验证节点类型为数组时，普通数据类型的节点出现多个
        List<String> arrayTypeList = new ArrayList<>();
        //验证children下存在主键
        List<String> primaryList = new ArrayList<>();
//        Set<String> nodeNameSet = new HashSet<>();
        List<TemplateNode> result = new ArrayList<>();
        List<TemplateNode> target = sources.stream().filter
                (item -> Objects.equals(innerId, item.getParentId())).collect(Collectors.toList());
        for (TemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (innerId != IfmApiParamsEnums.root_node.parentId) {
                TemplateNode templateNode = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(templateNode);
            }
            if (b) {
                String makeup = nodeName;
                if (item.getDataType() == 1) {
                    nodeName += "[primary:" + item.getNodeName() + "].";
                } else if (item.getDataType() == 0) {
                    nodeName += item.getNodeName() + ".";
                } else {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s存在子节点，该节点的数据类型只能是节点或主键", item.getInnerId(), item.getNodeName()));
                }
                item.setChildren(getNT(sources, item.getInnerId(), nodeName));
                item.setFullNodeName(nodeName);
                nodeName = makeup;
            } else {
                if (item.getDataType() == 2 || item.getDataType() == 3) {
                    item.setFullNodeName(nodeName + "[foreign:" + item.getNodeName() + "]");
                } else {
                    item.setFullNodeName(nodeName + item.getNodeName());
                }
                //验证children不能出现主键数据类型
                if (item.getDataType() == 1) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s无子节点的对象不能出现主键数据类型", item.getInnerId(), item.getNodeName()));
                }
            }
            // 非主键参与目标字段重复判断
            if (item.getDataType() != 1 && item.getDataType() != 0) {
                if (!targetNameSet.add(item.getTargetName())) {
                    throw new RuntimeException(String.format("id：%s,目标字段：%s重复了", item.getInnerId(), item.getNodeName()));
                }
                //非拆分匹配节点重复判断
                if (item.getMatchType() != 1) {
                    if (!nodeSet.add(item.getNodeName())) {
                        throw new RuntimeException(String.format("id：%s,平台字段：%s重复了", item.getInnerId(), item.getNodeName()));
                    }
                }
            }
            //一个children下的对象只能在储存在一张表
            if (item.getDataType() == 9 || item.getDataType() == 2 || item.getDataType() == 3) {
                //并集外键
                if (item.getDataType() == 3) {
                    if (Objects.equals(item.getForeignId(), item.getParentNode().getInnerId())) {
                        throw new RuntimeException(String.format("id：%s,平台字段：%s的外键id不能设置为当前节点的主键id", item.getInnerId(), item.getNodeName()));
                    }
                }
                tableSet.add(item.getTargetTable());
                tableSet.add(item.getParentNode().getTargetTable());
                //跟children和主键所属表做对比，必须一致
                if (tableSet.size() > 1) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s所属表不一致", item.getInnerId(), item.getNodeName()));
                }
                nodeTypeSet.add(item.getNodeType());
                nodeTypeSet.add(item.getParentNode().getNodeType());
                //跟children和主键所属节点类型做对比，必须一致
                if (nodeTypeSet.size() > 1) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s的节点类型不一致", item.getInnerId(), item.getNodeName()));
                }
                //如果是数组，数组只能有一个普通数据类型的节点，主键和外键除外
                if (item.getParentNode().getParentNode().getNodeType() == 2) {
                    if (item.getDataType() == 9) {
                        arrayTypeList.add(item.getNodeName());
                    }
                    if (arrayTypeList.size() > 1) {
                        throw new RuntimeException(String.format("id：%s，平台字段：%s，目标字段：%s，节点类型是%s，只能存在一个普通数据类型节点",
                                item.getInnerId(), item.getNodeName(), item.getTargetName(), item.getParentNode().getParentNode().getNodeType()));
                    }
                }
            }
            result.add(item);
        }
        //验证拆分匹配的字段是否根完全匹配有重复
        Map<String, List<TemplateNode>> collect = result.stream().filter(item -> Objects.equals(item.getMatchType(), 1)).collect(Collectors.groupingBy(item -> item.getNodeName()));
        for (Map.Entry<String, List<TemplateNode>> entry : collect.entrySet()) {
            if (nodeSet.contains(entry.getKey())) {
                for (TemplateNode item : entry.getValue()) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s重复了", item.getInnerId(), item.getNodeName()));
                }
            }
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

    public static TemplateNode getUp(TemplateNode templateNode, String tableName, String foreignField) {
        if (Objects.equals(templateNode.getParentNode().getTargetTable(), tableName)) {
            return getUp(templateNode.getParentNode(), tableName, foreignField);
        } else if (!Objects.equals(templateNode.getParentNode().getTargetName(), foreignField)) {
            return getUp(templateNode.getParentNode(), tableName, foreignField);
        } else {
            return templateNode.getParentNode();
        }
    }

    @Data
    public static class IfmPlatformTemplateDetailDTO implements Serializable {

        public IfmPlatformTemplateDetailDTO() {
        }

        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName, Integer dataType,
                                            Integer matchType, Integer nodeType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.dataType = dataType;
            this.matchType = matchType;
            this.nodeType = nodeType;

        }


        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName, Integer dataType, String targetTable,
                                            String targetName, String foreignField, Integer matchType, Integer nodeType,
                                            Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.dataType = dataType;
            this.targetTable = targetTable;
            this.targetName = targetName;
            this.foreignField = foreignField;
            this.matchType = matchType;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
        }


        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName, Integer dataType, String targetTable,
                                            String targetName, Integer matchType, Integer nodeType, Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.dataType = dataType;
            this.targetTable = targetTable;
            this.targetName = targetName;
            this.matchType = matchType;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
        }


        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName, Integer dataType, String targetTable,
                                            String targetName, Integer matchType, Integer selectType, String selectStart, String selectEnd,
                                            Integer nodeType, Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.dataType = dataType;
            this.targetTable = targetTable;
            this.targetName = targetName;
            this.matchType = matchType;
            this.selectType = selectType;
            this.selectStart = selectStart;
            this.selectEnd = selectEnd;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
        }

        public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, Integer foreignId, String nodeName, Integer dataType,
                                            String targetTable, String targetName, Integer matchType, Integer nodeType,
                                            Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.dataType = dataType;
            this.targetTable = targetTable;
            this.targetName = targetName;
            this.foreignId = foreignId;
            this.matchType = matchType;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
        }


        public Integer innerId;

        public Integer parentId;

        private String targetTable;

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
         * 数据类型(0：节点，1：主键，2：外键，3：外键（（非同父节点和唯一节点）并集外键），9：普通字段) (字典类型是data_type)
         */
        private Integer dataType;
        private String dataTypeStr;
        private Integer foreignId;
        private String foreignField;

        /**
         * 节点类型 0:对象，1：对象数组，2：数组，最终节点
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
        String tableName;
        List<Table> list = new ArrayList<>();
        FullTable parent;
        List<FullTable> children = new ArrayList<>();

    }

    @Data
    public static class Table {
        public Table() {
        }

        public String id;
        public Integer innerId;
        public String targetTable;
        public String targetName;
        public String nodeName;
        public Integer fieldType;
        public Object value;

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
                    ", dataType=" + this.getDataType() +
                    ", dataTypeStr='" + this.getDataTypeStr() + '\'' +
                    ", foreignId=" + this.getForeignId() +
                    ", foreignField='" + this.getForeignField() + '\'' +
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
