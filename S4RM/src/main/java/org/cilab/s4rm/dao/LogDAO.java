package org.cilab.s4rm.dao;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.model.PersistenceLog;

public interface LogDAO {
	/**
	 * Class Name:	LogDAO.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.16
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean create(PersistenceLog log);
	public PersistenceLog read(int logID);
	public boolean update(PersistenceLog log);
	public boolean delete(int logID);
	
	public List<PersistenceLog> list();
	public PersistenceLog getByUniqueKey(String startTime, String streamID);
	public List<PersistenceLog> search(Map<String, String> map);
	public List<PersistenceLog> listSearch(Map<String, List<String>> map);
}
