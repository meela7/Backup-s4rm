package org.cilab.s4rm.dao;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.model.Stream;

public interface StreamDAO {
	/**
	 * Class Name:	StreamDAO.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.06.13
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	public boolean create(Stream stream);
	public Stream read(String streamID);
	public boolean update(Stream stream);
	public boolean delete(String streamID);	
	public List<Stream> list();	
	
	public Stream getByUniqueKey(String createdAt, String sensorID);
	public List<Stream> search(Map<String, String> map);
	public List<Stream> listSearch(Map<String, List<String>> map);
}
