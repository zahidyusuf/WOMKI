package daten;

import java.util.TreeMap;

public class dSpieler extends d{

	public dSpieler(){
		add("id","");
		add("name","");
		add("nation","");
	}
	
	public dSpieler(TreeMap<String, String> daten) {
		super(daten);
	}

	public dSpieler(String csv){
		super(csv);
	}
	
	@Override
	public dEinheit clone(){
		TreeMap<String,String> klonDaten=this.cloneDaten();
		return new dEinheit(klonDaten);
	}
}
