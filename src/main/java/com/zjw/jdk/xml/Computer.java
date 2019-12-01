package com.zjw.jdk.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 电脑类
 *
 * @author Steven
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Computer")
@XmlType(propOrder = {"serialNumber", "brandName", "productDate", "price", "zjws"})
@Data
public class Computer implements Serializable {
    private static final long serialVersionUID = 1L;

    // 序列号
    private String serialNumber;
    // 品牌名
    private String brandName;
    // 生成日期
    private Date productDate;
    // 价格
    private double price;

    private List<Integer> zjws;

    public Computer() {
        super();
    }

    public Computer(String serialNumber, String brandName, Date productDate,
                    double price, List<Integer> zjws) {
        super();
        this.serialNumber = serialNumber;
        this.brandName = brandName;
        this.productDate = productDate;
        this.price = price;
        this.zjws = zjws;
    }


}