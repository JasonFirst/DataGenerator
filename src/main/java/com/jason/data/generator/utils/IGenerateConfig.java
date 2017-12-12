package com.jason.data.generator.utils;

public interface IGenerateConfig {

	/**
	 * 设置数组或者列表的生成数量<P/>
	 * set the count of array or collection for Generator
	 * 
	 * @param generateCount
	 */
	void setGenerateCount(Integer generateCount);

	/**
	 * 核心方法，添加一个对象生成器，按照类型分辨。<P/>
	 * put the generator for configuration by class.(which one in Generator<?>)
	 * 
	 * @param objectGenerator
	 */
	void putGenerator(Generator<?> objectGenerator);

	/**
	 * 核心方法，添加一个对象生成器，按照字段名分辨<P/>
	 * put the generator for configuration by field's name. 
	 * 
	 * @param fieldName
	 * @param objectGenerator
	 */
	void putGenerator(String fieldName, Generator<?> objectGenerator);

	/**
	 * 是否开启消息提示<P/>
	 * set whether need to open message's tip .<P/>
	 * just support system.out.println() now.
	 * 
	 * @param isOpenMessageTip
	 */
	void setOpenMessageTip(boolean isOpenMessageTip);

}
