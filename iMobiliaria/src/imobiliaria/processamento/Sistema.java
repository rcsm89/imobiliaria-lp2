package imobiliaria.processamento;

import java.io.Serializable;
import imobiliaria.controladores.*;

/**
 * Classe Sistema para guardar informacoes de um Sistema Imobiliario
 * 
 * @author Yuri
 * @version IT 2
 */

/*
 * Essa Classe ira guardar todas as informacoes do Sistema, tais como banco de
 * dados contendo os Imoveis, Clientes, Funcionarios e o Caixa da Empresa
 */
public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;

	private ControladorFinanceiro controladorFinanceiro = new ControladorFinanceiro();
	private ControladorImovel controladorImoveis = new ControladorImovel();
	private ControladorCliente controladorClientes = new ControladorCliente();
	private ControladorFuncionario controladorFuncionarios = new ControladorFuncionario();
	private ControladorPedidos controladorPedidos = new ControladorPedidos();

	/**
	 * Metodo Login - Efetua login baseado no login, senha e Tipo do Usuario
	 * 
	 * @param login
	 *            Login do Usuario
	 * @param senha
	 *            Senha do Usuario
	 * @param tipoDeUsuario
	 *            Tipo de Usuario que vai ser logado
	 * @return True - Se o usuario efetuou login com sucesso <br>
	 *         False - Se o usuario nao conseguiu logar
	 */
	public boolean login(String login, String senha, TipoLogin tipoDeUsuario) {

		if (tipoDeUsuario == TipoLogin.ADMINISTRADOR) {
			if (login.equals("admin") && senha.equals("admin"))
				return true;
			return false;

		} else if (tipoDeUsuario == TipoLogin.FUNCIONARIO) {

			if (controladorFuncionarios.login(login, senha)) {
				return true;
			}
			return false;

		} else {

			if (controladorClientes.login(login, senha)) {
				return true;
			}
			return false;
		}
	}

	/* Metodos Acessadores dos Controladores */

	/**
	 * Metodo acessador do Controlador Financeiro
	 * 
	 * @return Controlador Financeiro
	 */
	public ControladorFinanceiro controladorFinanceiro() {
		return controladorFinanceiro;
	}

	/**
	 * Metodo acessador do Contrador de Imoveis do Sistema
	 * 
	 * @return the controladorImoveis
	 */
	public ControladorImovel controladorImoveis() {
		return controladorImoveis;
	}

	/**
	 * Metodo acessador do Contrador de Clientes do Sistema
	 * 
	 * @return the controladorClientes
	 */
	public ControladorCliente controladorClientes() {
		return controladorClientes;
	}

	/**
	 * Metodo acessador do Contrador de Funcionarios do Sistema
	 * 
	 * @return the controladorFuncionarios
	 */
	public ControladorFuncionario controladorFuncionarios() {
		return controladorFuncionarios;
	}

	/* Metodos relacionados a Pedidos */

	/**
	 * Metodo que adiciona pedido ao sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel pedido
	 * @param cpf
	 *            Cpf do Cliente que fez o pedido
	 * @throws Exception
	 *             Lanca Excecao caso algum dos parametros esteja errado
	 */
	public void adicionaPedido(String registroImovel, String cpf)
			throws Exception {

		controladorPedidos.adicionaPedido(registroImovel, cpf,
				controladorImoveis, controladorClientes);
	}

	/**
	 * Metodo que efetua um pedido do Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que ja foi pedido
	 * @param creciFuncionario
	 *            Creci do Funcionario que efetuou a compra
	 * @throws Exception
	 *             Lanca excecao caso algum parametro seja invalido
	 */
	public void efetuaPedido(String registroImovel, String creciFuncionario)
			throws Exception {

		controladorPedidos.efetuaPedido(registroImovel, creciFuncionario,
				controladorFuncionarios,
				controladorFinanceiro);
	}

	/**
	 * Metodo que remove um pedido do Sistema
	 * 
	 * @param registroImovel
	 *            Registro do Imovel que foi pedido
	 * @throws Exception
	 *             Lanca excecao caso algum parametro seja invalido
	 */
	public void removePedido(String registroImovel) throws Exception {
		controladorPedidos.removePedido(registroImovel);
	}

	/**
	 * Metodo que lista os pedidos do Sistema
	 * 
	 * @return String contendo uma listagem dos pedidos do Sistema
	 */
	public String listagemPedidos() {
		return controladorPedidos.listagemDePedido();
	}
}
