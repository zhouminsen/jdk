package com.zjw.jdk;

import com.alibaba.fastjson.JSON;
import com.zjw.jdk.util.UtilFuns;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2019-10-12.
 */
public class TreeUtils2 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, DocumentException {
        /*List<IfmBisRequestApiParameterDO> sources = new ArrayList<>();
        sources.add(new IfmBisRequestApiParameterDTO("1", 1, -1, "name"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 2, -1, "name2"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 3, -1, "name3"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 4, 1, "name4"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 5, 1, "name5"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 6, 1, "name6"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 7, 4, "name7"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 8, 4, "name8"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 9, 8, "name9"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 10, 2, "name10"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 11, 2, "name11"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 12, 10, "name12"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 13, 10, "name13"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 14, 11, "name14"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 15, 14, "name15"));
        sources.add(new IfmBisRequestApiParameterDTO("1", 16, 3, "name15"));
        List<IfmBisRequestApiParameterDTO> tree = getApiParams(new ArrayList(sources), -1);
        System.out.println(JSON.toJSONString(tree));
        List<String> list = new ArrayList<>();
        System.out.println(join(sources, sources.get(sources.size() - 2), list));
        System.out.println(list);*/

       /* List<Param> sources = new ArrayList<>();
        sources.add(new Param("1", 1, -1, "name", "111"));
        sources.add(new Param("1", 2, -1, "name2", "111"));
        sources.add(new Param("1", 3, -1, "name3", "111"));
        sources.add(new Param("1", 4, 1, "name4", "111"));
        sources.add(new Param("1", 5, 1, "name5", "111"));
        sources.add(new Param("1", 6, 1, "name6", "111"));
        sources.add(new Param("1", 7, 4, "name7", "111"));
        sources.add(new Param("1", 8, 4, "name8", "111"));
        sources.add(new Param("1", 9, 8, "name9", "111"));
        sources.add(new Param("1", 10, 2, "name10", "111"));
        sources.add(new Param("1", 11, 2, "name11", "111"));
        sources.add(new Param("1", 12, 10, "name12", "111"));
        sources.add(new Param("1", 13, 10, "name13", "111"));
        sources.add(new Param("1", 14, 11, "name14", "111"));
        sources.add(new Param("1", 15, 14, "name15", "111"));
        sources.add(new Param("1", 16, 3, "name15", "111"));
        sources.add(new Param("1", 17, 3, "name15", "111"));
        List<HashMap<String, Object>> tree2 = getApi(sources, -1);
        System.out.println(JSON.toJSONString(tree2));*/
//        test();
        test2();

    }

    static String str = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
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
            "            <produceCode>生产批号, string (50)</produceCode>" +
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
            "    </orderLines>" +
            "</request>";


    private static void test2() throws DocumentException, InvocationTargetException, IllegalAccessException {
        List<Dic> sources = new ArrayList<>();
        List<Table> tableList = new ArrayList<>();
        sources.add(new Dic(1, -1, "request", 0));
        sources.add(new Dic(2, 1, "entryOrder", 1, "t", "id"));
        sources.add(new Dic(3, 2, "totalOrderLines", 9, "t", "totalOrderLines"));
        sources.add(new Dic(4, 2, "entryOrderCode", 9, "t", "entryOrderCode"));

        sources.add(new Dic(5, 1, "orderLines", 0));
        sources.add(new Dic(6, 5, "orderLine", 1, "t2", "id"));
        sources.add(new Dic(7, 6, 2, "orderLine", 3, "t2", "order_id"));
        sources.add(new Dic(8, 6, "outBizCode", 9, "t2", "outBizCode"));

        sources.add(new Dic(9, 6, "snList", 1, "t3", "id"));
        sources.add(new Dic(10, 9, "snList", 2, "t3", "sub_id", "id"));
        sources.add(new Dic(11, 9, "sn", 9, "t3", "sn"));

        sources.add(new Dic(12, 6, "batchs", 0));
        sources.add(new Dic(13, 12, "batch", 1, "t4", "id"));
        sources.add(new Dic(14, 13, "batch", 2, "t4", "order_id", "id"));
        sources.add(new Dic(15, 13, "batchCode", 9, "t4", "batchCode"));


        List<NT> sources2 = new ArrayList<>();
        for (Dic item : sources) {
            NT nt = new NT();
            BeanUtils.copyProperties(item, nt);
            sources2.add(nt);
        }
        List<NT> nts = getNT(sources2, -1);
        NT nt = nts.get(0);
        org.dom4j.Document read = DocumentHelper.parseText(str);
        Element rootElement = read.getRootElement();
        List<Table> params = new ArrayList<>();
        FullTable fullTable = new FullTable();
        Node node = rootElement.selectSingleNode("/" + nt.getNodeName());
        bb(nt, params, fullTable, node);
        System.out.println(JSON.toJSONString(fullTable));
    }

    private static void bb(NT nt, List<Table> params, FullTable fullTable, Node node) {
        Table table = new Table();
        BeanUtils.copyProperties(nt, table);
        //主键
        if (nt.getFieldType() == 1) {
            params.add(table);
            Object v = UtilFuns.getRandomOfScope(1, 1000) + "";
            nt.setValue(v);
            table.setValue(v);
            //初始化或者并集节点
            if (fullTable.getTableName() == null || fullTable.getTableName().equals(table.getTableName())) {
                fullTable.setTableName(nt.getTableName());
                fullTable.getList().add(table);
            } else {
                //子集节点
                FullTable full = new FullTable();
                full.setTableName(table.getTableName());
                full.getList().add(table);
                full.setParent(fullTable);
                fullTable.getChildren().add(full);
                fullTable = full;
//                bb(nt, params, full, node);
//                return;
            }
        } else if (nt.getFieldType() == 2) {
            //子集外键
            NT parentNode = getUp(nt, nt.getTableName(), nt.getForeignField());
            table.setValue(parentNode.getValue());
            fullTable.getList().add(table);
        } else if (nt.getFieldType() == 3) {
            //并集外键
            Table t = params.stream().filter(item -> item.getInnerId() == nt.getForeignId()).findFirst().get();
            table.setValue(t.getValue());
            fullTable.getList().add(table);
        } else if (nt.getFieldType() == 9) {
            //最终节点
            table.setValue(node.getText());
            fullTable.getList().add(table);
        }
        if (CollectionUtils.isEmpty(nt.getChildren())) {
            return;
        }
        for (NT item : nt.getChildren()) {
            List list = null;
            //如果当前节点是外键，需要套上主键的节点
            if (item.getFieldType() == 2 || item.getFieldType() == 3) {
                list = (Arrays.asList(node));
            } else {
                list = node.selectNodes(item.getNodeName());
            }
            for (Object e : list) {
                Element element = (Element) e;
                //如果发现相同节点，
                boolean b = fullTable.getList().stream().anyMatch(sub -> Objects.equals(sub.getFieldName(), element.getName()));
                if (b) {
                    FullTable fullTable2 = new FullTable();
                    fullTable2.setParent(fullTable.getParent());
                    fullTable2.setTableName(fullTable.getTableName());
                    fullTable.getParent().getChildren().add(fullTable2);
                    for (Table sub : fullTable.getList()) {
                        Table t = new Table();
                        BeanUtils.copyProperties(sub, t);
                        if (Objects.equals(sub.getFieldName(), element.getName())) {
                            t.setValue(element.getText());
                        }
                        fullTable2.getList().add(t);
                    }
                    return;
                }
                bb(item, params, fullTable, element);
            }
        }
    }

    public static NT getUp(NT nt, String tableName, String foreignField) {
        if (Objects.equals(nt.getParentNode().getTableName(), tableName)) {
            return getUp(nt.getParentNode(), tableName, foreignField);
        } else if (!Objects.equals(nt.getParentNode().getFieldName(), foreignField)) {
            return getUp(nt.getParentNode(), tableName, foreignField);
        } else {
            return nt.getParentNode();

        }
    }


    public static ArrayList<IfmBisRequestApiParameterDTO> getApiParams(ArrayList<IfmBisRequestApiParameterDTO> sources, Integer innerId) {
        ArrayList<IfmBisRequestApiParameterDTO> result = new ArrayList<>();
        List<IfmBisRequestApiParameterDTO> target = sources.stream().filter
                (item -> Objects.equals(innerId, item.getParentId())).collect(Collectors.toList());
        for (IfmBisRequestApiParameterDTO item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (b) {
                item.setState("open");
                item.setChildren(getApiParams(sources, item.getInnerId()));
            } else {
                item.setState("closed");
                item.setParameterName(item.getParameterName());
            }
            result.add(item);
        }
        return result;
    }

    private static List<HashMap<String, Object>> getApi(List<Param> sources, Integer innerId) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        Map<Integer, List<Param>> collect = sources.stream().filter
                (item -> Objects.equals(innerId, item.getParentId())).collect(Collectors.groupingBy(item -> item.getInnerId()));
        for (Map.Entry<Integer, List<Param>> entry : collect.entrySet()) {
            for (Param item : entry.getValue()) {
                HashMap<String, Object> map = new HashMap<>();
                boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
                if (b) {
                    List<HashMap<String, Object>> value = getApi(sources, item.getInnerId());
                    map.put(item.getParameterName(), value);
                } else {
                    map.put(item.getParameterName(), item.getValue());
                }
                result.add(map);
            }
        }
        return result;
    }


    private static List<String> join(List<IfmBisRequestApiParameterDO> params, IfmBisRequestApiParameterDO cc, List<String> list) {

        List<String> bb = new ArrayList<>();
        bb.add(cc.getParameterName());
        list.add(cc.getParameterName());
        for (IfmBisRequestApiParameterDO j : params) {
            if (Objects.equals(cc.getParentId(), j.getInnerId())) {
                bb.addAll(join(params, j, list));
            }
        }
        return bb;
    }


    public static List<NT> getNT(List<NT> sources, Integer innerId) throws InvocationTargetException, IllegalAccessException {
        ArrayList<NT> result = new ArrayList<>();
        List<NT> target = sources.stream().filter
                (item -> Objects.equals(innerId, item.getParentId())).collect(Collectors.toList());
        for (NT item : target) {
            boolean b = sources.stream().anyMatch(e -> Objects.equals(item.getInnerId(), e.getParentId()));
            if (innerId != -1) {
                NT nt = sources.stream().filter(e -> Objects.equals(item.getParentId(), e.getInnerId())).findFirst().get();
                item.setParentNode(nt);
            }
            if (b) {
                item.setChildren(getNT(sources, item.getInnerId()));
            }
            result.add(item);
        }
        return result;
    }

    @Data
    public static class IfmBisRequestApiParameterDO implements Serializable {

        protected static final long serialVersionUID = 2857101911338580951L;

        public IfmBisRequestApiParameterDO(String apiId, Integer innerId, Integer parentId, String parameterName) {
            this.apiId = apiId;
            this.innerId = innerId;
            this.parentId = parentId;
            this.parameterName = parameterName;
        }


        public IfmBisRequestApiParameterDO(String apiId, String parameterName, Integer parameterType, Integer parameterColumnType) {
            this.apiId = apiId;
            this.parameterName = parameterName;
            this.parameterType = parameterType;
            this.parameterColumnType = parameterColumnType;
        }

        public IfmBisRequestApiParameterDO() {
        }


        protected String id;

        private Integer innerId;
        private Integer parentId;

        /**
         * API ID
         */
        protected String apiId;

        protected String parameterName;
        protected Integer parameterType;
        protected Integer parameterColumnType;

        /**
         * 创建人
         */
        protected String createBy;

        /**
         * 创建时间
         */
        protected Date createTime;


        /**
         * 修改人
         */
        protected String updateBy;

        /**
         * 修改时间
         */
        protected Date updateTime;

    }


    @Data
    public static class IfmBisRequestApiParameterDTO extends IfmBisRequestApiParameterDO implements Serializable {


        protected static final long serialVersionUID = 1189612875316947004L;

        public IfmBisRequestApiParameterDTO(String apiId, Integer innerId, Integer parentId, String parameterName) {
            super(apiId, innerId, parentId, parameterName);
        }

        public IfmBisRequestApiParameterDTO() {
        }

        protected ArrayList<IfmBisRequestApiParameterDTO> children;

        protected String state;

        public ArrayList<IfmBisRequestApiParameterDTO> getChildren() {
            if (children == null) {
                children = new ArrayList<>();
            }
            return children;
        }

    }

    @Data
    public static class Param extends IfmBisRequestApiParameterDTO implements Serializable {

        public Param(String apiId, Integer innerId, Integer parentId, String parameterName, String value, String tableName) {
            super(apiId, innerId, parentId, parameterName);
            this.value = value;
            this.tableName = tableName;
        }

        public Param(String value) {
            this.value = value;
        }

        public Param() {
        }

        protected static final long serialVersionUID = 3904312824337043416L;

        private String rowId;

        private String paramId;

        private String parameterTypeStr;

        private Object value;

        private String tableName;
    }

    @Data
    public static class Dic implements Serializable {

        public Dic() {
        }

        public Dic(Integer innerId, Integer parentId, String nodeName, Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
        }

        public Dic(Integer innerId, Integer parentId, String nodeName, Integer fieldType, String tableName,
                   String fieldName, String foreignField) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
            this.tableName = tableName;
            this.fieldName = fieldName;
            this.foreignField = foreignField;
        }

        public Dic(Integer innerId, Integer parentId, String nodeName, Integer fieldType, String tableName, String fieldName) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
            this.tableName = tableName;
            this.fieldName = fieldName;
        }


        public Dic(Integer innerId, Integer parentId, Integer foreignId, String nodeName, Integer fieldType,
                   String tableName, String fieldName) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
            this.tableName = tableName;
            this.fieldName = fieldName;
            this.foreignId = foreignId;
        }

        public Dic(Integer innerId, Integer parentId, Integer foreignId, String nodeName, Integer fieldType,
                   String tableName, String fieldName, String foreignField) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
            this.tableName = tableName;
            this.fieldName = fieldName;
            this.foreignId = foreignId;
            this.foreignField = foreignField;

        }


        public String id;
        public Integer innerId;
        public Integer parentId;
        public String nodeName;
        public Integer fieldType;
        public String tableName;
        public String fieldName;
        public Integer foreignId;
        public String foreignField;

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

        public Table(Integer innerId, String tableName, String fieldName, Object value) {
            this.innerId = innerId;
            this.tableName = tableName;
            this.fieldName = fieldName;
            this.value = value;
        }

        public String id;
        public Integer innerId;
        public String tableName;
        public String fieldName;
        public Integer fieldType;
        public Object value;

    }

    @Data
    private static class NT extends Dic implements Serializable {
        public NT() {
        }

        public NT(Integer innerId, Integer parentId, String nodeName, Integer fieldType) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
        }

        public NT(Integer innerId, Integer parentId, String nodeName, Integer fieldType, String tableName, String fieldName) {
            this.innerId = innerId;
            this.parentId = parentId;
            this.nodeName = nodeName;
            this.fieldType = fieldType;
            this.tableName = tableName;
            this.fieldName = fieldName;
        }


        public NT parentNode;
        public Object value;
        public List<NT> children;

    }
}
