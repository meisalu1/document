package documents.controller.command.doc_command;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.controller.DocFormProcessor;
import documents.form.SearchForm;
import documents.model.dao.DocumentDAOImpl;
import documents.model.data.Document;

public class SearchDocsCommand implements Command {
	
	DocFormProcessor docFormProcessor = new DocFormProcessor();

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		int result = 0;
		
		request.setAttribute("addEmpty", true);
		
		try {
			
			SearchForm searchForm = docFormProcessor.getDocSearchForm(request);
			
			Connection connection = DBConnection.connect();
			DocumentDAOImpl docDAO = new DocumentDAOImpl(connection);
			Document[] docs = docDAO.getAll(searchForm);
			DBConnection.disconnect(connection);
			
			request.setAttribute("searchForm", searchForm);
			request.setAttribute("docs", docs);
			
		} catch (Exception e){
			MyLogger.Log("SearchDocsCommand.execute()", e.toString());
		}
		return result;
	}

}
