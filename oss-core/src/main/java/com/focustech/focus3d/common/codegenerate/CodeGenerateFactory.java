package com.focustech.focus3d.common.codegenerate;

import java.util.HashMap;
import java.util.Map;

import com.focustech.focus3d.common.codegenerate.common.CommonCmdClient;

/**
 * 编号，订单号，等等生产工厂 *
 *
 * @author lihaijun
 */
public class CodeGenerateFactory {
	/** 各种号码生成实现类 */
	private static Map<String, String> codeGenerateImplMap = new HashMap<String, String>();
	static {
        codeGenerateImplMap.put(CodeGenerate.COMMON, CommonCmdClient.class.getName());
	}

	public static String generate(String type) {
		CodeGenerate codeGenerate = null;
		if (codeGenerateImplMap.containsKey(type)) {
			try {
				codeGenerate = (CodeGenerate) Class.forName(codeGenerateImplMap.get(type)).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("类型为：" + type + ",没有找到对应的实现类");
		}
		return codeGenerate.generate();
	}

	public static void main(String[] args){
		CodeGenerateFactory.generate(CodeGenerate.AD_POS);
	}
}
