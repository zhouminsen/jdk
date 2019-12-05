package com.zjw.jdk.xml.convert.platform;

import lombok.Data;

import java.io.Serializable;

@Data
public class Table implements Serializable {


    private static final long serialVersionUID = 6819942684391804754L;

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