package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Kueste extends Feld {
	public Kueste(){
		setErlaubteRessourcen();
	}

	public Kueste(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Kueste);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Fisch);
		addErlaubteRessource(RessourcenArt.Oel);
	}
}
