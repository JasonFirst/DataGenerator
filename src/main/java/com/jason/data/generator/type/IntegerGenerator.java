package com.jason.data.generator.type;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.type.base.Generator;

public class IntegerGenerator extends Generator<Integer>{

	private int startInclusive;
	private int endExclusive;
	
	public IntegerGenerator() {
		this.startInclusive = 0;
		this.endExclusive = 100000;
	}

	public IntegerGenerator(int startInclusive, int endExclusive) {
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}

	@Override
	public Integer generateData(String fieldName) {
		return RandomUtils.nextInt(startInclusive, endExclusive);
	}
	

	
}
