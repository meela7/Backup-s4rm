package com.s4rm.wise.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.s4rm.wise.dao.SiteDAO;
import com.s4rm.wise.model.Site;

public class SiteDAOImpl implements SiteDAO {

	/**
	 * Class Name:	SiteDAOImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.14
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SiteDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public SiteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public boolean create(Site site) {
		try {
			sessionFactory.getCurrentSession().save(site);			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Site read(int siteID) {
		return (Site)this.sessionFactory.getCurrentSession().get(Site.class, siteID);
	}

	@Override
	@Transactional
	public boolean update(Site site) {
		try {
			sessionFactory.getCurrentSession().update(site);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(int siteID) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Site WHERE SiteID = :siteID");
			query.setParameter("siteID", siteID);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public List<Site> list() {
		@SuppressWarnings("unchecked")
		List<Site> siteList = (List<Site>) sessionFactory.getCurrentSession().createCriteria(Site.class)
				.addOrder(Order.asc("SiteID")).list();
		
		return siteList;
	}

	@Override
	@Transactional
	public Site getByUniqueKey(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Site WHERE SiteName = :siteName ");
		if (name != null) {
			query.setParameter("siteName", name);
		}
		Site site = (Site) query.uniqueResult();
		return site;
	}

	@Override
	@Transactional
	public List<Site> search(Map<String, String> map) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "FROM Site WHERE ";
		
//		// get all the columns of the Site
//		// remove the parameters which doesn't match with column in the list
//		AbstractEntityPersister aep=((AbstractEntityPersister)sessionFactory.getClassMetadata(Site.class));  
//		String[] properties = aep.getPropertyNames();
//		List<String> columnNames = Arrays.asList(properties);
//		
//		// remove parameters that doesn't match the scheme.
//		for(String key : map.keySet()){
//			if(!columnNames.contains(key))
//				map.remove(key);
//		}
//		
		int index = 0;
		for(String key : map.keySet()){
			if(index == 0 )
				hqlQuery = hqlQuery + key + " = :" + key ;
			else
				hqlQuery = hqlQuery + " and " + key + " = :" + key ;
			index++;
		}
		
		// execute HQL Query
		logger.info("Execute Query: {}", hqlQuery);
		Query query = session.createQuery(hqlQuery);
		for(String key : map.keySet()){
			query.setParameter(key, map.get(key));	// convert type if the values are not String type
		}
		@SuppressWarnings("unchecked")
		List<Site> siteList = (List<Site>) query.list();
		return siteList;
	}

	@Override
	@Transactional
	public List<Site> listSearch(Map<String, List<String>> map) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "FROM Site WHERE ";
			
		// create HQL Statement
		int index = 0;
		for(String key : map.keySet()){			
			if(index == 0 )
				hqlQuery = hqlQuery + key + " in :" + key ;
			else
				hqlQuery = hqlQuery + " and " + key + " in :" + key ;
			index++;
		}
		
		// execute HQL Query
		logger.info("Execute Query: {}", hqlQuery);
		Query query = session.createQuery(hqlQuery);
		for(String key : map.keySet()){
			if(key.equals("SiteID")){
				List<Integer> valueList = new ArrayList<Integer>();
				for(String value: map.get(key)){
					valueList.add(Integer.parseInt(value));
				}
				query.setParameterList(key, valueList);
			}else
				query.setParameterList(key, map.get(key));
		}
		@SuppressWarnings("unchecked")
		List<Site> siteList = (List<Site>) query.list();
		
		return siteList;
	}

}
