package com.jason.data.generator.generator;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import com.jason.data.generator.utils.Generator;

public class StringGenerator extends Generator<String>{

	private int charCount;
	private String prefix="";
	private String suffix="";
	private String charRange = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String dateNumbers = "123456789";
	private String format;
	private String[] formatParameters;
	private String[][] formatParameterArrays;
	private int generateType;
	private String joinSeparator = ",";
	private int joinCount = 5;
	
	public StringGenerator() {
		charCount = 5;
		generateType=1;
	}
	
	public StringGenerator(int charCount) {
		this.charCount = charCount;
		generateType=1;
	}

	public StringGenerator(int charCount, String charRange) {
		this.charCount = charCount;
		this.charRange = charRange;
		generateType=1;
	}
	
	public StringGenerator(int charCount, boolean chineseCharRange) {
		this.charCount = charCount;
		generateType = chineseCharRange?2:1;
	}
	

	public StringGenerator(int charCount,String prefix, String suffix) {
		this.charCount = charCount;
		this.prefix = StringUtils.defaultIfEmpty(prefix, "");
		this.suffix = StringUtils.defaultIfEmpty(suffix, "");
		generateType=1;
	}
	
	public StringGenerator(int charCount, String charRange,String prefix, String suffix) {
		this.charCount = charCount;
		this.charRange = charRange;
		this.prefix = StringUtils.defaultIfEmpty(prefix, "");
		this.suffix = StringUtils.defaultIfEmpty(suffix, "");
		generateType=1;
	}
	
	public StringGenerator(int charCount,String prefix, String suffix, String joinSeparator, int joinCount) {
		this.charCount = charCount;
		this.prefix = StringUtils.defaultIfEmpty(prefix, "");
		this.suffix = StringUtils.defaultIfEmpty(suffix, "");
		this.joinSeparator = StringUtils.defaultIfEmpty(joinSeparator, "");
		this.joinCount = joinCount;
		generateType=3;
	}

	public StringGenerator(String format, String... formatParameters) {
		this.format = format;
		this.formatParameters = formatParameters;
		generateType=4;
	}
	
	public StringGenerator(String format, String[]... formatParameterArrays) {
		this.format = format;
		this.setFormatParameterArrays(formatParameterArrays);
		generateType=5;
	}
	
	@Override
	public String generateData(String fieldName) {
		
		switch (generateType) {
		case 1:
			if (StringUtils.isBlank(fieldName)) {
				return prefix+RandomStringUtils.random(charCount, charRange)+suffix;
			}else if (StringUtils.containsIgnoreCase(fieldName, "id")) {
				return prefix+UUID.randomUUID().toString().replaceAll("-", "");
			}else if (StringUtils.containsIgnoreCase(fieldName, "date")) {
				return prefix+getFormatParameterString("201%s-0%s-1%s",dateNumbers,dateNumbers,dateNumbers);
			}else if (StringUtils.containsIgnoreCase(fieldName, "time")) {
				return prefix+getFormatParameterString("201%s-0%s-1%s 1%s-3%s-4%s"
						,dateNumbers,dateNumbers,dateNumbers,dateNumbers,dateNumbers,dateNumbers);
			}else {
				return prefix+RandomStringUtils.random(charCount, charRange)+suffix;
			}
		case 2:
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < charCount; i++) {
				sb.append((char)RandomUtils.nextDouble(19968, 40869));
			}
			return sb.toString();
		case 3:
			List<String> list = new ArrayList<>();
			for (int i = 0; i < joinCount; i++) {
				list.add(prefix+RandomStringUtils.random(charCount, charRange)+suffix);
			}
			return StringUtils.join(list.toArray(), joinSeparator);
		case 4:
			return getFormatParameterString(format,formatParameters);
		case 5:
			return getFormatParameterArrayString(format,formatParameterArrays);
		default:
			return "";
		}
	}

	private String getFormatParameterString(String formatTemp,String... formatParametersTemp) {
		if (ArrayUtils.isEmpty(formatParametersTemp)) {
			return formatTemp;
		}
		Object[] realParameters = new String[formatParametersTemp.length];
		for (int i = 0; i < formatParametersTemp.length; i++) {
			realParameters[i] = RandomStringUtils.random(1,formatParametersTemp[i]);
		}
		return String.format(formatTemp, realParameters);
	}

	private String getFormatParameterArrayString(String formatTemp,String[]... formatParameterArraysTemp) {
		if(ArrayUtils.isEmpty(formatParameterArraysTemp)) {
			return formatTemp;
		}
		Object[] arrayParameters = new String[formatParameterArraysTemp.length];
		for (int i = 0; i < formatParameterArraysTemp.length; i++) {
			int parameterIndex = RandomUtils.nextInt(0, formatParameterArraysTemp[i].length);
			arrayParameters[i] = formatParameterArraysTemp[i][parameterIndex];
		}
		return String.format(formatTemp, arrayParameters);
	}

	public String[][] getFormatParameterArrays() {
		return formatParameterArrays;
	}

	public void setFormatParameterArrays(String[][] formatParameterArrays) {
		this.formatParameterArrays = formatParameterArrays;
	}
	
}
