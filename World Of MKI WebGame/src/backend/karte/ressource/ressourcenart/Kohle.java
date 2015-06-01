package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Kohle extends Ressource {
	public Kohle() {
		super(RessourcenArt.Kohle,RessourcenArt.getBild(RessourcenArt.Kohle));
	}
}