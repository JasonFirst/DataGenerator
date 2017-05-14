package generator;

import org.apache.commons.lang3.RandomUtils;

import utils.Generator;

public class LongGenerator extends Generator<Long>{

	private long startInclusive;
	private long endExclusive;
	
	public LongGenerator() {
		this.startInclusive = 0;
		this.endExclusive = 100000;
	}

	public LongGenerator(long startInclusive, long endExclusive) {
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}

	@Override
	public Long generateData() {
		return RandomUtils.nextLong(startInclusive, endExclusive);
	}
	

	
}
