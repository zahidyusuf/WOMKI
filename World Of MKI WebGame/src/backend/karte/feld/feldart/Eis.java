package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Eis extends Feld {
	public Eis(){
		setErlaubteRessourcen();
	}

	public Eis(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Eis);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Aluminium);
		addErlaubteRessource(RessourcenArt.Oel);
		addErlaubteRessource(RessourcenArt.Pelz);
		addErlaubteRessource(RessourcenArt.Wild);
	}
}