package daten;

import java.util.TreeMap;

public class dStadt extends d{

	public dStadt() {
		add("idSpieler","");
		add("id","");
		add("name","");
		add("posX","");
		add("posY","");
		add("rundeGruendung","");
		add("groesse","");
		add("siedler","");
		add("krieger", "");
		add("inProduktion","");
		add("siedlerAnteil","");
		add("kriegerAnteil","");
		add("produziert","");
	}
	
	public dStadt(TreeMap<String, String> daten) {
		super(daten);
	}

	public dStadt(String csv){
		super(csv);
	}
	

	
	@Override
	public dEinheit clone(){
		TreeMap<String,String> klonDaten=this.cloneDaten();
		return new dEinheit(klonDaten);
	}
	
}
