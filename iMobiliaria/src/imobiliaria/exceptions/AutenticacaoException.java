package imobiliaria.exceptions;

/**
 * Excecao para, caso a Autenticacao seja invalida
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class AutenticacaoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para Excecao de Autenticacao
	 */
	public AutenticacaoException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public AutenticacaoException(String mensagem) {
		super(mensagem);
	}
}
