package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Gold extends Ressource {
	public Gold() {
		super(RessourcenArt.Gold,RessourcenArt.getBild(RessourcenArt.Gold));
	}
}