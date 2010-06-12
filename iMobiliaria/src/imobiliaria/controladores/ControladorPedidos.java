package imobiliaria.controladores;

import imobiliara.auxiliar.TipoContratual;
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
 * 
 * @author Yuri
 * @version IT02
 */

public class ControladorPedidos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Atributos
	
	private TreeSet<Pedido> listaPedidos = new TreeSet<Pedido>();

	private static ControladorPedidos controladorPedidos = new 
		ControladorPedidos();
	
	//Construtor
	
	/**
	 * Construtor privado para nao ocorrer instanciacoes da classe <br>
	 * fora da classe.
	 */
	private ControladorPedidos() {}
	
	/**
	 * Metodo que retorna uma instacia de pedidos
	 * @return Controlador de Pedidos
	 */
	public static ControladorPedidos getInstance(){
		return controladorPedidos;
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
	public void efetuaPedido(String registroImovel, String creciFuncionario)
			throws Exception {

		Pedido pedido = getPedido(registroImovel);

		Funcionario vendedor = ControladorFuncionario.getInstance().
				getFuncionarioPorCreci(creciFuncionario);

		if (vendedor == null || pedido == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (pedido.getImovel().getTipoContratual() == TipoContratual.VENDA) {

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
	
	private void efetuaVenda(Pedido pedido, Funcionario vendedor) throws Exception {
		
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
				pedido.getComprador(), pedido.getImovel());
		
		pedido.getImovel().alugado();
		
		pedido.getComprador().getAlugueis().addImovel(pedido.getImovel());
		
		ControladorTransacoes.getInstance().adicionaAoCaixa(
				pedido.getImovel().getValor());
	}

}
