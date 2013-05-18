package documents.controller.command.doc_command;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.DBConnection;
import documents.classes.MyLogger;
import documents.controller.DocFormProcessor;
import documents.model.dao.AttributeDAOImpl;
import documents.model.dao.DocumentDAOImpl;
import documents.model.data.Attribute;
import documents.model.data.Document;
import documents.model.validator.AttributeValidator;
import documents.model.validator.DocumentValidator;

public class AddDocument implements Command {
	
	
	
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
		
		DocFormProcessor processor = new DocFormProcessor();
		Document doc = processor.getdocForm(request);
		Attribute[] attrs = (Attribute[])request.getAttribute("attributes");
		Connection connection;
		int id = 0;
		
		DocumentValidator docValidator = new DocumentValidator(doc);
		AttributeValidator attrsValidator = new AttributeValidator(attrs, doc.getAttrs());
		
		Map<String, String> errors = null;
		docValidator.validate();
		attrsValidator.validate();

		
		if(docValidator.isValid() && attrsValidator.isValid()) {

			connection = DBConnection.connect();
			DocumentDAOImpl docDAO = new DocumentDAOImpl(connection);
			id = docDAO.insert(doc);
			if(attrs.length > 0){
				AttributeDAOImpl attrDAO = new AttributeDAOImpl(connection);
				attrDAO.insertDocAttributes(attrs, id);
			}
			DBConnection.disconnect(connection);
		} else {
			errors = docValidator.getErrors();
			errors.putAll(attrsValidator.getErrors());
			request.setAttribute("errors", errors);
		}
		
		} catch (Exception e) {
			MyLogger.Log("AddDocument.execute()", e.toString());
		}
		// TODO Auto-generated method stub
		return 0;
	}

}
