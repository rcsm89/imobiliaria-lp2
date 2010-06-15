package imobiliaria.exceptions;

/**
 * Excecao para caso uma data de nascimento recebida esteja no futuro
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class DataNascimentoDeMenorException extends DataInvalidaException {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para a Excecao da Data de Nascimento conter menos de 
	 * 18 anos
	 */
	public DataNascimentoDeMenorException() {
	}

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public DataNascimentoDeMenorException(String mensagem) {
		super(mensagem);
	}
}
