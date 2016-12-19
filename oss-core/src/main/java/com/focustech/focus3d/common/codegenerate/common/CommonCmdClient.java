package com.focustech.focus3d.common.codegenerate.common;

import com.focustech.focus3d.common.codegenerate.CodeGenerate;

/**
 *
 * *
 *
 * @author lihaijun
 *
 */
public class CommonCmdClient implements CodeGenerate {
    private CommonCodeGenerateCmd cmd;
    private CommonCodeGenerateInvoker invoker;
    private CommonCodeGenerateCmdReceiver receiver;

	@Override
	public String generate() {
        cmd = new CommonCodeGenerateCmd();
        receiver = new CommonCodeGenerateCmdReceiver();
        invoker = new CommonCodeGenerateInvoker();
		cmd.setCmdReceiver(receiver);
		invoker.setCodeGenerateCmd(cmd);
		return invoker.generate();
	}
}
