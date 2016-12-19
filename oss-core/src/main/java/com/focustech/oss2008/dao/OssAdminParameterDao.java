package com.focustech.oss2008.dao;

import java.util.List;

import com.focustech.oss2008.model.OssAdminParameter;

/**
 * *
 * 
 * @author lihaijun
 * @param <T>
 */
public interface OssAdminParameterDao<T> extends BaseHibernateDao<T> {
    /**
     * 取得所有参数列表
     */
    public List<OssAdminParameter> selectAllBaseParameters();

    /**
     * 根据参数类型取得一条参数信息集合
     */
    public List<OssAdminParameter> selectParameters(String parameterType);

    /**
     * 根据参数类型,站点类型取得一条参数信息集合
     */
    public List<OssAdminParameter> selectParameters(String parameterType, String siteType);

    /**
     * 根据参数类型、参数值和站点类型获取参数
     * 
     * @param parameterType 参数类型
     * @param parameterKey 参数值
     * @param website 站点类型
     * @return
     */
    public OssAdminParameter selectParameterByTypeKeyAndSite(String parameterType, String parameterKey, String website);

    /**
     * 获取省份列表
     * 
     * @param website 站点类型
     * @return
     */
    public List<OssAdminParameter> selectProvinceList(String website);

    /**
     * 根据参数类型与站点类型获取含有指定地区的所有参数列表（用于地区的三級联动）
     * 
     * @param parameterType 根据参数类型
     * @param parameterKey 指定地区
     * @param website 站点类型
     * @return
     */
    public List<OssAdminParameter> selectNextLevelAreaList(String parameterType, String parameterKey, String website);

    /**
     * 根据状态查询信息 *
     * 
     * @param parameterType 类型
     * @param status 状态
     * @param siteType 站点类型 默认1
     * @return
     */
    public List<OssAdminParameter> listParameters(String parameterType, String status, String siteType);

    /**
     * 通过parameterType删除记录 *
     * 
     * @param parameterType
     */
    public void deleteByParameterType(String parameterType);

    /**
     * 查询信息 *
     * 
     * @param parentParKey 父亲节点
     * @param parameterType 类型
     * @param status 状态
     * @param siteType 站点类型 默认1
     * @return
     */
    public List<OssAdminParameter> listParameters(String parentParKey, String parameterType, String status,
            String siteType);

    public OssAdminParameter getLikeValueWithTypeAndWibsite(String parameterValue, String type, String website);

    public OssAdminParameter getAdminEmail(String email);

    /**
     * 根据父亲节点、类型、key、状态和站点类型 查询信息 *
     * 
     * @param parentParKey 父亲节点
     * @param parameterType 类型
     * @param parameterKey
     * @param status 状态
     * @param siteType 站点类型 默认1
     * @return
     */
    public OssAdminParameter listParametersByTypeKeyParAndSite(String parentParKey, String parameterType,
            String parameterKey, String status, String siteType);
}
