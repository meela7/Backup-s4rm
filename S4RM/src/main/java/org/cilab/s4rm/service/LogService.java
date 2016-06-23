package org.cilab.s4rm.service;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.model.PersistenceLog;

public interface LogService {
	
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
	public boolean newInstance(PersistenceLog log);
	public PersistenceLog readInstance(int logID);
	public boolean updateInstance(PersistenceLog log);
	public boolean deleteInstance(int logID);	
	public List<PersistenceLog> readCollection();
	
	public boolean isInstanceExist(String startTime, String streamID);
	public List<PersistenceLog> search(Map<String, String> map);
	public List<PersistenceLog> listSearch(Map<String, List<String>> map);

}
