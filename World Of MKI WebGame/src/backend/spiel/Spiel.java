package backend.spiel;

import interfaces.iBean;

import java.util.ArrayList;

import daten.d;
import daten.dSpiel;
import daten.dSpieler;
import backend.Backend;
import backend.BackendException;
import backend.karte.Karte;
import backend.spiel.einheit.Einheit;
import backend.spiel.einheit.einheitart.Siedler;
import backend.spieler.Spieler;

public class Spiel {
	private Backend ba;
	private Karte k;
	private ArrayList<Spieler> spieler=null;
	private dSpiel dSp;
	
	public Spiel(Backend ba,Karte k){
		this.ba=ba;
		this.k=k;
	}

	// nur beim Start eines neuen Spiels von Beginn an
	public Spiel(Backend ba,Karte k,ArrayList<dSpieler> spielerDaten,boolean neuesSpiel){
		this(ba,k);
		dSp=new dSpiel();
		dSp.set("anzahlSpieler",""+spielerDaten.size());
		spieler=new ArrayList<Spieler>();
		for(int i=1;i<=d.toInt(dSp.get("anzahlSpieler"));i++){
			dSpieler daten=spielerDaten.get(i-1);
			Spieler s=new Spieler(k,daten);
			spieler.add(s);
			if (neuesSpiel){
				// Siedler erzeugen und setzen
				int[] pos=k.getStartposition(i);
				Siedler si=new Siedler(k,s);
				si.setPos(pos);			
				s.addEinheit(si);
			}
		}
		if (neuesSpiel) naechsterSpieler();
	}
	
	public void setDaten(dSpiel d){
		this.dSp=d;
	}
	
	public dSpiel getDaten(){
		return dSp;
	}
	
	public ArrayList<Spieler> getSpieler(){
		return spieler;
	}

	public void setSpieler(ArrayList<Spieler> spieler){
		this.spieler=spieler;
	}
	
	public Spieler getSpielerAmZug(){
		return spieler.get(d.toInt(dSp.get("spielerAmZug"))-1);
	}
	
	public Spieler naechsterSpieler(){
		if (dSp.get("spielerAmZug").equals(dSp.get("anzahlSpieler"))){
			dSp.set("spielerAmZug",""+1);
			dSp.inc("runde");
		}
		else
			dSp.inc("spielerAmZug");
		Spieler sp=getSpielerAmZug();
		sp.neueRunde();
		return sp;
	}

	public Karte getKarte(){
		return k;
	}
	
	public int getRunde(){
		return d.toInt(dSp.get("runde"));
	}

	public void bewegeEinheit(int ea){
		iBean fr=ba.getFrontend();
		try{
			int[] posAlt=getKarte().getFeldGewaehlt().getPos();
			Einheit ei=getKarte().getFeldGewaehlt().getEinheit();
			ei.bewegeEinheit(ea); 
			int[] posNeu=getKarte().getFeldGewaehlt().getPos();
				
		}
		catch (BackendException e){
//			fr.log("FEHLER: "+e.getMessage());
		}
	}
}
