package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Oel extends Ressource {
	public Oel() {
		super(RessourcenArt.Oel,RessourcenArt.getBild(RessourcenArt.Oel));
	}
}