package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Dschungel extends Feld {
	public Dschungel(){
		setErlaubteRessourcen();
	}

	public Dschungel(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Dschungel);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Edelsteine);
		addErlaubteRessource(RessourcenArt.Faerbemittel);
		addErlaubteRessource(RessourcenArt.Gewuertz);
		addErlaubteRessource(RessourcenArt.Gummi);
		addErlaubteRessource(RessourcenArt.Kohle);
		addErlaubteRessource(RessourcenArt.Obst);
		addErlaubteRessource(RessourcenArt.Seide);
	}
}
