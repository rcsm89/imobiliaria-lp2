package imobiliaria.exceptions;

/**
 * Excecao para, caso a Senha seja invalida
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class SenhaInvalidaException extends AutenticacaoException{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para Excecao de Senha Errada
	 */
	public SenhaInvalidaException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public SenhaInvalidaException(String mensagem) {
		super(mensagem);
	}
}