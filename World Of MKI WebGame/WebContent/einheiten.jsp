<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function FensterOeffnen (Adresse) {
  MeinFenster = window.open(Adresse, "Zweitfenster", "width=300,height=400,left=100,top=200");
  MeinFenster.focus();
}
</script>
</head>
<body>
<center>
<h1>Einheitsproduktion</h1>

<table align="center" border="1">
<tr>
<th align = "left"> Leben: </th> <th> von 10</th> <th><a href= "produktion.jsp" onclick="FensterOeffnen(this.href); return false"> Produktion</a></th>

</tr>
<tr>
<th align = "left"> Angriff: </th> <th> von </th> <th><a href> Auflösen</a></th>
</tr>

<tr>
<th align = "left"> Verteidigung: </th> <th> von </th> <th><a href="javascript:window.close()"> Abbrechen </a></th>
</tr>

<tr>
<th align = "left"> Bewegung: </th> <th> von </th> <th></th>
</tr>

</table>

</center>
</body>
</html>