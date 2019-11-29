package com.zjw.jdk.xml.convert;

import lombok.Data;

import java.io.Serializable;

@Data
public class IfmResponseTemplateDetailDTO implements Serializable {

    public IfmResponseTemplateDetailDTO() {
    }

    public IfmResponseTemplateDetailDTO(String nodeName, String targetName, Integer nodeType, Integer fieldType) {
        this.nodeName = nodeName;
        this.targetName = targetName;
        this.nodeType = nodeType;
        this.fieldType = fieldType;
    }

    public IfmResponseTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                        String targetName, Integer nodeType) {
        this.innerId = innerId;
        this.parentId = parentId;
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.targetName = targetName;

    }


    public IfmResponseTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                        String targetName, Integer matchType, Integer nodeType,
                                        Integer fieldType) {
        this.innerId = innerId;
        this.parentId = parentId;
        this.nodeName = nodeName;
        this.targetName = targetName;
        this.matchType = matchType;
        this.nodeType = nodeType;
        this.fieldType = fieldType;
    }


    public IfmResponseTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName,
                                        String targetName, Integer matchType, Integer selectType, String selectStart, String selectEnd,
                                        Integer nodeType, Integer fieldType) {
        this.innerId = innerId;
        this.parentId = parentId;
        this.nodeName = nodeName;
        this.targetName = targetName;
        this.matchType = matchType;
        this.selectType = selectType;
        this.selectStart = selectStart;
        this.selectEnd = selectEnd;
        this.nodeType = nodeType;
        this.fieldType = fieldType;
    }


    private Integer innerId;

    private Integer parentId;


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
     * 节点类型 0:对象，1：对象数组，2：数组，3：最终节点
     */
    private Integer nodeType;

    /**
     * 节点类型 0:对象，1：对象数组，2：数组，3：最终节点
     */
    private String nodeTypeStr;

    /**
     * 字段类型(0：字符串 1：时间 2：数字)
     */
    private Integer fieldType;

    private String fieldTypeStr;
}
