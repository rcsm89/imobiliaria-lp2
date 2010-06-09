package imobiliaria.entidades;

public class Aluguel {
	private Cliente alugante;
	private Funcionario vendedor;
	private Imovel imovelAlugado;

	public Aluguel(Cliente alugante, Funcionario vendedor, Imovel imovelAlugado) {
		if (alugante == null || vendedor == null || imovelAlugado == null)
			throw new IllegalArgumentException("Parametros invalidos");
		this.alugante = alugante;
		this.vendedor = vendedor;
		this.imovelAlugado = imovelAlugado;
	}

	/**
	 * Metodo acessador do Cliente do Aluguel
	 * @return O Cliente do Aluguel
	 */
	public Cliente getAlugante() {
		return alugante;
	}

	/**
	 * Metodo acessador do Vendedor do Aluguel
	 * @return O Funcionario que vendeu o Aluguel
	 */
	public Funcionario getVendedor() {
		return vendedor;
	}

	/**
	 * Metodo acessador do Imovel Alugado
	 * @return Imovel Alugado
	 */
	public Imovel getImovelAlugado() {
		return imovelAlugado;
	}

}
