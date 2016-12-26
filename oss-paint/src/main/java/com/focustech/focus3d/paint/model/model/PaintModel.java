package com.focustech.focus3d.paint.model.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.focustech.model.common.BaseEntity;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Entity
@Table(name = "paint_model")
public class PaintModel extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sn;//序列号
	private String name;//名称
	private Long picFileSn;//模型图片
	private Long modelFileSn;//模型文件
	private Integer versionNum;
	private Integer useCount;//使用次数
	private Long downloadCount;//下载次数
	private Integer modelType;//模型数据类型 1-3D全景 2-3D模型
	private Integer houseType;//房屋类型：1-别墅 2-小高层 3-高层
	public Long getSn(){
	    return sn;
	}
	public void setSn(Long sn){
	    this.sn = sn;
	}
	public String getName(){
	    return name;
	}
	public void setName(String name){
	    this.name = name;
	}
	public Long getPicFileSn(){
	    return picFileSn;
	}
	public void setPicFileSn(Long picFileSn){
	    this.picFileSn = picFileSn;
	}
	public Long getModelFileSn(){
	    return modelFileSn;
	}
	public void setModelFileSn(Long modelFileSn){
	    this.modelFileSn = modelFileSn;
	}
	public Integer getUseCount(){
	    return useCount;
	}
	public void setUseCount(Integer useCount){
	    this.useCount = useCount;
	}
	public Long getDownloadCount(){
	    return downloadCount;
	}
	public void setDownloadCount(Long downloadCount){
	    this.downloadCount = downloadCount;
	}
	public Integer getModelType(){
	    return modelType;
	}
	public void setModelType(Integer modelType){
	    this.modelType = modelType;
	}
	public Integer getHouseType(){
	    return houseType;
	}
	public void setHouseType(Integer houseType){
	    this.houseType = houseType;
	}
	public Integer getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(Integer versionNum) {
		this.versionNum = versionNum;
	}
}
