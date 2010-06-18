package imobiliaria.exceptions;

/**
 * Excecao para, caso a Transacao nao Exista
 * @version IT02
 */
public class TransacaoNaoExistenteException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para Excecao de Transacao
	 */
	public TransacaoNaoExistenteException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public TransacaoNaoExistenteException(String mensagem) {
		super(mensagem);
	}
}
