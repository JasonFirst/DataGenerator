package com.jason.data.generator.generator;


import java.util.ArrayList;
import java.util.List;

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
	public String generateData() {
		
		switch (generateType) {
		case 1:
			return prefix+RandomStringUtils.random(charCount, charRange)+suffix;
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
			if (ArrayUtils.isEmpty(formatParameters)) {
				return format;
			}
			Object[] realParameters = new String[formatParameters.length];
			for (int i = 0; i < formatParameters.length; i++) {
				realParameters[i] = RandomStringUtils.random(1,formatParameters[i]);
			}
			return String.format(format, realParameters);
		case 5:
			if(ArrayUtils.isEmpty(formatParameterArrays)) {
				return format;
			}
			Object[] arrayParameters = new String[formatParameterArrays.length];
			for (int i = 0; i < formatParameterArrays.length; i++) {
				int parameterIndex = RandomUtils.nextInt(0, formatParameterArrays[i].length);
				arrayParameters[i] = formatParameterArrays[i][parameterIndex];
			}
			return String.format(format, arrayParameters);
		default:
			return "";
		}
	}

	public String[][] getFormatParameterArrays() {
		return formatParameterArrays;
	}

	public void setFormatParameterArrays(String[][] formatParameterArrays) {
		this.formatParameterArrays = formatParameterArrays;
	}
	
}
