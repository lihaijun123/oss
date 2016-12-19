package com.focustech.focus3d.common.codegenerate.common;

import com.focustech.focus3d.common.codegenerate.CodeGenerateCmdReceiver;
import com.focustech.focus3d.utils.DateUtils;

/**
 * 展会编号生成 *
 *
 * @author lihaijun
 *
 */
public class CommonCodeGenerateCmdReceiver implements CodeGenerateCmdReceiver {

	@Override
	public String generate() {
        return DateUtils.getCurDate(DateUtils.YYYYMM + System.currentTimeMillis());
	}

}
