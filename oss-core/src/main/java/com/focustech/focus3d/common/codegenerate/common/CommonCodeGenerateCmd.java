package com.focustech.focus3d.common.codegenerate.common;

import com.focustech.focus3d.common.codegenerate.CodeGenerateCmd;
import com.focustech.focus3d.common.codegenerate.CodeGenerateCmdReceiver;

/**
 * 展馆编号生成命令
 * *
 * @author lihaijun
 *
 */
public class CommonCodeGenerateCmd implements CodeGenerateCmd {
	private CodeGenerateCmdReceiver cmdReceiver;

	public void setCmdReceiver(CodeGenerateCmdReceiver cmdReceiver) {
		this.cmdReceiver = cmdReceiver;
	}

	@Override
	public String generate() {
		return cmdReceiver.generate();
	}

}
