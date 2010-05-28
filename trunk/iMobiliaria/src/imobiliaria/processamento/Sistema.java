package imobiliaria.processamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;
import imobiliaria.controladores.*;

/**
 * Classe Sistema para guardar informacoes de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version IT 2
 */

/*
 * Essa Classe ira guardar todas as informacoes do Sistema, tais como banco de
 * dados contendo os Imoveis, Clientes, Funcionarios e o Caixa da Empresa
 */
public class Sistema {

	private final double SALARIO_DEFAULT = 1500;
	private final double COMISSAO = 0.03;

	private ControladorImovel controladorImoveis = new ControladorImovel();
	private ControladorCliente controladorClientes = new ControladorCliente();
	private ControladorFuncionario controladorFuncionarios = new ControladorFuncionario();

	private TreeMap<Imovel, Cliente> listaPedidos = new TreeMap<Imovel, Cliente>();

	private Calendar ultimoPagamento = new GregorianCalendar(2010, 06, 1);
	private boolean pagouNesseMes = false;
	private double caixaTotal;

	/*
	 * Para a primeira execucao coloquei que o pagamento ja foi efetuado e que
	 * foi feito na data de 01/06/2010 :D
	 */

	/**
	 * Metodo para efetuar pagamento no Mes
	 * 
	 * @return String contendo a Folha de Pagamento do mes
	 * @throws Exception
	 *             Lanca excecao se ja tiver feito pagamento esse mes
	 */

	public String efetuaPagamentoNoMes() throws Exception {

		atualizaPagamento();

		if (pagouNesseMes)
			throw new Exception("Pagamento ja efetuado esse mes");

		double despesas = 0;
		double salarioFuncionario;

		String folhaDePagamento = "";

		HashMap<String, Double> listagemPagamentos = controladorFuncionarios
				.listaTotaisDeVendas();

		for (String informacaoFuncionario : listagemPagamentos.keySet()) {

			salarioFuncionario = SALARIO_DEFAULT + COMISSAO
					* listagemPagamentos.get(informacaoFuncionario);

			despesas += salarioFuncionario;

			folhaDePagamento += informacaoFuncionario + " - Salario: "
					+ salarioFuncionario + "\n";
		}

		folhaDePagamento += "Saldo Anterior: " + caixaTotal + " - Despesas: "
				+ despesas;

		caixaTotal -= despesas;

		folhaDePagamento += " - Novo Saldo: " + caixaTotal;

		ultimoPagamento = new GregorianCalendar();
		pagouNesseMes = true;

		return folhaDePagamento;
	}

	private void atualizaPagamento() {
		GregorianCalendar hoje = new GregorianCalendar();

		// Se nao estiverem no Mes do Ano igual
		// Entao quer dizer que ainda nao fez o pagamento do mes

		if (!(ultimoPagamento.get(Calendar.YEAR) == hoje.get(Calendar.YEAR) && ultimoPagamento
				.get(Calendar.MONTH) == hoje.get(Calendar.MONTH))) {
			// Ainda nao pagou nesse mes
			pagouNesseMes = false;
		}
	}

	/* PEDIDOS */

