package org.cilab.s4rm.service.impl;

import java.util.List;
import java.util.Map;

import org.cilab.s4rm.model.Value;
import org.cilab.s4rm.service.ValueService;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		//http://52.193.230.38:8086/write?db=wise classpath:META-INF/
//		String fileName = "influx.properties";
//		Properties prop = new Properties();
//		InputStream inStream = getClass().getClassLoader().getResourceAsStream(fileName);
		
		InfluxDB influxDB = InfluxDBFactory.connect("http://52.193.230.38:8086", "root", "cir@817!");
		
//		if(inStream != null){
//			try {
//				prop.load(inStream);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else{
//			
//			logger.info("{}","");
//			try {
//				throw new FileNotFoundException("property file" + fileName + " not found in the classpath");
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		InfluxDB influxDB = InfluxDBFactory.connect(prop.getProperty("idbc.databaseurl"), prop.getProperty("idbc.user"), prop.getProperty("idbc.password"));
		
		String dbName = "wise";
		influxDB.createDatabase(dbName);

		BatchPoints batchPoints = BatchPoints
		                .database(dbName)
		                .tag("async", "true")
		                .retentionPolicy("default")
		                .consistency(ConsistencyLevel.ALL)
		                .build();

		Point point = Point.measurement("DataValue").addField("StreamID", value.getStreamID()).addField("DateTime", value.getDateTime()).addField("Value", value.getValue()).build();
		
		batchPoints.point(point);
		influxDB.write(batchPoints);
		
		logger.info("Add SensorData to Infux DB, StreamID: {}, DateTime:{}, Value: {}", value.getStreamID(), value.getDateTime(), value.getValue());
//		Query query = new Query("SELECT * FROM DataValue", dbName);
//		QueryResult result = influxDB.query(query);
//		logger.info("Inserted Info:{}",result.getResults().size());
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
