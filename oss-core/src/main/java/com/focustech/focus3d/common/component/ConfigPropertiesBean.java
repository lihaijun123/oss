package com.focustech.focus3d.common.component;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import com.focustech.common.utils.PropertyUtils;
import com.focustech.common.utils.StringUtils;

/**
 * 配置属性文件(涉及到系统发布需要修改到的配置统一由此类获取)
 * *
 * @author lihaijun
 *
 */
public class ConfigPropertiesBean {

	protected static Log log = LogFactory.getLog(ConfigPropertiesBean.class);

	private static Properties configPro = null;
    //
    private Resource configResource = null;

    /**
     * 容器启动时调用该方法，初始化读入验证、异常 资源文件
     *
     * @throws IOException
     */
    public synchronized void init() throws IOException {
        // TODO:将默认信息与项目信息分开，对于一类资源需要读入多个资源文件
        Assert.notNull(configResource);
        configPro = new Properties();
        configPro = PropertyUtils.readFromResource(configResource);
    }

    /**
     * 供取得字段名称调用，根据key，取得对应value
     *
     * @param key 需要读取字段
     * @return value
     */
    public static String getValue(String key) {
        return getPropertyValue(configPro, key);
    }

    protected static String getPropertyValue(Properties properties, String key) {
        if (StringUtils.isEmpty(key)) {
            return "";
        }
        String value = properties.getProperty(key);
        if (null == value) {
            log.warn("the key :" + key + " has no value;please check the properties file first!");
            value = "";
        }
        return value;
    }

	public static Properties getConfigPro() {
		return configPro;
	}

	public static void setConfigPro(Properties configPro) {
		ConfigPropertiesBean.configPro = configPro;
	}

	public Resource getConfigResource() {
		return configResource;
	}

	public void setConfigResource(Resource configResource) {
		this.configResource = configResource;
	}
}
