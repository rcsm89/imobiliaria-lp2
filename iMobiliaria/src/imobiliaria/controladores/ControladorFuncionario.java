package imobiliaria.controladores;

import java.io.Serializable; 
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeSet;

import imobiliaria.entidades.Funcionario;
import imobiliaria.processamento.ColecaoFuncionario;
import imobiliaria.util.VerificaInvalido;

/**
 * Classe ControladorFuncionario que controla e lista opcoes sobre Funcionario
 * 
 * @author thiagofp
 * 
 * @version IT02
 */
public class ControladorFuncionario implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ControladorFuncionario controladorFuncionarioUnico = new ControladorFuncionario();
	private HashMap<String, String> loginFuncionarios = new HashMap<String, String>();
	private ColecaoFuncionario colecaoFunc = new ColecaoFuncionario();

	public static ControladorFuncionario getInstance() {
		return controladorFuncionarioUnico;
	}

	private ControladorFuncionario() {
	}

	/**
	 * Adiciona um funcionario no Controlador
	 * 
	 * @param cpf
	 *            Cpf do funcionario a ser adicionado
	 * @param dataNascimento
	 *            Data do nascimento do funcionario a ser adicionado
	 * @param endereco
	 *            Endereco do funcionario a ser adicionado
	 * @param nome
	 *            Nome do funcionario a ser adicionado
	 * @param creci
	 *            Creci do funcionario a ser adicionado
	 * @return True, se um funcionario foi adicionado<br>
	 *         False, caso contrario
	 * @throws Exception
	 *             Caso algum parametro seja invalido
	 */

	public boolean adicionaFuncionario(String cpf, Calendar dataNascimento,
			String endereco, String nome, String creci) throws Exception {

		Funcionario funcionarioASerAdicionado = new Funcionario(cpf,
				dataNascimento, endereco, nome, creci);

		if (colecaoFunc.adicionaFuncionario(funcionarioASerAdicionado)) {
			loginFuncionarios.put(funcionarioASerAdicionado.getLogin(),
					funcionarioASerAdicionado.getSenha());
			return true;
		}

		return false;
	}

	/**
	 * Metodo verificador de login e senha para Funcionarios
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
		if (loginFuncionarios.containsKey(login)) {
			if (loginFuncionarios.get(login).equals(senha))
				return true;
		}
		return false;
	}

	/**
	 * Metodo responsavel por listar todos os funcionarios
	 * 
	 * @return Uma String contendo a lista dos funcionarios
	 */
	public String listaFuncionarios() {
		return listaFuncionarios(colecaoFunc.getColecaoFuncionarios());
	}

	/**
	 * Metodo responsavel por listar um conjunto de funcionario
	 * 
	 * @param conjuntoDeFuncionarios
	 *            Representa um conjunto que contem os funcionarios que seram
	 *            listados
	 * @return Uma String contendo uma listagem dos funcionarios
	 */
	private String listaFuncionarios(TreeSet<Funcionario> listaDeFuncionarios) {
		String saida = "";

		for (Funcionario func : listaDeFuncionarios) {

			saida += "Nome: " + func.getNome() + " - CPF: " + func.getCpf()
					+ "\n" + "Endereco: " + func.getEndereco()
					+ "\nData de Nascimento: " + func.getDataNascimento()
					+ " - Creci: " + func.getCreci() + "\n\n";

		}

		return saida;
	}

	/**
	 * Metodo que modifica um funcionario
	 * 
	 * @param funcionario
	 *            Funcionario a ser modificado
	 * @param cpf
	 *            Cpf do funcionario
	 * @param dataNascimento
	 *            Data do nascimento do funcionario
	 * @param endereco
	 *            Endereco do funcionario
	 * @param nome
	 *            Nome do funcionario
	 * @param creci
	 *            Creci do funcionario
	 * @throws Exception
	 *             Se o funcionario for nulo
	 */
	public void modificaFuncionario(Funcionario funcionario, String cpf,
			Calendar dataNascimento, String endereco, String nome, String creci)
			throws Exception {
		if (funcionario == null) {
			throw new Exception("Funcionario invalido");
		}
		funcionario.setCpf(cpf);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setEndereco(endereco);
		funcionario.setNome(nome);
	}

	/**
	 * Imprime uma listagem com dados dos funcionarios
	 * 
	 * @param conjuntoFunc
	 *            Conjunto de funcionarios a ser exibido
	 */
	public void imprimeListagemFuncionario(TreeSet<Funcionario> conjuntoFunc) {

		if (conjuntoFunc.isEmpty()) {
			System.out.println("Nada a ser listado");
			return;
		}

		for (Funcionario func : conjuntoFunc) {
			System.out.println("Cpf: " + func.getCpf() + " - " + func.getNome()
					+ "Creci: " + func.getCreci() + "\n - Data de Nascimento: "
					+ func.getDataNascimento() + "\nEndereco: "
					+ func.getEndereco() + "\n");
		}
	}

	/**
	 * Retorna um mapa com o nome do funcionario e o total de vendas como chave
	 * e valor
	 * 
	 * @return Mapa retornado
	 */
	public HashMap<String, Double> listaTotaisDeVendas() {
		HashMap<String, Double> funcSalario = new HashMap<String, Double>();
		double valorDeVendas;

		for (Funcionario funcionario : colecaoFunc.getColecaoFuncionarios()) {
			funcionario.resetaImoveisVendidosMes();
			valorDeVendas = funcionario.getTotalDeVendas();
			funcSalario.put(funcionario.getNome(), valorDeVendas);
		}
		return funcSalario;
	}

	/**
	 * Metodo Acessador de um Funcionario
	 * 
	 * @param creci
	 *            Creci do Funcionario a ser retornado
	 * @return Funcionario ou null, caso nao exista
	 */
	public Funcionario getFuncionarioPorCreci(String creci) {
		for (Funcionario func : colecaoFunc.getColecaoFuncionarios()) {
			if (func.getCreci().equals(creci))
				return func;
		}
		return null;
	}

	/**
	 * Metodo que exibe informacoes de um Funcionario
	 * 
	 * @param creci
	 *            Creci do Funcionario a ser exibido
	 * @return String contendo informacoes do Funcionario
	 */
	public String exibeFuncionarioPorCreci(String creci) {
		Funcionario func = getFuncionarioPorCreci(creci);

		return "Nome: " + func.getNome() + "\nCreci: " + func.getCreci()
				+ "\nCpf: " + func.getCpf() + "\nData de Nascimento: "
				+ func.getDataNascimento() + "\nEndereco: "
				+ func.getEndereco();
	}

	/**
	 * Metodo Acessador de um Funcionario
	 * 
	 * @param cpf
	 *            CPF do Funcionario a ser retornado
	 * @return Funcionario ou null, caso nao exista
	 */
	public Funcionario getFuncionarioPorCpf(String cpf) {
		for (Funcionario func : colecaoFunc.getColecaoFuncionarios()) {
			if (func.getCpf().equals(cpf))
				return func;
		}
		return null;
	}

	/**
	 * Metodo que exibe informacoes de um Funcionario
	 * 
	 * @param cpf
	 *            CPF do Funcionario a ser exibido
	 * @return String contendo informacoes do Funcionario
	 */
	public String exibeFuncionarioPorCpf(String cpf) {
		Funcionario func = getFuncionarioPorCpf(cpf);

		return "Nome: " + func.getNome() + "\nCreci: " + func.getCreci()
				+ "\nCpf: " + func.getCpf() + "\nData de Nascimento: "
				+ func.getDataNascimento() + "\nEndereco: "
				+ func.getEndereco();
	}

}