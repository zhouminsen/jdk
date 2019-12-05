package com.zjw.jdk.xml.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjw.jdk.util.UtilFuns;
import com.zjw.jdk.xml.convert.platform.FullTable;
import com.zjw.jdk.xml.convert.platform.IfmPlatformTemplateDetailDTO;
import com.zjw.jdk.xml.convert.platform.PlatformTemplateNode;
import com.zjw.jdk.xml.convert.platform.Table;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 加入matchtype
 * Created by Administrator on 2019-10-12.
 */
public class JsonToData2 {

    private List<PlatformTemplateNode> getTemplateNodes(List<IfmPlatformTemplateDetailDTO> sources) {
        List<PlatformTemplateNode> sources2 = new ArrayList<>();
        for (int i = 0; i < sources.size(); i++) {
            IfmPlatformTemplateDetailDTO item = sources.get(i);
            if (item.getNodeType() == null) {
                throw new RuntimeException(String.format("id：%s,平台字段：%s未设置节点类型", item.getInnerId(), item.getNodeName()));
            }

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

            if (item.getNodeType() == 3) {
                if (item.getDataType() == null) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置字段类型", item.getInnerId(), item.getNodeName()));
                }

                if (item.getDataType() == 1) {
                    str = "主键";
                } else if (item.getDataType() == 2) {
                    str = "外键";
                } else if (item.getDataType() == 3) {
                    str = "外键";
                } else if (item.getDataType() == 9) {
                    str = "普通字段";
                }
                item.setDataTypeStr(str);


                if (item.getMatchType() == null) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置匹配类型", item.getInnerId(), item.getNodeName()));
                }
                //除了普通字段，其他都只能是完全匹配
                if ((item.getDataType() == 1 || item.getDataType() == 2 || item.getDataType() == 3) && item.getMatchType() != 0) {
                    throw new RuntimeException(String.format("id：%s,平台字段%s的数据类型是%s，只能设置为完全匹配",
                            item.getInnerId(), item.getNodeName(), item.getDataTypeStr()));

                }
                if (item.getDataType() == 2 || item.getDataType() == 3) {
                    if (item.getForeignId() == null) {
                        throw new RuntimeException(String.format("id：%s,平台字段%s的数据类型是%s，外键id不能为空",
                                item.getInnerId(), item.getNodeName(), item.getDataTypeStr()));
                    }
                    int count = 0;
                    // 循环当前索引前的对象，从中找当前对象的外键id，
                    for (int j = 0; j < i; j++) {
                        PlatformTemplateNode e = sources2.get(j);
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
            }
            PlatformTemplateNode platformTemplateNode = new PlatformTemplateNode();
            BeanUtils.copyProperties(item, platformTemplateNode);
            sources2.add(platformTemplateNode);
        }
        return sources2;
    }

    public List<PlatformTemplateNode> validate(List<IfmPlatformTemplateDetailDTO> list) {
        List<PlatformTemplateNode> sources2 = this.getTemplateNodes(list);
        List<PlatformTemplateNode> platformTemplateNodes = this.getNT(sources2, IfmApiParamsEnums.root_node.getParentId(), "", "");
        this.validateRepeat(sources2);
        return sources2;
    }

    public PlatformTemplateNode getTemplateNode(List<IfmPlatformTemplateDetailDTO> list) {
        List<PlatformTemplateNode> sources2 = this.getTemplateNodes(list);
        List<PlatformTemplateNode> templateNodes = this.getNT(sources2, IfmApiParamsEnums.root_node.getParentId(), "", "");
        this.validateRepeat(sources2);
//        this.removeParents(templateNodes.get(0));
        return templateNodes.get(0);
    }

    private void removeParents(PlatformTemplateNode templateNode) {
        if (templateNode.getParentNode() != null) {
            PlatformTemplateNode parent = new PlatformTemplateNode();
            BeanUtils.copyProperties(templateNode.getParentNode(), parent);
            //设置为nul避免循环依赖
            parent.setChildren(null);
            templateNode.setParentNode(parent);
        }
        for (PlatformTemplateNode item : templateNode.getChildren()) {
            this.removeParents(item);
        }
    }

