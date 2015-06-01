package backend.spiel.einheit;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;






import javax.swing.JOptionPane;

import daten.d;
import daten.dEinheit;
import backend.BackendException;
import backend.karte.*;
import backend.karte.feld.Feld;
import backend.karte.feld.FeldArt;
import backend.spieler.Spieler;
import backend.spiel.stadt.*;

public abstract class Einheit {
	private static int counterId = 1;
	private Spieler spieler;
	private Karte k;
	private BufferedImage bild = null;
	protected ArrayList<FeldArt> erlaubteFelder=new ArrayList<FeldArt>(); 
	protected dEinheit dMaximal=new dEinheit();
	protected dEinheit dAktuell=new dEinheit();
	//Variable fuer Kaempfen
	private Random rd = new Random();
	private static int verteidigungA;
	private static int angriffA;
	private static int verteidigungV;
	private static int angriffV; 
	private static int lebenV;
	private static int lebenA;
	private static int schadenV;
	private static int schadenA;
	
	

	public static int getNaechsteId() {
		int id = counterId;
		counterId++;
		return id;
	}

	public static void setBelegteId(int id) {
		if (id > counterId)
			counterId = id + 1;
	}

	public Einheit() {
		setErlaubteFelder();
		init();
	}

	public Einheit(Karte k, Spieler spieler) {
		this();
		this.k = k;
		this.spieler = spieler;
	}

	public void setDatenMaximal(dEinheit d) {
		setBild(EinheitArt.getBild(d.get("art")));
		this.dMaximal = d;
	}

	public void setDatenAktuell(dEinheit d) {
		setBild(EinheitArt.getBild(d.get("art")));
		this.dAktuell = d;
	}

	public dEinheit getDatenMaximal() {
		return dMaximal;
	}

	public dEinheit getDatenAktuell() {
		return dAktuell;
	}

