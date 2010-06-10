package imobiliaria.controladores;

import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ControladorAlugueis implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ControladorAlugueis controladorAlugueisUnico = new ControladorAlugueis();

	private ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();

	private ControladorAlugueis() {
	}

	/**
	 * Metodo acessador do Controlador de Alugueis Unico
	 * 
	 * @return Controlador de Alugueis Unico
	 */
	public static ControladorAlugueis getInstance() {
		return controladorAlugueisUnico;
	}

	/* Metodos de Aluguel */

	/**
	 * Metodo que adiciona um Aluguel no Controlador
	 * 
	 * @param alugante
	 *            Cliente que alugou o Imovel
	 * @param vendedor
	 *            Vendedor do Imovel vendido
	 * @param imovelAlugado
	 *            Imovel que foi Alugado
	 * @return
	 */
	public boolean adicionaAluguel(Cliente alugante, Funcionario vendedor,
			Imovel imovelAlugado) {
		if (alugante == null || vendedor == null || imovelAlugado == null)
			throw new IllegalArgumentException("Parametros invalidos");

		Aluguel aluguel = new Aluguel(alugante, vendedor, imovelAlugado);

		return alugueis.add(aluguel);
	}

	/**
	 * Metodo que remove um Aluguel do Controlador de Alugueis
	 * 
	 * @param imovelDoAluguel
	 *            Imovel do Aluguel a ser removido
	 * @return True - Caso o imovel tenha sido removido <br>
	 *         False - Caso o imovel nao seja encontrado
	 * @throws Exception
	 *             Lanca excecao caso o imovel nao seja encontrado na lista de
	 *             Alugueis de Cliente
	 */
	public boolean removeAluguel(Imovel imovelDoAluguel) throws Exception {

		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado() == imovelDoAluguel) {

				a.getAlugante().getAlugueis().removeImovel(
						String
								.valueOf(a.getImovelAlugado()
										.getRegistroImovel()));

				return alugueis.remove(a);
			}
		}
		return false;
	}

	/**
	 * Metodo acessador do Aluguel de um Dado Imovel Alugado
	 * 
	 * @param imovelAlugado
	 *            Imovel Alugado
	 * @return Aluguel
	 */
	public Aluguel getAluguel(Imovel imovelAlugado) {
		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado().equals(imovelAlugado)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Metodo que adiciona ao caixa o valor de todos os alugueis
	 */
	public void adquireAlugueis() {

		Iterator<Aluguel> itAluguel = alugueis.iterator();

		ControladorTransacoes controladorTransacoes = ControladorTransacoes
				.getInstance();

		while (itAluguel.hasNext()) {

			Aluguel aluguel = itAluguel.next();

			controladorTransacoes.adicionaAoCaixa(aluguel.getImovelAlugado()
					.getValor());
		}
	}

}
