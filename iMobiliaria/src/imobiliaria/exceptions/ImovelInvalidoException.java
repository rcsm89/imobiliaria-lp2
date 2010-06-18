package imobiliaria.exceptions;

/**
 * Excecao para caso o Imovel seja Invalido
 * @version IT02
 */
public class ImovelInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param string
	 *            Representa a mensagem de erro
	 */

	public ImovelInvalidoException(String string) {
		super(string);
	}

}
