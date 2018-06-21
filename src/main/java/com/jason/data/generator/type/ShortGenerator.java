package com.jason.data.generator.type;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.type.base.Generator;

public class ShortGenerator extends Generator<Short>{

	private int startInclusive;
	private int endExclusive;
	
	public ShortGenerator() {
		this.startInclusive = 0;
		this.endExclusive = 256;
	}

	public ShortGenerator(short startInclusive, short endExclusive) {
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}

	@Override
	public Short generateData(String fieldName) {
		return (short)RandomUtils.nextInt(startInclusive, endExclusive);
	}
	

	
}
