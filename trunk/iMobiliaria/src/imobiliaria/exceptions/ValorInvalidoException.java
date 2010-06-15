package imobiliaria.exceptions;

/**
 * Excecao para caso algum valor digitado seja invalido
 * 
 * @author Yuri
 * @version versao 02
 */
public class ValorInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor padrao da Excecao
	 */
	public ValorInvalidoException() {
	}

	/**
	 * Construtor com parametro de mensagem para enviar informacoes do Erro
	 * 
	 * @param mensagem
	 *            Mensagem com informacao do Erro
	 */
	public ValorInvalidoException(String mensagem) {
		super(mensagem);
	}

}
