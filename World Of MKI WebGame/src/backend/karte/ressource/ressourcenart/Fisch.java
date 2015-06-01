package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Fisch extends Ressource {
	public Fisch() {
		super(RessourcenArt.Fisch,RessourcenArt.getBild(RessourcenArt.Fisch));
	}
}