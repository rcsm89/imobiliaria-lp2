package imobiliaria.util;

import imobiliaria.auxiliar.MenuInterface;

import java.util.LinkedList;

/**
 * Metodos auxliares para construcao do Menu na Interface Textual<br>
 * Auxiliary methods to make the Textual Interface's Menu
 * 
 * @author Jeanderson Barros Candido
 * @version version 2 (new)
 * 
 */
public class FerramentaMenu implements MenuInterface {

    // Attributes
    private static String[] opcoesMenuCliente = { "Listar Imoveis",
	    "Fazer Pedido", "Listar Pedidos", "Cancelar Pedido",
	    "Verificar seus dados", "Ver Historico de Compras",
	    "Listar meus alugueis", "Cancelar um aluguel" };

    private static String[] opcoesMenuFuncionario1 = {
	    // Opcoes relacionadas a cliente (CRDU)

	    "Cadastrar Cliente", "Listar Clientes",
	    "Excluir Cadastro de Cliente", "Atualizar Cadastro de Cliente",
	    "Verificar informacoes de um Cliente" };
    private static String[] opcoesMenuFuncionario2 = {
	    // Opcoes relacionadas a Imoveis (CRDU)

	    "Cadastrar Imovel", "Listar Imoveis", "Excluir Cadastro de Imovel",
	    "Atualizar Cadastro de Imovel",
	    "Verificar informacoes de um Imovel" };

    private static String[] opcoesMenuFuncionario3 = {
	    // Opcoes do Funcionario

	    "Listar Pedidos", "Efetuar um Pedido", "Verificar seus dados",
	    "Ver Historico de Vendas" };

    private static String[] opcoesMenuAdmin = {

	    // Opcoes relacionadas a Funcionarios (CRDU) + Efetuar Pedido

	    "Cadastrar Funcionario", "Listar Funcionario",
	    "Excluir Cadastro de Funcionario",
	    "Atualizar Cadastro de Funcionario",
	    "Verificar informacoes de um Funcionario",
	    "Efetuar um Pedido",

	    // Opcoes do Admin
	    "Realiza Pagamento", "Ver Saldo Atual",
	    "Listar Transacoes Mensais", "Listar Transacoes",
	    "Remover Transacao", "Listar Alugueis" };

    // Methods

    /**
     * Retorna uma lista contendo todas as opcoes de um tipo de menu<br>
     * Returns a list with all the options from a kind of menu
     * 
     * @param tipo
     *            O tipo do menu a ser construido<br>
     *            The kind of menu to be done
     * @return Uma lista contendo as opcoes do menu<br>
     *         A list with the menu's options
     */
    public static LinkedList<String> opcoesMenu(int tipo) {

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

    /**
     * Constroe um menu apartir de uma lista de opcoes<br>
     * Builds a menu with a list that contains all the menu's options
     * 
     * @param opcoes
     *            Uma lista contendo as opcoes do menu<br>
     *            A list that contais all the menu's options
     * @return Uma string que representa o menu<br>
     *         A string that represents the menu
     */
    public static String constroeMenu(LinkedList<String> opcoes) {
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

}
