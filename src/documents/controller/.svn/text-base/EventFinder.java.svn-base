package documents.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EventFinder {
	
	public String[] findEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] event = new String[2];
		
		String mode = request.getParameter("mode");	
		String action = request.getParameter("action");
		
		if(action == null || action.equals("login") || action.equals("search")) {
			event[0] = "search";
		} else if (action.equals("add")) {
			event[0] = "form";
		} else if (action.equals("view")) {
			event[0] = "view";
		} else if (action.equals("insert")) {
			event[0] = "insert";
		} else if (action.equals("remove")) {
			event[0] = "remove";
		} else if (action.equals("update")) {
			event[0] = "update";
		}
		
		if(mode == null || mode.equals("documents") ){
			event[1] = "documents";
		} else if (mode.equals("categories")){
			event[1] = "category";
		}
		
		return event;
	}

}
