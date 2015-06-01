package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Uran extends Ressource {
	public Uran() {
		super(RessourcenArt.Uran,RessourcenArt.getBild(RessourcenArt.Uran));
	}
}