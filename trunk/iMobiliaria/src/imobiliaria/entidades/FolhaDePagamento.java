package imobiliaria.entidades;

import java.util.HashMap;

public class FolhaDePagamento {
	
	private HashMap<String, Double> vendasFuncionarios;
	private double saldoAnterior;
	private double despesas;
	private double novoSaldo;
	
	public FolhaDePagamento(HashMap<String, Double> vendasFuncionarios, double saldoAnterior,
			double despesas, double novoSaldo) {
		
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

		folhaDePagamento += "Saldo Anterior: " + saldoAnterior + " - Despesas: "
				+ despesas + " - Novo Saldo: " + novoSaldo;

		return folhaDePagamento;
	}
	
	

}
