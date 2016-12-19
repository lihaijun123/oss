package com.focustech.oss2008.rpc;


public class RefundRpc extends CommonsXmlRpcClient {

	/**
	 * 確認退款，前台停止收費服務
	 * @param orderNumber
	 * @throws Exception
	 */
	public void stopService(String orderNumber) throws Exception{
		afterPropertiesSet();
        Object[] arguments = {orderNumber};
        execute("server.refundOrder", arguments);
	}

	/**
	 * 開始服務
	 * @param orderNumber
	 * @throws Exception
	 */
	public void startService(String orderNumber) throws Exception{
		afterPropertiesSet();
        Object[] arguments = {orderNumber};
        log.debug("call interface [server.startService] start");
        log.debug("args: " + arguments);
        execute("server.startService", arguments);
        log.debug("call interface [server.startService] end");
	}

	/**
	 * 檢查是否開通域名
	 * @param orderNumber
	 * @throws Exception
	 */
	public boolean checkDomain(String orderNumber) throws Exception{
		afterPropertiesSet();
        Object[] arguments = {orderNumber};
        Object result = execute("server.checkDomain", arguments);
        return (Boolean)result;
	}

	/**
	 * 退款完成發送提醒
	 * @throws Exception
	 */
	public void sendRefundRemind(String orderNumber) throws Exception{
		afterPropertiesSet();
        Object[] arguments = {orderNumber};
        execute("server.sendRefundRemind", arguments);
	}

	/**
	 * 修改前台訂單狀態
	 * @param contractId
	 * @param orderPay
	 * @throws Exception
	 */
	public void updateOrder(Long contractId, Integer status) throws Exception {
		afterPropertiesSet();
        Object[] arguments = {contractId.toString(),status};
        execute("server.updateOrder", arguments);
	}

}
