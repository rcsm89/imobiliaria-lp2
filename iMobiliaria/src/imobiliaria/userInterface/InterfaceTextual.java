package imobiliaria.userInterface;

import imobiliara.auxiliar.MenuInterface;
import imobiliara.auxiliar.MenuInterfaceAdmin;
import imobiliara.auxiliar.MenuInterfaceCliente;
import imobiliara.auxiliar.MenuInterfaceFuncionario;
import imobiliara.auxiliar.TipoLogin;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Login;
import imobiliaria.processamento.Sistema;
import imobiliaria.util.FerramentaMenu;
import imobiliaria.util.MetodoEntrada;
import imobiliaria.util.PersistenciaDados;

/**
 * Interface textual do sistema<br>
 * System's textual interface
 * 
 * @author Jeanderson Barros Candido
 * @version version 02 (update)
 * 
 */
public class InterfaceTextual implements MenuInterfaceCliente,
	MenuInterfaceFuncionario, MenuInterface, MenuInterfaceAdmin {

    private OperacoesInterfaceTextual op;
    private Funcionario func;
    private Cliente cliente;
    private Sistema sis;

    /**
     * Cria uma interface textual<br>
     * Creates the textual interface
     */
    public InterfaceTextual() {

	// Leitura de Dados
	try {
	    this.sis = (Sistema) PersistenciaDados.ler("DadosDeSistema.dat");
	    sis.atualizaDados();
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	}
	this.op = new OperacoesInterfaceTextual(sis);
    }

    /**
     * Interface textual do usuario<br>
     * User's textual interface
     */
    public void interfaceComUsuario() {
	boolean loopPrincipal = true;

	final int CADASTRO_CLIENTE = 4;
	final int SAIR = 5;

	while (loopPrincipal) {

	    // Grava Dados!
	    sis.salvarDados();

	    promptLogin();
	    int opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case CLIENTE:
		if (acesso(TipoLogin.CLIENTE)) {
		    System.out.println(loginOk);
		    interfaceCliente(cliente);
		    System.out.println(logOut);
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case FUNCIONARIO:
		if (acesso(TipoLogin.FUNCIONARIO)) {
		    System.out.println(loginOk);
		    interfaceFuncionario(func);
		    System.out.println(logOut);
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case ADMINISTRADOR:
		if (acesso(TipoLogin.ADMINISTRADOR)) {
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

    /**
     * Mostra a interface de cliente<br>
     * Shows the client's interface
     * 
     * @param cliente
     *            Que esta efetuando operacoes<br>
     *            Who is doing operations
     */
    private void interfaceCliente(Cliente cliente) {

	boolean repeteMenu = true;
	int opcao;

	while (repeteMenu) {

	    promptMenuCliente();
	    opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case CLIENTE_LISTAR_IMOVEIS:
		op.listarImoveis();
		break;

	    case CLIENTE_FAZER_PEDIDO:
		op.fazerPedido(cliente.getCpf());
		break;

	    case CLIENTE_VERIF_DADOS:
		op.verificarDadosPessoais(cliente);
		break;

	    case CLIENTE_HIST_COMPRAS:
		op.historicoCompras(cliente);
		break;

	    case CLIENTE_SAIR:
		repeteMenu = !(repeteMenu);
		break;

	    default:
		System.out.println(opcaoInvalida);
	    }
	}
    }

    /**
     * Mostra a interface de funcionario<br>
     * Shows the functionary's interface
     * 
     * @param func
     *            Que esta efetuando operacoes<br>
     *            Who is doing operations
     */
    private void interfaceFuncionario(Funcionario func) {

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
		System.out.println("Opcao em Construcao");
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
		System.out.println("Opcao em Construcao");
		break;

	    case INFORMACOES_IMOVEL:
		op.informacoesImovel();
		break;

	    case FUNC_EFETUAR_PEDIDO:
		op.efetuaPedido();
		break;

	    case FUNC_VERIFICAR_DADOS:
		op.verificaDadosPessoais(func);
		break;

	    case FUNC_HIST_VENDAS:
		op.historicoVendas(func);
		break;

	    case FUNC_SAIR:
		repeteMenu = !(repeteMenu);
		break;

	    default:
		System.out.println(opcaoInvalida);
	    }

	}
    }

    /**
     * Mostra a interface do administrador<br>
     * Shows the administrador's interface
     */
    private void interfaceAdmin() {
	boolean repeteMenu = true;
	int opcao;

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
		System.out.println("Opcao em Construcao");
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
		System.out.println("Opcao em Construcao");
		break;

	    case INFORMACOES_IMOVEL:
		op.informacoesImovel();
		break;

	    case ADMIN_CADASTRO_FUNCIONARIO:
		op.cadastroDeFuncionario();
		break;

	    case ADMIN_LISTAR_FUNCIONARIO:
		op.listarFuncionarios();
		break;

	    case ADMIN_EXCLUIR_CADASTRO_FUNCIONARIO:
		op.excluirFuncionario();
		break;

	    case ADMIN_ATUALIZAR_CADASTRO_FUNCIONARIO:
		System.out.println("Opcao em Construcao");
		break;

	    case ADMIN_VERIFICAR_INF_FUNCIONARIO:
		op.verificaInformacoesFuncionario();
		break;

	    case ADMIN_EFETUAR_PEDIDO:
		op.efetuaPedido();
		break;

	    case ADMIN_EFETUAR_PAGAMENTO:
		op.efetuarPagamento();
		break;

	    case ADMIN_VER_SALDO_ATUAL:
		op.verificaSaldoAtual();
		break;

	    case ADMIN_SAIR:
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
			+ lineSep
			+ FerramentaMenu.constroeMenu(FerramentaMenu
				.opcoesMenu(ADMINISTRADOR)));
    }

    private void promptMenuCliente() {
	System.out
		.println("----------------------- Cliente -----------------------"
			+ lineSep
			+ FerramentaMenu.constroeMenu(FerramentaMenu
				.opcoesMenu(CLIENTE)));

    }

    private void promptMenuFuncionario() {
	System.out
		.println("--------------------- Funcionario ---------------------"
			+ lineSep
			+ FerramentaMenu.constroeMenu(FerramentaMenu
				.opcoesMenu(FUNCIONARIO)));
    }

    private boolean acesso(TipoLogin tipo) {

	String userName = MetodoEntrada.recebeString("Login: ");
	String password = MetodoEntrada.recebeString("Senha: ");
	
	Login teste = sis.getControladorLogin().getLogin(Username);

	return sis.getControladorLogin().verificaLogin(teste);

    }

}