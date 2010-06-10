package imobiliaria.entidades;

import java.util.HashMap;

/**
 * Classe que guarda as informacoes de uma Folha de Pagamento
 * 
 * @author Yuri
 * @version IT02
 */
public class FolhaDePagamento {

	private HashMap<String, Double> vendasFuncionarios;
	private double saldoAnterior;
	private double despesas;
	private double novoSaldo;

	/**
	 * Construtor da Classe
	 * 
	 * @param vendasFuncionarios
	 *            HashMap contendo a Informacao do Funcionario e seu salario
	 * @param saldoAnterior
	 *            Saldo Anterior do Caixa
	 * @param despesas
	 *            Despesas do Caixa
	 * @param novoSaldo
	 *            Novo Saldo do Caixa
	 */
	public FolhaDePagamento(HashMap<String, Double> vendasFuncionarios,
			double saldoAnterior, double despesas, double novoSaldo) {

		this.vendasFuncionarios = vendasFuncionarios;
		this.saldoAnterior = saldoAnterior;
		this.despesas = despesas;
		this.novoSaldo = novoSaldo;
	}

	public String getFolhaDePagamentoString() {

		String folhaDePagamento = "";

		for (String informacaoFuncionario : vendasFuncionarios.keySet()) {

			folhaDePagamento += informacaoFuncionario + " - Salario: "
					+ vendasFuncionarios.get(informacaoFuncionario) + "\n";
		}

		folhaDePagamento += "Saldo Anterior: " + saldoAnterior
				+ " - Despesas: " + despesas + " - Novo Saldo: " + novoSaldo;

		return folhaDePagamento;
	}

}
