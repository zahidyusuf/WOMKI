package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frontend.FrontendBean;


@WebServlet("/MenueLeisteRechts")
public class MenueLeisteRechts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MenueLeisteRechts() {
        super();
    
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(true);

		FrontendBean f = (FrontendBean) getServletContext().getAttribute("f");
 
		
	
	}
	 
	 
	 

}
