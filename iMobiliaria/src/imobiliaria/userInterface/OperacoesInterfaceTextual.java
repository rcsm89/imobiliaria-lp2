package imobiliaria.userInterface;

import imobiliara.auxiliar.EstadoImovel;
import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorAlugueis;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorFuncionario;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorPedidos;
import imobiliaria.controladores.ControladorTransacoes;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.FolhaDePagamento;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.exceptions.ClienteNotFoundException;
import imobiliaria.processamento.Sistema;
import imobiliaria.util.FormataEntrada;
import imobiliaria.util.MetodoEntrada;

import java.util.Calendar;

public class OperacoesInterfaceTextual {

    private String lineSep;

    public OperacoesInterfaceTextual(Sistema sis) {
	this.lineSep = System.getProperty("line.separator");
    }

    // METODOS DA INTERFACE DE CLIENTE

    protected void verificarDadosPessoais(Cliente cliente) {
    	try {
    		System.out.println(ControladorCliente.getInstance().exibeCliente(
		cliente.getCpf()));
    	} catch (ClienteNotFoundException e) {
    		System.out.println("Cliente nao encontrado");
    	}
    }

    protected void historicoCompras(Cliente cl) {
	int contador = 1;
	System.out.println(lineSep);
	for (Imovel imovel : cl.getHistoricoCompras().getImoveis()) {
	    System.out.println(String.format("%2d. %s", contador, imovel));
	    contador++;
	}
	System.out.println(lineSep);
    }

    protected void historicoVendas(Funcionario func) {
	int contador = 1;
	System.out.println(lineSep);
	for (Imovel imovel : func.getImoveisVendidos().getImoveis()) {
	    System.out.println(String.format("%2d. %s", contador, imovel));
	    contador++;
	}
	System.out.println(lineSep);
    }

    protected void listarImoveis() {
	System.out
		.println(lineSep
			+ "======================= Listagem de Imoveis ======================="
			+ lineSep
			+ ControladorImovel.getInstance().listaImoveis(
				EstadoImovel.A_VENDA));
    }

    protected void listarClientes() {
	System.out
		.println(lineSep
			+ "======================= Listagem de Clientes ======================="
			+ lineSep + ControladorCliente.getInstance().listaClientes());
    }

    protected void listarFuncionarios() {
	System.out
		.println(lineSep
			+ "===================== Listagem de Funcionarios ====================="
			+ lineSep
			+ ControladorFuncionario.getInstance().listaFuncionarios());
    }

    protected void fazerPedido(String cpfCliente) {
	System.out
		.println(lineSep
			+ "========================== Fazer Pedido =========================="
			+ lineSep);

	String registroImovel = MetodoEntrada
		.recebeString("Digite o Registro do Imovel que for pedir: ");

	try {

	    ControladorPedidos.getInstance().adicionaPedido(registroImovel, cpfCliente);

	} catch (Exception e) {

	    System.out.println("Erro: " + e.getMessage());

	}
    }

    // Metodos da Interface

    protected void cadastroDeClientes() {

	String nome, cpf, endereco;
	Calendar dataNascimento;
	TipoImovel preferencia;

	boolean repeteCadastro;
	int opcaoImovel = 0;
	do {
	    System.out.println(lineSep
		    + "----------------- Cadastro de Cliente -----------------"
		    + lineSep);

	    nome = MetodoEntrada.recebeString("Digite o Nome do Cliente: ");

	    cpf = MetodoEntrada.recebeString("CPF (Apenas os 11 digitos): ");

	    System.out.print("Data de Nascimento (dd/MM/AAAA): ");
	    dataNascimento = MetodoEntrada.recebeData();

	    endereco = MetodoEntrada.recebeString("Endereco: ");

	    System.out.print("\nQual sua preferência de imovel?\n"
		    + "1. Casa\n" + "2. Apartamento\n" + "3. Terreno\n"
		    + "---------------\n");

	    opcaoImovel = MetodoEntrada.recebeInt();

	    try {
		preferencia = TipoImovel.values()[opcaoImovel - 1];
	    } catch (IndexOutOfBoundsException erro) {
		preferencia = null;
	    }

	    try {

		if (ControladorCliente.getInstance().adicionaCliente(cpf,
			dataNascimento, endereco, nome, preferencia)) {
		    repeteCadastro = false;
		} else {
		    throw new Exception("Cliente ja existente");
		}

	    } catch (Exception erro) {
		System.out.println("\n=========== AVISO =============\n"
			+ erro.getMessage());
		repeteCadastro = true;
	    }

	} while (repeteCadastro);

	Cliente novoCliente = ControladorCliente.getInstance().getCliente(
		FormataEntrada.cpf(cpf));

	System.out.println(lineSep
		+ "=========== Cadastro Efetuado com Sucesso ============="
		+ lineSep + lineSep + novoCliente.getLogin().toString()
		+ lineSep + lineSep
		+ "        Voce podera redefinir suas preferencias\n"
		+ "        posteriormente quando acessar sua conta\n");
    }

