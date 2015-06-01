package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Obst extends Ressource {
	public Obst() {
		super(RessourcenArt.Obst,RessourcenArt.getBild(RessourcenArt.Obst));
	}
}