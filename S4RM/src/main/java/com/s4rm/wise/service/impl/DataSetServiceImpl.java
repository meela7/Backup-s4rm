package com.s4rm.wise.service.impl;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.dao.DataSetDAO;
import com.s4rm.wise.model.DataSet;
import com.s4rm.wise.service.DataSetService;

public class DataSetServiceImpl implements DataSetService {

	/**
	 * Class Name:	DataSetServiceImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.16
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	DataSetDAO dataSetDao;
	
	public void setDataSetDao(DataSetDAO dataSetDao){
		this.dataSetDao = dataSetDao;
	}
	
	@Override
	public boolean newInstance(DataSet dataSet) {
		return this.dataSetDao.create(dataSet);
	}

	@Override
	public DataSet readInstance(String dataSetID) {
		return this.dataSetDao.read(dataSetID);
	}

	@Override
	public boolean updateInstance(DataSet dataSet) {
		return this.dataSetDao.update(dataSet);
	}

	@Override
	public boolean deleteInstance(String dataSetID) {
		return this.dataSetDao.delete(dataSetID);
	}

	@Override
	public List<DataSet> readCollection() {
		return this.dataSetDao.list();
	}

	@Override
	public boolean isInstanceExist(int siteID, int entityID, int variableID, int methodID, int sourceID) {
		if(this.dataSetDao.getByUniqueKey(siteID, entityID, variableID, methodID, sourceID) != null)
			return true;
		else
			return false;
	}

	@Override
	public List<DataSet> search(Map<String, String> map) {
		return this.dataSetDao.search(map);
	}

	@Override
	public List<DataSet> listSearch(Map<String, List<String>> map) {
		return this.dataSetDao.listSearch(map);
	}

}
