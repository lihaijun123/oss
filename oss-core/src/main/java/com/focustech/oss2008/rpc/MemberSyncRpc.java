package com.focustech.oss2008.rpc;

import java.util.List;
import java.util.Map;

/**
 * 同步前臺會員的RPC
 *
 * @author sunjingyu
 *
 */
public class MemberSyncRpc extends CommonsXmlRpcClient {

	/**
	 * 同步圖數數據
	 *
	 * @param member
	 * @throws Exception
	 */
	public void synchroPicLimitJob(Map<String, List> map) throws Exception {
		afterPropertiesSet();
		Object[] arguments = { map };
		execute("server.synchroPicLimitJob", arguments);
	}

	/**
	 * 同步待上下線的數據
	 *
	 * @param member
	 * @throws Exception
	 */
	public void synchroMemberJob(Map<String, List> map) throws Exception {
		afterPropertiesSet();
		Object[] arguments = { map };
		execute("server.synchroMemberJob", arguments);
	}

	/**
	 * 同步當日修改，創建的數據
	 *
	 * @param member
	 * @throws Exception
	 */
	public void syncModify(List<Map<String, Object>> member) throws Exception {
		afterPropertiesSet();
		Object[] arguments = { member };
		execute("server.changedMemberList", arguments);
	}

	/**
	 * 同步老數據中存在的平面會員的數據
	 *
	 * @param member
	 * @throws Exception
	 */
	public void syncOldData(List<Map<String, Object>> member) throws Exception {
		afterPropertiesSet();
		Object[] arguments = { member };
		execute("server.mISMemberList", arguments);
	}
}
