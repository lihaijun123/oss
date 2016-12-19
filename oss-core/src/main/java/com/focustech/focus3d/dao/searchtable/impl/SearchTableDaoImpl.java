package com.focustech.focus3d.dao.searchtable.impl;

import java.util.Date;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import com.focustech.common.constant.OverallConst;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.focus3d.dao.searchtable.SearchTableDao;
import com.focustech.focus3d.model.searchtable.SearchTable;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;

/**
 *
 * *
 *
 * @author lihaijun
 *
 */
@Repository
public class SearchTableDaoImpl extends OssHibernateDaoSupport<SearchTable> implements SearchTableDao<SearchTable> {
	/**
	 *
	 * *
	 * @param tableName
	 * @param sn
	 * @return
	 */
	private Long getQueryResult(String tableName, Long sn) {
		String sql = "SELECT SN FROM " + tableName + " WHERE sn = " + sn;
		Object obj = getCurrentSession().createSQLQuery(sql).addScalar("SN", Hibernate.LONG).uniqueResult();
		return null == obj ? 0L : TCUtil.lv(obj.toString());
	}

	@Override
	public void maintanceSearchDeleteTable(String bizTableName, Long bizTableRecordSn, String action) {
		if(StringUtils.isNotEmpty(bizTableName) && bizTableRecordSn != null){
			String searchTableName = bizTableName + SEARCH_TABLE_SUFFIX;
			Long searchTableSn = getQueryResult(searchTableName, bizTableRecordSn);
			if(searchTableSn != 0){
				// 删除search表
				String delSql = "DELETE FROM " + searchTableName  + " WHERE SN = " + bizTableRecordSn;
				getCurrentSession().createSQLQuery(delSql).executeUpdate();
				// 在search_table_delete表里加条记录
				SearchTable tableDelete = new SearchTable();
				tableDelete.setSearchTableSn(bizTableRecordSn);
				tableDelete.setSearchTableName(searchTableName);
				tableDelete.setAdderSn(-1L);
				tableDelete.setAdderName("");
				tableDelete.setAddTime(new Date());
				tableDelete.setUpdaterSn(-1L);
				tableDelete.setUpdaterName("");
				tableDelete.setUpdateTime(new Date());
				insert(tableDelete);
			}
			if(OverallConst.PASS.equals(action)){
				String insertSql = "INSERT INTO " + searchTableName + " SELECT * FROM " + bizTableName + " b where b.sn = " + bizTableRecordSn;
				getCurrentSession().createSQLQuery(insertSql).executeUpdate();
			}
		}
	}
}
