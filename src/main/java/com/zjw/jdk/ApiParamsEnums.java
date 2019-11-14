package com.zjw.jdk;

import lombok.Getter;

/**
 * @author zhoujiawei
 * @ClassName: IfmEnums
 * @Description: 根节点 枚举类
 * @date 2019/9/25
 */
@Getter
public enum ApiParamsEnums {


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


    ApiParamsEnums(Integer innerId, String name, Integer parentId) {
        this.innerId = innerId;
        this.name = name;
        this.parentId = parentId;
    }
}
