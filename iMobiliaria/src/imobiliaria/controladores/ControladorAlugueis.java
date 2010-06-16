package imobiliaria.controladores;

import imobiliara.auxiliar.EstadoImovel;
import imobiliara.auxiliar.TipoContratual;
import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Imovel;
import imobiliaria.exceptions.ClienteNotFoundException;
import imobiliaria.exceptions.ImovelInvalidoException;
import imobiliaria.exceptions.ImovelNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe que ira ser o Controlador de Alugueis do Sistema
 * 
 * @author Yuri Farias
 * @version version 02
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
	 * Metodo que modifica a instancia unica do Controlador de Alugueis
	 * 
	 * @param controlador
	 *            Nova Instancia para o Controlador
	 */
	public static void setInstance(ControladorAlugueis controlador) {
		if (controlador == null) {
			throw new IllegalArgumentException(
					"Controlador de Alugueis invalido");
		}
		controladorAlugueisUnico = controlador;
	}

	/**
	 * Metodo que adiciona um Aluguel no Controlador
	 * 
	 * @param cpfCliente
	 *            Cpf do Cliente que alugou o Imovel
	 * @param registroDoImovel
	 *            Registro do Imovel que foi Alugado
	 * @return True - caso ele tenha sido adicionado com sucesso <br>
	 *         False - caso contrario
	 * @throws Exception 
	 */
	public boolean adicionaAluguel(String cpfCliente, String registroDoImovel) throws Exception {

		Cliente alugante = ControladorCliente.getInstance().getCliente(
				cpfCliente);
		Imovel imovelAlugado = ControladorImovel.getInstance().getImovel(
				registroDoImovel);

		if (alugante == null )
			throw new ClienteNotFoundException("Cliente invalido");
		
		if ( imovelAlugado == null )
			throw new ImovelNotFoundException("Imovel nao encontrado");
		
		if ( imovelAlugado.getEstadoDoImovel() != EstadoImovel.PEDIDO
				|| imovelAlugado.getTipoContratual() != TipoContratual.ALUGUEL) {
			throw new ImovelInvalidoException("Imovel invalido");
		}

		Aluguel aluguel = new Aluguel(alugante, imovelAlugado);
		
		imovelAlugado.alugado();

		return alugueis.add(aluguel);
	}

	/**
	 * Metodo que remove um Aluguel do Controlador de Alugueis
	 * 
	 * @param registroDoImovel
	 *            Registro Imovel do Aluguel a ser removido
	 * @return True - Caso o imovel tenha sido removido <br>
	 *         False - Caso o imovel nao seja encontrado
	 */
	public boolean removeAluguel(String registroDoImovel) {

		int registroImovel;
		try {
			registroImovel = Integer.parseInt(registroDoImovel);
		} catch (Exception e) {
			throw new IllegalArgumentException("Registro invalido");
		}

		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado().getRegistroImovel() == registroImovel) {
				a.getImovelAlugado().a_venda();
				return alugueis.remove(a);
			}
		}
		return false;
	}

	/**
	 * Metodo acessador do Aluguel de um Dado Imovel Alugado
	 * 
	 * @param registroDoImovel
	 *            Registro do Imovel Alugado
	 * @return Aluguel ou <br>
	 *         Null caso ele nao esteja alugado
	 */
	public Aluguel getAluguel(String registroDoImovel) {

		int registroImovel;
		try {
			registroImovel = Integer.parseInt(registroDoImovel);
		} catch (Exception e) {
			throw new IllegalArgumentException("Registro invalido");
		}

		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado().getRegistroImovel() == registroImovel) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Metodo que retorna o valor total de todos os alugueis
	 * 
	 * @return Valor Total de Todos os Alugueis no Controlador
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
