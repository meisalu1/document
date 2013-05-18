package documents.model.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import documents.classes.BasicMapper;
import documents.classes.GeneralFunction;
import documents.model.data.Document;

public class DocumentMapper implements BasicMapper<Document> {
	
	GeneralFunction function = new GeneralFunction();

	@Override
	public Document mapRow(ResultSet resultSet) throws SQLException{
		Document doc = null;
		Map<String, String> attrs = new HashMap<String,String>();

		while(resultSet.next()){
			if(doc == null){
				doc = new Document();
				doc.setId(resultSet.getInt("document"));
				doc.setName(resultSet.getString("name"));
				doc.setDesc(resultSet.getString("description"));
				doc.setStatus(resultSet.getInt("doc_status_type_fk"));
				doc.setType(resultSet.getInt("doc_type_fk"));
			}
			switch(resultSet.getInt("data_type")) {
				case 1:
					attrs.put(resultSet.getString("doc_attribute_type_fk"), 
							function.stringIsSet(resultSet.getString("value_text"), ""));
					break;
				case 2:
					attrs.put(resultSet.getString("doc_attribute_type_fk"), 
							function.stringIsSet(resultSet.getString("value_number"), ""));
					break;
				case 3:
					attrs.put(resultSet.getString("doc_attribute_type_fk"), 
							function.stringIsSet(function.sqlDateToString(resultSet.getString("value_date")), ""));
					break;
				case 4:
					attrs.put(resultSet.getString("doc_attribute_type_fk"), 
							function.stringIsSet(resultSet.getString("atr_type_selection_value_fk"), ""));
					break;
			}
			
		}
		doc.setAttrs(attrs);
		
		return doc;
	}

}
