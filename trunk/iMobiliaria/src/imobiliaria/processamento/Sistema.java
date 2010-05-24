package imobiliaria.processamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Classe Sistema para guardar informacoes de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version IT 1
 */

/*
 * Essa Classe irá guardar todas as informacoes do Sistema, tais como banco de
 * dados contendo os Imoveis, Clientes, Funcionarios e o Caixa da Empresa
 */
public class Sistema {

	private final double SALARIO_DEFAULT = 500;
	private final double COMISSAO = 0.03;

	private ColecaoImoveis todosImoveis = new ColecaoImoveis();
	private ColecaoClientes todosClientes = new ColecaoClientes();
	private ColecaoFuncionario todosFuncionarios = new ColecaoFuncionario();
	private HashMap<String, String> loginClientes = new HashMap<String, String>();
	private HashMap<String, String> loginFuncionarios = new HashMap<String, String>();
	private TreeMap<Imovel, Cliente> listaPedidos = new TreeMap<Imovel, Cliente>();
	private Calendar ultimoPagamento = new GregorianCalendar(2010, 06, 1);
	private boolean pagouNesseMes = true;
	private double caixaTotal;

	/*
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

		if (pagouNesseMes) {
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
			
			funcionario.resetaImoveisVendidosMes();
		}

		/*
		 * Folha de Pagamento = HashMap com Salario dos Funcionarios, double
		 * Quanto Tinha, double Despesas
		 */

		Object[] folhaDePagamento = { salarioFuncionarios, caixaTotal, despesas };

		caixaTotal -= despesas;

		ultimoPagamento = new GregorianCalendar();
		pagouNesseMes = true;

		return folhaDePagamento;

		// Fiz isso pra podermos usar tanto na GUI qto no Console :D

	}

	/**
	 * Metodo para atualizar as informacoes de Pagamento
	 */

	public void atualizaPagamento() {
		GregorianCalendar hoje = new GregorianCalendar();

		// Se nao estiverem no Mes do Ano igual
		// Entao quer dizer que ainda nao fez o pagamento do mes

		if (!(ultimoPagamento.get(Calendar.YEAR) == hoje.get(Calendar.YEAR) && ultimoPagamento
				.get(Calendar.MONTH) == hoje.get(Calendar.MONTH))) {
			// Ainda nao pagou nesse mes
			pagouNesseMes = false;
		}
	}

	/**
	 * Metodo que adiciona um Pedido na lista de Pedidos
	 * 
	 * @param imovel
	 *            Imovel a ser pedido
	 * @param cliente
	 *            Cliente que pediu o imovel
	 * @throws Exception
	 *             Lanca excecao caso o imovel ja tenha sido pedido
	 */

	public void adicionaPedido(Imovel imovel, Cliente cliente) throws Exception {
		if (listaPedidos.containsKey(imovel)) {
			throw new Exception("Imovel ja pedido");
		}
		cliente.fazPedido(imovel);
		listaPedidos.put(imovel, cliente);
		imovel.setEstadoDoImovel(EstadoImovel.PEDIDO);
	}
	
	public void efetuaPedido(Imovel imovel, Funcionario funcionario) throws Exception {
		if (imovel.getEstadoDoImovel() != EstadoImovel.PEDIDO) {
			throw new Exception("Imovel nao pedido");
		}
		
		funcionario.addImovelVendido(imovel);
		imovel.setEstadoDoImovel(EstadoImovel.VENDIDO);
		caixaTotal += imovel.getValor();
	}
	
	public void removePedido(Imovel imovel) throws Exception {
		if (!(listaPedidos.containsKey(imovel))) {
			throw new Exception("Imovel nao pedido");
		}
		imovel.setEstadoDoImovel(EstadoImovel.A_VENDA);
		listaPedidos.get(imovel).removePedido(imovel);
		listaPedidos.remove(imovel);
		
	}

	/* Getters e Setters */
	
	/**
	 * Metodo acessador da Lista de Pedidos
	 * @return TreeMap contendo Lista dos Pedidos
	 */
	
	public TreeMap<Imovel, Cliente> getListaPedido() {
		return listaPedidos;
	}
	
	

	/**
	 * Metodo acessador das informacoes do Pagamento
	 * 
	 * @return Array contendo:<br>
	 *         Calendar com a Data do Ultimo Pagamento<br>
	 *         Boolean True se Ja Pagou esse mes e False se nao
	 */
	public boolean getPagouNesseMes() {
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
