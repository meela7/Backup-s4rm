package org.cilab.s4rm.subscribe;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.cilab.s4rm.dao.StreamDAO;
import org.cilab.s4rm.model.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PersistenceManager2 {

	private static final Logger logger = LoggerFactory.getLogger(PersistenceManager2.class);	
	
	private StreamDAO streamDao;
	private static final ConcurrentHashMap<String, SubscriberRunnable> subscriberMap = new ConcurrentHashMap<String, SubscriberRunnable>();
	private MyMessageCallback mc = new MyMessageCallback();
	private static final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	
	private String url; // = "tcp://117.16.146.89:1883";
	private String user; // = "lejohy@gmail.com";
	private String password; // = "Cir@817!";

	// designed as singleton class
	private static PersistenceManager2 instance;
		
	private PersistenceManager2() {
//		queue.add("STARTED");
		init();
	}
	
	public static PersistenceManager2 getInstance (){
		if ( instance == null )
			instance = new PersistenceManager2();
		return instance;		
	}
	
	public void setStreamDao(StreamDAO streamDao){
		this.streamDao = streamDao;
	}
	
	public void setUrl (String url){
		this.url = url;
	}
	
	public void setUser (String user){
		this.user = user;
	}
	
	public void setPassword (String password){
		this.password = password;
	}
	
	public void init(){
		List<Stream> streamList = null;
		try {
			streamList = streamDao.list();	// error occurs
			for(Stream stream : streamList){
				if(stream.getPersistence() == 1)
					startSubscribe(stream.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void startSubscribe(String streamID){
		SubscriberRunnable subscriber = new SubscriberRunnable(queue, streamID);
		subscriber.setConnection(url, user, password, streamID);
		subscriber.setMessageCallback(mc);
		subscriber.subscribe();
		subscriberMap.put(streamID, subscriber);
	}
	
	public void stopSubscribe(String streamID){
		try {
			queue.put("STOP");
				
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		subscriberMap.get(streamID).unsubscribe();
	}
	
	public void stopSubscribe(){
		for(String s: subscriberMap.keySet()){
			subscriberMap.get(s).unsubscribe();
		}
	}
	
	public Set<String> getSubscriberList(){
		
		return subscriberMap.keySet();
	}

}
