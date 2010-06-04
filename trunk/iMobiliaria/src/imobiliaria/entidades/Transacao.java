package imobiliaria.entidades;

import imobiliaria.util.FormataEntrada;

import java.util.GregorianCalendar;

public class Transacao {
	
	private Funcionario vendedor;
	private Cliente comprador;
	private double valor;
	private String data;
	private int registroTransacao;
	
	private static int criadorRegistroTransacao = 0;
	
	
	public Transacao(Funcionario vendedor, Cliente comprador, double valor) {
		
		this.vendedor = vendedor;
		this.comprador = comprador;
		this.valor = valor;
		data = FormataEntrada.data(new GregorianCalendar());
		registroTransacao = criadorRegistroTransacao++;
	}
	
	public String exibeInformacao() {
		return "Transacao de Registro: " + registroTransacao + "\n" +
				"Vendedor:" + vendedor.getNome() + " - CRECI:" + vendedor.getCreci() + "\n" +
				"Comprador:" + comprador + " - CPF: " + comprador.getCpf() + "\n" +
				"Valor da Transacao: " + valor + "\n" +
				"Data: " + data;
	}
	
	public int getRegistroTransacao() {
		return registroTransacao;
	}
	
	@Override
	public String toString() {
		return registroTransacao + " - " + data + " - " + vendedor.getNome() + " - " + comprador.getNome() + " - " + valor;
	}

	/**
	 * @return the criadorRegistroTransacao
	 */
	public static int getCriadorRegistroTransacao() {
		return criadorRegistroTransacao;
	}


	/**
	 * @param criadorRegistroTransacao the criadorRegistroTransacao to set
	 */
	public static void setCriadorRegistroTransacao(int criadorRegistroTransacao) {
		Transacao.criadorRegistroTransacao = criadorRegistroTransacao;
	}
	
	
	
	

}
