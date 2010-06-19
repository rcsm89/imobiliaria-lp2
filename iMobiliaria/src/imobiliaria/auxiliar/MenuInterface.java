package imobiliaria.auxiliar;

/**
 * Interface auxiliar para o menu do sistema
 * 
 * @version IT02
 */
public interface MenuInterface {

    String lineSep = System.getProperty("line.separator");

    int ADMINISTRADOR = 3;
    int FUNCIONARIO = 2;
    int CLIENTE = 1;

    // Opcoes em comum de Funcionario e Administrador
    int CADASTRO_CLIENTE = 1;
    int LISTAR_CLIENTE = 2;
    int EXCLUIR_CLIENTE = 3;
    int ATUALIZAR_CADASTRO_CLIENTE = 4;
    int INFORMACOES_CLIENTE = 5;
    int VER_HIST_DE_UM_CLIENTE = 6;

    int CADASTRO_IMOVEL = 7;
    int LISTAR_IMOVEIS = 8;
    int EXCLUIR_CADASTRO_IMOVEL = 9;
    int ATUALIZAR_CADASTRO_IMOVEL = 10;

    int INFORMACOES_IMOVEL = 11;

    int LISTAR_ALUGUEIS = 12;
    int LISTAR_ALUGUEIS_DE_UM_CLIENTE = 13;
    int CANCELAR_ALUGUEL = 14;

    int LISTAR_PEDIDO = 15;
    int LISTAR_PEDIDO_DE_UM_CLIENTE = 16;
    int EFETUAR_UM_PEDIDO = 17;
    int CANCELAR_UM_PEDIDO = 18;


    // Mensagens do Sistema

    String programaFinalizado = "================= Programa "
	    + "Finalizado =================";

    String opcaoInvalida = "========= Digite Uma Opcao Valida"
	    + ", Por Favor ==========";

    String loginFail = "===================  LOGIN FAILED =="
	    + "===================";

    String loginOk = "=================== SEJA BEM VINDO ===="
	    + "================";

    String logOut = "================ VOCE SAIU DO SISTEMA =="
	    + "===============";

}
