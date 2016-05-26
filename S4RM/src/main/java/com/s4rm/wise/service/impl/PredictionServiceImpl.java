package com.s4rm.wise.service.impl;

import java.util.List;
import java.util.Map;

import com.s4rm.wise.dao.PredictionDAO;
import com.s4rm.wise.model.Prediction;
import com.s4rm.wise.service.PredictionService;

public class PredictionServiceImpl implements PredictionService {

	/**
	 * Class Name:	PredictionServiceImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.13
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	PredictionDAO predictionDao;
	public void setPredictionDao(PredictionDAO predictionDao){
		this.predictionDao = predictionDao;
	}
	
	@Override
	public boolean newInstance(Prediction pred) {
		return predictionDao.create(pred);
	}

	@Override
	public Prediction readInstance(int predID) {
		Prediction pred = predictionDao.read(predID);
		return pred;
	}

	@Override
	public boolean updateInstance(Prediction pred) {
		return predictionDao.update(pred);
	}

	@Override
	public boolean deleteInstance(int predID) {
		return predictionDao.delete(predID);
	}

	@Override
	public List<Prediction> readCollection() {
		return predictionDao.list();
	}


	@Override
	public List<Prediction> search(Map<String, String> map) {
		return predictionDao.search(map);
	}

	@Override
	public List<Prediction> listSearch(Map<String, List<String>> map) {
		return predictionDao.listSearch(map);
	}

}
