package backend.spieler;

import java.util.ArrayList;

import daten.d;
import daten.dSpieler;
import errorHandling.SpielerException;
import backend.karte.Karte;
import backend.karte.feld.Feld;
import backend.spiel.einheit.Einheit;
import backend.spiel.stadt.Stadt;
import backend.spieler.nation.Nation;
import backend.spieler.nation.NationArt;

public class Spieler {
	private Karte k;
	private ArrayList<Einheit> einheiten=new ArrayList<Einheit>();
	private ArrayList<Stadt> staedte=new ArrayList<Stadt>();
	private dSpieler dSp;
	private String name;
	public Spieler(Karte k,dSpieler dSp){
		this.k=k;
		this.dSp=dSp;
	}
	
	public Spieler(String name) throws SpielerException{
		 setName(name);
		 
	}

	
	void setName(String name) throws SpielerException{
		if(name==null){
			throw new SpielerException ("Name erstellen fehlerhaft! Spieler nicht erstellt.");
		}
		else{
		this.name = name;		
		}
		
	}
	
	
	public dSpieler getDaten(){
		return dSp;
	}
	
	public ArrayList<Einheit> getEinheiten(){
		return einheiten;
	}
	
	public Einheit getEinheit(int id){
		for(Einheit ei:einheiten){
			if (d.toInt(ei.getDatenAktuell().get("id"))==id) return ei;
		}
		return null;
	}
	
	public void addEinheit(Einheit ei){
		if (einheiten.contains(ei)) return;
		einheiten.add(ei);
		ei.setKarte(k);
		ei.setSpieler(this);
		Feld f=k.getFeld(ei.posX(),ei.posY());
		f.setEinheit(ei);
	}
	
	public void removeEinheit(Einheit ei){
		if (!einheiten.contains(ei)) return;
		einheiten.remove(ei);
		Feld f=k.getFeld(ei.posX(),ei.posY());
		f.setEinheit(null);
	}
	
	public ArrayList<Stadt> getStaedte(){
		return staedte;
	}
	
	public void addStadt(Stadt s){
		if (staedte.contains(s)) return;
		staedte.add(s);
		Feld f=k.getFeld(s.posX(),s.posY());
		f.setStadt(s);
	}
	
	public void neueRunde(){
		if (einheiten.size()>0){
			for (Einheit ei:einheiten) ei.neueRunde();
		}
		if (staedte.size()>0){
			for (Stadt st:staedte) st.neueRunde();
		}		
		if (einheiten.size()>0)
			k.setFeldGewaehlt(einheiten.get(0).getPos());
		else if (staedte.size()>0)
			k.setFeldGewaehlt(staedte.get(0).getPos());
	}

	public int getId() {
		return d.toInt(dSp.get("id"));
	}
	
	public String getName() {
		return dSp.get("name");
	}

	public Nation getNation() {
		return NationArt.getNation(dSp.get("nation"));
	}
	
	@Override
	public String toString(){
		return "Spieler Nummer "+getId()+", "+getName()+" von "+getNation();
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Spieler)) return false;
		Spieler s=(Spieler)o;
		return (s.getId()==this.getId());
	}
}
