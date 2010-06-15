package imobiliaria.exceptions;

/**
 * Excecao para, caso a medida de comprimento de um terreno seja invalido
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class MedidaComprimentoInvalidoException extends MedidaInvalidaException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor default para Excecao de Comprimento invalido de Terreno
	 */
	public MedidaComprimentoInvalidoException(){
	}
	
	/**
	 * Construtor responsavel por criar uma nova excessao, contendo uma mensagem
	 * 
	 * @param mensagem
	 *            Representa a mensagem de erro
	 */
	public MedidaComprimentoInvalidoException(String mensagem){
		super(mensagem);
	}
}
