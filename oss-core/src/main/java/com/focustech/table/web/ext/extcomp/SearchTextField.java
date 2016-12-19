package com.focustech.table.web.ext.extcomp;

public class SearchTextField extends SearchField {
    /*
     * 文本框大小
     */
    private int size = 10;

    public SearchTextField(int size) {
        this.size = size;
        this.type = "text";
    }

    public String getHTML() {
        String str = "";
        String displayName = getDisplayName();
        String name = this.getFieldName();// getName();
        String type = getType();
        String defaultValue = getDefaultValue();
        String oper = this.getOper();
        SearchFieldIFace operationField = new OperationField(oper, this.getColumnName() + "-" + oper);
        str = str + "<table border='0'>";
        str = str + "<tr>";
        str = str + "<td>";
        str = str + displayName;
        str = str + "</td>";
        str = str + "<td>";
        str = str + operationField.getHTML() + "";
        str = str + "</td>";
        str = str + "<td>";
        str =
                str + "<input type = 'text' name = '" + name + "' size = '" + size + "' value = '" + defaultValue
                        + "' onBlur='isNumber(this.value)'>&nbsp;&nbsp;";
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
}
