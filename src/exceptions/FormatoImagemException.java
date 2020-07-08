package exceptions;

public class FormatoImagemException extends Exception {

	private static final long serialVersionUID = 1L;

	public FormatoImagemException() {
		super("O Arquivo nao e no formato PGM.");
	}

}
