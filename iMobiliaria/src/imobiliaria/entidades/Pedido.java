package imobiliaria.entidades;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author yuri
 *
 */
public class Pedido implements Comparable<Object> {
	
	private Calendar dataDoPedido;
	private Imovel imovel;
	private Cliente comprador;
	
	public Pedido(Imovel imovel, Cliente comprador) {
		
		this.imovel = imovel;
		this.comprador = comprador;
		dataDoPedido = new GregorianCalendar();
	}

	@Override
	public int compareTo(Object obj) {
		if (!(obj instanceof Pedido))
			throw new IllegalArgumentException();
		
		Pedido outroPedido = (Pedido) obj;
		
		return dataDoPedido.compareTo(outroPedido.getDataDoPedido());
	}

	/**
	 * @return the dataDoPedido
	 */
	public Calendar getDataDoPedido() {
		return dataDoPedido;
	}

	/**
	 * @return the imovel
	 */
	public Imovel getImovel() {
		return imovel;
	}

	/**
	 * @return the comprador
	 */
	public Cliente getComprador() {
		return comprador;
	}
}
