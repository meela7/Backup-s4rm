package org.cilab.s4rm.model;

public class APIKey {

	/**
	 * Class Name: APIKey.java 
	 * Description: 
	 * 
	 * @author Meilan Jiang
	 * @since 2016.06.10
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private int ID;
	private String Secret;
	private int Status;
//	private User CreatedBy;
	private String Name;
	private String Desc;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getSecret() {
		return Secret;
	}
	public void setSecret(String secret) {
		Secret = secret;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
//	public User getCreatedBy() {
//		return CreatedBy;
//	}
//	public void setCreatedBy(User createdBy) {
//		CreatedBy = createdBy;
//	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
}
