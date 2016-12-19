package com.focustech.oss2008.dao.impl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.NamingStrategy;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.focustech.common.utils.ReflectUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.model.common.BaseEntity;
import com.focustech.oss2008.dao.BaseEntityDao;
import com.focustech.oss2008.dao.OssHibernateDaoSupport;
/**
 *
 * *
 * @author lihaijun
 *
 */
@Repository
public class BaseEntityDaoImpl extends OssHibernateDaoSupport<BaseEntity> implements BaseEntityDao<BaseEntity> {
	public static NamingStrategy namingStrategy = new ImprovedNamingStrategy();
	@Override
	public BaseEntity select(BaseEntity baseEntity) {
		Class<?> clas = baseEntity.getClass();
		Table annotation = clas.getAnnotation(Table.class);
		long sn = 0;
		if(annotation != null){
			String tableName = annotation.name();
			Field[] declaredFields = clas.getDeclaredFields();
			String snColumnName = "";
			for (Field field : declaredFields) {
				Id tableId = field.getAnnotation(Id.class);
				if(tableId != null){
					Column column = field.getAnnotation(Column.class);
					if(column != null){
						snColumnName = column.name();;
					} else {
						snColumnName = namingStrategy.propertyToColumnName(field.getName());
					}
					if(StringUtils.isNotEmpty(snColumnName)){
						String getSnName = ReflectUtils.buildMethodName("get", field.getName());
						sn = TCUtil.lv(ReflectUtils.invoke(baseEntity, getSnName));
						break;
					}
				}
			}
			if(StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(snColumnName) && sn > 0){
				String sql = "SELECT "
						   + "	ADDER_SN "
						   + ", ADDER_NAME "
						   + ", ADD_TIME "
						   + ", UPDATER_SN "
						   + ", UPDATER_NAME "
						   + ", UPDATE_TIME "
						   + " FROM " + tableName
						   + " WHERE " + snColumnName + "=?";
				return getJdbcTemplate().queryForObject(sql, new BaseEntityMapper(), sn);
			}
		}
		return null;
	}

	private static final class BaseEntityMapper implements ParameterizedRowMapper<BaseEntity> {
		public BaseEntity mapRow(ResultSet rs, int rowNum)
		throws SQLException {
			BaseEntity baseEntity = new BaseEntity();
			baseEntity.setAdderSn(rs.getLong("ADDER_SN"));
			baseEntity.setAdderName(rs.getString("ADDER_NAME"));
			baseEntity.setAddTime(rs.getTimestamp("ADD_TIME"));
			baseEntity.setUpdaterSn(rs.getLong("UPDATER_SN"));
			baseEntity.setUpdaterName(rs.getString("UPDATER_NAME"));
			baseEntity.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
			return baseEntity;
		}
	}
}
