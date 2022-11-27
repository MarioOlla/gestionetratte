package it.prova.gestionetratte.web.api.exception;

public class StillHasLinkedTratteException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public StillHasLinkedTratteException(String msg) {
		super(msg);
	}
}