	public void setKarte(Karte k) {
		this.k = k;
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public void setSpieler(Spieler spieler) {
		this.spieler = spieler;
	}

	public void setPos(int[] pos) {
		setPos(pos[0], pos[1]);
	}

	public void setPos(int posX, int posY) {
		dAktuell.set("posX", "" + posX);
		dAktuell.set("posY", "" + posY);
	}

	public int posX() {
		return d.toInt(dAktuell.get("posX"));
	}

	public int posY() {
		return d.toInt(dAktuell.get("posY"));
	}

	public int[] getPos() {
		int[] ausgabe = new int[2];
		ausgabe[0] = posX();
		ausgabe[1] = posY();
		return ausgabe;
	}

	public String getArt() {
		return dAktuell.get("art");
	}

	public void setArt(String art) {
		dAktuell.set("art", art);
		dMaximal.set("art", art);
	}

	protected void setBild(BufferedImage bild) {
		this.bild = bild;
	}

	public BufferedImage getBild() {
		return bild;
	}

	public long getId() {
		return d.toInt(dAktuell.get("id"));
	}

	@Override
	public String toString() {
		return "Einheit " + getArt();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Einheit))
			return false;
		Einheit ei = (Einheit) o;
		return (this.getId() == ei.getId());
	}

	public void bewegeEinheit(int ea) {
		boolean kampf=false;
		
		if (d.toInt(dAktuell.get("bewegung"))<=0) 
			throw new BackendException("Diese Einheit kann sich in dieser "
					+ "Runde nicht mehr bewegen bzw angreiffen!");
		int posXalt=d.toInt(dAktuell.get("posX"));
		int posYalt=d.toInt(dAktuell.get("posY"));
		int posXneu=d.toInt(dAktuell.get("posX"));
		int posYneu=d.toInt(dAktuell.get("posY"));
		AktionEinheit x=AktionEinheit.getAktion(ea);

		switch (x) {

		case geheNW:
			posXneu = posXalt - 1;
			posYneu = posYalt - 1;
			break;
		case geheN:
			posYneu = posYalt - 1;
			break;
		case geheNO:
			posXneu = posXalt + 1;
			posYneu = posYalt - 1;
			break;
		case geheW:
			posXneu = posXalt - 1;
			break;
		case geheO:
			posXneu = posXalt + 1;
			break;
		case geheSW:
			posXneu = posXalt - 1;
			posYneu = posYalt + 1;
			break;
		case geheS:
			posYneu = posYalt + 1;
			break;
		case geheSO:
			posXneu = posXalt + 1;
			posYneu = posYalt + 1;
			break;
		default:
			throw new BackendException("Unerlaubte Bewegung!");
		}

		if ((posXneu < 1) || (posYneu < 1)
				|| (posXneu > k.getGroesseX() || (posYneu > k.getGroesseY())))
			throw new BackendException(
					"Keine Bewegung ausserhalb der Karte moeglich!");

		Feld fAlt = k.getFeld(posXalt, posYalt);
		Feld fNeu = k.getFeld(posXneu, posYneu);

		// Bewegung auf das Zielfeld nicht erlaubt:
		if (!erlaubteFelder.contains(fNeu.getArt()))
			throw new BackendException(
					"Diese Einheit kann sich leider nicht auf dieses Feld bewegen!");

						if ((fNeu.getEinheit()!=null)||((fNeu.getStadt()!=null) && !(fNeu.getStadt().getSpieler().getId() == fAlt.getEinheit().getSpieler().getId()))) {
						// auf dem Zielfeld ist schon eine Einheit von diesem Spieler:
						if ((fNeu.getEinheit() != null)
						&& (fNeu.getEinheit().getSpieler()
						.equals(this.getSpieler())))
						throw new BackendException(
						"Sie koennen nicht mehrere Einheiten auf einem Feld versammeln!");
						// wenn diese Einheit keine Angriffspunkte hat, kann sie keine
						// gegnerische Einheit/Stadt angreifen:
						if (d.toInt(dAktuell.get("angriff")) <= 0) {
						throw new BackendException(
						"Diese Einheit kann leider nicht angreifen!");}
						else {
				// TODO Kaempfen!
				Kaempfen(fAlt, fNeu);
				JOptionPane.showMessageDialog(null, "Krieg!!"+ "\n" +"Status von Attacker, Angriffspunkte : " +angriffA + " Verteidigungspunkte : " + verteidigungA + " Lebenspunkte : " + lebenA + "\n" + 
				"Status von Defender, Angriffspunkte : " +angriffV + " Verteidigungspunkte : " + verteidigungV + " Lebenspunkte : " + lebenV);
				kampf=true;
			}
		}
		
		if(kampf==false) {
		fAlt.setEinheit(null);
		fAlt.abwaehlen();
		dAktuell.set("posX", "" + posXneu);
		dAktuell.set("posY", "" + posYneu);
		fNeu.setEinheit(this);
		fNeu.waehlen();
		dAktuell.dec("bewegung");
		k.setFeldGewaehlt(posXneu, posYneu);
		}
	}

	//Methode prueft ob Einheit in einer Stadt steht
	public boolean inStadtpruefen(){
		boolean istInStadt =false;
		ArrayList<Stadt> staedte = spieler.getStaedte();
		for(Stadt s : staedte){
			int posStadt [] = s.getPos();
			int posEinheit [] = this.getPos();
				for(int i=0; i<= posStadt.length; i++){
					for(int  j=0; j<= posEinheit.length; j++){
						istInStadt=true;
					}
				}
			
		}
		return  istInStadt;
	}
	
	
	public void neueRunde() {
		dAktuell.set("bewegung", dMaximal.get("bewegung"));
	}
	

	public void Kaempfen(Feld attacker, Feld defender) {
		
		
		Einheit.verteidigungA = Integer.parseInt(attacker.getEinheit().getDatenAktuell().get("verteidigung")); //Die aktuelle Verteidigungspunkte von Attacker!
		Einheit.angriffA = Integer.parseInt(attacker.getEinheit().getDatenAktuell().get("angriff")); //Die aktuelle Angriffspunkte von Attacker!
		Einheit.verteidigungV = Integer.parseInt(defender.getEinheit().getDatenAktuell().get("verteidigung"));; //Die aktuelle verteidigungspunkte vond Defender! problem : Fehlt noch, wie man die Daten von Defender zugreifen kann. Erledigt!
		Einheit.angriffV = Integer.parseInt(defender.getEinheit().getDatenAktuell().get("angriff")); //Die aktuelle Angriffspunkte von Defender! note : Fehlt noch, wie man die Daten von Defender zugreifen kann. Nicht mehr gebraucht!
		
		angriffA = attackieren();
		verteidigungV = verteidigen();
		
		schadenV = angriffA - verteidigungV;
		schadenA = verteidigungV - verteidigungA;
		
		if(schadenV<0)
			schadenV = 0;
		if(schadenA<0)
			schadenA = 0;
		
		Einheit.lebenV = Integer.parseInt(defender.getEinheit().getDatenAktuell().get("leben")); //Die aktuelle Lebenspunkte von Defender!
		Einheit.lebenA = Integer.parseInt(attacker.getEinheit().getDatenAktuell().get("leben")); //Die aktuelle Lebenspunkte von Attacker!
		
		// Die Schadenspunkte werden von Lebenspunkte abgezogen
		lebenV-=schadenV;
		lebenA-=schadenA;
		defender.getEinheit().getDatenAktuell().set("leben", ""+lebenV);
		attacker.getEinheit().getDatenAktuell().set("leben", ""+lebenA);
		attacker.getEinheit().getDatenAktuell().dec("bewegung"); //Nach dem Angriff setzt die Bewegung von dem Krieger zu 0 ein, damit der Krieger nicht mehr als einmal angreifen kann.
		
		if(lebenV<=0) {
			JOptionPane.showMessageDialog(null,"Der Krieger von " + attacker.getEinheit().getSpieler() + " hat der Krieger von " + defender.getEinheit().getSpieler() + " umgebracht!");
			defender.setEinheit(null); //Das Bild wird vom Gui geloescht
			
		} 
		else if(lebenA<=0) {
			JOptionPane.showMessageDialog(null,"Der Krieger von " + defender.getEinheit().getSpieler() + " hat der Krieger von " + attacker.getEinheit().getSpieler() + " umgebracht!");
			attacker.setEinheit(null); //Das Bild wird vom Gui geloescht
		}
	}
	
	public int attackieren() {
		int r = rd.nextInt(3); //Beim Angriff wird Angriffspunkte in 3 Stufen unterschieden
		int res=0;
		switch(r) 
		{
		case 0 : res = 10; break; //Level 2 - stabil
		case 1 : res = 15; break; //Level 3 - stark
		case 2 : res = 20; break; //Level 4 - zerstoerlich
 		}
		return res;
	}
	
	public int verteidigen() {
		int r = rd.nextInt(3); //Beim Verteidigen wird Verteidigungspunkte in 3 Stufen unterschieden
		int res=0;
		switch(r) 
		{
		case 0 : res = 5; break; //Level 1 - kraftlos
		case 1 : res = 8; break; //Level 2 - stabil
		case 2 : res = 10; break; //Level 3 - nashorn
		}
		return res;
	}

	public abstract void setErlaubteFelder();

	public abstract void init();
}
