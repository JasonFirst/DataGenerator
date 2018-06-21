package com.jason.data.generator.type;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.RandomUtils;

import com.jason.data.generator.type.base.Generator;

public class DateGenerator extends Generator<Date>{

	public static int fluctuate_year=Calendar.YEAR;
	public static int fluctuate_month=Calendar.MONTH;
	public static int fluctuate_day=Calendar.DAY_OF_MONTH;
	public static int fluctuate_hour=Calendar.HOUR_OF_DAY;
	public static int fluctuate_minute=Calendar.MINUTE;
	public static int fluctuate_second=Calendar.SECOND;
	public static int fluctuate_milltsecond=Calendar.MILLISECOND;
	
	private int fluctuateField;
	private int fluctuateAmountBegin;
	private int fluctuateAmountEnd;
	
	public DateGenerator() {
		this.fluctuateField=fluctuate_day;
		fluctuateAmountBegin=0;
		fluctuateAmountEnd=30;
	}
	
	public DateGenerator(int fluctuateField) {
		this.fluctuateField=fluctuateField;
		fluctuateAmountBegin=0;
		fluctuateAmountEnd=30;
	}

	
	public DateGenerator(int fluctuateField, int fluctuateAmountBegin,int fluctuateAmountEnd) {
		this.fluctuateField = fluctuateField;
		this.fluctuateAmountBegin = fluctuateAmountBegin;
		this.fluctuateAmountEnd = fluctuateAmountEnd;
	}

	@Override
	public Date generateData(String fieldName) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(fluctuateField, -RandomUtils.nextInt(fluctuateAmountBegin, fluctuateAmountEnd));
		return calendar.getTime();
	}

	public int getFluctuateField() {
		return fluctuateField;
	}

	public void setFluctuateField(int fluctuateField) {
		this.fluctuateField = fluctuateField;
	}

	public int getFluctuateAmountBegin() {
		return fluctuateAmountBegin;
	}

	public void setFluctuateAmountBegin(int fluctuateAmountBegin) {
		this.fluctuateAmountBegin = fluctuateAmountBegin;
	}

	public int getFluctuateAmountEnd() {
		return fluctuateAmountEnd;
	}

	public void setFluctuateAmountEnd(int fluctuateAmountEnd) {
		this.fluctuateAmountEnd = fluctuateAmountEnd;
	}
}
