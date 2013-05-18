package documents.controller.command.catalog;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import documents.classes.Command;
import documents.classes.MyLogger;
import documents.model.dao.CatalogDAOImpl;
import documents.model.data.Catalog;

public class GetCats implements Command {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		CatalogDAOImpl catDAO = new CatalogDAOImpl();
		List<Catalog> cats = catDAO.getAllCatalogs();
		request.setAttribute("catalogs", cats);
		return 0;
	}

}
