package generator;

import java.util.Objects;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import utils.Generator;

public class StringGenerator extends Generator<String>{

	private int charCount;
	private String charRange;
	private String format;
	private String[] formatParameters;
	
	public StringGenerator() {
		charCount = 5;
	}
	
	public StringGenerator(int charCount) {
		this.charCount = charCount;
	}

	public StringGenerator(int charCount, String charRange) {
		this.charCount = charCount;
		this.charRange = charRange;
	}

	public StringGenerator(String format, String... formatParameters) {
		this.format = format;
		this.formatParameters = formatParameters;
	}

	@Override
	public String generateData() {
		
		if (Objects.nonNull(format)) {
			if (ArrayUtils.isEmpty(formatParameters)) {
				return format;
			}
			Object[] realParameter = new String[formatParameters.length];
			for (int i = 0; i < formatParameters.length; i++) {
				realParameter[i] = RandomStringUtils.random(1,formatParameters[i]);
			}
			return String.format(format, realParameter);
		}
		
		if (StringUtils.isBlank(charRange)) {
			return RandomStringUtils.random(charCount);
		}else {
			return RandomStringUtils.random(charCount, charRange);
		}
	}
	
}
