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
import frontend.DatensammlungSpieler;
import frontend.FrontendBean;


/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Index() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
	
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DatensammlungSpieler.pfad =  request.getServletContext().getRealPath(".");
		HttpSession s = request.getSession(true);
		FrontendBean f = (FrontendBean) getServletContext().getAttribute("f");

		if (f == null) {

			f = new FrontendBean();

			getServletContext().setAttribute("f", f);

		}


		if (f.getS1erstellt() == false & f.getS2erstellt() == false) {
			f.setS1erstellt(true);
			response.sendRedirect("s1Anmelden.jsp");

		}

		else if (f.getS1erstellt() == true) {
			if(f.getAngemeldet1() == false){
				response.sendRedirect("index.jsp");
			}

			if (f.getS2erstellt() == false & f.getAngemeldet1() == true) {
				f.setS2erstellt(true);

				response.sendRedirect("s2Anmelden.jsp");
			}

			else if (f.getS2erstellt() == true &f.getAngemeldet2()==true & f.getSpieleranz() == 3) {
				if(f.getAngemeldet2() == false){
					response.sendRedirect("index.jsp");
				}
				if (f.getS2erstellt() == false) {
					f.setS3erstellt(true);

					response.sendRedirect("s3Anmelden.jsp");
				}
			} else if (f.getS3erstellt() == true & f.getAngemeldet3()==true &  f.getSpieleranz() == 4) {
				if(f.getAngemeldet3() == false){
					response.sendRedirect("index.jsp");
				}
				if (f.getS3erstellt() == false) {
					f.setS3erstellt(true);

					response.sendRedirect("s4anmelden.jsp");
				}
			}
		} else {
			response.sendRedirect("index.jsp");
		}
	}
	
	

}
