package imobiliaria.controladores;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.TipoImovel;
import imobiliaria.processamento.ColecaoClientes;
import imobiliaria.util.VerificaInvalido;

/**
 * Classe ControladorCliente que ira controlar e listar algumas opcoes sobre
 * Cliente
 * 
 * @author Yuri
 * @IT 1
 */
public class ControladorCliente extends ColecaoClientes {

	private static final long serialVersionUID = 1L;
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
		
		System.out.println("Ta aqui!");

		Cliente clienteASerAdicionado = new Cliente(cpf, dataNascimento,
				endereco, nome, preferencia);
		
		System.out.println(clienteASerAdicionado);

		if (adicionaCliente(clienteASerAdicionado)) {
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
	 * Metodo Acessador de um Cliente
	 * 
	 * @param cpf
	 *            CPF do Cliente a ser retornado
	 * @return Cliente ou null, caso nao exista
	 */
	public Cliente getCliente(String cpf) {
		for (Cliente c : getClientes()) {
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
		return listaClientes(getClientesPorOrdemAlfabetica());
	}

	/**
	 * Metodo que lista clientes de uma preferencia
	 * 
	 * @param preferencia
	 *            Preferencia dos clientes a serem listados
	 * @return String contendo listagem de clientes
	 */
	public String listaClientes(TipoImovel preferencia) {
		return listaClientes(getClientes(preferencia));
	}

	/**
	 * Metodo que lista clientes de uma letra inicial
	 * 
	 * @param letraInicial
	 *            Letra Inicial dos clientes a serem listados
	 * @return String contendo listagem de clientes iniciados pela letra
	 */

	public String listaClientes(String letraInicial) {
		return listaClientes(getClientesPorLetraInicial(letraInicial));
	}

	private String listaClientes(List<Cliente> listaDeClientes) {
		String saida = "";

		for (Cliente c : listaDeClientes) {
			saida += "Nome: " + c.getNome() + " - CPF: " + c.getCpf()
					+ " - Data de Nascimento: " + c.getDataNascimento() + "\n"
					+ "Endereco: " + c.getEndereco() + " - Preferencia: "
					+ c.getPreferencia() + "\n\n";
		}
		return saida;
	}
}
