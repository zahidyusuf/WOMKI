package backend;

import interfaces.iBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import daten.d;
import daten.dEinheit;
import daten.dSpiel;
import daten.dSpieler;
import daten.dStadt;
import backend.karte.*;
import backend.karte.feld.*;
import backend.spiel.Spiel;
import backend.spiel.einheit.*;
import backend.spiel.stadt.Stadt;
import backend.spieler.Spieler;

public class Dateizugriff{
	private Backend ba;
	private iBean fr;
	
	public Dateizugriff(Backend ba,iBean fr){
		this.ba=ba;
		this.fr=fr;
	}

	public void ladenKarte(String datei) {
	  if (!datei.endsWith(".map")) datei=datei+".map";
  	BufferedReader br=null;
  	try{
    	br=new BufferedReader(new FileReader(datei));
    	ladenKarte(br);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BackendException(e.getMessage());
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {}
		}
  }

	
	
	
	
	public void ladenKarte(BufferedReader br) throws IOException {
//		fr.log("Lade Kartendaten...");
  	String karteIdString=br.readLine();
  	String karteArtString=br.readLine();
		int groesseX=d.toInt(br.readLine());
		int groesseY=d.toInt(br.readLine());
		Karte k=new Karte(ba,d.toInt(karteIdString),groesseX,groesseY,karteArtString);
		ba.setKarte(k);
  	for(int i=1;i<=groesseX;i++){
  		for(int j=1;j<=groesseY;j++){
  			String datensatz=br.readLine();
  			if ((datensatz==null)||(datensatz.length()==0)) continue;
  			String[] daten=datensatz.split(";");
  			if ((daten==null)||(daten.length!=5)) continue;
  			int x=d.toInt(daten[0]);
  			int y=d.toInt(daten[1]);
  			String fArt=daten[2];
  			String res=null;
  			if ((daten[3]!=null)&&(daten[3].length()>0)) res=daten[3];
  			int spielerstart=d.toInt(daten[4]);
  			k.setFeld(x,y,fArt,res,spielerstart);
	  	}
  	}
  	// Zoomfaktor
  	int zoomfaktor=d.toInt(br.readLine());
  	ba.setZoomfaktor(zoomfaktor);
//	fr.log("OK. Die geladene Karte ist "+groesseX+" Felder breit und "+groesseY+" Felder hoch.");
	}
	

