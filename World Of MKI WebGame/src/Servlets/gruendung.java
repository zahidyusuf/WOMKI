package Servlets;

import interfaces.iBackend;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import frontend.FrontendBean;

/**
 * Servlet implementation class gruendung
 */
@WebServlet("/gruendung")
public class gruendung extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public gruendung() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession(false);
		if (request.getParameter("Stadtname").equals("")) {
			session.setAttribute("nameLeer2", "true");
			request.getRequestDispatcher("gruendung.jsp").forward(request, response);
		}
		else
		{
		FrontendBean fr = (FrontendBean) getServletContext().getAttribute(
				"frontend");
		iBackend ba = fr.getBackend();
		String Stadtname=""+request.getParameter("Stadtname");
		if(request.getParameter("Stadtname")!=null)
		ba.neueStadt(ba.getDatenEinheit(ba.getPosFeldGewaehlt()), Stadtname);

		
		int k=1;
		for(int i=1;i<=50;i++)
		{
			for(int j=1;j<=50;j++)
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
