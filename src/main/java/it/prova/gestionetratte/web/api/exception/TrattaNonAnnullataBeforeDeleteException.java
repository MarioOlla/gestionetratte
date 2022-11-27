package it.prova.gestionetratte.web.api.exception;

public class TrattaNonAnnullataBeforeDeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
		
	public TrattaNonAnnullataBeforeDeleteException(String msg) {
		super(msg);
	}
}
