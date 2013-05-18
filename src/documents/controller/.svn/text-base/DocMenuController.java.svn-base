package documents.controller;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.MenuController;
import documents.classes.MyLogger;

public class DocMenuController implements MenuController {

	@Override
	public String service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String view = "document_search";
		String action = "";
		String submode = "search";
		
		try {
	
			DocCommandFactory docCommandFactory = new DocCommandFactory(); 
			submode = (request.getParameter("submode") == null ? submode : request.getParameter("submode"));
			action = (request.getParameter("action") == null ? action : request.getParameter("action"));
			Queue<Command> commands = docCommandFactory.getCommand(submode, action);
			while(!commands.isEmpty()){
				commands.poll().execute(request, response);
			}
			
			MyLogger.LogMessage("doc "+ submode + " " + action);
			
			if(submode.equals("search")){
				view = "document_search";
			} else if (submode.equals("add_form")) {
				view = "add_document";
			} else if(submode.equals("view_all")) {
				view = "all_docs";
			}
			
			MyLogger.LogMessage(view);
		
		} catch (Exception e){
			MyLogger.Log("DocMenuController.service", e.toString());
		}
		return view;
	}

}
