package com.jason.data.generator.type.base;

public interface IGenerator<T> {
	
	/**
	 * 生成一个T对象
	 * @param fieldName
	 * @return
	 */
	T generateData(String fieldName);
	
	
	/**
	 * 获得T的类型
	 * 如果懒得实现，可以直接继承Generator对象，自动帮你实现好。
	 * @return
	 */
	Class<T> getClassType();
}
