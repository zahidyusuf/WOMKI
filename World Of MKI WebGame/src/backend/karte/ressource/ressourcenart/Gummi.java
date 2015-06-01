package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Gummi extends Ressource {
	public Gummi() {
		super(RessourcenArt.Gummi,RessourcenArt.getBild(RessourcenArt.Gummi));
	}
}