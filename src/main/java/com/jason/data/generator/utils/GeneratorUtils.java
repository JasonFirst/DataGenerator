package com.jason.data.generator.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象生成器工具类
 * @author Administrator
 *
 */
public class GeneratorUtils {
	
	public static <T> T getOne(Class<T> dataType){
		return getOne(dataType,new GenerateConfig());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getOne(Class<T> dataType,GenerateConfig generateConfig){
		GeneratorProcesser processer = new GeneratorProcesser();
		return (T) processer.generateObject(generateConfig,dataType);
	}
	
	public static <T> List<T> getList(Class<T> dataType){
		return getList(dataType,new GenerateConfig());
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getList(Class<T> dataType,GenerateConfig generateConfig){
		
		GeneratorProcesser processer = new GeneratorProcesser();
		
		List<T> results = new ArrayList<>();
		for (int i = 0; i < generateConfig.getGenerateCount(); i++) {
			T object = (T) processer.generateObject(generateConfig,dataType);
			results.add(object);
		}
		return results;
	}
}
