package com.focustech.table.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.common.utils.FileUtils;
import com.focustech.common.utils.XmlUtil;
import com.focustech.oss2008.model.Column;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.model.SearchCondition;
import com.focustech.oss2008.model.TableConfig;
import com.focustech.oss2008.service.OssAdminParameterService;
import com.focustech.table.engine.TableConfigueEngine;
import com.focustech.table.exception.NoSuchTableConfigurationException;

/**
 * <li>XML表格配置引擎</li>
 *
 * @author MagicYang 2007-1-24 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public class XmlTableConfigueEngine implements TableConfigueEngine {
    @Autowired
    OssAdminParameterService baseParametersService;
    /** 存放緩存數據 */
    private static Map<String, TableConfig> cacheMap = null;
    private String filePath = "";
    private String fileSuffix = "xml";
    /** 配置屬性:表格名 */
    public static final String TABLE_ATTRIBUTE_NAME = "name";
    /** 配置屬性:表格顯示名 */
    public static final String TABLE_ATTRIBUTE_DISPLAY_NAME = "displayName";
    /** 配置屬性:表格寬 */
    public static final String TABLE_ATTRIBUTE_WIDTH = "width";
    /** 配置屬性:表格高 */
    public static final String TABLE_ATTRIBUTE_HEIGHT = "height";
    /** 配置屬性:數據源 */
    public static final String TABLE_ATTRIBUTE_DATA_SOURCE = "dataSource";
    /** 配置屬性:數據庫域 */
    public static final String TABLE_ATTRIBUTE_SCHEMA = "schema";
    /** 配置屬性:數據構造引擎 */
    public static final String TABLE_ATTRIBUTE_DATA_CONSTRUCT_ENGINE = "dataConstructEngine";
    /** 配置屬性:搜索條件 */
    public static final String TABLE_ATTRIBUTE_WHERE = "where";
    /** 配置屬性:初始化時搜索條件 */
    public static final String TABLE_ATTRIBUTE_INIT_WHERE = "initWhere";
    /** 配置屬性:初始化時排序條件 */
    public static final String TABLE_ATTRIBUTE_INIT_ORDER = "initOrder";
    /** 配置屬性:每頁顯示數據量 */
    public static final String TABLE_ATTRIBUTE_PAGE_DATA_SIZE = "pageDataSize";
    //
    /** 配置元素:列 */
    public static final String ELEMENT_COLUMN = "column";
    /** 配置元素:列 */
    public static final String ELEMENT_COLUMNS = "columns";
    /** 配置屬性:列排序 */
    public static final String COLUMN_ATTRIBUTE_SORTABLE = "sortable";
    /** 配置屬性:列類型 */
    public static final String COLUMN_ATTRIBUTE_TYPE = "type";
    /** 配置屬性:列顯示類型 */
    public static final String COLUMN_ATTRIBUTE_DISPLAY_TYPE = "displayType";
    /** 配置屬性:列搜索類型 */
    public static final String COLUMN_ATTRIBUTE_SEARCH_TYPE = "searchType";
    /** 配置屬性:列是否可搜索 */
    public static final String COLUMN_ATTRIBUTE_SEARCHALE = "searchable";
    /** 配置屬性:列url */
    public static final String COLUMN_ATTRIBUTE_URL = "url";
    public static final String COLUMN_ATTRIBUTE_NBAME_URL = "nameUrl";
    /** 配置屬性:列名稱 */
    public static final String COLUMN_ATTRIBUTE_NAME = "name";
    /** 配置屬性:列名稱 */
    public static final String COLUMN_ATTRIBUTE_DISPLAY_NAME = "displayName";
    /** 配置屬性:列排序 */
    public static final String COLUMN_ATTRIBUTE_ORDER = "order";
    /** 配置屬性:列搜索條件 */
    public static final String COLUMN_ATTRIBUTE_SEARCHCONDITION = "searchCondition";
    /** 配置屬性:值集合名稱 */
    public static final String COLUMN_ATTRIBUTE_VALUE_SET_NAME = "valueSetName";
    /** 配置屬性:本列是否顯示 */
    public static final String COLUMN_ATTRIBUTE_VISIBLE = "visible";
    /** 配置屬性:本列列名鏈接 */
    public static final String COLUMN_ATTRIBUTE_NAME_URL = "nameUrl";
    /** 配置屬性:列寬 */
    public static final String COLUMN_ATTRIBUTE_WIDTH = "width";
    //
    /** 配置元素:列搜索條件 */
    public static final String ELEMENT_SEARCHCONDITION = "searchCondition";
    /** 配置屬性:搜索條件名稱 */
    public static final String SEARCHCONDITION_ATTRIBUTE_NAME = "name";
    /** 配置屬性:搜索條件顯示名稱 */
    public static final String SEARCHCONDITION_ATTRIBUTE_DISPLAY_NAME = "displayName";
    /** 配置屬性:搜索條件默認值 */
    public static final String SEARCHCONDITION_ATTRIBUTE_DEFAULT_VALUE = "defaultValue";
    /** 配置屬性:參數表 */
    public static final String COLUMN_ATTRIBUTE_PARAMETER_NAME = "parameterName";
    /** 動作 查看 修改 刪除 */
    public static final String ACTION = "action";
    public static final String VIEW_ACTION = "view";
    public static final String MODIFY_ACTION = "modify";
    public static final String DELEATEACTION = "delete";
    /** 批量刪除 */
    public static final String BULK_DELEATE_ACTION = "bulkDelete";
    /** 列的外鍵引用 */
    public static final String FOREIGN_REFERENCE = "foreignTable";
    /** 外鍵引用的表名 */
    public static final String FOREIGN_REFERENCE_ATTRIBUTE_NAME = "name";
    /** 外鍵引用的表的字段 */
    public static final String FOREIGN_REFERENCE_ATTRIBUTE_KEY = "key";
    /** 外鍵引用的表名的值 */
    public static final String FOREIGN_REFERENCE_ATTRIBUTE_VALUE = "value";

    /** 根據文件名取得列表配置信息TableConfig */
    public TableConfig getTableConf(String name) {
        Document document;
        TableConfig tableConfig = null;
        try {
            document = XmlUtil.readXmlFile(name);
            Iterator<Element> itrs = XmlUtil.findElementIterator(document, "table");
            while (itrs.hasNext()) {
                Element childElement = (Element) itrs.next();
                tableConfig = getTableConfig(childElement);
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return tableConfig;
    }

    /** 根據文件名取得列表配置信息TableConfig */
    public List<TableConfig> getTableConfList(String name) {
        Document document;
        List<TableConfig> list = new ArrayList();
        TableConfig tableConfig = null;
        try {
            document = XmlUtil.readXmlFile(name);
            Iterator<Element> itrs = XmlUtil.findElementIterator(document, "table");
            while (itrs.hasNext()) {
                Element childElement = (Element) itrs.next();
                tableConfig = getTableConfig(childElement);
                list.add(tableConfig);
            }
        }
        catch (DocumentException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根據文件名取得列表配置信息TableConfig
     */
    public TableConfig getTableConfig(Element element) {
        TableConfig tableConfig = new TableConfig();
        String name = element.attributeValue(TABLE_ATTRIBUTE_NAME);
        String displayName = element.attributeValue(TABLE_ATTRIBUTE_DISPLAY_NAME);
        String width =
                element.attributeValue(TABLE_ATTRIBUTE_WIDTH) == null ? "0" : element
                        .attributeValue(TABLE_ATTRIBUTE_WIDTH);
        String height =
                element.attributeValue(TABLE_ATTRIBUTE_HEIGHT) == null ? "0" : element
                        .attributeValue(TABLE_ATTRIBUTE_HEIGHT);
        String pageDadaSize =
                element.attributeValue(TABLE_ATTRIBUTE_PAGE_DATA_SIZE) == null ? ""
                        + TableConfig.DEFAULT_PAGE_DATA_SIZE : element.attributeValue(TABLE_ATTRIBUTE_PAGE_DATA_SIZE);
        String dataSource =
                element.attributeValue(TABLE_ATTRIBUTE_DATA_SOURCE) == null ? "" : element
                        .attributeValue(TABLE_ATTRIBUTE_DATA_SOURCE);
        String schema =
                element.attributeValue(TABLE_ATTRIBUTE_SCHEMA) == null ? "" : element
                        .attributeValue(TABLE_ATTRIBUTE_SCHEMA);
        tableConfig.setName(name);
        tableConfig.setDisplayName(displayName);
        tableConfig.setWidth(Integer.parseInt(width));
        tableConfig.setHeight(Integer.parseInt(height));
        tableConfig.setSchema(schema);
        tableConfig.setDataSource(dataSource);
        tableConfig.setPageDataSize(Integer.parseInt(pageDadaSize));
        List<Element> list2 = element.elements(ELEMENT_COLUMNS);
        if (list2.size() > 0) {
            Element emnt = (Element) list2.get(0);
            List<Element> list = emnt.elements(ELEMENT_COLUMN);
            List<Column> columns = new ArrayList();
            int size = list.size();
            int i = 0;
            for (i = 0; i < size; i++) {
                Column column = getColumn((Element) list.get(i));
                String parameterName = column.getParameterName();
                if (parameterName != null && !parameterName.equals("")) {
                    List<OssAdminParameter> parameter = baseParametersService.listParameters(parameterName);
                    column.setParameter(parameter);
                }
                columns.add(column);
            }
            tableConfig.setColumns(columns);
        }
        List<Element> listAction = element.elements(ACTION);
        int actionSize = listAction.size();
        if (actionSize > 0) {
            Element action = listAction.get(0);
            String delete = action.attributeValue(this.DELEATEACTION);
            String view = action.attributeValue(this.VIEW_ACTION);
            String modify = action.attributeValue(this.MODIFY_ACTION);
            String bulkDelete = action.attributeValue(this.BULK_DELEATE_ACTION);
            if (delete != null && !delete.equals("")) {
                tableConfig.setDeleteFlag(true);
                tableConfig.setDeleteUrl(delete);
            }
            if (view != null && !view.equals("")) {
                tableConfig.setViewFlag(true);
                tableConfig.setViewUrl(view);
            }
            if (modify != null && !modify.equals("")) {
                tableConfig.setModifyFlag(true);
                tableConfig.setModifyUrl(modify);
            }
            if (bulkDelete != null && !bulkDelete.equals("")) {
                tableConfig.setBulkDeleteFlag(true);
                tableConfig.setBulkDeleteUrl(bulkDelete);
                String url = "";
                if (bulkDelete.indexOf("~") > 0) {
                    int left = bulkDelete.indexOf("~");
                    int right = bulkDelete.lastIndexOf("~");
                    String IDRef = bulkDelete.substring(left + 1, right);
                    tableConfig.setBulkDeleteField(IDRef);
                }
            }
        }
        List<Column> columns = tableConfig.getVisibleFieldColumn();
        int size = columns.size();
        tableConfig.configAction();
        tableConfig.configHiddenColumns();
        tableConfig.configTableNames();
        return tableConfig;
    }

    public TableConfig getTableConfig(String tableName) throws NoSuchTableConfigurationException {
        TableConfig tableConfig = (TableConfig) cacheMap.get(tableName);
        return tableConfig;
    }

    public Column getColumn(Element element) {
        Column column = new Column();
        String name = element.attributeValue(COLUMN_ATTRIBUTE_NAME);
        column.setName(name);
        String visible =
                element.attributeValue(COLUMN_ATTRIBUTE_VISIBLE) == null ? "true" : element
                        .attributeValue(COLUMN_ATTRIBUTE_VISIBLE);
        String sortable =
                element.attributeValue(COLUMN_ATTRIBUTE_SORTABLE) == null ? "true" : element
                        .attributeValue(COLUMN_ATTRIBUTE_SORTABLE);
        String searchable =
                element.attributeValue(COLUMN_ATTRIBUTE_SEARCHALE) == null ? "true" : element
                        .attributeValue(COLUMN_ATTRIBUTE_SEARCHALE);
        String displayType =
                element.attributeValue(COLUMN_ATTRIBUTE_DISPLAY_TYPE) == null ? "input" : element
                        .attributeValue(COLUMN_ATTRIBUTE_DISPLAY_TYPE);
        String url =
                element.attributeValue(COLUMN_ATTRIBUTE_URL) == null ? "" : element
                        .attributeValue(COLUMN_ATTRIBUTE_URL);
        String order =
                element.attributeValue(COLUMN_ATTRIBUTE_ORDER) == null ? "" : element
                        .attributeValue(COLUMN_ATTRIBUTE_ORDER);
        String searchType =
                element.attributeValue(COLUMN_ATTRIBUTE_SEARCH_TYPE) == null ? "input" : element
                        .attributeValue(COLUMN_ATTRIBUTE_SEARCH_TYPE);
        String type =
                element.attributeValue(COLUMN_ATTRIBUTE_TYPE) == null ? "String" : element
                        .attributeValue(COLUMN_ATTRIBUTE_TYPE);
        String nameUrl =
                element.attributeValue(COLUMN_ATTRIBUTE_NBAME_URL) == null ? "" : element
                        .attributeValue(COLUMN_ATTRIBUTE_NBAME_URL);
        String parameterName =
                element.attributeValue(COLUMN_ATTRIBUTE_PARAMETER_NAME) == null ? "" : element
                        .attributeValue(COLUMN_ATTRIBUTE_PARAMETER_NAME);
        String width =
                element.attributeValue(COLUMN_ATTRIBUTE_WIDTH) == null ? "80" : element
                        .attributeValue(COLUMN_ATTRIBUTE_WIDTH);
        String displayName =
                element.attributeValue(COLUMN_ATTRIBUTE_DISPLAY_NAME) == null ? name : element
                        .attributeValue(COLUMN_ATTRIBUTE_DISPLAY_NAME);
        column.setVisible(visible.equals("true"));
        column.setSortable(sortable.equals("true"));
        column.setSearchable(searchable.equals("true"));
        column.setDisplayType(displayType);
        column.setUrl(url);
        // column.setOrder(order);
        column.setSearchType(searchType);
        column.setNameUrl(nameUrl);
        column.setParameterName(parameterName);
        column.setWidth(width);
        column.setDisplayName(displayName);
        column.setType(type);
        if (column.isSearchable()) {
            List<Element> list = element.elements("searchCondition");
            int size = list.size();
            int i = 0;
            SearchCondition[] searchConditions = new SearchCondition[size];
            for (i = 0; i < size; i++) {
                Element emnt = (Element) list.get(i);
                String key =
                        emnt.attributeValue(SEARCHCONDITION_ATTRIBUTE_NAME) == null ? "equal" : emnt
                                .attributeValue(SEARCHCONDITION_ATTRIBUTE_NAME);
                String dispName =
                        emnt.attributeValue(SEARCHCONDITION_ATTRIBUTE_DISPLAY_NAME) == null ? displayName : emnt
                                .attributeValue(SEARCHCONDITION_ATTRIBUTE_DISPLAY_NAME);
                String defaultValue =
                        emnt.attributeValue(SEARCHCONDITION_ATTRIBUTE_DEFAULT_VALUE) == null ? "" : emnt
                                .attributeValue(SEARCHCONDITION_ATTRIBUTE_DEFAULT_VALUE);
                searchConditions[i] = new SearchCondition();
                searchConditions[i].setKey(key);
                searchConditions[i].setDisplayName(dispName);
                searchConditions[i].setDefaultValue(defaultValue);
            }
            column.setSearchConditions(searchConditions);
        }
        List<Element> foreignElements = element.elements(this.FOREIGN_REFERENCE);
        int size = foreignElements.size();
        if (size == 1) {
            column.setForeignTableFlag(true);
            Element emnt = (Element) foreignElements.get(0);
            String foreignTableName = emnt.attributeValue(this.FOREIGN_REFERENCE_ATTRIBUTE_NAME);
            String foreignTableKey = emnt.attributeValue(this.FOREIGN_REFERENCE_ATTRIBUTE_KEY);
            String foreignTableValue = emnt.attributeValue(this.FOREIGN_REFERENCE_ATTRIBUTE_VALUE);
            column.setForeignTableName(foreignTableName);
            column.setForeignTableKey(foreignTableKey);
            column.setForeignTableValue(foreignTableValue);
        }
        return column;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.TableConfigService#init()
     */
    public void init() {
        cacheMap = new HashMap<String, TableConfig>();
        Map<String, String> fileMap = FileUtils.getFiles(filePath, fileSuffix);
        Set keySet = fileMap.keySet();
        Iterator iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            String value = (String) fileMap.get(key);
            List<TableConfig> list = getTableConfList(value);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TableConfig tableConfig = (TableConfig) list.get(i);
                cacheMap.put(tableConfig.getName(), tableConfig);
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.TableConfigService#refreshAll()
     */
    public void refreshAll() {
        init();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public OssAdminParameterService getBaseParametersService() {
        return baseParametersService;
    }

    public void setBaseParametersService(OssAdminParameterService baseParametersService) {
        this.baseParametersService = baseParametersService;
    }
}
