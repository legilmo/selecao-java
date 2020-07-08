package br.com.indra.avaliacao.apirest.models.foundation.exception;

public class LogicValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3047884899909974888L;
	private String messageLabel;

	public LogicValidationException(String messageLabel) {
		this.messageLabel = messageLabel;
	}

	@Override
	public String getMessage() {
		return messageLabel;
	}
}
