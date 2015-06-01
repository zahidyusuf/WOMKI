package backend.karte.ressource.ressourcenart;

import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;

public class Wal extends Ressource {
	public Wal() {
		super(RessourcenArt.Wal,RessourcenArt.getBild(RessourcenArt.Wal));
	}
}