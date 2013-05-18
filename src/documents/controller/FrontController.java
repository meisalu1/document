package documents.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import documents.classes.Ajax;
import documents.classes.MenuController;
import documents.classes.MyLogger;
import documents.model.dao.UserAuth;
import documents.model.dao.UserAuthImpl;
import documents.model.data.User;


@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	try {
			super.init(config);
		} catch (ServletException e) {
			MyLogger.Log("FrontController.init():",e.getMessage());
		}
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String action = (request.getParameter("action") == null ? "" : request.getParameter("action"));
		String view = "login";
		try {
			if(session.getAttribute("user_id") == null && action.equals("login")){
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				if(username != null && !username.trim().equals("") && password != null & !password.trim().equals("")){
					UserAuth userAuth = new UserAuthImpl();
					User user = userAuth.getUser(username, password);
					if(user != null){
						session.setAttribute("first_name",user.getFirst_name());
						session.setAttribute("last_name",user.getLast_name());
						session.setAttribute("user_id",user.getId());
						MenuControllerFactory menuControllerFactory = new MenuControllerFactory(); 
						MenuController menuController = menuControllerFactory.create("");
						view = menuController.service(request, response);
					} else {
						//errors
					}
				} 		
			} else if(session.getAttribute("user_id") != null) {
				if(action.equals("logout")) {
					session.removeAttribute("first_name");
					session.removeAttribute("last_name");
					session.removeAttribute("user_id");
				} else if(action.equals("ajax")){
					Ajax ajax = new Ajax();
					ajax.ajaxControl(request, response);
				} else {
					view = "document_search";
					String mode = (request.getParameter("mode") == null ? "" : request.getParameter("mode"));
					MenuControllerFactory menuControllerFactory = new MenuControllerFactory(); 
					MenuController menuController = menuControllerFactory.create(mode);
					view = menuController.service(request, response);
				}
			}
		} catch (Exception e) {
			MyLogger.Log("FrontController.doPost()", e.toString());
		}
		if(!action.equals("ajax"))
			getServletConfig().getServletContext().getRequestDispatcher(String.format("/%s.jsp", view)).forward(request, response);
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
