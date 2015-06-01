package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Weihrauch extends Ressource {
	public Weihrauch() {
		super(RessourcenArt.Weihrauch,RessourcenArt.getBild(RessourcenArt.Weihrauch));
	}
}