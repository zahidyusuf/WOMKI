package daten;

import java.util.TreeMap;

public class dSpiel extends d{

	public dSpiel() {
		add("anzahlSpieler",""+1);
		add("runde",""+1);
		add("spielerAmZug",""+0);
	}
	
	public dSpiel(TreeMap<String, String> daten) {
		super(daten);
	}

	public dSpiel(String csv){
		super(csv);
	}
	
	@Override
	public dEinheit clone(){
		TreeMap<String,String> klonDaten=this.cloneDaten();
		return new dEinheit(klonDaten);
	}
}
