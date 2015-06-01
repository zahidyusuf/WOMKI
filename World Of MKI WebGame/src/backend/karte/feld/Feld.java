package backend.karte.feld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import backend.Backend;
import backend.karte.ressource.Ressource;
import backend.karte.ressource.RessourcenArt;
import backend.spiel.einheit.Einheit;
import backend.spiel.stadt.Stadt;
import daten.d;

public abstract class Feld {
	private FeldArt fArt = null;
	private Backend ba;
	private int spielerstart = 0;
	private boolean istGewaehlt = false;
	private Ressource res = null;
	private Einheit ei;
	private Stadt st;
	private int x;
	private int y;
	private ArrayList<RessourcenArt> erlaubteRessourcen = new ArrayList<RessourcenArt>();
	private BufferedImage bild = null;
	private static BufferedImage bildGewahelt;
	static {
		try {
			bildGewahelt = ImageIO
					.read(new File("C://daten//felder//gewaehlt.png"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public Feld() {
	}

	public Feld(Backend ba, int posX, int posY, String art) {
		init(ba, posX, posY, art);
	}

	public Feld(Backend ba, int posX, int posY, FeldArt art) {
		init(ba, posX, posY, "" + art);
	}

	public void init(Backend ba, int posX, int posY, String fArt) {
		this.ba = ba;
		setPos(posX, posY);
		setArt(FeldArt.getFeldArt(fArt));
		setBild(FeldArt.getBild(fArt));
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public int[] getPos() {
		return new int[] { getPosX(), getPosY() };
	}

	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void setErlaubteRessourcen();

	public void addErlaubteRessource(RessourcenArt art) {
		if (erlaubteRessourcen.contains(art))
			return;
		erlaubteRessourcen.add(art);
	}

	public ArrayList<RessourcenArt> getErlaubteRessourcen() {
		return erlaubteRessourcen;
	}

	public Image getBild() {
		Image ausgabe;
		if (bild == null)
			return null;
		BufferedImage bildIcon = new BufferedImage(bild.getWidth(),
				bild.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bildIcon.getGraphics();
		g.drawImage(bild, 0, 0, null);

		if (st != null) {
			BufferedImage bildStadt = st.getBild();
			g.drawImage(bildStadt,
					bildIcon.getWidth() / 2 - bildStadt.getWidth() / 2,
					bildIcon.getHeight() / 2 - bildStadt.getWidth() / 2, null);
		} else {
			if (res != null) {
				BufferedImage bildRessource = res.getBild();
				g.drawImage(bildRessource, bildIcon.getWidth() / 2
						- bildRessource.getWidth() / 2, bildIcon.getHeight()
						/ 2 - bildRessource.getWidth() / 2, null);
			}
			if (ei != null) {
				BufferedImage bildEinheit = ei.getBild();
				g.drawImage(bildEinheit,
						bildIcon.getWidth() / 2 - bildEinheit.getWidth() / 2,
						bildIcon.getHeight() / 2 - bildEinheit.getWidth() / 2,
						null);
			}
		}

		if (istGewaehlt)
			g.drawImage(bildGewahelt,
					bildIcon.getWidth() / 2 - bildGewahelt.getWidth() / 2,
					bildIcon.getHeight() / 2 - bildGewahelt.getWidth() / 2,
					null);

		if (ba.getSpiel() == null) {
			if (spielerstart != 0) {
				g.setFont(new Font("Arial", Font.BOLD, 24));
				g.setColor(new Color(0, 0, 0));
				g.drawString("" + spielerstart, 5, 24);
			}
		}

		if (ba.getZoomfaktor() == 100)
			ausgabe = bildIcon;
		else
			ausgabe = bildIcon.getScaledInstance(
					bild.getWidth() * ba.getZoomfaktor() / 100,
					bild.getHeight() * ba.getZoomfaktor() / 100,
					Image.SCALE_FAST);
		return ausgabe;
	}

	public void setBild(BufferedImage bild) {
		this.bild = bild;
	}

	public void setRessource(Ressource res) {
		if ((res == null) || (erlaubteRessourcen.contains(res.getArt()))) {
			this.res = res;
		}
	}

	public Ressource getRessource() {
		return res;
	}

	protected void setArt(FeldArt art) {
		this.fArt = art;
	}

	public FeldArt getArt() {
		return fArt;
	}

	public void setSpielerstart(int spielerstart) {
		this.spielerstart = spielerstart;
	}

	public int getSpielerstart() {
		return spielerstart;
	}

	public void setEinheit(Einheit ei) {
		this.ei = ei;
	}

	public Einheit getEinheit() {
		return ei;
	}

	public void setStadt(Stadt st) {
		this.st = st;
	}

	public Stadt getStadt() {
		return st;
	}

	public boolean istGewaehlt() {
		return istGewaehlt;
	}

	public void waehlen() {
		this.istGewaehlt = true;
		// int[] pos={posX,posY};
		// b.getGui().zeichneFeld(pos);
	}

	public void abwaehlen() {
		this.istGewaehlt = false;
		// int[] pos={posX,posY};
		// b.getGui().zeichneFeld(pos);
	}

	public void regeneration(Feld fNeu) {
		int maxHP;

		// abprüfung ob krieger oder Siedler sich auf einem Feld befindet
		if (fNeu.getEinheit() != null) {
			Einheit zuregenerierendeE = fNeu.getEinheit();
			// String wird zu int geparst
			int bewegtGeaendert = d.toInt(zuregenerierendeE.getDatenAktuell()
					.get("bewegung"));
			// wenn einheit sich bewegt keine regenartion möglich
			if (bewegtGeaendert == 0) {
				
				return;
			} else {

				int xEinheit = fNeu.getPosX();
				int yEinheit = fNeu.getPosY();

				String einheit = fNeu.getEinheit().getArt();
				// unterscheidung zwischen Krieger und Siedler
				if (einheit.equals("Siedler")) {
					maxHP = 10;
					
				} else {
					maxHP = 20;
				
				}
				

				int aktHP = d.toInt(fNeu.getEinheit().getDatenAktuell()
						.get("leben"));
			

				if (maxHP == 10) {
					if (aktHP > 0 && aktHP <= maxHP) {
						if (zuregenerierendeE.inStadtpruefen() == true) {
							if (aktHP == maxHP) {
								return;
							} else if (aktHP == maxHP - 1) {
								aktHP += 1;
								fNeu.getEinheit().getDatenAktuell()
										.add("leben", "" + (aktHP));
							} else {

								aktHP += 2;
								fNeu.getEinheit().getDatenAktuell()
										.add("leben", "" + (aktHP));
							}
						} else {
							if (aktHP == maxHP) {
								return;
							} else {
								aktHP += 1;
								fNeu.getEinheit().getDatenAktuell()
										.add("leben", "" + (aktHP));
							}
						}
					} else if (maxHP == 20) {
						if (aktHP > 0 && maxHP <= maxHP) {
							if (zuregenerierendeE.inStadtpruefen() == true) {
								if (aktHP == maxHP) {
									return;
								} else if (aktHP == maxHP - 1) {
									aktHP += 1;
									fNeu.getEinheit().getDatenAktuell()
											.add("leben", "" + (aktHP));
								} else {

									aktHP += 2;
									fNeu.getEinheit().getDatenAktuell()
											.add("leben", "" + (aktHP));
								}
							} else {
								if (aktHP == maxHP) {
									return;
								} else {
									aktHP += 1;
									fNeu.getEinheit().getDatenAktuell()
											.add("leben", "" + (aktHP));
								}
							}
						}
					} else {
						return;
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		String s = "";
		s += "Spielfeld " + getPosX() + "/" + getPosY() + " ist " + getArt();
		if (res != null)
			s += " mit Ressource " + res.getArt();
		if (st != null)
			s += " mit Stadt " + st.getName() + " von "
					+ st.getSpieler().getName() + " aus "
					+ st.getSpieler().getNation();
		else {
			if (ei != null)
				s += " mit Einheit " + ei.getArt() + " von "
						+ ei.getSpieler().getName() + " aus "
						+ ei.getSpieler().getNation();
		}
		return s;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Feld))
			return false;
		Feld f = (Feld) o;
		return ((f.getPosX() == this.getPosX()) && (f.getPosY() == this
				.getPosY()));
	}






public Image getBildGamescreen() {
	Image ausgabe = null;
	if (bild == null)
		return null;
	BufferedImage bildIcon = new BufferedImage(bild.getWidth(),
			bild.getHeight(), BufferedImage.TYPE_INT_ARGB);
	Graphics g = bildIcon.getGraphics();
	g.drawImage(bild, 0, 0, null);

	if (st != null) {
		BufferedImage bildStadt = st.getBild();
		g.drawImage(bildStadt,
				bildIcon.getWidth() / 2 - bildStadt.getWidth() / 2,
				bildIcon.getHeight() / 2 - bildStadt.getWidth() / 2, null);
	} else {
		if (res != null) {
			BufferedImage bildRessource = res.getBild();
			g.drawImage(bildRessource, bildIcon.getWidth() / 2
					- bildRessource.getWidth() / 2, bildIcon.getHeight()
					/ 2 - bildRessource.getWidth() / 2, null);
		}
		if (ei != null) {
			BufferedImage bildEinheit = ei.getBild();
			g.drawImage(bildEinheit,
					bildIcon.getWidth() / 2 - bildEinheit.getWidth() / 2,
					bildIcon.getHeight() / 2 - bildEinheit.getWidth() / 2,
					null);
		}
	}

	if (istGewaehlt)
		g.drawImage(bildGewahelt,
				bildIcon.getWidth() / 2 - bildGewahelt.getWidth() / 2,
				bildIcon.getHeight() / 2 - bildGewahelt.getWidth() / 2,
				null);

		return ausgabe;
}

}
