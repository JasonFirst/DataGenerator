package com.jason.data.generator.type;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.type.base.Generator;

public class ByteGenerator extends Generator<Byte>{

	private int start = 0;
	private int end = 7;
	
	public ByteGenerator() {
	}

	public ByteGenerator(int start, int end) {
		final boolean rightFormat = start>=Byte.MIN_VALUE && start<=Byte.MAX_VALUE && end>=start && end<=Byte.MAX_VALUE;
		if (!rightFormat) {
			return;
		}
		this.start = start;
		this.end = end;
	}

	@Override
	public Byte generateData(String fieldName) {
		return (byte) RandomUtils.nextInt(start, end);
	}
	
}
