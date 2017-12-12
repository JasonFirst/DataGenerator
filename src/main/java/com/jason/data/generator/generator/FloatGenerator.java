package com.jason.data.generator.generator;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.utils.Generator;

public class FloatGenerator extends Generator<Float>{

	private float startInclusive;
	private float endExclusive;
	
	public FloatGenerator() {
		this.startInclusive = 0;
		this.endExclusive = 100000;
	}

	public FloatGenerator(float startInclusive, float endExclusive) {
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}

	@Override
	public Float generateData() {
		return RandomUtils.nextFloat(startInclusive, endExclusive);
	}
	

	
}
