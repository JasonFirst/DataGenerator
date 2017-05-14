package generator;

import org.apache.commons.lang3.RandomUtils;

import utils.Generator;

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
