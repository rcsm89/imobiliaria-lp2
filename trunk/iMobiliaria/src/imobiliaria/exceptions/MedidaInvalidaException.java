package imobiliaria.exceptions;

/**
 * Excecao para, caso o Registro de um Imovel nao Exista
 * @version IT02
 */
public class MedidaInvalidaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor default para Excecao de Medida invalida de Terreno
	 */
	public MedidaInvalidaException(){
	}
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public MedidaInvalidaException(String mensagem){
		super(mensagem);
	}
}