    protected void cadastroDeFuncionario() {

	Funcionario novoFunc = null;

	String nome, cpf, endereco, creci;
	Calendar dataNascimento;

	boolean repeteCadastro;
	do {
	    System.out.println(lineSep
		    + "--------------- Cadastro de Funcionario ---------------"

		    + lineSep);

	    nome = MetodoEntrada.recebeString("Nome do Funcionario: ");

	    cpf = MetodoEntrada.recebeString("CPF (Apenas os 11 digitos): ");

	    System.out.print("Data de Nascimento (dd/MM/AAAA): ");
	    dataNascimento = MetodoEntrada.recebeData();

	    endereco = MetodoEntrada.recebeString("Endereco: ");
	    creci = MetodoEntrada.recebeString("Numero do CRECI: ");

	    try {
		if (ControladorFuncionario.getInstance().adicionaFuncionario(cpf,
			dataNascimento, endereco, nome, creci)) {

		    novoFunc = ControladorFuncionario.getInstance()
			    .getFuncionarioPorCreci(creci);

		    repeteCadastro = false;
		} else {
		    throw new Exception("Funcionario ja existente");
		}

	    } catch (Exception erro) {
		System.out.println("\n=========== AVISO =============\n"
			+ erro.getMessage());
		repeteCadastro = true;
	    }

	} while (repeteCadastro);

	System.out.println(lineSep
		+ "=========== Cadastro Efetuado com Sucesso ============="
		+ lineSep + lineSep + novoFunc.getLogin().toString() + lineSep
		+ lineSep + "        Voce podera redefinir suas preferencias\n"
		+ "        posteriormente quando acessar sua conta\n");
    }

    protected void cadastroDeImovel() {

	String nome, endereco;

	TipoImovel tipoDoImovel;
	TipoContratual tipoContratual;
	Area area = null;
	double valor, comprimento, largura;

	boolean repeteCadastro;

	int opcaoImovel;
	int opcaoContrato;

	do {
	    System.out.println(lineSep
		    + "------------------ Cadastro de Imovel -----------------"
		    + lineSep);

	    nome = MetodoEntrada.recebeString("Descricao: ");
	    endereco = MetodoEntrada.recebeString("Endereco: ");

	    // TIPO DO IMOVEL
	    System.out
		    .print("\nQual eh o tipo do imovel?\n" + "1. Casa\n"
			    + "2. Apartamento\n" + "3. Terreno\n"
			    + "---------------\n");

	    opcaoImovel = MetodoEntrada.recebeInt();

	    // TIPO DO CONTRATO
	    System.out.print("\nQual eh o tipo do contrato?\n" + "1. Aluguel\n"
		    + "2. Venda\n" + "---------------\n");
	    opcaoContrato = MetodoEntrada.recebeInt();

	    // DIMENSOES DO IMOVEL
	    System.out.print("\nQuais as dimensoes da area?\n"
		    + "Comprimento: ");
	    comprimento = MetodoEntrada.recebeDouble();
	    System.out.print("Largura: ");
	    largura = MetodoEntrada.recebeDouble();

	    // PRECO DO IMOVEL
	    System.out.print("\nQual o preco do imovel? R$ ");
	    valor = MetodoEntrada.recebeDouble();

	    // CRIACAO/CADASTRO DO IMOVEL
	    try {
		tipoDoImovel = TipoImovel.values()[opcaoImovel - 1];
		tipoContratual = TipoContratual.values()[opcaoContrato - 1];
		area = new Area(comprimento, largura);
		ControladorImovel.getInstance().addImovel(nome, endereco, valor, area,
			tipoDoImovel, tipoContratual);
		repeteCadastro = false;

	    } catch (Exception erro) {
		System.out.println("\n=========== AVISO =============\n"
			+ erro.getMessage());
		repeteCadastro = true;
	    }

	} while (repeteCadastro);

	System.out.println(lineSep
		+ "======= Cadastro De Imovel Efetuado com Sucesso ======="
		+ lineSep);
    }

