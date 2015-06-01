package backend;

public class BackendException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public BackendException(String meldung){
		super(meldung);
	}
}
