package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Wueste extends Feld {
	public Wueste(){
		setErlaubteRessourcen();
	}

	public Wueste(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Wueste);
		setErlaubteRessourcen();
	}
	
	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Oel);
		addErlaubteRessource(RessourcenArt.Oase);
		addErlaubteRessource(RessourcenArt.Salpeter);
		addErlaubteRessource(RessourcenArt.Weihrauch);
	}
}
