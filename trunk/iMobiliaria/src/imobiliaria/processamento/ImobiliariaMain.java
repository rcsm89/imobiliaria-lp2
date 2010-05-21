package imobiliaria.processamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class ImobiliariaMain {
	
//	private static Sistema sistema = new Sistema();
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean repete = true;
		int opcao = 0;
//		Cliente cliente = null;
//		Funcionario funcionario = null;

		do {

			System.out.print(" * ----------"
				+ "------------- * Imobiliaria * ----------------------- * \n"
				+ "1 - Administrador.\n"
				+ "2 - Funcionario.\n"
				+ "3 - Cliente.\n"
				+ "4 - Cadastra Cliente.\n"
				+ "0 - Sair.\n"
				+ "-------------Digite o numero da opcao desejada-------------"
				+ "\n" + "Número da opção: ");

			try {
				opcao = sc.nextInt();
			} catch (Exception e) {
				sc.next();
				System.out.println("\nDigite umas das opções sugeridas.");
				continue;
			}

			switch (opcao) {
			
			case 1:
				
				System.out.println("Admin AEW!");
				break;
				
			case 2:
				
				System.out.println("Func AEW!");

			case 3:
				
				try{	
					criaCliente();
				}catch(Exception e){
					System.out.println(e.getMessage());
					repete = false;
					break;
				}

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
		System.out.println("Programa Finalizado");
	}
	
	private static Cliente criaCliente() {
		//cpf = recebe(cpf);
		//nascimento = recebe(calendar - data);
		//endereco = recebe(endereco);
		//nome = recebe(nome);
		//preferencia = rece(TipoImovel.CASA);
		Calendar nascimento = new GregorianCalendar(1991, Calendar.APRIL, 4);
		String endereco = "Rua alBerto de brito, 844";
		TipoImovel preferencia = TipoImovel.CASA;

		try {
			return new Cliente("12345678910", nascimento, endereco, "Bruno",
					preferencia);
		} catch (Exception e) {
			return null;
		}
	}
		

}
