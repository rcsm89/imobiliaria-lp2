package imobiliaria.controladores;

import imobiliara.auxiliar.EstadoImovel;
import imobiliara.auxiliar.TipoContratual;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Pedido;
import imobiliaria.exceptions.FuncionarioNotFoundException;
import imobiliaria.exceptions.ImovelInvalidoException;
import imobiliaria.exceptions.PedidoNotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Classe que Controla os Pedidos de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version version 02
 */

public class ControladorPedidos implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos

	private TreeSet<Pedido> listaPedidos = new TreeSet<Pedido>();

	private static ControladorPedidos controladorPedidos = new ControladorPedidos();

	// Construtor

	/**
	 * Construtor privado para nao ocorrer instanciacoes da classe <br>
	 * fora da classe.
	 */
	private ControladorPedidos() {
	}

	/**
	 * Metodo que retorna uma instacia de pedidos
	 * 
	 * @return Controlador de Pedidos
	 */
	public static ControladorPedidos getInstance() {
		return controladorPedidos;
	}

	/**
	 * Metodo que modifica a instancia unica do Controlador de Pedidos
	 * 
	 * @param controlador
	 *            Nova Instancia para o Controlador
	 */
	public static void setInstance(ControladorPedidos controlador) {
		if (controlador == null) {
			throw new IllegalArgumentException(
					"Controlador de Pedidos invalido");
		}
		controladorPedidos = controlador;
	}

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
	public void adicionaPedido(String registroImovel, String cpf)
			throws Exception {

		Imovel imovelPedido = ControladorImovel.getInstance().getImovel(
				registroImovel);

		Cliente clienteQueSolicitou = ControladorCliente.getInstance()
				.getCliente(cpf);

		if (imovelPedido == null)
			throw new IllegalArgumentException("Registro de Imovel invalido");

		if (clienteQueSolicitou == null)
			throw new IllegalArgumentException("CPF de Cliente invalido");

		if (imovelPedido.getEstadoDoImovel() == EstadoImovel.PEDIDO) {
			throw new ImovelInvalidoException("Imovel ja pedido");
		}

		listaPedidos.add(new Pedido(imovelPedido, clienteQueSolicitou));
	}

	/**
	 * Metodo que realiza a compra de um pedido ao Sistema sem necessidade do
	 * Funcionario
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que foi Pedido
	 * @throws Exception
	 *             Caso o Registro seja invalido ou o Imovel nao tenha sido
	 *             pedido ou seu <br>
	 *             tipo contratual eh de venda.
	 */
	public void efetuaPedido(String registroImovel) throws Exception {
		efetuaPedido(registroImovel, "");
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
	public void efetuaPedido(String registroImovel, String creciFuncionario)
			throws Exception {

		Pedido pedido = getPedido(registroImovel);

		if (pedido == null)
			throw new PedidoNotFoundException("Pedido nao encontrado");

		if (pedido.getImovel().getTipoContratual() == TipoContratual.VENDA) {

			Funcionario vendedor = ControladorFuncionario.getInstance()
					.getFuncionarioPorCreci(creciFuncionario);

			if (vendedor == null)
				throw new FuncionarioNotFoundException("Funcionario invalido");

			// A Venda

			efetuaVenda(pedido, vendedor);

		} else {

			// Aluguel

			efetuaAluguel(pedido);

		}
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
			throw new PedidoNotFoundException("Parametros invalidos");

		pedido.getImovel().a_venda();
		listaPedidos.remove(pedido);
	}

	/**
	 * Metodo de Listagem de Pedido de um Cliente
	 * 
	 * @param cpf
	 *            CPF do Cliente
	 * @return Listagem de Pedidos
	 */
	public String listaPedidosDeCliente(String cpf) {

		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		for (Pedido p : listaPedidos) {
			if (p.getComprador().getCpf().equals(cpf)) {
				pedidos.add(p);
			}
		}

		return listaPedidos(pedidos);
	}

	/**
	 * Metodo de Listagem para Todos os Pedidos do Controlador
	 * 
	 * @return Todos os Pedidos do Controlador
	 */
	public String listagemDePedido() {
		return listaPedidos(listaPedidos);
	}

	/* Metodos de Auxilio */

	private String listaPedidos(Collection<Pedido> pedidos) {

		String saida = "";

		Iterator<Pedido> it = pedidos.iterator();

		while (it.hasNext()) {

			Pedido p = it.next();

			saida += p.exibeInformacao() + "\n\n";
		}
		return saida;
	}

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

	private void efetuaVenda(Pedido pedido, Funcionario vendedor)
			throws Exception {

		pedido.getComprador().getHistoricoCompras().addImovel(
				pedido.getImovel());

		ControladorTransacoes.getInstance().adicionaTransacao(
				pedido.getComprador().getCpf(), vendedor.getCreci(),
				String.valueOf(pedido.getImovel().getRegistroImovel()));

		vendedor.addImovelVendido(pedido.getImovel());
		pedido.getImovel().vendido();
		ControladorTransacoes.getInstance().adicionaAoCaixa(
				pedido.getImovel().getValor());
		listaPedidos.remove(pedido);
	}

	private void efetuaAluguel(Pedido pedido) throws Exception {

		ControladorAlugueis.getInstance().adicionaAluguel(
				pedido.getComprador().getCpf(),
				String.valueOf(pedido.getImovel().getRegistroImovel()));

		pedido.getImovel().alugado();

		ControladorTransacoes.getInstance().adicionaAoCaixa(
				pedido.getImovel().getValor());
		
		listaPedidos.remove(pedido);
	}

}
