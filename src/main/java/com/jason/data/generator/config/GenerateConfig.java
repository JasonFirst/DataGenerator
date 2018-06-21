package com.jason.data.generator.config;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.jason.data.generator.type.BigDecimalGenerator;
import com.jason.data.generator.type.BooleanGenerator;
import com.jason.data.generator.type.ByteGenerator;
import com.jason.data.generator.type.DateGenerator;
import com.jason.data.generator.type.DoubleGenerator;
import com.jason.data.generator.type.FloatGenerator;
import com.jason.data.generator.type.IntegerGenerator;
import com.jason.data.generator.type.LongGenerator;
import com.jason.data.generator.type.ShortGenerator;
import com.jason.data.generator.type.StringGenerator;
import com.jason.data.generator.type.base.Generator;


/**
 * 生成器配置
 * @author Administrator
 *
 */
public class GenerateConfig implements IGenerateConfig{
	
	private int generateCount;
	private boolean isOpenMessageTip;
	private Map<String,Generator<?>> classToGenerators = new HashMap<>();
	private Map<String,Generator<?>> fieldNameToGenerators = new HashMap<>();

	public static GenerateConfig DEFAULT_CONFIG = new GenerateConfig();
	
	public GenerateConfig() {
		this.generateCount=3;
		setDefaultGenerators();
	}
	public GenerateConfig(Integer generateCount) {
		this.generateCount = generateCount;
		setDefaultGenerators();
	}

	private void setDefaultGenerators() {
		isOpenMessageTip =false;
		putGenerator(new StringGenerator(10));
		putGenerator(new IntegerGenerator(3000,8000));
		putGenerator(new LongGenerator(100000,30000000));
		putGenerator(new FloatGenerator(200.3f,3000.8f));
		putGenerator(new DoubleGenerator(2000000.5,3000000.7));
		putGenerator(new BooleanGenerator(20,80));
		putGenerator(new DateGenerator());
		putGenerator(new ByteGenerator());
		putGenerator(new ShortGenerator());
		putGenerator(new BigDecimalGenerator());
	}

	public int getGenerateCount() {
		return generateCount;
	}
	
	public Generator<?> getObjectGenerator(Class<?> classType, String fieldName){
		
		Class<?> wrapperClassType = ClassUtils.primitiveToWrapper(classType);
		
		if (StringUtils.isEmpty(fieldName)) {
			return classToGenerators.get(wrapperClassType.getName());
		}
		
		String fieldKey = fieldName+"_"+wrapperClassType.getName();
		Generator<?> generator = fieldNameToGenerators.get(fieldKey);
		if (Objects.nonNull(generator)) {
			return generator;
		}else {
			return classToGenerators.get(wrapperClassType.getName());
		}
	}
	
	public boolean isOpenMessageTip() {
		return isOpenMessageTip;
	}
	
	
	@Override
	public void setGenerateCount(Integer generateCount) {
		this.generateCount = generateCount;
	}
	
	@Override
	public void putGenerator(Generator<?> objectGenerator){
		classToGenerators.put(objectGenerator.getClassType().getName(), objectGenerator);
	}
	
	@Override
	public void putGenerator(String fieldName,Generator<?> objectGenerator){
		String fieldKey = fieldName+"_"+objectGenerator.getClassType().getName();
		fieldNameToGenerators.put(fieldKey, objectGenerator);
	}
	
	@Override
	public void setOpenMessageTip(boolean isOpenMessageTip) {
		this.isOpenMessageTip = isOpenMessageTip;
	}
	
}