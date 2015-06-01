package backend.spieler.nation;

public abstract class Nation{
	private NationArt nArt=null;
	
	public Nation(){
	}

	public void setArt(NationArt nArt) {
		this.nArt=nArt;
	}

	public NationArt getNationArt() {
		return nArt;
	}
	
	@Override
	public String toString(){
		return ""+nArt;
	}
}
