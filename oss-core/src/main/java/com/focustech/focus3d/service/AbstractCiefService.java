package com.focustech.focus3d.service;

import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.common.codegenerate.CodeGenerate;
import com.focustech.focus3d.common.codegenerate.CodeGenerateFactory;
import com.focustech.oss2008.service.AbstractServiceSupport;

public class AbstractCiefService extends AbstractServiceSupport {
	 /**
     * 生成12位的id号，yyyyMMDD+sn的后四位（不足四位前面补零）
     * 注：（两条数据id重复的最小可能是，记录表中的sn从一位开始，一天中插入的数据超过9000条）
     * @param recordSn
     * @return
     */
    protected String generet12CharacterId(Long recordSn) {
    	if(null != recordSn){
    		String exbCode = CodeGenerateFactory.generate(CodeGenerate.EXBHALL);
    		return exbCode + ( ( recordSn >= 0 && recordSn <= 1000 ) ? StringUtils.frontCompWithZore( TCUtil.iv( recordSn ) , 4 )
    				: recordSn.toString().substring( recordSn.toString().length() - 4 ) );
    	}
		return null;
	}
}
