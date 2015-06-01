<%@page import="java.io.FileInputStream"%>
<%@page import="frontend.manager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.awt.Image" %>
<%@ page import="frontend.FrontendBean" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="frontend.KarteErstellen" %>
<%@ page import="backend.Backend" %>
<%@ page import="interfaces.iBackend" %>
<%@ page import=" java.awt.image.BufferedImage" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Viel Spaß!!! - Civilisation hat gestartet</title>
</head>
	<body>
		


	<%
	out.print("<table style='border-collapse:collapse'>");
	int k=1;
	for(int i=1;i<=30;i++)
	{
		for(int j=1;j<=30;j++)
		{
			if(i==1&&j==1||i==2&&j==1||i==3&&j==1||i==4&&j==1||i==5&&j==1||i==6&&j==1||i==7&&j==1||i==8&&j==1
					||i==9&&j==1||i==10&&j==1||i==11&&j==1||i==12&&j==1||i==13&&j==1||i==14&&j==1||
					i==15&&j==1||i==16&&j==1||i==17&&j==1||i==18&&j==1||i==19&&j==1||i==20&&j==1||
					i==21&&j==1||i==22&&j==1||i==23&&j==1||i==24&&j==1||i==25&&j==1||i==26&&j==1||
					i==27&&j==1||i==28&&j==1||i==29&&j==1||i==30&&j==1)
			{
				out.print("<tr>");
			}
			out.print("<td>");
			out.print("<a href='LadeKarte?value=LadeKarte"+k+"'><img src='"+getServletContext().getAttribute("map"+k)+"'/></a>");
			out.print("</td>");
			k++;
			if(i==1&&j==30||i==2&&j==30||i==3&&j==30||i==4&&j==30||i==5&&j==30||i==6&&j==30||i==7&&j==30||i==8&&j==30
					||i==9&&j==30||i==10&&j==30||i==11&&j==30||i==12&&j==30||i==13&&j==30||i==14&&j==30||
					i==15&&j==30||i==16&&j==30||i==17&&j==30||i==18&&j==30||i==19&&j==30||i==20&&j==30||
					i==21&&j==30||i==22&&j==30||i==23&&j==30||i==24&&j==30||i==25&&j==30||i==26&&j==30||
					i==27&&j==30||i==28&&j==30||i==29&&j==30||i==30&&j==30)
			{
				out.print("</tr>");
			}
			
		}
	}
	out.print("</table>");
		
	%>
	</body>
</html>