package backend.spiel.stadt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import daten.d;
import daten.dStadt;
import frontend.StatusStadt;
import backend.*;
import backend.spiel.Spiel;
import backend.spiel.einheit.*;
import backend.spieler.Spieler;

public class Stadt {
	private static int counterId=1;
	private static final String pfadBild="daten//staedte";
	private Spieler sp;
	private dStadt dSt;
	private Backend ba;
	

	private int wachs;
	private StatusStadt st;
	private Einheit ei;
	private int siedlerWurdeProduziert;
	private int kriegerWurdeProduziert;
//	private static int produzierteSiedler = 0;
//	private static int produzierteKrieger = 0;
	private boolean ausgewaehlterSiedler;
	private boolean ausgewaehlterKrieger;

	


	private static BufferedImage[] bild=new BufferedImage[4];
	private static BufferedImage bildEinheitInStadt;
	static{
		try {
			bildEinheitInStadt=ImageIO.read(new File(pfadBild,"flagge.png"));
			for(int i=0;i<4;i++){
				bild[i]=ImageIO.read(new File(pfadBild,(i+1)+".png"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public int getWachs(){
		return wachs;
	}
	
	public void setWachs(int wachs){
		this.wachs = wachs;
	}
	
	public static int getNaechsteId(){
		int id=counterId;
		counterId++;
		return id;
	}
	
	public static void setBelegteId(int id){
		if (id>counterId) counterId=id+1;
	}
	
	// Stadt aus einem Siedler raus gruenden
	public Stadt(Backend ba,Einheit ei,String name,int runde) {
		if (!ei.getArt().equals("Siedler"))
			throw new BackendException("Nur Siedler koennen Staedte gruenden!");
		this.ba=ba;
		this.sp=ei.getSpieler();
		this.wachs = 0;
		dSt=new dStadt();
		dSt.set("idSpieler",""+sp.getId());
		dSt.set("id",""+Stadt.getNaechsteId());
		dSt.set("name",name);
		dSt.set("posX",""+ei.posX());
		dSt.set("posY",""+ei.posY());
		dSt.set("rundeGruendung",""+runde);
		dSt.set("groesse",""+ 1);
		dSt.set("inProduktion","nichts");
		dSt.set("siedler", "" + 0);
		dSt.set("krieger", "" + 0);
		dSt.set("siedlerAnteil","" + 0);
		dSt.set("kriegerAnteil","" + 0);
		sp.removeEinheit(ei);
		sp.addStadt(this);
	}
	
	// Stadt laden aus Datei
	public Stadt(Backend ba,Spieler spieler,dStadt dSt) {
		this.ba=ba;
		this.sp=spieler;
		this.dSt=dSt;
		spieler.addStadt(this);
	}
	
	public ArrayList<String> getProduktionsliste(){
		ArrayList<String> erg=new ArrayList<String>();
		erg.add(""+EinheitArt.Siedler);
		erg.add(""+EinheitArt.Krieger);
		return erg;
	}
	
	public int getProduktionProRunde(){
		return 1;
	}
	
	public dStadt getDaten(){
		return dSt;
	}
	
	public int getId(){
		return d.toInt(getDaten().get("id"));		
	}
	
	public int posX(){
		return d.toInt(dSt.get("posX"));
	}
	public int posY(){
		return d.toInt(dSt.get("posY"));
	}
	public int[] getPos(){
		int[] ausgabe={posX(),posY()};
		return ausgabe;
	}
	
	public String getName(){
		return dSt.get("name");
	}
	
	public Spieler getSpieler(){
		return sp;
	}
	
	public BufferedImage getBild(){
		BufferedImage ausgabe;
		BufferedImage kopieren;
		int groesse=d.toInt(dSt.get("groesse"));

		if (groesse<3) 
			ausgabe=bild[0];
		else if (groesse<6) 
			ausgabe=bild[1];
		else if (groesse<9)
			ausgabe=bild[2];
		else 
			ausgabe=bild[3];
		
		kopieren = new BufferedImage(ausgabe.getWidth(), ausgabe.getHeight(),3);
		Graphics k = kopieren.getGraphics();
		
		Graphics g=ausgabe.getGraphics();
		g.drawImage(ausgabe,0,0,null);
		
		k.drawImage(ausgabe, 0, 0, null);
		
		k.drawImage(bildEinheitInStadt, 0, 0, null);
	
		if (ba.getFeld(getPos()).getEinheit()!=null){
			return kopieren;
		}
		return ausgabe;
	}
	
	public void neueRunde() {
		Spiel spiel = ba.getSpiel();
		
		int wachsNeu = this.getWachs();
		wachsNeu++;
		
		if(spiel.getRunde() == siedlerWurdeProduziert){	
			dSt.set("inProduktion", "nichts");
//			sp.getDaten().add("Siedler", "");
			int pos[] = { d.toInt(dSt.get("posX")), d.toInt(dSt.get("posY"))};
			int produzierteSiedler = Integer.parseInt(this.getDaten().get("siedler"));
			ei = EinheitArt.getEinheit("Siedler");
			ei.setKarte(ba.getKarte());
			ei.setSpieler(sp);
			ei.setPos(pos);
			ba.getFeld(pos).setEinheit(ei);
			sp.addEinheit(ei);
			ei.getDatenAktuell().set("idSpieler", ""+sp.getId());
			
			produzierteSiedler++;
			dSt.set("siedler", String.valueOf(produzierteSiedler));
		}
		
		int anzahlSiedler = Integer.parseInt(getDaten().get("siedlerAnteil"));
		if(auswahlSiedler() == true){
			anzahlSiedler++;
			dSt.set("siedlerAnteil", String.valueOf(anzahlSiedler));
			if(spiel.getRunde() >= siedlerWurdeProduziert){
				dSt.set("siedlerAnteil", "" + 0);
			}
		}
		
		if(spiel.getRunde() == kriegerWurdeProduziert){
			dSt.set("inProduktion", "nichts");
			sp.getDaten().add("Krieger", "");
			int pos[] = { d.toInt(dSt.get("posX")), d.toInt(dSt.get("posY"))};
			int produzierteKrieger = Integer.parseInt(this.getDaten().get("krieger"));
			ei = EinheitArt.getEinheit("Krieger");
			ei.setKarte(ba.getKarte());
			ei.setSpieler(sp);
			ei.setPos(pos);
			ba.getFeld(pos).setEinheit(ei);
			sp.addEinheit(ei);
			ei.getDatenAktuell().set("idSpieler", ""+sp.getId());
			
			produzierteKrieger++;
			dSt.set("krieger", String.valueOf(produzierteKrieger));
			
		}
		
		int anzahlKrieger = Integer.parseInt(getDaten().get("kriegerAnteil"));
		if(auswahlKrieger() == true){
			anzahlSiedler++;
			dSt.set("kriegerAnteil", String.valueOf(anzahlSiedler));
			if(spiel.getRunde() >= kriegerWurdeProduziert){
				dSt.set("kriegerAnteil", "" + 0);
			}
		}
		
		if(wachsNeu < 20 && spiel.getRunde() > siedlerWurdeProduziert && spiel.getRunde() > kriegerWurdeProduziert){
			this.setWachs(wachsNeu);
		}
		else if(wachsNeu == 20){
			int groesse = Integer.parseInt(this.getDaten().get("groesse"));
			groesse++;
			dSt.set("groesse", String.valueOf(groesse));
			this.setWachs(0);
		}
		else{
			this.setWachs(0);
		}
	}
	
	public void siedlerProduzieren(){
		Spiel spiel = ba.getSpiel();
		spiel.getRunde();
		siedlerWurdeProduziert = (spiel.getRunde() + 15);
		dSt.set("inProduktion", "Siedler");
		ausgewaehlterSiedler = true;
		auswahlSiedler();
	
	}
	
	public void kriegerProduzieren(){
		Spiel spiel = ba.getSpiel();
		spiel.getRunde();
		kriegerWurdeProduziert = spiel.getRunde() + 5;
		dSt.set("inProduktion", "Krieger");
		ausgewaehlterKrieger = true;
		auswahlKrieger();
	
	}
	
	public boolean auswahlSiedler(){
		if(ausgewaehlterSiedler == true){
			return true;
		}
		return false;
	}

	public boolean auswahlKrieger(){
		if(ausgewaehlterKrieger == true){
			return true;
		}
		return false;
	}
}
