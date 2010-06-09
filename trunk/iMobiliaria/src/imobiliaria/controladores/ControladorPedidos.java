package imobiliaria.controladores;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.EstadoImovel;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Pedido;
import imobiliaria.entidades.TipoContratual;

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
	private TreeSet<Pedido> listaPedidos = new TreeSet<Pedido>();

	// Variaveis de referencia para os outros controladores
	private ControladorImovel controladorImoveis;
	private ControladorCliente controladorClientes;
	private ControladorFuncionario controladorFuncionarios;
	private ControladorFinanceiro controladorFinanceiro;

	public ControladorPedidos(ControladorImovel controladorImoveis,
			ControladorCliente controladorClientes,
			ControladorFuncionario controladorFuncionarios,
			ControladorFinanceiro controladorFinanceiro) {

		this.controladorClientes = controladorClientes;
		this.controladorImoveis = controladorImoveis;
		this.controladorFinanceiro = controladorFinanceiro;
		this.controladorFuncionarios = controladorFuncionarios;
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
	public void efetuaPedido(String registroImovel, String creciFuncionario)
			throws Exception {

		Pedido pedido = getPedido(registroImovel);

		Funcionario vendedor = controladorFuncionarios
				.getFuncionario(creciFuncionario);

		if (vendedor == null || pedido == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (pedido.getImovel().getTipoContratual() == TipoContratual.VENDA) {

			// A Venda

			pedido.getComprador().getHistoricoCompras().addImovel(
					pedido.getImovel());

			controladorFinanceiro.adicionaTransacao(pedido.getComprador()
					.getCpf(), vendedor.getCreci(), pedido.getImovel()
					.getValor());

			vendedor.addImovelVendido(pedido.getImovel());
			pedido.getImovel().vendido();
			controladorFinanceiro
					.adicionaAoCaixa(pedido.getImovel().getValor());
			listaPedidos.remove(pedido);

		} else {

			// Aluguel

			// Adiciona Aluguel na lista de Alugueis do Cliente (FAZER!)

			controladorFinanceiro.adicionaAluguel(pedido.getComprador(),
					vendedor, pedido.getImovel());
			pedido.getImovel().alugado();
			
			// CONTINUA AKI!

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

}
