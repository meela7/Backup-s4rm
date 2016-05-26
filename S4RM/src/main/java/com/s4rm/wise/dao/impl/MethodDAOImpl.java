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

import com.s4rm.wise.dao.MethodDAO;
import com.s4rm.wise.model.Method;

public class MethodDAOImpl implements MethodDAO {
	
	/**
	 * Class Name:	MethodDAOImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.02.04
	 * @version 1.0
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(MethodDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public MethodDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public boolean create(Method method) {		
		try {
			sessionFactory.getCurrentSession().save(method);			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Method read(int methodID) {
		return (Method) sessionFactory.getCurrentSession().get(Method.class, methodID);
	}

	@Override
	@Transactional
	public boolean update(Method method) {
		try {
			sessionFactory.getCurrentSession().update(method);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public boolean delete(int methodID) {
		try {
			Query query = sessionFactory.getCurrentSession().createQuery("DELETE FROM Method WHERE MethodID = :methodID");
			query.setParameter("methodID", methodID);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public List<Method> list() {
		@SuppressWarnings("unchecked")
		List<Method> methodList = (List<Method>) sessionFactory.getCurrentSession().createCriteria(Method.class)
				.addOrder(Order.asc("MethodID")).list();
		
		return methodList;
	}

	@Override
	@Transactional
	public Method getByUniqueKey(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Method WHERE MethodName = :methodName ");
		if (name != null) {
			query.setParameter("methodName", name);
		}
		Method method = (Method) query.uniqueResult();
		return method;
	}

	@Override
	@Transactional
	public List<Method> search(Map<String, String> map) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "FROM Method WHERE ";
		
//		// get all the columns of the Method
//		// remove the parameters which doesn't match with column in the list
//		AbstractEntityPersister aep=((AbstractEntityPersister)sessionFactory.getClassMetadata(Method.class));  
//		String[] properties = aep.getPropertyNames();
//		List<String> columnNames = Arrays.asList(properties);
//		
//		// remove parameters that doesn't match the scheme.
//		for(String key : map.keySet()){
//			if(!columnNames.contains(key))
//				map.remove(key);
//		}
		
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
		List<Method> methodList = (List<Method>) query.list();
		return methodList;
	}

	@Override
	@Transactional
	public List<Method> listSearch(Map<String, List<String>> map) {
		Session session = sessionFactory.getCurrentSession();
		String hqlQuery = "FROM Method WHERE ";
		
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
		List<Method> methodList = (List<Method>) query.list();
		
		return methodList;
	}

}
