package backend.karte;


import backend.Backend;
import backend.BackendException;
import backend.karte.feld.Feld;
import backend.karte.feld.FeldArt;
import backend.karte.ressource.RessourcenArt;

public class Karte {
	private Backend ba;
	private int id;
	private KartenArt art;
	private Feld[][] felder;
	private int groesseX;
	private int groesseY;
	

	// neue Karte einer gewissen Groesse, deren Felder noch nicht besetzt sind,
	// weil diese z.B. nach dem Konstruktor aus einer Datei geladen werden
	public Karte(Backend ba, int id, int groesseX, int groesseY, String kArt) {
		this.ba = ba;
		this.id = id;
		this.groesseX = groesseX;
		this.groesseY = groesseY;
		this.art = KartenArt.getKartenArt(kArt);
		felder = new Feld[groesseX + 1][groesseY + 1];
	}
	


	// neue Karte, die nur aus einer Standard-Feldart besteht, z.B. Wald
	public Karte(Backend ba, int id, int groesseX, int groesseY, String kArt,String fArt) {
		this(ba, id, groesseX, groesseY, kArt);
		for (int i = 1; i <= groesseX; i++) {
			for (int j = 1; j <= groesseY; j++) {
				setFeld(i, j, fArt, null, 0);
			}
		}
	}

	// Setzen eines Feldes aus dem Karteneditor heraus
	public void setFeld(int x, int y, Feld f) {
		felder[x][y] = f;
	}

	// Setzen eines Feldes durch Laden einer Datei oder bei einer neuen Karte im
	// Editor
	public void setFeld(int x, int y, String fArt, String res, int spielerstart) {
		Feld f = FeldArt.getFeld(fArt);
		f.init(ba, x, y, fArt);
		f.setRessource(RessourcenArt.getRessource(res));
		f.setSpielerstart(spielerstart);
		setFeld(x, y, f);
	}

	public int getGroesseX() {
		return groesseX;
	}

	public int getGroesseY() {
		return groesseY;
	}

	public void setRessource(String res, int x, int y) {
		felder[x][y].setRessource(RessourcenArt.getRessource(res));
	}

	public void delRessource(int x, int y) {
		felder[x][y].setRessource(null);
	}

	public void setSpielerstart(int spielernummer, int x, int y) {
		if ((felder[x][y].getArt().equals(FeldArt.Meer))
				|| (felder[x][y].getArt().equals(FeldArt.Kueste)))
			throw new BackendException(
					"Ein Spieler kann leider nicht auf dem Gelaende '"
							+ felder[x][y].getArt() + "' starten!");
		if (spielernummer != 0) {
			int[] posAlt = getStartposition(spielernummer);
			if (posAlt != null) {
				Feld f = felder[posAlt[0]][posAlt[1]];
				f.setSpielerstart(0);
				
		}
		felder[x][y].setSpielerstart(spielernummer);}
		
	}

	public int[] getStartposition(int spielernummer) {
		for (int i = 1; i <= groesseX; i++) {
			for (int j = 1; j <= groesseY; j++) {
				Feld f = felder[i][j];
				if (f.getSpielerstart() == spielernummer)
					return new int[] { i, j };
			}
		}
		return null;
	}

	public void waehleFeld(int[] pos) {
		felderAbwaehlen();
		felder[pos[0]][pos[1]].waehlen();
		
	}

	private void felderAbwaehlen() {
		for (int i = 1; i <= groesseX; i++) {
			for (int j = 1; j <= groesseY; j++) {
				Feld f = felder[i][j];
				if (f.istGewaehlt())
					f.abwaehlen();
				
			}
		}
	}

	public int getId() {
		return id;
	}

	public KartenArt getArt() {
		return art;
	}

	public Feld[][] getFelder() {
		return felder;
	}

	public Feld getFeld(int[] pos) {
		return getFeld(pos[0], pos[1]);
	}

	public Feld getFeld(int x, int y) {
		return felder[x][y];
	}

	public void setFeldGewaehlt(int[] pos) {
		if (pos == null)
			setFeldGewaehlt(0, 0);
		else
			setFeldGewaehlt(pos[0], pos[1]);
	}

	public void setFeldGewaehlt(int x, int y) {
		for (int i = 1; i <= groesseX; i++) {
			for (int j = 1; j <= groesseY; j++) {
				if (felder[i][j].istGewaehlt()) {
					felder[i][j].abwaehlen();
				}}}}
			
		

	public Feld getFeldGewaehlt() {
		for (int i = 1; i <= groesseX; i++) {
			for (int j = 1; j <= groesseY; j++) {
				if (felder[i][j].istGewaehlt())
					return felder[i][j];
			}
		}
		return null;
	}

	

	

	public void regenerationEinheitAufKarte() {
		for (int i = 1; i <= getGroesseX(); i++) {
			for (int j = 1; j <= getGroesseY(); j++) {
				Feld fAkt = felder[i][j];
				felder[i][j].regeneration(fAkt);
			}
		}
	}
}
