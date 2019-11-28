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
public class JsonToApi {

    private List<TemplateNode> getTemplateNodes(List<IfmResponseTemplateDetailDTO> sources) {
        List<TemplateNode> sources2 = new ArrayList<>();
        for (int i = 0; i < sources.size(); i++) {
            IfmResponseTemplateDetailDTO item = sources.get(i);
            String str = "";
            if (item.getNodeType() == 0) {
                str = "对象";
            } else if (item.getNodeType() == 1) {
                str = "对象数组";
            } else if (item.getNodeType() == 2) {
                str = "数组";
            } else if (item.getNodeType() == 3) {
                str = "最终节点";
            }
            item.setNodeTypeStr(str);
            if (StringUtils.isEmpty(item.getNodeName())) {
                throw new RuntimeException(String.format("节点名词不能为空"));
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
            "  \"entryOrder2\": {\n" +
            "    \"totalOrderLines2\": \"\\n            单据总行数，int，当单据需要分多个请求发送时，发送方需要将totalOrderLines填入，接收方收到后，根据实际接收到的条数和totalOrderLines进行比对，如果小于，则继续等待接收请求。如果等于，则表示该单据的所有请求发送完成。\\n        \",\n" +
            "    \"entryOrderCode2\": \"入库单编码, string (50) , 必填\",\n" +
            "    \"ownerCode2\": \"货主编码, string (50)\",\n" +
            "    \"warehouseCode\": \"仓库编码, string (50)，必填\",\n" +
            "    \"entryOrderId\": \"仓储系统入库单ID, string (50) , 条件必填\",\n" +
            "    \"entryOrderType\": \"入库单类型 ，SCRK=生产入库，LYRK=领用入1库，CCRK=残次品入库，CGRK=采购入库, DBRK=调拨入库, QTRK=其他入库，B2BRK=B2B入库\\n        \",\n" +
            "    \"outBizCode\": \"外部业务编码, 消息ID, 用于去重, ISV对于同一请求，分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，请求不会被重复处理, ,必填\",\n" +
            "    \"confirmType\": \"支持出入库单多次收货, int，\\n            多次收货后确认时\\n            0 表示入库单最终状态确认；\\n            1 表示入库单中间状态确认；\\n            每次入库传入的数量为增量，特殊情况，同一入库单，如果先收到0，后又收到1，允许修改收货的数量。\\n        \",\n" +
            "    \"status\": \"入库单状态, string (50) , 必填 (NEW-未开始处理, ACCEPT-仓库接单 , PARTFULFILLED-部分收货完成, FULFILLED-收货完成, EXCEPTION-异常,\\n            CANCELED-取消, CLOSED-关闭, REJECT-拒单, CANCELEDFAIL-取消失败) , (只传英文编码)\\n        \",\n" +
            "    \"freight\": \"快递费用 (元) , double (18, 2)\",\n" +
            "    \"operateTime\": \"操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)\",\n" +
            "    \"remark\": \"备注, string (500)\",\n" +
            "    \"haha\": {\n" +
            "      \"haha1\": \"1111\",\n" +
            "      \"haha2\": \"1111\",\n" +
            "      \"haha3\": \"1111\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"orderLines\": [\n" +
            "    {\n" +
            "      \"orderLine\": {\n" +
            "        \"outBizCode\": \"外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用\",\n" +
            "        \"orderLineNo\": \"单据行号，string（50）\",\n" +
            "        \"ownerCode\": \"货主编码, string (50)\",\n" +
            "        \"itemCode\": \"商品编码, string (50) , 必填\",\n" +
            "        \"itemId\": \"仓储系统商品ID, string (50) , 条件必填\",\n" +
            "        \"snList\": [\n" +
            "          \"商品序列号, string(40)\",\n" +
            "          \"商品序列号, string(50)\"\n" +
            "        ],\n" +
            "        \"itemName\": \"商品名称, string (200)\",\n" +
            "        \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\",\n" +
            "        \"planQty\": \"应收数量, int\",\n" +
            "        \"actualQty\": \"实收数量, int，必填\",\n" +
            "        \"batchCode\": \"批次编码, string (50)\",\n" +
            "        \"productDate\": \"商品生产日期，string（10）， YYYY-MM-DD\",\n" +
            "        \"expireDate\": \"商品过期日期，string（10），YYYY-MM-DD\",\n" +
            "        \"produceCode\": \"生产批号, string (50)\",\n" +
            "        \"cc\": {\n" +
            "          \"zjw\": {\n" +
            "            \"zjw2\": \"周家伟\"\n" +
            "          },\n" +
            "          \"zjw3\": [\n" +
            "            {\n" +
            "              \"zjw4\": \"周家伟\",\n" +
            "              \"zjw5\": \"dsadas\",\n" +
            "              \"zjw6\": [\n" +
            "                1,\n" +
            "                2,\n" +
            "                3,\n" +
            "                4,\n" +
            "                5\n" +
            "              ],\n" +
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
            "              \"zjw4\": \"周家伟\",\n" +
            "              \"zjw5\": \"dsadas\",\n" +
            "              \"zjw6\": [\n" +
            "                1,\n" +
            "                2,\n" +
            "                3,\n" +
            "                4,\n" +
            "                5\n" +
            "              ],\n" +
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
            "        },\n" +
            "        \"batchs\": [\n" +
            "          {\n" +
            "            \"batchCode\": \"批次编号，string(50)\",\n" +
            "            \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"produceCode\": \"生产批号，string(50)，\",\n" +
            "            \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "            \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"batchCode\": \"批次编号，string(50)\",\n" +
            "            \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"produceCode\": \"生产批号，string(50)，\",\n" +
            "            \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "            \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"remark\": \"备注, string (500)\"\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"orderLine\": {\n" +
            "        \"outBizCode\": \"外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用\",\n" +
            "        \"orderLineNo\": \"单据行号，string（50）\",\n" +
            "        \"ownerCode\": \"货主编码, string (50)\",\n" +
            "        \"itemCode\": \"商品编码, string (50) , 必填\",\n" +
            "        \"itemId\": \"仓储系统商品ID, string (50) , 条件必填\",\n" +
            "        \"snList\": [\n" +
            "          \"商品序列号, string(40)\",\n" +
            "          \"商品序列号, string(50)\"\n" +
            "        ],\n" +
            "        \"itemName\": \"商品名称, string (200)\",\n" +
            "        \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\",\n" +
            "        \"planQty\": \"应收数量, int\",\n" +
            "        \"actualQty\": \"实收数量, int，必填\",\n" +
            "        \"batchCode\": \"批次编码, string (50)\",\n" +
            "        \"productDate\": \"商品生产日期，string（10）， YYYY-MM-DD\",\n" +
            "        \"expireDate\": \"商品过期日期，string（10），YYYY-MM-DD\",\n" +
            "        \"produceCode\": \"生产批号, string (50)\",\n" +
            "        \"cc\": {\n" +
            "          \"zjw\": {\n" +
            "            \"zjw2\": \"周家伟\"\n" +
            "          },\n" +
            "          \"zjw3\": [\n" +
            "            {\n" +
            "              \"zjw4\": \"周家伟\",\n" +
            "              \"zjw5\": \"dsadas\",\n" +
            "              \"zjw6\": [\n" +
            "                1,\n" +
            "                2,\n" +
            "                3,\n" +
            "                4,\n" +
            "                5\n" +
            "              ],\n" +
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
            "              \"zjw4\": \"周家伟\",\n" +
            "              \"zjw5\": \"dsadas\",\n" +
            "              \"zjw6\": [\n" +
            "                1,\n" +
            "                2,\n" +
            "                3,\n" +
            "                4,\n" +
            "                5\n" +
            "              ],\n" +
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
            "        },\n" +
            "        \"batchs\": [\n" +
            "          {\n" +
            "            \"batchCode\": \"批次编号，string(50)\",\n" +
            "            \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"produceCode\": \"生产批号，string(50)，\",\n" +
            "            \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "            \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"batchCode\": \"批次编号，string(50)\",\n" +
            "            \"productDate\": \"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"expireDate\": \"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "            \"produceCode\": \"生产批号，string(50)，\",\n" +
            "            \"inventoryType\": \"库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)\\n                    \",\n" +
            "            \"actualQty\": \"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"remark\": \"备注, string (500)\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void json() {
        List<IfmResponseTemplateDetailDTO> sources = new ArrayList<>();
        sources.add(new IfmResponseTemplateDetailDTO(-1, -2, "params", "params", 0));
        sources.add(new IfmResponseTemplateDetailDTO(1, -1, "entryOrder", "entryOrder", 0));
        sources.add(new IfmResponseTemplateDetailDTO(3, 1, "totalOrderLines", "totalOrderLines", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(4, 1, "entryOrderCode", "entryOrderCode", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(199, 1, "ownerCode", "ownerCode", 0, 3, 0));

        sources.add(new IfmResponseTemplateDetailDTO(300, -1, "entryOrder2", "entryOrder2", 0));
        sources.add(new IfmResponseTemplateDetailDTO(301, 300, "totalOrderLines2", "totalOrderLines", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(302, 300, "entryOrderCode2", "entryOrderCode", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(303, 300, "ownerCode2", "ownerCode", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(304, 300, "haha", "haha", 0));
        sources.add(new IfmResponseTemplateDetailDTO(305, 304, "haha1", "haha1", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(306, 304, "haha2", "haha2", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(307, 304, "haha3", "haha3", 0, 3, 0));


        sources.add(new IfmResponseTemplateDetailDTO(1001, -1, "orderLines", "orderLines", 1));
        sources.add(new IfmResponseTemplateDetailDTO(5, 1001, "orderLine", "", 0));
        sources.add(new IfmResponseTemplateDetailDTO(6, 5, "outBizCode", "outBizCode2", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(7, 5, "remark", "remark2", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(400, 5, "itemId", "itemId2", 0, 3, 0));
//
        sources.add(new IfmResponseTemplateDetailDTO(8, 5, "snList", "snList", 0, 2, 0));
//
        sources.add(new IfmResponseTemplateDetailDTO(14, 5, "batchs", "batchs", 1));
        sources.add(new IfmResponseTemplateDetailDTO(16, 14, "batchCode", "batchCode", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(16, 14, "produceCode", "produceCode", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(101, 14, "productDate", "productDate_text2", 1, 0, "0", "2", 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(102, 14, "productDate", "productDate_text3", 1, 0, "2", "4", 3, 0));
////
        sources.add(new IfmResponseTemplateDetailDTO(200, 5, "cc", "cc", 0));
        sources.add(new IfmResponseTemplateDetailDTO(201, 200, "zjw", "zjw", 0));
        sources.add(new IfmResponseTemplateDetailDTO(202, 201, "zjw2", "zjw2", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(203, 200, "zjw3", "zjw3", 1));
        sources.add(new IfmResponseTemplateDetailDTO(204, 203, "zjw4", "zjw4", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(205, 203, "zjw5", "zjw5", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(206, 203, "zjw6", "zjw6", 0, 2, 0));
        sources.add(new IfmResponseTemplateDetailDTO(207, 203, "zjw7", "zjw7", 0));
        sources.add(new IfmResponseTemplateDetailDTO(209, 207, "zjw8", "zjw8", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(210, 207, "zjw9", "zjw9", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(211, 207, "zjw10", "zjw10", 0));
        sources.add(new IfmResponseTemplateDetailDTO(212, 211, "zjw11", "zjw11", 0, 3, 0));
        sources.add(new IfmResponseTemplateDetailDTO(213, 211, "zjw12", "zjw12", 0, 3, 0));

        List<TemplateNode> sources2 = getTemplateNodes(sources);
        List<TemplateNode> templateNodes = getNT(sources2, IfmApiParamsEnums.root_node.parentId, "", "");
        validate(sources2);
        validate(templateNodes.get(0));
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

    private void validate(List<TemplateNode> sources2) {
        List<TemplateNode> splitList = new ArrayList<>();
        for (Iterator<TemplateNode> iterator = sources2.iterator(); iterator.hasNext(); ) {
            TemplateNode item = iterator.next();
            if (StringUtils.isEmpty(item.getTargetName()) && item.getNodeType() == 2) {
                throw new RuntimeException(String.format("%s%s的目标字段不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }
            if (item.getNodeType() == 2 && item.getFieldType() == null) {
                throw new RuntimeException(String.format("%s%s的字段类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }
            if (item.getNodeType() == 3 && item.getMatchType() == null) {
                throw new RuntimeException(String.format("%s%s的参数匹配类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }
            if (item.getNodeType() == 2 && item.getMatchType() == 1) {
                throw new RuntimeException(String.format("%s%s的参数只能设置为完全匹配", item.getNodeTypeStr(), item.getFullNodeName()));
            }

            if (StringUtils.isEmpty(item.getTargetName()) && item.getNodeType() == 3) {
                throw new RuntimeException(String.format("%s%s的目标字段不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }
            if (item.getNodeType() == 3 && item.getFieldType() == null) {
                throw new RuntimeException(String.format("%s%s的字段类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }
            if (item.getNodeType() == 3 && item.getMatchType() == null) {
                throw new RuntimeException(String.format("%s%s的参数匹配类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }
            if (item.getNodeType() == 3 && item.getMatchType() == 1 && item.getSelectType() == null) {
                throw new RuntimeException(String.format("%s%s的参数匹配类型规则不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
            }

            //顶级节点的params.开头的节点不算在重复判断项，
            if (item.getFullTargetName().equals("params.")) {
                iterator.remove();
                continue;
            }
            //拆分匹配不要
            if (item.getMatchType() != null && item.getMatchType() == 1) {
                splitList.add(item);
                iterator.remove();
            }
        }
        Set<String> nodeName = new HashSet<>();
        Set<String> targetName = new HashSet<>();
        for (TemplateNode item : sources2) {
            if (!nodeName.add(item.getFullNodeName())) {
                throw new RuntimeException(String.format("节点%s重复。", item.getFullNodeName()));
            }
            //目标字段只有节点类型=3时才判断重复
            if (item.getNodeType() == 3) {
                if (!targetName.add(item.getFullTargetName())) {
                    throw new RuntimeException(String.format("目标字段%s重复。", item.getFullTargetName()));
                }
            }
        }
        Map<String, List<TemplateNode>> collect = splitList.stream().collect(Collectors.groupingBy(item -> item.getFullNodeName()));
        for (Map.Entry<String, List<TemplateNode>> item : collect.entrySet()) {
            if (!nodeName.add(item.getKey())) {
                throw new RuntimeException(String.format("节点%s重复。", item.getKey()));
            }
        }
        for (TemplateNode item : splitList) {
            //目标字段只有节点类型=3时才判断重复
            if (item.getNodeType() == 3) {
                if (!targetName.add(item.getFullTargetName())) {
                    throw new RuntimeException(String.format("目标字段%s重复。", item.getFullTargetName()));
                }
            }
        }
    }

    private void validate(TemplateNode templateNode) {
        if (templateNode.getNodeType() == 2 || templateNode.getNodeType() == 3) {
            if (!CollectionUtils.isEmpty(templateNode.getChildren())) {
                throw new RuntimeException(String.format("节点%s的数据类型是%s，不能包含子集合",
                        templateNode.getFullNodeName(), templateNode.getNodeTypeStr()));
            }
        } else {
            if (CollectionUtils.isEmpty(templateNode.getChildren())) {
                throw new RuntimeException(String.format("节点%s的数据类型是%s，子集合不能为空",
                        templateNode.getFullNodeName(), templateNode.getNodeTypeStr()));
            }
        }
        for (TemplateNode item : templateNode.getChildren()) {
            if (StringUtils.isEmpty(item.getTargetName())) {
                if (item.getNodeType() == 1 || item.getNodeType() == 2) {
                    throw new RuntimeException(String.format("当前节点%s的类型是%s，父节点%s的类型是%s ,目标字段不为能空。"
                            , item.getFullNodeName(), item.getNodeTypeStr(), item.getParentNode().getFullNodeName(),
                            item.getParentNode().getNodeTypeStr()));
                }
            }
            validate(item);
        }
    }

    private void cc(TemplateNode nt, JSONArray jsonArray) {
        Map<String, Object> map = new LinkedHashMap<>();
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
            //当循环至根节点时重置nodeName
            if (item.getParentId() == IfmApiParamsEnums.root_node.innerId) {
                nodeName = "";
            }
            Object o = node.get(item.getNodeName());
            if (o == null) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询不到节点%s，节点类型%s", nodeName,
                        node.keySet(), item.getFullNodeName(), item.getNodeTypeStr()));
            }
            if (CollectionUtils.isEmpty(item.getChildren())) {
                if (StringUtils.isEmpty(item.getTargetName())) {
                    continue;
                }
                validate2(item, node, nodeName, o);
                if (item.getParentNode().getNodeType() == 1) {
                    LinkedList<Map<String, Object>> list = (LinkedList<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                    Map<String, Object> child = list.getLast();
                    child.put(item.getTargetName(), o);
                } else {
                    map.put(item.getTargetName(), o);
                }
                continue;
            }
            nodeName += item.getNodeName() + ".";
            System.out.println("是什么" + nodeName);
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
                if (StringUtils.isEmpty(item.getTargetName())) {
                    if (item.getParentNode().getNodeType() == 1) {
                        LinkedList<Map<String, Object>> o1 = (LinkedList<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                        Map<String, Object> last = o1.getLast();
                        if (last.size() > 0) {
                            last = new LinkedHashMap<>();
                            o1.add(last);
                        }
                        this.jsonToData(item.getChildren(), last, jsonObject, nodeName);
                    } else {
                        this.jsonToData(item.getChildren(), map, jsonObject, nodeName);
                    }
                } else {
                    if (item.getParentNode().getNodeName().equals("params")) {
                        if (item.getNodeType() == 0) {
                            Map<String, Object> childMap = new LinkedHashMap<>();
                            map.put(item.getTargetName(), childMap);

                            this.jsonToData(item.getChildren(), childMap, jsonObject, nodeName);
                            System.out.println("aaa");
                        } else if (item.getNodeType() == 1) {
                            LinkedList<Map<String, Object>> subMapList = (LinkedList<Map<String, Object>>) map.get(item.getTargetName());
                            if (CollectionUtils.isEmpty(subMapList)) {
                                subMapList = new LinkedList<>();
                                map.put(item.getTargetName(), subMapList);
                            }
                            subMapList.add(new LinkedHashMap<>());
                            // 对象数组
                            this.jsonToData(item.getChildren(), map, jsonObject, nodeName);
                            System.out.println("aaa");
                        }
                    } else {
                        //对象
                        if (item.getNodeType() == 0) {
                            //父节点是对象
                            if (item.getParentNode().getNodeType() == 0) {
                                //父节点是对象
                                Map<String, Object> first = new LinkedHashMap<>();
                                map.put(item.getTargetName(), first);
                                this.jsonToData(item.getChildren(), first, jsonObject, nodeName);
                            } else {
                                LinkedList<Map<String, Object>> parentList = (LinkedList<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                                Map<String, Object> subMap = parentList.getLast();
                                Map<String, Object> second = new LinkedHashMap<>();
                                if (subMap.containsKey(item.getTargetName())) {
                                    subMap = new LinkedHashMap<>();
                                    parentList.add(subMap);
                                    subMap.put(item.getTargetName(), second);
                                } else {
                                    subMap.put(item.getTargetName(), second);
                                }
                                this.jsonToData(item.getChildren(), second, jsonObject, nodeName);
                            }
                            System.out.println("aaa");
                        } else if (item.getNodeType() == 1) {
                            //当前是对象数组
                            if (item.getParentNode().getNodeType() == 0) {
                                //父节点是对象
                                LinkedList<Map<String, Object>> subMapList = (LinkedList<Map<String, Object>>) map.get(item.getTargetName());
                                if (CollectionUtils.isEmpty(subMapList)) {
                                    subMapList = new LinkedList<>();
                                    map.put(item.getTargetName(), subMapList);
                                }
                                subMapList.add(new LinkedHashMap<>());
                                this.jsonToData(item.getChildren(), map, jsonObject, nodeName);
                                System.out.println("aaa");
                            } else {
                                // 父节点是对象数组
                                LinkedList<Map<String, Object>> parentList = (LinkedList<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                                Map<String, Object> sub = parentList.getLast();
                                LinkedList<Map<String, Object>> subMapList = (LinkedList<Map<String, Object>>) sub.get(item.getTargetName());
                                if (CollectionUtils.isEmpty(subMapList)) {
                                    subMapList = new LinkedList<>();
                                    sub.put(item.getTargetName(), subMapList);
                                }
                                Map<String, Object> second = new LinkedHashMap<>();
                                subMapList.add(second);
                                this.jsonToData(item.getChildren(), sub, jsonObject, nodeName);
                                System.out.println("aaa");
                            }
                        } else if (item.getNodeType() == 2) {
                            //数组
                            List<Object> subArray = new LinkedList<>();
                            map.put(item.getTargetName(), subArray);
                            this.jsonToData(item.getChildren(), map, jsonObject, nodeName);
                            System.out.println("aaa");
                        }
                    }
                }
            }
        }

    }

    private void validate2(TemplateNode nt, JSONObject node, String nodeName, Object o) {
        //数组，取得的节点一定是jsonArray，并且集合里的数据一定是基本数据类型
        if (nt.getNodeType() == 2) {
            if (!(o instanceof JSONArray)) {
                throw new RuntimeException(String.format("设置节点对象，当前节点%s的子节点%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", nodeName, node.keySet(), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
            JSONArray jsonArray = (JSONArray) o;
            for (Object item : jsonArray) {
                if (item instanceof JSONArray || item instanceof JSONObject) {
                    throw new RuntimeException(String.format("设置节点对象，当前节点%s的子节点%s查询到节点%s，节点类型是%s，" +
                                    "但是源数据该节点的类型非%s", nodeName, node.keySet(), nt.getFullNodeName(),
                            nt.getNodeTypeStr(), nt.getNodeTypeStr()));
                }
            }
        } else if (nt.getNodeType() == 3) {
            // 最终节点，取得的对象一定不是jsonObject和jsonArray
            if (o instanceof JSONObject && o instanceof JSONArray) {
                throw new RuntimeException(String.format("设置节点对象，当前节点%s的子节点%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", nodeName, node.keySet(), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
        } else {
            throw new RuntimeException(String.format("设置节点对象，当前节点%s的类型是%s，不能设置值", nt.getFullNodeName(),
                    nt.getNodeTypeStr()));
        }
    }


    private void validate(TemplateNode nt, JSONObject node, String nodeName, Object o) {
        // 对象
        if (nt.getNodeType() == 0) {
            if (!(o instanceof JSONObject)) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", nodeName, node.keySet(), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
        } else if (nt.getNodeType() == 1) {
            //对象数组
            if (!(o instanceof JSONArray)) {
                throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", nodeName, node.keySet(), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
            JSONArray jsonArray = (JSONArray) o;
            for (Object item : jsonArray) {
                if (!(item instanceof JSONArray || item instanceof JSONObject)) {
                    throw new RuntimeException(String.format("获取节点对象，当前节点%s的子节点%s查询到节点%s，节点类型是%s，" +
                                    "但是源数据该节点的类型非%s", nodeName, node.keySet(), nt.getFullNodeName(),
                            nt.getNodeTypeStr(), nt.getNodeTypeStr()));
                }
            }
        } else {
            throw new RuntimeException(String.format("当前节点%s的子节点%s查询到节点%s，节点类型是%s，不能作为对象类型的判断",
                    nodeName, node.keySet(), nt.getFullNodeName(), nt.getNodeTypeStr()));
        }
    }

    /**
     * 只有节点类型为0的根节点的目标字段可以为空行，其他的一律不为空
     *
     * @param sources
     * @param parentId
     * @param nodeName
     * @return
     */
    public static List<TemplateNode> getNT(List<TemplateNode> sources, Integer parentId, String nodeName, String targetName) {
        List<TemplateNode> result = new ArrayList<>();
        List<TemplateNode> target = sources.stream().filter
                (item -> Objects.equals(parentId, item.getParentId())).collect(Collectors.toList());
        for (TemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (parentId != IfmApiParamsEnums.root_node.parentId) {
                TemplateNode templateNode = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(templateNode);
            }
            if (b) {
                String makeup = nodeName;
                nodeName += item.getNodeName() + ".";

                String makeup2 = targetName;
                if (StringUtils.isNotEmpty(item.getTargetName())) {
                    targetName += item.getTargetName() + ".";
                }
                item.setChildren(getNT(sources, item.getInnerId(), nodeName, targetName));
                item.setFullNodeName(nodeName);
                item.setFullTargetName(targetName);
                nodeName = makeup;
                targetName = makeup2;
            } else {
                item.setFullNodeName(nodeName + item.getNodeName());
                item.setFullTargetName(targetName + item.getTargetName());
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 递归取上级节点,取节点类型!=0
     *
     * @param node
     * @return
     */
    static TemplateNode getUpByNodeType(TemplateNode node) {
        if (node.getInnerId() != IfmApiParamsEnums.root_node.getParentId()) {
            if (node.getNodeType() == 0) {
                if (node.getParentNode() == null) {
                    return null;
                }
                return getUpByNodeType(node.getParentNode());
            }
            return node.getParentNode();
        } else {
            return null;
        }
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
    public static class IfmResponseTemplateDetailDTO implements Serializable {

        public IfmResponseTemplateDetailDTO() {
        }


        public IfmResponseTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                            String targetName, Integer nodeType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.nodeType = nodeType;
            this.targetName = targetName;

        }


        public IfmResponseTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
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


        public IfmResponseTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
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
         * 节点类型 0:对象，1：对象数组，2：数组，3：最终节点
         */
        private Integer nodeType;

        /**
         * 节点类型 0:对象，1：对象数组，2：数组，3：最终节点
         */
        private String nodeTypeStr;

        /**
         * 字段类型(0：字符串 1：时间 2：数字)
         */
        private Integer fieldType;

        private String fieldTypeStr;
    }


    @Data
    private static class TemplateNode extends IfmResponseTemplateDetailDTO implements Serializable {
        public TemplateNode() {
        }

        public String fullNodeName;
        public String fullTargetName;
        public TemplateNode parentNode;
        public Object value;
        public List<TemplateNode> children;

        public List<TemplateNode> getChildren() {
            if (children == null) {
                children = new ArrayList<>();
            }
            return children;
        }

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
