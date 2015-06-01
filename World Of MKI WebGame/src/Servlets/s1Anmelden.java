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
import errorHandling.SpielerException;
import frontend.DatensammlungSpieler;
import frontend.FrontendBean;

@WebServlet("/s1Anmelden")
public class s1Anmelden extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static boolean Amerika = true;
	public static boolean China = true;
	public static boolean Deutschland = true;
	public static boolean England = true;
	public static boolean Frankreich = true;
	public static boolean Russland = true;
	public static boolean Tuerkei = true;
	public static int spieleranz;
	public static boolean s1 = true;
	public static boolean angemeldet = false;
	public static String spielerName;
	public static String karte[];
	
	public s1Anmelden() {
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
		System.out.println("s1anmelden funktioniert");
		HttpSession s = request.getSession(true);

		FrontendBean f = (FrontendBean) getServletContext().getAttribute("f");

		String inseln = request.getParameter("Inseln");
		String pangaea = request.getParameter("Pangaea");
		String kontinente = request.getParameter("Kontinente");
		String seen = request.getParameter("Seen");
		String spiegel = request.getParameter("Spiegel");
		DatensammlungSpieler.karte = request.getParameter("Datei");
		
		String spieler = request.getParameter("Spieler");
		String spielerName = request.getParameter("s1Name");
		String okay = request.getParameter("GameStart");
		
		String nation = request.getParameter("Nation");
		
		
		try {

			
			
			boolean Inseln = true; boolean Pangaea = true; boolean Kontinente=true;
			boolean Seen = true; boolean Spiegel = true;

			s.setAttribute("s1", s1);

			getServletContext().setAttribute("s1Session", s1);
			
			
			if("Inseln".equals(karte)){
				Pangaea = false; Kontinente = false; Seen = false;
				Spiegel = false;
				s.setAttribute("Inseln", Inseln);
				getServletContext().setAttribute("s1Session", karte);
			}
			if("Pangaea".equals(karte)){
				Inseln = false; Kontinente = false; Seen = false;
				Spiegel = false;
				s.setAttribute("Pangaea", Pangaea);
				getServletContext().setAttribute("s1Session", karte);
			}
			if("Kontinente".equals(karte)){
				Pangaea = false; Inseln = false; Seen = false;
				Spiegel = false;
				s.setAttribute("Kontinente", Kontinente);
				getServletContext().setAttribute("s1Session", karte);
			}
			if("Seen".equals(karte)){
				Pangaea = false; Kontinente = false; Inseln = false;
				Spiegel = false;
				s.setAttribute("Seen", Seen);
				getServletContext().setAttribute("s1Session", karte);
			}
			if("Spiegel".equals(karte)){
				Pangaea = false; Kontinente = false; Seen = false;
				Inseln = false;
				s.setAttribute("Spiegel", Spiegel);
				getServletContext().setAttribute("s1Session", karte);
			}
						
			

			if ( "2Spieler".equals(spieler)) {
				// Spieleranzahl in radiobuttoruens
				spieleranz = 2;
				s.setAttribute("Spieler", spieleranz);
				getServletContext().setAttribute("s1Session", spieler);

			}

			if ("3Spieler".equals(spieler)) {
				spieleranz = 3;
				s.setAttribute("Spieler", spieleranz);
				getServletContext().setAttribute("s1Session", spieler);
			}

			if ("4Spieler".equals(spieler)) {
				spieleranz = 4;
				s.setAttribute("Spieler", spieleranz);
				getServletContext().setAttribute("s1Session", spieler);
			}

			if (request.getParameter("Nation") != null) {

				if ("Amerika".equals(nation)) {
			
					Amerika = false;
					s.setAttribute("s1", Amerika);
					getServletContext().setAttribute("s1Session", nation);
				}

				if ("China".equals(nation)) {

					China = false;
					s.setAttribute("s1", China);

					getServletContext().setAttribute("s1Session", nation);
				}

				if ("Deutschland".equals(nation)) {
					Deutschland = false;

					s.setAttribute("s1", Deutschland);
					getServletContext().setAttribute("s1Session", nation);

									}

				if ("England".equals(nation)) {
					England = false;

					s.setAttribute("s1", England);
					getServletContext().setAttribute("s1Session", nation);

				}

				if ("Frankreich".equals(nation)) {
					Frankreich = false;
					s.setAttribute("s1", Frankreich);
					getServletContext().setAttribute("s1Session", nation);

				}

				if ("Russland".equals(nation)) {
					Russland = false;
					s.setAttribute("s1", Russland);
					getServletContext().setAttribute("s1Session", nation);

				}

				if ("Tuerkei".equals(nation)) {
					Tuerkei = false;
					s.setAttribute("s1", Tuerkei);
					getServletContext().setAttribute("s1Session", nation);

				}

			}
			
			if (request.getParameter("s1Name").length() < 1 & request.getParameter("s1Name") == null) {

				response.sendRedirect("s1Anmelden.jsp");

			}
			
			if(request.getParameter("s1Name") != null){

				
				s.setAttribute("s1Name", spielerName);
				getServletContext().setAttribute("s1Session", spielerName);
				f.spielerHinzufuegen(spielerName, nation);
				System.out.println("Spieler 1 erstellt");

			}

		} finally {
			if (okay != null) {
				try {
					f.anmelden("s1Name");
				} catch (SpielerException e) {
					
					e.printStackTrace();
				}
				angemeldet = true;
				System.out.println();
				request.getRequestDispatcher("Warten.jsp").forward(request,
						response);
			}

		}
	}


}

