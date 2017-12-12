package com.jason.data.generator.test;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.jason.data.generator.generator.DateGenerator;
import com.jason.data.generator.generator.IntegerGenerator;
import com.jason.data.generator.generator.StringGenerator;
import com.jason.data.generator.utils.GenerateConfig;
import com.jason.data.generator.utils.GeneratorUtils;

public class Test {
	
	public static void main(String[] args) throws Exception {
		Task one = GeneratorUtils.getOne(Task.class);
		List<Task> tasks = GeneratorUtils.getList(Task.class);
		System.out.println(JSONObject.toJSONString(one));
		System.out.println(JSONObject.toJSONString(tasks));
		
		
		GenerateConfig config = new GenerateConfig();
		config.putGenerator(new StringGenerator(5,"QWERT"));
		config.putGenerator(new IntegerGenerator(250,300));
		config.putGenerator(new DateGenerator(DateGenerator.fluctuate_milltsecond, 3, 16));
		config.putGenerator("contactWechat",new StringGenerator(7,"1234567890"));
		config.putGenerator("timeString",new StringGenerator("201%s-05-03","12345670"));
		config.putGenerator("alternative",new StringGenerator("今天吃%s，喝%s",
					new String[]{"汉堡","薯条"},new String[]{"可乐","奶茶"}));
		config.setOpenMessageTip(false);
		config.setGenerateCount(4);
		
		Task superOne = GeneratorUtils.getOne(Task.class, config);
		List<Task> superTasks = GeneratorUtils.getList(Task.class,config);
		System.out.println(JSONObject.toJSONString(superOne));
		System.out.println(JSONObject.toJSONString(superTasks));
		
		
		
	}
	
	
}
