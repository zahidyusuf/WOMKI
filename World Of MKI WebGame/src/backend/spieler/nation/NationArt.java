package backend.spieler.nation;

public enum NationArt {
	Deutschland, England, Frankreich, Russland, Amerika, China, Tuerkei;
	
	private static String packageKlasse="backend.spieler.nation.nationart.";
	
	public static NationArt getNationArt(String s){
		for(NationArt na:NationArt.values()){
			if ((""+na).equals(s)) return na;
		}
		return null;
	}
	
	public static Nation getNation(NationArt art) {
		return getNation(""+art);
	}
	
	public static Nation getNation(String art){
    try {
  		@SuppressWarnings("unchecked")
  		Class<Nation> c=(Class<Nation>)Class.forName(packageKlasse+art);
  		Nation n=c.newInstance();	// Reflection?
  		for(int i=0;i<NationArt.values().length;i++){
  			if (art.equals(""+NationArt.values()[i])){
  				n.setArt(NationArt.values()[i]);
  				break;
  			}
  		}
			return n;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
