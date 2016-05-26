package com.s4rm.wise.service.impl;

import java.util.List;
import java.util.Map;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4rm.wise.model.Value;
import com.s4rm.wise.service.ValueService;

public class ValueServiceImpl implements ValueService {

	/**
	 * Class Name:	ValueServiceImpl.java
	 * Description: 	
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.24
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private static final Logger logger = LoggerFactory.getLogger(ValueServiceImpl.class);
	
	@Override
	public boolean newInstance(Value value) {
		//http://52.193.230.38:8086/write?db=wise
		InfluxDB influxDB = InfluxDBFactory.connect("http://52.193.230.38:8086", "root", "cir@817!");
		String dbName = "wise";
		influxDB.createDatabase(dbName);

		BatchPoints batchPoints = BatchPoints
		                .database(dbName)
		                .tag("async", "true")
		                .retentionPolicy("default")
		                .consistency(ConsistencyLevel.ALL)
		                .build();

		Point point = Point.measurement("DataValue").addField("StreamID", value.getDataSetID()).addField("DateTime", value.getDateTime()).addField("Value", value.getValue()).build();
		
		batchPoints.point(point);
		influxDB.write(batchPoints);
		logger.info("Add SensorData to Infux DB, StreamID: {}, DateTime:{}, Value: {}", value.getDataSetID(), value.getDateTime(), value.getValue());
		Query query = new Query("SELECT * FROM DataValue", dbName);
		QueryResult result = influxDB.query(query);
		logger.info("Inserted Info:{}",result.getResults().size());
//		influxDB.deleteDatabase(dbName);
		return true;
	}

	@Override
	public Value readInstance(String valueID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInstance(Value value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteInstance(String valueID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Value> readCollection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInstanceExist(String streamID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Value> search(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Value> listSearch(Map<String, List<String>> map) {
		// TODO Auto-generated method stub
		return null;
	}

}
