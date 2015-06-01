package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Rinder extends Ressource {
	public Rinder() {
		super(RessourcenArt.Rinder,RessourcenArt.getBild(RessourcenArt.Rinder));
	}
}