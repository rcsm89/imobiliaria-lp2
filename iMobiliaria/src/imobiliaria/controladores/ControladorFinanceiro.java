package imobiliaria.controladores;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Transacao;
import java.util.ArrayList;


public class ControladorFinanceiro {
	ArrayList<Transacao> logsFinanceiros = new ArrayList<Transacao>();
	ArrayList<Transacao> logsFinanceirosMensal = new ArrayList<Transacao>();
	
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
	
	public String exibeTransacao(int registro) {
		
		for (Transacao t : logsFinanceiros) {
			if (t.getRegistroTransacao() == registro)
				return t.exibeInformacao();
		}
		return null;
	}
	
	public String listaTransacoes() {
		return listaTransacoes(logsFinanceiros);
	}
	
	public String listaTransacoesMensais() {
		return listaTransacoes(logsFinanceirosMensal);
	}
	
	private String listaTransacoes(ArrayList<Transacao> transacoes) {
		String saida = "";
		
		for (Transacao t : transacoes) {
			saida += t.exibeInformacao() + "\n";
		}
		return saida;
	}
	
}
