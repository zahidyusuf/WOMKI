package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Wild extends Ressource {
	public Wild() {
		super(RessourcenArt.Wild,RessourcenArt.getBild(RessourcenArt.Wild));
	}
}