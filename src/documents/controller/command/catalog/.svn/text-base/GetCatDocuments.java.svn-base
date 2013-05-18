package documents.controller.command.catalog;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.DBConnection;
import documents.model.dao.DocumentDAOImpl;
import documents.model.data.Document;

public class GetCatDocuments implements Command {

	
	
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection connection;
		DocumentDAOImpl docDAO;
		Document[] docs;
		String cat = request.getParameter("submode");
		
		
		if(cat != null){
			int id = Integer.parseInt(cat.replace("cat_", ""));
			connection = DBConnection.connect();
			docDAO = new DocumentDAOImpl(connection);
			docs = docDAO.getByCatalog(id);
			DBConnection.disconnect(connection);
			request.setAttribute("docs", docs);			
		}
		return 0;
	}

}
