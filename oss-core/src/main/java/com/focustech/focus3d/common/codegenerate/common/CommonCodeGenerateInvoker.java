package com.focustech.focus3d.common.codegenerate.common;

import com.focustech.focus3d.common.codegenerate.CodeGenerateCmd;
import com.focustech.focus3d.common.codegenerate.CodeGenerateCmdInvoker;

public class CommonCodeGenerateInvoker implements CodeGenerateCmdInvoker {
	private CodeGenerateCmd codeGenerateCmd;

	public void setCodeGenerateCmd(CodeGenerateCmd codeGenerateCmd) {
		this.codeGenerateCmd = codeGenerateCmd;
	}

	@Override
	public String generate() {
		return this.codeGenerateCmd.generate();
	}

}
