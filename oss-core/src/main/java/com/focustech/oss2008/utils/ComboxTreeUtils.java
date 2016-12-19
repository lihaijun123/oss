package com.focustech.oss2008.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.focustech.oss2008.model.TreeNode;

public class ComboxTreeUtils {
    public List<TreeNode> menuList = new ArrayList();
    private String className = null;
    public String serviceClassName = "";
    public String serviceMethod = "";
    public Object serviceObject = null;

    public ComboxTreeUtils() {
        super();
    }

    public ComboxTreeUtils(String className, String serviceClassName, String serviceMethod, Object serviceObject) {
        super();
        this.className = className;
        this.serviceClassName = serviceClassName;
        this.serviceMethod = serviceMethod;
        this.serviceObject = serviceObject;
    }

    /**
     * @param list
     * @throws throws OssCheckedException
     */
    public void createMenu(List list, String[] names, List aList) throws Exception {
        // 取得List中的每一項，並且name屬性值創建TreeNode
        int i = 0;
        String methodNames[] = new String[names.length];
        for (i = 0; i < names.length; i++) {
            String aName = names[i];
            String str = "get" + names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
            methodNames[i] = str;
        }
        for (i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Class cls = Class.forName(this.className);
            // id
            Method meth = cls.getMethod(methodNames[0], null);
            Object retobj = meth.invoke(obj, null);
            Long id = (Long) retobj;
            // 顯示名字
            meth = cls.getMethod(methodNames[1], null);
            retobj = meth.invoke(obj, null);
            String text = (String) retobj;
            TreeNode menu = new TreeNode();
            menu.setId(String.valueOf(id));
            menu.setText(text);
            menu.setLink(null);
            menu.setHref(null);
            // 默認信息
            menu.setLeaf(true);
            menu.setCls("file");
            menu.setChildren(null);
            aList.add(menu);
            // 對于每一個TreeNode
            createSubMenu(menu, names);

        }
    }

    private void createSubMenu(TreeNode menu, String[] names) throws Exception {
        Class cls = Class.forName(this.serviceClassName);
        Method meth = cls.getMethod(this.serviceMethod, new Class[]{Long.class});
        long id = Long.parseLong(menu.getId());
        Object arglist[] = new Object[1];
        arglist[0] = new Long(id);
        Object retobj = meth.invoke(this.getServiceObject(), arglist);
        List list = (List) retobj;
        // 1.如果不存在， 退出
        if (list == null || list.size() == 0) {
            menu.setLeaf(true);
            menu.setCls("file");
            menu.setChildren(null);
            return;
        }
        // 2. 如果存在， 設置為文件夾, 並取得下一級子結點， 繼續迭代
        else {
            menu.setLeaf(false);
            menu.setCls("folder");
            menu.setChildren(new ArrayList());
            this.createMenu(list, names, menu.getChildren());
        }
    }

    public String getAttribute(Object obj, String attribute) {
        String str = "";
        return str;
    }

    /**
     * 將目標java對象轉換成JSON格式的String
     */
    public String getJSONString() {
        JSONArray jsonObject = JSONArray.fromObject(this.menuList);
        return jsonObject.toString();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ComboxTreeUtils(String className) {
        super();
        this.className = className;
    }

    public String getServiceClassName() {
        return serviceClassName;
    }

    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }

    public String getServiceMethod() {
        return serviceMethod;
    }

    public void setServiceMethod(String serviceMethod) {
        this.serviceMethod = serviceMethod;
    }

    public Object getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(Object serviceObject) {
        this.serviceObject = serviceObject;
    }
}
