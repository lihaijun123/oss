package com.focustech.table.web.ext.extcomp;

public class OperationField implements SearchFieldIFace {
    /** 搜索條件中控件的名字 */
    protected String name;
    protected String operName;

    public OperationField(String operName, String name) {
        this.operName = operName;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getHTML() {
        String str = "";
        int i = 0;
        int len = 0;
        String orValues[] = new String[]{">", "<", "=", ">=", "<="};
        String stringValues[] = new String[]{"=", "LIKE"};
        if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_EQUAL)) {
            str =
                    str + "<input type = 'hidden'  name = '" + name + "' size = '" + "1" + "' value = '" + "="
                            + "' disabled style='border:0 solid red'>:&nbsp;&nbsp;";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_MORE)) {
            str =
                    str + "<input type = 'text' name = '" + name + "' size = '" + "1" + "' value = '" + ">"
                            + "'disabled style='border:0 solid red'>&nbsp;&nbsp;";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_LESS)) {
            str =
                    str + "<input type = 'text' name = '" + name + "' size = '" + "1" + "' value = '" + "<"
                            + "'disabled style='border:0 solid red'>&nbsp;&nbsp;";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_MORE_EQUAL)) {
            str =
                    str + "<input type = 'text' name = '" + name + "' size = '" + "1" + "' value = '" + ">="
                            + "'disabled style='border:0 solid red'>&nbsp;&nbsp;";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_LESS_EQUAL)) {
            str =
                    str + "<input type = 'text' name = '" + name + "' size = '" + "1" + "' value = '" + "<="
                            + "'disabled style='border:0 solid red'>&nbsp;&nbsp;";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_LIKE)) {
            str =
                    str + "<input type = 'text' name = '" + name + "' size = '" + "1" + "' value = '" + "LIKE"
                            + "'disabled style='border:0 solid red'>&nbsp;&nbsp;";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_NUM)) {
            len = orValues.length;
            str = str + "<select name = '" + this.name + "'>";
            for (i = 0; i < len; i++) {
                str = str + "<option value = '" + orValues[i] + "'>";
                str = str + orValues[i];
                str = str + "</option>";
            }
            str = str + "</select>";
        }
        else if (operName.equals(SearchPanel.SEARCHCONDITION_VALUE_SRING)) {
            len = stringValues.length;
            str = str + "<select name = '" + this.name + "'>";
            for (i = 0; i < len; i++) {
                str = str + "<option value = '" + stringValues[i] + "'>";
                str = str + stringValues[i];
                str = str + "</option>";
            }
            str = str + "</select>";
        }
        return str;
    }
}
