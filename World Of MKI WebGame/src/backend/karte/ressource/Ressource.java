package backend.karte.ressource;

import java.awt.image.BufferedImage;

public class Ressource{
	private RessourcenArt art=null;
	private BufferedImage bild=null;
	
	public Ressource(RessourcenArt art,BufferedImage bild){
		this.art=art;
		this.bild=bild;
	}
	
	public void setArt(RessourcenArt art) {
		this.art = art;
	}

	public RessourcenArt getArt() {
		return art;
	}

	public void setBild(BufferedImage bild) {
		this.bild = bild;
	}

	public BufferedImage getBild() {
		return bild;
	}
	
	@Override
	public String toString(){
		return ""+this.getArt();
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Ressource)) return false;
		Ressource r=(Ressource)o;
		return r.getArt().equals(this.getArt());
	}
}
