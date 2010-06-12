package imobiliaria.exceptions;

/**
 * Exception para caso a Transacao nao Exista
 * @author Yuri
 * @version IT02
 */
public class TransacaoNaoExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	public TransacaoNaoExistenteException() {
	}

	public TransacaoNaoExistenteException(String arg0) {
		super(arg0);
	}
}
