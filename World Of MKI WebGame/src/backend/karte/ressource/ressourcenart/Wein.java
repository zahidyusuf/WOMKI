package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Wein extends Ressource {
	public Wein() {
		super(RessourcenArt.Wein,RessourcenArt.getBild(RessourcenArt.Wein));
	}
}