package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Salpeter extends Ressource {
	public Salpeter() {
		super(RessourcenArt.Salpeter,RessourcenArt.getBild(RessourcenArt.Salpeter));
	}
}