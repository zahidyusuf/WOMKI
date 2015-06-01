<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Servlets.MenueLeisteRechts" %> 
<%@page import= "java.awt.Image" %>
<%@page import= "java.awt.image.BufferedImage" %> 
<%@ page import="java.io.File" %>
<%@ page import=" javax.swing.ImageIcon" %> 
<%@ page import= "javax.imageio.ImageIO" %> 
<%@ page import="com.sun.image.codec.jpeg.JPEGCodec" %>
<%@ page import= "com.sun.image.codec.jpeg.JPEGImageEncoder"%>
<%@ page import= "org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream" %>
<%@ page import ="sun.misc.BASE64Encoder" %>
<%@ page import= "frontend.FrontendBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Auswahlmöglichkeiten</title>
<script type="text/javascript">
function FensterOeffnen (Adresse) {
  MeinFenster = window.open(Adresse, "Zweitfenster", "width=300,height=400,left=100,top=200");
  MeinFenster.focus();
}
</script>
</head>
<body>
<form method="post" action="MenueLeisteRechts">
<% 

				final int SCALE = -10;
				Image img1= new ImageIcon("/daten/frontend/").getImage();

				BufferedImage bi = new BufferedImage(SCALE * img1.getWidth(null),
        	SCALE * img1.getHeight(null),
		        BufferedImage.TYPE_INT_ARGB);

				ByteArrayOutputStream os= new ByteArrayOutputStream();
				JPEGImageEncoder encJPG = JPEGCodec.createJPEGEncoder(os);
				try{
					encJPG.encode(bi);
				}catch(Exception e){
					e.printStackTrace();
				}
				BASE64Encoder enc64= new BASE64Encoder();
				enc64.encode(os.toByteArray());
				
   				ImageIO.write(bi, "png", os);  


%>
<table align="right" border="1" width="150px">

<tr>

<td><a href='LinksObenGehen?value=LinksObenGehen'><img alt="pfeil1" src="/WorldOfMki_Web02/daten/frontend/pfeil_1.png"> 
 </a></td>
<td><a href='HochGehen?value=HochGehen'><img alt="pfeil2" src="/WorldOfMki_Web02/daten/frontend/pfeil_2.png"> </a></td>
<td><a href='RechtsHochGehen?=RechtsHochGehen'><img alt="pfeil3" src="/WorldOfMki_Web02/daten/frontend/pfeil_3.png"> </a></td>
</tr>


<tr>
<td><a href='LinksGehen?value=LinksGehen'><img alt="pfeil4" src="/WorldOfMki_Web02/daten/frontend/pfeil_4.png"> </a></td>
<td><a href='gamescreen1.jsp' onclick="javascript:window.location.reload();return false"><img alt="pfeil5" src="/WorldOfMki_Web02/daten/frontend/pfeil_5.png"> </a></td>
<td><a href='RechtsGehen?value=RechtsGehen'><img alt="pfeil6" src="/WorldOfMki_Web02/daten/frontend/pfeil_6.png"> </a></td>

</tr>

<tr>
<td><a href='LinksUntenGehen?value=LinksUntenGehen'><img alt="pfeil7" src="/WorldOfMki_Web02/daten/frontend/pfeil_7.png"> </a></td>
<td><a href='RunterGehen?value=RunterGehen'><img alt="pfeil8" src="/WorldOfMki_Web02/daten/frontend/pfeil_8.png"> </a></td>
<td><a href='RechtsRunterGehen?value=RechtsRunterGehen'><img alt="pfeil9" src="/WorldOfMki_Web02/daten/frontend/pfeil_9.png"> </a></td>

</tr>
<tr><th><a href = "einheiten.jsp" onclick="FensterOeffnen(this.href); return false"> Einheit </a></th> 

<th><a href = "Stadt.jsp" onclick="FensterOeffnen(this.href); return false"> Stadt </a> </th>

<th><a href='RundeBeenden?value=RundeBeenden' > Runde beenden</a></th>
</tr>
<tr>
<th><a href = "gamescreen1.jsp" onclick="javascript:window.location.reload(); return false"> Refresh</a> </th>
</tr>
</table>


</form>
</body>
</html>