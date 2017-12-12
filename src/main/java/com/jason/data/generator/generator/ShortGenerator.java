package com.jason.data.generator.generator;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.utils.Generator;

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
	public Short generateData() {
		return (short)RandomUtils.nextInt(startInclusive, endExclusive);
	}
	

	
}
