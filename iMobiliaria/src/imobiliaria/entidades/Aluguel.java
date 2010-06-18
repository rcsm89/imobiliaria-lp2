package imobiliaria.entidades;

/**
 * Classe Aluguel que guarda informacoes sobre um Aluguel
 * 
 * @version IT02
 */
public class Aluguel extends Pedido {

	/**
	 * Construtor da Classe
	 * 
	 * @param alugante
	 *            Cliente que alugou o Imovel
	 * @param imovelAlugado
	 *            Imovel que foi Alugado
	 */
	public Aluguel(Cliente alugante, Imovel imovelAlugado) {
		super(imovelAlugado, alugante);
	}

	/**
	 * Metodo que exibe informacoes do Aluguel formatadas
	 * 
	 * @return Informacoes do Aluguel formatada
	 */
	@Override
	public String exibeInformacao() {
		return "Informacoes do Aluguel de " + getCliente().getNome() + "\n"
				+ super.exibeInformacao();
	}

	public String toString() {
		return "Alugante: " + getCliente().getNome() + " ("
				+ getCliente().getCpf() + ") Imovel: " + getImovel().getNome()
				+ " (" + getImovel().getRegistroImovel() + ")";
	}
}
