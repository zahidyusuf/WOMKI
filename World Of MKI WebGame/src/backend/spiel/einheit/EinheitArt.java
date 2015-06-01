package backend.spiel.einheit;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public enum EinheitArt {
	Siedler,Krieger;
	
	private static String pfadBild="C://daten//einheiten";
	private static String packageKlasse="backend.spiel.einheit.einheitart.";
	
	private static BufferedImage[] bild=new BufferedImage[EinheitArt.values().length];
	static{
		try {
			for(int i=0;i<EinheitArt.values().length;i++){
				String name=(""+EinheitArt.values()[i]).toLowerCase();
				bild[i]=ImageIO.read(new File(pfadBild,name+".png"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static Einheit getEinheit(EinheitArt art) {
		return getEinheit(""+art);
	}
	
	public static Einheit getEinheit(String art){
    try {
  		@SuppressWarnings("unchecked")
  		Class<Einheit> c=(Class<Einheit>)Class.forName(packageKlasse+art);
  		Einheit ei=c.newInstance();
 // 		ei.init();
			return ei;
		} catch (Exception e) {
			return null;
		}
	}
	
	public static BufferedImage getBild(String art){
		for(int i=0;i<EinheitArt.values().length;i++){
			if (art.equals(""+EinheitArt.values()[i])){
				return bild[i];
			}
//		return bild[art.ordinal()];
		}
		return null;
	}
}

