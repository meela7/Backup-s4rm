package com.s4rm.wise.model;

public class Prediction extends Method{
	/**
	 * Class Name: Prediction.java 
	 * Description: Hibernate Mapping pojo File
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.09
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */

	private String ModelingMethod;
	private String ExcuteEnvironment;
	private String Creator;
	private String CreatedDate;
	private String Description;

	public String getModelingMethod() {
		return ModelingMethod;
	}
	public void setModelingMethod(String modelingMethod) {
		ModelingMethod = modelingMethod;
	}
	public String getExcuteEnvironment() {
		return ExcuteEnvironment;
	}
	public void setExcuteEnvironment(String excuteEnvironment) {
		ExcuteEnvironment = excuteEnvironment;
	}
	public String getCreator() {
		return Creator;
	}
	public void setCreator(String creator) {
		Creator = creator;
	}
	public String getCreatedDate() {
		return CreatedDate;
	}
	public void setCreatedDate(String createdDate) {
		CreatedDate = createdDate;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}

	
}
