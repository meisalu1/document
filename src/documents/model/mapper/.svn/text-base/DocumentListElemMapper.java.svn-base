package documents.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import documents.classes.BasicMapper;
import documents.model.data.Document;

public class DocumentListElemMapper implements BasicMapper<Document>{

	@Override
	public Document mapRow(ResultSet resultSet) throws SQLException {
		Document doc = new Document();
		
		doc.setId(resultSet.getInt("document"));
		doc.setName(resultSet.getString("name"));
		doc.setDesc(resultSet.getString("description"));
			
		return doc;
	}

}
