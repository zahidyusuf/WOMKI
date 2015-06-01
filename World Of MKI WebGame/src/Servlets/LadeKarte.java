package Servlets;

import interfaces.iBackend;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frontend.FrontendBean;
import backend.karte.Karte;
import backend.karte.feld.Feld;

/**
 * Servlet implementation class LadeKarte
 */
@WebServlet("/LadeKarte")
public class LadeKarte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontendBean f;
	private Feld[][] felder;
	private Feld feld;   
    
	
	
	public LadeKarte() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FrontendBean fr = (FrontendBean) getServletContext().getAttribute("f");
		System.out.println(fr);
		iBackend ba = fr.getBackend(); 
//		if((ba.getFeldGewaehltHatEigeneEinheit()==true&&session.getAttribute("name").equals(ba.getSpielernameAmZug()))||(ba.getFeldGewaehltHatEigeneStadt()==true&&session.getAttribute("name").equals(ba.getSpielernameAmZug()))||session.getAttribute("name").equals(ba.getSpielernameAmZug()))
//		{
			int k=1;
			for(int i=1;i<=30;i++)
			{
				for(int j=1;j<=30;j++)
				{	
					int [] pos={j,i};
				
					if(request.getParameter("value")!=null||request.getParameter("value")!=null)
					{
						if(request.getParameter("value").equals("LadeKarte"+k))
						{
							ba.waehleFeld(pos);
						}
					}
					getServletContext().setAttribute("map"+k,"data:image/png;base64,"+FrontendBean.encodeToString((BufferedImage) ba.getFeldBild(pos), "png"));
					k++;
				}
			}
//		}
/*
		int y=1;
		for(int x=1;x<=30;x++)
		{
			for(int n=1;n<=30;n++)
			{	
				int [] pos={n,x};
				getServletContext().setAttribute("/WorldOfMki_Web02/Karten/Kontinente.map"+y,"data:image/png;base64,"+FrontendBean.encodeToString((BufferedImage) ba.getFeldBild(pos), "png"));
				y++;
			}
		}
		*/
				response.sendRedirect("gamescreen1.jsp");
		
}
}