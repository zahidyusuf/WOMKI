package backend.spiel.einheit;

public enum AktionEinheit {
	geheNW,geheN,geheNO,geheW,status,geheO,geheSW,geheS,geheSO;
	
	public static AktionEinheit getAktion(int ea){
		return AktionEinheit.values()[ea];
	}
}