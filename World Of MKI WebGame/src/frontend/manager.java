package frontend;

import errorHandling.SpielerException;
import frontend.FrontendBean;

import java.io.Serializable;


public class manager implements Serializable {


	private static final long serialVersionUID = 1L;

	FrontendBean f;
	
	

	
	public manager(FrontendBean f) {
		
		this.f = f;
		
	}
	

	
	void s1Angemeldet(String nameS1) throws SpielerException {
		
		f.anmelden(nameS1);
		
	}
	
	void s2Angemeldet(String nameS2) throws SpielerException {
		
		f.anmelden(nameS2);
		
	}

	void s3Angemeldet(String nameS3) throws SpielerException {
		
		f.anmelden(nameS3);
		
	}
	
	void s4Angemeldet(String nameS4) throws SpielerException {
		
		f.anmelden(nameS4);
		
	}

	public void starteGamescreen(byte aktiverSpieler, String s1Name,
			int spielzug,  boolean s1erstellt) {
		
//		muss noch geschrieben werden
		
	}
	
	
	
	

	
	
	
	

	
		

}
