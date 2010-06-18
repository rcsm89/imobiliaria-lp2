package imobiliaria.exceptions;

/**
 * Exception para caso o Pedido nao tenha sido encontrado
 * @version IT02
 */
public class PedidoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param string
	 *            Representa a mensagem de erro
	 */

	public PedidoNotFoundException(String string) {
		super(string);
	}

}
