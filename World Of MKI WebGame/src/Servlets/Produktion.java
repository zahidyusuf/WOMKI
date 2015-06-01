package Servlets;

import interfaces.iBackend;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daten.dStadt;
import frontend.FrontendBean;


@WebServlet("/Produktion")
public class Produktion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Produktion() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(true);
	
		String einheit= request.getParameter("einheit");
		FrontendBean f = (FrontendBean) getServletContext().getAttribute(
				"frontend");
		iBackend ba = f.getBackend();

		
		
		if("siedler".equals(einheit)){
			
			dStadt[] dSt = ba.getDatenStadt(ba.getPosFeldGewaehlt());
			if (dSt[0].get("inProduktion") == null
					&& !ba.getFeldGewaehltHatEigeneEinheit()) {
				dSt[0].add("inProduktion", "Krieger");
				dSt[0].add("produziert", "0");
				switch (dSt[0].get("inProduktion")) {
				case "siedler":
					f.log("Es wir gerade ein Siedler produziert. Nach 15 Runden ist er fertig gestellt.");
					break;
				case "krieger":
					f.log("Es wir gerade ein Krieger produziert. Nach 5 Runden ist er fertig gestellt.");
					break;
				}
			} else {
				if (dSt[0].get("inProduktion") != null) {
					f.log("Kann keine neuen Einheiten produzieren, da sich bereits Einheit \""
							+ dSt[0].get("inProduktion")
							+ "\" in Produktion befindet.");
				} else {
					f.log("Kann keine neuen Einheiten produzieren, da sich bereits eine Einheit "
							+ "in der Stadt befindet befindet.");
				}
			}
			
			

			int k = 1;
			for (int i = 1; i <= 30; i++) {
				for (int j = 1; j <= 30; j++) {
					int[] pos = { j, i };
					getServletContext().setAttribute(
							"map" + k,
							"data:image/png;base64,"
									+ FrontendBean.encodeToString(
											(BufferedImage) ba.getFeldBild(pos),
											"png"));
					k++;
				}
			}
			response.sendRedirect("gamescreen1.jsp");
		}
			
		
		else if("krieger".equals(einheit)){
			
			dStadt[] dSt = ba.getDatenStadt(ba.getPosFeldGewaehlt());
			if (dSt[0].get("inProduktion") == null
					&& !ba.getFeldGewaehltHatEigeneEinheit()) {
				dSt[0].add("inProduktion", "Siedler");
				dSt[0].add("produziert", "0");
				switch (dSt[0].get("inProduktion")) {
				case "siedler":
					f.log("Es wir gerade ein Siedler produziert. Nach 15 Runden ist er fertig gestellt.");
					break;
				case "krieger":
					f.log("Es wir gerade ein Krieger produziert. Nach 5 Runden ist er fertig gestellt.");
					break;
				}
			} else {
				if (dSt[0].get("inProduktion") != null) {
					f.log("Kann keine neuen Einheiten produzieren, da sich bereits Einheit \""
							+ dSt[0].get("inProduktion")
							+ "\" in Produktion befindet.");
				} else {
					f.log("Kann keine neuen Einheiten produzieren, da sich bereits eine Einheit "
							+ "in der Stadt befindet befindet.");
				}
			}
			
			
			int k=1;
			for(int i=1;i<=30;i++)
			{
				for(int j=1;j<=30;j++)
				{	
					int [] pos={j,i};
					getServletContext().setAttribute("map"+k,"data:image/png;base64,"+FrontendBean.encodeToString((BufferedImage) ba.getFeldBild(pos), "png"));
					k++;
				}
			}
			response.sendRedirect("gamescreen1.jsp");
		}

		}
		
		
		
		
		
	}


