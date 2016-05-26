package com.s4rm.wise.dao;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.model.Entity;

public interface EntityDAO {
	/**
	 * Class Name:	EntityDAO.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.16
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	public boolean create(Entity entity);
	public Entity read(int entityID);
	public boolean update(Entity entity);
	public boolean delete(int entityID);
	
	public List<Entity> list();
	public Entity getByUniqueKey(String name);
	public List<Entity> search(Map<String, String> map);
	public List<Entity> listSearch(Map<String, List<String>> map);
}
