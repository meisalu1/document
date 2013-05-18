package documents.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import documents.classes.BasicMapper;
import documents.model.data.User;

public class UserMapper implements BasicMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setFirst_name(resultSet.getString("first_name"));
		user.setLast_name(resultSet.getString("last_name"));
		user.setId(resultSet.getInt("user_account"));
		return user;
	}

}
