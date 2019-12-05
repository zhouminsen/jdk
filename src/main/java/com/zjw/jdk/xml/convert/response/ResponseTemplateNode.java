package com.zjw.jdk.xml.convert.response;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujiawei
 * @ClassName: PlatformTemplateNode
 * @Description: 模板树节点
 * @since 2019-06-24
 */
@Data
public class ResponseTemplateNode extends IfmResponseTemplateDetailDTO implements Serializable {
    private String fullNodeName;
    private String fullTargetName;
    private ResponseTemplateNode parentNode;
    private Object value;
    private List<ResponseTemplateNode> children;

    public List<ResponseTemplateNode> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

}