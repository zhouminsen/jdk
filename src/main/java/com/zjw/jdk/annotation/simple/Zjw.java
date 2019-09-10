package com.zjw.jdk.annotation.simple;

import java.util.Date;

/**
 * Created by Administrator on 2019-08-04.
 */
class Zjw {
    @Simple(name = "伟哥")
    private String name;

    @Simple(value = "i'm flying")
    private String action;

    @Simple(value = "1992-05-12")
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Zjw{" +
                "name='" + name + '\'' +
                ", action='" + action + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
