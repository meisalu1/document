package documents.model.action;

import java.sql.Connection;

import documents.classes.DBConnection;
import documents.form.SearchForm;
import documents.model.dao.DocumentDAOImpl;
import documents.model.data.Document;

public class DocServicesImpl implements DocServices {
	
	

	@Override
	public Document[] search(SearchForm form) {
		
		Connection connection = DBConnection.connect();
		
		DocumentDAOImpl docDAO = new DocumentDAOImpl(connection);
		Document[] docs = docDAO.getAll(form);
		DBConnection.disconnect(connection);
		return docs;
		
	}


}
