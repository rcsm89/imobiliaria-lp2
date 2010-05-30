package imobiliaria.processamento;

/**
 * Classe Area que guarda informacoes e classificacoes de uma determinada Area
 * 
 * @version IT 1.1
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
		String mensagemDeErro = "";
		if (comprimento <= 0)
			mensagemDeErro += "Comprimento invalido\n";

		if (largura <= 0)
			mensagemDeErro += "Largura invalida\n";
		
		if (mensagemDeErro.length() != 0)
			throw new Exception(mensagemDeErro);

		this.comprimento = comprimento;
		this.largura = largura;

		atualizaClassificacao();

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
		atualizaClassificacao();
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
	public void setLargura(double largura) throws Exception {
		if (largura <= 0) {
			throw new Exception("Largura invalida");
		}
		this.largura = largura;
		atualizaClassificacao();
	}

	/**
	 * Metodo Acessador da Classificacao
	 * @return Classificacao
	 */
	public TipoArea getClassificacao() {
		return classificacao;
	}

	/**
	 * toString contendo informacoes da Area
	 * (Formato Exemplo: 4.0|5.0|PEQUENA)
	 */
	
	@Override
	public String toString() {
		return comprimento + "x" + largura + " (" + classificacao + ")";
	}
	
	/**
	 * equals da Classe<br>
	 * Duas areas sao iguais se possuirem o mesmo comprimento e largura
	 */
	
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Area)) {
			return false;
		}
		Area outraArea = (Area) objeto;
		if (outraArea.getComprimento() == comprimento && outraArea.getLargura() == largura) {
			return true;
		}
		return false;
	}
	
	
	
	
	
	/* Atualizador de Classificacao */
	
	private void atualizaClassificacao() {
		double area = largura * comprimento;
		
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

}
