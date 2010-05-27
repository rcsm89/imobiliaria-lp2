/**
 * Package contendo os elementos de Interface com o usuario
 */
package imobiliaria.userInterface;

/**
 * Interface textual do sistema
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class InterfaceTextual {

    private static String[] opcoesMenuCliente = { "Cadastrar Cliente",
	    "Listar Clientes", "Excluir Cadastro de Cliente",
	    "Atualizar Cadastro de Cliente" };
    private static String[] opcoesMenuFuncionario = { "Cadastrar Funcionario",
	    "Listar Funcionario", "Excluir Cadastro de Funcionario",
	    "Atualizar Cadastro de Funcionario", "Cadastrar Imovel",
	    "Listar Imoveis", "Excluir Cadastro de Imovel",
	    "Atualizar Cadastro de Imovel"};
    private static String[] opcoesMenuAdmin = { "" };

    public static void promptMenuPrincipal() {
	System.out
		.println("--------------------- iMobiliaria ---------------------\n"
			+ "  Bem Vindo, realize o login ou cadastre-se, por favor \n\n"
			+ "1. Cliente                4. Cadastro\n"
			+ "2. Funcionario            5. Sair\n"
			+ "3. Administrador\n");
    }

    public static void promptMenuCliente() {
	System.out
		.println("----------------------- Cliente -----------------------\n\n"
			+ constroeMenu(opcoesMenuCliente));

    }

    public static void promptMenuFuncionario() {
	System.out
		.println("--------------------- Funcionario ---------------------\n\n"
			+ constroeMenu(opcoesMenuCliente)
			+ "\n"
			+ constroeMenu(opcoesMenuFuncionario));
    }

    public static void promptMenuAdmin() {
	System.out
		.println("-------------------- Administrador --------------------\n\n"
			+ constroeMenu(opcoesMenuCliente)
			+ "\n"
			+ constroeMenu(opcoesMenuFuncionario)
			+ "\n"
			+ constroeMenu(opcoesMenuAdmin));
    }

    public static void promptCadastroCliente() {
	System.out
		.println("----------------- Cadastro de Cliente -----------------\n\n"
			+ "");
    }

    public static void promptProgramaFinalizado() {
	System.out
		.println("-------------------------------------------------------\n\n"
			+ "Programa Finalizado");
    }

    public static void promptOpcaoInvalida() {
	System.out
		.println("-------------------------------------------------------\n"
			+ "Digite uma opcao valida, por favor\n");
    }

    private static String constroeMenu(String[] prompts) {
	String opcoes = "";
	for (String opcao : prompts) {
	    opcoes += opcao + "\n";
	}
	return opcoes;
    }

}