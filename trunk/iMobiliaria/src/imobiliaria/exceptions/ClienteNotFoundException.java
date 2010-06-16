package imobiliaria.exceptions;

/**
 * Exception para caso o Cliente nao seja encontrado
 * @author Yuri
 */
public class ClienteNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param string
	 *            Representa a mensagem de erro
	 */

	public ClienteNotFoundException(String string) {
		super(string);
	}

}
