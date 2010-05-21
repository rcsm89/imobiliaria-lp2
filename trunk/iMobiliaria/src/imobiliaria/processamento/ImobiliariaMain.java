package imobiliaria.processamento;

import java.util.HashMap;
import imobiliaria.aux.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ImobiliariaMain {

	private static Sistema sistema = new Sistema();

	public static void main(String[] args) {
		sistema.getLoginClientes().put("cliente", "1234");

		boolean repete = true;
		int opcao = 0;

		do {

			System.out.print(promptPrincipal());

			opcao = recebeInteiroEntre(0, 4);

			switch (opcao) {

			case 1:

				System.out.println("Digite seu Login: ");
				String loginAdmin = VerificaInvalido.recebeString();
				String senhaAdmin = VerificaInvalido.recebeString();

				if (!(loginAdmin.equals("admin") && senhaAdmin.equals("admin"))) {
					System.out.println("Login e/ou Senha invalida!");
				} else {
					opcoesAdmin();
				}

				break;

			case 2:

				System.out.println("Func AEW!");
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
						System.out.println("Cliente Logado com Sucesso");
						opcoesCliente();
					} else {
						System.out.println("Login e/ou Senha invalida!");
					}
				} else {
					System.out.println("Login falhou");
				}

				break;

			case 4:

				System.out.println("Cadastra Cliente");
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
		return " * ----------"
				+ "------------- * Imobiliaria * ----------------------- * \n"
				+ "1 - Administrador.\n" + "2 - Funcionario.\n"
				+ "3 - Cliente.\n" + "4 - Cadastrar Cliente.\n" + "0 - Sair.\n"
				+ "-------------Digite o numero da opcao desejada-------------"
				+ "\n" + "Número da opção: ";
	}

	/*
	 * private static Cliente criaCliente() { //cpf = recebe(cpf); //nascimento
	 * = recebe(calendar - data); //endereco = recebe(endereco); //nome =
	 * recebe(nome); //preferencia = rece(TipoImovel.CASA); Calendar nascimento
	 * = new GregorianCalendar(1991, Calendar.APRIL, 4); String endereco =
	 * "Rua alBerto de brito, 844"; TipoImovel preferencia = TipoImovel.CASA;
	 * 
	 * try { return new Cliente("12345678910", nascimento, endereco, "Bruno",
	 * preferencia); } catch (Exception e) { return null; }
	 * 
	 * try{ criaCliente(); }catch(Exception e){
	 * System.out.println(e.getMessage()); repete = false; break; }
	 * 
	 * }
	 */

	/* Metodos de Administrador */


	
	/* Metodos de Administrador */

	// SuppressWarnings pelo Cast do Object para HashMap
	@SuppressWarnings("unchecked")
	private static void opcoesAdmin() {
		System.out
				.println("\n" + "Menu de Administracao - iMobiliaria" + "1. ");
		
		boolean menuAdminRodando = true;
		
		System.out.println("\n" + "Menu de Administracao - iMobiliaria\n"
				+ "1. Verificar e Alterar dados de Clientes\n"
				+ "2. Verificar e Alterar dados de Funcionarios\n"
				+ "3. Verificar e Alterar dados de Imoveis\n"
				+ "4. Efetua pedido de Cliente\n"
				+ "5. Pagamento de Contas\n"
				+ "6. Sair");
		
		int opcaoEscolhida = recebeInteiroEntre(1, 6);

		switch (opcaoEscolhida) {

		case 1:
			// CRDU de Clientes
			//crduClientes();
			break;
		case 2:
			// CRDU de Funcioanrios
			//crduFuncionarios();
			break;
		case 3:
			// CRDU de Imoveis
			//crduImoveis();
			break;
		case 4:
			// Efetua pedido do Cliente
			break;
		case 5:

			// Se ele ja pagou esse Mes
			Object[] folhaDePagamento;
			
			try {
				
				folhaDePagamento = sistema.efetuaPagamentoNoMes();
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				break;
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
			
			System.out.println("-------------------------------------------\n" +
					"Total Antigo : " + (Double) folhaDePagamento[1] + "\n" +
							"Despesas: " + (Double) folhaDePagamento[2] + "\n" +
									"Novo Total: " + sistema.getCaixaTotal());
			
			break;
		case 6:
			menuAdminRodando = false;
			break;
		}
		
		/* Se ele nao digitou para sair (opcao 6)
		 * O menu eh chamado novamente (Chamada Recursiva)
		 */
		
		if (menuAdminRodando) {
			opcoesAdmin();
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

	private static void opcoesCliente() {
		boolean menuClienteRodando = true;

		System.out
				.print("_____________Menu de Cliente__________ \n"
						+ "1 - Listar Imoveis da Imobiliaria.\n"
						+ "2 - Ver informacoes do cliente.\n"
						+ "3 - Deslogar.\n"
						+ "-------------Digite o numero da opcao desejada-------------"
						+ "\n" + "Número da opção: ");

		int opcaoEscolhida = recebeInteiroEntre(1, 3);

		switch (opcaoEscolhida) {

		case 1:

			System.out.println("Listando Imoveis da Imobiliaria\n");
			break;

		case 2:

			System.out.println("Vendo Informacoes do Cliente\n");
			break;

		case 3:

			System.out.println("Voce foi deslogado\n");
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
}