package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Zucker extends Ressource {
	public Zucker() {
		super(RessourcenArt.Zucker,RessourcenArt.getBild(RessourcenArt.Zucker));
	}
}