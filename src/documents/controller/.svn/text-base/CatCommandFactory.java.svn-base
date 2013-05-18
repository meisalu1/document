package documents.controller;

import java.util.LinkedList;
import java.util.Queue;

import documents.classes.Command;
import documents.controller.command.catalog.GetCatDocuments;
import documents.controller.command.catalog.GetCats;
import documents.controller.command.general_command.PopulateDocStatuses;

public class CatCommandFactory {
	
	public Queue<Command> getCommand(String submode, String event) {
		Queue<Command> commands = new LinkedList<Command>();

		if(submode.contains("cat_")){
			commands.add(new GetCats());
			commands.add(new PopulateDocStatuses());
			commands.add(new GetCatDocuments());
		}
		
		return commands;
	}

}
