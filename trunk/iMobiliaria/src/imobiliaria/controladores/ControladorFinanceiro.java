package imobiliaria.controladores;

import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.EstadoImovel;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Transacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Classe que Controla Transacoes de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version IT02
 */

public class ControladorFinanceiro implements Serializable {

	private static final long serialVersionUID = 1L;
	private final double SALARIO_DEFAULT = 1500;
	private final double COMISSAO = 0.03;

	/*
	 * Assim que o Controlador eh iniciado ele ja comeca com o primeiro
	 * pagamento efetuado
	 */

	private Calendar ultimoPagamento = new GregorianCalendar();
	private boolean pagouNesseMes = true;
	private double caixaTotal;
	private ArrayList<Transacao> logsFinanceiros = new ArrayList<Transacao>();
	private ArrayList<Transacao> logsFinanceirosMensal = new ArrayList<Transacao>();
	private ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();

	private ControladorFuncionario controladorFuncionario;
	private ControladorCliente controladorClientes;
	private ControladorImovel controladorImoveis;

	public ControladorFinanceiro(ControladorFuncionario controladorFuncionario,
			ControladorCliente controladorClientes,
			ControladorImovel controladorImoveis) {

		this.controladorFuncionario = controladorFuncionario;
		this.controladorClientes = controladorClientes;
		this.controladorImoveis = controladorImoveis;

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
	 * Metodo que adiciona valores ao caixa
	 * 
	 * @param valor
	 *            Valor a ser adicionado
	 */

	public void adicionaAoCaixa(double valor) {
		caixaTotal += valor;
	}

	/**
	 * Metodo que remove valores do caixa
	 * 
	 * @param valor
	 *            Valor a ser removido
	 */

	public void removeDoCaixa(double valor) {
		caixaTotal -= valor;
	}

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

		HashMap<String, Double> vendasFuncionarios = controladorFuncionario
				.listaTotaisDeVendas();

		String folhaDePagamento = "";

		for (String informacaoFuncionario : vendasFuncionarios.keySet()) {

			salarioFuncionario = SALARIO_DEFAULT + COMISSAO
					* vendasFuncionarios.get(informacaoFuncionario);

			despesas += salarioFuncionario;

			folhaDePagamento += informacaoFuncionario + " - Salario: "
					+ salarioFuncionario + "\n";
		}

		folhaDePagamento += "Saldo Anterior: " + caixaTotal + " - Despesas: "
				+ despesas;

		removeDoCaixa(despesas);

		folhaDePagamento += " - Novo Saldo: " + caixaTotal;

		ultimoPagamento = new GregorianCalendar();
		pagouNesseMes = true;

		return folhaDePagamento;
	}

	// Transacoes

	/**
	 * Metodo que adiciona uma Transacao ao Controlador
	 * 
	 * @param comprador
	 *            Comprador da Transacao
	 * @param vendedor
	 *            Vendedor da Transacao
	 * @param valor
	 *            Valor da Transacao
	 * @return True - Se a transacao for adicionada com sucesso <br>
	 *         False - Se a transacao nao for adicionada
	 */
	public boolean adicionaTransacao(String cpfComprador, String creciVendedor,
			double valor) {

		Cliente comprador = controladorClientes.getCliente(cpfComprador);
		Funcionario vendedor = controladorFuncionario
				.getFuncionarioPorCreci(creciVendedor);

		Transacao transacao = new Transacao(vendedor, comprador, valor);

		if (logsFinanceiros.add(transacao)) {
			if (logsFinanceirosMensal.add(transacao)) {
				return true;
			}
			logsFinanceiros.remove(transacao);
		}
		return false;
	}

	/**
	 * Metodo que remove Transacao do Controlador
	 * 
	 * @param registro
	 *            Registro da Transacao a ser removida
	 */
	public void removeTransacao(int registro) {

		for (Transacao t : logsFinanceiros) {
			if (t.getRegistroTransacao() == registro) {
				logsFinanceiros.remove(t);
				try {
					logsFinanceirosMensal.remove(t);
				} catch (Exception e) {
					continue;
				}
			}
		}
	}

	/**
	 * Metodo que exibe informacoes de uma Transacao
	 * 
	 * @param registro
	 *            Registro da Transacao a ser exibida
	 * @return String contendo informacoes da Transacao
	 */
	public String exibeTransacao(int registro) {

		for (Transacao t : logsFinanceiros) {
			if (t.getRegistroTransacao() == registro)
				return t.exibeInformacao();
		}
		return null;
	}

	/**
	 * Metodo de listagem das Transacoes do Controlador
	 * 
	 * @return String contendo informacoes de todas as transacoes
	 */
	public String listaTransacoes() {
		return listaTransacoes(logsFinanceiros);
	}

	/**
	 * Metodo de listagem das Transacoes Mensais do Controlador
	 * 
	 * @return String contendo informacoes de todas transacoes mensais
	 */
	public String listaTransacoesMensais() {
		return listaTransacoes(logsFinanceirosMensal);
	}

	/**
	 * Metodo que reseta as Transacoes Mensais
	 */
	public void resetaTransacoeMensais() {
		logsFinanceirosMensal.clear();
	}

	public void atualizaControlador() {
		atualizaPagamento();

	}

	/* Metodos de Aluguel */

	public boolean adicionaAluguel(Cliente alugante, Funcionario vendedor, Imovel imovelAlugado) {
		if (alugante == null || vendedor == null || imovelAlugado == null)
			throw new IllegalArgumentException("Parametros invalidos");
		
		Aluguel aluguel = new Aluguel(alugante, vendedor, imovelAlugado);
		
		return alugueis.add(aluguel);
	}
	
	public boolean removeAluguel(Imovel imovelDoAluguel) {
		
		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado() == imovelDoAluguel)
				return alugueis.remove(a);
		}
		return false;
	}

	/* Metodos Privados */

	private void adquireAlugueis() throws Exception {

		Iterator<Imovel> it = controladorImoveis.getImoveis(
				EstadoImovel.ALUGADO).iterator();

		while (it.hasNext()) {
			Imovel i = it.next();
			adicionaAoCaixa(i.getValor());
			adicionaTransacao(i.getClienteDoAluguel().getCpf(), i
					.getVendedorDoAluguel().getCreci(), i.getValor());
			// Apenas Adiciona ao Caixa do Vendedor!11
			// i.getVendedorDoAluguel().addImovelVendido(imovelVendido)

		}
	}

	private void atualizaPagamento() {
		GregorianCalendar hoje = new GregorianCalendar();

		// Se nao estiverem no Mes do Ano igual
		// Entao quer dizer que ainda nao fez o pagamento do mes

		if (!(ultimoPagamento.get(Calendar.YEAR) == hoje.get(Calendar.YEAR) && ultimoPagamento
				.get(Calendar.MONTH) == hoje.get(Calendar.MONTH))) {

			if (pagouNesseMes == true)
				try {
					adquireAlugueis();
				} catch (Exception e) {
					System.out.println("Erro: " + e.getMessage());
				}

			pagouNesseMes = false;
		}
	}

	private String listaTransacoes(ArrayList<Transacao> transacoes) {
		String saida = "";

		for (Transacao t : transacoes) {
			saida += t.exibeInformacao() + "\n";
		}
		return saida;
	}
}
