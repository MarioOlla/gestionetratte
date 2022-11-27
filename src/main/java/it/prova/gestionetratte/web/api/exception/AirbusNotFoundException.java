package it.prova.gestionetratte.web.api.exception;

public class AirbusNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public AirbusNotFoundException(String msg) {
		super(msg);
	}
}
