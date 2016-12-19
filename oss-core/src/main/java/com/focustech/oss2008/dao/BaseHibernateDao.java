package com.focustech.oss2008.dao;

import java.io.Serializable;
import java.util.List;

/**
 * <li>Hibernate 基本方法支持</li>
 *
 * @author yangpeng 2008-5-12 下午05:20:16
 */
public interface BaseHibernateDao<T> {
    public void insert(T t);

    public void update(T t);

    public void delete(Serializable id);

    public void delete(T t);

    public T select(Serializable id);

    /**
     * hibernate 悲觀鎖定後取數據的方法。用于高並發要求的場景。
     * <p>
     * 調用此方法後得到的實體，被數據庫鎖定，其他並行請求想得到此實體必須等待。
     *
     * @param id 實體主鍵
     * @return 被鎖定的實體。
     */
    public T pessimisticSelect(Serializable id);

    /**
     * 當保存的對象是一個新對象，即沒有在數據庫中出現過，則會將其保存在數據庫中； <br>
     * 如果沒有將其與當前hibernate session綁定，從新接受hibernate管理。
     *
     * @param t
     */
    public void insertOrUpdate(T t);

    /**
     * 執行之前程序對數據庫的修改
     * <p>
     * 請注意，在當前數據庫鏈接中的所有數據庫更改都會寫入數據庫，但是事務未提交，程序可以回滾。 <br>
     * 但是當前鏈接已經可以響應數據變化。
     */
    public void flush();
    
    public List<T> list();
}
