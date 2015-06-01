<%@page import="com.sun.xml.internal.bind.v2.schemagen.xmlschema.Import"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="Servlets.s1Anmelden"
 %>    
 <%@ page import="frontend.FrontendBean"  	
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Willkommen bei Civilisation!!!</title>
</head>


<body>
	<form action="s2Anmelden" method="post">
		<center>
			<h1>WILLKOMMEN BEI CIVILISATION</h1>
			<h3>Sie sind Spieler 2</h3>
		</center>

		<center>

			<table border="1" width="600px">
				
				
				<tr align="center">
					<th width="300px">Bitte gib deinen Spielernamen ein:</th>
					<th align="left" width="300px"><input type="text" id="spieler"
						name="s2Name"></th>
				</tr>
				<tr align="center">
					<th width="300px">Bitte wähle eine Nation aus:</th>
					<th align="left" width="300px"><p>
					<%
					FrontendBean f = (FrontendBean) application.getAttribute("f");
				
					boolean Amerika = f.getAmerika1();
					boolean China = f.getChina1();
					boolean Deutschland = f.getDeutschland1();
					boolean England = f.getEngland1();
					boolean Frankreich = f.getFrankreich1();
					boolean Russland = f.getRussland1();
					boolean Tuerkei = f.getTuerkei1();	
					
					if(Amerika == false){
						out.println("<input type='hidden' name='Nation' value='Amerika'>");
					}
					if(Amerika == true){
						out.println("<input type='radio'  name='Nation' value='Amerika'>");
						out.println("Amerika");
						out.println("<br>");
					}
					if(China == false){
						out.println("<input type='hidden' name='Nation' value='China'>");
					}if(China == true){
						out.println("<input type='radio'  name='Nation' value='China'>");
						out.println("China");
						out.println("<br>");
					}
					if(Deutschland == false){
						out.println("<input type='hidden' name='Nation' value='Deutschland'>");
					}if(Deutschland == true){
						out.println("<input type='radio'  name='Nation' value='Deutschland'>");
						out.println("Deutschland");
						out.println("<br>");
					}
					if(England == false){
						out.println("<input type='hidden' name='Nation' value='England'>");
					}if(England == true){
						out.println("<input type='radio'  name='Nation' value='England'>");
						out.println("England");
						out.println("<br>");
					}
					if(Frankreich == false){
						out.println("<input type='hidden' name='Nation' value='Frankreich'>");
					}if(Frankreich == true){
						out.println("<input type='radio'  name='Nation' value='Frankreich'>");
						out.println("Frankreich");
						out.println("<br>");
					}
					if(Russland == false){
						out.println("<input type='hidden' name='Nation' value='Russland'>");
					}if(Russland == true){
						out.println("<input type='radio'  name='Nation' value='Russland'>");
						out.println("Russland");
						out.println("<br>");
					}
					if(Tuerkei == false){
						out.println("<input type='hidden' name='Nation' value='Tuerkei'>");
					}if(Tuerkei == true){
						out.println("<input type='radio'  name='Nation' value='Tuerkei'>");
						out.println("Türkei");
						out.println("<br>");
					}
					%>
						
						</p>
						<p align="right">
							<input type="submit" name="Start" value="Los Geht's">
						</p></th>
				</tr>

			</table>


		</center>

	</form>
</body>
</html>