	public void speichernKarte(String datei) {
		if (ba.getKarte()==null){
//			fr.log("ABBRUCH: Keine Karte vorhanden!");
			return;
		}
	  if (!datei.endsWith(".map")) datei=datei+".map";
  	BufferedWriter bw=null;
	  try {
	  	bw=new BufferedWriter(new FileWriter(datei));
	  	speichernKarte(bw);
		} catch (Exception e) {
//			fr.log("FEHLER: "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				bw.close();
			} catch (IOException e) {}
		}
	}

	public void speichernKarte(BufferedWriter bw) throws IOException {
//		fr.log("Speichere Kartendaten...");
		Karte k=ba.getKarte();
  	bw.write(""+k.getId());
  	bw.newLine();
  	bw.write(""+k.getArt());
  	bw.newLine();
  	bw.write(""+k.getGroesseX());
  	bw.newLine();
  	bw.write(""+k.getGroesseY());
  	bw.newLine();
  	Feld[][] felder=k.getFelder();
   	for(int i=0;i<felder.length;i++){
  		for(int j=0;j<felder[0].length;j++){
	  		Feld f=felder[i][j];
	  		if (f==null) continue;
	  		String s="";
	  		s+=i+";";
	  		s+=j+";";
	  		s+=f.getArt()+";";
	  		if(f.getRessource()==null)
		  		s+=";";		  			
	  		else
		  		s+=f.getRessource().getArt()+";";
	  		s+=f.getSpielerstart();
		  	bw.write(s);
		  	bw.newLine();
  		}
   	}
  	// Zoomfaktor
  	bw.write(""+ba.getZoomfaktor());
  	bw.newLine();
//  	fr.log("OK");
	}
	
	public void ladenSpiel(String datei) {
	  if (!datei.endsWith(".wom")) datei=datei+".wom";
  	BufferedReader br=null;
	  try {
	  	br=new BufferedReader(new FileReader(datei));
	  	// Karte laden
	  	ladenKarte(br);
	  	Karte k=ba.getKarte();
	  	// gewaehltes Feld
	  	String felddaten=br.readLine();
	  	if (!felddaten.equals("0;0")){
	  		String[] daten=felddaten.split(";");
	  		if ((daten==null)||(daten.length!=2))
	  			throw new RuntimeException("Daten ungueltig: "+felddaten);
	  		int[] pos=new int[2];
	  		try{
	  			pos[0]=d.toInt(daten[0]);
	  			pos[1]=d.toInt(daten[1]);
	  			ba.getKarte().setFeldGewaehlt(pos);
	  		}
	  		catch (Exception e){
	  			e.printStackTrace();
	  			throw new RuntimeException("Datenwert ungueltig: "+e.getMessage());
	  		}		
	  	}
	  	// Spielstatus laden
	  	dSpiel spielstatus=new dSpiel(br.readLine());
	  	// Spieler nacheinander laden
	  	int anzahlSpieler=d.toInt((spielstatus.get("anzahlSpieler")));
	  	ArrayList<dSpieler> alleSpielerDaten=new ArrayList<dSpieler>();
	  	ArrayList<Spieler> spielerListe=new ArrayList<Spieler>();
	  	for(int i=1;i<=anzahlSpieler;i++){
	  		dSpieler einSpielerDaten=new dSpieler(br.readLine());
	  		alleSpielerDaten.add(einSpielerDaten);
	  		Spieler spieler=new Spieler(k,einSpielerDaten);
	  		spielerListe.add(spieler);
		  	// Einheiten dieses Spielers laden
		  	int anzahlEinheiten=d.toInt(br.readLine());
		  	for(int j=1;j<=anzahlEinheiten;j++){
		  		dEinheit einheitDatenMaximal=new dEinheit(br.readLine());
		  		Einheit ei=EinheitArt.getEinheit(einheitDatenMaximal.get("art"));
		  		ei.setDatenMaximal(einheitDatenMaximal);
		  		dEinheit einheitDatenAktuell=new dEinheit(br.readLine());
		  		ei.setDatenAktuell(einheitDatenAktuell);
		  		spieler.addEinheit(ei);
		  	}	
		  	// Staedte jedes Spielers laden
		  	int anzahlStaedte=d.toInt(br.readLine());
		  	for(int j=1;j<=anzahlStaedte;j++){
		  		dStadt stadtDaten=new dStadt(br.readLine());
		  		new Stadt(ba,spieler,stadtDaten);
		  	}	
	  	}
			Spiel spiel=new Spiel(ba,k,alleSpielerDaten,false);
			spiel.setDaten(spielstatus);
			spiel.setSpieler(spielerListe);
			ba.setSpiel(spiel);
		} catch (Exception e) {
//			fr.log("FEHLER: "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {}
		}
	}

	public void speichernSpiel(String datei){
		Spiel sp=ba.getSpiel();
  	BufferedWriter bw=null;
		try {
			if (sp==null) throw new BackendException("Kein Spiel vorhanden!");
			Karte k=sp.getKarte();
			if (k==null) throw new BackendException("Keine Karte vorhanden!");
		  if (!datei.endsWith(".wom")) datei=datei+".wom";
	  	bw=new BufferedWriter(new FileWriter(datei));
	  	// Karte speichern
	  	speichernKarte(bw);
	   	// gewaehltes Feld
	   	if (k.getFeldGewaehlt()!=null)
		   	bw.write(k.getFeldGewaehlt().getPosX()+";"+k.getFeldGewaehlt().getPosY());
	   	else
		   	bw.write("0;0");
	  	bw.newLine();	   	
	   	// Spielstatus speichern
	  	bw.write(sp.getDaten().getCsv()); // anzahlSpieler;runde;spielerAmZug
	  	bw.newLine();	   	
	   	// Spieler speichern
	   	ArrayList<Spieler> spielerListe=sp.getSpieler();
	   	for(Spieler spieler:spielerListe){
		  	bw.write(spieler.getDaten().getCsv()); // id;name;nation
		  	bw.newLine();
		   	// Einheiten jedes Spielers speichern
		  	ArrayList<Einheit> einheitenListe=spieler.getEinheiten();
		  	bw.write(""+einheitenListe.size()); // Anzahl der Einheiten dieses Spielers
		  	bw.newLine();
		  	for(Einheit einheit:einheitenListe){
			  	bw.write(einheit.getDatenMaximal().getCsv()); // Aktuell: id;art;posX;posY;leben;bewegung;angriff;verteidigung
			  	bw.newLine();
			  	bw.write(einheit.getDatenAktuell().getCsv()); // Maximal: id;art;posX;posY;leben;bewegung;angriff;verteidigung
			  	bw.newLine();
		  	}
		   	// Staedte jedes Spielers speichern
		  	ArrayList<Stadt> stadtListe=spieler.getStaedte();
		  	bw.write(""+stadtListe.size()); // Anzahl der Staedte dieses Spielers
		  	bw.newLine();
		  	for(Stadt stadt:stadtListe){
			  	bw.write(stadt.getDaten().getCsv()); // name;posX;posY;rundeGruendung,groesse
			  	bw.newLine();
		  	}
			}
		}
		catch (Exception e){
//			fr.log("FEHLER: "+e.getMessage());
			e.printStackTrace();
		}
		finally{
			try {
				bw.close();
			} catch (IOException e) {}
		}
	}
}
