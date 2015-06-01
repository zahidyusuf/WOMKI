package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Steppe extends Feld {
	public Steppe(){
		setErlaubteRessourcen();
	}

	public Steppe(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Steppe);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Elfenbein);
		addErlaubteRessource(RessourcenArt.Pferde);
		addErlaubteRessource(RessourcenArt.Rinder);
		addErlaubteRessource(RessourcenArt.Wein);
		addErlaubteRessource(RessourcenArt.Weizen);
		addErlaubteRessource(RessourcenArt.Zucker);
	}
}
