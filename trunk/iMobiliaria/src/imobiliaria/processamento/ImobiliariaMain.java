package imobiliaria.processamento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import imobiliaria.util.*;

import java.util.Scanner;

public class ImobiliariaMain {

	private static Sistema sistema = new Sistema();

	public static void main(String[] args) throws Exception {
		sistema.getLoginClientes().put("cliente", "1234");
		sistema.getLoginFuncionarios().put("func", "4321");

		boolean repete = true;
		int opcao = 0;

		do {

			System.out.print(promptPrincipal());

			opcao = recebeInteiroEntre(0, 4);

			switch (opcao) {

			case 1:

				System.out.print("Login: ");
				String loginAdmin = VerificaInvalido.recebeString();
				System.out.print("Senha: ");
				String senhaAdmin = VerificaInvalido.recebeString();

				if (!(loginAdmin.equals("admin") && senhaAdmin.equals("admin"))) {
					System.out.println("Login falhou!");
				} else {
					System.out.println("Administrador logado com Sucesso!");
					opcoesAdmin();
				}

				break;

			case 2:

				System.out.print("Login: ");
				String loginFunc = VerificaInvalido.recebeString();
				System.out.print("Senha: ");
				String senhaFunc = VerificaInvalido.recebeString();

				HashMap<String, String> loginFuncs = sistema
						.getLoginFuncionarios();
				if (loginFuncs.containsKey(loginFunc)) {
					if (loginFuncs.get(loginFunc).equals(senhaFunc)) {
						System.out.println("Funcionario Logado com Sucesso!");
						opcoesFunc();
					}/* else {
						System.out.println("Login e/ou Senha invalida!");
					}*/
				} else {
					System.out.println("Login falhou!");
				}

				break;

			case 3:

				System.out.print("Login: ");
				String loginCliente = VerificaInvalido.recebeString();
				System.out.print("Senha: ");
				String senhaCliente = VerificaInvalido.recebeString();

				HashMap<String, String> loginClientes = sistema
						.getLoginClientes();
				if (loginClientes.containsKey(loginCliente)) {
					if (loginClientes.get(loginCliente).equals(senhaCliente)) {
						System.out.println("Cliente Logado com Sucesso!");
						opcoesCliente();
					}/* else {
						System.out.println("Login e/ou Senha invalida!");
					}*/
				} else {
					System.out.println("Login falhou!");
				}

				break;

			case 4:

				System.out.println("\n - - - - - - - - - - - Cadastramento de Cliente" + 
						"- - - - - - - - - - - -\n");
				criaCliente();
				System.out.println("Cliente cadastrado com Sucesso!");
				break;

			case 0:

				repete = false;
				break;

			default:
				System.out.println("\nDigite umas das opções sugeridas.");
				break;
			}

		} while (repete);
		System.out.println("Sistema Finalizado");
	}

	private static String promptPrincipal() {
		return " * ----------------------- * Imobiliaria * ------------------------ * \n"
				+ "1 - Administrador.\n"
				+ "2 - Funcionario.\n"
				+ "3 - Cliente.\n"
				+ "4 - Cadastrar Cliente.\n"
				+ "0 - Sair.\n"
				+ "-------------Digite o numero da opcao desejada-------------"
				+ "\n" + "Número da opção: ";
	}
	
	
	/* Metodos de Imovel */

	private static void crduImovel() throws Exception {

		boolean menuImovelRodando = true;

		System.out.println("\nMenu de Funcionario - iMobiliaria\n" +
				"1. Criar novo Imovel\n" +
				"2. Listar Imoveis\n" +
				"3. Modificar Imovel\n" +
				"4. Deletar Imovel\n" +
				"5. Sair");

		int opcaoEscolhida = recebeInteiroEntre(1, 5);

		switch (opcaoEscolhida) {

		case 1:

			// Criando Imovel
			sistema.getTodosImoveis().adicionaImovel(criaImovel());
			break;

		case 2:

			// Listando Imoveis
			listagemDeImovel(sistema.getTodosImoveis().getImoveis());
			break;

		case 3:

			System.out.println("Modifica Imovel");
			break;

		case 4:

			System.out.println("Deleta Imovel");
			removeImovel();
			break;

		case 5:
			menuImovelRodando = false;
			System.out
					.println("---------------------------------------------------------");
			break;
		}

		if (menuImovelRodando) {
			crduImovel();
		}

	}

