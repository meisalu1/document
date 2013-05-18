package documents.controller.command.general_command;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.model.dao.StatusDAOImpl;
import documents.model.data.Status;

public class PopulateDocStatuses implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int result = 0;
		
		try {
			Connection connection = DBConnection.connect();
			StatusDAOImpl statusDAO = new StatusDAOImpl(connection);
			Status[] statuses = statusDAO.getAll();
			request.setAttribute("statuses", statuses);
			DBConnection.disconnect(connection);
			result = 1;
		} catch ( Exception e ) {
			MyLogger.Log("PopulateDocStatuses.execute", e.toString());
		}
		
		return result;
	}

}
