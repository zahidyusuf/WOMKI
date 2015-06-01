package backend.karte.feld.feldart;

import backend.Backend;
import backend.karte.feld.*;
import backend.karte.ressource.*;

public class Wald extends Feld {
	public Wald(){
		setErlaubteRessourcen();
	}

	public Wald(Backend b,int posX,int posY) {
		super(b,posX,posY,FeldArt.Wald);
		setErlaubteRessourcen();
	}

	@Override
	public void setErlaubteRessourcen(){
		addErlaubteRessource(RessourcenArt.Faerbemittel);
		addErlaubteRessource(RessourcenArt.Gewuertz);
		addErlaubteRessource(RessourcenArt.Gummi);
		addErlaubteRessource(RessourcenArt.Pelz);
		addErlaubteRessource(RessourcenArt.Seide);
		addErlaubteRessource(RessourcenArt.Wild);
		addErlaubteRessource(RessourcenArt.Uran);
	}
}
