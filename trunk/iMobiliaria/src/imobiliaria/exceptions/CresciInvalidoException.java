package imobiliaria.exceptions;

/**
 * Excecao para, caso o Cresci do Funcionario nao Exista
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class CresciInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para Excecao de Cresci Invalido
	 */
	public CresciInvalidoException() {
	}
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public CresciInvalidoException(String mensagem) {
		super(mensagem);
	}
}
