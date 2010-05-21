package imobiliaria.processamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
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

	/*
	 * pagouNesseMes = [DataDoUltimoPagamento, JaPagouNesseMes]
	 * 
	 * Para a primeira execucao coloquei que o pagamento ja foi efetuado e que
	 * foi feito na data de 01/06/2010 :D
	 */

	private Object[] pagouNesseMes = { new GregorianCalendar(2010, 06, 1), true };

	public Object[] efetuaPagamentoNoMes() throws Exception {

		atualizaPagamento();

		boolean jaPagouEsseMes = (Boolean) pagouNesseMes[1];

		if (jaPagouEsseMes) {
			throw new Exception("Pagamento ja efetuado esse mes");
		}

		HashMap<String, Double> folhaDePagamentoFuncionarios = new HashMap<String, Double>();
		
		double despesas = 0;
		double salarioFuncionario;

		for (Funcionario funcionario : todosFuncionarios
				.getColecaoFuncionarios()) {
			
			salarioFuncionario = SALARIO_DEFAULT + COMISSAO
					* funcionario.getTotalDeVendas();
			
			folhaDePagamentoFuncionarios.put(funcionario.getNome(),
					salarioFuncionario);
			
			despesas += salarioFuncionario;
		}

		/* Folha de Pagamento = HashMap com Salario dos Funcionarios,
		 * double Quanto Tinha, double Despesas
		 */

		Object[] folhaDePagamento = { folhaDePagamentoFuncionarios, caixaTotal,
				despesas };

		caixaTotal -= despesas;
		return folhaDePagamento;
		
		// Fiz isso pra podermos usar tanto na GUI qto no Console :D
		
	}

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

	/**
	 * @return the pagouNesseMes
	 */
	public Object[] getPagouNesseMes() {
		return pagouNesseMes;
	}

	/**
	 * @param pagouNesseMes
	 *            the pagouNesseMes to set
	 */
	public void setPagouNesseMes(Object[] pagouNesseMes) {
		this.pagouNesseMes = pagouNesseMes;
	}

	/**
	 * @return the loginClientes
	 */
	public HashMap<String, String> getLoginClientes() {
		return loginClientes;
	}

	/**
	 * @return the loginFuncionarios
	 */
	public HashMap<String, String> getLoginFuncionarios() {
		return loginFuncionarios;
	}

	/**
	 * @return the todosImoveis
	 */
	public ColecaoImoveis getTodosImoveis() {
		return todosImoveis;
	}

	/**
	 * @param todosImoveis
	 *            the todosImoveis to set
	 */
	public void setTodosImoveis(ColecaoImoveis todosImoveis) {
		this.todosImoveis = todosImoveis;
	}

	/**
	 * @return the todosClientes
	 */
	public ColecaoClientes getTodosClientes() {
		return todosClientes;
	}

	/**
	 * @param todosClientes
	 *            the todosClientes to set
	 */
	public void setTodosClientes(ColecaoClientes todosClientes) {
		this.todosClientes = todosClientes;
	}

	/**
	 * @return the todosFuncionarios
	 */
	public ColecaoFuncionario getTodosFuncionarios() {
		return todosFuncionarios;
	}

	/**
	 * @param todosFuncionarios
	 *            the todosFuncionarios to set
	 */
	public void setTodosFuncionarios(ColecaoFuncionario todosFuncionarios) {
		this.todosFuncionarios = todosFuncionarios;
	}

	/**
	 * @return the caixaTotal
	 */
	public double getCaixaTotal() {
		return caixaTotal;
	}

	/**
	 * @param caixaTotal
	 *            the caixaTotal to set
	 */
	public void setCaixaTotal(double caixaTotal) {
		this.caixaTotal = caixaTotal;
	}
}
