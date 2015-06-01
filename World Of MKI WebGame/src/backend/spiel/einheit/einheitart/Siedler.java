package backend.spiel.einheit.einheitart;

import daten.dEinheit;
import backend.karte.Karte;
import backend.karte.feld.FeldArt;
import backend.spiel.einheit.Einheit;
import backend.spieler.Spieler;

public class Siedler extends Einheit{
	

	
	
	public Siedler(){
		super();
	}

	public Siedler(Karte k,Spieler s) {
		super(k,s);
		getDatenMaximal().add("idSpieler",""+s.getId());
		getDatenAktuell().add("idSpieler",""+s.getId());
	}
	
	
	@Override
	public void setErlaubteFelder(){
		erlaubteFelder.add(FeldArt.Berg);
		erlaubteFelder.add(FeldArt.Dschungel);
		erlaubteFelder.add(FeldArt.Eis);
		erlaubteFelder.add(FeldArt.Huegel);
		erlaubteFelder.add(FeldArt.Steppe);
		erlaubteFelder.add(FeldArt.Sumpf);
		erlaubteFelder.add(FeldArt.Wald);
		erlaubteFelder.add(FeldArt.Wiese);
		erlaubteFelder.add(FeldArt.Wueste);		
	}
	
	@Override
	public void init() {
		dEinheit dEi=new dEinheit();
		dEi.set("id",""+Einheit.getNaechsteId());
		dEi.set("art",this.getClass().getSimpleName());
		dEi.set("angriff",""+0);
		dEi.set("verteidigung",""+0);
		dEi.set("bewegung",""+1);
		dEi.set("leben",""+10);
		dEi.set("produktionskosten",""+15);
		setErlaubteFelder();
		setDatenMaximal(dEi.clone());
		setDatenAktuell(dEi.clone());
	}
	
}
