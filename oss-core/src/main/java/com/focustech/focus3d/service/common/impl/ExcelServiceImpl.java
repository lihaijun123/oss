/*
 * Copyright 2012 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.focustech.focus3d.service.common.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.focustech.common.constant.BaseSysParConst;
import com.focustech.common.constant.OssAdminParConst;
import com.focustech.focus3d.service.common.ExcelService;
import com.focustech.oss2008.dao.OssAdminParameterDao;
import com.focustech.oss2008.model.OssAdminParameter;

/**
 * ExcelServiceImpl.java
 *
 * @author liushuyan
 */
@Service
public class ExcelServiceImpl implements ExcelService {

	@Resource
	private OssAdminParameterDao<OssAdminParameter> ossAdminParameterDao;

	@Override
	public void insertDataToDatabase(Map<String, Object> map) {
		transferMapToDataObj(map);
	}

	/**
	 * 把map数据封装成数据对象 *
	 *
	 * @param staffinfoMap
	 * @return
	 */
	private List<OssAdminParameter> transferMapToDataObj(
			Map<String, Object> staffinfoMap) {
		List<OssAdminParameter> parameterinfoList = new ArrayList<OssAdminParameter>();
		OssAdminParameter ossAdminParameter = null;
		Map<String, String> columnMap = null;
		for (Map.Entry<String, Object> rMap : staffinfoMap.entrySet()) {
			columnMap = (Map<String, String>) rMap.getValue();
			// String excelData = columnMap.get("城市");
			String excelCityData = columnMap.get("城市");
			if (null != excelCityData) {

				// 以空格隔开获取城市和区号的值
				// String city = excelData.split(" ")[0];
				// String parameterValue = excelData.split(" ")[1];
				String parameterValue = "0" + columnMap.get("区号");
				OssAdminParameter city = ossAdminParameterDao
						.getLikeValueWithTypeAndWibsite(excelCityData,
								"CITY_LIST", "1");
				if (null != city) {
					String parameterKey = city.getParameterKey();
					String parameterName = city.getParameterValue();
					// String parameterKey = ossAdminParameterDao
					// .getKeyLikeValueWithTypeAndWibsite(excelCityData,
					// "CITY_LIST", "1");
					ossAdminParameter = new OssAdminParameter();
					ossAdminParameter
							.setWebSite(OssAdminParConst.DEFAULT_WEB_SITE); // "1"
					ossAdminParameter.setStatus(1);// OssAdminParConst.DEFAULT_STATUS
					ossAdminParameter
							.setParameterType(BaseSysParConst.PT_CITY_CODE_LIST);// "CITY_CODE_LIST"
					ossAdminParameter.setParameterOrder(1L);
					ossAdminParameter.setParameterKey(parameterKey);
					ossAdminParameter.setParameterName(parameterName);
					ossAdminParameter.setParameterValue(parameterValue);

					parameterinfoList.add(ossAdminParameter);
					// 插入数据
					ossAdminParameterDao.insert(ossAdminParameter);
				} else {

				}

			}
		}
		return parameterinfoList;
	}

}
