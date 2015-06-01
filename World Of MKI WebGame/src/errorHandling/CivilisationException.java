package errorHandling;

public class CivilisationException extends Exception {	
	
	private static final long serialVersionUID = 1L;

	public CivilisationException(){
		super();
		
	}

	public CivilisationException(String message){
		
		super(message);
	
	}


}
