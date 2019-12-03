package com.zjw.jdk.xml.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.zjw.jdk.util.UtilFuns;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 加入matchtype
 * Created by Administrator on 2019-10-12.
 */
public class XmlToData2 {

    private List<TemplateNode> getTemplateNodes(List<IfmPlatformTemplateDetailDTO> sources) {
        List<TemplateNode> sources2 = new ArrayList<>();
        for (int i = 0; i < sources.size(); i++) {
            IfmPlatformTemplateDetailDTO item = sources.get(i);
            if (item.getNodeType() == null) {
                throw new RuntimeException(String.format("id：%s,平台字段：%s未设置节点类型", item.getInnerId(), item.getNodeName()));
            }
            if (item.getNodeType() == 3) {
                if (item.getDataType() == null) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置字段类型", item.getInnerId(), item.getNodeName()));
                }
                if (item.getMatchType() == null) {
                    throw new RuntimeException(String.format("id：%s,平台字段：%s未设置匹配类型", item.getInnerId(), item.getNodeName()));
                }
                //除了普通字段，其他都只能是完全匹配
                if ((item.getDataType() == 1 || item.getDataType() == 2 || item.getDataType() == 3) && item.getMatchType() != 0) {
                    throw new RuntimeException(String.format("id：%s,平台字段%s的数据类型是%s，只能设置为完全匹配",
                            item.getInnerId(), item.getNodeName(), item.getDataTypeStr()));

                }
            }
            if (item.getDataType() == 2 || item.getDataType() == 3) {
                if (item.getForeignId() == null) {
                    throw new RuntimeException(String.format("id：%s,平台字段%s的数据类型是%s，外键id不能为空",
                            item.getInnerId(), item.getNodeName(), item.getDataTypeStr()));
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
            TemplateNode templateNode = new TemplateNode();
            BeanUtils.copyProperties(item, templateNode);
            sources2.add(templateNode);
        }
        return sources2;
    }

    String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<request>" +
            "    <entryOrder>" +
            "        <totalOrderLines>" +
            "            单据总行数，int，当单据需要分多个请求发送时，发送方需要将totalOrderLines填入，接收方收到后，根据实际接收到的条数和totalOrderLines进行比对，如果小于，则继续等待接收请求。如果等于，则表示该单据的所有请求发送完成。" +
            "        </totalOrderLines>" +
            "        <entryOrderCode>入库单编码, string (50) , 必填</entryOrderCode>" +
            "        <ownerCode>货主编码, string (50)</ownerCode>" +
            "        <warehouseCode>仓库编码, string (50)，必填</warehouseCode>" +
            "        <entryOrderId>仓储系统入库单ID, string (50) , 条件必填</entryOrderId>" +
            "        <entryOrderType>入库单类型 ，SCRK=生产入库，LYRK=领用入1库，CCRK=残次品入库，CGRK=采购入库, DBRK=调拨入库, QTRK=其他入库，B2BRK=B2B入库" +
            "        </entryOrderType>" +
            "        <outBizCode>外部业务编码, 消息ID, 用于去重, ISV对于同一请求，分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，请求不会被重复处理, ,必填</outBizCode>" +
            "        <confirmType>支持出入库单多次收货, int，" +
            "            多次收货后确认时" +
            "            0 表示入库单最终状态确认；" +
            "            1 表示入库单中间状态确认；" +
            "            每次入库传入的数量为增量，特殊情况，同一入库单，如果先收到0，后又收到1，允许修改收货的数量。" +
            "        </confirmType>" +
            "        <status>入库单状态, string (50) , 必填 (NEW-未开始处理, ACCEPT-仓库接单 , PARTFULFILLED-部分收货完成, FULFILLED-收货完成, EXCEPTION-异常," +
            "            CANCELED-取消, CLOSED-关闭, REJECT-拒单, CANCELEDFAIL-取消失败) , (只传英文编码)" +
            "        </status>" +
            "        <freight>快递费用 (元) , double (18, 2)</freight>" +
            "        <operateTime>操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime为入库时间)</operateTime>" +
            "        <remark>备注, string (500)</remark>" +
            "    </entryOrder>" +
            "    <orderLines>" +
            "        <orderLine>" +
            "            <outBizCode>外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用11</outBizCode>" +
            "            <orderLineNo>单据行号，string（50）</orderLineNo>" +
            "            <ownerCode>货主编码, string (50)</ownerCode>" +
            "            <itemCode>商品编码, string (50) , 必填</itemCode>" +
            "            <itemId>仓储系统商品ID, string (50) , 条件必填</itemId>" +
            "            <snList>" +
            "                <sn>商品序列号, string(40)</sn>" +
            "                <sn>商品序列号, string(1000)</sn>" +
            "            </snList>" +
            "            <itemName>商品名称, string (200)</itemName>" +
            "            <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)</inventoryType>" +
            "            <planQty>应收数量, int</planQty>" +
            "            <actualQty>实收数量, int，必填</actualQty>" +
            "            <batchCode>批次编码, string (50)</batchCode>" +
            "            <productDate>商品生产日期，string（10）， YYYY-MM-DD</productDate>" +
            "            <expireDate>商品过期日期，string（10），YYYY-MM-DD</expireDate>" +
            "            <produceCode>生产批号, string (50)</produceCode>" +
            "            <batchs><!-- 同一行号下多批次支持-->" +
            "                <batch>" +
            "                    <batchCode>批次编号，string(50)</batchCode>" +
            "                    <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>" +
            "                    <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>" +
            "                    <produceCode>生产批号，string(50)，</produceCode>" +
            "                    <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)" +
            "                    </inventoryType>" +
            "                    <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>" +
            "                </batch>" +
            "                <batch>" +
            "                    <batchCode>批次编号，string(51)</batchCode>" +
            "                    <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>" +
            "                    <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>" +
            "                    <produceCode>生产批号，string(50)，</produceCode>" +
            "                    <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)" +
            "                    </inventoryType>" +
            "                    <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>" +
            "                </batch>" +
            "            </batchs>" +
            "            <remark>备注, string (500)</remark>" +
            "        </orderLine>" +
            "        <orderLine>" +
            "            <outBizCode>外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用22</outBizCode>" +
            "            <orderLineNo>单据行号，string（50）</orderLineNo>" +
            "            <ownerCode>货主编码, string (50)</ownerCode>" +
            "            <itemCode>商品编码, string (50) , 必填</itemCode>" +
            "            <itemId>仓储系统商品ID, string (50) , 条件必填</itemId>" +
            "            <snList>" +
            "                <sn>商品序列号, string(50)</sn>" +
            "                <sn>商品序列号, string(51)</sn>" +
            "            </snList>" +
            "            <itemName>商品名称, string (200)</itemName>" +
            "            <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)</inventoryType>" +
            "            <planQty>应收数量, int</planQty>" +
            "            <actualQty>实收数量, int，必填</actualQty>" +
            "            <batchCode>批次编码, string (50)</batchCode>" +
            "            <productDate>商品生产日期，string（10）， YYYY-MM-DD</productDate>" +
            "            <expireDate>商品过期日期，string（10），YYYY-MM-DD</expireDate>" +
            "            <produceCode>生产批号, string (80)</produceCode>" +
            "            <batchs><!-- 同一行号下多批次支持-->" +
            "                <batch>" +
            "                    <batchCode>批次编号，string(47)</batchCode>" +
            "                    <productDate>生产日期，string(10)，YYYY-MM-DD</productDate>" +
            "                    <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>" +
            "                    <produceCode>生产批号，string(50)，</produceCode>" +
            "                    <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)" +
            "                    </inventoryType>" +
            "                    <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>" +
            "                </batch>" +
            "                <batch>" +
            "                    <batchCode>批次编号，string(48)</batchCode>" +
            "                    <productDate>生产日期，string(20)，YYYY-MM-DD</productDate>" +
            "                    <expireDate>过期日期，string(10)，YYYY-MM-DD</expireDate>" +
            "                    <produceCode>生产批号，string(50)，</produceCode>" +
            "                    <inventoryType>库存类型，string (50) , ZP=正品, CC=残次,JS=机损, XS= 箱损，默认为ZP, (收到商品总数=正品数+残品数+机损数+箱损数)" +
            "                    </inventoryType>" +
            "                    <actualQty>实收数量, int，要求batchs节点下所有的实收数量之和等于orderline中的实收数量</actualQty>" +
            "                </batch>" +
            "            </batchs>" +
            "            <remark>备注, string (600)</remark>" +
            "        </orderLine>" +
            "    </orderLines>" +
            "</request>";

    @Test
    public void xml() throws DocumentException {

        List<IfmPlatformTemplateDetailDTO> sources = new ArrayList<>();
        sources.add(new IfmPlatformTemplateDetailDTO(-1, -2, "params", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(1, -1, "request", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(2, 1, "entryOrder", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(3, 2, "zjw_id", 1, "t", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(4, 3, "totalOrderLines", 9, "t", "totalOrderLines", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(5, 3, "entryOrderCode", 9, "t", "entryOrderCode", 0, 3, 0));

        //子集外键
        sources.add(new IfmPlatformTemplateDetailDTO(100, 2, 3, "zjw2_id", 1, "t_t", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(101, 100, 3, "foreign_id", 2, "t_t", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(102, 100, 3, "outBizCode", 9, "t_t", "outBizCode", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(13, 100, 3, "confirmType", 9, "t_t", "confirmType", 0, 3, 0));


        sources.add(new IfmPlatformTemplateDetailDTO(7, 1, "orderLines", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(8, 7, "orderLine", 0, 0, 1));
        sources.add(new IfmPlatformTemplateDetailDTO(9, 8, "line_id", 1, "t2", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(10, 9, 3, "foreign_id", 3, "t2", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(11, 9, "outBizCode", 9, "t2", "outBizCode", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(12, 9, "remark", 9, "t2", "remark", 0, 3, 0));

//        sources.add(new IfmPlatformTemplateDetailDTO(13, 8, "snList", 0, 0, 2));
//        sources.add(new IfmPlatformTemplateDetailDTO(14, 13, "sn_id", 1, "t3", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(15, 13, 9, "foreign_id", 2, "t3", "foreign_id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(16, 13, "sn", 9, "t3", "sn2", 0, 0, 0));
////
        sources.add(new IfmPlatformTemplateDetailDTO(17, 8, "batchs", 0, 0, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(18, 17, "batch", 0, 0, 1));
        sources.add(new IfmPlatformTemplateDetailDTO(19, 18, "batch_id", 1, "t4", "id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(20, 19, 9, "foreign_id", 2, "t4", "foreign_id", 0, 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(21, 19, "batchCode", 9, "t4", "batchCode", 0, 3, 0));
//            sources.add(new IfmPlatformTemplateDetailDTO9102, 13, "productDate", 9, "t4", "batchCode2", 0));
        sources.add(new IfmPlatformTemplateDetailDTO(22, 19, "productDate", 9, "t4", "productDate_text", 1, 0, "0", "2", 3, 0));
        sources.add(new IfmPlatformTemplateDetailDTO(23, 19, "productDate", 9, "t4", "productDate_text2", 1, 0, "2", "4", 3, 0));
////
//////        //并集无外键
//        sources.add(new IfmPlatformTemplateDetailDTO(25, 1, "id", 1, "t5", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(26, 25, "confirmType", 9, "t5", "confirmType", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(27, 25, "status", 9, "t5", "status", 0, 0, 0));
//////
////        //并集有外键
//        sources.add(new IfmPlatformTemplateDetailDTO(29, 18, "id", 1, "t7", "id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(30, 29, 18, "foreign_id", 2, "t7", "order_id", 0, 0, 0));
//        sources.add(new IfmPlatformTemplateDetailDTO(31, 29, "inventoryType", 9, "t7", "confirmType", 0, 0, 0));

        List<TemplateNode> sources2 = getTemplateNodes(sources);
        List<TemplateNode> templateNodes = getNT(sources2, IfmApiParamsEnums.root_node.getParentId(), "", "");
        validateRepeat(sources2);
        TemplateNode templateNode = templateNodes.get(0);
//        System.out.println(JSON.toJSONString(templateNode, SerializerFeature.WriteMapNullValue));

        cc2(templateNode, str);
    }

    public void cc2(TemplateNode nt, String str) throws DocumentException {
        org.dom4j.Document read = DocumentHelper.parseText(str);
        Element rootElement = read.getRootElement();
        Map<String, Object> map = new LinkedHashMap<>();
        List<Table> all = new ArrayList<>();
        //ifm的params下的子节点
        List<FullTable> fullTableList = new ArrayList<>();
        this.convert(nt.getChildren(), all, fullTableList, null, rootElement);
        System.out.println(JSON.toJSONString(map, SerializerFeature.WriteMapNullValue));
//        System.out.println(XML.toString(new JSONObject(map)));
    }

    private void convert(List<TemplateNode> sources, List<Table> all, List<FullTable> fullTableList, FullTable ft, Element node) {
        for (int i = 0; i < sources.size(); i++) {
            TemplateNode item = sources.get(i);
            List o = null;
            if (item.getParentId() == IfmApiParamsEnums.root_node.getInnerId()) {
                o = node.selectNodes("/" + item.getNodeName());
            } else {
                o = node.selectNodes(item.getNodeName());
            }
            if (item.getDataType() != null && item.getDataType() == 9) {
                if (CollectionUtils.isEmpty(o)) {
                    Object[] objects = node.elements().stream().map(e -> ((Element) e).getName()).toArray();
                    throw new RuntimeException(String.format("获取节点对象，在节点%s%s查询不到节点%s，节点类型%s",
                            node.getPath(), Arrays.toString(objects), item.getFullNodeName(), item.getNodeTypeStr()));
                }
            }
//            validateValue(item, o);
            String v = null;
            Table table = new Table();
            BeanUtils.copyProperties(item, table);
            //主键
            if (item.getDataType() == 1) {
                o = Arrays.asList(node);
                if (ft == null) {
                    ft = new FullTable();
                }
                Object id = UtilFuns.getRandomOfScope(1, 1000) + "";
                if (Objects.equals(ft.getVId(), item.getForeignId().toString())) {
                    FullTable subFt = new FullTable();
                    ft.getChildren().add(subFt);
                    ft = subFt;
                    all.add(table);
                    item.setValue(v);
                    table.setValue(v);
                    subFt.setTableName(item.getTargetTable());
                    subFt.getList().add(table);
                } else {
                    fullTableList.add(ft);
                    all.add(table);
                    table.setId(id.toString());
                    item.setValue(id);
                    table.setValue(id);
                    ft.setVId(id.toString());
                    ft.setTableName(item.getTargetTable());
                    ft.setVId(id.toString());
                    ft.getList().add(table);
                }
            } else if (item.getDataType() == 2) {
                //子集外键
                //永远取最新的一条
                Table foreign = null;
                for (int i1 = all.size() - 1; i1 >= 0; i1--) {
                    foreign = all.get(i1);
                    if (foreign.getInnerId() == item.getForeignId()) {
                        break;
                    }
                }
                table.setValue(foreign.getValue());
                ft.getList().add(table);
            } else if (item.getDataType() == 3) {
                Table foreign = null;
                for (int i1 = all.size() - 1; i1 >= 0; i1--) {
                    foreign = all.get(i1);
                    if (foreign.getInnerId() == item.getForeignId()) {
                        break;
                    }
                }
                table.setValue(foreign.getValue());
                ft.getList().add(table);
            } else if (item.getDataType() == 9) {
                Element e = (Element) o.get(0);
                //普通节点
                if (item.getMatchType() == 1) {
                    v = getSplit(e.getText(), item.getSelectType(), item.getSelectStart(), item.getSelectEnd());
                }
                table.setValue(v);
                ft.getList().add(table);
            }
            List list = new ArrayList(o);
            for (int j = 0; j < list.size(); j++) {
                Element el = (Element) list.get(j);
                convert(item.getChildren(), all, fullTableList, ft, el);
            }
        }
    }


    private void validateValue(TemplateNode nt, Object o) {
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


    private void validateDateType(TemplateNode nt, Element el, List list) {
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
    public List<TemplateNode> getNT(List<TemplateNode> sources, Integer parentId, String nodeName, String targetName) {
        List<TemplateNode> result = new ArrayList<>();
        List<TemplateNode> target = sources.stream().filter
                (item -> Objects.equals(parentId, item.getParentId())).collect(Collectors.toList());
        for (TemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (parentId != IfmApiParamsEnums.root_node.getParentId()) {
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

    private void validateRepeat(List<TemplateNode> sources2) {
        List<TemplateNode> splitList = new ArrayList<>();
        List<TemplateNode> others = new ArrayList<>();
        for (TemplateNode item : sources2) {
            if (item.getParentNode() != null) {
                TemplateNode parent = new TemplateNode();
                BeanUtils.copyProperties(item.getParentNode(), parent);
                //设置为nul避免循环依赖
                parent.setParentNode(null);
                parent.setChildren(null);
                item.setParentNode(parent);
            }
            if (item.getNodeType() == 3 && item.getDataType() != 1) {
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
                if (item.getNodeType() == 3) {
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
      /*  Set<String> nodeName = new HashSet<>();
        Set<String> targetName = new HashSet<>();
        for (TemplateNode item : others) {
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
        }*/
    }

    public static List<TemplateNode> getNT(List<TemplateNode> sources, Integer innerId) {
        Set<String> nodeSet = new HashSet<>();
        Set<String> tableSet = new HashSet<>();
        Set<String> targetNameSet = new HashSet<>();
        Set<Integer> arrayTypeSet = new HashSet<>();
        ArrayList<TemplateNode> result = new ArrayList<>();
        List<TemplateNode> target = sources.stream().filter
                (item -> Objects.equals(innerId, item.getParentId())).collect(Collectors.toList());
        for (TemplateNode item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (innerId != IfmApiParamsEnums.root_node.getParentId()) {
                TemplateNode templateNode = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(templateNode);
            }
            if (b) {
                item.setChildren(getNT(sources, item.getInnerId()));
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
            result.add(item);
        }
        //验证拆分匹配，完全匹配是否重复
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

    private void validateDependence(TemplateNode templateNode) {
        if (templateNode.getParentNode() != null) {
            TemplateNode parent = new TemplateNode();
            BeanUtils.copyProperties(templateNode.getParentNode(), parent);
            //设置为nul避免循环依赖
            parent.setParentNode(null);
            parent.setChildren(null);
            templateNode.setParentNode(parent);
        }
        Set<String> nodeSet = new HashSet<>();
        Set<String> tableSet = new HashSet<>();
        Set<String> targetNameSet = new HashSet<>();
        Set<Integer> arrayTypeSet = new HashSet<>();
        ArrayList<TemplateNode> result = new ArrayList<>();
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
            this.foreignId = foreignId;
            this.matchType = matchType;
            this.nodeType = nodeType;
            this.fieldType = fieldType;
            this.targetName = targetName;
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
         * 字段类型(1：主键，2：外键，3：外键（（非同父节点和唯一节点）并集外键），9：普通字段)
         */
        private Integer dataType;
        private String dataTypeStr;
        private Integer foreignId;
        private String foreignField;

        /**
         * 节点类型 0:对象，1：对象数组，2：数组，3:最终节点
         */
        private Integer nodeType;

        /**
         * 节点类型 0:对象，1：对象数组，2：数组，3:最终节点
         */
        private String nodeTypeStr;
        /**
         * 字段类型(0：字符串 1：时间 2：数字)
         */
        private Integer fieldType;
    }

    @Data
    public static class FullTable {
        /**
         * 虚id
         */
        String vId;
        String tableName;
        List<Table> list;
        FullTable parent;
        List<FullTable> children;

        public List<Table> getList() {
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        public List<FullTable> getChildren() {
            if (children == null) {
                children = new ArrayList<>();
            }
            return children;
        }
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

        private String fullTargetName;
        public String fullNodeName;
        public TemplateNode parentNode;
        public Object value;
        public List<TemplateNode> children;
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
