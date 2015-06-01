package frontend;

import interfaces.iBackend;
import interfaces.iBean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

import org.apache.tomcat.util.codec.binary.Base64;

import sun.misc.BASE64Encoder;
import Servlets.Warten;
import Servlets.s1Anmelden;
import Servlets.s2Anmelden;
import Servlets.s3Anmelden;
import Servlets.s4Anmelden;
import backend.Backend;
import backend.spieler.Spieler;
import daten.dSpieler;
import errorHandling.SpielerException;

//endet auf Bean und ist serialisierbar
public class FrontendBean implements iBean, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private iBackend ba;
	private boolean S1erstellt = false;
	private boolean S2erstellt = false;
	private boolean S3erstellt = false;
	private boolean S4erstellt = false;
	private Spieler spieler1;
	private Spieler spieler2;
	private Spieler spieler3;
	private Spieler spieler4;
	private boolean fehler = false;
	private boolean meldung = false;
	private String fehlerNachricht;
	private String meldungNachricht;
	private boolean gui = false;
	private manager guiManager;
	private byte aktiverSpieler = 1;
	private int spielzug;
	private String holeKarte;
	private String log = "", status = "";


	// default-Konstruktor
	public FrontendBean() {
		ba = new Backend(this);

	}

	public void anmelden(String name) throws SpielerException {

		

		if(getAngemeldet1() == true & getS1Name() != null)	{	
					setSpieler1(new backend.spieler.Spieler(name));
				
				S1erstellt = true;

				System.out.println("Spieler 1 erstellt");

			


			}

			if (getAngemeldet2() == true & getS2Name() != null)  {

				setSpieler2(new backend.spieler.Spieler(name));

				S2erstellt = true;

				System.out.println("Spieler 2 erstellt");


			}

			if (getAngemeldet3() == true & getS3Name() != null) {

				setSpieler3(new backend.spieler.Spieler(name));

				S3erstellt = true;

				System.out.println("Spieler 3 erstellt");


			}

			if (getAngemeldet4() == true & getS4Name() != null) {

				setSpieler4(new backend.spieler.Spieler(name));

				S4erstellt = true;

				System.out.println("Spieler 4 wurde erstellt");


				
				
			}

		
	}
	

	@Override
	public iBackend getBackend() {
		return ba;
	}




	
