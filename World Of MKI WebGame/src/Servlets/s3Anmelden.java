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
 * Servlet implementation class s3Anmelden
 */
@WebServlet("/s3Anmelden")
public class s3Anmelden extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean Amerika = true;
	public static boolean China = true;
	public static boolean Deutschland = true;
	public static boolean England =true;
	public static boolean Frankreich =true;
	public static boolean Russland =true;
	public static boolean Tuerkei =true;
	public static int spieleranz;   
	public static boolean angemeldet=false;
	public static boolean s3 = true;
	public static String spielerName;
    public s3Anmelden() {
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
		String spieler = request.getParameter("Spieler");
		String nation = request.getParameter("Nation");

		try {
			Amerika = f.getAmerika2();
			China = f.getChina2();
			Deutschland = f.getDeutschland2();
			England = f.getEngland2();
			Frankreich = f.getFrankreich2();
			Russland = f.getRussland2();
			Tuerkei = f.getTuerkei2();
			int spieleranz = s1Anmelden.spieleranz;
			System.out.println(spieleranz);
			
			s.setAttribute("s3", s3);

			getServletContext().setAttribute("s3Session", s3);

			//
			// if (request.getParameter("s2Name").length() < 1) {
			//
			// f.setMeldungNachricht("Fehler Spielername !");
			//
			// response.sendRedirect("s2Anmelden.jsp");
			// }
			if (request.getParameter("s3Name") != null) {

				s.setAttribute("s3Name", spielerName);
				getServletContext().setAttribute("s3Session", spielerName);
				System.out.println("Spieler 3 erstellt");
			}

		
			// if (request.getParameter("4Spieler") != null && spieleranz ==4) {
			//
			// getServletContext().setAttribute("s3Session", spieler);
			//
			// }

			if (request.getParameter("Nation") != null) {

				if ("Amerika".equals(nation) & f.getAmerika1() == true) {
					Amerika = false;
					s.setAttribute("s3", f.getAmerika1());
					getServletContext().setAttribute("s3Session", f.getAmerika1());
				}
				else if ("China".equals(nation) & China == true) {
					China = false;
					s.setAttribute("s3", China);
					getServletContext().setAttribute("s3Session", China);

				}
				else if ("Deutschland".equals(nation)&
						 Deutschland == true) {
					Deutschland = false;
					s.setAttribute("s3", Deutschland);
					getServletContext().setAttribute("s3Session", Deutschland);

				}
				else if ("England".equals(nation) & England == true) {
					
					England = false;
					s.setAttribute("s3", England);
					getServletContext().setAttribute("s3Session", England);
				}

				else if ("Frankreich".equals(nation) 
						& Frankreich == true) {
					Frankreich = false;
					s.setAttribute("s3", Frankreich);
					getServletContext().setAttribute("s3Session", Frankreich);

				}

				else if ("Russland".equals(nation)& Russland == true) {
					Russland = false;
					s.setAttribute("s3", Russland);
					getServletContext().setAttribute("s3Session", Russland);

				}

				else if ("Tuerkei".equals(nation) & Tuerkei == true) {
					Tuerkei = false;
					s.setAttribute("s3", Tuerkei);
					getServletContext().setAttribute("s3Session", Tuerkei);


				}

				// if (okay != null && spieleranz == 3 || spieleranz ==4) {

				if (okay != null) {
					s.setAttribute("s3", nation);
					s.setAttribute("s3Name", spielerName);
				
					f.anmelden("s3Name");
					getServletContext().setAttribute("s3Session", nation);
					getServletContext().setAttribute("s3Name", spielerName);
					angemeldet =true;
					response.sendRedirect("Warten.jsp");
					// }
					// else{
					// s.setAttribute("s3", nation);
					// s.setAttribute("s3Name", spielerName);
					// getServletContext().setAttribute("s3Session", nation);
					// getServletContext().setAttribute("s3Name", spielerName);

					// response.sendRedirect("gamescreen2.jsp");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
