package imobiliaria.entidades;

import imobiliaria.util.FormataEntrada;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Classe Transacao que guarda informacoes de uma Transacao Financeira
 * 
 * @author Yuri @ IT02
 */
public class Transacao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Funcionario vendedor;
	private Cliente comprador;
	private double valor;
	private String data;
	private int registroTransacao;

	private static int criadorRegistroTransacao = 0;

	/**
	 * Construtor da Classe
	 * 
	 * @param vendedor
	 *            Vendedor do imovel
	 * @param comprador
	 *            Comprador do imovel
	 * @param valor
	 *            Valor d'a Transacao
	 */
	public Transacao(Funcionario vendedor, Cliente comprador, double valor) {

		this.vendedor = vendedor;
		this.comprador = comprador;
		this.valor = valor;
		data = FormataEntrada.data(new GregorianCalendar());
		registroTransacao = criadorRegistroTransacao++;
	}

	/**
	 * Metodo que exibe informacao completa da Transacao
	 * 
	 * @return String contendo informacoes completas da Transacao
	 */
	public String exibeInformacao() {
		return "Transacao de Registro: " + registroTransacao + "\n"
				+ "Vendedor:" + vendedor.getNome() + " - CRECI:"
				+ vendedor.getCreci() + "\n" + "Comprador:" + comprador
				+ " - CPF: " + comprador.getCpf() + "\n"
				+ "Valor da Transacao: " + valor + "\n" + "Data: " + data;
	}

	/**
	 * Metodo acessador do Registro da Transacao
	 * 
	 * @return Registro da Transacao
	 */
	public int getRegistroTransacao() {
		return registroTransacao;
	}

	/**
	 * toString da Transacao contendo as informacoes em uma linha
	 */
	@Override
	public String toString() {
		return registroTransacao + " - " + data + " - " + vendedor.getNome()
				+ " - " + comprador.getNome() + " - " + valor;
	}

	/**
	 * Metodo acessador do Criador de Registro da classe Transacao
	 * 
	 * @return O Criador de Registro da Classe
	 */
	public static int getCriadorRegistroTransacao() {
		return criadorRegistroTransacao;
	}

	/**
	 * @param criadorRegistroTransacao
	 *            Novo Numero para o Criador de Registro
	 */
	public static void setCriadorRegistroTransacao(int criadorRegistroTransacao) {
		Transacao.criadorRegistroTransacao = criadorRegistroTransacao;
	}
}
