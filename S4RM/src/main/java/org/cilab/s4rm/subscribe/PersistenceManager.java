package org.cilab.s4rm.subscribe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PersistenceManager {

	private static final Logger logger = LoggerFactory.getLogger(PersistenceManager.class);	
	
	private static final ConcurrentHashMap<String, SubscriberRunnable> subscriberMap = new ConcurrentHashMap<String, SubscriberRunnable>();
	private static MyMessageCallback mc = new MyMessageCallback();
	private static final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	
	private String url = "tcp://117.16.146.89:1883";
	private String user = "lejohy@gmail.com";
	private String password = "Cir@817!";

	// designed as singleton class
	private static PersistenceManager instance;
		
	private PersistenceManager() {
//		queue.add("STARTED");
		init();
	}
	
	public static PersistenceManager getInstance (){
		if ( instance == null )
			instance = new PersistenceManager();
		return instance;		
	}
	
	public void init(){
		try {
			// error occurs when using Service or DAO
			logger.info(" ========== Persistence Manager Initiates ... ==========");
			String url = "jdbc:mysql://52.197.130.88:3306/S4RM?characterEncoding=UTF-8";
	        String id = "root";
	        String pw = "mlk2910";
	        Connection con = null;
	       
	        Class.forName("com.mysql.jdbc.Driver");
	        System.out.println("드라이버 로드됨~!");
	        con = DriverManager.getConnection(url, id, pw);
	        
	        String sql = "SELECT * FROM Stream;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
	        while(rs.next()){
	        	if(rs.getInt("Persistence") == 1){
	        		logger.info(" ========== Subscribe Stream: {} ... ==========", rs.getString("StreamID"));
	        		startSubscribe(rs.getString("StreamID"));
	        	}
	        }
//	        for(Stream stream : streamList){
//				if(stream.getPersistence() == 1)
//					startSubscribe(stream.getId());
//			}
	        con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
            System.out.println(" ========== Failed to load Driver!  ========== ");
        } catch (Exception e) {
            System.out.println(" ========== Error:  ========== ");
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
