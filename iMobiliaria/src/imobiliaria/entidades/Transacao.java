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
	private Imovel imovelTransacao;
	private String data;
	private int registroTransacao;

	private static int criadorRegistroTransacao = 0;

	/**
	 * Construtor da Classe
	 * 
	 * @param vendedor
	 *            Vendedor na Transacao
	 * @param comprador
	 *            Comprador na Transacao
	 * @param imovelTransacao
	 *            Imovel da Transacao
	 */
	public Transacao(Funcionario vendedor, Cliente comprador,
			Imovel imovelTransacao) {

		if (vendedor == null || comprador == null || imovelTransacao == null)
			throw new IllegalArgumentException("Parametros invalidos");

		this.vendedor = vendedor;
		this.comprador = comprador;
		this.imovelTransacao = imovelTransacao;
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
				+ "Vendedor: " + vendedor.getNome() + " - CRECI: "
				+ vendedor.getCreci() + "\n" + "Comprador: "
				+ comprador.getNome() + " - CPF: " + comprador.getCpf() + "\n"
				+ "Imovel: " + imovelTransacao.getRegistroImovel() + " - "
				+ imovelTransacao.getNome() + "\n" + "Valor da Transacao: "
				+ imovelTransacao.getValor() + " - Data: " + data;
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
				+ " - " + comprador.getNome() + " - Imovel: "
				+ imovelTransacao.getRegistroImovel() + " - "
				+ imovelTransacao.getValor();
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
