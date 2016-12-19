package com.focustech.oss2008.model;

import java.util.ArrayList;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class AutoArrayList<T> extends ArrayList<T>{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Class itemClass;

	public AutoArrayList(Class itemClass){
		this.itemClass = itemClass;
	}

	@Override
	public T get(int index) {
		try {
			while(index >= size()){
				add((T) itemClass.newInstance());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.get(index);
	}



}
