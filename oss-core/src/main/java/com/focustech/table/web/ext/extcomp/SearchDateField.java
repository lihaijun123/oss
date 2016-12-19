package com.focustech.table.web.ext.extcomp;

public class SearchDateField extends SearchField {
    /*
     * 文本框大小
     */
    private int size = 10;

    public SearchDateField() {
        this.type = "date";
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
        str = str + "<div name = '" + name + "' id = '" + name + "'></div&nbsp;&nbsp;";
        // str = str +"<script>Ext.onReady(function() {new Ext.form.DateField({name: '"+name+"', width:120,
        // renderTo:"+name+", allowBlank:false,format:'m-d-Y'});});</script>";
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
