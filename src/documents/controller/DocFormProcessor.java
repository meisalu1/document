package documents.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import documents.classes.GeneralFunction;
import documents.form.SearchForm;
import documents.model.data.Document;

public class DocFormProcessor {
	
	GeneralFunction functions = new GeneralFunction();
	
	public SearchForm getDocSearchForm(HttpServletRequest request) {
		
		SearchForm form = new SearchForm();
				
		form.setId(functions.stringIsSet(request.getParameter("id"), ""));
		form.setName(functions.stringIsSet(request.getParameter("name"), ""));
		form.setDesc(functions.stringIsSet(request.getParameter("desc"), ""));
		form.setLast_modifier(functions.stringIsSet(request.getParameter("last_changer"), ""));
		form.setCat_name(functions.stringIsSet(request.getParameter("cat_name"), ""));
		form.setSubject(functions.stringIsSet(request.getParameter("subject"), ""));
		form.setCurrentStatusID(functions.parseStringToInt(request.getParameter("status"), 0));
		form.setCurrentTypeID(functions.parseStringToInt(request.getParameter("type"), 0));
		form.setAttribute(functions.stringIsSet(request.getParameter("attribute"), ""));
		form.setAttributes(functions.getAttributesValues(request));
		
		return form;
	}
	
	public Document getdocForm(HttpServletRequest request){
		
		Document form = new Document();
		HttpSession session = request.getSession();
		
		form.setType(Integer.parseInt(request.getParameter("type_id") != null ? request.getParameter("type_id") : request.getParameter("type")));
		form.setName(functions.stringIsSet(request.getParameter("name"), ""));
		form.setDesc(functions.stringIsSet(request.getParameter("desc"), ""));
		form.setId(functions.parseStringToInt(request.getParameter("id"), 0));
		
		form.setAttrs(functions.getAttributesValues(request));
		
		form.setCat_id(functions.parseStringToInt(request.getParameter("cat_id"), 0));
		form.setCreated_by(Integer.parseInt(session.getAttribute("user_id").toString()));
		form.setStatus(functions.parseStringToInt(request.getParameter("status"), 3));
		
		return form;
	}

}
