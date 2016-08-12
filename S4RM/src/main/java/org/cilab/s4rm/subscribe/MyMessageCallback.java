package org.cilab.s4rm.subscribe;

import java.io.IOException;

import org.cilab.agabus.messaging.sub.MessageCallback;
import org.cilab.s4rm.model.SensorData;
import org.cilab.s4rm.service.ValueService;
import org.cilab.s4rm.service.impl.ValueServiceImpl;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

class MyMessageCallback extends MessageCallback {
	
	public static final Logger logger = LoggerFactory
			.getLogger(MyMessageCallback.class);
	private ValueService valueService = new ValueServiceImpl();
	
	@Override
	public void messageArrived(String data) {
		
		logger.debug(" ===== Subscribed Data: {} ===== ", data);
		saveData2DB(data);
	}

	@Override
	public void connectionLost(Throwable cause) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// TODO Auto-generated method stub

	}

	public void saveData2DB(String data) {
		ObjectMapper mapper = new ObjectMapper();
		
		SensorData sData = new SensorData();
		try {
			if(data != null)
				sData = mapper.readValue(data, SensorData.class);
			else
				logger.debug(" ===== Subscribed NULL ===== ");
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.valueService.newInstance(sData);
				
	}
}
