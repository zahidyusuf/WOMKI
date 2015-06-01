package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Oase extends Ressource {
	public Oase() {
		super(RessourcenArt.Oase,RessourcenArt.getBild(RessourcenArt.Oase));
	}
}