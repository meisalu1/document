package documents.classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import documents.controller.DocFormProcessor;
import documents.model.dao.AttributeDAOImpl;
import documents.model.dao.DocumentDAOImpl;
import documents.model.data.Attribute;
import documents.model.data.Document;
import documents.model.validator.AttributeValidator;
import documents.model.validator.DocumentValidator;

public class Ajax {
	
	Connection connection;
	DocumentDAOImpl docDAO = null;
	GeneralFunction function = new GeneralFunction();
	Gson gson = new Gson();

	public void ajaxControl(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String json = null;
		PrintWriter out = response.getWriter();
		String ajaxAction = request.getParameter(function.stringIsSet("ajax_action", ""));
		
		if(ajaxAction.equals("edit")){
			int id = function.parseStringToInt(request.getParameter("id"), 0);
			json = getDocument(id);	
		} else if (ajaxAction.equals("editSubmit")) {
			DocFormProcessor processor = new DocFormProcessor();
			Document doc = processor.getdocForm(request);
			json = editDocument(doc);
		} else if(ajaxAction.equals("delete")) {
			int id = function.parseStringToInt(request.getParameter("id"), 0);
			deleteDocument(id);
		} else if (ajaxAction.equals("addCache")) {			
			MyLogger.LogMessage("controller");
			HttpSession session = request.getSession();
			int id = function.parseStringToInt(request.getParameter("id"), 0);	
			json = addToCache(id, session);
		} else if  (ajaxAction.equals("removeCache")) {
			HttpSession session = request.getSession();
			int id = function.parseStringToInt(request.getParameter("id"), 0);	
			removeFromCache(id, session);
		} else if  (ajaxAction.equals("emptyCache")) {
			HttpSession session = request.getSession();
			session.removeAttribute("cache");
		} else if  (ajaxAction.equals("removeSelectedCache")) {
			HttpSession session = request.getSession();
			String selected = function.stringIsSet(request.getParameter("selected"), "");	
			removeSelected(selected, session);
		}
		
		out.print(json);
	}
	
	private String getDocument(int id){
		
		MyLogger.LogMessage("Ajax edit");
		
		Document doc = new Document();
		Attribute[] attrs;
			
		connection = DBConnection.connect();
		docDAO = new DocumentDAOImpl(connection);	
		doc = docDAO.getById(id);
		AttributeDAOImpl attrDAO = new AttributeDAOImpl(connection);
		attrs = attrDAO.getByDocTypeId(doc.getType());
		DBConnection.disconnect(connection);
		
		Object[] toJSON = new Object[2];
		toJSON[0] = doc;
		toJSON[1] = attrs;

		return gson.toJson(toJSON);
	}
	
	private String editDocument(Document doc) {

		Map<String, String> errors = null;
		Gson gson = new Gson();
		Object[] msg = new Object[2];
		AttributeDAOImpl attrDAO;
		DocumentDAOImpl docDAO;
		Attribute[] attrs;
		
		connection = DBConnection.connect();
		attrDAO = new AttributeDAOImpl(connection);
		attrs = attrDAO.getByDocTypeId(doc.getType());
		DBConnection.disconnect(connection);
		
		DocumentValidator docValid = new DocumentValidator(doc);
		AttributeValidator attrValid = new AttributeValidator(attrs, doc.getAttrs());
		
		docValid.validate();
		attrValid.validate();
		
		if(docValid.isValid() && attrValid.isValid()) {
			connection = DBConnection.connect();
			attrDAO = new AttributeDAOImpl(connection);
			attrDAO.update(attrs, doc.getId());
			docDAO = new DocumentDAOImpl(connection);
			docDAO.update(doc);
			DBConnection.disconnect(connection);
			msg[0] = true;
			msg[1] = "Dokument on edukalt uuendatud";
		} else {
			errors = docValid.getErrors();
			errors.putAll(attrValid.getErrors());
			msg[0] = false;
			msg[1] = errors;
		}
	
		return gson.toJson(msg);
	}
	
	private void deleteDocument(int id) {

		connection = DBConnection.connect();
		docDAO = new DocumentDAOImpl(connection);
		docDAO.delete(id);
		DBConnection.disconnect(connection);
	}
	
	private String addToCache(int id, HttpSession session) {
		
		Map<String, Document> docs;
		
		if(session.getAttribute("cache") != null) {
			docs = (HashMap<String, Document>) session.getAttribute("cache");
		} else {
			docs = new HashMap<String, Document>();
		}
	
		connection = DBConnection.connect();
		docDAO = new DocumentDAOImpl(connection);
		Document doc = docDAO.getById(id);
		DBConnection.disconnect(connection);
		docs.put(Integer.toString(id), doc);
		
		session.setAttribute("cache", docs);

		return gson.toJson(doc);
	}
	
	private void removeFromCache(int id, HttpSession session) {
		
		HashMap<String, Document> docs;
		
		if(session.getAttribute("cache") != null) {
			docs = (HashMap<String, Document>) session.getAttribute("cache");
		} else {
			docs = new HashMap<String, Document>();
		}
		
		docs.remove(Integer.toString(id));
		
		session.setAttribute("cache", docs);
	}
	
	private void removeSelected(String selected, HttpSession session) {
		
		String[] selectedArray = selected.split(",");
		HashMap<String, Document> docs;
		
		if(session.getAttribute("cache") != null) {
			docs = (HashMap<String, Document>) session.getAttribute("cache");
		} else {
			docs = new HashMap<String, Document>();
		}
		for(String i : selectedArray) {
			docs.remove(i);
		}
		session.setAttribute("cache", docs);
	}

}
