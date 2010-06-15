package imobiliaria.exceptions;

/**
 * Excecao para, caso uma data, seja invalida
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class DataInvalidaException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para a Excecao de Data Errada
	 */
	public DataInvalidaException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public DataInvalidaException(String mensagem) {
		super(mensagem);
	}
}
