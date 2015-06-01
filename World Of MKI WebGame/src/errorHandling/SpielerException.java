package errorHandling;

public class SpielerException extends CivilisationException{
private static final long serialVersionUID = 1L;
	
	public SpielerException() {
		super();
	}
	
	public SpielerException(String message){
		super(message);
	}	
}
