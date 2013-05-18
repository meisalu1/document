package documents.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import documents.classes.BasicMapper;
import documents.model.data.Status;

public class StatusMapper implements BasicMapper<Status> {

	@Override
	public Status mapRow(ResultSet resultSet) throws SQLException {
		Status status = new Status();
		status.setName(resultSet.getString("type_name"));
		status.setId(resultSet.getInt("doc_status_type"));
		return status;
	}

}
