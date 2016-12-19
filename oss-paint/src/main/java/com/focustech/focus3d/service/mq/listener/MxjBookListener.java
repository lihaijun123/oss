package com.focustech.focus3d.service.mq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.log4j.Logger;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class MxjBookListener implements MessageListener {
	protected static final Logger log = Logger.getLogger(MxjBookListener.class);
	/*@Autowired
	private MxjBookService<MxjBook> mxjBookService;
	@Autowired
	private MxjBookPageService<MxjBookPage> mxjBookPageService;*/
	@Override
	public void onMessage(Message message) {/*
		if(message instanceof TextMessage){
			TextMessage textMessage = (TextMessage)message;
			try {
				String msg = textMessage.getText();
				log.debug(msg);
				JSONObject jo = JSONObject.fromObject(msg);
				String picFileId = jo.getString("picFileId");
				String contentStr = jo.getString("content");
				Pattern pattern = Pattern.compile("\\d+");
				int pageNum = 0;
				String content = "";
				String typeName = "";
				if(StringUtils.isNotEmpty(contentStr)){
					JSONObject contentJo = JSONObject.fromObject(contentStr);
					@SuppressWarnings("rawtypes")
					Iterator keys = contentJo.keys();
					while(keys.hasNext()){
						String key = TCUtil.sv(keys.next());
						Matcher matcher = pattern.matcher(key);
						if(matcher.matches()){
							pageNum = TCUtil.iv(key);
							content = contentJo.getString(key);
							break;
						}
					}
					typeName = contentJo.getString("type");
					int type = MxjBookType.getTypeByNameEn(typeName);
					MxjBook mxjBook = mxjBookService.selectByType(type);
					Long mxjBookSn = null;
					if(mxjBook == null){
						mxjBook = new MxjBook();
						mxjBook.setName(MxjBookType.getNameZhByNameEn(typeName));
						mxjBook.setType(type);
						mxjBook.setValid(1);
						mxjBookService.insertOrUpdate(mxjBook);
					}
					mxjBookSn = mxjBook.getSn();
					//删除旧页
					if(pageNum > 0 && mxjBookSn > 0 && StringUtils.isNotEmpty(picFileId)){
						log.debug("保存页:" + pageNum);
						mxjBookPageService.delete(mxjBookSn, pageNum);
						//新增
						MxjBookPage mxjBookPage = new MxjBookPage();
						mxjBookPage.setMxjBook(mxjBook);
						mxjBookPage.setPageNum(pageNum);
						mxjBookPage.setPicFileSn(TCUtil.lv(picFileId));
						mxjBookPage.setContent(content);
					    mxjBookPageService.insertOrUpdate(mxjBookPage);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	*/}
}
