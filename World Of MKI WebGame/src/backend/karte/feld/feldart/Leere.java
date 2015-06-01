package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.Feld;
import backend.karte.feld.FeldArt;
import backend.karte.ressource.RessourcenArt;

public class Leere extends Feld {
	public Leere(){
		setErlaubteRessourcen();
	}

	public Leere(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Leere);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Fisch);
		addErlaubteRessource(RessourcenArt.Oel);
		addErlaubteRessource(RessourcenArt.Wal);
	}
}