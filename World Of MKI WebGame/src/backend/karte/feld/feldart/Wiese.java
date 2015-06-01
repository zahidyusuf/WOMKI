package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Wiese extends Feld {
	public Wiese(){
		setErlaubteRessourcen();
	}

	public Wiese(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Wiese);
		setErlaubteRessourcen();
	}
	
	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Pferde);
		addErlaubteRessource(RessourcenArt.Tabak);
		addErlaubteRessource(RessourcenArt.Rinder);
		addErlaubteRessource(RessourcenArt.Wein);
		addErlaubteRessource(RessourcenArt.Weizen);
	}
}