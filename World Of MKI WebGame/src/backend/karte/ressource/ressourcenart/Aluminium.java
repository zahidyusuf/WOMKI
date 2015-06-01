package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Aluminium extends Ressource { 
	public Aluminium() {
		super(RessourcenArt.Aluminium,RessourcenArt.getBild(RessourcenArt.Aluminium));
	}
}