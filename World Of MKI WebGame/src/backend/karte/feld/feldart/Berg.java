package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Berg extends Feld {
	public Berg(){
		setErlaubteRessourcen();
	}
	
	public Berg(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Berg);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Edelsteine);
		addErlaubteRessource(RessourcenArt.Eisen);
		addErlaubteRessource(RessourcenArt.Gold);
		addErlaubteRessource(RessourcenArt.Kohle);
		addErlaubteRessource(RessourcenArt.Salpeter);
		addErlaubteRessource(RessourcenArt.Uran);
	}
}