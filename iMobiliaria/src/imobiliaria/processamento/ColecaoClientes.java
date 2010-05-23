package imobiliaria.processamento;

import imobiliaria.util.VerificaInvalido;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe ColecaoClientes que guarda clientes
 * 
 * @version IT01
 */
public class ColecaoClientes {

	// Atributos

	private List<Cliente> colecaoClientes = new ArrayList<Cliente>();

	// Metodos de adicao e remocao de clientes

	/**
	 * Metodo que adiciona clientes na Colecao
	 * 
	 * @param clienteASerAdicionado
	 *            cliente que sera Adicionado
	 * @return True - Caso o cliente tenha sido adicionado <br>
	 *         False - Caso o cliente ja exista na colecao
	 */
	public boolean adicionaCliente(Cliente clienteASerAdicionado) {
		if (clienteASerAdicionado == null)
			return false;

		if (colecaoClientes.contains(clienteASerAdicionado)) {
			return false;
		}

		return colecaoClientes.add(clienteASerAdicionado);
	}

	/**
	 * Metodo que remove um cliente da Colecao
	 * 
	 * @param cpfDoClienteASerRemovido
	 *            Representa o CPF do cliente a Ser Removido
	 * @return True - Caso o cliente seja removido <br>
	 *         False - Caso ele nao seja encontrado
	 */
	public boolean removeCliente(String cpfDoClienteASerRemovido) {
		final int TAM_CPF_FORMATADO = 11;

		if (VerificaInvalido.basico(cpfDoClienteASerRemovido))
			return false;

		if (VerificaInvalido.numeroFormatado(cpfDoClienteASerRemovido,
				TAM_CPF_FORMATADO)) {
			return false;
		}

		for (Cliente cliente : colecaoClientes) {
			if (cliente.getCpf() == cpfDoClienteASerRemovido) {
				return colecaoClientes.remove(cliente);
			}
		}
		return false;
	}

	// Metodos de Organizacao de clientes

	/**
	 * Metodo acessador de toda a colecao de clientes
	 * 
	 * @return ArrayList contendo todos os clientes da Colecao
	 */
	public List<Cliente> getClientes() {
		return colecaoClientes;
	}

	/**
	 * Metodo responsavel por retornar a quantidade de clientes
	 * 
	 * @return Inteiro, indicando o total de clientes cadastrados
	 */
	public int numeroTotalDeClientes() {
		return colecaoClientes.size();
	}

	/**
	 * Metodo responsavel por retornar os clientes com determinada preferencia
	 * 
	 * @param preferencia
	 *            Representa a preferencia dos clientes que serao retornados
	 * 
	 * @return Clientes com a dada preferencia de imovel passada
	 */
	public List<Cliente> getClientesPorPreferencia(TipoImovel preferencia) {

		List<Cliente> colecaoRetornada = new ArrayList<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			if (cliente.getPreferencia() == preferencia) {
				colecaoRetornada.add(cliente);
			}
		}
		return colecaoRetornada;
	}

	/**
	 * Metodo filtrador que retorna clientes com mesmo pedido de imovel
	 * 
	 * @param tipoDeImovelRequerido
	 *            Tipo de Imovel para ser usado como Filtro
	 * @return List contendo clientes com imovel em comum nos pedidos
	 */
	public List<Cliente> getClientesPorPedido(Imovel pedidoRequerido) {

		List<Cliente> colecaoRetornada = new ArrayList<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			for (Imovel pedido : cliente.getPedidos().getImoveis()) {

				if (pedido.equals(pedidoRequerido))
					colecaoRetornada.add(cliente);
			}
		}
		return colecaoRetornada;

	}

	/**
	 * Metodo filtrador que retorna os clientes cujo nome possuem letra inicial
	 * igual a letra passada como parametro
	 * 
	 * @param letra
	 *            Representa a letra inicial do nome dos clientes
	 * @return List contendo os clientes que possuem a letra inicial do nome
	 *         igual a passada
	 */
	public List<Cliente> getClientesPorLetraInicial(String letra) {

		List<Cliente> colecaoRetornada = new ArrayList<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			if (cliente.getNome().startsWith(letra.toUpperCase()))
				colecaoRetornada.add(cliente);

		}
		return colecaoRetornada;
	}

	/**
	 * Metodo filtrador que retorna clientes com um determinado nome.
	 * 
	 * @param nome
	 *            Representa o nome pelo qual seram pesquisados os novos
	 *            clientes
	 * @return List contendo os clientes que possuem o nome passado
	 */
	public List<Cliente> getClientesPorNome(String nome) {

		List<Cliente> colecaoRetornada = new ArrayList<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			if (cliente.getNome().contains(nome)) {
				colecaoRetornada.add(cliente);
			}
		}
		return colecaoRetornada;
	}

	/**
	 * Metodo filtrador que retornar todos os clientes em ordem alfabetica
	 * 
	 * @return List contendo clientes ordenados pelo alfabeto
	 */
	public List<Cliente> getClientesPorOrdemAlfabetica() {

		String[] alfabeto = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
				"w", "x", "y", "z" };

		List<Cliente> colecaoRetornada = new ArrayList<Cliente>();

		for (int i = 0; i < alfabeto.length; i++) {

			for (Cliente cliente : colecaoClientes) {

				if (cliente.getNome().startsWith(alfabeto[i].toUpperCase()))
					colecaoRetornada.add(cliente);

			}
		}
		return colecaoRetornada;
	}

}