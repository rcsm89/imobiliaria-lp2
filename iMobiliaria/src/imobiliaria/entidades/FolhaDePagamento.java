package imobiliaria.entidades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Classe que guarda as informacoes de uma Folha de Pagamento
 * 
 * @version IT02
 */
public class FolhaDePagamento implements Serializable {

	private HashMap<String, Double> vendasFuncionarios;
	private double saldoAnterior;
	private double novoSaldo;
	private double despesas;

	/**
	 * Construtor da Classe
	 * 
	 * @param vendasFuncionarios
	 *            HashMap contendo a Informacao do Funcionario e seu salario
	 * @param novoSaldo
	 *            Novo Saldo do Caixa
	 */
	public FolhaDePagamento(HashMap<String, Double> vendasFuncionarios,
			double novoSaldo) {
		if (vendasFuncionarios == null)
			throw new IllegalArgumentException("Parametros invalidos");

		this.vendasFuncionarios = vendasFuncionarios;
		this.novoSaldo = novoSaldo;
		despesas = calculaDespesas();
		saldoAnterior = novoSaldo + despesas;
	}

	public String getFolhaDePagamentoString() {

		String folhaDePagamento = "";
		
		TreeMap<String, Double> vendas = new TreeMap<String, Double>();
		vendas.putAll(vendasFuncionarios);
		
		for (String informacaoFuncionario : vendas.keySet()) {

			folhaDePagamento += informacaoFuncionario + " - Salario: "
					+ vendasFuncionarios.get(informacaoFuncionario) + "\n";
		}

		folhaDePagamento += "Saldo Anterior: " + saldoAnterior
				+ " - Despesas: " + despesas + " - Novo Saldo: " + novoSaldo;

		return folhaDePagamento;
	}
	
	
	private double calculaDespesas() {
		double despesas = 0;
		for (Double valor : vendasFuncionarios.values()) {
			despesas += valor;
		}
		return despesas;
	}

}
