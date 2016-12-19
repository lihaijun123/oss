package com.focustech.focus3d.paint.category.model;
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
@Table(name = "paint_catgory")
public class PaintCatgory extends BaseEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sn;//序列号
	private String name;//类别名称
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
	
}
