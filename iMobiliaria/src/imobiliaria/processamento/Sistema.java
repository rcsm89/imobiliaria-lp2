package imobiliaria.processamento;

import java.io.Serializable;
import java.util.ArrayList;

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
    		
    		ArrayList<Object> controladores = PersistenciaDados.ler(ARQUIVO_DO_SISTEMA);
    		
    		ControladorImovel.setInstance((ControladorImovel) controladores.get(0));
    		
    		ControladorCliente.setInstance((ControladorCliente) controladores.get(1));
    		
    		ControladorFuncionario
    		.setInstance((ControladorFuncionario) controladores.get(2));
    		
    		ControladorTransacoes
    		.setInstance((ControladorTransacoes) controladores.get(3));
    		
    		ControladorPedidos.setInstance((ControladorPedidos) controladores.get(4));
    		
    		ControladorAlugueis.setInstance((ControladorAlugueis) controladores.get(5));
    		
    		ControladorLogin.setInstance((ControladorLogin)
    				controladores.get(6));
    		
    		
    		
    		
    		
    		

	/*Imovel.setCriadorDeRegistro((Integer) PersistenciaDados
		.ler(ARQUIVO_DE_REGISTROS));
	Transacao.setCriadorRegistroTransacao((Integer) PersistenciaDados
		.ler(ARQUIVO_DE_REGISTROS));*/
    	} catch (Exception e) {
    		System.out.println("Erro atualizando dados: " + e.getMessage());
    	}
    }

    /**
     * Metodo que salva os dados do sistema em um arquivo
     */
    public void salvarDados() {

	ControladorTransacoes.getInstance().atualizaControlador();
	
	ArrayList<Object> controladores = new ArrayList<Object>();
	
	try {
		
		controladores.add(ControladorImovel.getInstance());
		controladores.add(ControladorCliente.getInstance());
		controladores.add(ControladorFuncionario.getInstance());
		controladores.add(ControladorTransacoes.getInstance());
		controladores.add(ControladorPedidos.getInstance());
		controladores.add(ControladorAlugueis.getInstance());
		controladores.add(ControladorLogin.getInstance());
		
	PersistenciaDados.gravar(controladores,
		ARQUIVO_DO_SISTEMA);

	PersistenciaDados.gravar(Imovel.getCriadorDeRegistro(),
		ARQUIVO_DE_REGISTROS);
	PersistenciaDados.gravar(Transacao.getCriadorRegistroTransacao(),
		ARQUIVO_DE_REGISTROS);
	} catch (Exception e) {
		System.out.println("Erro Salvando Dados: " + e.getMessage());
	}
    }
}
