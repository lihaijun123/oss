/*
 * Excel文件导入导出公用类
 * author: Zhang_xiaofeng
 * date:2011-06-24
 */
package com.focustech.focus3d.common.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.SheetSettings;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelIEDeal {

	// 要返回的List对象
	private static ArrayList<TreeMap<String, Object>> subData = new ArrayList<TreeMap<String, Object>>();

	/**
	 * excel文件导入-地市月度全勤
	 * @param is
	 * @param monthHead
	 * @return
	 */
	public static ArrayList<TreeMap<String, Object>> readExcelForFullduty(InputStream is, String[] monthHead) {
		// 判断文件是否存在
		if (is==null) {
			return null;
		}
		try {
			// 将静态的ArrayList数组清空，否则会不断增加
			subData.clear();
			WorkbookSettings wbs = new WorkbookSettings();
			wbs.setEncoding("ISO-8859-1");
			Workbook rwb = Workbook.getWorkbook(is, wbs);
			// 判断是否文件中无sheet
			if (null == rwb.getSheet(0)) {
				return null;
			}
			Sheet st[] = rwb.getSheets();// 得到Excel中所有页的sheet列表
			for (int a = 0; a < st.length; a++) {
				TreeMap<String, Object> tm = new TreeMap<String, Object>();
				for (int i = 1; i < st[a].getRows(); i++) {
					Map<String, String> hm = new Hashtable<String, String>();
					// 循环行中的每一个元素
					String key = st[a].getCell(0, i).getContents().trim();
					for (int j = 1; j < st[a].getColumns(); j++) {
						Cell c00 = st[a].getCell(j, i);
						// 通用的获取cell值的方法，返回字符串
						String strc00 = c00.getContents().trim();
						// 获取cell具体类型值的方式得到内容
						hm.put(monthHead[j-1], strc00);
					}
					tm.put(key, hm);
				}
				subData.add(tm);
			}
			// 关闭
			rwb.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(null != is){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return subData;
	}

	/**
	 * 获取sheet名称，用于验证
	 * *
	 * @param is
	 * @return
	 */
	public static String[] getSheetName(InputStream is){
		String[] sheetNameAry = null;
		// 判断文件是否存在
		if (is==null) {
			return null;
		}
		try {
			// 将静态的ArrayList数组清空，否则会不断增加
			subData.clear();
			WorkbookSettings wbs = new WorkbookSettings();
			wbs.setEncoding("ISO-8859-1");
			Workbook rwb = Workbook.getWorkbook(is, wbs);
			// 判断是否文件中无sheet
			if (null == rwb.getSheet(0)) {
				return null;
			}
			Sheet[] stAry = rwb.getSheets();// 得到Excel中所有页的sheet列表
			if(stAry != null && stAry.length > 0){
				sheetNameAry = new String[stAry.length];
				for(int i = 0, j = stAry.length; i < j; i ++){
					sheetNameAry[i] = stAry[i].getName();
				}
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(null != is){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sheetNameAry;
	}

	/**
	 *
	 * excel文件导入
	 *
	 * @param filePath 导入的文件路径
	 */
	public static ArrayList<TreeMap<String, Object>> readExcel(InputStream is, String[] monthHead) throws ExcelDealException{
		// 判断文件是否存在
		if (is==null) {
			return null;
		}
		try {
			// 将静态的ArrayList数组清空，否则会不断增加
			subData.clear();
			WorkbookSettings wbs = new WorkbookSettings();
			wbs.setEncoding("ISO-8859-1");
			Workbook rwb = Workbook.getWorkbook(is, wbs);
			// 判断是否文件中无sheet
			if (null == rwb.getSheet(0)) {
				return null;
			}
			Sheet st[] = rwb.getSheets();// 得到Excel中所有页的sheet列表
			// 循环每个列表
			for (int a = 0; a < st.length; a++) {
				// 循环sheet中的每行
				TreeMap<String, Object> tm = new TreeMap<String, Object>(new Comparator() {
					@Override
					public int compare(Object o1, Object o2) {
						if(o1 == null || o2 == null){
							return 0;
						}
						return String.valueOf(02).compareTo(String.valueOf((01)));
					}
				});
				int columnsLength = st[a].getColumns();
				if(columnsLength > monthHead.length){
					throw new ExcelImportColumnLengthException("实际导入的列长度为：" + columnsLength + " 列, 而系统模板规定的列长度为：" + monthHead.length + " 列");
				}
				for (int i = 1; i < st[a].getRows(); i++) {
					Map<String, String> hm = new HashMap<String, String>();
					// 循环行中的每一个元素
					if(isBlankRow(monthHead, st[a], i)){
						break;
					}
					for (int j = 0; j < columnsLength; j++) {
						Cell c00 = st[a].getCell(j, i);
						// 通用的获取cell值的方法，返回字符串
						String strc00 = c00.getContents().trim();
						// 获取cell具体类型值的方式得到内容
						//暂时
						hm.put(monthHead[j], strc00);
					}
					tm.put("" + i + "", (Object) hm);
				}
				subData.add(tm);
			}
			// 关闭
			rwb.close();
			// is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(null != is){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return subData;
	}

	private static boolean isBlankRow(String[] columnHeaderAry, Sheet sheet, int rowIndex){
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < columnHeaderAry.length; j++) {
			Cell c00 = sheet.getCell(j, rowIndex);
			// 通用的获取cell值的方法，返回字符串
			String strc00 = c00.getContents().trim();
			sb.append(strc00);
		}
		return "".equals(sb.toString().trim());
	}

	/**
	 * excel文件导出
	 *
	 * @param filePath 要导出的文件路径
	 * @param startCol 开始列
	 * @param startRow 开始行
	 * @param eList 传入的数据对象
	 */
	public static File writeExcel(String filePath, int startCol[],
			int startRow[], List<SheetEnttiy> eList) {
		File eFile = new File(filePath);
		// 判断文件是否存在
		try {
			// 新建工作表路径和Excel表名字
			WritableWorkbook book = Workbook.createWorkbook(eFile);
			WritableFont wFont = new WritableFont(WritableFont.TIMES,10,WritableFont.BOLD);
			WritableFont wInfoFont = new WritableFont(WritableFont.TIMES,10,WritableFont.NO_BOLD);
			// 循环将数据写入各个sheet
			for (int i = 0; i < eList.size(); i++) {
				// 获取模板excel中的各个sheet
				SheetEnttiy sheetEntity = eList.get(i);
				WritableSheet sheet = book.createSheet(sheetEntity.getSheetName(), i);
				SheetSettings setting = sheet.getSettings();
				setting.setVerticalFreeze(1);
				// 获取当前sheet的信息
				 Map<String, Object> tMap = sheetEntity.getDetailMap();
				 //TreeMap<String, Object> tMap = (TreeMap<String, Object>) sheetEntity
				// .getDetailMap();
				// 获取当前sheet的表头对应的ID
				String sheetHead[] = sheetEntity.getSheetHead();
				int r = 0;
				Map mapColumn = new HashMap();
				int maxHeadWidth[] = new int[sheetHead.length];
				// 设置sheet中的表头
				if(null != sheetHead && sheetHead.length != 0) {
					for (int b = 0; b < sheetHead.length; b++) {
						int iSCol = 0;
						int iSRow = 0;
						WritableCellFormat wcFormat = new WritableCellFormat(wFont);
						Label label = new Label(iSRow+b, iSCol,sheetEntity.getHeadNameMap().get(sheetHead[b]),wcFormat);
						wcFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
						wcFormat.setBackground(Colour.ICE_BLUE);
						wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
						wcFormat.setAlignment(Alignment.CENTRE);
						sheet.setColumnView(iSRow+b, sheetEntity.getHeadNameMap().get(sheetHead[b]).length()+10);
						sheet.addCell(label);
						maxHeadWidth[b] = label.getString().getBytes().length;
					}
				}
				// 取数据行
				if(!tMap.isEmpty() && tMap.size()>0) {
					for (Map.Entry<String, Object> rMap : tMap.entrySet()) {
						r++;
						//设置行高
						sheet.setRowView(r, 300);
						Map<String, String> columnMap = (Map<String, String>) rMap.getValue();
						// 取数据列
						for (int a = 0; a < sheetHead.length; a++) {
							int iSCol = 0;
							int iSRow = 0;
							WritableCellFormat wcFormat = new WritableCellFormat(wInfoFont);
							wcFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
							//隔行设置颜色
							if(r%2==0) {
								wcFormat.setBackground(Colour.VERY_LIGHT_YELLOW);
							}
							Label label = null;
							if("false".equals(columnMap.get(sheetHead[a]))) {
								label = new Label( a+ iSRow, r + iSCol, "",wcFormat);
								wcFormat.setBackground(Colour.GRAY_25);
								wcFormat.setLocked(true);
							} else if("true".equals(columnMap.get(sheetHead[a]))){
								label = new Label( a+ iSRow, r + iSCol, "",wcFormat);
								wcFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
							} else {
								label = new Label( a+ iSRow, r + iSCol, columnMap.get(sheetHead[a]),wcFormat);
								wcFormat.setBorder(Border.ALL,BorderLineStyle.THIN);
							}
							sheet.addCell(label);
							if(r==1) {
								mapColumn.put(a, label.getString().getBytes().length);
							} else {
								if(label.getString().length() > Integer.parseInt(String.valueOf(mapColumn.get(a)))) {
									mapColumn.put(a, label.getString().getBytes().length) ;
								}
							}
						}
					}
					Iterator it = mapColumn.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry mapEntry = (Map.Entry)it.next();
						int iIndex = Integer.parseInt(String.valueOf(mapEntry.getKey()));
						int iWidth = Integer.parseInt(String.valueOf(mapEntry.getValue()));
						if(maxHeadWidth[iIndex] > iWidth) {
							iWidth = maxHeadWidth[iIndex];
						}
						if ((iWidth)>80) {
							iWidth = 80;
						} else {
							iWidth = iWidth+1;
						}
						sheet.setColumnView(iIndex, iWidth);
					}

				}
			}
			book.write();
			// 关闭
			book.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return eFile;
	}

}
