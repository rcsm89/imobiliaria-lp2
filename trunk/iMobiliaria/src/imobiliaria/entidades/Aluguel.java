package imobiliaria.entidades;

/**
 * Classe Aluguel que guarda informacoes sobre um Aluguel
 * 
 * @author Yuri Farias
 * @version IT 02
 */
public class Aluguel {
	private Cliente alugante;
	private Imovel imovelAlugado;

	/**
	 * Construtor da Classe
	 * 
	 * @param alugante
	 *            Cliente que alugou o Imovel
	 * @param imovelAlugado
	 *            Imovel que foi Alugado
	 */
	public Aluguel(Cliente alugante, Imovel imovelAlugado) {
		if (alugante == null || imovelAlugado == null)
			throw new IllegalArgumentException("Parametros invalidos");
		this.alugante = alugante;
		this.imovelAlugado = imovelAlugado;
	}

	/**
	 * Metodo acessador do Cliente do Aluguel
	 * 
	 * @return O Cliente do Aluguel
	 */
	public Cliente getAlugante() {
		return alugante;
	}

	/**
	 * Metodo acessador do Imovel Alugado
	 * 
	 * @return Imovel Alugado
	 */
	public Imovel getImovelAlugado() {
		return imovelAlugado;
	}

}
