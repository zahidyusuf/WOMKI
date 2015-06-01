package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Huegel extends Feld {
	public Huegel(){
		setErlaubteRessourcen();
	}

	public Huegel(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Huegel);
		setErlaubteRessourcen();
	}


	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Aluminium);
		addErlaubteRessource(RessourcenArt.Eisen);
		addErlaubteRessource(RessourcenArt.Gold);
		addErlaubteRessource(RessourcenArt.Kohle);
		addErlaubteRessource(RessourcenArt.Salpeter);
		addErlaubteRessource(RessourcenArt.Tabak);
		addErlaubteRessource(RessourcenArt.Wein);
		addErlaubteRessource(RessourcenArt.Weihrauch);
		addErlaubteRessource(RessourcenArt.Zucker);

	}
}
