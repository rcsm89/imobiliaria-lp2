package imobiliaria.controladores;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.EstadoImovel;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Pedido;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Classe que Controla os Pedidos de um Sistema Imobiliario
 * @author Yuri
 * @version IT02
 */

public class ControladorPedidos implements Serializable {

	private static final long serialVersionUID = 1L;
	private TreeSet<Pedido> listaPedidos = new TreeSet<Pedido>();

	/**
	 * Metodo que adiciona um pedido ao Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel pedido
	 * @param cpf
	 *            CPF do Cliente que fez a solicitacao
	 * @throws Exception
	 *             Lanca excecao caso o imovel nao exista, o cliente nao exista
	 *             ou o imovel ja tenha sido pedido
	 */
	public void adicionaPedido(String registroImovel, String cpf,
			ControladorImovel controladorImoveis,
			ControladorCliente controladorClientes) throws Exception {

		Imovel imovelPedido = controladorImoveis.getImovel(registroImovel);
		Cliente clienteQueSolicitou = controladorClientes.getCliente(cpf);

		if (imovelPedido == null || clienteQueSolicitou == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (imovelPedido.getEstadoDoImovel() == EstadoImovel.PEDIDO) {
			throw new Exception("Imovel ja pedido");
		}

		clienteQueSolicitou.fazPedido(imovelPedido);
		imovelPedido.pedido();
		listaPedidos.add(new Pedido(imovelPedido, clienteQueSolicitou));
	}

	/**
	 * Metodo que realiza a compra de um pedido ao Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que vai ser realizada a compra
	 * @param creciFuncionario
	 *            Creci do funcionario que efetuou a compra
	 * @throws Exception
	 *             Lanca excecao caso o Imovel ou funcionario nao exista, ou
	 *             caso o Imovel nao tenha sido pedido
	 */
	public void efetuaPedido(String registroImovel, String creciFuncionario,
			ControladorFuncionario controladorFuncionarios,
			ControladorFinanceiro controladorFinanceiro) throws Exception {
		
		Pedido pedido = getPedido(registroImovel);
		
		Funcionario funcionarioQueEfetuouACompra = controladorFuncionarios
				.getFuncionario(creciFuncionario);

		if (funcionarioQueEfetuouACompra == null || pedido == null)
			throw new IllegalArgumentException("Parametros invalidos");

		controladorFinanceiro.adicionaTransacao(pedido.getComprador(),
				funcionarioQueEfetuouACompra,
				pedido.getImovel().getValor());

		funcionarioQueEfetuouACompra.addImovelVendido(pedido.getImovel());

		pedido.getImovel().vendido();

		controladorFinanceiro.adicionaAoCaixa(pedido.getImovel().getValor());

		listaPedidos.remove(pedido);
	}

	/**
	 * Metodo que realiza a compra de um pedido ao Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que foi pedido
	 * @throws Exception
	 *             Lanca Excecao caso o imovel nao exista ou nao tenha sido
	 *             pedido
	 */
	public void removePedido(String registroImovel) throws Exception {
		
		
		Pedido pedido = getPedido(registroImovel);
		
		if (pedido == null)
			throw new IllegalArgumentException("Parametros invalidos");

		pedido.getImovel().a_venda();
		pedido.getComprador().removePedido(pedido.getImovel());
		listaPedidos.remove(pedido);
	}

	/**
	 * Metodo que lista os pedidos
	 * 
	 * @return String com os pedidos
	 */
	public String listagemDePedido() {

		String saida = "";

		Iterator<Pedido> it = listaPedidos.iterator();
		
		while (it.hasNext()) {
			
			Pedido p = it.next();
			
			saida += p.getImovel().getRegistroImovel() + " - "
					+ p.getImovel().getNome() + " Valor: "
					+ p.getImovel().getValor() + "\n" + "Cliente que pediu: "
					+ p.getComprador().getNome() + " - CPF: "
					+ p.getComprador().getCpf() + "\n\n";

		}
		return saida;
	}
	
	
	/* Metodos de Auxilio */
	
	private Pedido getPedido(String registroImovel) {
		int registro;
		try {
			registro = Integer.parseInt(registroImovel);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Registro invalido!");
		}
		
		for (Pedido p : listaPedidos) {
			if (p.getImovel().getRegistroImovel() == registro) {
				return p;
			}
		}
		return null;
	}

}
