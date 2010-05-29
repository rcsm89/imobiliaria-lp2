/**
 * Package contendo os elementos de Interface com o usuario
 */
package imobiliaria.userInterface;

import imobiliaria.processamento.Sistema;
import imobiliaria.processamento.TipoImovel;
import imobiliaria.processamento.TipoLogin;
import imobiliaria.util.MetodoEntrada;

import java.util.Calendar;
import java.util.LinkedList;

/**
 * Interface textual do sistema
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class InterfaceTextual {

    private Sistema sis;
    private String lineSep;

    private final int CLIENTE = 1;
    private final int FUNCIONARIO = 2;
    private final int ADMINISTRADOR = 3;

    // Mensagens do Sistema

    private final String programaFinalizado = "================= Programa "
	    + "Finalizado =================";

    private final String opcaoInvalida = "========= Digite Uma Opcao Valida"
	    + ", Por Favor ==========";

    private final String loginFail = "===================  LOGIN FAILED =="
	    + "===================";

    private final String loginOk = "=================== SEJA BEM VINDO ==="
	    + "=================";

    /**
     * Metodo que cria uma interface textual para o usuario
     */
    public InterfaceTextual() {
	this.lineSep = System.getProperty("line.separator");
	this.sis = new Sistema();
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
		    interfaceCliente();
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case FUNCIONARIO:
		if (acesso(FUNCIONARIO)) {
		    System.out.println(loginOk);
		    interfaceFuncionario();
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case ADMINISTRADOR:
		if (acesso(ADMINISTRADOR)) {
		    System.out.println(loginOk);
		    interfaceAdmin();
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case CADASTRO_CLIENTE:
		cadastroDeClientes();
		break;

	    case SAIR:
		loopPrincipal = setLoop(loopPrincipal);
		break;

	    default:
		System.out.println(opcaoInvalida);
	    }
	}
	System.out.println(programaFinalizado);
    }

    // Metodos da Interface

    private void cadastroDeClientes() {

	boolean repeteCadastro;
	int opcaoImovel = 0;
	do {
	    System.out.println(lineSep
		    + "----------------- Cadastro de Cliente -----------------"
		    + lineSep);

	    String nome = MetodoEntrada
		    .recebeString("Digite o Nome do Cliente: ");

	    String cpf = MetodoEntrada
		    .recebeString("CPF (Apenas os 11 digitos): ");

	    System.out.print("Data de Nascimento (dd/MM/AAAA): ");
	    Calendar dataNascimento = MetodoEntrada.recebeData();

	    String endereco = MetodoEntrada.recebeString("Endereco: ");

	    System.out.print("\nQual sua preferência de imóvel?\n"
		    + "1. Casa\n" + "2. Apartamento\n" + "3. Terreno\n"
		    + "---------------\n");

	    opcaoImovel = MetodoEntrada.recebeInt();

	    TipoImovel preferencia;
	    try {
		preferencia = TipoImovel.values()[opcaoImovel];
	    } catch (IndexOutOfBoundsException erro) {
		preferencia = null;
	    }
	    try {
		sis.controladorClientes().adicionaCliente(cpf, dataNascimento,
			endereco, nome, preferencia);
		repeteCadastro = false;
	    } catch (Exception erro) {
		System.out.println("\n=========== AVISO =============\n"
			+ erro.getMessage());
		repeteCadastro = true;
	    }

	} while (repeteCadastro);
	System.out
		.println("\n=========== Cadastro Efetuado com Sucesso =============\n"
			+ "\nLogin default: 6 primeiros numeros do seu CPF\nSenha default: "
			+ "Data de Nascimento (DDmmAAAA)\n\n"
			+ "        Voce podera redefinir suas preferencias\n"
			+ "        posteriormente quando acessar sua conta\n");
    }

    private boolean acesso(int tipo) {

	String userName = MetodoEntrada.recebeString("Login: ");
	String password = MetodoEntrada.recebeString("Senha: ");

	if (tipo == ADMINISTRADOR) {
	    return sis.login(userName, password, TipoLogin.ADMINISTRADOR);

	} else if (tipo == FUNCIONARIO) {
	    return sis.login(userName, password, TipoLogin.FUNCIONARIO);

	} else if (tipo == CLIENTE) {
	    return sis.login(userName, password, TipoLogin.CLIENTE);

	} else {
	    return false;
	}
    }

    private void interfaceCliente() {

	boolean repeteMenu = true;
	int opcao;

	while (repeteMenu) {
	    promptMenuCliente();
	    opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case 1:
		break;

	    }
	}
    }

    private void interfaceFuncionario() {
	boolean repeteMenu = true;
	int opcao;

	while (repeteMenu) {
	    promptMenuFuncionario();
	    opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case 1:
		break;

	    }
	}

    }

    private void interfaceAdmin() {
	boolean repeteMenu = true;
	int opcao;

	while (repeteMenu) {
	    promptMenuAdmin();
	    opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case 1:
		break;

	    }
	}

    }

    // Metodos modificadores da Interface
    private boolean setLoop(boolean loop) {
	return !(loop);
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
			+ lineSep);
	constroeMenu(opcoesMenu(FUNCIONARIO));

    }

    private LinkedList<String> opcoesMenu(int tipo) {

	String[] opcoesMenuCliente = { "Cadastrar Cliente", "Listar Clientes",
		"Excluir Cadastro de Cliente", "Atualizar Cadastro de Cliente" };
	String[] opcoesMenuFuncionario = { "Cadastrar Funcionario",
		"Listar Funcionario", "Excluir Cadastro de Funcionario",
		"Atualizar Cadastro de Funcionario" };
	String[] opcoesImoveis = { "Cadastrar Imovel", "Listar Imoveis",
		"Excluir Cadastro de Imovel", "Atualizar Cadastro de Imovel" };
	String[] opcoesMenuAdmin = { "Efetuar Pagamento" };

	LinkedList<String> menu = new LinkedList<String>();

	if (tipo == CLIENTE) {
	    for (String opcao : opcoesMenuCliente)
		menu.add(opcao);

	} else if (tipo == FUNCIONARIO) {
	    menu = opcoesMenu(CLIENTE);

	    String[][] conjuntoOpcoes = { opcoesMenuFuncionario, opcoesImoveis };

	    for (String[] tipoDaVez : conjuntoOpcoes) {
		menu.add(lineSep);
		for (String opcao : tipoDaVez)
		    menu.add(opcao);
	    }
	} else if (tipo == ADMINISTRADOR) {
	    menu = opcoesMenu(FUNCIONARIO);

	    menu.add(lineSep);
	    for (String opcao : opcoesMenuAdmin)
		menu.add(opcao);
	}
	return menu;
    }

    private String constroeMenu(LinkedList<String> opcoes) {
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
	return saida += String.format("  %2d. %s%s", num, "Sair", lineSep);
    }
}