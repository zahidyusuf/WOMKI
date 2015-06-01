package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Seide extends Ressource {
	public Seide() {
		super(RessourcenArt.Seide,RessourcenArt.getBild(RessourcenArt.Seide));
	}
}