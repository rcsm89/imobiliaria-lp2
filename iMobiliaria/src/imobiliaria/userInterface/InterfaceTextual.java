/**
 * Package contendo os elementos de Interface com o usuario
 */
package imobiliaria.userInterface;

import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.Funcionario;
import imobiliaria.processamento.Sistema;
import imobiliaria.processamento.TipoLogin;
import imobiliaria.util.MetodoEntrada;

import java.util.LinkedList;

/**
 * Interface textual do sistema
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class InterfaceTextual {

	private final int ADMINISTRADOR = 3;
	private final int FUNCIONARIO = 2;
	private final int CLIENTE = 1;

	private OperacoesInterfaceTextual op;
	private String lineSep;

	private Funcionario func;
	private Cliente cliente;
	private Sistema sis;

	// Opcoes em comum de Funcionario e Administrador

	private final int ATUALIZAR_CADASTRO_CLIENTE = 4;
	private final int ATUALIZAR_CADASTRO_IMOVEL = 9;
	private final int EXCLUIR_CADASTRO_IMOVEL = 8;
	private final int INFORMACOES_CLIENTE = 5;
	private final int INFORMACOES_IMOVEL = 10;
	private final int CADASTRO_CLIENTE = 1;
	private final int CADASTRO_IMOVEL = 6;
	private final int EXCLUIR_CLIENTE = 3;
	private final int LISTAR_CLIENTE = 2;
	private final int LISTAR_IMOVEL = 7;

	// Mensagens do Sistema

	private final String programaFinalizado = "================= Programa "
			+ "Finalizado =================";

	private final String opcaoInvalida = "========= Digite Uma Opcao Valida"
			+ ", Por Favor ==========";

	private final String loginFail = "===================  LOGIN FAILED =="
			+ "===================";

	private final String loginOk = "=================== SEJA BEM VINDO ===="
			+ "================";

	private final String logOut = "================ VOCE SAIU DO SISTEMA =="
			+ "===============";

	/**
	 * Metodo que cria uma interface textual para o usuario
	 */
	public InterfaceTextual() {
		this.lineSep = System.getProperty("line.separator");
		this.sis = new Sistema();
		this.op = new OperacoesInterfaceTextual(sis);
	}

	/**
	 * Interface textual do usuario com o programa.
	 */
	public void interfaceComUsuario() {
		boolean loopPrincipal = true;

		final int CADASTRO_CLIENTE = 4;
		final int SAIR = 5;

		while (loopPrincipal) {
			promptLogin();
			int opcao = MetodoEntrada.recebeInt();

			switch (opcao) {
			case CLIENTE:
				if (acesso(CLIENTE)) {
					System.out.println(loginOk);
					interfaceCliente(cliente);
					System.out.println(logOut);
				} else {
					System.out.println(loginFail);
				}
				break;

			case FUNCIONARIO:
				if (acesso(FUNCIONARIO)) {
					System.out.println(loginOk);
					interfaceFuncionario(func);
					System.out.println(logOut);
				} else {
					System.out.println(loginFail);
				}
				break;

			case ADMINISTRADOR:
				if (acesso(ADMINISTRADOR)) {
					System.out.println(loginOk);
					interfaceAdmin();
					System.out.println(logOut);
				} else {
					System.out.println(loginFail);
				}
				break;

			case CADASTRO_CLIENTE:
				op.cadastroDeClientes();
				break;

			case SAIR:
				loopPrincipal = !(loopPrincipal);
				break;

			default:
				System.out.println(opcaoInvalida);
			}
		}
		System.out.println(programaFinalizado);
	}

	private void interfaceCliente(Cliente cliente) {

		boolean repeteMenu = true;
		int opcao;

		final int LISTAR_IMOVEIS = 1;
		final int FAZER_PEDIDO = 2;
		final int VERIF_DADOS = 3;
		final int HIST_COMPRAS = 4;
		final int SAIR = 5;

		while (repeteMenu) {
			promptMenuCliente();
			opcao = MetodoEntrada.recebeInt();

			switch (opcao) {
			case LISTAR_IMOVEIS:
				op.listarImoveis();
				break;

			case FAZER_PEDIDO:

				op.fazerPedido(cliente.getCpf());

				break;

			case VERIF_DADOS:
				op.verificarDadosPessoais(cliente);
				break;

			case HIST_COMPRAS:
				op.historicoCompras(cliente);
				break;

			case SAIR:
				repeteMenu = !(repeteMenu);
				break;

			default:
				System.out.println(opcaoInvalida);

			}

		}
	}

	private void interfaceFuncionario(Funcionario func) {

		final int EFETUAR_PEDIDO = 11;
		final int VERIFICAR_DADOS = 12;
		final int HIST_VENDAS = 13;
		final int SAIR = 14;

		boolean repeteMenu = true;
		int opcao;

		while (repeteMenu) {
			promptMenuFuncionario();
			opcao = MetodoEntrada.recebeInt();

			switch (opcao) {

			case CADASTRO_CLIENTE:
				op.cadastroDeClientes();
				break;

			case LISTAR_CLIENTE:
				op.listarClientes();
				break;

			case EXCLUIR_CLIENTE:

				op.excluirCliente();

				break;

			case ATUALIZAR_CADASTRO_CLIENTE:

				System.out.println("Construcao");

				break;

			case INFORMACOES_CLIENTE:

				op.verificaInformacoesCliente();

				break;

			case CADASTRO_IMOVEL:
				op.cadastroDeImovel();
				break;

			case LISTAR_IMOVEL:
				op.listarImoveis();
				break;

			case EXCLUIR_CADASTRO_IMOVEL:

				op.excluirImovel();

				break;

			case ATUALIZAR_CADASTRO_IMOVEL:

				System.out.println("Construcao");

				break;

			case INFORMACOES_IMOVEL:

				op.informacoesImovel();

				break;

			case EFETUAR_PEDIDO:

				op.efetuaPedido();

				break;

			case VERIFICAR_DADOS:

				op.verificaDadosPessoais(func);

				break;

			case HIST_VENDAS:

				op.historicoVendas(func);

				break;

			case SAIR:
				repeteMenu = !(repeteMenu);
				break;

			default:
				System.out.println(opcaoInvalida);
			}

		}
	}

	private void interfaceAdmin() {
		boolean repeteMenu = true;
		int opcao;

		final int CADASTRO_FUNCIONARIO = 11;
		final int LISTAR_FUNCIONARIO = 12;
		final int EXCLUIR_CADASTRO_FUNCIONARIO = 13;
		final int ATUALIZAR_CADASTRO_FUNCIONARIO = 14;
		final int VERIFICAR_INF_FUNCIONARIO = 15;
		final int EFETUAR_PEDIDO = 16;
		final int EFETUAR_PAGAMENTO = 17;
		final int VER_SALDO_ATUAL = 18;
		final int SAIR = 19;

		while (repeteMenu) {
			promptMenuAdmin();
			opcao = MetodoEntrada.recebeInt();

			switch (opcao) {
			case CADASTRO_CLIENTE:
				op.cadastroDeClientes();
				break;

			case LISTAR_CLIENTE:
				op.listarClientes();
				break;

			case EXCLUIR_CLIENTE:

				op.excluirCliente();

				break;

			case ATUALIZAR_CADASTRO_CLIENTE:

				System.out.println("Construcao");

				break;

			case INFORMACOES_CLIENTE:
				
				op.verificaInformacoesCliente();
				
				break;

			case CADASTRO_IMOVEL:
				op.cadastroDeImovel();
				break;

			case LISTAR_IMOVEL:
				op.listarImoveis();
				break;

			case EXCLUIR_CADASTRO_IMOVEL:

				op.excluirImovel();

				break;

			case ATUALIZAR_CADASTRO_IMOVEL:
				System.out.println("Construcao");
				break;

			case INFORMACOES_IMOVEL:

				op.informacoesImovel();

				break;

			case CADASTRO_FUNCIONARIO:
				op.cadastroDeFuncionario();
				break;

			case LISTAR_FUNCIONARIO:
				op.listarFuncionarios();
				break;

			case EXCLUIR_CADASTRO_FUNCIONARIO:

				op.excluirFuncionario();

				break;

			case ATUALIZAR_CADASTRO_FUNCIONARIO:

				System.out.println("Construcao");

				break;

			case VERIFICAR_INF_FUNCIONARIO:

				op.verificaInformacoesFuncionario();

				break;

			case EFETUAR_PEDIDO:

				op.efetuaPedido();

				break;

			case EFETUAR_PAGAMENTO:

				op.efetuarPagamento();

				break;

			case VER_SALDO_ATUAL:

				op.verificaSaldoAtual();

				break;

			case SAIR:
				repeteMenu = !(repeteMenu);
				break;

			default:
				System.out.println(opcaoInvalida);
			}
		}

	}

	// Metodos que mostram o prompt da operacao atual
	private void promptLogin() {
		System.out
				.println("--------------------- iMobiliaria ---------------------"
						+ lineSep
						+ "  Bem Vindo, realize o login ou cadastre-se, por favor "
						+ lineSep
						+ lineSep
						+ "      1. Cliente                    4. Cadastro"
						+ lineSep
						+ "      2. Funcionario                5. Sair"
						+ lineSep + "      3. Administrador" + lineSep);
	}

	private void promptMenuAdmin() {
		System.out
				.println("-------------------- Administrador --------------------"
						+ lineSep + constroeMenu(opcoesMenu(ADMINISTRADOR)));
	}

	private void promptMenuCliente() {
		System.out
				.println("----------------------- Cliente -----------------------"
						+ lineSep + constroeMenu(opcoesMenu(CLIENTE)));

	}

	private void promptMenuFuncionario() {
		System.out
				.println("--------------------- Funcionario ---------------------"
						+ lineSep + constroeMenu(opcoesMenu(FUNCIONARIO)));
	}

	private LinkedList<String> opcoesMenu(int tipo) {

		String[] opcoesMenuCliente = { "Listar Imoveis", "Fazer Pedido",
				"Verificar seus dados", "Ver Historico de Compras" };

		String[] opcoesMenuFuncionario1 = {
				// Opcoes relacionadas a cliente (CRDU)

				"Cadastrar Cliente", "Listar Clientes",
				"Excluir Cadastro de Cliente", "Atualizar Cadastro de Cliente",
				"Verificar informacoes de um Cliente" };
		String[] opcoesMenuFuncionario2 = {
				// Opcoes relacionadas a Imoveis (CRDU)

				"Cadastrar Imovel", "Listar Imoveis",
				"Excluir Cadastro de Imovel", "Atualizar Cadastro de Imovel",
				"Verificar informacoes de um Imovel" };

		String[] opcoesMenuFuncionario3 = {
				// Opcoes do Funcionario

				"Efetuar um Pedido", "Verificar seus dados",
				"Ver Historico de Vendas" };

		String[] opcoesMenuAdmin = {

				// Opcoes relacionadas a Funcionarios (CRDU) + Efetuar Pedido

				"Cadastrar Funcionario", "Listar Funcionario",
				"Excluir Cadastro de Funcionario",
				"Atualizar Cadastro de Funcionario",
				"Verificar informacoes de um Funcionario", "Efetuar um Pedido",

				// Opcoes do Admin

				"Realiza Pagamento", "Ver Saldo Atual" };

		LinkedList<String> menu = new LinkedList<String>();

		if (tipo == CLIENTE) {
			for (String opcao : opcoesMenuCliente)
				menu.add(opcao);

		} else if (tipo == FUNCIONARIO) {

			String[][] conjuntoOpcoes = { opcoesMenuFuncionario1,
					opcoesMenuFuncionario2, opcoesMenuFuncionario3 };

			for (String[] tipoDaVez : conjuntoOpcoes) {
				menu.add(lineSep);
				for (String opcao : tipoDaVez)
					menu.add(opcao);
			}
		} else if (tipo == ADMINISTRADOR) {
			String[][] conjuntoOpcoes = { opcoesMenuFuncionario1,
					opcoesMenuFuncionario2, opcoesMenuAdmin };

			for (String[] tipoDaVez : conjuntoOpcoes) {
				menu.add(lineSep);
				for (String opcao : tipoDaVez)
					menu.add(opcao);
			}
		}
		return menu;
	}

	private String constroeMenu(LinkedList<String> opcoes) {
		opcoes.add("Deslogar");
		String saida = lineSep;

		int num = 1;
		for (String opcao : opcoes) {
			if (!(opcao.equals(lineSep))) {
				saida += String.format("  %2d. %s%s", num, opcao, lineSep);
				num++;
			} else {
				saida += lineSep;
			}
		}
		return saida;
	}

	private boolean acesso(int tipo) {

		String userName = MetodoEntrada.recebeString("Login: ");
		String password = MetodoEntrada.recebeString("Senha: ");

		if (tipo == ADMINISTRADOR) {
			return sis.login(userName, password, TipoLogin.ADMINISTRADOR);

		} else if (tipo == FUNCIONARIO) {
			for (Funcionario f : sis.controladorFuncionarios()
					.getColecaoFuncionarios()) {
				if ((f.getLogin().equals(userName))
						&& (f.getSenha().equals(password))) {
					try {
						func = sis.controladorFuncionarios().getFuncionario(
								f.getCreci());
					} catch (Exception e) {
						return false;
					}
				}
			}
			return sis.login(userName, password, TipoLogin.FUNCIONARIO);

		} else if (tipo == CLIENTE) {
			for (Cliente cl : sis.controladorClientes().getClientes()) {
				if ((cl.getLogin().equals(userName))
						&& (cl.getSenha().equals(password))) {
					cliente = sis.controladorClientes().getCliente(cl.getCpf());
				}
			}

			return sis.login(userName, password, TipoLogin.CLIENTE);

		} else {
			return false;
		}
	}

}