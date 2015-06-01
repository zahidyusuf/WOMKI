package daten;

import java.util.TreeMap;

public abstract class d {
	protected TreeMap<String,String> daten=new TreeMap<String,String>();

	public static int toInt(String s){
		return Integer.parseInt(s);
	}

	public d(){
	}
	
	public d(String csv){
		String[] datenCsv=csv.split(";");
		if ((datenCsv==null)||(datenCsv.length==0)) return;
		for(String datensatz:datenCsv){
			String[] datenwert=datensatz.split("=");
			if ((datenwert!=null)&&(datenwert.length==2))
				add(datenwert[0],datenwert[1]);
			else if ((datenwert!=null)&&(datenwert.length==1))
				add(datenwert[0],null);				
		}
	}

	public d(TreeMap<String,String> daten){
		this.daten=daten;
	}
	
	public void add(String key,String value){
		daten.put(key, value);
	}

	public String get(String key){
		return daten.get(key);
	}
	
	public void set(String key,String value){
		daten.put(key, value);
	}

	public void dec(String key){
		int x=d.toInt(get(key));
		x--;		
		daten.put(key,""+x);
	}

	public void inc(String key){
		int x=d.toInt(get(key));
		x++;		
		daten.put(key,""+x);
	}

	public String getCsv(){
		if (daten.isEmpty()) return "";
		String ret="";
		for(String x:daten.keySet()){
			ret+=x+"="+daten.get(x)+";";
		}
		return ret.substring(0,ret.length()-1);
	}
	
	public TreeMap<String,String> cloneDaten(){
		TreeMap<String,String> klon=new TreeMap<String,String>();
		for(String key:daten.keySet()){
			klon.put(key, daten.get(key));
		}
		return klon;
	}
	
	@Override
	public String toString(){
		return getCsv();
	}
}
