package generator;

import org.apache.commons.lang3.RandomUtils;

import utils.Generator;

public class DoubleGenerator extends Generator<Double>{

	private double startInclusive;
	private double endExclusive;
	
	public DoubleGenerator() {
		this.startInclusive = 0;
		this.endExclusive = 100000;
	}

	public DoubleGenerator(double startInclusive, double endExclusive) {
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}

	@Override
	public Double generateData() {
		return RandomUtils.nextDouble(startInclusive, endExclusive);
	}
	

	
}
