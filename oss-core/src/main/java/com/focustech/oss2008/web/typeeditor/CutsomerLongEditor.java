package com.focustech.oss2008.web.typeeditor;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

import com.focustech.oss2008.exception.OssCheckedException;

public class CutsomerLongEditor extends CustomNumberEditor {
    public CutsomerLongEditor(Class numberClass, boolean allowEmpty) throws OssCheckedException {
        super(numberClass, allowEmpty);
    }
}
