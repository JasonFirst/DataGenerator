package generator;

import java.math.BigDecimal;

import org.apache.commons.lang3.RandomUtils;

import utils.Generator;

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
	public BigDecimal generateData() {
		return new BigDecimal(RandomUtils.nextInt(startInclusive, endExclusive));
	}
	

	
}
