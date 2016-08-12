package org.cilab.s4rm.controller;

import java.util.List;

import org.cilab.s4rm.model.SensorData;
import org.cilab.s4rm.model.Value;
import org.cilab.s4rm.service.ValueService;
import org.cilab.s4rm.service.impl.ValueServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "values")
@RestController
public class ValueController {

	/**
	 * Class Name: ValueController.java Description: CRUD, Service
	 * 
	 * @author Meilan Jiang
	 * @since 2016.05.23
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private ValueService valueService = new ValueServiceImpl();
	
	private static final Logger logger = LoggerFactory.getLogger(ValueController.class);

	// -------------------- Create a Value Instance Resource ------------------
	@RequestMapping(value = "/values/new", method = RequestMethod.POST)
	public ResponseEntity<Boolean> create(@RequestBody List<SensorData> sDataList) {

		// insert into influx DB installed in the Amazon EC2 - using HTTP API http://52.193.230.38:8086/write?db=wise
		// DB - wise, Measurement - value]
		boolean flag = false;
		for(SensorData sData: sDataList){
			logger.info("StreamID: {}, Value: {}", sData.getId(), sData.getValue());
			Value val = new Value();
			val.setStreamID(sData.getId());
			val.setDateTime(sData.getTimestamp());
			val.setValue(sData.getValue());
			flag = this.valueService.newInstance(val);
		}
		return new ResponseEntity<Boolean>(flag, HttpStatus.CREATED);
	}
	
//	// -------------------- Create a Value Instance Resource ------------------
//		@RequestMapping(value = "/values/new", method = RequestMethod.POST)
//		public ResponseEntity<Boolean> create(@RequestBody SensorData sData) {
//
//			// insert into influx DB installed in the Amazon EC2 - using HTTP API http://52.193.230.38:8086/write?db=wise
//			// DB - wise, Measurement - value]
//			boolean flag = false;
//			logger.info("StreamID: {}, Value: {}", sData.getId(), sData.getValue());
//			Value val = new Value();
//			val.setDataSetID(sData.getId());
//			val.setDateTime(sData.getTimestamp());
//			val.setValue(sData.getValue());
//			flag = this.valueService.newInstance(val);
//			
////			if(flag)
////				return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
////			else
////				return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
//			return new ResponseEntity<Boolean>(flag, HttpStatus.CREATED);
//		}
}