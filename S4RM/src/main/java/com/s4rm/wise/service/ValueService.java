package com.s4rm.wise.service;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.Value;

public interface ValueService {
	
	/**
	 * Class Name:	ValueService.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.24
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean newInstance(Value value);
	public Value readInstance(String valueID);
	public boolean updateInstance(Value value);
	public boolean deleteInstance(String valueID);	
	public List<Value> readCollection();
	
	public boolean isInstanceExist(String streamID);
	public List<Value> search(Map<String, String> map);
	public List<Value> listSearch(Map<String, List<String>> map);

}
