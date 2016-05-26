package com.s4rm.wise.service;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.Instrument;

public interface InstrumentService {
	
	/**
	 * Class Name:	InstrumentService.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.04.28
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean newInstance(Instrument inst);
	public Instrument readInstance(int instID);
	public boolean updateInstance(Instrument inst);
	public boolean deleteInstance(int instID);	
	public List<Instrument> readCollection();
	
	public List<Instrument> search(Map<String, String> map);
	public List<Instrument> listSearch(Map<String, List<String>> map);

}
