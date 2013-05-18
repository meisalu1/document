package documents.controller.command.doc_command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.controller.DocFormProcessor;
import documents.model.data.Document;

public class DocAddForm implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null){

			request.setAttribute("showform", true);
			DocFormProcessor processor = new DocFormProcessor();
			Document doc = processor.getdocForm(request);
			request.setAttribute("doc", doc);
			request.setAttribute("type_id", doc.getType());			
		}
		return 0;
	}

}
