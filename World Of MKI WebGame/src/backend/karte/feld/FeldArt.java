package backend.karte.feld;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Servlets.gamescreen1;



public enum FeldArt {
		Berg,Dschungel,Eis,Huegel,Kueste,Meer,Steppe,Sumpf,Wiese,Wald,Wueste,Leere;
		
		private static String pfadBild="C://daten//felder";
		private static String packageKlasse="backend.karte.feld.feldart.";

		private static BufferedImage[] bild=new BufferedImage[FeldArt.values().length];
		static{
			try {
				for(int i=0;i<FeldArt.values().length;i++){
					String name=(""+FeldArt.values()[i]).toLowerCase();
					bild[i]=ImageIO.read(new File(pfadBild,name+".jpg"));
					System.out.println("KLasse FeldArt");
					System.out.println(bild[i].toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		public static FeldArt getFeldArt(String fArtString){
			for(int i=0;i<FeldArt.values().length;i++){
				if ((""+FeldArt.values()[i]).equals(fArtString))
					return FeldArt.values()[i]; 
			}
			return null;
		}
		
		public static Feld getFeld(FeldArt art) {
			return getFeld(""+art);
		}
		
		public static Feld getFeld(String art){
	    try {
	  		@SuppressWarnings("unchecked")
	  		Class<Feld> c=(Class<Feld>)Class.forName(packageKlasse+art);
	  		Feld f=c.newInstance();	// Reflection?
	  		for(int i=0;i<FeldArt.values().length;i++){
	  			if (art.equals(""+FeldArt.values()[i])){
	  				f.setArt(FeldArt.values()[i]);
	  				f.setBild(FeldArt.getBild(art));
	  				break;
	  			}
	  		}
				return f;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public static BufferedImage getBild(FeldArt art) {
			return bild[art.ordinal()];
		}
		
		public static BufferedImage getBild(String art) {
			for(int i=0;i<FeldArt.values().length;i++){
				if (art.equals(""+FeldArt.values()[i])){
					return getBild(FeldArt.values()[i]);
				}
			}
			return null;
		}
}
