package utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 数据生成器处理中心
 * @author Administrator
 *
 */
public class GeneratorProcesser {
	
	private static String currFieldName;
	
	
	Object generateObject(GenerateConfig generateConfig,Class<?> classType){
		try {
			return generateNormalObject(generateConfig,classType);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 普通对象类型创建对象
	 * 如果没有找到生成器，则拆分对象的字段，逐个字段生成数据。
	 * @param generateConfig
	 * @param classType
	 * @return
	 * @throws Exception 
	 */
	private Object generateNormalObject(GenerateConfig generateConfig,Class<?> classType) throws Exception{
		
		Generator<?> fieldGenerator = generateConfig.getObjectGenerator(classType, currFieldName);
		
		if (Objects.isNull(fieldGenerator)) {
			
			String generateMsg = String.format("can't find ObjectGenerator for class : %s,\n    attempt split fields to generate Individually.",classType.getName());
			printMsg(generateMsg, generateConfig);
			
			Field[] fields = classType.getDeclaredFields();
			return generateObjectByFields(fields,generateConfig,classType);
		}else {
			return fieldGenerator.generateData();
		}
	}

	private <T> T generateObjectByFields(Field[] fields,GenerateConfig generateConfig,Class<T> dataType) throws Exception {
		
		T resultObject = dataType.newInstance();
		
		for (Field field : fields) {
			currFieldName = field.getName();
			field.setAccessible(true);
			Object typeValue = generateObjectByType(generateConfig, field.getGenericType());
			field.set(resultObject,typeValue);
		}
		return resultObject;
	}

	/**
	 * 
	 * 核心类，根据类型创建对象
	 * @param generateConfig
	 * @param fieldType
	 * @return
	 * @throws Exception
	 */
	private Object generateObjectByType(GenerateConfig generateConfig, Type fieldType) throws Exception {
		
		if (fieldType instanceof Class) {
			return getObjectByClassType(generateConfig,fieldType);
		}else if (fieldType instanceof ParameterizedType) {
			return getObjectParameterizedType(generateConfig, fieldType);
		}else if (fieldType instanceof GenericArrayType) {
			return getObjectGenericArrayType(generateConfig, fieldType);
		}else if (fieldType instanceof WildcardType) {
			return getObjectWildcardType();
		}else {
			return null;
		}
	}
	
	private Object getObjectByClassType(GenerateConfig generateConfig, Type fieldType) throws Exception {
		Class<?> fieldClass = (Class<?>)fieldType;
		if (fieldClass.isArray()) {
			return generateArrayObjectByType(generateConfig,fieldClass.getComponentType());
		}
		return generateNormalObject(generateConfig,fieldClass);
	}

	private Object getObjectParameterizedType(GenerateConfig generateConfig, Type fieldType) throws Exception{
		
		Class<?> fieldClass = (Class<?>) ((ParameterizedType)fieldType).getRawType();
		
		if (fieldClass.isAssignableFrom(List.class)) {
			List<?> listObject = new ArrayList<>();
			ParameterizedType parameterizedType = (ParameterizedType)fieldType;
			Type listArguType = parameterizedType.getActualTypeArguments()[0];
			for (int i = 0; i < generateConfig.getGenerateCount(); i++) {
				Object oneElement = generateObjectByType(generateConfig,listArguType);
				Method addMethod = List.class.getDeclaredMethod("add",Object.class);
				addMethod.invoke(listObject, oneElement);
			}
			return listObject;
		}else if (fieldClass.isAssignableFrom(Map.class)) {
			
			Map<?,?> mapObject = new HashMap<>();
			ParameterizedType parameterizedType = (ParameterizedType)fieldType;
			Type firstArguType = parameterizedType.getActualTypeArguments()[0];
			Type secondArguType = parameterizedType.getActualTypeArguments()[1];
			for (int i = 0; i < generateConfig.getGenerateCount(); i++) {
				Object firstElement = generateObjectByType(generateConfig,firstArguType);
				Object secondElement = generateObjectByType(generateConfig,secondArguType);
				Method putMethod = Map.class.getDeclaredMethod("put",Object.class,Object.class);
				putMethod.invoke(mapObject, firstElement,secondElement);
			}
			return mapObject;
		}else if (fieldClass.isAssignableFrom(Set.class)) {
			
			Set<?> setObject = new HashSet<>();
			ParameterizedType parameterizedType = (ParameterizedType)fieldType;
			Type setArguType = parameterizedType.getActualTypeArguments()[0];
			for (int i = 0; i < generateConfig.getGenerateCount(); i++) {
				Object oneElement = generateObjectByType(generateConfig,setArguType);
				Method addMethod = Set.class.getDeclaredMethod("add",Object.class);
				addMethod.invoke(setObject, oneElement);
			}
			return setObject;
		}else {
			return generateNormalObject(generateConfig,fieldClass);
		}
	}

	private Object getObjectGenericArrayType(GenerateConfig generateConfig, Type fieldType) throws Exception {
		//TODO 未实现
		return null;
	}

	private Object getObjectWildcardType() {
		//TODO 未实现
		return null;
	}

	/**
	 * 数组类型创建对象
	 * @param generateConfig
	 * @param conponentType
	 * @return
	 * @throws Exception
	 */
	private Object generateArrayObjectByType(GenerateConfig generateConfig, Type conponentType)throws Exception {
		Object genericArray = Array.newInstance((Class<?>)conponentType, generateConfig.getGenerateCount());
		for (int i = 0; i < generateConfig.getGenerateCount(); i++) {
			Object oneElement = generateObjectByType(generateConfig,conponentType);
			Array.set(genericArray, i, oneElement);
		}
		return genericArray;
	}
	
	private void printMsg(String message,GenerateConfig generateConfig){
		if (generateConfig.isOpenMessageTip()) {
			System.out.println(message);
		}
	}

}
