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

	private ControladorImovel controladorImoveis = ControladorImovel.getInstance();
	private ControladorCliente controladorClientes = ControladorCliente.getInstance();
	private ControladorFuncionario controladorFuncionarios = ControladorFuncionario.getInstance();
	private ControladorPedidos controladorPedidos = ControladorPedidos.getInstance();
	private ControladorTransacoes controladorTransacoes = ControladorTransacoes.getInstance();
	private ControladorAlugueis controladorAlugueis = ControladorAlugueis.getInstance();

	/**
	 * Metodo Login - Efetua login baseado no login, senha e Tipo do Usuario
	 * 
	 * @param login
	 *            Login do Usuario
	 * @param senha
	 *            Senha do Usuario
	 * @param tipoDeUsuario
	 *            Tipo de Usuario que vai ser logado
	 * @return True - Se o usuario efetuou login com sucesso <br>
	 *         False - Se o usuario nao conseguiu logar
	 */
	public boolean login(String login, String senha, TipoLogin tipoDeUsuario) {

		if (tipoDeUsuario == TipoLogin.ADMINISTRADOR) {
			if (login.equals("admin") && senha.equals("admin"))
				return true;
			return false;

		} else if (tipoDeUsuario == TipoLogin.FUNCIONARIO) {

			if (controladorFuncionarios.login(login, senha)) {
				return true;
			}
			return false;

		} else {

			if (controladorClientes.login(login, senha)) {
				return true;
			}
			return false;
		}
	}

	/* Metodos Acessadores dos Controladores */
	
	/**
	 * Metodo acessador do Controlador de Alugueis do Sistema
	 * @return the controladorAlugueis
	 */
	public ControladorAlugueis getControladorAlugueis() {
		return controladorAlugueis;
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
		
		PersistenciaDados.gravar(this, ARQUIVO_DO_SISTEMA);
		PersistenciaDados.gravar(Imovel.getCriadorDeRegistro(),
				ARQUIVO_DE_REGISTROS);
		PersistenciaDados.gravar(Transacao.getCriadorRegistroTransacao(),
				ARQUIVO_DE_REGISTROS);
	}
}
