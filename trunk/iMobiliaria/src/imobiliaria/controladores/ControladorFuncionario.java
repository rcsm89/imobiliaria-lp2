package imobiliaria.controladores;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeSet;

import imobiliaria.colecoes.ColecaoFuncionario;
import imobiliaria.entidades.Funcionario;
import imobiliaria.exceptions.FuncionarioNotFoundException;
import imobiliaria.util.VerificaInvalido;

/**
 * Classe ControladorFuncionario que controla e lista opcoes sobre Funcionario
 * 
 * @version IT02
 */
public class ControladorFuncionario implements Serializable {

	private static final long serialVersionUID = 1L;
	private static ControladorFuncionario controladorFuncionarioUnico = new ControladorFuncionario();
	private ColecaoFuncionario colecaoFunc = new ColecaoFuncionario();

	private ControladorFuncionario() {
	}

	/**
	 * Metodo que retorna uma instacia de controlador de funcionario
	 * 
	 * @return Controlador de Funcionario
	 */
	public static ControladorFuncionario getInstance() {
		return controladorFuncionarioUnico;
	}

	/**
	 * Metodo que modifica a instancia unica do Controlador de Funcionario
	 * 
	 * @param controladorFuncionario
	 *            Nova Instancia para o Funcionario
	 */
	public static void setInstance(ControladorFuncionario controladorFuncionario) {

		if (controladorFuncionario == null) {
			throw new IllegalArgumentException("Controlador invalido");
		}
		controladorFuncionarioUnico = controladorFuncionario;
	}

	/**
	 * Metodo acessador da colecao de funcionario
	 * 
	 * @return Colecao de funcionario
	 */
	public ColecaoFuncionario getColecaoFuncionario() {
		return colecaoFunc;
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

		return colecaoFunc.adicionaFuncionario(funcionarioASerAdicionado);
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
	 * @param creci
	 *            Creci do funcionario
	 * @param cpf
	 *            Cpf do funcionario
	 * @param dataNascimento
	 *            Data do nascimento do funcionario
	 * @param endereco
	 *            Endereco do funcionario
	 * @param nome
	 *            Nome do funcionario
	 * 
	 * @throws Exception
	 *             Se o funcionario for nulo
	 */
	public void modificaFuncionario(String creci, String cpf,
			Calendar dataNascimento, String endereco, String nome)
			throws Exception {

		Funcionario funcionario = colecaoFunc.getFuncionario(creci);

		if (funcionario == null) {
			throw new FuncionarioNotFoundException("Funcionario nao existente");
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
	 * Exibe informacoes de totais de vendas
	 * 
	 * @return Uma string com informacoes de venda de cada funcionario
	 */
	public String exibeTotaisDeVendas() {
		HashMap<String, Double> funcSalario = listaTotaisDeVendas();
		String saida = "";

		for (String nomeFunc : funcSalario.keySet()) {
			saida += "Nome: " + nomeFunc + "\nValor de Vendas: "
					+ funcSalario.get(nomeFunc) + "\n\n";
		}

		return saida;

	}

	/**
	 * Metodo Acessador de um Funcionario
	 * 
	 * @param creci
	 *            Creci do Funcionario a ser retornado
	 * @return Funcionario ou null, caso nao exista
	 */
	public Funcionario getFuncionarioPorCreci(String creci) {
		if (VerificaInvalido.numero(creci)) {
			throw new IllegalArgumentException("Creci invalido");
		}

		for (Funcionario func : colecaoFunc.getColecaoFuncionarios()) {
			if (func.getCreci().equals(creci))
				return func;
		}
		return null;
	}

	/**
	 * Metodo acessador de Funcionario por Username
	 * 
	 * @param username
	 *            Username do Funcionario
	 * @return Funcionario com o username passado como parametro
	 */
	public Funcionario getFuncionarioPorUsername(String username) {
		if (VerificaInvalido.basico(username)) {
			throw new IllegalArgumentException("Username invalido");
		}

		for (Funcionario func : colecaoFunc.getColecaoFuncionarios()) {
			if (func.getLogin().getUserName().equals(username))
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
		if (VerificaInvalido.numero(creci)) {
			throw new IllegalArgumentException("Creci invalido");
		}

		Funcionario func = getFuncionarioPorCreci(creci);

                if (func == null) {
                    return null;
                }

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
		if (VerificaInvalido.basico(cpf)) {
			throw new IllegalArgumentException("CPF invalido");
		}

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
		if (VerificaInvalido.basico(cpf)) {
			throw new IllegalArgumentException("CPF invalido");
		}
		Funcionario func = getFuncionarioPorCpf(cpf);

		return "Nome: " + func.getNome() + "\nCreci: " + func.getCreci()
				+ "\nCpf: " + func.getCpf() + "\nData de Nascimento: "
				+ func.getDataNascimento() + "\nEndereco: "
				+ func.getEndereco();
	}

	/**
	 * Remove um funcionario da colecao apartir do seu creci
	 * 
	 * @param creci
	 *            Creci do funcionario a ser removido
	 * @return True, se o funcionario foi removido<br>
	 *         False, caso contrario
	 * @throws IllegalArgumentException
	 *             Caso o creci seja invalido
	 */
	public boolean removeFuncionario(String creci) {
		if (VerificaInvalido.numero(creci)) {
			throw new IllegalArgumentException("Creci Invalido");
		}
		for (Funcionario func : colecaoFunc.getColecaoFuncionarios()) {
			if (func.getCreci().equals(creci)) {
                            return colecaoFunc.getColecaoFuncionarios().remove(func);
			}
		}



		return false;
	}

}