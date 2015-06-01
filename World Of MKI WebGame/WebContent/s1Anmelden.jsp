<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="frontend.FrontendBean" %>
<%  	
	FrontendBean f = (FrontendBean) application.getAttribute("f");
	%>

   	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Willkommen bei Civilisation!!!</title>
</head>



<body>
	<form method= "post" action="s1Anmelden">
	
		<center>
			<h1>WILLKOMMEN BEI CIVILISATION</h1>
		</center>

		<center>


			<table border="1" width="600px">
				<tr align="center">
					<th width="300px">Wähle deine Karte</th>
					<th align="left" width="300px">
						<p>
							 <input type="radio"
								name="Datei"  value="Inseln" >Inseln
						</p>
						<p>
							 <input type="radio"
								name="Datei"  value="Kontinente" >Kontinente
						</p>
						<p>
							<input type="radio"
								name="Datei"  value="Pangaea" >Pangaea
						</p>
						<p>
							<input type="radio"
								name="Datei"  value="Seen" >Seen
						</p>
						<p>
							 <input type="radio"
								name="Datei"  value="Spiegel" > Spiegel
						</p>
					</th>
				</tr>
				<tr align="center">
					<th width="300px">Wie viele Spieler sollen mitspielen?</th>
					<th align="left" width="300px">
						<p>
							<input type="radio" name="Spieler" value="2Spieler"> 2
							Spieler<br> 
							<input type="radio" name="Spieler"
								value="3Spieler"> 3 Spieler<br> <input type="radio"
								name="Spieler" value="4spieler"> 4 Spieler
						</p>
					</th>
				</tr>
				<tr align="center">
					<th width="300px">Bitte gib deinen Spielernamen ein:</th>
					<th align="left" width="300px"><input type = "text" name = "s1Name"></th>
				</tr>
				<tr align="center">
					<th width="300px">Bitte wähle eine Nation aus:</th>
					<th align="left" width="300px"><p>
							<input type="radio" name="Nation" value="Amerika">
							Amerika<br>
							
							 <input type="radio" name="Nation" value="China">
							China<br> <input type="radio" name="Nation"
								value="Deutschland"> Deutschland<br> <input
								type="radio" name="Nation" value="England">England<br>
							<input type="radio" name="Nation" value="Frankreich">Frankreich<br>
							<input type="radio" name="Nation" value="Russland">
							Russland<br> <input type="radio" name="Nation"
								value="Tuerkei"> Tuerkei
						</p>
						<p align="right">
							<input type="submit" name="GameStart" value="Los Geht's">
						</p></th>
				</tr>

			</table>


		</center>

	</form>
</body>

</html>