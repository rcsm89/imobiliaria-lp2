package imobiliaria.processamento;

import java.io.Serializable;
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
    
    private final String DIRETORIO = "";

    private final String ARQUIVO_DE_IMOVEIS = DIRETORIO + "DadosImoveis.im";
    private final String ARQUIVO_DE_CLIENTES = DIRETORIO + "DadosClientes.im";
    private final String ARQUIVO_DE_FUNCIONARIOS = DIRETORIO + "DadosFuncionarios.im";
    private final String ARQUIVO_DE_TRANSACOES = DIRETORIO + "DadosTransacoes.im";
    private final String ARQUIVO_DE_PEDIDOS = DIRETORIO + "DadosPedidos.im";
    private final String ARQUIVO_DE_ALUGUEIS = DIRETORIO + "DadosAlugueis.im";
    private final String ARQUIVO_DE_LOGINS = DIRETORIO + "DadosLogins.im";
    private final String ARQUIVO_DE_REGISTRO_IMOVEL = DIRETORIO + "RegistrosImovel.dat";
    private final String ARQUIVO_DE_REGISTRO_TRANSACAO = DIRETORIO + "RegistrosTransacoes.dat";

    /* Metodos de Atualizacao */

    /**
     * Metodo que atualiza os dados do sistema com dados gravados
     * 
     * @throws Exception
     *             Lanca excecao caso os dados nao tenham sido gravados
     *             anteriormente
     */
    public void atualizaDados() throws Exception {
    	
    	try {
    		
    		ControladorImovel.setInstance((ControladorImovel)
    				PersistenciaDados.ler(ARQUIVO_DE_IMOVEIS));
    		
    		ControladorCliente.setInstance((ControladorCliente)
    				PersistenciaDados.ler(ARQUIVO_DE_CLIENTES));
    		
    		ControladorFuncionario.setInstance((ControladorFuncionario)
    				PersistenciaDados.ler(ARQUIVO_DE_FUNCIONARIOS));
    		
    		ControladorTransacoes.setInstance((ControladorTransacoes)
    				PersistenciaDados.ler(ARQUIVO_DE_TRANSACOES));
    		
    		ControladorPedidos.setInstance((ControladorPedidos)
    				PersistenciaDados.ler(ARQUIVO_DE_PEDIDOS));
    		
    		ControladorAlugueis.setInstance((ControladorAlugueis)
    				PersistenciaDados.ler(ARQUIVO_DE_ALUGUEIS));
    		
    		ControladorLogin.setInstance((ControladorLogin)
    				PersistenciaDados.ler(ARQUIVO_DE_LOGINS));
    		

    		Imovel.setCriadorDeRegistro((Integer) PersistenciaDados
    				.ler(ARQUIVO_DE_REGISTRO_IMOVEL));
    		Transacao.setCriadorRegistroTransacao((Integer) PersistenciaDados
    				.ler(ARQUIVO_DE_REGISTRO_TRANSACAO));
	
    	} catch (Exception e) {
    		System.out.println("Erro atualizando dados: " + e.getMessage());
    	}
    }

    /**
     * Metodo que salva os dados do sistema em um arquivo
     */
    public void salvarDados() {

	ControladorTransacoes.getInstance().atualizaControlador();
	
	try {
		
		PersistenciaDados.gravar(ControladorImovel.getInstance(),
				ARQUIVO_DE_IMOVEIS);
		
		PersistenciaDados.gravar(ControladorCliente.getInstance(),
				ARQUIVO_DE_CLIENTES);
		
		PersistenciaDados.gravar(ControladorFuncionario.getInstance(),
				ARQUIVO_DE_FUNCIONARIOS);
		
		PersistenciaDados.gravar(ControladorTransacoes.getInstance(),
				ARQUIVO_DE_TRANSACOES);
		
		PersistenciaDados.gravar(ControladorPedidos.getInstance(),
				ARQUIVO_DE_PEDIDOS);
		
		PersistenciaDados.gravar(ControladorAlugueis.getInstance(),
				ARQUIVO_DE_ALUGUEIS);
		
		PersistenciaDados.gravar(ControladorLogin.getInstance(),
				ARQUIVO_DE_LOGINS);
		
		PersistenciaDados.gravar(Imovel.getCriadorDeRegistro(),
				ARQUIVO_DE_REGISTRO_IMOVEL);
		PersistenciaDados.gravar(Transacao.getCriadorRegistroTransacao(),
				ARQUIVO_DE_REGISTRO_TRANSACAO);
		
	} catch (Exception e) {
		System.out.println("Erro Salvando Dados: " + e.getMessage());
	}
    }
}
