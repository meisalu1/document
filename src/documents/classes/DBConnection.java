package documents.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBConnection {

	
		
	public static Connection connect() {
		
		Connection connection = null;
		
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("DBConnection");
			Class.forName(bundle.getString("Driver"));
			String url = bundle.getString("url");
			String usr = bundle.getString("usr");
			String pwd = bundle.getString("pwd");
			connection = DriverManager.getConnection(url, usr, pwd);
		} catch (Exception e) {
			MyLogger.Log("DBConnection.connection(): ",e.getMessage());
		}
		return connection;
	}
	
	public static void disconnect(final Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			MyLogger.Log("DBConnection.disconnect() : ",e.getMessage());
		}
	}
	
	public static void closeStatement(final Statement statement) {
		try {
			statement.close();
		} catch (SQLException e) {
			MyLogger.Log("DBConnection.closeStatement() : ",e.getMessage());
		}
	}
	
	public static void closeResultSet(final ResultSet resutlSet) {
		if(resutlSet != null){
			try {
				resutlSet.close();
			} catch (SQLException e) {
				MyLogger.Log("DBConnection.closeResultSet() : ",e.getMessage());
			}
		}
	}
	
}
