package imobiliaria.controladores;

import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.EstadoImovel;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Classe que Controla os Pedidos de um Sistema Imobiliario
 * @author Yuri
 * @version IT02
 */

public class ControladorPedidos implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashMap<Imovel, Cliente> listaPedidos = new HashMap<Imovel, Cliente>();

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

		if (listaPedidos.containsKey(imovelPedido)) {
			throw new Exception("Imovel ja pedido");
		}

		clienteQueSolicitou.fazPedido(imovelPedido);
		imovelPedido.pedido();
		listaPedidos.put(imovelPedido, clienteQueSolicitou);
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
			ControladorImovel controladorImoveis,
			ControladorFuncionario controladorFuncionarios,
			ControladorFinanceiro controladorFinanceiro) throws Exception {

		Imovel imovelASerEfetuado = controladorImoveis
				.getImovel(registroImovel);
		Funcionario funcionarioQueEfetuouACompra = controladorFuncionarios
				.getFuncionario(creciFuncionario);

		if (imovelASerEfetuado == null || funcionarioQueEfetuouACompra == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (imovelASerEfetuado.getEstadoDoImovel() != EstadoImovel.PEDIDO)
			throw new Exception("Imovel nao pedido");

		controladorFinanceiro.adicionaTransacao(listaPedidos
				.get(imovelASerEfetuado), funcionarioQueEfetuouACompra,
				imovelASerEfetuado.getValor());

		funcionarioQueEfetuouACompra.addImovelVendido(imovelASerEfetuado);

		imovelASerEfetuado.vendido();

		controladorFinanceiro.adicionaAoCaixa(imovelASerEfetuado.getValor());

		listaPedidos.remove(imovelASerEfetuado);
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
	public void removePedido(String registroImovel,
			ControladorImovel controladorImoveis) throws Exception {
		
		Imovel imovelDoPedido = controladorImoveis.getImovel(registroImovel);

		if (imovelDoPedido == null)
			throw new IllegalArgumentException("Parametros invalidos");

		if (!(listaPedidos.containsKey(imovelDoPedido)))
			throw new Exception("Imovel nao pedido");

		imovelDoPedido.a_venda();
		listaPedidos.get(imovelDoPedido).removePedido(imovelDoPedido);
		listaPedidos.remove(imovelDoPedido);
	}

	/**
	 * Metodo que lista os pedidos
	 * 
	 * @return String com os pedidos
	 */
	public String listagemDePedido() {

		String saida = "";

		for (Imovel imovelPedido : listaPedidos.keySet()) {

			Cliente clientePedinte = listaPedidos.get(imovelPedido);

			saida += imovelPedido.getRegistroImovel() + " - "
					+ imovelPedido.getNome() + " Valor: "
					+ imovelPedido.getValor() + "\n" + "Cliente que pediu: "
					+ clientePedinte.getNome() + " - CPF: "
					+ clientePedinte.getCpf() + "\n\n";

		}
		return saida;
	}

}
