package imobiliaria.processamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Classe Sistema para guardar informacoes de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version IT 1
 */

 /*
  * Essa Classe irá guardar todas as informacoes do Sistema, tais como banco de dados
  * contendo os Imoveis, Clientes, Funcionarios e o Caixa da Empresa
  */
public class Sistema {

	private final double SALARIO_DEFAULT = 500;
	private final double COMISSAO = 0.03;

	private ColecaoImoveis todosImoveis = new ColecaoImoveis();
	private ColecaoClientes todosClientes = new ColecaoClientes();
	private ColecaoFuncionario todosFuncionarios = new ColecaoFuncionario();
	private HashMap<String, String> loginClientes = new HashMap<String, String>();
	private HashMap<String, String> loginFuncionarios = new HashMap<String, String>();
	private double caixaTotal;

	private Object[] pagouNesseMes = { new GregorianCalendar(2010, 06, 1), true };

	/*
	 * pagouNesseMes = [DataDoUltimoPagamento, JaPagouNesseMes]
	 * 
	 * Para a primeira execucao coloquei que o pagamento ja foi efetuado e que
	 * foi feito na data de 01/06/2010 :D
	 */

	/**
	 * Metodo para efetuar pagamento no Mes
	 * 
	 * @return Array de Object Contendo:<br>
	 *         Hashmap contendo <Funcionario, SeuSalario><br>
	 *         Double contendo o Caixa Antigo Total<br>
	 *         Double contendo as Despesas do Pagamento
	 * @throws Exception
	 *             Lanca excecao se ja tiver feito pagamento esse mes
	 */

	public Object[] efetuaPagamentoNoMes() throws Exception {

		atualizaPagamento();

		boolean jaPagouEsseMes = (Boolean) pagouNesseMes[1];

		if (jaPagouEsseMes) {
			throw new Exception("Pagamento ja efetuado esse mes");
		}

		HashMap<String, Double> salarioFuncionarios = new HashMap<String, Double>();

		double despesas = 0;
		double salarioFuncionario;

		for (Funcionario funcionario : todosFuncionarios
				.getColecaoFuncionarios()) {

			salarioFuncionario = SALARIO_DEFAULT + COMISSAO
					* funcionario.getTotalDeVendas();

			salarioFuncionarios.put(funcionario.getNome(), salarioFuncionario);

			despesas += salarioFuncionario;
		}

		/*
		 * Folha de Pagamento = HashMap com Salario dos Funcionarios, double
		 * Quanto Tinha, double Despesas
		 */

		Object[] folhaDePagamento = { salarioFuncionarios, caixaTotal, despesas };

		caixaTotal -= despesas;

		pagouNesseMes[0] = new GregorianCalendar();
		pagouNesseMes[1] = true;

		return folhaDePagamento;

		// Fiz isso pra podermos usar tanto na GUI qto no Console :D

	}

	/**
	 * Metodo para atualizar as informacoes de Pagamento
	 */

	public void atualizaPagamento() {
		GregorianCalendar dataUltimoPagamento = (GregorianCalendar) pagouNesseMes[0];
		GregorianCalendar hoje = new GregorianCalendar();

		// Se nao estiverem no Mes do Ano igual
		// Entao quer dizer que ainda nao fez o pagamento do mes

		if (!(dataUltimoPagamento.get(Calendar.YEAR) == hoje.get(Calendar.YEAR) && dataUltimoPagamento
				.get(Calendar.MONTH) == hoje.get(Calendar.MONTH))) {
			// Ainda nao pagou nesse mes
			pagouNesseMes[1] = false;
		}
	}
	
	/* Getters e Setters */

	/**
	 * Metodo acessador das informacoes do Pagamento
	 * 
	 * @return Array contendo:<br>
	 *         Calendar com a Data do Ultimo Pagamento<br>
	 *         Boolean True se Ja Pagou esse mes e False se nao
	 */
	public Object[] getPagouNesseMes() {
		atualizaPagamento();
		return pagouNesseMes;
	}

	/**
	 * Metodo acessador dos Logins de Clientes
	 * 
	 * @return Hashmap contendo:<br>
	 *         Hashmap<Login, Senha>
	 */
	public HashMap<String, String> getLoginClientes() {
		return loginClientes;
	}

	/**
	 * Metodo acessador dos Logins de Funcionarios
	 * 
	 * @return Hashmap contendo:<br>
	 *         Hashmap<Login, Senha>
	 */
	public HashMap<String, String> getLoginFuncionarios() {
		return loginFuncionarios;
	}

	/**
	 * Metodo Acessador de Todos os Imoveis do Sistema
	 * 
	 * @return Colecao de Imoveis
	 */
	public ColecaoImoveis getTodosImoveis() {
		return todosImoveis;
	}

	/**
	 * Metodo Acessador de Todos os Clientes do Sistema
	 * 
	 * @return Colecao de Clientes
	 */
	public ColecaoClientes getTodosClientes() {
		return todosClientes;
	}

	/**
	 * Metodo Acessador de Todos os Funcionarios do Sistema
	 * 
	 * @return Colecao de Funcionarios
	 */
	public ColecaoFuncionario getTodosFuncionarios() {
		return todosFuncionarios;
	}

	/**
	 * Metodo Acessador do Caixa Total do Sistema
	 * 
	 * @return Caixa Total
	 */
	public double getCaixaTotal() {
		return caixaTotal;
	}

	/**
	 * Metodo Modificador do Caixa Total
	 * 
	 * @param caixaTotal
	 *            Novo Caixa Total
	 */
	public void setCaixaTotal(double caixaTotal) {
		this.caixaTotal = caixaTotal;
	}
}
