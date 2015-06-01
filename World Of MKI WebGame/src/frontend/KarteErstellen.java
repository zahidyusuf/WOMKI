package frontend;

public class KarteErstellen {
	public KarteErstellen(FrontendBean fB){
		StringBuffer(fB);
	}
	
	
	public static String StringBuffer(FrontendBean fB) {
		StringBuffer table = new StringBuffer(
				"<table cellspacing=0 cellpadding=0>");
		for (int i = 1; i < 30; i++) {
			table.append("<tr>");
			for (int j = 1; j < 30; j++) {
				int[] pos={j,i};
				String s = "'" +j +";"+i + "'";
				table.append("<a href= <td name="+s+"><img src=\"data:image/jpeg;base64,");
				table.append(fB.Base64Encoder(pos)+"\" border='0' />"+"</td></a>");
			}
			table.append("</tr>");
		}
		table.append("</table>");
		return table.toString();
	}

}
