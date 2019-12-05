package com.zjw.jdk.xml.convert.platform;

import lombok.Data;

import java.io.Serializable;

@Data
public class IfmPlatformTemplateDetailDTO implements Serializable {


    private static final long serialVersionUID = -1305506627629057385L;

    public IfmPlatformTemplateDetailDTO() {
    }

    public IfmPlatformTemplateDetailDTO(Integer innerId, Integer parentId, String nodeName, Integer nodeType) {
        this.innerId = innerId;
        this.parentId = parentId;
        this.nodeName = nodeName;
        this.nodeType = nodeType;

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
    private String fieldTypeStr;
}