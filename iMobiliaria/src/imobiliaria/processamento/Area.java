package imobiliaria.processamento;

/**
 * Classe Area que guarda informacoes e classificacoes de uma determinada Area
 * 
 * @version IT 1.0
 */
public class Area {

	private double comprimento;
	private double largura;
	private TipoArea classificacao;

	/**
	 * Construtor da Classe
	 * 
	 * @param comprimento
	 *            Comprimento
	 * @param largura
	 *            Largura
	 * @throws Exception
	 *             Lanca Excecao caso Comprimento ou Largura sejam negativas
	 */
	public Area(double comprimento, double largura) throws Exception {
		if (comprimento <= 0) {
			throw new Exception("Comprimento invalido");
		}

		if (largura <= 0) {
			throw new Exception("Largura invalida");
		}

		this.comprimento = comprimento;
		this.largura = largura;

		double area = comprimento * largura;

		/* Areas Menores ou iguais a 25m² */

		if (area <= 25) {
			this.classificacao = TipoArea.PEQUENA;

			/* Areas menores ou iguais a 100m² e mais de 25 */

		} else if (area <= 100) {
			this.classificacao = TipoArea.MEDIA;

			/* Areas maiores que 100m² */

		} else {
			this.classificacao = TipoArea.GRANDE;
		}

	}

	/**
	 * Metodo Acessador do Comprimento
	 * 
	 * @return Comprimento
	 */
	public double getComprimento() {
		return comprimento;
	}

	/**
	 * Metodo Modificador do Comprimento
	 * 
	 * @param comprimento
	 *            Novo Comprimento
	 */
	public void setComprimento(double comprimento) throws Exception {
		if (comprimento <= 0) {
			throw new Exception("Comprimento invalido");
		}
		this.comprimento = comprimento;
	}

	/**
	 * Metodo Acessador da Largura
	 * 
	 * @return Largura
	 */
	public double getLargura() {
		return largura;
	}

	/**
	 * Metodo Modificador da Largura
	 * @param largura
	 *            Nova Largura
	 */
	public void setLargura(double largura) {
		this.largura = largura;
	}

	/**
	 * Metodo Acessador da Classificacao
	 * @return Classificacao
	 */
	public TipoArea getClassificacao() {
		return classificacao;
	}

	/**
	 * Metodo Modificador da Classificacao
	 * @param classificacao
	 *            Nova Classificacao
	 */
	public void setClassificacao(TipoArea classificacao) {
		this.classificacao = classificacao;
	}
	
	/**
	 * toString contendo informacoes da Area
	 * (Formato Exemplo: 4|5|TipoArea.PEQUENA)
	 */
	
	@Override
	public String toString() {
		return comprimento + "|" + largura + "|" + classificacao;
	}

}
