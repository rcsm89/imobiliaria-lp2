package imobiliaria.controladores;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.FolhaDePagamento;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Transacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Classe que ira ser o Controlador de Transacoes do Sistema
 * 
 * @author Yuri Farias
 * @version IT02
 */
public class ControladorTransacoes implements Serializable {

	private static ControladorTransacoes controladorTransacoesUnico = new ControladorTransacoes();

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

	private ControladorTransacoes() {
	}

	/**
	 * Metodo acessador da unica instancia do Controlador de Transacoes
	 * 
	 * @return Controlador de Transacoes Unico
	 */
	public static ControladorTransacoes getInstance() {
		return controladorTransacoesUnico;
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
		atualizaDataDePagamento();
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

	public FolhaDePagamento efetuaPagamentoNoMes() throws Exception {

		atualizaDataDePagamento();

		if (pagouNesseMes)
			throw new Exception("Pagamento ja efetuado esse mes");

		double despesas = 0;
		double salarioFuncionario;

		// Cria a Folha de Pagamento

		HashMap<String, Double> vendasFuncionarios = ControladorFuncionario
				.getInstance().listaTotaisDeVendas();

		HashMap<String, Double> salarioFuncionarios = new HashMap<String, Double>();

		for (String informacaoFuncionario : vendasFuncionarios.keySet()) {

			salarioFuncionario = SALARIO_DEFAULT + COMISSAO
					* vendasFuncionarios.get(informacaoFuncionario);

			despesas += salarioFuncionario;

			salarioFuncionarios.put(informacaoFuncionario, salarioFuncionario);
		}

		removeDoCaixa(despesas);
		resetaTransacoeMensais();
		atualizaPagamentoParaAgora();

		return new FolhaDePagamento(salarioFuncionarios, caixa());
	}

	// Transacoes

	/**
	 * Metodo que adiciona uma Transacao ao Controlador
	 * 
	 * @param cpfComprador
	 *            CPF do Comprador da Transacao
	 * @param creciVendedor
	 *            Creci do Vendedor da Transacao
	 * @param registroImovel
	 *            Registro do Imovel da Transacao que foi vendido
	 * @return True - Se a transacao for adicionada com sucesso <br>
	 *         False - Se a transacao nao for adicionada
	 */
	public boolean adicionaTransacao(String cpfComprador, String creciVendedor,
			String registroImovel) {

		Cliente comprador = ControladorCliente.getInstance().getCliente(
				cpfComprador);
		
		Funcionario vendedor = ControladorFuncionario.getInstance()
				.getFuncionarioPorCreci(creciVendedor);
		
		Imovel imovel = ControladorImovel.getInstance().getImovel(registroImovel);

		Transacao transacao = new Transacao(vendedor, comprador, imovel);

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
				
				if (logsFinanceirosMensal.contains(t))
					logsFinanceirosMensal.remove(t);
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
		return listagemDasTransacoes(logsFinanceiros);
	}

	/**
	 * Metodo de listagem das Transacoes Mensais do Controlador
	 * 
	 * @return String contendo informacoes de todas transacoes mensais
	 */
	public String listaTransacoesMensais() {
		return listagemDasTransacoes(logsFinanceirosMensal);
	}

	/**
	 * Metodo que atualiza o Controlador de Transacoes
	 */
	public void atualizaControlador() {
		atualizaDataDePagamento();

	}

	/* Metodos Privados */

	private void atualizaDataDePagamento() {
		GregorianCalendar hoje = new GregorianCalendar();

		// Se nao estiverem no Mes do Ano igual
		// Entao quer dizer que ainda nao fez o pagamento do mes

		if (!(ultimoPagamento.get(Calendar.YEAR) == hoje.get(Calendar.YEAR) && ultimoPagamento
				.get(Calendar.MONTH) == hoje.get(Calendar.MONTH))) {

			if (pagouNesseMes == true)
				adicionaAoCaixa(ControladorAlugueis.getInstance()
						.getValorTotalDeAlugueis());

			pagouNesseMes = false;
		}
	}

	private String listagemDasTransacoes(ArrayList<Transacao> transacoes) {
		String saida = "";

		for (Transacao t : transacoes) {
			saida += t.exibeInformacao() + "\n";
		}
		return saida;
	}

	private void atualizaPagamentoParaAgora() {
		ultimoPagamento = new GregorianCalendar();
		pagouNesseMes = true;
	}

	private void resetaTransacoeMensais() {
		logsFinanceirosMensal.clear();
	}

}
