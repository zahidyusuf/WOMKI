package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daten.dSpieler;
import frontend.FrontendBean;

/**
 * Servlet implementation class Warten
 */
@WebServlet("/Warten")
public class Warten extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static  boolean spielstart = false;
	public Warten() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession s = request.getSession(true);
		FrontendBean f = (FrontendBean) getServletContext().getAttribute("f");
		String weiter = request.getParameter("Reload");
		
		
		if (f.getSpieleranz() == 2 & weiter != null) {
			if (f.getAngemeldet1() == true & f.getAngemeldet2() == true) {

				spielstart = true;
				response.sendRedirect("LadeKarte");

			} else if (f.getAngemeldet1() == false || f.getAngemeldet1() == true
					& f.getAngemeldet2() == true) {
				response.sendRedirect("Warten.jsp");
			}

		}
		
		else if(f.getSpieleranz() == 3 & weiter != null){
			if(f.getAngemeldet1() == true & f.getAngemeldet2() == true & f.getAngemeldet3() == true){
				
				spielstart = true;
				response.sendRedirect("LadeKarte");
			}
		}else if(f.getAngemeldet1() == false || f.getAngemeldet1() == true & f.getAngemeldet2() == false ||
				f.getAngemeldet1() == true & f.getAngemeldet2() == true & f.getAngemeldet3() == false){
			response.sendRedirect("Warten.jsp");
		}

		
		else if(f.getSpieleranz() == 4 & weiter != null){
			if(f.getAngemeldet1() == true & f.getAngemeldet2() == true 
					& f.getAngemeldet3() == true & f.getAngemeldet4() == true){
				
				spielstart = true;
				response.sendRedirect("LadeKarte");
			}
		}else if(f.getAngemeldet1() == false || f.getAngemeldet1() == true & f.getAngemeldet2() == false ||
				f.getAngemeldet1() == true & f.getAngemeldet2() == true & f.getAngemeldet3() == false || 
				f.getAngemeldet1() == true & f.getAngemeldet2() ==true & f.getAngemeldet3() == true & 
				f.getAngemeldet4() == false){
			response.sendRedirect("Warten.jsp");
		}
		
	
	
}


}
