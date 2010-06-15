package imobiliaria.exceptions;

/**
 * Excecao para, caso o Registro de um Imovel nao Exista
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class RegistroInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor default para Excecao de Registro de Imovel Invalido
	 */
	public RegistroInvalidoException() {
	}
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public RegistroInvalidoException(String mensagem) {
		super(mensagem);
	}
}