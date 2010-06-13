package imobiliaria.controladores;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeSet;

import imobiliara.auxiliar.TipoImovel;
import imobiliaria.entidades.Cliente;
import imobiliaria.processamento.ColecaoClientes;
import imobiliaria.util.VerificaInvalido;

/**
 * Classe ControladorCliente que ira controlar e listar algumas opcoes sobre
 * Cliente
 * 
 * @author thiagofp
 * @version IT02
 */
public class ControladorCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ControladorCliente controladorClienteUnico = new ControladorCliente();
	private ColecaoClientes colecaoClientes = new ColecaoClientes();

	public static ControladorCliente getInstance() {
		return controladorClienteUnico;
	}

	private ControladorCliente() {
	}

	private HashMap<String, String> loginClientes = new HashMap<String, String>();

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

		if (colecaoClientes.adicionaCliente(clienteASerAdicionado)) {
			loginClientes.put(clienteASerAdicionado.getLogin(),
					clienteASerAdicionado.getSenha());
			return true;
		}
		return false;
	}

	/**
	 * Metodo verificador de login e senha para Clientes
	 * 
	 * @param login
	 *            Login
	 * @param senha
	 *            Senha
	 * @return True - caso o login seja efetuado <br>
	 *         False - caso o login e senha sejam invalidos
	 */

	public boolean login(String login, String senha) {
		if (VerificaInvalido.basico(login) || VerificaInvalido.basico(senha))
			return false;
		if (loginClientes.containsKey(login)) {
			if (loginClientes.get(login).equals(senha))
				return true;
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
			return null;
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
