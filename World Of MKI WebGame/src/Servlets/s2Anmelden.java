package Servlets;

import java.io.File;
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
 * Servlet implementation class s2Anmelden
 */
@WebServlet("/s2Anmelden")
public class s2Anmelden extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean Amerika;
	public static boolean China ;
	public static boolean Deutschland;
	public static boolean England ;
	public static boolean Frankreich;
	public static boolean Russland ;
	public static boolean Tuerkei ;
	public static String spielerName;
 	public static boolean angemeldet=false;
	public static boolean s2 = true;

	public s2Anmelden() {
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
		String okay = request.getParameter("Start");
		String spielerName = request.getParameter("s2Name");
//		String spieler = request.getParameter("Spieler");
		String nation = request.getParameter("Nation");

		try {

			
			int spieleranz = f.getSpieleranz();
			System.out.println(spieleranz);
			
			s.setAttribute("s2", s2);

			getServletContext().setAttribute("s2Session", s2);

			//
			// if (request.getParameter("s2Name").length() < 1) {
			//
			// f.setMeldungNachricht("Fehler Spielername !");
			//
			// response.sendRedirect("s2Anmelden.jsp");
			// }
			if (request.getParameter("s2Name") != null) {

				s.setAttribute("s2Name", spielerName);
				getServletContext().setAttribute("s2Session", spielerName);
				System.out.println("Spieler 2 erstellt");
			}

			// if (request.getParameter("3Spieler") != null && spieleranz ==3) {
			//
			// getServletContext().setAttribute("s2Session", spieler);
			//
			// }
			//
			// if (request.getParameter("4Spieler") != null && spieleranz ==4) {
			//
			// getServletContext().setAttribute("s2Session", spieler);
			//
			// }

//			if (request.getParameter("Nation") != null) {

				if ("Amerika".equals(nation) & f.getAmerika1() == true) {
					Amerika = false;
					s.setAttribute("s2", f.getAmerika1());
					getServletContext().setAttribute("s2Session", f.getAmerika1());
				}
				else if ("China".equals(nation) & China == true) {
					China = false;
					s.setAttribute("s2", China);
					getServletContext().setAttribute("s2Session", China);

				}
				else if ("Deutschland".equals(nation)&
						 Deutschland == true) {
					Deutschland = false;
					s.setAttribute("s2", Deutschland);
					getServletContext().setAttribute("s2Session", Deutschland);

				}
				else if ("England".equals(nation) & England == true) {
					
					England = false;
					s.setAttribute("s2", England);
					getServletContext().setAttribute("s2Session", England);
				}

				else if ("Frankreich".equals(nation) 
						& Frankreich == true) {
					Frankreich = false;
					s.setAttribute("s2", Frankreich);
					getServletContext().setAttribute("s2Session", Frankreich);

				}

				else if ("Russland".equals(nation)& Russland == true) {
					Russland = false;
					s.setAttribute("s2", Russland);
					getServletContext().setAttribute("s2Session", Russland);

				}

				else if ("Tuerkei".equals(nation) & Tuerkei == true) {
					Tuerkei = false;
					s.setAttribute("s2", Tuerkei);
					getServletContext().setAttribute("s2Session", Tuerkei);


				}
				// if (okay != null && spieleranz == 3 || spieleranz ==4) {

				if (okay != null) {
					
		
					s.setAttribute("s2", nation);
					s.setAttribute("s2Name", spielerName);
					
					f.anmelden("s2Name");
					getServletContext().setAttribute("s2Session", nation);
					getServletContext().setAttribute("s2Name", spielerName);
					f.spielerHinzufuegen(spielerName, nation);
					angemeldet =true;
					
					if(f.getSpieleranz() == 2){
						
						//f.getBackend().neuesSpiel(DatensammlungSpieler.pfad+"/WebContent/daten/Maps/"+DatensammlungSpieler.karte+".map", DatensammlungSpieler.spieler);
						f.getBackend().neuesSpiel("C:\\daten\\Maps\\"+DatensammlungSpieler.karte+".map", DatensammlungSpieler.spieler);
							
					}
					
					response.sendRedirect("Warten.jsp");
					// }
					// else{
					// s.setAttribute("s2", nation);
					// s.setAttribute("s2Name", spielerName);
					// getServletContext().setAttribute("s2Session", nation);
					// getServletContext().setAttribute("s2Name", spielerName);

					// response.sendRedirect("gamescreen2.jsp");

				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
