package imobiliaria.exceptions;

/**
 * Exception para caso o pagamento ja tenha sido efetuado
 * @version IT02
 */ 
public class PagamentoJaEfetuadoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param string
	 *            Representa a mensagem de erro
	 */
	public PagamentoJaEfetuadoException(String string) {
		super(string);
	}

}
