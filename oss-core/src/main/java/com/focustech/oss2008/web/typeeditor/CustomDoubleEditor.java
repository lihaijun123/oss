package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;

public class CustomDoubleEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Double doubleClass = Double.valueOf(text);
        super.setValue(doubleClass);
    }
}
