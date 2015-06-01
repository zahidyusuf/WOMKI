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
 * Servlet implementation class s4Anmelden
 */
@WebServlet("/s4Anmelden")
public class s4Anmelden extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean Amerika;
	public static boolean China;
	public static boolean Deutschland;
	public static boolean England;
	public static boolean Frankreich;
	public static boolean Russland;
	public static boolean Tuerkei;
	public static int spieleranz;    
	public static boolean angemeldet=false;
	public static boolean s4 = true;
	public static String spielerName;
    public s4Anmelden() {
        super();
        
    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(true);

		FrontendBean f = (FrontendBean) getServletContext().getAttribute("f");
		String okay = request.getParameter("Start");
		String spielerName = request.getParameter("s3Name");
		String nation = request.getParameter("Nation");

		try {
			Amerika = s3Anmelden.Amerika;
			China = s3Anmelden.China;
			Deutschland = s3Anmelden.Deutschland;
			England = s3Anmelden.England;
			Frankreich = s3Anmelden.Frankreich;
			Russland = s3Anmelden.Russland;
			Tuerkei = s3Anmelden.Tuerkei;
			spieleranz = s3Anmelden.spieleranz;
			System.out.println(spieleranz);
			
			s.setAttribute("s4", s4);

			getServletContext().setAttribute("s4Session", s4);


			
			if (request.getParameter("s4Name") != null) {

				s.setAttribute("s4Name", spielerName);
				getServletContext().setAttribute("s4Session", spielerName);
				System.out.println("Spieler 4 erstellt");
			}

			

			if (request.getParameter("Nation") != null) {

				if (request.getParameter("Amerika") != null && Amerika == true) {
					s.setAttribute("s4", Amerika);
					getServletContext().setAttribute("s4Session", Amerika);
					Amerika = false;
				}
				if (request.getParameter("China") != null && China == true) {
					s.setAttribute("s4", China);
					getServletContext().setAttribute("s4Session", China);

					China = false;
				}
				if (request.getParameter("Deutschland") != null
						&& Deutschland == true) {
					s.setAttribute("s4", Deutschland);
					getServletContext().setAttribute("s4Session", Deutschland);

					Deutschland = false;
				}
				if (request.getParameter("England") != null && England == true) {
					s.setAttribute("s4", England);
					getServletContext().setAttribute("s4Session", England);

					England = false;
				}

				if (request.getParameter("Frankreich") != null
						&& Frankreich == true) {
					s.setAttribute("s4", Frankreich);
					getServletContext().setAttribute("s4Session", Frankreich);

					Frankreich = false;
				}

				if (request.getParameter("Russland") != null
						&& Russland == true) {
					s.setAttribute("s4", Russland);
					getServletContext().setAttribute("s4Session", Russland);

					Russland = false;
				}

				if (request.getParameter("Tuerkei") != null && Tuerkei == true) {
					s.setAttribute("s4", Tuerkei);
					getServletContext().setAttribute("s4Session", Tuerkei);

					Tuerkei = false;
				}

				if (okay != null) {
					s.setAttribute("s4", nation);
					s.setAttribute("s4Name", spielerName);
					
					f.anmelden("s4Name");
					getServletContext().setAttribute("s4Session", nation);
					getServletContext().setAttribute("s4Name", spielerName);
					angemeldet = true;
					response.sendRedirect("gamescreen4.jsp");
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		

	}


}
