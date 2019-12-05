package com.zjw.jdk.xml.convert.response;

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

    private Integer dataType;
    private String dataTypeStr;
    private Integer foreignId;
    private String foreignField;

    private Integer nodeType;

    private String nodeTypeStr;

    private Integer fieldType;
    private String fieldTypeStr;


    @Override
    public boolean equals(Object o) {
       /* if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IfmResponseTemplateDetailDto that = (IfmResponseTemplateDetailDto) o;

        return nodeName.equals(that.nodeName);*/
        return super.equals(o);
    }

    @Override
    public int hashCode() {
/*        return nodeName.hashCode();*/
        return super.hashCode();
    }
}