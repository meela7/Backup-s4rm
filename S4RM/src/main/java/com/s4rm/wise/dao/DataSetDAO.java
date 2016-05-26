package com.s4rm.wise.dao;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.DataSet;

public interface DataSetDAO {
	/**
	 * Class Name:	DataSetDAO.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.20
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	public boolean create(DataSet dataSet);
	public DataSet read(String dataSetID);
	public boolean update(DataSet dataSet);
	public boolean delete(String dataSetID);
	
	public List<DataSet> list();	
	public DataSet getByUniqueKey(int siteID, int entityID, int variableID, int methodID, int sourceID);
	public List<DataSet> search(Map<String, String> map);
	public List<DataSet> listSearch(Map<String, List<String>> map);
}
