package com.jason.data.generator.type;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.type.base.Generator;

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
	public Float generateData(String fieldName) {
		return RandomUtils.nextFloat(startInclusive, endExclusive);
	}
	

	
}
