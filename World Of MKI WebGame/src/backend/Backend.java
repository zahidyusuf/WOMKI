package backend;

import interfaces.iBackend;
import interfaces.iBean;
import java.awt.Image;
import java.util.ArrayList;
import backend.karte.Karte;
import backend.karte.KartenArt;
import backend.karte.feld.Feld;
import backend.karte.feld.FeldArt;
import backend.karte.ressource.RessourcenArt;
import backend.spiel.Spiel;
import backend.spiel.einheit.Einheit;
import backend.spiel.einheit.EinheitArt;
import backend.spiel.stadt.Stadt;
import backend.spieler.Spieler;
import backend.spieler.nation.NationArt;
import daten.d;
import daten.dEinheit;
import daten.dSpieler;
import daten.dStadt;

public class Backend implements iBackend{
	private iBean fr;
	private Dateizugriff dz;
	private Karte k; // die gerade geladene, sichtbare Karte
	private Spiel sp;

	public Backend(iBean fr){
		this.fr=fr;
		dz=new Dateizugriff(this,fr);
	}

	@Override
	public iBean getFrontend() {
		return fr;
	}

	@Override
	public int getAufloesungX() {
		return BackendParameter.aufloesungX;
	}

	@Override
	public int getAufloesungY() {
		return BackendParameter.aufloesungY;
	}
	
	@Override
	public int getSpielfeldGroesse(){
		return BackendParameter.spielfeldGroesse;
	}
	
	@Override
	public int getZoomfaktor(){
		return BackendParameter.zoomfaktor;
	}
	
	@Override
	public void setZoomfaktor(int x){
		BackendParameter.zoomfaktor=x;
	}

	@Override
	public ArrayList<String> getKartenarten() {
		ArrayList<String> arten=new ArrayList<String>(); 
		for (KartenArt x:KartenArt.values()) arten.add(""+x);
		return arten;
	}

	@Override
	public ArrayList<String> getErlaubteFeldarten(String kArt) {
		ArrayList<String> arten=new ArrayList<String>(); 
		for (FeldArt x:KartenArt.getErlaubteFeldarten(kArt)) arten.add(""+x);
		return arten;
	}

	@Override
	public ArrayList<String> getErlaubteRessourcen(String fArt) {
		ArrayList<String> arten=new ArrayList<String>(); 
		Feld f=FeldArt.getFeld(fArt);
		for (RessourcenArt x:f.getErlaubteRessourcen()) arten.add(""+x);
		return arten;
	}
	
	@Override
	public ArrayList<String> getNationsarten() {
		ArrayList<String> arten=new ArrayList<String>(); 
		for (NationArt x:NationArt.values()) arten.add(""+x);
		return arten;
	}
	
	@Override
	public void neuesSpiel(String dateiKarte, ArrayList<dSpieler> spielerDaten) {
		ladenKarte(dateiKarte);
		sp=new Spiel(this,k,spielerDaten,true);
	}
	
	@Override
	public void neuesSpielGamescreen(String dateiKarte) {
		ladenKarte(dateiKarte);
	}
	
	
	@Override
	public void spielLaden(String dateiSpiel) {
		dz.ladenSpiel(dateiSpiel);
	}

	@Override
	public void spielSpeichern(String dateiSpiel) {
		dz.speichernSpiel(dateiSpiel);
	}
	
	@Override
	public void neueKarte(int id, int spielfeldAnzahlX, int spielfeldAnzahlY, String kArt, String fArt) {
		sp=null;
		Karte k=new Karte(this,id,spielfeldAnzahlX,spielfeldAnzahlY,kArt,fArt);
		setKarte(k);
	}
	
	
	
	@Override
	public void ladenKarte(String datei) {
		sp=null;
		dz.ladenKarte(datei);
	
	}
	
	
	
	
	@Override
	public void speichernKarte(String datei) {
		dz.speichernKarte(datei);
	}
	
	@Override
	public void waehleFeld(int[] pos) {
		k.waehleFeld(pos);	
	}

	@Override
	public String getGeladeneKartenart() {
		if (k==null) return null;
		return ""+k.getArt();
	}

	@Override
	public String getFeldInfos(int[] pos) {
		Feld f=k.getFeld(pos);
		return f.toString();
	}

	@Override
	public Image getFeldBild(int[] pos) {
		Feld f=k.getFeld(pos);
		return f.getBild();
	}

	
	
	
	
	@Override
	public String getFeldArt(int[] pos) {
		Feld f=k.getFeld(pos);
		return ""+f.getArt();
	}
	
	public Feld getFeld(int[] pos) {
		return k.getFeld(pos);
	}

	@Override
	public void setFeldart(String fArtNeu,int[] pos) {
		Feld f=FeldArt.getFeld(fArtNeu);
		f.init(this,pos[0],pos[1],fArtNeu);
		k.setFeld(pos[0],pos[1],f);
		
	}

	@Override
	public void setRessource(String res,int[] pos) {
		k.setRessource(res,pos[0],pos[1]);
		
	}
	
	@Override
	public void delRessource(int[] pos) {
		k.delRessource(pos[0],pos[1]);
	
	}

	@Override
	public void setSpielerstart(int spielernummer,int[] pos) {
		k.setSpielerstart(spielernummer,pos[0],pos[1]);
	}
	
	@Override
	public int[] getGeladeneKartengroesse() {
		if (k==null) return null;
		return new int[]{k.getGroesseX(),k.getGroesseY()};
	}

