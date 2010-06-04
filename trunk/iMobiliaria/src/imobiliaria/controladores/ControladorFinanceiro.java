package imobiliaria.controladores;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Transacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

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

	public String efetuaPagamentoNoMes(
			HashMap<String, Double> vendasFuncionarios) throws Exception {

		atualizaPagamento();

		if (pagouNesseMes)
			throw new Exception("Pagamento ja efetuado esse mes");

		double despesas = 0;
		double salarioFuncionario;

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
	public boolean adicionaTransacao(Cliente comprador, Funcionario vendedor,
			double valor) {

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

	private String listaTransacoes(ArrayList<Transacao> transacoes) {
		String saida = "";

		for (Transacao t : transacoes) {
			saida += t.exibeInformacao() + "\n";
		}
		return saida;
	}
}
