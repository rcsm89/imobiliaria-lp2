package imobiliaria.processamento;

import java.io.Serializable;

import imobiliara.auxiliar.TipoLogin;
import imobiliaria.controladores.*;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Transacao;
import imobiliaria.util.PersistenciaDados;

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

    private final String ARQUIVO_DO_SISTEMA = "DadosDeSistema.dat";
    private final String ARQUIVO_DE_REGISTROS = "Registros.dat";

    private ControladorImovel controladorImoveis = ControladorImovel
	    .getInstance();
    private ControladorCliente controladorClientes = ControladorCliente
	    .getInstance();
    private ControladorFuncionario controladorFuncionarios = ControladorFuncionario
	    .getInstance();
    private ControladorPedidos controladorPedidos = ControladorPedidos
	    .getInstance();
    private ControladorTransacoes controladorTransacoes = ControladorTransacoes
	    .getInstance();
    private ControladorAlugueis controladorAlugueis = ControladorAlugueis
	    .getInstance();
    private ControladorLogin controladorLogin = ControladorLogin.getInstance();

    /* Metodos Acessadores dos Controladores */

    /**
     * Metodo acessador do Controlador de Alugueis do Sistema
     * 
     * @return the controladorAlugueis
     */
    public ControladorAlugueis controladorAlugueis() {
	return controladorAlugueis;
    }

    /**
     * Metodo acessador do Controlador de Logins do Sistema
     * 
     * @return the controladorLogin
     */
    public ControladorLogin getControladorLogin() {
	return controladorLogin;
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
     * Metodo acessador do Controlador de Pedidos do Sistema
     * 
     * @return the controladorPedidos
     */
    public ControladorPedidos controladorPedidos() {
	return controladorPedidos;
    }

    /**
     * Metodo acessador do Contrador de Funcionarios do Sistema
     * 
     * @return the controladorFuncionarios
     */
    public ControladorFuncionario controladorFuncionarios() {
	return controladorFuncionarios;
    }

    /**
     * Metodo acessador do Contrador de Transacoes do Sistema
     * 
     * @return the controladorTransacoes
     */
    public ControladorTransacoes controladorTransacoes() {
	return controladorTransacoes;
    }

    /* Metodos de Atualizacao */

    /**
     * Metodo que atualiza os dados do sistema com dados gravados
     * 
     * @throws Exception
     *             Lanca excecao caso os dados nao tenham sido gravados
     *             anteriormente
     */
    public void atualizaDados() throws Exception {

	ControladorImovel.setInstance((ControladorImovel) PersistenciaDados
		.ler(ARQUIVO_DO_SISTEMA));
	ControladorCliente.setInstance((ControladorCliente) PersistenciaDados
		.ler(ARQUIVO_DO_SISTEMA));
	ControladorFuncionario
		.setInstance((ControladorFuncionario) PersistenciaDados
			.ler(ARQUIVO_DO_SISTEMA));
	ControladorTransacoes
		.setInstance((ControladorTransacoes) PersistenciaDados
			.ler(ARQUIVO_DO_SISTEMA));
	ControladorPedidos.setInstance((ControladorPedidos) PersistenciaDados
		.ler(ARQUIVO_DO_SISTEMA));
	ControladorAlugueis.setInstance((ControladorAlugueis) PersistenciaDados
		.ler(ARQUIVO_DO_SISTEMA));

	ControladorLogin.setInstance(PersistenciaDados.ler(ARQUIVO_DO_SISTEMA));

	// Verificar necessidade!

	this.controladorImoveis = ControladorImovel.getInstance();
	this.controladorClientes = ControladorCliente.getInstance();
	this.controladorFuncionarios = ControladorFuncionario.getInstance();
	this.controladorTransacoes = ControladorTransacoes.getInstance();
	this.controladorPedidos = ControladorPedidos.getInstance();
	this.controladorAlugueis = ControladorAlugueis.getInstance();
	this.controladorLogin = ControladorLogin.getInstance();

	controladorTransacoes.atualizaControlador();

	Imovel.setCriadorDeRegistro((Integer) PersistenciaDados
		.ler(ARQUIVO_DE_REGISTROS));
	Transacao.setCriadorRegistroTransacao((Integer) PersistenciaDados
		.ler(ARQUIVO_DE_REGISTROS));
    }

    /**
     * Metodo que salva os dados do sistema em um arquivo
     */
    public void salvarDados() {

	controladorTransacoes.atualizaControlador();

	PersistenciaDados.gravar(ControladorImovel.getInstance(),
		ARQUIVO_DO_SISTEMA);
	PersistenciaDados.gravar(ControladorCliente.getInstance(),
		ARQUIVO_DO_SISTEMA);
	PersistenciaDados.gravar(ControladorFuncionario.getInstance(),
		ARQUIVO_DO_SISTEMA);
	PersistenciaDados.gravar(ControladorTransacoes.getInstance(),
		ARQUIVO_DO_SISTEMA);
	PersistenciaDados.gravar(ControladorPedidos.getInstance(),
		ARQUIVO_DO_SISTEMA);
	PersistenciaDados.gravar(ControladorAlugueis.getInstance(),
		ARQUIVO_DO_SISTEMA);
	PersistenciaDados.gravar(ControladorLogin.getInstance(),
		ARQUIVO_DO_SISTEMA);

	PersistenciaDados.gravar(Imovel.getCriadorDeRegistro(),
		ARQUIVO_DE_REGISTROS);
	PersistenciaDados.gravar(Transacao.getCriadorRegistroTransacao(),
		ARQUIVO_DE_REGISTROS);
    }
}
