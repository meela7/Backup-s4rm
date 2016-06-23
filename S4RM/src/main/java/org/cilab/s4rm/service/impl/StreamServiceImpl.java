package org.cilab.s4rm.service.impl;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.dao.StreamDAO;
import org.cilab.s4rm.model.Stream;
import org.cilab.s4rm.service.StreamService;

public class StreamServiceImpl implements StreamService {

	/**
	 * Class Name:	StreamServiceImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.06.13
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	StreamDAO streamDao;
	public void setStreamDao(StreamDAO streamDao){
		this.streamDao = streamDao;
	}
	
	@Override
	public boolean newInstance(Stream stream) {

		return this.streamDao.create(stream);
	}

	@Override
	public Stream readInstance(String streamID) {
		return this.streamDao.read(streamID);
	}

	@Override
	public boolean updateInstance(Stream stream) {
		
		return this.streamDao.update(stream);
	}

	@Override
	public boolean deleteInstance(String streamID) {
		return this.streamDao.delete(streamID);
	}

	@Override
	public List<Stream> readCollection() {
		return this.streamDao.list();
	}

	@Override
	public boolean isInstanceExist(String createdAt, String sensorID) {
		if(this.streamDao.getByUniqueKey(createdAt, sensorID) != null)
			return true;
		return false;
	}

	@Override
	public List<Stream> search(Map<String, String> map) {
		return this.streamDao.search(map);
	}

	@Override
	public List<Stream> listSearch(Map<String, List<String>> map) {
		return this.streamDao.listSearch(map);
	}

}
