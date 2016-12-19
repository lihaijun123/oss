package com.focustech.table.web.ext.extcomp;

import java.util.ArrayList;
import java.util.List;

import com.focustech.oss2008.model.OssAdminParameter;

public class SearchComboxField extends SearchField {
    /*
     * 文本框大小
     */
    private int size = 20;
    private List<OssAdminParameter> listValues;

    public SearchComboxField(List<OssAdminParameter> listValues) {
        this.size = listValues.size();
        this.type = "list";
        this.listValues = listValues;
    }

    public String getHTML() {
        String str = "";
        int i = 0;
        if (this.listValues == null) {
            listValues = new ArrayList();
        }
        int len = this.listValues.size();
        String displayName = getDisplayName();
        String name = this.getFieldName();
        String type = getType();
        String oper = this.getOper();
        SearchFieldIFace operationField = new OperationField(oper, this.getColumnName() + "-" + oper);
        String defaultValue = getDefaultValue();
        str = str + "<table border='0'>";
        str = str + "<tr>";
        str = str + "<td>";
        str = str + displayName;
        str = str + "</td>";
        str = str + "<td>";
        str = str + operationField.getHTML() + "";
        str = str + "</td>";
        str = str + "<td>";
        str = str + "<select name = '" + name + "' style = 'size:" + this.size + "'>";
        str = str + "<option value = '" + "" + "'>";
        str = str + "    ";
        str = str + "</option>";
        for (i = 0; i < len; i++) {
            OssAdminParameter OssAdminParameter = listValues.get(i);
            str = str + "<option value = '" + OssAdminParameter.getParameterKey() + "'>";
            str = str + OssAdminParameter.getParameterValue();
            str = str + "</option>";
        }
        str = str + "</select>";
        str = str + "</td>";
        str = str + "</tr>";
        str = str + "</table>";
        return str;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setListValues(ArrayList listValues) {
        this.listValues = listValues;
    }
}