	public Karte getKarte() {
		return k;
	}
	
	public void setKarte(Karte k){
		this.k=k;
	}
	
	public Spiel getSpiel() {
		return sp;
	}
	
	public void setSpiel(Spiel sp){
		this.sp=sp;
	}

	@Override
	public int getRunde() {
		if (sp==null) return 0;
		return sp.getRunde();
	}

	@Override
	public String getSpielernameAmZug() {
		if (sp==null) return "";
		return sp.getSpielerAmZug().getName();
	}
	
	public Spieler getSpielerAmZug() {
		if (sp==null) return null;
		return sp.getSpielerAmZug();
	}

	@Override
	public void rundeBeenden() {
		if (sp==null) return;
		k.setFeldGewaehlt(null);
		sp.naechsterSpieler();
		k.regenerationEinheitAufKarte();
	}

	@Override
	public int[] getPosFeldGewaehlt() {
		if (k==null) return null;
		if (k.getFeldGewaehlt()==null) return null;
		return k.getFeldGewaehlt().getPos();
	}

	@Override
	public boolean getFeldGewaehltHatEigeneEinheit() {
		int[] pos=getPosFeldGewaehlt();
		if (pos==null) return false;
		if (k==null) return false;
		Feld f=k.getFeld(pos);
		Einheit ei=f.getEinheit();
		if (ei==null) return false;
		return (ei.getSpieler().equals(getSpielerAmZug()));
	}

	@Override
	public boolean getFeldGewaehltHatEigeneStadt() {
		int[] pos=getPosFeldGewaehlt();
		if (pos==null) return false;
		if (k==null) return false;
		Feld f=k.getFeld(pos);
		Stadt st=f.getStadt();
		if (st==null) return false;
		return (st.getSpieler().equals(getSpielerAmZug()));
	}
	
	@Override
	public void bewegeEinheit(int ea) {
		if (sp==null) return;
		sp.bewegeEinheit(ea);
	}

	@Override
	public dEinheit[] getDatenEinheit(int[] pos) {
		if (pos==null) return null;
		if (k==null) return null;
		Feld f=k.getFeld(pos);
		Einheit ei=f.getEinheit();
		if (ei==null) return null;
		return new dEinheit[]{ei.getDatenAktuell(),ei.getDatenMaximal()};
	}

	@Override
	public dStadt[] getDatenStadt(int[] pos) {
		if (pos==null) return null;
		if (k==null) return null;
		Feld f=k.getFeld(pos);
		Stadt st=f.getStadt();
		if (st==null) return null;
		return new dStadt[]{st.getDaten()};
	}

	@Override
	public dSpieler getSpielerdaten(int idSpieler) {
		if (sp==null) return null;
		Spieler s=getSpieler(idSpieler);
		if (s==null) return null;
		return s.getDaten();
	}
	
	@Override
	public void neueStadt(dEinheit[] dE, String name) {
		Spieler s=getSpieler(d.toInt(dE[0].get("idSpieler")));
		Einheit ei=s.getEinheit(d.toInt(dE[0].get("id")));
		s.addStadt(new Stadt(this,ei,name,sp.getRunde()));
	
	}

	@Override
	public ArrayList<String> getProduktionsliste(int idSpieler, int idStadt) {
		Stadt st=getStadt(idSpieler,idStadt);
		if (st==null) return null;
		return st.getProduktionsliste();
	}

	public Spieler getSpieler(int idSpieler) {
		if (sp==null) return null;
		for (Spieler s:sp.getSpieler()){
			if (s.getId()==idSpieler) return s;
		}
		return null;
	}

	public Stadt getStadt(int idSpieler,int idStadt) {
		Spieler sp=getSpieler(idSpieler);
		if (sp==null) return null;
		for (Stadt st:sp.getStaedte()){
			if (st.getId()==idStadt) return st;
		}
		return null;
	}

	@Override
	public int getProduktionskosten(String einheit) {
		Einheit ei=EinheitArt.getEinheit(einheit);
		return d.toInt(ei.getDatenMaximal().get("produktionskosten"));
	}
	
	@Override
	public void produktion(dStadt[] dS, Object inhalt){
		Stadt st = null;
		st = getStadt(d.toInt(dS[0].get("idSpieler")), d.toInt(dS[0].get("id")));
		Feld f = k.getFeld(st.getPos());
		if(f.getEinheit() == null){
			if(inhalt.equals("Siedler")){
				st.siedlerProduzieren();
			}
			else if(inhalt.equals("Krieger")){
				st.kriegerProduzieren();
			}
		}
	}
	
	@Override
	public void SiedlerProduzieren(Object inhalt){
		Stadt st = null;
		dStadt[] dS = null;
		st = getStadt(d.toInt(dS[0].get("idSpieler")), d.toInt(dS[0].get("id")));
		Feld f = k.getFeld(st.getPos());
		if(f.getEinheit() == null){
			if(inhalt.equals("Siedler")){
				st.siedlerProduzieren();
			}
		}
		}
	
	
	@Override
	public void KriegerProduzieren(Object inhalt){
		Stadt st = null;
		dStadt[] dS = null;
		st = getStadt(d.toInt(dS[0].get("idSpieler")), d.toInt(dS[0].get("id")));
		Feld f = k.getFeld(st.getPos());
		if(f.getEinheit() == null){
			if(inhalt.equals("Siedler")){
				st.kriegerProduzieren();
			}
		}
	}
	
	
}