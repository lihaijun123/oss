package com.focustech.table.engine;

/**
 * <li>the factory of data construct engine</li>
 * <p>
 * create data construct engine with the gaving engine name.
 * <p>
 * if none engine has the gaving name,it will return null.
 *
 * @author MagicYang 2007-1-18 下午02:47:11 <a href="mailto:ypypnj@gmail.com">contact Magic Yang</a>
 */
public interface DataConstructEngineFactory {
    /**
     * return a registed data construct engine with engineName(alias in the app).
     *
     * @param engineName name(alias) of registed construct engine.
     * @return a registed data construct engine or null.
     * @see DataConstructEngine
     */
    public abstract DataConstructEngine findDataConstructEngine(String engineName);

    /**
     * @param engineName name(alias) of registed construct engine.
     * @param dataConstructEngine the data construct engine.
     */
    public abstract void registDataConstructEngine(String engineName, DataConstructEngine dataConstructEngine);
}
