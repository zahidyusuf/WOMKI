package backend.karte;

import java.util.ArrayList;

import backend.karte.feld.FeldArt;

public enum KartenArt {
	Erde,Unterwasser,Weltraum;
	
	public static KartenArt getKartenArt(String kArtString){
		for(int i=0;i<KartenArt.values().length;i++){
			if ((""+KartenArt.values()[i]).equals(kArtString))
				return KartenArt.values()[i]; 
		}
		return null;
	}
	
	public static ArrayList<FeldArt> getErlaubteFeldarten(String kArtString){
		ArrayList<FeldArt> erlaubt=new ArrayList<FeldArt>();
		KartenArt kArt=getKartenArt(kArtString);
		switch (kArt){
		case Erde:
			erlaubt.add(FeldArt.Berg);
			erlaubt.add(FeldArt.Dschungel);
			erlaubt.add(FeldArt.Eis);
			erlaubt.add(FeldArt.Huegel);
			erlaubt.add(FeldArt.Kueste);
			erlaubt.add(FeldArt.Meer);
			erlaubt.add(FeldArt.Steppe);
			erlaubt.add(FeldArt.Sumpf);
			erlaubt.add(FeldArt.Wald);
			erlaubt.add(FeldArt.Wiese);
			erlaubt.add(FeldArt.Wueste);
			break;
		case Unterwasser:
			erlaubt.add(FeldArt.Meer);
			break;
		case Weltraum:
			erlaubt.add(FeldArt.Leere);
			break;
		}
		return erlaubt;
	}
}