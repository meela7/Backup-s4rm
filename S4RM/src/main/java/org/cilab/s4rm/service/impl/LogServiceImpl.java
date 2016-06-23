package org.cilab.s4rm.service.impl;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.dao.LogDAO;
import org.cilab.s4rm.model.PersistenceLog;
import org.cilab.s4rm.service.LogService;

public class LogServiceImpl implements LogService {

	/**
	 * Class Name:	LogServiceImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.06.13
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	LogDAO logDao;
	
	public void setLogDao(LogDAO logDao){
		this.logDao = logDao;
	}
	
	@Override
	public boolean newInstance(PersistenceLog log) {
		return this.logDao.create(log);
	}

	@Override
	public PersistenceLog readInstance(int logID) {
		return this.logDao.read(logID);
	}

	@Override
	public boolean updateInstance(PersistenceLog log) {
		return this.logDao.update(log);
	}

	@Override
	public boolean deleteInstance(int logID) {
		return this.logDao.delete(logID);
	}

	@Override
	public List<PersistenceLog> readCollection() {
		return this.logDao.list();
	}

	@Override
	public boolean isInstanceExist(String startTime, String streamID) {
		if(this.logDao.getByUniqueKey(startTime, streamID) != null)
			return true;
		else
			return false;
	}

	@Override
	public List<PersistenceLog> search(Map<String, String> map) {
		return this.logDao.search(map);
	}

	@Override
	public List<PersistenceLog> listSearch(Map<String, List<String>> map) {
		return this.logDao.listSearch(map);
	}

}