	private static void removeImovel() {
		final int NUMERO_IMOVEIS = sistema.getTodosImoveis().getImoveis()
				.size();
		int registroDoImovel;
		int numeroDoImovel;
		boolean removeImovel = false;

		while (!removeImovel)

			System.out
					.println("Digite o numero do imovel que deseja remover: \n");
		listagemDeImovel(sistema.getTodosImoveis().getImoveis());
		System.out.print("Numero do Imovel: ");

		numeroDoImovel = recebeInteiroEntre(1, NUMERO_IMOVEIS);
		registroDoImovel = sistema.getTodosImoveis().getImoveis().get(
				numeroDoImovel).getRegistroImovel();

		if (sistema.getTodosImoveis().removeImovel(registroDoImovel))
			removeImovel = true;

	}

	private static Imovel criaImovel() throws Exception {
		String nome = null;
		String endereco = null;
		double preco = 0.0;
		double comprimento = 0.0;
		double largura = 0.0;
		TipoImovel tipoDoImovel = null;
		TipoContratual tipoContratualDoImovel = null;
		int numero;

		System.out.print("Nome do imovel: ");
		nome = recebeNome();
		System.out.print("Endereco: ");
		endereco = recebeEndereco();
		System.out.print("Preco: ");
		preco = VerificaInvalido.recebeDouble();
		System.out.print("Comprimento: ");
		comprimento = VerificaInvalido.recebeDouble();
		System.out.print("Largura: ");
		largura = VerificaInvalido.recebeDouble();

		while (tipoDoImovel == null) {
			System.out
					.print("Digite o numero correspondente ao tipo do imovel: \n"
							+ "1. CASA\n"
							+ "2. APARTAMENTO\n"
							+ "3. TERRENO\n"
							+ "Numero: ");
			numero = VerificaInvalido.recebeInteiro();
			if (numero == 1)
				tipoDoImovel = TipoImovel.CASA;
			else if (numero == 2)
				tipoDoImovel = TipoImovel.APARTAMENTO;
			else if (numero == 3)
				tipoDoImovel = TipoImovel.TERRENO;
			else
				System.out.println("Numero de terreno Invalido");
		}

		while (tipoContratualDoImovel == null) {
			System.out
					.print("Digite o numero correspondente ao tipo do imovel: \n"
							+ "1. ALUGUEL\n" + "2. VENDA\n" + "Numero: ");
			numero = VerificaInvalido.recebeInteiro();
			if (numero == 1)
				tipoContratualDoImovel = TipoContratual.ALUGUEL;
			if (numero == 2)
				tipoContratualDoImovel = TipoContratual.VENDA;
			else
				System.out
						.println("Numero do tipo contratual de imovel Invalido");
		}

		return new Imovel(nome, endereco, preco,
				new Area(comprimento, largura), tipoDoImovel,
				tipoContratualDoImovel);

	}

	/* Metodos de Cliente */

	private static void opcoesCliente() {
		boolean menuClienteRodando = true;
		System.out.print("_____________Menu de Cliente__________ \n" +
				"1 - Listar Imoveis da Imobiliaria.\n" +
				"2 - Ver informacoes do cliente.\n" +
				"0 - Deslogar.\n" +
				"-------------Digite o numero da opcao desejada-------------" +
				"\n" + "Número da opção: ");

		int opcaoEscolhida = recebeInteiroEntre(0, 2);

		switch (opcaoEscolhida) {

		case 1:

			System.out.println("Listando Imoveis da Imobiliaria\n");
			break;

		case 2:

			System.out.println("Vendo Informacoes do Cliente\n");
			break;

		case 0:
			System.out.println("Saindo...");
			menuClienteRodando = false;
			break;

		default:

			System.out.println("\nDigite umas das opções sugeridas.");
			break;

		}
		if (menuClienteRodando) {
			opcoesCliente();
		}
	}

