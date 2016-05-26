package com.s4rm.wise.dao;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.Site;


public interface SiteDAO {
	
	/**
	 * Class Name:	SiteDAO.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.13
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean create(Site site);
	public Site read(int siteID);
	public boolean update(Site site);
	public boolean delete(int siteID);
	
	public List<Site> list();
	public Site getByUniqueKey(String name);
	public List<Site> search(Map<String, String> map);
	public List<Site> listSearch(Map<String, List<String>> map);

}
