package com.zjw.jdk.xml;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by Administrator on 2019-10-24.
 */
public class Main {
    public static void main(String[] args) throws DocumentException {
        org.dom4j.Document read = DocumentHelper.parseText("<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
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
                "            <outBizCode>外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用</outBizCode>" +
                "            <orderLineNo>单据行号，string（50）</orderLineNo>" +
                "            <ownerCode>货主编码, string (50)</ownerCode>" +
                "            <itemCode>商品编码, string (50) , 必填</itemCode>" +
                "            <itemId>仓储系统商品ID, string (50) , 条件必填</itemId>" +
                "            <snList>" +
                "                <sn>商品序列号, string(50)</sn>" +
                "                <sn>商品序列号, string(50)</sn>" +
                "                <sn>商品序列号, string(50)</sn>" +
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
                "            </batchs>" +
                "            <remark>备注, string (500)</remark>" +
                "        </orderLine>" +
                "        <orderLine>" +
                "            <outBizCode>外部业务编码, 消息ID, 用于去重，当单据需要分批次发送时使用</outBizCode>" +
                "            <orderLineNo>单据行号，string（50）</orderLineNo>" +
                "            <ownerCode>货主编码, string (50)</ownerCode>" +
                "            <itemCode>商品编码, string (50) , 必填</itemCode>" +
                "            <itemId>仓储系统商品ID, string (50) , 条件必填</itemId>" +
                "            <snList>" +
                "                <sn>商品序列号, string(50)</sn>" +
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
                "            </batchs>" +
                "            <remark>备注, string (500)</remark>" +
                "        </orderLine>" +
                "    </orderLines>" +
                "</request>");
        Element rootElement = read.getRootElement();
        List list = rootElement.selectNodes("/request/orderLines/orderLine/batchs/batch");
        for (Object item : list) {
            Element element = (Element) item;
            System.out.println(element.getName() + ":" + element.getText());
        }
    }
}
