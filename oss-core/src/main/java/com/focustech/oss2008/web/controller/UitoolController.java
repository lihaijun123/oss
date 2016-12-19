package com.focustech.oss2008.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.StringUtils;
import com.focustech.common.utils.TCUtil;
import com.focustech.oss2008.model.OssAdminResource;
import com.focustech.oss2008.model.UitoolFuncDisplayInfo;
import com.focustech.oss2008.model.UitoolFuncTable;
import com.focustech.oss2008.model.UitoolListDisplayField;
import com.focustech.oss2008.service.MenuTree;
import com.focustech.oss2008.service.ResourceService;
import com.focustech.oss2008.service.UitoolFuncDisplayInfoService;
import com.focustech.oss2008.service.UitoolFuncTableService;
import com.focustech.oss2008.service.UitoolListDisplayFieldService;
import com.focustech.oss2008.web.AbstractController;
import com.focustech.uitool.framework.NLGlobal;
import com.focustech.uitool.framework.SystemTool;
/**
 *
 * *
 * @author lihaijun
 *
 */
@SessionAttributes("tableNameList")
@Controller
@RequestMapping(value = "/uitool.do")
public class UitoolController extends AbstractController {
	@Autowired
	private UitoolFuncDisplayInfoService<UitoolFuncDisplayInfo> funcDisplayInfoService;
	@Autowired
	private UitoolListDisplayFieldService<UitoolListDisplayField> listDisplayFieldService;
	@Autowired
	private UitoolFuncTableService<UitoolFuncTable> funcTableService;
	@Autowired
	private MenuTree menuTree;
	@Autowired
	private ResourceService resourceService;
	/**
	 *
	 * *
	 * @param resourceId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.GET)
	public String add(Long resourceId, ModelMap model) {
		UitoolFuncDisplayInfo uitoolFuncDisplayInfo = new UitoolFuncDisplayInfo();
		if(resourceId != null){
			OssAdminResource resource = resourceService.getResource(resourceId);
			if(resource != null){
				uitoolFuncDisplayInfo.setFuncId(resourceId);
				uitoolFuncDisplayInfo.setFuncTitle(resource.getResourceName());
			}
		}
		model.addAttribute("uitoolFuncDisplayInfo", uitoolFuncDisplayInfo);
		return "/uitool/add";
	}
	/**
	 *
	 * *
	 * @param funcDisplayInfo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=add", method = RequestMethod.POST)
	public String save(UitoolFuncDisplayInfo funcDisplayInfo, ModelMap model, HttpServletRequest request) {
		funcDisplayInfoService.saveFuncDisplayInfo(funcDisplayInfo);
		request.getSession().setAttribute("ok", "保存成功！");
		return redirectTo("/uitool.do?method=edit&funcId=" + funcDisplayInfo.getFuncId());
	}
	/**
	 *
	 * *
	 * @param funcId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.GET)
	public String edit(long funcId, ModelMap model) {
		UitoolFuncDisplayInfo funcDisplayInfo = funcDisplayInfoService.selectByfuncId(funcId);
		model.put("uitoolFuncDisplayInfo", funcDisplayInfo);
		return "/uitool/modify";
	}
	/**
	 *
	 * *
	 * @param funcDisplayInfo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=edit", method = RequestMethod.POST)
	public String modify(UitoolFuncDisplayInfo funcDisplayInfo, ModelMap model, HttpServletRequest request) {
		funcDisplayInfoService.saveFuncDisplayInfo(funcDisplayInfo);
		model.put("uitoolFuncDisplayInfo", funcDisplayInfo);
		request.getSession().setAttribute("ok", "修改成功！");
		return "/uitool/modify";
	}
	/**
	 *
	 * *
	 * @param funcId
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "method=delete", method = RequestMethod.GET)
	public String delete(long funcId, ModelMap model) {
		funcDisplayInfoService.deleteByfuncId(funcId);
		return redirectTo("/uitool.do?method=list");
	}
	/**
     * 进入列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=list", method = RequestMethod.GET)
    public String list(ModelMap model) {
    	List<UitoolFuncDisplayInfo> funcDisplayInfoList = funcDisplayInfoService.list();
    	model.put("funcDisplayInfoList", funcDisplayInfoList);
        return "/uitool/list";
    }
    /**
     * 进入表格维护页面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=table", method = RequestMethod.GET)
    public String table(Long funcId, String tableName, ModelMap model, HttpServletRequest req) {
    	Connection connection = null;
    	List<UitoolListDisplayField> lines = new ArrayList<UitoolListDisplayField>();
    	List<UitoolListDisplayField> listByFuncId = listDisplayFieldService.getListByFuncId(funcId);
    	List<String> tableNameList = new ArrayList<String>();
    	if(StringUtils.isEmpty(tableName)){
    		UitoolFuncTable funcTable = funcTableService.selectByFuncId(funcId);
    		if(funcTable != null){
    			tableName = funcTable.getTableName();
    		}
    	}
    	if(ListUtils.isNotEmpty(listByFuncId)){
    		//直接从配置表中取数据
    		lines = listByFuncId;
    	} else {
    		if(!StringUtils.isEmpty(tableName)){
    			try {
    				//从业务表中构建
    				Context context = new InitialContext();
    				DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/OssDatabase");
    				connection = source.getConnection();
    				DatabaseMetaData metaData = connection.getMetaData();
    				ResultSet tables = metaData.getColumns(null, "%", tableName, "%");
    				lines = new ArrayList<UitoolListDisplayField>();
    				while(tables.next()){
    					String isDisplay = "1";
    					String isFind = "1";
    					String isOrder = "0";
    					int searchType = 0;
    					String columnName = tables.getString("COLUMN_NAME");
    					if(columnName.endsWith("_SN") || columnName.endsWith("_sn") || columnName.equals("SN")){
    						isDisplay = "0";
    						isFind = "0";
    						//isOrder = "0";
    					}
    					if(columnName.endsWith("_TIME") || columnName.endsWith("_time")){
    						searchType = 4;
    					}
    					String remarks = tables.getString("REMARKS");
    					UitoolListDisplayField displayField = new UitoolListDisplayField();
    					displayField.setFuncId(funcId);
    					displayField.setFieldName(columnName.toUpperCase());
    					displayField.setDisplayText(StringUtils.limitMsgShowLength(remarks, 5));
    					displayField.setRemarks(remarks);
    					displayField.setIsDisplay(isDisplay);
    					displayField.setIsFind(isFind);
    					displayField.setIsOrder(isOrder);
    					displayField.setFindType(searchType);
    					lines.add(displayField);
    				}
    				if(!lines.isEmpty()){
    					UitoolListDisplayField displayField = new UitoolListDisplayField();
    					displayField.setFuncId(funcId);
    					displayField.setFieldName("_oper_");
    					displayField.setDisplayText("新建;修改;删除");
    					displayField.setIsDisplay("0");
    					displayField.setIsFind("0");
    					displayField.setIsOrder("0");
    					displayField.setRemarks("操作");
    					lines.add(displayField);
    				}
    			} catch (Exception e) {
    				e.printStackTrace();
    			} finally {
    				if(connection != null){
    					try {
    						connection.close();
    					} catch (SQLException e) {
    						e.printStackTrace();
    					}
    				}
    			}
    		}
    	}
    	Object attribute = req.getSession().getAttribute("tableNameList");
    	if(attribute == null){
			//列出表名供选择
			try {
				Context context = new InitialContext();
				DataSource source = (DataSource) context.lookup("java:comp/env/jdbc/OssDatabase");
				connection = source.getConnection();
				DatabaseMetaData metaData = connection.getMetaData();
				ResultSet rs = metaData.getTables(null, null, null, new String[] { "TABLE" });
				while (rs.next()) {
					String tName = rs.getString(3);
			     	if(!tName.startsWith("oss") && !tName.startsWith("uitool")){
			     		tableNameList.add(tName);
			     	}
				}
				model.addAttribute("tableNameList", tableNameList);
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if(connection != null){
					try {
						connection.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

    	}
    	if(StringUtils.isNotEmpty(tableName)){
			req.getSession().getServletContext().getRealPath("/");
			File configFile = new File(SystemTool.getProperty(NLGlobal.APP_REAL_PATH) + "/WEB-INF/config/extend-configs/");
			if(configFile != null){
				if(configFile.isDirectory()){
					File[] listFiles = configFile.listFiles();
					for (File file : listFiles) {
						if(file.isFile() && file.getName().contains(tableName)){
							log.debug(file.getPath());
							try {
								FileInputStream fr = new FileInputStream(file);
								try {
									StringBuffer sb = new StringBuffer();
									byte[] bty = new byte[fr.available()];
									while(fr.read(bty) != -1){
										sb.append(new String(bty));
									}
									log.debug(sb.toString());
									model.addAttribute("sql", sb.toString());
									model.addAttribute("sqlFileName", file.getName());
								} catch (Exception e) {
									e.printStackTrace();
								} finally {
									if(fr != null){
										fr.close();
									}
								}
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						}
					}
				}
			}
		}
    	model.addAttribute("lines", lines);
    	model.addAttribute("tableName", tableName);
    	model.addAttribute("funcId", funcId);
        return "/uitool/table/modify";
    }

    /**
     * 进入表格维护页面
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=table", method = RequestMethod.POST)
    public String tableAdd(UitoolFuncDisplayInfo funcDisplayInfo, String tableName, int isCreateConfigXml, ModelMap model) {
    	long funcId = funcDisplayInfo.getFuncId();
    	if(funcId > 0){
    		if(StringUtils.isNotEmpty(tableName)){
    			funcTableService.deleteByFuncId(funcId);
    			UitoolFuncTable uitoolFuncTable = new UitoolFuncTable();
				uitoolFuncTable.setFuncId(funcId);
				uitoolFuncTable.setTableName(tableName);
				funcTableService.save(uitoolFuncTable);
				funcDisplayInfoService.saveFuncListFields(funcDisplayInfo);
				if(isCreateConfigXml > 0){
					createConfigFile(TCUtil.sv(funcId), tableName, funcDisplayInfo);
				}
    		} else {
    			throw new IllegalArgumentException("表名称不能为空");
    		}
    	} else {
    		throw new IllegalArgumentException("funcId不能为空");
    	}
    	menuTree.refresh();
    	//model.addAttribute("lines", funcDisplayInfo.getDisplayFields());
		//model.addAttribute("funcId", funcId);
    	//model.addAttribute("tableName", tableName);
        return redirectTo("/uitool.do?method=table&funcId=" + funcId + "&tableName=" + tableName);
    }

	/**
	 * 更新列表查询sql
	 *
	 * @param sql
	 * @param sqlFileName
	 * @param funcId
	 * @param tableName
	 * @return
	 */
	@RequestMapping(params = "method=updateSql", method = RequestMethod.POST)
	public String updateSql(String sql, String sqlFileName, Long funcId, String tableName) {
		File configFile = new File(SystemTool.getProperty(NLGlobal.APP_REAL_PATH) + "/WEB-INF/config/extend-configs/" + sqlFileName);
		if (configFile.exists()) {
			try {
				FileWriter fileWriter = new FileWriter(configFile);
				try {
					fileWriter.write(sql);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (fileWriter != null) {
						fileWriter.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return redirectTo("/uitool.do?method=table&funcId=" + funcId + "&tableName=" + tableName);
	}

	/**
	 * 增加自定义列
	 * *
	 */
	@RequestMapping(params = "method=addLine", method = RequestMethod.POST)
	public String addLine(UitoolListDisplayField field, String tableName) {
		Long funcId = field.getFuncId();
		String fieldName = field.getFieldName();
		String displayName = field.getDisplayText();
		if (funcId != null && StringUtils.isNotEmpty(fieldName) && StringUtils.isNotEmpty(displayName)) {
			listDisplayFieldService.insertOrUpdate(field);
		}
		return redirectTo("/uitool.do?method=table&funcId=" + funcId + "&tableName=" + tableName);
	}
    /**
     * 导出数据库脚本
     *
     * @param model
     * @return
     */
    @RequestMapping(params = "method=exportSql", method = RequestMethod.GET)
    public String exportSql(Long funcId, ModelMap model) {
    	String deleteSql= funcDisplayInfoService.exportDeleteListSql(funcId);
    	String insertSql = funcDisplayInfoService.exportInsertListSql(funcId);
    	model.addAttribute("exportSql", deleteSql + insertSql);
    	return "/uitool/exportSql";
    }
    /**
     * 创建配置文件
     * *
     * @param funcId
     */
	private void createConfigFile(String funcId, String tableName, UitoolFuncDisplayInfo funcDisplayInfo) {
		//保存配置文件 命名规范 function-conifg-id.xml
    	String templateFile = "function-config-template.xml";
    	String appRealPath = SystemTool.getProperty(NLGlobal.APP_REAL_PATH);
		String listConfigCate = appRealPath + "/WEB-INF/config/extend-configs/";
		String templatePath = appRealPath + "/WEB-INF/config/" + templateFile;
		String newFileName = templateFile.replace("template", tableName + "_" + funcId);
		try {
			String content = FileUtils.readFileToString(new File(templatePath));
			content = content.replace("AAAA", funcId).replace("BBBB", tableName).replace("CCCC", buildSql(funcDisplayInfo, tableName));
			FileUtils.writeStringToFile(new File(listConfigCate + newFileName), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 *
	 * *
	 * @param funcDisplayInfo
	 * @param tableName
	 * @return
	 */
	private String buildSql(UitoolFuncDisplayInfo funcDisplayInfo, String tableName){
		if(ListUtils.isEmpty(funcDisplayInfo.getDisplayFields())){
			return "";
		}
		StringBuffer buffer = new StringBuffer("select \n");
		List<UitoolListDisplayField> displayFields = funcDisplayInfo.getDisplayFields();
		for (UitoolListDisplayField uitoolListDisplayField : displayFields) {
			String fieldName = uitoolListDisplayField.getFieldName();
			if("_oper_".equals(fieldName)){
				continue;
			}
			buffer.append(fieldName).append(",\n");
		}
		return buffer.substring(0, buffer.lastIndexOf(",")) + "\n from " + tableName;
	}
}
