package com.jason.data.generator.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 对象生成器抽象类
 * @author Administrator
 *
 * @param <T>
 */
public abstract class Generator<T> implements IGenerator<T>{
	
	protected Class<T> classType;
	
	@SuppressWarnings("unchecked")
	public Generator() {
		ParameterizedType pType = (ParameterizedType)this.getClass().getGenericSuperclass();
		Type arguType = pType.getActualTypeArguments()[0];
		this.classType = (Class<T>)arguType;
	}
	
	public Class<T> getClassType() {
		return classType;
	}

}
