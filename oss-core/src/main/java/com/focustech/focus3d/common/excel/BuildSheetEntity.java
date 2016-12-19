package com.focustech.focus3d.common.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * *
 * @author lihaijun
 *
 */
public class BuildSheetEntity {

	/**
	 *@todo 从map里面构建sheet数据
	 *@param resultMap 从returnMap里面Key-RESULT的值
	 *
	 * */
	public static SheetEnttiy build(Map<String, Object> resultMap){
		/*String sheetName = resultMap.get(IFunction.TEMPLATE_SHEET_NAME_STR).toString();
	    String[] sheetHead = (String[])resultMap.get(IFunction.TEMPLATE_HEADER_CODE_ARY);
	    Map<String, String> headNameMap = (Map<String, String>)resultMap.get(IFunction.TEMPLATE_HEADER_CODE_NAME_MAP);
	    Map<String, Object> dataMap = (Map<String, Object>)resultMap.get(IFunction.TEMPLATE_DATA_MAP);
		SheetEnttiy sheetEntity = new SheetEnttiy();
		sheetEntity.setSheetName(sheetName);
		sheetEntity.setSheetHead(sheetHead);
		sheetEntity.setHeadNameMap(headNameMap);
		sheetEntity.setDetailMap(dataMap);
		return sheetEntity;*/
		return null;
	}

	/**
	 * 导出多个sheet
	 * *
	 * @param resultMap
	 * @return
	 */
	public static List<SheetEnttiy> buildSheetEntityList(Map<String, Object> resultMap){
		List<SheetEnttiy> sheetEntityList = new ArrayList<SheetEnttiy>();
		Map<String, Object> cMap = null;
		for(Map.Entry<String, Object> rMap : resultMap.entrySet()){
			cMap = (Map<String, Object>)rMap.getValue();
			sheetEntityList.add(build(cMap));
		}
		return sheetEntityList;
	}

}
