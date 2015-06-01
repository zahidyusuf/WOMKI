package backend.karte.ressource;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Servlets.gamescreen1;

public enum RessourcenArt {
	Aluminium,Edelsteine,Eisen,Elfenbein,Faerbemittel,Fisch,Gewuertz,Gold,Gummi,
	Kohle,Oase,Obst,Oel,Pelz,Pferde,Rinder,Salpeter,Seide,Tabak,Uran,Wal,Weihrauch,
	Wein,Weizen,Wild,Zucker;
	
	private static String pfadBild="C://daten//ressourcen";
	private static String packageKlasse="backend.karte.ressource.ressourcenart.";


	private static BufferedImage[] bild=new BufferedImage[RessourcenArt.values().length];
	static{
		try {
			for(int i=0;i<RessourcenArt.values().length;i++){
				String name=(""+RessourcenArt.values()[i]).toLowerCase();
				bild[i]=ImageIO.read(new File(pfadBild,name+".png"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static Ressource getRessource(RessourcenArt art){
		return getRessource(""+art);
	}
	
	public static Ressource getRessource(String art){
    try {
  		@SuppressWarnings("unchecked")
  		Class<Ressource> c=(Class<Ressource>)Class.forName(packageKlasse+art);
			return c.newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static BufferedImage getBild(RessourcenArt art){
		return bild[art.ordinal()];
	}
}