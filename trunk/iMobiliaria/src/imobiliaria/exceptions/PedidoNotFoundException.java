package imobiliaria.exceptions;

/**
 * Exception para caso o Pedido nao tenha sido encontrado
 * @author Yuri
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
