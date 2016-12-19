package com.focustech.focus3d.paint.product.model;
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
@Table(name = "paint_product")
public class PaintProduct extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sn;//序列号
	private String name;//涂料名称
	private String colorName;//颜色名称
	private Long picFileSn;//模型图片
	private Long modelFileSn;//模型文件
	private String productId;//产品ID
	private Long cateSn;//产品系列编号
	private String size;//规格
	private String dosage;//用量
	private Long colorSn;//色系编号
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
	public String getColorName(){
	    return colorName;
	}
	public void setColorName(String colorName){
	    this.colorName = colorName;
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
	public String getProductId(){
	    return productId;
	}
	public void setProductId(String productId){
	    this.productId = productId;
	}
	public String getSize(){
	    return size;
	}
	public void setSize(String size){
	    this.size = size;
	}
	public String getDosage(){
	    return dosage;
	}
	public void setDosage(String dosage){
	    this.dosage = dosage;
	}
	public Long getCateSn() {
		return cateSn;
	}
	public void setCateSn(Long cateSn) {
		this.cateSn = cateSn;
	}
	public Long getColorSn() {
		return colorSn;
	}
	public void setColorSn(Long colorSn) {
		this.colorSn = colorSn;
	}
	
}
