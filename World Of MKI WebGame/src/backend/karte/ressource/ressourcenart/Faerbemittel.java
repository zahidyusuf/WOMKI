package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Faerbemittel extends Ressource {
	public Faerbemittel() {
		super(RessourcenArt.Faerbemittel,RessourcenArt.getBild(RessourcenArt.Faerbemittel));
	}
}