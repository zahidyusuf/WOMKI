package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Tabak extends Ressource {
	public Tabak() {
		super(RessourcenArt.Tabak,RessourcenArt.getBild(RessourcenArt.Tabak));
	}
}