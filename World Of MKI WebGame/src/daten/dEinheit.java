package daten;

import java.util.TreeMap;

public class dEinheit extends d{
	
	public dEinheit(){
		add("idSpieler","");
		add("id","");
		add("art","");
		add("posX","");
		add("posY","");
		add("leben","");
		add("bewegung","");
		add("angriff","");
		add("verteidigung","");
		add("produktionskosten","");
	}
	
	public dEinheit(TreeMap<String, String> daten) {
		super(daten);
	}

	public dEinheit(String csv){
		super(csv);
	}

	@Override
	public dEinheit clone(){
		TreeMap<String,String> klonDaten=this.cloneDaten();
		return new dEinheit(klonDaten);
	}
	
}
