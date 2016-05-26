package com.s4rm.wise.service;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.DataSet;

public interface DataSetService {
	
	/**
	 * Class Name:	StreamService.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.20
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean newInstance(DataSet stream);
	public DataSet readInstance(String streamID);
	public boolean updateInstance(DataSet stream);
	public boolean deleteInstance(String streamID);	
	public List<DataSet> readCollection();
	
	public boolean isInstanceExist(int siteID, int entityID, int variableID, int methodID, int sourceID);
	public List<DataSet> search(Map<String, String> map);
	public List<DataSet> listSearch(Map<String, List<String>> map);

}
