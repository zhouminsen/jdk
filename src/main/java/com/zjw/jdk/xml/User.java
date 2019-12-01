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
 * @author Steven
 */
@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "User")
// 控制JAXB 绑定类中属性和字段的排序
@XmlType(propOrder = {
        "userId",
        "userName",
        "password",
        "birthday",
        "money",
        "computers",
        "computer",
        "snList"
})
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    // 用户Id
    private int userId;
    // 用户名
    private String userName;
    // 用户密码
    private String password;
    // 用户生日
    private Date birthday;
    // 用户钱包
    private double money;
    // 拥有的电脑
    private List<Computer> computers;
    private Computer computer;

    private List<Integer> snList;

    public User() {
        super();
    }

    public User(int userId, String userName, String password, Date birthday,
                double money) {
        super();
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.birthday = birthday;
        this.money = money;
    }


}