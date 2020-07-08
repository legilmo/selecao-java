package br.com.indra.avaliacao.apirest.models.foundation.exception;

public class RoleNotAllowedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7758812713907712791L;

	private String label;

	public RoleNotAllowedException(String field) {
		this.label = field;
	}

	@Override
	public String getMessage() {
		return label;
	}
}
