package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Edelsteine extends Ressource { 
	public Edelsteine() {
		super(RessourcenArt.Edelsteine,RessourcenArt.getBild(RessourcenArt.Edelsteine));
	}
}