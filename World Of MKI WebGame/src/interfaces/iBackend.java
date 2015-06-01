package interfaces;

import java.awt.Image;
import java.util.ArrayList;

import backend.spiel.Spiel;

import daten.dEinheit;
import daten.dSpieler;
import daten.dStadt;

public interface iBackend {
	iBean getFrontend();

	int getAufloesungX();

	int getAufloesungY();

	int getSpielfeldGroesse();

	int getZoomfaktor();

	void setZoomfaktor(int x);

	void SiedlerProduzieren(Object inhalt);
	
	void KriegerProduzieren(Object inhalt);
	// Karteneditor
	ArrayList<String> getKartenarten();

	ArrayList<String> getErlaubteFeldarten(String kArt);

	ArrayList<String> getErlaubteRessourcen(String fArt);

	void neueKarte(int id, int spielfeldAnzahlX, int spielfeldAnzahlY,
			String kArt, String fArt);

	


	

	String getGeladeneKartenart();

	String getFeldInfos(int[] pos);

	Image getFeldBild(int[] pos);

	String getFeldArt(int[] pos);

	void setFeldart(String fArtNeu, int[] pos);

	void setRessource(String res, int[] pos);

	void delRessource(int[] pos);

	void setSpielerstart(int spielernummer, int[] pos);

	void ladenKarte(String datei);

	void speichernKarte(String datei);

	int[] getGeladeneKartengroesse();

	// Spiel
	ArrayList<String> getNationsarten();

	void neuesSpiel(String dateiKarte, ArrayList<dSpieler> spielerDaten);

	void neuesSpielGamescreen(String dateiKarte);
	
	void spielLaden(String datei);

	void spielSpeichern(String datei);

	void waehleFeld(int[] pos);

	int getRunde();

	String getSpielernameAmZug();

	void rundeBeenden();

	int[] getPosFeldGewaehlt();

	void bewegeEinheit(int ordinal);

	boolean getFeldGewaehltHatEigeneEinheit();

	boolean getFeldGewaehltHatEigeneStadt();

	dEinheit[] getDatenEinheit(int[] pos);

	dStadt[] getDatenStadt(int[] pos);

	dSpieler getSpielerdaten(int idSpieler);

	void neueStadt(dEinheit[] dE, String name);

	ArrayList<String> getProduktionsliste(int idSpieler, int idStadt);

	int getProduktionskosten(String einheit);

	Spiel getSpiel();
	
	void produktion(dStadt[] dS, Object inhalt);

	


}