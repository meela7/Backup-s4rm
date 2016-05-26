package com.s4rm.wise.service;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.Site;

public interface SiteService {
	
	/**
	 * Class Name:	SiteService.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.14
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean newInstance(Site site);
	public Site readInstance(int siteID);
	public boolean updateInstance(Site site);
	public boolean deleteInstance(int siteID);	
	public List<Site> readCollection();
	
	public boolean isInstanceExist(String name);
	public List<Site> search(Map<String, String> map);
	public List<Site> listSearch(Map<String, List<String>> map);

}
