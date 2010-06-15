package imobiliaria.exceptions;

/**
 * Excecao para, caso o Login seja invalido
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class LoginInvalidoException extends AutenticacaoException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para Excecao de Login
	 */
	public LoginInvalidoException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public LoginInvalidoException(String mensagem) {
		super(mensagem);
	}
}
