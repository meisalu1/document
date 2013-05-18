package documents.controller.command.general_command;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.model.dao.DocumentTypeDAOImpl;
import documents.model.data.DocumentType;

public class PopulateTypes implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int result = 0;
		
		try {
			Connection connection = DBConnection.connect();
			DocumentTypeDAOImpl typesDAO = new DocumentTypeDAOImpl(connection);
			DocumentType[] types = typesDAO.getAll();
			DBConnection.disconnect(connection);
			request.setAttribute("types", types);
			result = 1;
		} catch (Exception e) {
			MyLogger.Log("PopulateTypes.execute", e.toString());
		}
		
		return result;
	}

}
