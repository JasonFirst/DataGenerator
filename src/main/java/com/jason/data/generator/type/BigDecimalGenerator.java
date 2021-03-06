package com.jason.data.generator.type;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.type.base.Generator;

public class BigDecimalGenerator extends Generator<BigDecimal>{

	private int startInclusive;
	private int endExclusive;
	
	public BigDecimalGenerator() {
		this.startInclusive = 0;
		this.endExclusive = 100000;
	}

	public BigDecimalGenerator(int startInclusive, int endExclusive) {
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}

	@Override
	public BigDecimal generateData(String fieldName) {
		return new BigDecimal(RandomUtils.nextInt(startInclusive, endExclusive));
	}
	

	
}
