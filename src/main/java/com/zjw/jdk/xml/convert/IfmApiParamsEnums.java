package com.zjw.jdk.xml.convert;

import lombok.Getter;

@Getter
public enum IfmApiParamsEnums {


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