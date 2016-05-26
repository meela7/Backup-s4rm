package com.s4rm.wise.dao;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.Instrument;


public interface InstrumentDAO {
	
	/**
	 * Class Name:	InstrumentDAO.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.04.28
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean create(Instrument inst);
	public Instrument read(int instID);
	public boolean update(Instrument inst);
	public boolean delete(int instID);
	
	public List<Instrument> list();
	public List<Instrument> search(Map<String, String> map);
	public List<Instrument> listSearch(Map<String, List<String>> map);

}
