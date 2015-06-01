package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Meer extends Feld {
	public Meer(){
		setErlaubteRessourcen();
	}

	public Meer(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Meer);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Fisch);
		addErlaubteRessource(RessourcenArt.Oel);
		addErlaubteRessource(RessourcenArt.Wal);
	}
}
