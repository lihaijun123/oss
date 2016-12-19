package com.focustech.focus3d.common.pdf;
/**
 * pdf信息封装
 * *
 * @author lihaijun
 *
 */
public class PdfEntity {
	/**名称*/
	private String name;
	/**内容*/
	private String content;
	/**保存路径*/
	private String savePath;

	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
