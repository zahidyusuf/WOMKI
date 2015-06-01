package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Pelz extends Ressource {
	public Pelz() {
		super(RessourcenArt.Pelz,RessourcenArt.getBild(RessourcenArt.Pelz));
	}
}