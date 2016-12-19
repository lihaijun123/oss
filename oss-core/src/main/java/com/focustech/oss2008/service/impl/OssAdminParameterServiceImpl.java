package com.focustech.oss2008.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.Constants;
import com.focustech.oss2008.dao.OssAdminParameterDao;
import com.focustech.oss2008.exception.service.MailSendErrorException;
import com.focustech.oss2008.model.OssAdminParameter;
import com.focustech.oss2008.service.OssAdminParameterService;

/**
 * <li>参数组件</li>
 *
 * @author xufei 2008-4-17 下午14:25:39
 */
@Service
public class OssAdminParameterServiceImpl implements OssAdminParameterService {
    @Autowired
    OssAdminParameterDao<OssAdminParameter> parametersDao;

    /*
     * (non-Javadoc)
     * @see
     * com.focustech.oss2008.service.BaseParametersService#addParameter(com.focustech.oss2008.model.OssAdminParameter)
     */
    public void addParameter(OssAdminParameter parameter) {
        parametersDao.insert(parameter);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.BaseParametersService#getAllParameters()
     */
    public List<OssAdminParameter> getAllParameters() {
        return parametersDao.selectAllBaseParameters();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.focustech.oss2008.service.BaseParametersService#modifyParameter(com.focustech.oss2008.model.OssAdminParameter
     * )
     */
    public void modifyParameter(OssAdminParameter parameter) {
        parametersDao.update(parameter);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.OssAdminParameterService#listParameters(java.lang.String, java.lang.String)
     */
    public List<OssAdminParameter> listParameters(String parameterType, String siteType) {
        return parametersDao.selectParameters(parameterType, siteType);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.OssAdminParameterService#listParameters(java.lang.String)
     */
    public List<OssAdminParameter> listParameters(String parameterType) {
        return parametersDao.selectParameters(parameterType);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.OssAdminParameterService#selectParameterByTypeKeyAndSite(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public OssAdminParameter selectParameterByTypeKeyAndSite(String parameterType, String parameterKey, String website) {
		if (null != parameterKey)
			return parametersDao.selectParameterByTypeKeyAndSite(parameterType,
					parameterKey, website);
		else
			return null;
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.OssAdminParameterService#getReceiverAddressByOrgNo(java.lang.String)
     */
    public String getReceiverAddressByOrgNoSquare(String orgNo) throws MailSendErrorException {
        OssAdminParameter parameter =
                parametersDao.selectParameterByTypeKeyAndSite(
                        OssAdminParameterService.PARAMETER_KEY_SALEMAN_EMAIL_RECEIVER, orgNo,
                        Constants.SITE_TYPE_MIC_CN);
        if (null == parameter) {
            throw new MailSendErrorException("該合同已結清,合同對應的銷售人員不在當前發送銷售組當中,郵件無法發送！");
        }
        else {
            return parameter.getParameterValue();
        }
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.OssAdminParameterService#getReceiverAddressByOrgNoAttach(java.lang.String)
     */
    public String getReceiverAddressByOrgNoAttach(String orgNo) {
        OssAdminParameter parameter =
                parametersDao.selectParameterByTypeKeyAndSite(
                        OssAdminParameterService.PARAMETER_KEY_SALEMAN_EMAIL_RECEIVER, orgNo,
                        Constants.SITE_TYPE_MIC_CN);
        if (null == parameter) {
            return "";
        }
        else {
            return parameter.getParameterValue();
        }
    }

	@Override
	public List<OssAdminParameter> listParameters(String parameterType, String status, String siteType) {
		return parametersDao.listParameters(parameterType, status, siteType);
	}

	@Override
	public void deleteByParameterType(String parameterType) {
		parametersDao.deleteByParameterType(parameterType);
	}

	@Override
	public List<OssAdminParameter> listParameters(String parentParKey, String parameterType, String status, String siteType) {
		return parametersDao.listParameters(parentParKey, parameterType, status, siteType);
	}

    public OssAdminParameter listParametersByTypeKeyParAndSite(String parentParKey, String parameterType,
            String parameterKey, String status, String siteType) {
        return parametersDao.listParametersByTypeKeyParAndSite(parentParKey, parameterType, parameterKey, status,
                siteType);
    }

	@Override
	public OssAdminParameter selectDefaultParameterByTypeKey(
			String parameterType, String parameterKey) {
		if (null != parameterKey)
			return parametersDao.selectParameterByTypeKeyAndSite(parameterType,
					parameterKey, "1");
		else
			return null;
	}
}
