package imobiliaria.exceptions;

/**
 * Excecao para caso uma data de nascimento recebida esteja no futuro
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class DataNascimentoFuturoException extends DataInvalidaException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para a Excecao de Nascimento Futuro
	 */
	public DataNascimentoFuturoException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public DataNascimentoFuturoException(String mensagem) {
		super(mensagem);
	}
}
