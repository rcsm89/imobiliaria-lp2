package imobiliaria.entidades;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe que guarda informacoes de um Pedido
 * 
 * @version IT02
 */
public class Pedido implements Comparable<Object> {

	private Calendar dataDoPedido;
	private Cliente cliente;
	private Imovel imovel;

	/**
	 * Construtor da Classe
	 * 
	 * @param imovel
	 *            Imovel que foi pedido
	 * @param comprador
	 *            Comprador do Imovel
	 * @throws IllegalArgumentException
	 *             Caso Imovel ou Cliente sejam null
	 */
	public Pedido(Imovel imovel, Cliente comprador) {

		if (imovel == null || comprador == null)
			throw new IllegalArgumentException("Imovel ou Cliente invalidos");

		dataDoPedido = new GregorianCalendar();
		cliente = comprador;
		this.imovel = imovel;
	}

	/**
	 * Metodo acessador da Data do Pedido
	 * 
	 * @return dataDoPedido
	 */
	public Calendar getDataDoPedido() {
		return dataDoPedido;
	}

	/**
	 * Metodo acessador do Imovel Pedido
	 * 
	 * @return Imovel que foi Pedido
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * Metodo acessador do Comprador do Pedido
	 * 
	 * @return Cliente que fez o Pedido
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Metodo que mostra as informacoes do Pedido
	 * 
	 * @return String contendo informacoes completas do Pedido
	 */
	public String exibeInformacao() {
		return "Imovel: (" + getImovel().getRegistroImovel() + ") "
				+ getImovel().getNome() + " - Valor: " + getImovel().getValor()
				+ "\n" + "Cliente: " + getCliente().getNome() + " ("
				+ getCliente().getCpf() + ")";
	}

	public int compareTo(Object obj) {
		if (!(obj instanceof Pedido))
			throw new IllegalArgumentException();

		Pedido outroPedido = (Pedido) obj;

		return dataDoPedido.compareTo(outroPedido.getDataDoPedido());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pedido))
			throw new IllegalArgumentException();

		Pedido outroPedido = (Pedido) obj;

		return outroPedido.getCliente().equals(cliente)
				&& outroPedido.getImovel().equals(imovel);
	}
}