	/**
	 * Metodo que adiciona um pedido ao Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel pedido
	 * @param cpf
	 *            CPF do Cliente que fez a solicitacao
	 * @throws Exception
	 *             Lanca excecao caso o imovel nao exista, o cliente nao exista
	 *             ou o imovel ja tenha sido pedido
	 */
	public void adicionaPedido(String registroImovel, String cpf)
			throws Exception {

		Imovel imovelPedido = controladorImoveis.getImovel(registroImovel);
		Cliente clienteQueSolicitou = controladorClientes.getCliente(cpf);

		if (imovelPedido == null || clienteQueSolicitou == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (listaPedidos.containsKey(imovelPedido))
			throw new Exception("Imovel ja pedido");

		clienteQueSolicitou.fazPedido(imovelPedido);
		imovelPedido.setEstadoDoImovel(EstadoImovel.PEDIDO);
		listaPedidos.put(imovelPedido, clienteQueSolicitou);
	}

	/**
	 * Metodo que realiza a compra de um pedido ao Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que vai ser realizada a compra
	 * @param creciFuncionario
	 *            Creci do funcionario que efetuou a compra
	 * @throws Exception
	 *             Lanca excecao caso o Imovel ou funcionario nao exista, ou
	 *             caso o Imovel nao tenha sido pedido
	 */
	public void efetuaPedido(String registroImovel, String creciFuncionario)
			throws Exception {

		Imovel imovelASerEfetuado = controladorImoveis
				.getImovel(registroImovel);
		Funcionario funcionarioQueEfetuouACompra = controladorFuncionarios
				.getFuncionario(creciFuncionario);

		if (imovelASerEfetuado == null || funcionarioQueEfetuouACompra == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (imovelASerEfetuado.getEstadoDoImovel() != EstadoImovel.PEDIDO)
			throw new Exception("Imovel nao pedido");

		funcionarioQueEfetuouACompra.addImovelVendido(imovelASerEfetuado);
		imovelASerEfetuado.setEstadoDoImovel(EstadoImovel.VENDIDO);
		caixaTotal += imovelASerEfetuado.getValor();
	}

	/**
	 * Metodo que realiza a compra de um pedido ao Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que foi pedido
	 * @throws Exception
	 *             Lanca Excecao caso o imovel nao exista ou nao tenha sido
	 *             pedido
	 */
	public void removePedido(String registroImovel) throws Exception {
		Imovel imovelDoPedido = controladorImoveis.getImovel(registroImovel);

		if (imovelDoPedido == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (!(listaPedidos.containsKey(imovelDoPedido)))
			throw new Exception("Imovel nao pedido");

		imovelDoPedido.setEstadoDoImovel(EstadoImovel.A_VENDA);
		listaPedidos.get(imovelDoPedido).removePedido(imovelDoPedido);
		listaPedidos.remove(imovelDoPedido);
	}

	/* Listagem de Pedidos */

	/**
	 * Metodo que lista os pedidos
	 * 
	 * @return String com os pedidos
	 */
	public String listagemDePedido() {

		String saida = "";

		for (Imovel imovelPedido : listaPedidos.keySet()) {

			Cliente clientePedinte = listaPedidos.get(imovelPedido);

			saida += imovelPedido.getRegistroImovel() + " - "
					+ imovelPedido.getNome() + " Valor: "
					+ imovelPedido.getValor() + "\n" + "Cliente que pediu: "
					+ clientePedinte.getNome() + " - CPF: "
					+ clientePedinte.getCpf() + "\n\n";

		}

		return saida;

	}

	/* Login */

	/**
	 * Metodo Login - Efetua login baseado no login, senha e Tipo do Usuario
	 * 
	 * @param login
	 *            Login do Usuario
	 * @param senha
	 *            Senha do Usuario
	 * @param tipoDeUsuario
	 *            Tipo de Usuario que vai ser logado
	 * @return True - Se o usuario efetuou login com sucesso <br>
	 *         False - Se o usuario nao conseguiu logar
	 */
	public boolean login(String login, String senha, TipoLogin tipoDeUsuario) {

		if (tipoDeUsuario == TipoLogin.ADMINISTRADOR) {
			if (login.equals("admin") && senha.equals("admin"))
				return true;
			return false;

		} else if (tipoDeUsuario == TipoLogin.FUNCIONARIO) {

			if (controladorFuncionarios.login(login, senha)) {
				return true;
			}
			return false;

		} else {

			if (controladorClientes.login(login, senha)) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Metodo informador se o pagamento do mes ja foi efetuado
	 * 
	 * @return Boolean<br>
	 *         True - Se o pagamento desse mes foi efetuado<br>
	 *         False - Se ainda nao foi efetuado
	 */
	public boolean pagouNesseMes() {
		atualizaPagamento();
		return pagouNesseMes;
	}

	/**
	 * Metodo Acessador do Caixa Total do Sistema
	 * 
	 * @return Caixa da Empresa
	 */
	public double caixa() {
		return caixaTotal;
	}

	/**
	 * Metodo acessador do Contrador de Imoveis do Sistema
	 * @return the controladorImoveis
	 */
	public ControladorImovel controladorImoveis() {
		return controladorImoveis;
	}

	/**
	 * Metodo acessador do Contrador de Clientes do Sistema
	 * @return the controladorClientes
	 */
	public ControladorCliente controladorClientes() {
		return controladorClientes;
	}

	/**
	 * Metodo acessador do Contrador de Funcionarios do Sistema
	 * @return the controladorFuncionarios
	 */
	public ControladorFuncionario controladorFuncionarios() {
		return controladorFuncionarios;
	}

}
