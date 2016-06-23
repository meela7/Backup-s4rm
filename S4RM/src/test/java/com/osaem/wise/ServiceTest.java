package com.osaem.wise;

import org.cilab.s4rm.model.Value;
import org.cilab.s4rm.service.ValueService;
import org.cilab.s4rm.service.impl.ValueServiceImpl;
import org.junit.Test;


public class ServiceTest {

	private ValueService vService = new ValueServiceImpl();
	
	@Test
	public void testService() {
		Value value = new Value();
		value.setStreamID("59126PTY");
		value.setDateTime("test");
		value.setValue(1.5);
		vService.newInstance(value);	
		
	}
}
