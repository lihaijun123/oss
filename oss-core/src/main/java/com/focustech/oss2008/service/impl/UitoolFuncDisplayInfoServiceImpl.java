package com.focustech.oss2008.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.common.utils.ListUtils;
import com.focustech.oss2008.dao.UitoolFuncDisplayInfoDao;
import com.focustech.oss2008.dao.UitoolFuncTableDao;
import com.focustech.oss2008.dao.UitoolListDisplayFieldDao;
import com.focustech.oss2008.model.UitoolFuncDisplayInfo;
import com.focustech.oss2008.model.UitoolFuncTable;
import com.focustech.oss2008.model.UitoolListDisplayField;
import com.focustech.oss2008.service.UitoolFuncDisplayInfoService;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Service
public class UitoolFuncDisplayInfoServiceImpl implements UitoolFuncDisplayInfoService<UitoolFuncDisplayInfo> {

	@Autowired
	private UitoolFuncDisplayInfoDao<UitoolFuncDisplayInfo> funcDisplayInfoDao;
	@Autowired
	private UitoolListDisplayFieldDao<UitoolListDisplayField> listDisplayFieldDao;
	@Autowired
	private UitoolFuncTableDao<UitoolFuncTable> funcTableDao;

	@Override
	public List<UitoolFuncDisplayInfo> list() {
		return funcDisplayInfoDao.list();
	}

	@Override
	public void saveFuncListFields(UitoolFuncDisplayInfo funcDisplayInfo) {
		long funcId = funcDisplayInfo.getFuncId();
		if(funcId > 0){
			List<UitoolListDisplayField> displayFields = funcDisplayInfo.getDisplayFields();
			if(ListUtils.isNotEmpty(displayFields)){
				listDisplayFieldDao.deleteListByFuncId(funcId);
				for (UitoolListDisplayField uitoolListDisplayField : displayFields) {
					listDisplayFieldDao.insertOrUpdate(uitoolListDisplayField);
				}
			}
		}
	}

	@Override
	public String exportDeleteListSql(Long funcId) {
		UitoolFuncTable funcTable = funcTableDao.selectByFuncId(funcId);
    	List<UitoolListDisplayField> listFieldList = listDisplayFieldDao.getListByFuncId(funcId);
    	StringBuffer exportSql = new StringBuffer();
    	exportSql.append("#删除列表配置数据#");
    	exportSql.append("\n");
    	exportSql.append(funcDisplayInfoDao.exportDeleteListSql(funcId));
    	exportSql.append("\n");
    	if(ListUtils.isNotEmpty(listFieldList)){
    		exportSql.append(listDisplayFieldDao.exportDeleteListFieldSql(funcId));
    		exportSql.append("\n");
    	}
    	if(funcTable != null){
    		exportSql.append(funcTableDao.exportDeleteFuncTableSql(funcId));
    		exportSql.append("\n");
    	}
		return exportSql.toString();
	}

	@Override
	public String exportInsertListSql(Long funcId) {
		UitoolFuncDisplayInfo listInfo = funcDisplayInfoDao.selectByFuncId(funcId);
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("#插入列表配置数据#");
		insertSql.append("\n");
		String insertListSql = "insert into `uitool_func_display_info` (`func_id`, `func_title`, `display_css`, `display_js`, `display_page`, `like_type`, `page_max`, `total_page_limit`, `total_limit`) "
							 + "values("
							 + "'" + listInfo.getFuncId() + "'"
							 + ",'" + listInfo.getFuncTitle() + "'"
							 + ",'" + listInfo.getDisplayCss() + "'"
							 + ",'" + listInfo.getDisplayJs() + "'"
							 + ",'" + listInfo.getDisplayPage() + "'"
							 + ",'" + listInfo.getLikeType() + "'"
							 + ",'" + listInfo.getPageMax() + "'"
							 + ",'" + listInfo.getTotalPageLimit() + "'"
							 + ",'" + listInfo.getTotalLimit() + "'"
							 + ");";
		insertSql.append(insertListSql);
		insertSql.append("\n");
		List<UitoolListDisplayField> fieldList = listDisplayFieldDao.getListByFuncId(funcId);
		if(ListUtils.isNotEmpty(fieldList)){
			for (UitoolListDisplayField field : fieldList) {
				String insertFieldSql = "insert into `uitool_list_display_field` (`func_id`, `field_name`, `display_text`, `url`, `display_index`, `is_display`, `is_order`, `is_find`, `fetch_value_type`, `title_url`, `field_event`, `field_format`, `is_expand`, `find_type`, `num_total_def`, `row_attr`) "
					+ "values("
					+ "'" + field.getFuncId() + "'"
					+ ",'" + field.getFieldName() + "'"
					+ ",'" + field.getDisplayText() + "'"
					+ ",'" + field.getUrl() + "'"
					+ ",'" + field.getDisplayIndex() + "'"
					+ ",'" + field.getIsDisplay() + "'"
					+ ",'" + field.getIsOrder() + "'"
					+ ",'" + field.getIsFind() + "'"
					+ ",NULL"
					+ ",'" + field.getTitleUrl() + "'"
					+ ",NULL"
					+ ",NULL"
					+ ",NULL"
					+ ",NULL"
					+ ",NULL"
					+ ",NULL"
					+ ");";
				insertSql.append(insertFieldSql);
				insertSql.append("\n");
			}
		}

		UitoolFuncTable funcTable = funcTableDao.selectByFuncId(funcId);
		if(funcTable != null){
			String insertFuncTableSql = "insert into `uitool_func_table` (`func_id`, `table_name`) "
									  + "values("
									  + "'" + funcTable.getFuncId() + "'"
									  + ",'" + funcTable.getTableName() + "'"
									  + ");";
			insertSql.append(insertFuncTableSql);
			insertSql.append("\n");
		}

		return insertSql.toString();
	}

	@Override
	public void saveFuncDisplayInfo(UitoolFuncDisplayInfo t) {
		funcDisplayInfoDao.insertOrUpdate(t);
	}

	@Override
	public UitoolFuncDisplayInfo selectByfuncId(long funcId) {
		return funcDisplayInfoDao.selectByFuncId(funcId);
	}

	@Override
	public void deleteByfuncId(long funcId) {
		funcDisplayInfoDao.delete(funcId);
	}

}
