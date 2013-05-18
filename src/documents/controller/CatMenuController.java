package documents.controller;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.MenuController;
import documents.classes.MyLogger;

public class CatMenuController implements MenuController{

	@Override
	public String service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String view = "catalog";
		String action = "";
		String submode = "cat_1";
		
		try {
	
			CatCommandFactory catCommandFactory = new CatCommandFactory(); 
			submode = (request.getParameter("submode") == null ? submode : request.getParameter("submode"));
			action = (request.getParameter("action") == null ? action : request.getParameter("action"));
			Queue<Command> commands = catCommandFactory.getCommand(submode, action);
			while(!commands.isEmpty()){
				commands.poll().execute(request, response);
			}
			
			if(submode.contains("cat_")){
				view = "catalog";
			}
		
		} catch (Exception e){
			MyLogger.Log("DocMenuController.service", e.toString());
		}
		return view;
	}

}
