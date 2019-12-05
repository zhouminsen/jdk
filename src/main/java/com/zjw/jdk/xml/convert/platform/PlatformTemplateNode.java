package com.zjw.jdk.xml.convert.platform;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlatformTemplateNode extends IfmPlatformTemplateDetailDTO implements Serializable {

    private static final long serialVersionUID = -7177396157707072754L;

    public PlatformTemplateNode() {
    }

    private String fullTargetName;
    public String fullNodeName;
    @JSONField(serialize = false)
    public PlatformTemplateNode parentNode;
    public Object value;
    public List<PlatformTemplateNode> children;

    public List<PlatformTemplateNode> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }
}