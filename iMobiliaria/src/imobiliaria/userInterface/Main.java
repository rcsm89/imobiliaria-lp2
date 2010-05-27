package imobiliaria.userInterface;

import imobiliaria.util.MetodoEntrada;

/**
 * Main do Sistema
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class Main {

    public static void main(String[] args) {

	final int MENU_CLIENTE = 1;
	final int MENU_FUNCIONARIO = 2;
	final int MENU_ADMINISTRADOR = 3;
	final int CADASTRO_CLIENTE = 4;
	final int SAIR = 5;

	boolean loopMenuPrincipal = false;

	do {

	    InterfaceTextual.promptMenuPrincipal();
	    int opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {

	    case MENU_CLIENTE:
		InterfaceTextual.promptMenuCliente();
		int opcaoCliente = MetodoEntrada.recebeInt();
		loopMenuPrincipal = false;
		break;

	    case MENU_FUNCIONARIO:
		InterfaceTextual.promptMenuFuncionario();
		int opcaoFuncionario = MetodoEntrada.recebeInt();
		loopMenuPrincipal = false;
		break;

	    case MENU_ADMINISTRADOR:
		InterfaceTextual.promptMenuAdmin();
		int opcaoAdministrador = MetodoEntrada.recebeInt();
		loopMenuPrincipal = false;
		break;

	    /*
	     * Esse caso esta DELEGADO ao CONTROLADOR DE CLIENTE
	     * Aqui as entradas sao Strings
	     */
	    case CADASTRO_CLIENTE:
		InterfaceTextual.promptCadastroCliente();
		loopMenuPrincipal = false;
		break;

	    case SAIR:
		InterfaceTextual.promptProgramaFinalizado();
		loopMenuPrincipal = false;
		break;

	    default:
		InterfaceTextual.promptOpcaoInvalida();
		loopMenuPrincipal = true;
	    }

	} while (loopMenuPrincipal);
    }
}
