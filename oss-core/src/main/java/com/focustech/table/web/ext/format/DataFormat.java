package com.focustech.table.web.ext.format;

import net.sf.json.JsonConfig;

public interface DataFormat {
    public String format(Object data);

    public String format(Object data, long count, JsonConfig jsonConfig);
}
