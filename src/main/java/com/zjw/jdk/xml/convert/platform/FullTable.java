package com.zjw.jdk.xml.convert.platform;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class FullTable implements Serializable {

    private static final long serialVersionUID = 3611533802316177064L;
    /**
     * 虚id
     */
    Integer vId;
    String tableName;
    List<Table> list;
    FullTable parent;
    List<FullTable> children;

    public List<Table> getList() {
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    public List<FullTable> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }

    @JSONField(serialize = false)
    public Table getPrimary() {
        for (Table item : this.getList()) {
            if (Objects.equals(item.getInnerId(), this.getVId())) {
                return item;
            }
        }
        return null;
    }
}