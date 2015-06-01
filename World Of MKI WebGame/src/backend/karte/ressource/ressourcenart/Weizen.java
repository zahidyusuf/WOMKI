package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Weizen extends Ressource {
	public Weizen() {
		super(RessourcenArt.Weizen,RessourcenArt.getBild(RessourcenArt.Weizen));
	}
}