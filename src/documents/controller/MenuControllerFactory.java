package documents.controller;

import java.io.IOException;

import javax.servlet.ServletException;

import documents.classes.MenuController;
import documents.classes.MyLogger;

public class MenuControllerFactory {
	
	public MenuController create(String mode) 
			throws ServletException, IOException  {
		
		MenuController menuController = null;
		
		if(mode.equals("documents")) {
			menuController = new DocMenuController();
		}else if(mode.equals("categories")) {
			menuController = new CatMenuController();
		} else {
			menuController = new DocMenuController();
		}
		
		return menuController;
		
	}

}
