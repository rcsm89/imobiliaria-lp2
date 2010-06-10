package imobiliaria.controladores;

import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Imovel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que ira ser o Controlador de Alugueis do Sistema
 * @author Yuri Farias
 * @version IT 02
 */
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
	public boolean adicionaAluguel(Cliente alugante, Imovel imovelAlugado) {
		if (alugante == null || imovelAlugado == null)
			throw new IllegalArgumentException("Parametros invalidos");

		Aluguel aluguel = new Aluguel(alugante, imovelAlugado);

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
	public double getValorTotalDeAlugueis() {

		Iterator<Aluguel> itAluguel = alugueis.iterator();
		
		double valorTotal = 0;
		
		while (itAluguel.hasNext()) {
			valorTotal += itAluguel.next().getImovelAlugado().getValor();
		}
		
		return valorTotal;
	}

}
