package imobiliaria.exceptions;

/**
 * Excecao para, caso a medida de largura de um terreno seja invalido
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class MedidaLarguraInvalidaException extends MedidaInvalidaException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor default para Excecao de Largura invalida de Terreno
	 */
	public MedidaLarguraInvalidaException(){
	}
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public MedidaLarguraInvalidaException(String mensagem){
		super(mensagem);
	}
}
