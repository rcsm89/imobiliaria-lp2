package imobiliaria.controladores;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TreeSet;

import imobiliara.auxiliar.TipoImovel;
import imobiliaria.entidades.Cliente;
import imobiliaria.exceptions.ClienteNotFoundException;
import imobiliaria.processamento.ColecaoClientes;
import imobiliaria.util.VerificaInvalido;

/**
 * Classe ControladorCliente que ira controlar e listar algumas opcoes sobre
 * Cliente
 * 
 * @author Thiago Ferreira Patricio
 * @version IT02
 */
public class ControladorCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ControladorCliente controladorClienteUnico = new ControladorCliente();
	private ColecaoClientes colecaoClientes = new ColecaoClientes();
	
	private ControladorCliente() {
	}
	
	/**
	 * Metodo que retorna uma instacia de controlador de cliente
	 * 
	 * @return Controlador de Cliente
	 */
	public static ControladorCliente getInstance() {
		return controladorClienteUnico;
	}

	/**
	 * Metodo que modifica a instancia unica do Controlador de Clientes
	 * 
	 * @param controlador
	 *            Nova Instancia para o Controlador
	 */
	public static void setInstance(ControladorCliente controlador) {
		if (controlador == null) {
			throw new IllegalArgumentException(
					"Controlador de Pedidos invalido");
		}
		controladorClienteUnico = controlador;
	}
	
	/**
	 * Metodo que adiciona um Cliente no Controlador
	 * 
	 * @param cpf
	 *            CPF
	 * @param dataNascimento
	 *            Data de Nascimento
	 * @param endereco
	 *            Endereco
	 * @param nome
	 *            Nome
	 * @param preferencia
	 *            Preferencia de Imovel
	 * @return True - Caso o Cliente seja adicionado<br>
	 *         False - Caso ele ja exista no Sistema
	 * @throws Exception
	 *             Caso algum parametro seja invalido
	 */
	public boolean adicionaCliente(String cpf, Calendar dataNascimento,
			String endereco, String nome, TipoImovel preferencia)
			throws Exception {

		Cliente clienteASerAdicionado = new Cliente(cpf, dataNascimento,
				endereco, nome, preferencia);
		return colecaoClientes.adicionaCliente(clienteASerAdicionado); 
		
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

		for (Cliente cliente : colecaoClientes.getClientes()) {
			if (cpfASerRemovido.equals(cliente.getCpf())) {
				return colecaoClientes.getClientes().remove(cliente);
			}
		}
		return false;
	}

	/**
	 * Metodo acessador da colecao de clientes
	 * 
	 * @return Colecao de clientes
	 */
	public ColecaoClientes getColecaoClientes() {
		return colecaoClientes;
	}

	/**
	 * Metodo Acessador de um Cliente
	 * 
	 * @param cpf
	 *            CPF do Cliente a ser retornado
	 * @return Cliente ou null, caso nao exista
	 * 
	 */
	public Cliente getCliente(String cpf) {
		if(VerificaInvalido.basico(cpf)){
			throw new IllegalArgumentException("CPF invalido");
		}
		
		for (Cliente c : colecaoClientes.getClientes()) {
			if (c.getCpf().equals(cpf))
				return c;
		}
		return null;
	}

	/**
	 * Metodo que exibe informacoes de um Cliente
	 * 
	 * @param cpf
	 *            CPF do Cliente a ser exibido
	 * @return String contendo informacoes do Cliente
	 * 
	 * 
	 */
	public String exibeCliente(String cpf) {
		Cliente c = getCliente(cpf);

		if (c == null) {
			throw new ClienteNotFoundException("Cliente nao existente");
		}

		return "Nome: " + c.getNome() + " - CPF: " + c.getCpf() + "\n"
				+ "Endereco: " + c.getEndereco() + " - Data de Nascimento: "
				+ c.getDataNascimento() + " - Preferencia: "
				+ c.getPreferencia();
	}

	/**
	 * Metodo Lista Clientes
	 * 
	 * @return String contendo uma listagem dos clientes
	 */
	public String listaClientes() {
		return listaClientes(colecaoClientes.getClientes());
	}

	/**
	 * Metodo que lista clientes de uma preferencia
	 * 
	 * @param preferencia
	 *            Preferencia dos clientes a serem listados
	 * @return String contendo listagem de clientes
	 */
	public String listaClientes(TipoImovel preferencia) {
		return listaClientes(colecaoClientes.getClientes(preferencia));
	}

	/**
	 * Metodo que lista clientes de uma letra inicial
	 * 
	 * @param letraInicial
	 *            Letra Inicial dos clientes a serem listados
	 * @return String contendo listagem de clientes iniciados pela letra
	 */

	public String listaClientes(String letraInicial) {
		return listaClientes(colecaoClientes
				.getClientesPorLetraInicial(letraInicial));
	}

	private String listaClientes(TreeSet<Cliente> conjDeClientes) {
		String saida = "";

		for (Cliente c : conjDeClientes) {
			saida += "Nome: " + c.getNome() + " - CPF: " + c.getCpf()
					+ " - Data de Nascimento: " + c.getDataNascimento() + "\n"
					+ "Endereco: " + c.getEndereco() + " - Preferencia: "
					+ c.getPreferencia() + "\n\n";
		}
		return saida;
	}
}
