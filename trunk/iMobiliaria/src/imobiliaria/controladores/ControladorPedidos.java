package imobiliaria.controladores;

import imobiliaria.auxiliar.EstadoImovel;
import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Pedido;
import imobiliaria.exceptions.ClienteNotFoundException;
import imobiliaria.exceptions.FuncionarioNotFoundException;
import imobiliaria.exceptions.ImovelInvalidoException;
import imobiliaria.exceptions.ImovelNotFoundException;
import imobiliaria.exceptions.PedidoNotFoundException;
import imobiliaria.exceptions.ValorInvalidoException;
import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Classe que Controla os Pedidos de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version IT02
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
	 * @throws ClienteNotFoundException
	 *             Se o Cliente nao for Encontrado
	 * @throws ImovelInvalidoException
	 *             Se o Imovel for Invalido (Nao estiver a venda)
	 * @throws ImovelNotFoundException
	 *             Se o Imovel nao for encontrado
	 */
	public void adicionaPedido(String registroImovel, String cpf)
			throws ImovelInvalidoException, ClienteNotFoundException,
			ImovelNotFoundException {

		Imovel imovelPedido = ControladorImovel.getInstance().getImovel(
				registroImovel);

		Cliente clienteQueSolicitou = ControladorCliente.getInstance()
				.getCliente(cpf);

		if (imovelPedido == null)
			throw new ImovelNotFoundException("Registro de Imovel invalido");

		if (clienteQueSolicitou == null)
			throw new ClienteNotFoundException("CPF de Cliente invalido");

		if (imovelPedido.getEstadoDoImovel() != EstadoImovel.A_VENDA)
			throw new ImovelInvalidoException("Imovel precisa estar a venda");

		listaPedidos.add(new Pedido(imovelPedido, clienteQueSolicitou));
		imovelPedido.pedido();
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
		if (VerificaInvalido.basico(registroImovel)){
			throw new IllegalArgumentException("Registro invalido");
		}

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
		if (VerificaInvalido.basico(cpf)){
			throw new IllegalArgumentException("CPF invalido");
		}

		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

		for (Pedido p : listaPedidos) {
			if (p.getCliente().getCpf().equals(cpf)) {
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
			throws ValorInvalidoException, ImovelInvalidoException {

		pedido.getCliente().getHistoricoCompras().addImovel(
				pedido.getImovel());

		ControladorTransacoes.getInstance().adicionaTransacao(
				pedido.getCliente().getCpf(), vendedor.getCreci(),
				String.valueOf(pedido.getImovel().getRegistroImovel()));

		vendedor.addImovelVendido(pedido.getImovel());
		
		pedido.getImovel().vendido();
		
		ControladorTransacoes.getInstance().adicionaAoCaixa(
				pedido.getImovel().getValor());
		
		listaPedidos.remove(pedido);
	}

	private void efetuaAluguel(Pedido pedido) throws ClienteNotFoundException,
	ImovelInvalidoException, ImovelNotFoundException, ValorInvalidoException {

		ControladorAlugueis.getInstance().adicionaAluguel(
				pedido.getCliente().getCpf(),
				String.valueOf(pedido.getImovel().getRegistroImovel()));

		pedido.getImovel().alugado();
		
		ControladorTransacoes.getInstance().adicionaAoCaixa(
				pedido.getImovel().getValor());
		
		listaPedidos.remove(pedido);
	}

}
