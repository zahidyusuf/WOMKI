package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Pferde extends Ressource {
	public Pferde() {
		super(RessourcenArt.Pferde,RessourcenArt.getBild(RessourcenArt.Pferde));
	}
}