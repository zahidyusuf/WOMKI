package Servlets;

import frontend.FrontendBean;
import interfaces.iBackend;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import backend.spiel.einheit.AktionEinheit;

@WebServlet("/RechtsGehen")
public class RechtsGehen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public RechtsGehen() {
        super();
   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		FrontendBean fr = (FrontendBean) getServletContext().getAttribute(
				"frontend");
		iBackend ba = fr.getBackend();
	
		HttpSession session = request.getSession(false);
		if(ba.getFeldGewaehltHatEigeneEinheit()==true&&session.getAttribute("name").equals(ba.getSpielernameAmZug()))
		ba.bewegeEinheit(AktionEinheit.geheO.ordinal());
		
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
