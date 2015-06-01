package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Sumpf extends Feld {
	public Sumpf(){
		setErlaubteRessourcen();
	}

	public Sumpf(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Sumpf);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Oel);
		addErlaubteRessource(RessourcenArt.Gummi);
		addErlaubteRessource(RessourcenArt.Wild);
	}
}
