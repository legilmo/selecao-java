package br.com.indra.avaliacao.apirest.models.foundation.exception;

public class DuplicatedAssociationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 355400632226307709L;
	private String fieldLabel;

	public DuplicatedAssociationException(String field) {
		this.fieldLabel = field;
	}

	@Override
	public String getMessage() {
		return fieldLabel;
	}
}
