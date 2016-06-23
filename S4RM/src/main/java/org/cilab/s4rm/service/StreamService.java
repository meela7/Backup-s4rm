package org.cilab.s4rm.service;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.model.Stream;

public interface StreamService {
	
	/**
	 * Class Name:	StreamService.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.14
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean newInstance(Stream stream);
	public Stream readInstance(String streamID);
	public boolean updateInstance(Stream stream);
	public boolean deleteInstance(String streamID);	
	public List<Stream> readCollection();
	
	public boolean isInstanceExist(String createdAt, String sensorID);
	public List<Stream> search(Map<String, String> map);
	public List<Stream> listSearch(Map<String, List<String>> map);

}
