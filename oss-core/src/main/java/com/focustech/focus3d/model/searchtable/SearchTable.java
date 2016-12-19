package com.focustech.focus3d.model.searchtable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.focustech.model.common.BaseEntity;
/**
 *
 * *
 * @author lihaijun
 *
 */
@javax.persistence.Table(name = "BIZ_SEARCH_TABLE_UPDATE")
@Entity
public class SearchTable extends BaseEntity {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sn;

    private Long searchTableSn;

    private String searchTableName;

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public Long getSearchTableSn() {
		return searchTableSn;
	}

	public void setSearchTableSn(Long searchTableSn) {
		this.searchTableSn = searchTableSn;
	}

	public String getSearchTableName() {
		return searchTableName;
	}

	public void setSearchTableName(String searchTableName) {
		this.searchTableName = searchTableName;
	}
}
