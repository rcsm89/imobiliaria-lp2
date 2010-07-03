package imobiliaria.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Classe que engloba todos os metodos de entrada do programa
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class MetodoEntrada {

	private static Scanner entrada;

	/**
	 * Metodo de entrada de numeros inteiros
	 * 
	 * @return Valor inteiro digitado pelo usuario
	 */
	public static int recebeInt() {
		System.out.print("Qual a opcao desejada? ");
		entrada = new Scanner(System.in);

		if (!entrada.hasNextInt()) {
			entrada.next();
			System.out.println("Entrada invalida! Digite novamente");
			return recebeInt();
		}
		return entrada.nextInt();
	}

	/**
	 * Metodo de entrada de strings
	 * 
	 * @param prompt
	 *            Prompt para entrada de dados
	 * 
	 * @return A string inserida pelo usuario
	 */
	public static String recebeString(String prompt) {
		System.out.print(prompt);
		entrada = new Scanner(System.in);

		if (!entrada.hasNextLine()) {
			entrada.next();
			System.out.println("Entrada invalida! Digite novamente");
			return recebeString(prompt);
		}
		return entrada.nextLine();
	}

	public static String recebeString() {
		String string = null;
		Scanner entrada = new Scanner(System.in);

		while (VerificaInvalido.basico(string)) {
			string = entrada.nextLine();
		}
		return string;
	}

	public static double recebeDouble() {
		double numero = 0;
		boolean condicao = true;
		Scanner entrada = new Scanner(System.in);

		while (condicao) {
			try {
				numero = entrada.nextDouble();
				condicao = false;
			} catch (Exception e) {
				entrada.next();
				System.out.println("Entrada Invalida");
			}
		}
		return numero;
	}

	public static Calendar recebeData() {
		Scanner sc = new Scanner(System.in);
		Calendar data = null;

		if (!sc.hasNextLine()) {
			sc.next();
			System.out.println("Entrada invalida");
			return recebeData();
		}

		String dataString = sc.nextLine();
		final int TAM_STRING_DATA = 10;
		if ((dataString.isEmpty()) || (dataString.length() < TAM_STRING_DATA)
				|| (dataString.length() > TAM_STRING_DATA)) {
			return data;
		}

		int dia;
		int mes;
		int ano;

		try {
			String[] d = dataString.split("/");
			dia = Integer.parseInt(d[0]);
			mes = Integer.parseInt(d[1]);
			ano = Integer.parseInt(d[2]);
		} catch (Exception e) {
			System.out.println("Entrada invalida");
			return recebeData();
		}

		final int JAN = 1, DEZ = 12;
		final int PRIM_DIA = 1, ULTM_DIA = 31;
		if (dia < PRIM_DIA || dia > ULTM_DIA || mes < JAN || mes > DEZ) {
			System.out.println("Entrada invalida");
			return recebeData();
		}

		data = new GregorianCalendar(ano, mes - 1, dia);

		return data;

	}
	
	// Para a interface
	public static int recebeMesPeloNome(String nomeMes){
		
		if(nomeMes == "Janeiro"){ return 0;	}
		else if (nomeMes == "Fevereiro"){ return 1; }
		else if (nomeMes == "Março"){ return 2; }
		else if (nomeMes == "Abril"){ return 3; }
		else if (nomeMes == "Maio"){ return 4; }
		else if (nomeMes == "Junho"){ return 5; }
		else if (nomeMes == "Julho"){ return 6; }
		else if (nomeMes == "Agosto"){ return 7; }
		else if (nomeMes == "Setembro"){ return 8; }
		else if (nomeMes == "Outubro"){ return 9; }
		else if (nomeMes == "Novembro"){ return 10; }
		else if (nomeMes == "Dezembro"){ return 11; }
		return 0;
	}

}
