package com.focustech.crm.service.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.focustech.extend.spring.xmlrpc.annotation.XmlRpcService;
import com.focustech.oss2008.Constants;
import com.focustech.oss2008.model.OssAdminDepartment;
import com.focustech.oss2008.model.OssAdminUser;
import com.focustech.oss2008.service.DepartmentService;

/**
 * @author xufei
 */
@XmlRpcService
public class DepartmentFacade {
    protected Log log = LogFactory.getLog(Constants.LOG_ROOT_SERVICE);
    @Autowired
    private DepartmentService departmentService;

    public List<Map<String, String>> getAllSubDepartmentsByParentId(String departmentId) {
        List<Map<String, String>> list = new ArrayList();
        List<OssAdminDepartment> departmentList = departmentService.getAllSubDepartmentsByParentId(departmentId);
        for (OssAdminDepartment c : departmentList) {
            Map map = new HashMap();
            map.put("OrgNo", c.getDepartmentId());
            map.put("OrgName", c.getDepartmentName());
            map.put("OssAdmin", (OssAdminUser) c.getOssAdminUsers());
            for (OssAdminUser ossAdminUser : c.getOssAdminUsers()) {
                Map adminMap = new HashMap();
                map.put("adminMap", adminMap.put("userId", ossAdminUser.getUserId()));
                map.put("adminMap", adminMap.put("userName", ossAdminUser.getUsername()));
            }
            list.add(map);
        }
        return list;
    }

    public Map<String, String> getDepartmentNameById(String departmentId) {
        OssAdminDepartment department = departmentService.getDepartment(departmentId);
        if (null != department) {
            Map map = new HashMap();
            map.put("DEPARTMENT_NAME", department.getDepartmentName());
            return map;
        }
        return null;
    }

    /*******************************************************************************************************************
     * 取得集合部門編號中的所有部門的人員信息
     *
     * @param departmentList
     * @return
     */
    public LinkedHashMap<Integer, List<String>> getOssAdminUserByParentId(List departmentList) {
        LinkedHashMap<Integer, List<String>> usersInfo = new LinkedHashMap<Integer, List<String>>();
        // LinkedList<LinkedHashMap<String, String>> personList = new LinkedList();
        List<OssAdminUser> adminUserList = departmentService.getDepartmentUserListByIds(departmentList);
        // for (OssAdminUser c : adminUserList)
        // {
        // Map map = new HashMap();
        // map.put("OrgNo", c.getDepartmentId());
        // map.put("OrgName", c.getDepartmentName());
        // map.put("OssAdmin", (OssAdminUser)c.getOssAdminUsers());
        for (int i = 0; i < adminUserList.size(); i++) {
            OssAdminUser ossAdminUser = adminUserList.get(i);
            List<String> list = new ArrayList();
            list.add(ossAdminUser.getUserId());
            list.add(ossAdminUser.getFullname());
            usersInfo.put(Integer.valueOf(i), list);
            // Map adminMap = new HashMap();
            // list.put("adminMap", adminMap.put("userId", ossAdminUser.getUserId()));
            // map.put("adminMap", adminMap.put("userName", ossAdminUser.getUsername()));
            // usersInfo.put(list, ossAdminUser.getFullname());
        }
        // list.add(map);
        // }
        // personList.add(usersInfo);
        return usersInfo;
    }

    /*******************************************************************************************************************
     * 取得集合部門編號中的所有部門的有效的人員信息
     *
     * @param departmentList
     * @return
     */
    public Map<String, String> getOssAdminUserAbleUserByParentId(List departmentList) {
        log.error("enter getOssAdminUserAbleUserByParentId");
        // Map<String, String> usersInfo = new HashMap<String, String>();
        return departmentService.getDepartmentUserAbleUserListByIds(departmentList);
        // for (OssAdminUser c : adminUserList)
        // {
        // Map map = new HashMap();
        // map.put("OrgNo", c.getDepartmentId());
        // map.put("OrgName", c.getDepartmentName());
        // map.put("OssAdmin", (OssAdminUser)c.getOssAdminUsers());
        // for (OssAdminUser ossAdminUser : adminUserList)
        // {
        // // Map adminMap = new HashMap();
        // // list.put("adminMap", adminMap.put("userId", ossAdminUser.getUserId()));
        // // map.put("adminMap", adminMap.put("userName", ossAdminUser.getUsername()));
        // usersInfo.put(ossAdminUser.getUserId(), ossAdminUser.getUsername());
        // }
        // list.add(map);
        // }
        // return usersInfo;
    }

    /**
     * 得到所有部門列表
     *
     * @return
     */
    public Map<String, List<String>> getAllDepartment() {
        Map<String, List<String>> departmentMap = new HashMap<String, List<String>>();
        List<OssAdminDepartment> departmentList = departmentService.getAllDepartments();
        if (null != departmentList && departmentList.size() > 0) {
            for (int i = 0; i < departmentList.size(); i++) {
                List<String> department = new ArrayList<String>();
                OssAdminDepartment c = (OssAdminDepartment) departmentList.get(i);
                // li.add(StringUtil.notNull(org.getFaxNo()));
                // li.add(StringUtil.notNull(org.getSuperOrgNo()));
                // li.add(StringUtil.notNull(org.getDefaultRoleCodeSet().toString()));
                // li.add(StringUtil.notNull(org.getStatus()));
                // li.add(StringUtil.notNull(org.getCreateTime()));
                // li.add(StringUtil.notNull(org.getUpdateTime()));
                department.add(c.getDepartmentId());
                department.add(c.getDepartmentType());
                if (null == c.getDepartmentName()) {
                    department.add("");
                }
                else {
                    department.add(c.getDepartmentName());
                }
                if (null == c.getDepartmentAddress()) {
                    department.add("");
                }
                else {
                    department.add(c.getDepartmentAddress());
                }
                department.add("");
                department.add("");
                if (null == c.getDepartmentAddress()) {
                    department.add("");
                }
                else {
                    department.add(c.getDepartmentFax());
                }
                if (null == c.getDepartmentAddress()) {
                    department.add("");
                }
                else {
                    department.add(c.getDepartmentParentId());
                }
                department.add("");
                department.add(c.getActive());
                department.add("");
                department.add("");
                departmentMap.put(StringUtils.leftPad(String.valueOf(i), 10, "0"), department);
            }
            return departmentMap;
        }
        return null;
    }
}
