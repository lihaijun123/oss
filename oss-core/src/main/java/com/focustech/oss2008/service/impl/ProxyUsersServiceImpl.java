package com.focustech.oss2008.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focustech.oss2008.dao.ProxyUserDao;
import com.focustech.oss2008.model.OssAdminProxyUser;
import com.focustech.oss2008.service.AbstractServiceSupport;
import com.focustech.oss2008.service.ProxyUsersService;

@Service
public class ProxyUsersServiceImpl extends AbstractServiceSupport implements ProxyUsersService {
    @Autowired
    private ProxyUserDao<OssAdminProxyUser> proxyusersDao;

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ProxyusersService#addProxyuser(com.focustech.oss2008.model.OssAdminProxyUser)
     */
    public void addProxyuser(OssAdminProxyUser OssAdminProxyUser) {
        OssAdminProxyUser.setCreatedTime(new Date());
        OssAdminProxyUser.setCreatorId(getLoginUser().getUserId());
        fillTheUpdateInfo(OssAdminProxyUser);
        proxyusersDao.insert(OssAdminProxyUser);
    }

    /*
     * (non-Javadoc)
     * @see
     * com.focustech.oss2008.service.ProxyusersService#deleteProxyuser(com.focustech.oss2008.model.OssAdminProxyUser)
     */
    public void deleteProxyuser(OssAdminProxyUser OssAdminProxyUser) {
        proxyusersDao.delete(OssAdminProxyUser);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ProxyusersService#getAllProxyuser()
     */
    public List<OssAdminProxyUser> getAllProxyuser() {
        return proxyusersDao.selectAllProxyuser();
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ProxyusersService#getUsefulProxyuser()
     */
    public List<OssAdminProxyUser> getUsefulProxyuser() {
        return proxyusersDao.selectUsefulProxyuser();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.focustech.oss2008.service.ProxyusersService#modifyProxyuser(com.focustech.oss2008.model.OssAdminProxyUser)
     */
    public void modifyProxyuser(OssAdminProxyUser ossAdminProxyUser) {
        OssAdminProxyUser proxyUser = proxyusersDao.select(ossAdminProxyUser.getRecordId());
        proxyUser.setUserId(ossAdminProxyUser.getUserId());
        proxyUser.setProxyId(ossAdminProxyUser.getProxyId());
        proxyUser.setStartTime(ossAdminProxyUser.getStartTime());
        proxyUser.setEndTime(ossAdminProxyUser.getEndTime());
        proxyUser.setActive(ossAdminProxyUser.getActive());
        proxyUser.setRemark(ossAdminProxyUser.getRemark());
        fillTheUpdateInfo(ossAdminProxyUser);
        proxyusersDao.update(ossAdminProxyUser);
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ProxyusersService#getProxyuserByRecordId(java.lang.String)
     */
    public OssAdminProxyUser getProxyuserByRecordId(String proxyId) {
        return proxyusersDao.select(Long.parseLong(proxyId));
    }

    /*
     * (non-Javadoc)
     * @see com.focustech.oss2008.service.ProxyusersService#getDisabledProxyusers()
     */
    public List<OssAdminProxyUser> getDisabledProxyusers() {
        return proxyusersDao.selectDisabledProxyuser();
    }

    /**
     * 用于填充更新信息
     *
     * @param OssAdminProxyUser
     */
    private void fillTheUpdateInfo(OssAdminProxyUser OssAdminProxyUser) {
        OssAdminProxyUser.setModifierTime(new Date());
        OssAdminProxyUser.setModifierId(getLoginUser().getUserId());
    }
}