	private static void crduClientes() throws Exception {
		boolean menuCrduCliente = true;
		int opcaoCliente1 = 0;

		System.out.println("----------------\n" +
				"1. Cadastrar um cliente\n" +
				"2. Verificar dados do cliente\n" +
				"3. Alterar dados do cliente\n" +
				"0. Sair\n" +
				"-------------Digite o numero da opcao desejada-------------" +
				"\n" + "Número da opção: ");

		opcaoCliente1 = recebeInteiroEntre(0, 3);

		switch (opcaoCliente1) {

		case 1:
			
			
			// cadastrar um cliente

			/*
			 * (Yuri) Thiago, nessa parte do codigo eu axo q o ideal seria fazer
			 * como bruno fez o metodo criaImovel() eh basicamente um metodo que
			 * retorna um Imovel ja pronto, daí vc só precisa fazer a associacao
			 * Cliente novoCliente = criaCliente();
			 */ 
			
			try {
				sistema.getTodosClientes().adicionaCliente(criaCliente());//cadastraCliente
				System.out.println("Cliente adicionado com sucesso!");
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
			
			break;

		case 2:
			System.out.println("Verifica dados de cliente");

			break;

		case 3:
			break;

		case 0:
			System.out.println("Saindo...\n");
			menuCrduCliente = false;
			break;
		}
		
		if (menuCrduCliente) {
			crduClientes();
		}
	}

	private static Cliente criaCliente() throws Exception {
		System.out.print("Digite o Nome do Cliente: ");
		String nome = recebeNome();
		System.out.print("CPF: ");
		String cpf = recebeCpf();
		System.out.print("Data de Nascimento (dd/MM/AAAA): ");
		Calendar data = VerificaInvalido.recebeData();
		System.out.print("Endereco: ");
		String endereco = recebeEndereco();
		System.out.print("Qual sua preferência de imóvel?\n" +
				"1. Casa\n" +
				"2. Apartamento\n" + 
				"3. Terreno\n" +
				"---------\n" +
				"Escolha: ");
		TipoImovel pref = recebePreferencia();
		return new Cliente(cpf, data, endereco, nome, pref);
		
	}
	/* Metodos de Funcionario */

	private static void opcoesFunc() throws Exception {

		boolean menuFuncRodando = true;

		System.out.println(
				"-----Menu de Funcionario - iMobiliaria-----\n" +
				"1. Cadastrar, Verificar e Alterar dados de Clientes\n" +
				"2. Cadastrar, Verificar e Alterar dados de Imoveis\n" + 
				"3. Efetua pedido de Cliente\n" +
				"4. Listar clientes\n" +
				"5. Minhas informações\n" +
				"0. Sair\n" +
				"-------------Digite o numero da opcao desejada-------------" +
				"\n" + "Número da opção: ");

		int opcaoEscolhidaF = recebeInteiroEntre(0, 5);

		switch (opcaoEscolhidaF) {

		case 1:
			crduClientes();
			break;

		case 2:
			crduImovel();
			break;

		case 3:
			efetuaPedido();
			break;

		case 4:
			// funcInfo();
			break;

		case 5:
			// lista clientes
			break;
		
		case 0:
			System.out.println("Saindo...");
			menuFuncRodando = false;
			break;
		}

		if (menuFuncRodando) {
			opcoesFunc();
		}
	}

	// (Yuri) Fiz esse jah que eh um metodo que Administrador tava precisando
	// tambem

	private static void efetuaPedido() throws Exception {
		
		System.out.println("Listagem de Pedidos: \n");
		
		TreeMap<Imovel, Cliente> listaDePedidos = sistema.getListaPedido();
		Set<Imovel> listaImoveisPedidos = listaDePedidos.keySet();
		
		if (listaDePedidos.isEmpty()) {
			throw new Exception("Lista de Pedidos Vazia");
		}
		
		for (Imovel i : listaImoveisPedidos) {
			Cliente cliente = listaDePedidos.get(i);
			
			System.out.println(i.getRegistroImovel() + "Imovel: " + i.getNome()
					+ " Valor: " + i.getValor() + "\n"
					+ i.getEndereco() + " Area: " + i.getArea().getComprimento() + "x"
					+ i.getArea().getLargura() + " Contrato: "
					+ i.getTipoContratual() + " Tipo: " + i.getTipoDoImovel() + "\n"
					+ "Pedido por: " + cliente.getNome() + " CPF: " + cliente.getCpf() + "\n");
		}
		
		
		Imovel imovelASerAnalisado = null;
		do {
			System.out.print("Digite o Registro do Imovel pedido que deseja analisar: ");
			int registro = recebeInteiroEntre(0, 99999999);
			for (Imovel i : listaImoveisPedidos) {
				if (i.getRegistroImovel() == registro) {
					imovelASerAnalisado = i;
					break;
				}
			}
			if (imovelASerAnalisado == null)
				System.out.println("Imovel nao encontrado!");
		} while(imovelASerAnalisado == null);
		
		
		boolean menuOpcoesRodando = true;
		
		do {
			System.out.println("Menu de Opcoes de Pedido: \n" +
					"1. Deletar Pedido\n" +
					"2. Efetuar Pedido\n" +
					"3. Sair");
			
			int opcaoPedido = recebeInteiroEntre(1, 3);
			
			switch (opcaoPedido) {
			case 1:
				
				imovelASerAnalisado.setEstadoDoImovel(EstadoImovel.A_VENDA);
				listaDePedidos.get(imovelASerAnalisado).removePedido(imovelASerAnalisado);
				listaDePedidos.remove(imovelASerAnalisado);
				System.out.println("Pedido removido com sucesso!");
				
				break;
			case 2:
				// efetua pedido
				
				
				Funcionario funcionario = null;
				
				do {
					System.out.print("CRECI do Funcionario que realisou a compra: ");
					String creci = recebeNumero();
					
					for (Funcionario f : sistema.getTodosFuncionarios().getColecaoFuncionarios()) {
						if (f.getCreci().equals(creci)) {
							funcionario = f;
							break;
						}
					}
					if (funcionario == null)
						System.out.println("Funcionario nao encontrado!");
				} while(funcionario == null);
				
				sistema.efetuaPedido(imovelASerAnalisado, funcionario);
				
				System.out.println("Pedido efetuado com Sucesso!");
				
				break;
			case 3:
				menuOpcoesRodando = false;
			}
			
		} while (menuOpcoesRodando);
		
		

	}

	/* Metodos de Administrador */

	private static void opcoesAdmin() {

		boolean menuAdminRodando = true;

		System.out.println("\nMenu de Administracao - iMobiliaria\n" +
				"1. Verificar e Alterar dados de Clientes\n" +
				"2. Verificar e Alterar dados de Funcionarios\n" +
				"3. Verificar e Alterar dados de Imoveis\n" +
				"4. Efetua pedido de Cliente\n" +
				"5. Pagamento de Contas\n" + 
				"0. Sair");

		int opcaoEscolhida = recebeInteiroEntre(0, 5);

		switch (opcaoEscolhida) {

		case 1:
			// CRDU de Clientes

			try {
				crduClientes();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

			break;
		case 2:
			// CRDU de Funcioanrios
			crduFuncionarios();
			break;
		case 3:
			// CRDU de Imoveis
			try {

				crduImovel();

			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
			break;
		case 4:
			// Efetua pedido do Cliente
			try {

				efetuaPedido();

			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
			break;
		case 5:

			// Se ele ja pagou esse Mes
			pagamentoDoMes();

			break;
		case 0:
			System.out.println("Saindo...");
			menuAdminRodando = false;
			break;
		}

		/*
		 * Se ele nao digitou para sair (opcao 6) O menu eh chamado novamente
		 * (Chamada Recursiva)
		 */

		if (menuAdminRodando) {
			opcoesAdmin();
		}
	}

	// SuppressWarnings pelo Cast do Object para HashMap
	@SuppressWarnings("unchecked")
	private static void pagamentoDoMes() {
		Object[] folhaDePagamento;

		try {

			folhaDePagamento = sistema.efetuaPagamentoNoMes();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}

		HashMap<String, Double> pagamentoFuncionarios = (HashMap<String, Double>) folhaDePagamento[0];

		if (!(pagamentoFuncionarios.isEmpty())) {
			System.out.println("Funcionario ---------- Salario");
			for (String funcionario : pagamentoFuncionarios.keySet()) {
				System.out.println(funcionario + " ------- "
						+ pagamentoFuncionarios.get(funcionario));
			}
		} else {
			System.out.println("Nenhum funcionario cadastrado");
		}

		System.out.println("-------------------------------------------\n"
				+ "Total Antigo : " + (Double) folhaDePagamento[1] + "\n"
				+ "Despesas: " + (Double) folhaDePagamento[2] + "\n"
				+ "Novo Total: " + sistema.getCaixaTotal());
	}

	private static void crduFuncionarios() {
		boolean continua = true;
		System.out.print("----------------\n" +
				"1. Cadastrar Funcionario\n" +
				"2. Modificar/Remover Funcionario por CPF" +
				"3. Listar funcionarios\n" +
				"0. Sair" +
				"-------------Digite o numero da opcao desejada-------------" +
				"Número da opção: ");
		int opcao = recebeInteiroEntre(0, 3);

		switch (opcao) {
		case 1:

			try {
				sistema.getTodosFuncionarios().addFuncionario(
						criaFuncionario());
				System.out.println("Funcionario adicionado com sucesso!");
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
			break;
			
		case 2:

			try {
				modificaFunc();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
			
		case 3:

			List<Funcionario> funcionarios = sistema.getTodosFuncionarios()
					.getColecaoFuncionarios();

			listagemDeFuncionario(funcionarios);

			break;
			
		case 4:
			continua = false;
			break;
		}
		if (continua)
			crduFuncionarios();

	}
	
	private static Funcionario criaFuncionario() throws Exception {

		System.out.print("Cadastramento de Funcionario\n" +
				"Digite o Nome do Funcionario: ");
		String nome = recebeNome();
		System.out.print("CPF: ");
		String cpf = recebeCpf();
		System.out.print("Data de Nascimento (DD/MM/AAAA): ");
		Calendar dataDeNascimento = VerificaInvalido.recebeData();
		System.out.print("CRECI: ");
		String creci = recebeNumero();
		System.out.print("Endereco: ");
		String endereco = recebeEndereco();

		return new Funcionario(cpf, dataDeNascimento, endereco, nome, creci);

	}

	private static void modificaFunc() throws Exception {

		System.out.print("Digite o nome do Funcionario que deseja Modificar: ");
		String nome = recebeNome();

		List<Funcionario> funcionarios = sistema.getTodosFuncionarios()
				.getFuncionarioPorNome(nome);

		if (funcionarios.isEmpty()) {
			System.out.println("Nenhum Funcionario com esse nome");
			return;
		}

		Funcionario funcionario;

		if (!(funcionarios.size() == 1)) {
			int contador = 0;
			for (Funcionario f : funcionarios) {
				contador++;
				System.out.println(contador + ". " + f.getCpf() + " - "
						+ f.getNome() + " - " + f.getDataNascimento());
			}
			System.out.print("Numero do Funcionario que deseja Modificar: ");
			int opcao = recebeInteiroEntre(1, contador);
			funcionario = funcionarios.get(opcao - 1);
		} else {
			// So um funcionario na lista
			funcionario = funcionarios.get(0);
		}

		/*
		 * Pegou Funcionario, agora vamos listar opcoes para sua modificacao
		 */

		modificaFuncionario(funcionario);

	}

	private static void modificaFuncionario(Funcionario funcionario)
			throws Exception {

		boolean continua = true;

		System.out.print("Informacoes de " + funcionario.getNome() + "\n"
				+ "01. CRECI: " + funcionario.getCreci() + "\n" + "02. Nome: "
				+ funcionario.getCreci() + "\n" + "03. Data de Nascimento: "
				+ funcionario.getDataNascimento() + "\n" + "04. CPF: "
				+ funcionario.getCpf() + "\n" + "05. Endereco: "
				+ funcionario.getEndereco() + "\n"
				+ "06. Valor Total de Vendas no Mes: "
				+ funcionario.getTotalDeVendas() + "\n"
				+ "07. Listar Vendas no Mes\n"
				+ "08. Listar Historico de Vendas\n"
				+ "09. Deleta Funcionario\n" + "10. Sair\n"
				+ "Digite a opcao que deseja: ");

		int opcao = recebeInteiroEntre(1, 10);

		switch (opcao) {
		case 2:
			System.out.print("Digite o novo nome: ");
			String nome = recebeNome();
			funcionario.setNome(nome);

			break;
		case 3:

			System.out.print("Digite a nova Data de Nascimento: ");
			Calendar data = VerificaInvalido.recebeData();
			funcionario.setDataNascimento(data);

			break;
		case 5:

			System.out.print("Digite o novo endereco: ");
			String endereco = recebeEndereco();
			funcionario.setEndereco(endereco);

			break;
		case 7:

			ArrayList<Imovel> imoveisDoMes = funcionario
					.getImoveisVendidosMes().getImoveis();

			listagemDeImovel(imoveisDoMes);

			break;
		case 8:

			ArrayList<Imovel> imoveisHistorico = funcionario
					.getImoveisVendidos().getImoveis();

			listagemDeImovel(imoveisHistorico);

			break;
		case 9:

			sistema.getTodosFuncionarios().removeFuncionario(
					funcionario.getCreci());
			System.out.println("Funcionario Removido com Sucesso!");

			continua = false;

			break;
		case 10:

			continua = false;

			break;
		default:
			System.out.println("Opcao nao pode ser modificada ou listada");
		}

		if (continua) {
			modificaFuncionario(funcionario);
		}

	}

	/* Metodos Auxiliares ou de Entrada */

	private static int recebeInteiroEntre(int min, int max) {
		Scanner entrada = new Scanner(System.in);
		if (!entrada.hasNextInt()) {
			entrada.next();
			System.out.println("Numero invalido! Digite novamente: ");
			return recebeInteiroEntre(min, max);
 		}

		int numero = entrada.nextInt();

		if (numero < min || numero > max) {
			System.out.println("Numero invalido! Digite novamente: ");
			return recebeInteiroEntre(min, max);
		}
		return numero;
	}

	public static String recebeNome() {
		Scanner entrada = new Scanner(System.in);

		if (!entrada.hasNextLine()) {
			entrada.next();
			System.out.println("Nome invalido! Digite novamente: ");
			return recebeNome();
		}

		String string = entrada.nextLine();

		if (VerificaInvalido.nome(string)) {
			System.out.println("Nome invalido! Digite novamente: ");
			return recebeNome();
		}
		return string;
	}

	public static String recebeCpf() {
		Scanner entrada = new Scanner(System.in);

		if (!entrada.hasNextLine()) {
			entrada.next();
			System.out.println("Cpf invalido! Digite novamente: ");
			return recebeCpf();
		}

		String string = entrada.nextLine();

		if (VerificaInvalido.numeroFormatado(string, 11)) {
			System.out.println("Cpf invalido! Digite novamente: ");
			return recebeCpf();
		}
		return string;
	}

	public static String recebeNumero() {
		Scanner entrada = new Scanner(System.in);

		if (!entrada.hasNextLine()) {
			entrada.next();
			System.out.println("Numero invalido! Digite novamente: ");
			return recebeNumero();
		}

		String creci = entrada.nextLine();

		if (VerificaInvalido.numero(creci)) {
			System.out.println("Numero invalido! Digite novamente: ");
			return recebeNumero();
		}
		return creci;
	}

	public static String recebeEndereco() {
		Scanner entrada = new Scanner(System.in);

		if (!entrada.hasNextLine()) {
			entrada.next();
			System.out.println("Endereco invalido! Digite novamente: ");
			return recebeEndereco();
		}

		String string = entrada.nextLine();

		if (VerificaInvalido.endereco(string)) {
			System.out.println("Endereco invalido! Digite novamente: ");
			return recebeEndereco();
		}
		return string;
	}

	public static TipoImovel recebePreferencia() {
		TipoImovel pref = null;
		int escolhaPref = recebeInteiroEntre(1, 3);

		if (escolhaPref == 1) {
			pref = TipoImovel.CASA;

		} else if (escolhaPref == 2) {
			pref = TipoImovel.APARTAMENTO;

		} else if (escolhaPref == 3) {
			pref = TipoImovel.TERRENO;
		}

		return pref;
	}

	private static void listagemDeImovel(ArrayList<Imovel> array) {

		if (array.isEmpty()) {
			System.out.println("Nenhum Imovel para ser listado");
			return;
		}

		for (Imovel i : array) {
			System.out.println(i.getRegistroImovel() + " - " + i.getNome()
					+ " - R$ " + i.getValor() + "\n" + "Estado: "
					+ i.getEstadoDoImovel() + " - Tipo: " + i.getTipoDoImovel()
					+ " - Tipo Contratual: " + i.getTipoContratual()
					+ " - Area: " + i.getArea().getComprimento() + "x"
					+ i.getArea().getLargura() + "\n");
		}
	}

	private static void listagemDeFuncionario(List<Funcionario> array) {

		if (array.isEmpty()) {
			System.out.println("Nada a ser listado");
			return;
		}

		for (Pessoa p : array) {
			System.out.println(p.getCpf() + " - " + p.getNome()
					+ " - Data de Nascimento: " + p.getDataNascimento() + "\n"
					+ "Endereco: " + p.getEndereco() + "\n");
		}
	}

	private static void listagemDeClientes(List<Funcionario> array) {

		if (array.isEmpty()) {
			System.out.println("Nada a ser listado");
			return;
		}

		for (Pessoa p : array) {
			System.out.println(p.getCpf() + " - " + p.getNome()
					+ " - Data de Nascimento: " + p.getDataNascimento() + "\n"
					+ "Endereco: " + p.getEndereco() + "\n");
		}
	}

}