package Servlets;

import interfaces.iBackend;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daten.dSpieler;
import frontend.FrontendBean;


@WebServlet("/gamescreen1")
public class gamescreen1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FrontendBean f;
	private static String pfadDaten;
	public static String pfad;
	   
 
    public gamescreen1() {
        super();
    
    }

    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		pfadDaten = getServletContext().getRealPath("daten");
		System.out.println(pfadDaten);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		
		if (request.getSession(false) == null) {
			response.sendRedirect("index.jsp");
			return;
		}
		response.sendRedirect("gamescreen1.jsp");
		if (getServletContext().getAttribute("spielGestartet") == null) {
			FrontendBean fr = (FrontendBean) getServletContext().getAttribute(
					"frontend");
			iBackend ba = fr.getBackend();
			
			
			
				
			
			
		
			
//			int k=1;
//			for(int i=1;i<=30;i++)
//			{
//				for(int j=1;j<=30;j++)
//				{	
//					int [] pos={j,i};
//					f.Base64Encoder(pos);
//					getServletContext().setAttribute("/WorldOfMki_Web02/Karten/Kontinente.map"+k,"data:image/png;base64,"+FrontendBean.encodeToString((BufferedImage) ba.getFeldBild(pos), "png"));
//					k++;
//				}
//			}		
			
//			f.Kartenzeichner();
			
			//------------------------------------------------------------------------------------------
			
			getServletContext().setAttribute("spielGestartet", "true");
			response.getWriter().print("<script type= window.location.href=\"gamescreen1.jsp\"</script>");
		
		}
		
	

		
	
	}
	
	public static String getPfadDaten() {
		return pfadDaten;
	}
	
		
//		try{
//			HttpSession sess = request.getSession(true);
//			
//			String name=request.getParameter("name");
//			String[] daten=name.split(";");
//			int x = Integer.parseInt(daten[0]);
//			int y = Integer.parseInt(daten[1]);
//			int [] pos = {16,9};
//			f.getBackend().waehleFeld(pos);
//			sess.setAttribute("name", name);
//			
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//			response.getWriter().print("<script type= window.location.href=\"gamescreen1.jsp\"</script>");
//		}
//	}

}
