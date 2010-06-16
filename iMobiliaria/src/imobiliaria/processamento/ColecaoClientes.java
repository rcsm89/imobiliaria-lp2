package imobiliaria.processamento;

import imobiliara.auxiliar.TipoImovel;
import imobiliaria.entidades.Cliente;
import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * Classe ColecaoClientes que guarda clientes
 * 
 * @author thiagofp
 * @version IT02
 */
public class ColecaoClientes implements Serializable {

	// Atributos

	private static final long serialVersionUID = 1L;
	private TreeSet<Cliente> colecaoClientes = new TreeSet<Cliente>();

	// Metodos de adicao e remocao de clientes

	/**
	 * Metodo que adiciona clientes na Colecao
	 * 
	 * @param clienteASerAdicionado
	 *            cliente que sera Adicionado
	 * @return True - Caso o cliente tenha sido adicionado <br>
	 *         False - Caso o cliente ja exista na colecao
	 * @throws Caso o cliente seja invalido
	 */
	public boolean adicionaCliente(Cliente clienteASerAdicionado) throws Exception {
		if (clienteASerAdicionado == null){
			throw new IllegalArgumentException("Cliente invalido\n");
		}
		
		if (colecaoClientes.contains(clienteASerAdicionado)) {
			return false;
		}

		return colecaoClientes.add(clienteASerAdicionado);
	}

	/**
	 * Metodo que remove um cliente da Colecao
	 * 
	 * @param cpfASerRemovido
	 *            Representa o CPF do cliente a Ser Removido
	 * @return True - Caso o cliente seja removido <br>
	 *         False - Caso ele nao seja encontrado
	 */
	public boolean removeCliente(String cpfASerRemovido) {
		final int TAM_CPF_FORMATADO = 14;
		
		if (VerificaInvalido.basico(cpfASerRemovido))
			return false;
		
		if (!(cpfASerRemovido.length() == TAM_CPF_FORMATADO)) {
			return false;
		}
		
		for (Cliente cliente : colecaoClientes) {
			if (cpfASerRemovido.equals(cliente.getCpf())) {
				return colecaoClientes.remove(cliente);
			}
		}
		return false;
	}

	// Metodos de Organizacao de clientes

	/**
	 * Metodo responsavel por retornar a quantidade de clientes
	 * 
	 * @return Inteiro, indicando o total de clientes cadastrados
	 */
	public int numeroTotalDeClientes() {
		return colecaoClientes.size();
	}

	/**
	 * Metodo acessador de toda a colecao de clientes
	 * 
	 * @return ArrayList contendo todos os clientes da Colecao
	 */
	public TreeSet<Cliente> getClientes() {
		return colecaoClientes;
	}

	/**
	 * Metodo responsavel por retornar os clientes com determinada preferencia
	 * 
	 * @param preferencia
	 *            Representa a preferencia dos clientes que serao retornados
	 * 
	 * @return Clientes com a dada preferencia de imovel passada
	 */

	public TreeSet<Cliente> getClientes(TipoImovel preferencia) {

		TreeSet<Cliente> colecaoRetornada = new TreeSet<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			if (cliente.getPreferencia() == preferencia) {
				colecaoRetornada.add(cliente);
			}
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
	public TreeSet<Cliente> getClientes(String nome) {
		if (VerificaInvalido.nome(nome)) {
			throw new IllegalArgumentException("Nome invalido\n");
		}

		TreeSet<Cliente> colecaoRetornada = new TreeSet<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			if (cliente.getNome().contains(nome)) {
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
	 * @return TreeSet contendo os clientes que possuem a letra inicial do nome
	 *         igual a passada
	 */
	public TreeSet<Cliente> getClientesPorLetraInicial(String letra) {
		if (VerificaInvalido.basico(letra) || letra.length() != 1 ||
				Character.isDigit(letra.charAt(0))) {
			throw new IllegalArgumentException("Letra Invalida");
		}

		TreeSet<Cliente> colecaoRetornada = new TreeSet<Cliente>();

		for (Cliente cliente : colecaoClientes) {

			if (cliente.getNome().startsWith(letra.toUpperCase()))
				colecaoRetornada.add(cliente);

		}
		return colecaoRetornada;
	}

}