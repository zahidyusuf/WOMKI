package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Eisen extends Ressource {
	public Eisen() { 
		super(RessourcenArt.Eisen,RessourcenArt.getBild(RessourcenArt.Eisen));
	}
}