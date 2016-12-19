package com.focustech.oss2008.web.typeeditor;

import java.beans.PropertyEditorSupport;

public class CustomLongEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Long longClass = Long.valueOf(text);
        super.setValue(longClass);
    }
}
