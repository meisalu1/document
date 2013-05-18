package documents.model.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.model.data.User;
import documents.model.mapper.UserMapper;

public class UserAuthImpl implements UserAuth {
	
	@Override
	public User getUser(String name, String password) {
		
		UserMapper mapper = new UserMapper();
		Statement statement = null;
		Connection connection = null;
		ResultSet userSet = null;		
		User user = new User();
		int user_count = 0;
		
		String sql = String.format("SELECT E.employee,UA.user_account, P.first_name, P.last_name " +
				"FROM employee E " +
				"INNER JOIN user_account UA ON E.employee = UA.subject_fk " +
				"INNER JOIN person P ON E.person_fk = P.person " +
				"WHERE UA.subject_type_fk = 3  " +
				"AND UA.username='%s'  " +
				"AND UA.passw=md5('%s');", name, password);
		 
		try {
			
			connection = DBConnection.connect();
			statement = connection.createStatement();
			userSet = statement.executeQuery(sql);	
			
			while(userSet.next()){
				user_count++;
				user = mapper.mapRow(userSet);
			}
			
		} catch (SQLException e) {
			MyLogger.Log(String.format("UserAuthImpl.getUser(%s) : ", name), e.getMessage());
		} finally {
			DBConnection.closeResultSet(userSet);
			DBConnection.closeStatement(statement);
			DBConnection.disconnect(connection);
		}

		return (user_count == 1 ? user : null);

	}

}
