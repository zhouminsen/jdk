package com.zjw.jdk.xml.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjw.jdk.xml.convert.response.IfmResponseTemplateDetailDTO;
import com.zjw.jdk.xml.convert.response.ResponseTemplateNode;
import com.zjw.jdk.xml.convert.utils.JSONObject;
import com.zjw.jdk.xml.convert.utils.XML;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 加入matchtype
 * Created by Administrator on 2019-10-12.
 */
public class XmlToApi {
    private List<ResponseTemplateNode> getTemplateNodes(List<IfmResponseTemplateDetailDTO> sources) {
        List<ResponseTemplateNode> sources2 = new ArrayList<>();
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
            ResponseTemplateNode responseTemplateNode = new ResponseTemplateNode();
            BeanUtils.copyProperties(item, responseTemplateNode);
            sources2.add(responseTemplateNode);
        }
        return sources2;
    }


    String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<request>\n" +
            "    <entryOrder>\n" +
            "        <totalOrderLines>大撒大撒大撒、</totalOrderLines>\n" +
            "        <entryOrderCode>入库单编码, string (50) , 必填</entryOrderCode>\n" +
            "        <ownerCode>货主编码, string (50)</ownerCode>\n" +
            "        <warehouseCode>仓库编码, string (50)，必填</warehouseCode>\n" +
            "        <entryOrderId>仓储系统入库单ID, string (50) , 条件必填</entryOrderId>\n" +
            "        <entryOrderType>大苏打撒旦</entryOrderType>\n" +
            "        <outBizCode>x'z'X'zX</outBizCode>\n" +
            "        <confirmType>大撒大撒大撒</confirmType>\n" +
            "        <status>的撒旦撒旦</status>\n" +
            "        <freight>快递费用 (元) , double (18, 2)</freight>\n" +
            "        <operateTime>操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)</operateTime>\n" +
            "        <remark>备注, string (500)</remark>\n" +
            "    </entryOrder>\n" +
            "    <entryOrder2>\n" +
            "        <totalOrderLines2>dsadsads</totalOrderLines2>\n" +
            "        <entryOrderCode2>入库单编码, string (50) , 必填</entryOrderCode2>\n" +
            "        <ownerCode2>货主编码, string (50)</ownerCode2>\n" +
            "        <warehouseCode>仓库编码, string (50)，必填</warehouseCode>\n" +
            "        <entryOrderId>仓储系统入库单ID, string (50) , 条件必填</entryOrderId>\n" +
            "        <entryOrderType>撒大大撒旦</entryOrderType>\n" +
            "        <outBizCode>的撒旦撒旦</outBizCode>\n" +
            "        <confirmType>支持出入库单多次收货, int，</confirmType>\n" +
            "        <status>入库单状态, string (50) , 必填 (NEW-未开始处理, ACCEPT-仓库接单</status>\n" +
            "        <freight>快递费用 (元) , double (18, 2)</freight>\n" +
            "        <operateTime>操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)</operateTime>\n" +
            "        <remark>备注, string (500)</remark>\n" +
            "        <haha>\n" +
            "            <haha1>111</haha1>\n" +
            "            <haha2>111</haha2>\n" +
            "            <haha3>111</haha3>\n" +
            "        </haha>\n" +
            "    </entryOrder2>\n" +
            "    <orderLines>\n" +
            "        <outBizCode>外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用</outBizCode>\n" +
            "        <orderLineNo>单据行号，string（50）</orderLineNo>\n" +
            "        <ownerCode>货主编码, string (50)</ownerCode>\n" +
            "        <itemCode>商品编码, string (50) , 必填</itemCode>\n" +
            "        <itemId>仓储系统商品ID, string (50) , 条件必填</itemId>\n" +
            "        <snList>\n" +
            "            <sn>商品序列号, string(50)</sn>\n" +
            "            <sn>商品序列号, string(50)</sn>\n" +
            "        </snList>\n" +
            "        <itemName>商品名称, string (200)</itemName>\n" +
            "        <inventoryType>dasdsa</inventoryType>\n" +
            "        <planQty>应收数量, int</planQty>\n" +
            "        <actualQty>实收数量, int，必填</actualQty>\n" +
            "        <batchCode>批次编码, string (50)</batchCode>\n" +
            "        <productDate>商品生产日期，string（10）， YYYY-MM-DD</productDate>\n" +
            "        <expireDate>商品过期日期，string（10），YYYY-MM-DD</expireDate>\n" +
            "        <produceCode>生产批号, string (50)</produceCode>\n" +
            "        <cc>\n" +
            "            <zjw>\n" +
            "                <zjw2>周家伟</zjw2>\n" +
            "            </zjw>\n" +
            "            <zjws>\n" +
            "                <zjw4>周家伟</zjw4>\n" +
            "                <zjw5>dsadas</zjw5>\n" +
            "                <zjw6>\n" +
            "                    <zjw6_6>1</zjw6_6>\n" +
            "                    <zjw6_6>2</zjw6_6>\n" +
            "                    <zjw6_6>3</zjw6_6>\n" +
            "                    <zjw6_6>4</zjw6_6>\n" +
            "                    <zjw6_6>5</zjw6_6>\n" +
            "                </zjw6>\n" +
            "                <zjw7>\n" +
            "                    <zjw8>周家伟1</zjw8>\n" +
            "                    <zjw9>周家伟2</zjw9>\n" +
            "                    <zjw10>\n" +
            "                        <zjw11>周家伟3</zjw11>\n" +
            "                        <zjw12>周家伟4</zjw12>\n" +
            "                    </zjw10>\n" +
            "                </zjw7>\n" +
            "            </zjws>\n" +
            "            <zjws>\n" +
            "                <zjw4>周家伟</zjw4>\n" +
            "                <zjw5>dsadas</zjw5>\n" +
            "                <zjw6>\n" +
            "                    <zjw6_7>1</zjw6_7>\n" +
            "                    <zjw6_6>2</zjw6_6>\n" +
            "                    <zjw6_6>3</zjw6_6>\n" +
            "                    <zjw6_6>4</zjw6_6>\n" +
            "                    <zjw6_6>5</zjw6_6>\n" +
            "                </zjw6>\n" +
            "                <zjw7>\n" +
            "                    <zjw8>周家伟1</zjw8>\n" +
            "                    <zjw9>周家伟2</zjw9>\n" +
            "                    <zjw10>\n" +
            "                        <zjw11>周家伟3</zjw11>\n" +
            "                        <zjw12>周家伟4</zjw12>\n" +
            "                    </zjw10>\n" +
            "                </zjw7>\n" +
            "            </zjws>\n" +
            "        </cc>\n" +
            "        <batchs><!-- 同一行号下多批次支持-->\n" +
            "            <batch>\n" +
            "                <batchCode>批次编号，string(50)</batchCode>\n" +
            "                <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>\n" +
            "                <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>\n" +
            "                <produceCode>生产批号，string(50)，</produceCode>\n" +
            "                <inventoryType>撒大苏打撒旦</inventoryType>\n" +
            "                <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>\n" +
            "            </batch>\n" +
            "            <batch>\n" +
            "                <batchCode>批次编号，string(50)</batchCode>\n" +
            "                <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>\n" +
            "                <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>\n" +
            "                <produceCode>生产批号，string(50)，</produceCode>\n" +
            "                <inventoryType>的撒大大撒旦撒旦</inventoryType>\n" +
            "                <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>\n" +
            "            </batch>\n" +
            "        </batchs>\n" +
            "        <remark>备注, string (500)</remark>\n" +
            "    </orderLines>\n" +
            "    <orderLines>\n" +
            "        <outBizCode>外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用</outBizCode>\n" +
            "        <orderLineNo>单据行号，string（50）</orderLineNo>\n" +
            "        <ownerCode>货主编码, string (50)</ownerCode>\n" +
            "        <itemCode>商品编码, string (50) , 必填</itemCode>\n" +
            "        <itemId>仓储系统商品ID, string (50) , 条件必填</itemId>\n" +
            "        <snList>\n" +
            "            <sn>商品序列号, string(50)</sn>\n" +
            "            <sn>商品序列号, string(50)</sn>\n" +
            "        </snList>\n" +
            "        <itemName>商品名称, string (200)</itemName>\n" +
            "        <inventoryType>dasdsa</inventoryType>\n" +
            "        <planQty>应收数量, int</planQty>\n" +
            "        <actualQty>实收数量, int，必填</actualQty>\n" +
            "        <batchCode>批次编码, string (50)</batchCode>\n" +
            "        <productDate>商品生产日期，string（10）， YYYY-MM-DD</productDate>\n" +
            "        <expireDate>商品过期日期，string（10），YYYY-MM-DD</expireDate>\n" +
            "        <produceCode>生产批号, string (50)</produceCode>\n" +
            "        <cc>\n" +
            "            <zjw>\n" +
            "                <zjw2>周家伟</zjw2>\n" +
            "            </zjw>\n" +
            "            <zjws>\n" +
            "                <zjw4>周家伟</zjw4>\n" +
            "                <zjw5>dsadas</zjw5>\n" +
            "                <zjw6>\n" +
            "                    <zjw6_6>1</zjw6_6>\n" +
            "                    <zjw6_6>2</zjw6_6>\n" +
            "                    <zjw6_6>3</zjw6_6>\n" +
            "                    <zjw6_6>4</zjw6_6>\n" +
            "                    <zjw6_6>5</zjw6_6>\n" +
            "                </zjw6>\n" +
            "                <zjw7>\n" +
            "                    <zjw8>周家伟1</zjw8>\n" +
            "                    <zjw9>周家伟2</zjw9>\n" +
            "                    <zjw10>\n" +
            "                        <zjw11>周家伟3</zjw11>\n" +
            "                        <zjw12>周家伟4</zjw12>\n" +
            "                    </zjw10>\n" +
            "                </zjw7>\n" +
            "            </zjws>\n" +
            "            <zjws>\n" +
            "                <zjw4>周家伟</zjw4>\n" +
            "                <zjw5>dsadas</zjw5>\n" +
            "                <zjw6>\n" +
            "                    <zjw6_7>1</zjw6_7>\n" +
            "                    <zjw6_6>2</zjw6_6>\n" +
            "                    <zjw6_6>3</zjw6_6>\n" +
            "                    <zjw6_6>4</zjw6_6>\n" +
            "                    <zjw6_6>5</zjw6_6>\n" +
            "                </zjw6>\n" +
            "                <zjw7>\n" +
            "                    <zjw8>周家伟1</zjw8>\n" +
            "                    <zjw9>周家伟2</zjw9>\n" +
            "                    <zjw10>\n" +
            "                        <zjw11>周家伟3</zjw11>\n" +
            "                        <zjw12>周家伟4</zjw12>\n" +
            "                    </zjw10>\n" +
            "                </zjw7>\n" +
            "            </zjws>\n" +
            "        </cc>\n" +
            "        <batchs><!-- 同一行号下多批次支持-->\n" +
            "            <batch>\n" +
            "                <batchCode>批次编号，string(50)</batchCode>\n" +
            "                <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>\n" +
            "                <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>\n" +
            "                <produceCode>生产批号，string(50)，</produceCode>\n" +
            "                <inventoryType>撒大苏打撒旦</inventoryType>\n" +
            "                <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>\n" +
            "            </batch>\n" +
            "            <batch>\n" +
            "                <batchCode>批次编号，string(50)</batchCode>\n" +
            "                <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>\n" +
            "                <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>\n" +
            "                <produceCode>生产批号，string(50)，</produceCode>\n" +
            "                <inventoryType>的撒大大撒旦撒旦</inventoryType>\n" +
            "                <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>\n" +
            "            </batch>\n" +
            "        </batchs>\n" +
            "        <remark>备注, string (500)</remark>\n" +
            "    </orderLines>\n" +
            "</request>";

    @Test
    public void xml() throws DocumentException {
        List<IfmResponseTemplateDetailDTO> headerList = new ArrayList<>();
        headerList.add(new IfmResponseTemplateDetailDTO("token", "token", 3, 0));
        headerList.add(new IfmResponseTemplateDetailDTO("sessionId", "sessionId", 3, 0));

        List<IfmResponseTemplateDetailDTO> bodyList = new ArrayList<>();
        bodyList.add(new IfmResponseTemplateDetailDTO(-1, -2, "params", "params", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(1, -1, "request", "request", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(2, 1, "entryOrder", "entryOrder", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(3, 2, "totalOrderLines", "totalOrderLines", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(4, 2, "entryOrderCode", "entryOrderCode", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(5, 2, "ownerCode", "ownerCode", 0, 3, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(999, 1, "haha1", "haha1", 0, 3, 0));
////
        bodyList.add(new IfmResponseTemplateDetailDTO(300, 1, "entryOrder2", "entryOrder2", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(301, 300, "totalOrderLines2", "totalOrderLines2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(302, 300, "entryOrderCode2", "entryOrderCode2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(303, 300, "ownerCode2", "ownerCode2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(304, 300, "haha", "", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(305, 304, "haha1", "haha1", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(306, 304, "haha2", "haha2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(307, 304, "haha3", "haha3", 0, 3, 0));
////
//
        bodyList.add(new IfmResponseTemplateDetailDTO(7, 1, "orderLines", "orderLines", 1));
        bodyList.add(new IfmResponseTemplateDetailDTO(8, 7, "outBizCode", "outBizCode2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(399, 7, "remark", "remark2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(400, 7, "itemId", "itemId2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(9, 7, "snList", "", 0, 0, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(10, 9, "sn", "sn", 0, 2, 0));

        bodyList.add(new IfmResponseTemplateDetailDTO(13, 7, "batchs", "", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(14, 13, "batch", "batch", 1));
        bodyList.add(new IfmResponseTemplateDetailDTO(16, 14, "batchCode", "batchCode", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(16, 14, "produceCode", "produceCode", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(101, 14, "productDate", "productDate_text2", 1, 0, "0", "2", 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(102, 14, "productDate", "productDate_text3", 1, 0, "2", "4", 3, 0));
////
        bodyList.add(new IfmResponseTemplateDetailDTO(200, 7, "cc", "cc", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(201, 200, "zjw", "zjw", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(202, 201, "zjw2", "zjw2", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(203, 200, "zjws", "zjws", 1));
        bodyList.add(new IfmResponseTemplateDetailDTO(204, 203, "zjw4", "zjw4", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(205, 203, "zjw5", "zjw5", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(206, 203, "zjw6", "zjw6", 0, 0, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(250, 206, "zjw6_6", "zjw6_6", 0, 2, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(207, 203, "zjw7", "zjw7", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(209, 207, "zjw8", "zjw8", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(210, 207, "zjw9", "zjw9", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(211, 207, "zjw10", "zjw10", 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(212, 211, "zjw11", "zjw11", 0, 3, 0));
        bodyList.add(new IfmResponseTemplateDetailDTO(213, 211, "zjw12", "zjw12", 0, 3, 0));

        List<ResponseTemplateNode> sources2 = getTemplateNodes(bodyList);
        List<ResponseTemplateNode> responseTemplateNodes = getNT(sources2, IfmApiParamsEnums.root_node.getParentId(), "", "");
        validateRepeat(sources2);
        ResponseTemplateNode responseTemplateNode = responseTemplateNodes.get(0);
        System.out.println(JSON.toJSONString(responseTemplateNodes, SerializerFeature.WriteMapNullValue));

        cc(responseTemplateNode, str);
    }

    private void validateRepeat(List<ResponseTemplateNode> sources2) {
        List<ResponseTemplateNode> splitList = new ArrayList<>();
        List<ResponseTemplateNode> others = new ArrayList<>();
        for (ResponseTemplateNode item : sources2) {
            if (item.getParentNode() != null) {
                ResponseTemplateNode parent = new ResponseTemplateNode();
                BeanUtils.copyProperties(item.getParentNode(), parent);
                //设置为nul避免循环依赖
                parent.setParentNode(null);
                parent.setChildren(null);
                item.setParentNode(parent);
            }
            if (item.getNodeType() == 3) {
                if (StringUtils.isEmpty(item.getTargetName())) {
                    throw new RuntimeException(String.format("%s的目标字段不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getFieldType() == null) {
                    throw new RuntimeException(String.format("%s的字段类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getMatchType() == null) {
                    throw new RuntimeException(String.format("%s的参数匹配类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getMatchType() == 1 && item.getSelectType() == null) {
                    throw new RuntimeException(String.format("%s的参数匹配类型规则不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (!CollectionUtils.isEmpty(item.getChildren())) {
                    throw new RuntimeException(String.format("节点%s的数据类型是%s，不能包含子集合",
                            item.getFullNodeName(), item.getNodeTypeStr()));
                }
            } else if (item.getNodeType() == 2) {
                if (StringUtils.isEmpty(item.getTargetName())) {
                    throw new RuntimeException(String.format("%s的目标字段不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getFieldType() == null) {
                    throw new RuntimeException(String.format("%s的字段类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getMatchType() == null) {
                    throw new RuntimeException(String.format("%s的参数匹配类型不能为空", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (item.getMatchType() == 1) {
                    throw new RuntimeException(String.format("%s的参数只能设置为完全匹配", item.getNodeTypeStr(), item.getFullNodeName()));
                }
                if (!CollectionUtils.isEmpty(item.getChildren())) {
                    throw new RuntimeException(String.format("节点%s的数据类型是%s，不能包含子集合",
                            item.getFullNodeName(), item.getNodeTypeStr()));
                }
            } else {
                if (CollectionUtils.isEmpty(item.getChildren())) {
                    throw new RuntimeException(String.format("节点%s的数据类型是%s，子集合不能为空",
                            item.getFullNodeName(), item.getNodeTypeStr()));
                }
            }

            if (StringUtils.isEmpty(item.getTargetName())) {
                if (item.getNodeType() == 1 || item.getNodeType() == 2 || item.getNodeType() == 3) {
                    throw new RuntimeException(String.format("当前节点%s的类型是%s，父节点%s的类型是%s ,目标字段不为能空。"
                            , item.getFullNodeName(), item.getNodeTypeStr(), item.getParentNode().getFullNodeName(),
                            item.getParentNode().getNodeTypeStr()));
                }
            }


            //拆分匹配不要
            if (item.getMatchType() != null && item.getMatchType() == 1) {
                splitList.add(item);
                continue;
            }
            others.add(item);
        }
        Set<String> nodeName = new HashSet<>();
        Set<String> targetName = new HashSet<>();
        for (ResponseTemplateNode item : others) {
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
        Map<String, List<ResponseTemplateNode>> collect = splitList.stream().collect(Collectors.groupingBy(item -> item.getFullNodeName()));
        for (Map.Entry<String, List<ResponseTemplateNode>> item : collect.entrySet()) {
            if (!nodeName.add(item.getKey())) {
                throw new RuntimeException(String.format("节点%s重复。", item.getKey()));
            }
        }
        for (ResponseTemplateNode item : splitList) {
            //目标字段只有节点类型=3时才判断重复
            if (item.getNodeType() == 3) {
                if (!targetName.add(item.getFullTargetName())) {
                    throw new RuntimeException(String.format("目标字段%s重复。", item.getFullTargetName()));
                }
            }
        }
    }

    public void cc(ResponseTemplateNode nt, String str) throws DocumentException {
        org.dom4j.Document read = DocumentHelper.parseText(str);
        Element rootElement = read.getRootElement();
        Map<String, Object> map = new LinkedHashMap<>();
        //ifm的params下的子节点
        this.convert(nt.getChildren(), map, rootElement);
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
        System.out.println(XML.toString(new JSONObject(map)));
    }


    private void convert(List<ResponseTemplateNode> sources, Map<String, Object> map, Element node) {
        for (int i = 0; i < sources.size(); i++) {
            ResponseTemplateNode item = sources.get(i);
            List o = null;
            if (item.getParentId() == IfmApiParamsEnums.root_node.getInnerId()) {
                o = node.selectNodes("/" + item.getNodeName());
            } else {
                o = node.selectNodes(item.getNodeName());
            }
            if (CollectionUtils.isEmpty(o)) {
                Object[] objects = node.elements().stream().map(e -> ((Element) e).getName()).toArray();
                throw new RuntimeException(String.format("获取节点对象，在节点%s%s查询不到节点%s，节点类型%s",
                        node.getPath(), Arrays.toString(objects), item.getFullNodeName(), item.getNodeTypeStr()));
            }
            if (CollectionUtils.isEmpty(item.getChildren())) {
                if (StringUtils.isEmpty(item.getTargetName())) {
                    continue;
                }
                validateValue(item, o);
                Element e = (Element) o.get(0);
                String v = null;
                if (item.getParentNode().getNodeType() == 1) {
                    LinkedList<Map<String, Object>> list = (LinkedList<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                    Map<String, Object> child = list.getLast();
                    v = e.getText();
                    if (item.getMatchType() == 1) {
                        v = getSplit(v, item.getSelectType(), item.getSelectStart(), item.getSelectEnd());
                    }
                    child.put(item.getTargetName(), v);
                } else {
                    //数组直接取后存入当前目标字段中
                    if (item.getNodeType() == 2) {
                        List<Element> list = o;
                        List<String> collect = list.stream().map(sub -> sub.getText()).collect(Collectors.toList());
                        map.put(item.getTargetName(), collect);
                    } else {
                        v = e.getText();
                        if (item.getMatchType() == 1) {
                            v = getSplit(v, item.getSelectType(), item.getSelectStart(), item.getSelectEnd());
                        }
                        map.put(item.getTargetName(), v);
                    }
                }
                continue;
            }

            List list = new ArrayList(o);
            for (int j = 0; j < list.size(); j++) {
                Object e = list.get(j);
                Element el = (Element) e;
                validateDateType(item, el, o);
                if (StringUtils.isEmpty(item.getTargetName())) {
                    if (item.getParentNode().getNodeType() == 1) {
                        LinkedList<Map<String, Object>> o1 = (LinkedList<Map<String, Object>>) map.get(item.getParentNode().getTargetName());
                        Map<String, Object> last = o1.getLast();
                        this.convert(item.getChildren(), last, el);
                    } else {
                        this.convert(item.getChildren(), map, el);
                    }
                } else {
                    if (item.getParentNode().getNodeName().equals("params")) {
                        if (item.getNodeType() == 0) {
                            Map<String, Object> childMap = new LinkedHashMap<>();
                            map.put(item.getTargetName(), childMap);
                            this.convert(item.getChildren(), childMap, el);
                            System.out.println("aaa");
                        } else if (item.getNodeType() == 1) {
                            LinkedList<Map<String, Object>> subMapList = (LinkedList<Map<String, Object>>) map.get(item.getTargetName());
                            if (CollectionUtils.isEmpty(subMapList)) {
                                subMapList = new LinkedList<>();
                                map.put(item.getTargetName(), subMapList);
                            }
                            subMapList.add(new LinkedHashMap<>());
                            // 对象数组
                            this.convert(item.getChildren(), map, el);
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
                                this.convert(item.getChildren(), first, el);
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
                                this.convert(item.getChildren(), second, el);
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
                                this.convert(item.getChildren(), map, el);
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
                                this.convert(item.getChildren(), sub, el);
                                System.out.println("aaa");
                            }
                        } else if (item.getNodeType() == 2) {
                            //数组
                            List<Object> subArray = new LinkedList<>();
                            map.put(item.getTargetName(), subArray);
                            this.convert(item.getChildren(), map, el);
                            System.out.println("aaa");
                        }
                    }
                }
            }
        }

    }


    private void validateValue(ResponseTemplateNode nt, Object o) {
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

    private void validateDateType(ResponseTemplateNode nt, Element el, List list) {
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
    public List<ResponseTemplateNode> getNT(List<ResponseTemplateNode> sources, Integer parentId, String nodeName, String targetName) {
        List<ResponseTemplateNode> result = new ArrayList<>();
        List<ResponseTemplateNode> target = sources.stream().filter
                (item -> Objects.equals(parentId, item.getParentId())).collect(Collectors.toList());
        for (ResponseTemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (parentId != IfmApiParamsEnums.root_node.getParentId()) {
                ResponseTemplateNode responseTemplateNode = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(responseTemplateNode);
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