//	
//	public void neuesSpiel(String name, String nation, String karte){
//		dSpieler a = new dSpieler();
//		a.add("id", ""+1);
//		a.add("name", name);
//		a.add("nation", nation);
//		holeKarte = "D://daten//karten//"+getKarte()+".map";
//		this.getSpieleranz();
//	}
//	

	
	public void spielerHinzufuegen(String name, String nation){
		dSpieler s = new dSpieler();
		s.add("id", ""+DatensammlungSpieler.spieler.size()+1);
		s.add("name", name);
		s.add("nation", nation);

		DatensammlungSpieler.spieler.add(s);
		
		
	}
	
	/*
	public String umwandeln(int[] pos) {
		try {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
					
		BufferedImage b = getBufferedImage(pos);			
		ImageIO.write(b, "/WorldOfMki_Web02/Karten/Kontinente.map", os);
				
		byte[] s = os.toByteArray();

		return Base64.encodeBase64String(s);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	*/

	public void zeichneFeld(int[] pos) {
		BufferedImage img = null;
		img = (BufferedImage)ba.getFeldBild(pos);
		String imgstr;
		imgstr = "data:image/png;base64," + encodeToString(img, "png");
		
	}

	// getter und setter öffentlich sichtbar
	public iBackend getBa() {
		return ba;
	}

	public void setBa(iBackend ba) {
		this.ba = ba;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setS1erstellt(boolean s1erstellt) {
		S1erstellt = s1erstellt;
	}

	public void setS2erstellt(boolean s2erstellt) {
		S2erstellt = s2erstellt;
	}

	public void setS3erstellt(boolean s3erstellt) {
		S3erstellt = s3erstellt;
	}

	public void setS4erstellt(boolean s4erstellt) {
		S4erstellt = s4erstellt;
	}

	public String getS1Name() {

		return s1Anmelden.spielerName;
	}

	public String getS2Name() {

		return s2Anmelden.spielerName;
	}

	public String getS3Name() {

		return s3Anmelden.spielerName;
	}

	public String getS4Name() {

		return s4Anmelden.spielerName;
	}

	public boolean getS1erstellt() {
		return S1erstellt;
	}

	public boolean getS2erstellt() {
		return S2erstellt;
	}

	public boolean getS3erstellt() {
		return S3erstellt;
	}

	public boolean getS4erstelllt() {
		return S4erstellt;
	}

	public boolean getMeldung() {
		return meldung;
	}

	public void setMeldung(boolean meldung) {
		this.meldung = meldung;
	}

	public boolean getFehler() {
		return fehler;
	}

	public void setFehler(boolean fehler) {
		this.fehler = fehler;
	}

	public String getFehlerNachricht() {
		this.setFehler(false);
		// setFehlerNachricht("");
		return fehlerNachricht;
	}

	public void setFehlerNachricht(String fehlerNachricht) {
		this.setFehler(true);
		this.fehlerNachricht = fehlerNachricht;
	}

	public String getMeldungNachricht() {
		this.setMeldung(false);
		// setMeldungNachricht("");
		return meldungNachricht;
	}

	public void setMeldungNachricht(String meldungNachricht) {
		this.setMeldung(true);
		this.meldungNachricht = meldungNachricht;
	}

	public backend.spieler.Spieler getSpieler1() {

		return spieler1;
	}

	public void setSpieler1(backend.spieler.Spieler spieler1)
			throws SpielerException {

		if (spieler1 != null) {

			this.spieler1 = spieler1;

		} else {

			throw new SpielerException();
		}

	}

	public backend.spieler.Spieler getSpieler2() {

		return spieler2;
	}

	public void setSpieler2(backend.spieler.Spieler spieler2)
			throws SpielerException {

		if (spieler2 != null) {

			this.spieler2 = spieler2;

		} else {

			throw new SpielerException();
		}
	}

	public backend.spieler.Spieler getSpieler3() {

		return spieler3;
	}

	public void setSpieler3(backend.spieler.Spieler spieler3)
			throws SpielerException {

		if (spieler3 != null) {

			this.spieler3 = spieler3;

		} else {

			throw new SpielerException();
		}

	}

	public backend.spieler.Spieler getSpieler4() {

		return spieler4;
	}

	public void setSpieler4(backend.spieler.Spieler spieler4)
			throws SpielerException {

		if (spieler4 != null) {

			this.spieler4 = spieler4;

		} else {

			throw new SpielerException();
		}
	}

	public boolean isGui() {
		return gui;
	}

	public void setGui(boolean gui) {
		this.gui = gui;
	}

	public byte getAktiverSpieler() {

		return aktiverSpieler;
	}

	public void setAktiverSpieler(byte aktSpieler) {

		aktiverSpieler = aktSpieler;
	}

	public int getSpielzug() {
		return spielzug;
	}

	public void setSpielzug(int spielzug) {
		if (spielzug >= 0)
			this.spielzug = spielzug;
	}
	
	public int getSpieleranz(){
		return s1Anmelden.spieleranz;
	}
	
	public boolean getAmerika1(){
		return s1Anmelden.Amerika;
	}
	public boolean getAmerika2(){
		return s2Anmelden.Amerika;
	}
	public boolean getAmerika3(){
		return s3Anmelden.Amerika;
	}
	public boolean getChina1(){
		return s1Anmelden.China;
	}
	public boolean getChina2(){
		return s2Anmelden.China;
	}
	public boolean getChina3(){
		return s3Anmelden.China;
	}
	
	public boolean getDeutschland1(){
		return s1Anmelden.Deutschland;
	}

	public boolean getDeutschland2(){
		return s2Anmelden.Deutschland;
	}

	public boolean getDeutschland3(){
		return s3Anmelden.Deutschland;
	}
	public boolean getEngland1(){
		return s1Anmelden.England;
	}

	public boolean getEngland2(){
		return s2Anmelden.England;
	}

	public boolean getEngland3(){
		return s3Anmelden.England;
	}

	public boolean getFrankreich1(){
		return s1Anmelden.Frankreich;
	}

	public boolean getFrankreich2(){
		return s2Anmelden.Frankreich;
	}

	public boolean getFrankreich3(){
		return s3Anmelden.Frankreich;
	}
	public boolean getRussland1(){
		return s1Anmelden.Russland;
	}

	public boolean getRussland2(){
		return s2Anmelden.Russland;
	}

	public boolean getRussland3(){
		return s3Anmelden.Russland;
	}
	
	public boolean getTuerkei1(){
		return s1Anmelden.Tuerkei;
	}

	public boolean getTuerkei2(){
		return s2Anmelden.Tuerkei;
	}

	public boolean getTuerkei3(){
		return s3Anmelden.Tuerkei;
	}
		
	public boolean getAngemeldet1(){
		return s1Anmelden.angemeldet;
	}
	public boolean getAngemeldet2(){
		return s2Anmelden.angemeldet;
	}
	public boolean getAngemeldet3(){
		return s3Anmelden.angemeldet;
	}
	public boolean getAngemeldet4(){
		return s4Anmelden.angemeldet;
	}
	
	public boolean getSpielstart(){
		return Warten.spielstart;
	}
	
	
	public String Kartenzeichner(){
		String s= KarteErstellen.StringBuffer(this);
		return s;
	}
	public String Base64Encoder(int[] pos){
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			BufferedImage bild = (BufferedImage) ba.getFeldBild(pos);
			ImageIO.write(bild,"png",os);
			byte[] s = os.toByteArray();
	
			return 	Base64.encodeBase64String(s);
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public void karteLaden(String dateiName) {
//		String dateiPfad = "/WorldOfMki_Web02/Karten/Kontinente.map";
//		ba.ladenKarte(dateiPfad);
//	}
	
	
	
	public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
	
	@Override
	public void log(String text) {
		log += text + "\n";
	}

	public String getLog() {
		return log;
	}

	
//	public String[] getKarte(){
//			return s1Anmelden.karte;
//	}

}
