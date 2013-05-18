package documents.controller.command.general_command;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.DBConnection;
import documents.classes.GeneralFunction;
import documents.classes.MyLogger;
import documents.model.dao.AttributeDAOImpl;
import documents.model.data.Attribute;

public class PopulateDocAttr implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int result = 0;
		GeneralFunction function = new GeneralFunction();
		int id = function.parseStringToInt((request.getParameter("type") != null ? request.getParameter("type")
				: request.getParameter("type_id")), 0);
		
		try {
			if(id > 0) {
				Connection connection = DBConnection.connect();
				AttributeDAOImpl attributesDAO = new AttributeDAOImpl(connection);
				Attribute[] attributes = attributesDAO.getByDocTypeId(id);
				DBConnection.disconnect(connection);
				Map<String, String> values = function.getAttributesValues(request);
				for(Attribute attr : attributes){
					if(values.get(Integer.toString(attr.getAttribute_type())) != null){
						attr.setDefaultValue(values.get(Integer.toString(attr.getAttribute_type())));
					}
				}
				request.setAttribute("attributes", attributes);
				result = 1;
			}
		} catch ( Exception e ) {
			MyLogger.Log("PopulateDocAttr.execute", e.toString());
		}
		
		return result;
	}

}