    String str = "{\n" +
            "    \"entryOrder\":{\n" +
            "        \"totalOrderLines\":\"大撒大撒大撒、\",\n" +
            "        \"entryOrderCode\":\"入库单编码, string (50) , 必填\",\n" +
            "        \"ownerCode\":\"货主编码, string (50)\",\n" +
            "        \"warehouseCode\":\"仓库编码, string (50)，必填\",\n" +
            "        \"entryOrderId\":\"仓储系统入库单ID, string (50) , 条件必填\",\n" +
            "        \"entryOrderType\":\"大苏打撒旦\",\n" +
            "        \"outBizCode\":\"x'z'X'zX\",\n" +
            "        \"confirmType\":\"大撒大撒大撒\",\n" +
            "        \"status\":\"的撒旦撒旦\",\n" +
            "        \"freight\":\"快递费用 (元) , double (18, 2)\",\n" +
            "        \"operateTime\":\"操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)\",\n" +
            "        \"remark\":\"备注, string (500)\"\n" +
            "    },\n" +
            "    \"entryOrder2\":{\n" +
            "        \"totalOrderLines2\":\"dsadsads\",\n" +
            "        \"entryOrderCode2\":\"入库单编码, string (50) , 必填\",\n" +
            "        \"ownerCode2\":\"货主编码, string (50)\",\n" +
            "        \"warehouseCode\":\"仓库编码, string (50)，必填\",\n" +
            "        \"entryOrderId\":\"仓储系统入库单ID, string (50) , 条件必填\",\n" +
            "        \"entryOrderType\":\"撒大大撒旦\",\n" +
            "        \"outBizCode\":\"的撒旦撒旦\",\n" +
            "        \"confirmType\":\"支持出入库单多次收货, int，\",\n" +
            "        \"status\":\"入库单状态, string (50) , 必填 (NEW-未开始处理, ACCEPT-仓库接单\",\n" +
            "        \"freight\":\"快递费用 (元) , double (18, 2)\",\n" +
            "        \"operateTime\":\"操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)\",\n" +
            "        \"remark\":\"备注, string (500)\",\n" +
            "        \"haha\":{\n" +
            "            \"haha1\":\"111\",\n" +
            "            \"haha2\":\"111\",\n" +
            "            \"haha3\":\"111\"\n" +
            "        }\n" +
            "    },\n" +
            "    \"orderLines\":{\n" +
            "        \"orderLine\":[\n" +
            "            {\n" +
            "                \"outBizCode\":\"外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用\",\n" +
            "                \"orderLineNo\":\"单据行号，string（50）\",\n" +
            "                \"ownerCode\":\"货主编码, string (50)\",\n" +
            "                \"itemCode\":\"商品编码, string (50) , 必填\",\n" +
            "                \"itemId\":\"仓储系统商品ID, string (50) , 条件必填\",\n" +
            "                \"foreignId\":\"1111\",\n" +
            "                \"snList\":{\n" +
            "                    \"sn\":[\n" +
            "                        \"商品序列号, string(40)\",\n" +
            "                        \"商品序列号, string(50)\"\n" +
            "                    ]\n" +
            "                },\n" +
            "                \"itemName\":\"商品名称, string (200)\",\n" +
            "                \"inventoryType\":\"大撒大撒大撒\",\n" +
            "                \"planQty\":\"应收数量, int\",\n" +
            "                \"actualQty\":\"实收数量, int，必填\",\n" +
            "                \"batchCode\":\"批次编码, string (50)\",\n" +
            "                \"productDate\":\"商品生产日期，string（10）， YYYY-MM-DD\",\n" +
            "                \"expireDate\":\"商品过期日期，string（10），YYYY-MM-DD\",\n" +
            "                \"produceCode\":\"生产批号, string (50)\",\n" +
            "                \"cc\":{\n" +
            "                    \"zjw\":{\n" +
            "                        \"zjw2\":\"周家伟\"\n" +
            "                    },\n" +
            "                    \"zjw3_s\":{\n" +
            "                        \"zjw3\":[\n" +
            "                            {\n" +
            "                                \"zjw4\":\"周家伟\",\n" +
            "                                \"zjw5\":\"dsadas\",\n" +
            "                                \"zjw6\":{\n" +
            "                                    \"zjw6_6\":[\n" +
            "                                        \"1\",\n" +
            "                                        \"2\",\n" +
            "                                        \"3\",\n" +
            "                                        \"4\",\n" +
            "                                        \"5\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                \"zjw7\":{\n" +
            "                                    \"zjw8\":\"周家伟1\",\n" +
            "                                    \"zjw9\":\"周家伟2\",\n" +
            "                                    \"zjw10\":{\n" +
            "                                        \"zjw11\":\"周家伟3\",\n" +
            "                                        \"zjw12\":\"周家伟4\"\n" +
            "                                    }\n" +
            "                                }\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"zjw4\":\"周家伟\",\n" +
            "                                \"zjw5\":\"dsadas\",\n" +
            "                                \"zjw6\":{\n" +
            "                                    \"zjw6_6\":[\n" +
            "                                        \"1\",\n" +
            "                                        \"2\",\n" +
            "                                        \"3\",\n" +
            "                                        \"4\",\n" +
            "                                        \"5\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                \"zjw7\":{\n" +
            "                                    \"zjw8\":\"周家伟1\",\n" +
            "                                    \"zjw9\":\"周家伟2\",\n" +
            "                                    \"zjw10\":{\n" +
            "                                        \"zjw11\":\"周家伟3\",\n" +
            "                                        \"zjw12\":\"周家伟4\"\n" +
            "                                    }\n" +
            "                                }\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                },\n" +
            "                \"batchs\":{\n" +
            "                    \"batch\":[\n" +
            "                        {\n" +
            "                            \"batchCode\":\"批次编号，string(50)\",\n" +
            "                            \"productDate\":\"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"expireDate\":\"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"produceCode\":\"生产批号，string(50)，\",\n" +
            "                            \"inventoryType\":\"的撒旦撒旦\",\n" +
            "                            \"actualQty\":\"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"batchCode\":\"批次编号，string(50)\",\n" +
            "                            \"productDate\":\"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"expireDate\":\"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"produceCode\":\"生产批号，string(50)，\",\n" +
            "                            \"inventoryType\":\"dsadasdsa\",\n" +
            "                            \"actualQty\":\"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                \"remark\":\"备注, string (500)\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"outBizCode\":\"外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用\",\n" +
            "                \"orderLineNo\":\"单据行号，string（50）\",\n" +
            "                \"ownerCode\":\"货主编码, string (50)\",\n" +
            "                \"itemCode\":\"商品编码, string (50) , 必填\",\n" +
            "                \"itemId\":\"仓储系统商品ID, string (50) , 条件必填\",\n" +
            "                \"snList\":{\n" +
            "                    \"sn\":[\"商品序列号, string(200)\"]\n" +
            "                },\n" +
            "                \"itemName\":\"商品名称, string (200)\",\n" +
            "                \"inventoryType\":\"dasdsa\",\n" +
            "                \"planQty\":\"应收数量, int\",\n" +
            "                \"actualQty\":\"实收数量, int，必填\",\n" +
            "                \"batchCode\":\"批次编码, string (50)\",\n" +
            "                \"productDate\":\"商品生产日期，string（10）， YYYY-MM-DD\",\n" +
            "                \"expireDate\":\"商品过期日期，string（10），YYYY-MM-DD\",\n" +
            "                \"produceCode\":\"生产批号, string (50)\",\n" +
            "                \"cc\":{\n" +
            "                    \"zjw\":{\n" +
            "                        \"zjw2\":\"周家伟\"\n" +
            "                    },\n" +
            "                    \"zjw3_s\":{\n" +
            "                        \"zjw3\":[\n" +
            "                            {\n" +
            "                                \"zjw4\":\"周家伟\",\n" +
            "                                \"zjw5\":\"dsadas\",\n" +
            "                                \"zjw6\":{\n" +
            "                                    \"zjw6_6\":[\n" +
            "                                        \"1\",\n" +
            "                                        \"2\",\n" +
            "                                        \"3\",\n" +
            "                                        \"4\",\n" +
            "                                        \"5\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                \"zjw7\":{\n" +
            "                                    \"zjw8\":\"周家伟1\",\n" +
            "                                    \"zjw9\":\"周家伟2\",\n" +
            "                                    \"zjw10\":{\n" +
            "                                        \"zjw11\":\"周家伟3\",\n" +
            "                                        \"zjw12\":\"周家伟4\"\n" +
            "                                    }\n" +
            "                                }\n" +
            "                            },\n" +
            "                            {\n" +
            "                                \"zjw4\":\"周家伟\",\n" +
            "                                \"zjw5\":\"dsadas\",\n" +
            "                                \"zjw6\":{\n" +
            "                                    \"zjw6_7\":\"1\",\n" +
            "                                    \"zjw6_6\":[\n" +
            "                                        \"2\",\n" +
            "                                        \"3\",\n" +
            "                                        \"4\",\n" +
            "                                        \"5\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                \"zjw7\":{\n" +
            "                                    \"zjw8\":\"周家伟1\",\n" +
            "                                    \"zjw9\":\"周家伟2\",\n" +
            "                                    \"zjw10\":{\n" +
            "                                        \"zjw11\":\"周家伟3\",\n" +
            "                                        \"zjw12\":\"周家伟4\"\n" +
            "                                    }\n" +
            "                                }\n" +
            "                            }\n" +
            "                        ]\n" +
            "                    }\n" +
            "                },\n" +
            "                \"batchs\":{\n" +
            "                    \"batch\":[\n" +
            "                        {\n" +
            "                            \"batchCode\":\"批次编号，string(50)\",\n" +
            "                            \"productDate\":\"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"expireDate\":\"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"produceCode\":\"生产批号，string(50)，\",\n" +
            "                            \"inventoryType\":\"撒大苏打撒旦\",\n" +
            "                            \"actualQty\":\"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "                        },\n" +
            "                        {\n" +
            "                            \"batchCode\":\"批次编号，string(50)\",\n" +
            "                            \"productDate\":\"生产日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"expireDate\":\"过期日期，string(10)，YYYY-MM-DD\",\n" +
            "                            \"produceCode\":\"生产批号，string(50)，\",\n" +
            "                            \"inventoryType\":\"的撒大大撒旦撒旦\",\n" +
            "                            \"actualQty\":\"实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量\"\n" +
            "                        }\n" +
            "                    ]\n" +
            "                },\n" +
            "                \"remark\":\"备注, string (500)\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}";

    @Test
    public void xml() throws DocumentException {

        List<IfmPlatformTemplateDetailDTO> sources = new ArrayList<>();
        sources.add(new IfmPlatformTemplateDetailDTO(-1, -2, "params", 0));
        sources.add(new IfmPlatformTemplateDetailDTO(2, -1, "entryOrder", 0));
        sources.add(new IfmPlatformTemplateDetailDTO(3, 2, "zjw_id", 1, "t", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(4, 3, "totalOrderLines", 9, "t", "totalOrderLines", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(5, 3, "entryOrderCode", 9, "t", "entryOrderCode", 0, 3, 0));

//        sources.add(new IfmPlatformTemplateDetailDTO(150, 2, "zjw_id2", 1, "t", "id", 0, 3, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(151, 150, "totalOrderLines", 9, "t", "totalOrderLines", 0, 3, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(152, 150, "entryOrderCode", 9, "t", "entryOrderCode", 0, 3, 0));


        //子集外键
        sources.add(new IfmPlatformTemplateDetailDTO(100, 2, "zjw2_id", 1, "t_t", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(101, 100, 3, "foreign_id", 2, "t_t", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(102, 100, "outBizCode", 9, "t_t", "outBizCode", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(103, 100, "confirmType", 9, "t_t", "confirmType", 0, 3, 0));
//
//
        sources.add(new IfmPlatformTemplateDetailDTO(7, -1, "orderLines", 0));
        sources.add(new IfmPlatformTemplateDetailDTO(8, 7, "orderLine", 1));
        sources.add(new IfmPlatformTemplateDetailDTO(9, 8, "line_id", 1, "t2", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(10, 9, 3, "foreign_id", 3, "t2", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(11, 9, "outBizCode", 9, "t2", "outBizCode", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(12, 9, "remark", 9, "t2", "remark", 0, 3, 0));

        sources.add(new IfmPlatformTemplateDetailDTO(13, 8, "snList", 0));
        sources.add(new IfmPlatformTemplateDetailDTO(14, 13, "sn", 2));
        sources.add(new IfmPlatformTemplateDetailDTO(15, 14, "sn", 1, "t3", "sn2", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(16, 15, 9, "foreign_id", 2, "t3", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(17, 15, "aa", 9, "t3", "sn2", 0, 3, 0));

        sources.add(new IfmPlatformTemplateDetailDTO(200, 8, "batchs", 0));
        sources.add(new IfmPlatformTemplateDetailDTO(18, 200, "batch", 1));
        sources.add(new IfmPlatformTemplateDetailDTO(19, 18, "batch_id", 1, "t4", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(20, 19, 9, "foreign_id", 2, "t4", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(21, 19, "batchCode", 9, "t4", "batchCode", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(22, 19, "productDate", 9, "t4", "productDate_text", 1, 0, "0", "2", 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(23, 19, "productDate", 9, "t4", "productDate_text2", 1, 0, "2", "4", 3, 0));
////
//////        //并集无外键
        sources.add(new IfmPlatformTemplateDetailDTO(25, 2, "id", 1, "t5", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(26, 25, "confirmType", 9, "t5", "confirmType", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(27, 25, "status", 9, "t5", "status", 0, 3, 0));
//////
//        //并集有外键
        sources.add(new IfmPlatformTemplateDetailDTO(29, 18, "id", 1, "t7", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(30, 29, 19, "foreign_id", 2, "t7", "order_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(31, 29, "inventoryType", 9, "t7", "confirmType", 0, 3, 0));

        PlatformTemplateNode templateNode = getTemplateNode(sources);
//        System.out.println(JSON.toJSONString(templateNode, SerializerFeature.WriteMapNullValue));

        cc2(templateNode, str);
    }

    public void cc2(PlatformTemplateNode nt, String str) throws DocumentException {
        List<FullTable> all = new ArrayList<>();
        //ifm的params下的子节点
        List<FullTable> fullTableList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray();
        Object read = JSON.parse(str);
        if (read instanceof JSONObject) {
            jsonArray.add(read);
        } else {
            jsonArray.addAll((Collection<?>) read);
        }
        for (Object e : jsonArray) {
            JSONObject jsonObject = (JSONObject) e;
            //ifm的params下的子节点
            this.convert(nt.getChildren(), all, fullTableList, jsonObject);
        }
        System.out.println(JSON.toJSONString(fullTableList, SerializerFeature.WriteMapNullValue));
    }

    private void convert(List<PlatformTemplateNode> sources, List<FullTable> all, List<FullTable> fullTableList, JSONObject node) {
        for (int i = 0; i < sources.size(); i++) {
            PlatformTemplateNode item = sources.get(i);
            Object o = node.get(item.getNodeName());
            if (o == null) {
                if (item.getDataType() == null || item.getDataType() == 9) {
                    if (item.getParentNode().getParentNode().getNodeType() != 2) {
                        //如果当前的节点的父父节点的节点类型不是数组，
                        throw new RuntimeException(String.format("获取节点对象，在节点%s%s查询不到节点%s，节点类型%s", item.getParentNode().getFullNodeName(),
                                node.keySet(), item.getFullNodeName(), item.getNodeTypeStr()));
                    }
                }
            }
//            validateValue(item, o);
            if (item.getNodeType() == 3) {
                FullTable ft = null;
                String v = null;
                Table table = new Table();
                BeanUtils.copyProperties(item, table);
                //主键
                if (item.getDataType() == 1) {
                    v = UtilFuns.getRandomOfScope(1, 1000) + "";
                    //主键获取当前节点
                    o = Arrays.asList(node);
                    //如果是foreignId==null，说明该节点无任何表关联，属于顶级节点表
                    ft = new FullTable();
                    if (item.getForeignId() == null) {
                        fullTableList.add(ft);
                    } else {
                        //永远取最新的一条
                        FullTable foreign = getForeign(all, item);
//                        ft.setParent(foreign);
                        foreign.getChildren().add(ft);
                    }
                    all.add(ft);
                    ft.setVId(item.getInnerId());
                    item.setValue(v);
                    table.setValue(v);
                    ft.setTableName(item.getTargetTable());
                    ft.getList().add(table);
                } else if (item.getDataType() == 2) {
                    //子集外键
                    //永远取最新的一条
                    FullTable foreign = getForeign(all, item);
                    table.setValue(foreign.getPrimary().getValue());
                    //拿到当前表
                    ft = getFullTable(all, item);
                    ft.getList().add(table);
                    continue;
                } else if (item.getDataType() == 3) {
                    //子集外键
                    //永远取最新的一条
                    FullTable foreign = getForeign(all, item);
                    table.setValue(foreign.getPrimary().getValue());
                    //拿到当前表
                    ft = getFullTable(all, item);
                    ft.getList().add(table);
                    continue;
                } else if (item.getDataType() == 9) {
                    if (item.getParentNode().getParentNode().getNodeType() == 2) {
                        v = node.values().toString();
                    } else {
                        v = o.toString();
                    }
                    //拿到当前表
                    ft = getFullTable(all, item);
                    //普通节点
                    if (item.getMatchType() == 1) {
                        v = getSplit(v, item.getSelectType(), item.getSelectStart(), item.getSelectEnd());
                    }
                    table.setValue(v);
                    ft.getList().add(table);
                    continue;
                }
            }
            JSONArray list = new JSONArray();
            if (o instanceof JSONObject) {
                list.add(o);
            } else {
                list.addAll((Collection<? extends Object>) o);
            }
            for (int j = 0; j < list.size(); j++) {
                Object o1 = list.get(j);
                JSONObject el = new JSONObject();
                if (item.getNodeType() == 2) {
                    el.put(item.getNodeName(), o1);
                } else {
                    el = (JSONObject) list.get(j);
                }
                convert(item.getChildren(), all, fullTableList, el);
            }
        }
    }

    private FullTable getFullTable(List<FullTable> all, PlatformTemplateNode item) {
        for (int j = all.size() - 1; j >= 0; j--) {
            FullTable e = all.get(j);
            if (Objects.equals(e.getVId(), item.getParentId())) {
                return e;
            }
        }
        return null;
    }

    private FullTable getForeign(List<FullTable> all, PlatformTemplateNode item) {
        FullTable foreign = null;
        for (int j = all.size() - 1; j >= 0; j--) {
            foreign = all.get(j);
            if (Objects.equals(foreign.getVId(), item.getForeignId())) {
                break;
            }
        }
        return foreign;
    }


    private void validateValue(PlatformTemplateNode nt, Object o) {
        List<Element> list = new ArrayList<>();
        if (o instanceof Collection) {
            list = (List<Element>) o;
        } else {
            list.add((Element) o);
        }
        if (nt.getNodeType() == 2) {
            if (CollectionUtils.isEmpty(list)) {
                throw new RuntimeException(String.format("设置节点值，在%s查询不到节点%s，节点类型是%s" +
                                "但是源数据该节点的类型非%s", nt.getFullNodeName(), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
            for (Element element : list) {
                if (!element.isTextOnly()) {
                    Object[] objects = element.elements().stream().map(e -> ((Element) e).getName()).toArray();
                    throw new RuntimeException(String.format("设置节点值，在%s%s查询不到节点%s，节点类型是%s" +
                                    "，但是源数据该节点的类型非%s", nt.getFullNodeName(), Arrays.toString(objects), nt.getFullNodeName(),
                            nt.getNodeTypeStr(), nt.getNodeTypeStr()));
                }
            }
        } else if (nt.getNodeType() == 3) {
            for (Element element : list) {
                if (!element.isTextOnly()) {
                    throw new RuntimeException(String.format("设置节点值，在%s查询不到节点%s，节点类型是%s" +
                                    "但是源数据该节点的类型非%s", nt.getFullNodeName(), element.elements(), nt.getFullNodeName(),
                            nt.getNodeTypeStr(), nt.getNodeTypeStr()));
                }
            }
        } else {
            throw new RuntimeException(String.format("设置节点%s值，节点类型是%s，不能设置值", nt.getFullNodeName()
                    , nt.getNodeTypeStr()));
        }
    }


    private void validateDateType(PlatformTemplateNode nt, Element el, List list) {
        // 对象
        Object[] objects = el.elements().stream().map(e -> ((Element) e).getName()).toArray();
        if (nt.getNodeType() == 0) {
            if (el.isTextOnly()) {
                throw new RuntimeException(String.format("获取节点对象，在%s%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", el.getPath(), Arrays.toString(objects), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
            int count = 0;
            List<String> names = new ArrayList<>();
            for (Object e : list) {
                Element element = (Element) e;
                names.add(element.getName());
                if (nt.getNodeName().equals(element.getName())) {
                    count++;
                }
            }
            if (count > 1) {
                throw new RuntimeException(String.format("获取节点对象，在%s%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", nt.getParentNode().getFullNodeName(), names, nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
        } else if (nt.getNodeType() == 1) {
            //对象数组
            if (el.isTextOnly()) {
                throw new RuntimeException(String.format("获取节点对象，在%s%s查询到节点%s，节点类型是%s，" +
                                "但是源数据该节点的类型非%s", el.getPath(), Arrays.toString(objects), nt.getFullNodeName(),
                        nt.getNodeTypeStr(), nt.getNodeTypeStr()));
            }
        } else {
            throw new RuntimeException(String.format("获取节点对象，在%s%s查询到节点%s，节点类型是%s，不能作为对象类型的判断",
                    el.getPath(), el.elements(), nt.getFullNodeName(), nt.getNodeTypeStr(), nt.getNodeTypeStr()));
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
    public List<PlatformTemplateNode> getNT(List<PlatformTemplateNode> sources, Integer parentId, String nodeName, String targetName) {
        List<PlatformTemplateNode> result = new ArrayList<>();
        List<PlatformTemplateNode> target = sources.stream().filter
                (item -> Objects.equals(parentId, item.getParentId())).collect(Collectors.toList());
        for (PlatformTemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (parentId != IfmApiParamsEnums.root_node.getParentId()) {
                PlatformTemplateNode platformTemplateNode = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(platformTemplateNode);
            }
            if (b) {
                String makeup = nodeName;
                if (item.getDataType() != null) {
                    if (item.getDataType() == 1) {
                        nodeName += "[primary:" + item.getNodeName() + "].";
                    } else if (item.getDataType() == 2 || item.getDataType() == 3) {
                        nodeName += "[foreign:" + item.getNodeName() + "].";
                    } else {
                        nodeName += item.getNodeName() + ".";
                    }
                } else {
                    nodeName += item.getNodeName() + ".";
                }

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

    private void validateRepeat(List<PlatformTemplateNode> sources2) {
        List<PlatformTemplateNode> splitList = new ArrayList<>();
        List<PlatformTemplateNode> others = new ArrayList<>();
        for (PlatformTemplateNode item : sources2) {
            if (item.getParentNode() != null) {
                PlatformTemplateNode parent = new PlatformTemplateNode();
                BeanUtils.copyProperties(item.getParentNode(), parent);
                //设置为nul避免循环依赖
                parent.setChildren(null);
                item.setParentNode(parent);
            }
            //节点为最终节点
            if (item.getNodeType() == 3) {
                if (StringUtils.isEmpty(item.getTargetName())) {
                    throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，父节点%s的类型是%s ,目标字段不为能空。"
                            , item.getFullNodeName(), item.getNodeTypeStr(), item.getDataTypeStr(),
                            item.getParentNode().getFullNodeName(), item.getParentNode().getNodeTypeStr()));
                }
                if (item.getFieldType() == null) {
                    throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，字段类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getMatchType() == null) {
                    throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，的参数匹配类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getMatchType() == 1 && item.getSelectType() == null) {
                    throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，的参数匹配类型规则不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                //主键
                if (item.getDataType() == 1) {
                    if (CollectionUtils.isEmpty(item.getChildren())) {
                        throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，子集合不能为空",
                                item.getFullNodeName(), item.getNodeTypeStr(), item.getDataTypeStr()));
                    }
                    Integer foreign = null;
                    //判断子节点是否有外键
                    for (PlatformTemplateNode e : item.getChildren()) {
                        if (e.getDataType() == 2 || e.getDataType() == 3) {
                            foreign = e.getForeignId();
                            break;
                        }
                    }
                    //有外键给自己和子集都赋值
                    if (foreign != null) {
                        for (PlatformTemplateNode e : item.getChildren()) {
                            e.setForeignId(foreign);
                        }
                        item.setForeignId(foreign);
                    }
                } else {
                    if (!CollectionUtils.isEmpty(item.getChildren())) {
                        throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，不能包含子集合",
                                item.getFullNodeName(), item.getNodeTypeStr(), item.getDataTypeStr()));
                    }
                }
            } else {
                if (CollectionUtils.isEmpty(item.getChildren())) {
                    throw new RuntimeException(String.format("节点%s的类型是%s，数据类型是%s，子集合不能为空",
                            item.getFullNodeName(), item.getNodeTypeStr(), item.getDataTypeStr()));
                }
            }
            //拆分匹配不要
            if (item.getMatchType() != null && item.getMatchType() == 1) {
                splitList.add(item);
                continue;
            }
            others.add(item);
        }
      /*  Set<String> nodeName = new HashSet<>();
        Set<String> targetName = new HashSet<>();
        for (PlatformTemplateNode item : others) {
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
        Map<String, List<PlatformTemplateNode>> collect = splitList.stream().collect(Collectors.groupingBy(item -> item.getFullNodeName()));
        for (Map.Entry<String, List<PlatformTemplateNode>> item : collect.entrySet()) {
            if (!nodeName.add(item.getKey())) {
                throw new RuntimeException(String.format("节点%s重复。", item.getKey()));
            }
        }
        for (PlatformTemplateNode item : splitList) {
            //目标字段只有节点类型=3时才判断重复
            if (item.getNodeType() == 3) {
                if (!targetName.add(item.getFullTargetName())) {
                    throw new RuntimeException(String.format("目标字段%s重复。", item.getFullTargetName()));
                }
            }
        }*/
    }


    private void validateDependence(PlatformTemplateNode platformTemplateNode) {
        if (platformTemplateNode.getParentNode() != null) {
            PlatformTemplateNode parent = new PlatformTemplateNode();
            BeanUtils.copyProperties(platformTemplateNode.getParentNode(), parent);
            //设置为nul避免循环依赖
            parent.setParentNode(null);
            parent.setChildren(null);
            platformTemplateNode.setParentNode(parent);
        }
        Set<String> nodeSet = new HashSet<>();
        Set<String> tableSet = new HashSet<>();
        Set<String> targetNameSet = new HashSet<>();
        Set<Integer> arrayTypeSet = new HashSet<>();
        ArrayList<PlatformTemplateNode> result = new ArrayList<>();
        if (platformTemplateNode.getNodeType() == 2 || platformTemplateNode.getNodeType() == 3) {
            if (!CollectionUtils.isEmpty(platformTemplateNode.getChildren())) {
                throw new RuntimeException(String.format("节点%s的数据类型是%s，不能包含子集合",
                        platformTemplateNode.getFullNodeName(), platformTemplateNode.getNodeTypeStr()));
            }
        } else {
            if (CollectionUtils.isEmpty(platformTemplateNode.getChildren())) {
                throw new RuntimeException(String.format("节点%s的数据类型是%s，子集合不能为空",
                        platformTemplateNode.getFullNodeName(), platformTemplateNode.getNodeTypeStr()));
            }
        }
        for (PlatformTemplateNode item : platformTemplateNode.getChildren()) {
            //一个children下的对象只能在储存在一张表
            if (item.getDataType() == 9 || item.getDataType() == 2 || item.getDataType() == 3) {
                //并集外键
                if (item.getDataType() == 3) {
                    if (Objects.equals(item.getForeignId(), item.getParentNode().getInnerId())) {
                        throw new RuntimeException(String.format("id：%s,平台字段：%s的外键id不能设置为当前节点的主键id", item.getInnerId(), item.getNodeName()));
                    }
                }
                tableSet.add(item.getTargetTable());
                //跟主键所属表做对比
                tableSet.add(item.getParentNode().getTargetTable());
                arrayTypeSet.add(item.getNodeType());
                arrayTypeSet.add(item.getParentNode().getNodeType());
                if (tableSet.size() > 1) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s所属表不一致", item.getInnerId(), item.getNodeName()));
                }
                if (arrayTypeSet.size() > 1) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s的节点类型不一致", item.getInnerId(), item.getNodeName()));
                }
            }
            validateDependence(item);
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


}
