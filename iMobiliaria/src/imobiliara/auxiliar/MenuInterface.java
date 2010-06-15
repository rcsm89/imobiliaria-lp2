package imobiliara.auxiliar;

/**
 * Interface auxiliar para o menu do sistema<br>
 * Auxiliary interface for the system's menu
 * 
 * @author Jeanderson Barros Candido
 * @version version 02 (new)
 * 
 */
public interface MenuInterface {

    String lineSep = System.getProperty("line.separator");

    int ADMINISTRADOR = 3;
    int FUNCIONARIO = 2;
    int CLIENTE = 1;

    // Opcoes em comum de Funcionario e Administrador

    int ATUALIZAR_CADASTRO_CLIENTE = 4;
    int ATUALIZAR_CADASTRO_IMOVEL = 9;
    int EXCLUIR_CADASTRO_IMOVEL = 8;
    int INFORMACOES_CLIENTE = 5;
    int INFORMACOES_IMOVEL = 10;
    int CADASTRO_CLIENTE = 1;
    int CADASTRO_IMOVEL = 6;
    int EXCLUIR_CLIENTE = 3;
    int LISTAR_CLIENTE = 2;
    int LISTAR_IMOVEL = 7;

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
