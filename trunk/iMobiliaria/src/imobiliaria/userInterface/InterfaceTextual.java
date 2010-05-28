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
    private boolean loopPrincipal;
    private String lineSep;

    private final int CLIENTE = 1;
    private final int FUNCIONARIO = 2;
    private final int ADMINISTRADOR = 3;

    // Mensagens do Sistema
    private final String loginFail = "==================="
	    + "  LOGIN FAILED =====================";

    private final String programaFinalizado = "================= Programa "
	    + "Finalizado =================";

    private final String opcaoInvalida = "========= Digite Uma Opcao Valida"
	    + ", Por Favor ==========";

    /**
     * Metodo que cria uma interface textual para o usuario
     */
    public InterfaceTextual() {
	this.loopPrincipal = true;
	this.lineSep = System.getProperty("line.separator");
	this.sis = new Sistema();
    }

    /**
     * Interface textual do usuario com o programa.
     */
    public void interfaceComUsuario() {

	final int CADASTRO_CLIENTE = 4;
	final int SAIR = 5;

	while (loopPrincipal) {
	    promptLogin();
	    int opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case CLIENTE:
		if (acesso(CLIENTE)) {
		    interfaceCliente();
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case FUNCIONARIO:
		if (acesso(FUNCIONARIO)) {
		    interfaceFuncionario();
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case ADMINISTRADOR:
		if (acesso(ADMINISTRADOR)) {
		    interfaceAdmin();
		} else {
		    System.out.println(loginFail);
		}
		break;

	    case CADASTRO_CLIENTE:
		cadastroDeClientes();
		break;

	    case SAIR:
		setLoopPrincipal();
		break;

	    default:
		System.out.println(opcaoInvalida);
	    }
	}
	System.out.println(programaFinalizado);
	;
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
			+ "\nLogin default: 11 Digitos do seu CPF\nSenha default: "
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
    private void setLoopPrincipal() {
	loopPrincipal = !(loopPrincipal);
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

	int numMenu = 1;
	switch (tipo) {

	case CLIENTE:
	    for (String opcao : opcoesMenuCliente) {
		opcao = numMenu + ". " + opcao + lineSep;
		menu.add(opcao);
		numMenu++;
	    }
	    break;

	case FUNCIONARIO:
	    menu = opcoesMenu(CLIENTE);
	    numMenu = menu.size() + 1;

	    String[][] conjuntoOpcoes = { opcoesMenuFuncionario, opcoesImoveis };

	    for (String[] tipoDaVez : conjuntoOpcoes) {
		menu.add(lineSep);
		for (String opcao : tipoDaVez) {
		    opcao = numMenu + ". " + opcao + lineSep;
		    menu.add(opcao);
		    numMenu++;
		}
	    }
	    break;

	case ADMINISTRADOR:
	    menu = opcoesMenu(FUNCIONARIO);
	    numMenu = menu.size() + 1;

	    menu.add(lineSep);
	    for (String opcao : opcoesMenuAdmin) {
		opcao = numMenu + ". " + opcao + lineSep;
		menu.add(opcao);
		numMenu++;
	    }
	    break;
	}
	return menu;
    }

    private String constroeMenu(LinkedList<String> opcoes) {
	String saida = "";
	for (String opcao : opcoes) {
	    saida += opcao;
	}
	return saida;
    }

}