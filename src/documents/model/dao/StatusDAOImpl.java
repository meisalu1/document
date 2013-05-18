package documents.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.model.data.Status;
import documents.model.mapper.StatusMapper;



public class StatusDAOImpl implements GeneralDAO<Status> {
	
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet statusSet = null;
	private Status status = null;
	private StatusMapper mapper = new StatusMapper();

	public StatusDAOImpl(Connection connection){
		try{
			this.connection = connection;
		} catch (Exception e) {
			MyLogger.Log("StatusDAOImpl", e.getMessage());
		}
	}
	
	@Override
	public Status[] getAll() {	
		
		String sql = "SELECT doc_status_type, type_name FROM doc_status_type ORDER BY doc_status_type ASC";
		int statusCount = 0;
		Queue<Status> statusQueue = new LinkedList<Status>();
		Status[] statusArray = null;
		
		try {
			
			statement = connection.createStatement();
			statusSet = statement.executeQuery(sql);	
			
			while(statusSet.next()){
				status = mapper.mapRow(statusSet);
				statusQueue.add(status);
			}
			
			statusArray = new Status[statusQueue.size()];
			
			while(!statusQueue.isEmpty()){
				statusArray[statusCount] = statusQueue.poll();
				statusCount++;
			}
		
		} catch (Exception e) {
			MyLogger.Log("StatusDAOImpl.getAll()", e.getMessage());
		} finally {
			DBConnection.closeResultSet(statusSet);
			DBConnection.closeStatement(statement);
		}
		return statusArray;
	}

}
