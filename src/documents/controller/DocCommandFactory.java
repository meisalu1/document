package documents.controller;

import java.util.LinkedList;
import java.util.Queue;

import documents.classes.Command;
import documents.controller.command.catalog.GetCats;
import documents.controller.command.doc_command.AddDocument;
import documents.controller.command.doc_command.DocAddForm;
import documents.controller.command.doc_command.SearchDocsCommand;
import documents.controller.command.general_command.PopulateDocAttr;
import documents.controller.command.general_command.PopulateDocStatuses;
import documents.controller.command.general_command.PopulateTypes;

public class DocCommandFactory {
	
	public Queue<Command> getCommand(String submode, String event) {
		Queue<Command> commands = new LinkedList<Command>();
		
		if(submode.equals("search")) {
			commands.add(new PopulateTypes());
			commands.add(new PopulateDocStatuses());
			commands.add(new PopulateDocAttr());
			commands.add(new SearchDocsCommand());
		} else if(submode.equals("add_form")){
			commands.add(new PopulateTypes());
			if(event.equals("add_doc_form") || event.equals("add_new")) {
				commands.add(new DocAddForm());
				commands.add(new PopulateDocAttr());
				commands.add(new GetCats());
			}
			if(event.equals("add_new")) {
				commands.add(new AddDocument());
			}
		} else if(submode.equals("view_all")){
			commands.add(new SearchDocsCommand());
		}
		
		return commands;
	}

}
