package org.cilab.s4rm.model;

public class PersistenceLog {

	/**
	 * Class Name: PersistenceLog.java 
	 * Description: 
	 * 
	 * @author Meilan Jiang
	 * @since 2016.06.10
	 * @version 1.2
	 * 
	 * Copyright(c) 2016 by CILAB All right reserved.
	 */
	
	private int LogID;
	private String StreamID;
	private String StartTime;
	private String EndTime;
	
	public int getLogID() {
		return LogID;
	}
	public void setLogID(int logID) {
		LogID = logID;
	}
	public String getStreamID() {
		return StreamID;
	}
	public void setStreamID(String streamID) {
		StreamID = streamID;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}	
}
