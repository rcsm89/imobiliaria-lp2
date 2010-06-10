package imobiliaria.entidades;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe que guarda informacoes de um Pedido
 * 
 * @author Yuri Farias
 * @version IT 02
 */
public class Pedido implements Comparable<Object> {

	private Calendar dataDoPedido;
	private Imovel imovel;
	private Cliente comprador;

	/**
	 * Construtor da Classe
	 * 
	 * @param imovel
	 *            Imovel que foi pedido
	 * @param comprador
	 *            Comprador do Imovel
	 * @throws Exception
	 */
	public Pedido(Imovel imovel, Cliente comprador) throws Exception {

		String msgErro = "";

		if (imovel == null
				|| imovel.getEstadoDoImovel() != EstadoImovel.A_VENDA)
			msgErro += "Imovel invalido";

		if (comprador == null)
			msgErro += "Comprador invalido";

		if (!msgErro.equals(""))
			throw new IllegalArgumentException(msgErro);

		this.imovel = imovel;
		this.comprador = comprador;
		dataDoPedido = new GregorianCalendar();

		imovel.pedido();
		comprador.fazPedido(imovel);
	}

	/**
	 * Metodo acessador da Data do Pedido
	 * 
	 * @return the dataDoPedido
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
	public Cliente getComprador() {
		return comprador;
	}
	
	@Override
	public int compareTo(Object obj) {
		if (!(obj instanceof Pedido))
			throw new IllegalArgumentException();

		Pedido outroPedido = (Pedido) obj;

		return dataDoPedido.compareTo(outroPedido.getDataDoPedido());
	}

}