    // Metodos que eu criei (Yuri)

    protected void verificaDadosPessoais(Funcionario func) {

	System.out.println(lineSep
		+ ControladorFuncionario.getInstance().exibeFuncionarioPorCreci(
			func.getCreci()) + lineSep);

    }

    protected void excluirCliente() {
	System.out
		.println(lineSep
			+ "====================== Exclusao de Cliente ======================"
			+ lineSep);

	boolean continuaRodandoMenu = true;

	do {
	    String cpf = MetodoEntrada
		    .recebeString("Digite o CPF (XXX.XXX.XXX-XX) do Cliente que deseja Excluir: ");

	    String informacoes;
	    
	    try {
	    informacoes = ControladorCliente.getInstance().exibeCliente(cpf);
	    } catch (ClienteNotFoundException e) {
	    	System.out.println("Cliente nao encontrado");
	    	return;
	    }

	    System.out.println(informacoes);

	    System.out.println(lineSep + "1. Confirmar" + lineSep + "2. Sair");

	    int opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case 1:
		if (ControladorCliente.getInstance().removeCliente(cpf)) {

		    System.out.println("Cliente removido com Sucesso!");
		    continuaRodandoMenu = false;

		} else {

		    System.out.println("Falha ao remover cliente");
		}
		break;

	    case 2:
		continuaRodandoMenu = false;
		break;

	    default:
		System.out.println("Opcao Invalida!");

	    }
	} while (continuaRodandoMenu);
    }

    protected void verificaInformacoesCliente() {

	System.out
		.println(lineSep
			+ "=================== Verifica Dados de Cliente ==================="
			+ lineSep);

	String cpf = MetodoEntrada
		.recebeString("Digite o CPF (XXX.XXX.XXX-XX) do Cliente que deseja verificar:");

	String informacoes;
	
	try {
	informacoes = ControladorCliente.getInstance().exibeCliente(cpf);
	} catch (ClienteNotFoundException e) {
		System.out.println("Cliente nao encontrado");
		return;
	}

	System.out.println(informacoes + lineSep);

    }

    protected void excluirImovel() {

	System.out
		.println(lineSep
			+ "====================== Excluir Imovel ======================"
			+ lineSep);

	boolean continuaRodandoMenu = true;

	do {
	    String registroImovel = MetodoEntrada
		    .recebeString("Digite o registro do imovel que deseja Excluir: ");

	    String informacoes = ControladorImovel.getInstance().exibeImovel(
		    registroImovel);

	    if (informacoes == null) {
		System.out.println("Imovel nao cadastrado");
		return;
	    }

	    System.out.println(lineSep + informacoes);

	    System.out.println(lineSep + "1. Confirmar" + lineSep + "2. Sair");

	    int opcao = MetodoEntrada.recebeInt();

	    try {
		switch (opcao) {
		case 1:
		    if (ControladorImovel.getInstance().removeImovel(registroImovel)) {

			System.out.println("Imovel removido com Sucesso!");
			continuaRodandoMenu = false;

		    } else {

			System.out.println("Falha ao remover imovel");
		    }
		    break;

		case 2:
		    continuaRodandoMenu = false;
		    break;

		default:
		    System.out.println("Opcao Invalida");

		}

	    } catch (Exception e) {
		System.out.println("Erro: " + e.getMessage());
	    }

	} while (continuaRodandoMenu);
    }

    protected void informacoesImovel() {

	System.out
		.println(lineSep
			+ "====================== Informacoes de Imovel ======================"
			+ lineSep);

	String registroImovel = MetodoEntrada
		.recebeString("Digite o Registro do Imovel que deseja verificar: ");

	String informacoes = ControladorImovel.getInstance().exibeImovel(
		registroImovel);

	if (informacoes == null) {
	    System.out.println("Imovel nao cadastrado");
	    return;
	}

	System.out.println(lineSep + informacoes);

    }

    protected void excluirFuncionario() {

	System.out
		.println(lineSep
			+ "====================== Excluir Funcionario ======================"
			+ lineSep);

	boolean continuaRodandoMenu = true;

	do {

	    String creci = MetodoEntrada
		    .recebeString("Digite o CRECI do Funcionario que deseja Excluir: ");

	    String informacoes = "";

	    try {
		informacoes = ControladorFuncionario.getInstance()
			.exibeFuncionarioPorCreci(creci);
	    } catch (NullPointerException e) {
		System.out.println("Funcionario nao cadastrado");
		return;
	    }

	    System.out.println(informacoes);

	    System.out.println(lineSep + "1. Confirmar" + lineSep + "2. Sair");

	    int opcao = MetodoEntrada.recebeInt();

	    switch (opcao) {
	    case 1:

		try {
		    if (ControladorFuncionario.getInstance().removeFuncionario(creci)) {

			System.out.println("Funcionario removido com Sucesso!");
			continuaRodandoMenu = false;

		    } else {
			System.out.println("Falha ao remover Funcionario");
		    }

		} catch (Exception e) {
		    System.out.println("Erro: " + e.getMessage());
		}
		break;

	    case 2:
		continuaRodandoMenu = false;
		break;

	    default:
		System.out.println("Opcao Invalida!");
	    }
	} while (continuaRodandoMenu);
    }

    protected void verificaInformacoesFuncionario() {

	System.out
		.println(lineSep
			+ "=================== Verifica Dados de Funcionario ==================="
			+ lineSep);

	String creci = MetodoEntrada
		.recebeString("Digite o CRECI do Funcionario que deseja verificar:");

	String informacoes;

	try {
	    informacoes = ControladorFuncionario.getInstance()
		    .exibeFuncionarioPorCreci(creci);
	} catch (NullPointerException e) {
	    System.out.println("Funcionario nao cadastrado");
	    return;
	}

	System.out.println(informacoes + lineSep);

    }

    protected void efetuaPedido() {

	System.out
		.println(lineSep
			+ "======================= Efetua Pedido ======================="
			+ lineSep);

	String registroImovel = MetodoEntrada
		.recebeString("Registro do Imovel que foi efetuado pedido: ");
	String creciFuncionario = MetodoEntrada
		.recebeString("Digite o CRECI do Funcionario que realizou a Compra: ");

	try {
	    ControladorPedidos.getInstance().adicionaPedido(registroImovel,
		    creciFuncionario);
	    System.out.println("Pedido Efetuado com Sucesso!");
	} catch (Exception e) {
	    System.out
		    .println("Impossivel efetuar Pedido - Informacoes Invalidas");
	}
    }

    protected void efetuarPagamento() {

	try {
	    FolhaDePagamento folhaDePagamento = ControladorTransacoes.getInstance()
		    .efetuaPagamentoNoMes();
	    System.out.println("Folha de Pagamento do Mes:" + lineSep
		    + folhaDePagamento.getFolhaDePagamentoString());

	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	}

    }

    protected void verificaSaldoAtual() {

	System.out.println(lineSep + "Saldo Atual do Caixa: "
		+ ControladorTransacoes.getInstance().caixa() + lineSep);
    }
    
    // NOVOS METODOS!1111!11 (Yuri)
    
    protected void listaPedidosDeCliente(String cpfCliente) {
    	System.out.println(ControladorPedidos.getInstance().
    			listaPedidosDeCliente(cpfCliente));
    }
    
    protected void listaAlugueisDeCliente(String cpfCliente) {
    	System.out.println(ControladorAlugueis.getInstance().
    			listaAlugueisDeCliente(cpfCliente));
    	
    }
     
    

}
