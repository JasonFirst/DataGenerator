package generator;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import utils.Generator;

public class StringGenerator extends Generator<String>{

	private int charCount;
	private String prefix="";
	private String suffix="";
	private String charRange = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String format;
	private String[] formatParameters;
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
			Object[] realParameter = new String[formatParameters.length];
			for (int i = 0; i < formatParameters.length; i++) {
				realParameter[i] = RandomStringUtils.random(1,formatParameters[i]);
			}
			return String.format(format, realParameter);
		default:
			return "";
		}
	}
	
}
