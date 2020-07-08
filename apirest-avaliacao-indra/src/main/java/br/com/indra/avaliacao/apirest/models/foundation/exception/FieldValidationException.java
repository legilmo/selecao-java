package br.com.indra.avaliacao.apirest.models.foundation.exception;

public class FieldValidationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7757786369794650041L;

	private String fieldLabel;

	public FieldValidationException(String field) {
		this.fieldLabel = field;
	}

	@Override
	public String getMessage() {
		return fieldLabel;
	}
}
