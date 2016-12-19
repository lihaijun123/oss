package com.focustech.table.engine.impl;

import java.util.HashMap;
import java.util.Map;

import com.focustech.table.engine.DataConstructEngine;
import com.focustech.table.engine.DataConstructEngineFactory;

/**
 * <li>the implementation of DataConstructEngineFactory</li>
 * <p>
 * the all data constract engine is saved in a HashMap. use the unique name of them to get it.
 *
 * @author MagicYang 2007-4-9 下午01:16:52 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public class DataConstructEngineFactoryBean implements DataConstructEngineFactory {
    private Map<String, DataConstructEngine> dataConstructEngines = new HashMap<String, DataConstructEngine>();

    /*
     * (non-Javadoc)
     * @see net.magicyang.tabletool.engine.DataConstructEngineFactory#findDataConstructEngine(java.lang.String)
     */
    public DataConstructEngine findDataConstructEngine(String engineName) {
        return dataConstructEngines.get(engineName);
    }

    /*
     * (non-Javadoc)
     * @see net.magicyang.tabletool.engine.DataConstructEngineFactory#registDataConstructEngine(java.lang.String,
     * net.magicyang.tabletool.engine.DataConstructEngine)
     */
    public void registDataConstructEngine(String engineName, DataConstructEngine dataConstructEngine) {
        dataConstructEngines.put(engineName, dataConstructEngine);
    }

    /**
     * @return the dataConstructEngines
     */
    public Map<String, DataConstructEngine> getDataConstructEngines() {
        return dataConstructEngines;
    }

    /**
     * @param dataConstructEngines the dataConstructEngines to set
     */
    public void setDataConstructEngines(Map<String, DataConstructEngine> dataConstructEngines) {
        this.dataConstructEngines = dataConstructEngines;
    }
}
