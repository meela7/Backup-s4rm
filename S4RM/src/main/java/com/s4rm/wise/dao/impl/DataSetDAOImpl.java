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

import com.s4rm.wise.dao.DataSetDAO;
import com.s4rm.wise.model.DataSet;

public class DataSetDAOImpl implements DataSetDAO {

	/**
	 * Class Name:	DataSetDAOImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.20
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(DataSetDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public DataSetDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public boolean create(DataSet dataSet) {
		try {
			sessionFactory.getCurrentSession().save(dataSet);			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public DataSet read(String dataSetID) {
		return (DataSet) this.sessionFactory.getCurrentSession().get(DataSet.class, dataSetID);
	}

	@Override
	@Transactional
	public boolean update(DataSet dataSet) {
		try {
			sessionFactory.getCurrentSession().update(dataSet);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(String dataSetID) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM DataSet WHERE DataSetID = :dataSetID");
			query.setParameter("dataSetID", dataSetID);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public List<DataSet> list() {
		@SuppressWarnings("unchecked")
		List<DataSet> streamList = (List<DataSet>) sessionFactory.getCurrentSession().createCriteria(DataSet.class)
				.addOrder(Order.asc("DataSetID")).list();
		
		return streamList;
	}

	@Override
	@Transactional
	public DataSet getByUniqueKey(int siteID, int entityID, int variableID, int methodID, int sourceID) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Stream WHERE SiteID = :siteID and EntityID = :entityID");
		if (siteID != 0) {
			query.setParameter("siteID", siteID);
			query.setParameter("entityID", entityID);
		}
		DataSet stream = (DataSet) query.uniqueResult();
		return stream;
	}

	@Override
	@Transactional
	public List<DataSet> search(Map<String, String> map) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "FROM Stream WHERE ";
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
			query.setParameter(key, map.get(key));
		}
		@SuppressWarnings("unchecked")
		List<DataSet> streamList = (List<DataSet>) query.list();
		return streamList;
	}

	@Override
	@Transactional
	public List<DataSet> listSearch(Map<String, List<String>> map) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "FROM Stream WHERE ";
		
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
			if(key.equals("MethodID")){
				List<Integer> valueList = new ArrayList<Integer>();
				for(String value: map.get(key)){
					valueList.add(Integer.parseInt(value));
				}
				query.setParameterList(key, valueList);
			}else
				query.setParameterList(key, map.get(key));
		}
		@SuppressWarnings("unchecked")
		List<DataSet> streamList = (List<DataSet>) query.list();
		
		return streamList;
	}

}
