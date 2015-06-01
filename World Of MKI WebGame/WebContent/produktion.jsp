<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Produziere deine Einheiten</title>
</head>
<body>

<center>

<h3>Such deine Einheit aus:</h3>

<form method="post" action="Produktion">
Siedler: <input type="radio" name="einheit" value="siedler"></form>
<form method="post" action="Produktion"> Krieger: <input type="radio" name="einheit" value="krieger">
</form>

<table  align="center" >
<tr><th><a href="javascript:window.close()"><input type= "button" name= "okay" value="okay" ></a></th> <th>
<a href="javascript:window.close()"><input type="button" name="Abbrechen" name="cancel" value="Abbrechen">
</a></th></tr>
</table>


</center>

</body>
</html>