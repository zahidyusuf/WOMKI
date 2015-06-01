package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Gewuertz extends Ressource {
	public Gewuertz() {
		super(RessourcenArt.Gewuertz,RessourcenArt.getBild(RessourcenArt.Gewuertz));
	}
}