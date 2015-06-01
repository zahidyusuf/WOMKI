package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Elfenbein extends Ressource {
	public Elfenbein() { 
		super(RessourcenArt.Elfenbein,RessourcenArt.getBild(RessourcenArt.Elfenbein));
	}
}