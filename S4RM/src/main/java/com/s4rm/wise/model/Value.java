package com.s4rm.wise.model;

public class Value {

	/**
	 * Class Name: Value.java 
	 * Description: 
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.23
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private String DataSetID;
	private String DateTime;
	private double Value;
	
	public String getDataSetID() {
		return DataSetID;
	}
	public void setDataSetID(String dataSetID) {
		DataSetID = dataSetID;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	public double getValue() {
		return Value;
	}
	public void setValue(double value) {
		Value = value;
	}
	
	
}
