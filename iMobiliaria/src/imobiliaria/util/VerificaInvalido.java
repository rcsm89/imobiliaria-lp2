/**
 * Package com classes Auxiliares de Verificacao e Formatacao
 */
package imobiliaria.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Classe auxiliar que verifica entradas invalidas
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT01
 */
public class VerificaInvalido {

    // Tratamento de Datas (Jeanderson)
    // ------------------------------------------------------------------------

    private static Calendar dataAtual = new GregorianCalendar();

    /**
     * Metodo que verifica maior idade de uma pessoa
     * 
     * @param dataNascimento
     *            A data a ser a analisada
     * @return True, se a pessoa for menor de idade<br>
     *         False, caso contrario
     */

    public static boolean maiorIdade(Calendar dataNascimento) {
	final int MAIOR_IDADE = 18;

	int idadeVerificada = dataAtual.get(Calendar.YEAR)
		- dataNascimento.get(Calendar.YEAR);

	if (idadeVerificada < MAIOR_IDADE) {
	    return true;
	}
	return false;
    }

    /**
     * Metodo que verifica se uma data vem depois da data atual
     * 
     * @param data
     *            A data a ser verificada
     * @return True, se a data analisada vier depois da data atual <br>
     *         False, caso contrario
     */
    public static boolean data(Calendar data) {
	if (data == null) {
	    return true;
	}
	if (data.after(dataAtual)) {
	    return true;
	}
	return false;
    }

    // ------------------------------------------------------------------------

    /**
     * Metodo responsavel por verificacao basica
     * 
     * @param texto
     *            Representa a string a ser analisada
     * @return True, se a string for null, vazia ou em branco <br>
     *         False, caso contrario
     */
    public static boolean basico(String texto) {

	if ((texto == null) || (texto.replace(" ", "").equals("")))
	    return true;

	return false;
    }

    /**
     * Verifica se um endereco contem
     * 
     * @param texto
     * @return
     */
    public static boolean endereco(String texto) {
	if (basico(texto)) {
	    return true;
	}
	char[] textoSemEspacoEVirgula = texto.replace(" ", "").replace("/", "")
		.replace(",", "").toCharArray();
	for (Character c : textoSemEspacoEVirgula) {
	    if (!(Character.isLetterOrDigit(c))) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Metodo responsavel por verificar se o nome tem formatacao invalida
     * 
     * @param texto
     *            Representa o nome a ser analisado
     * @return True, se passar no teste basico e conter apenas letras <br>
     *         False, caso contrario
     */
    public static boolean nome(String texto) {

	if (basico(texto))
	    return true;

	texto = texto.replace(" ", "");
	for (int i = 0; i < texto.length(); i++) {
	    if (!(Character.isLetter(texto.charAt(i))))
		return true;
	}

	if (Character.isTitleCase(texto.charAt(0)))
	    return true;

	return false;
    }

    public static boolean numero(String numero) {

	if (basico(numero))
	    return true;

	for (int i = 0; i < numero.length(); i++) {
	    if (!(Character.isDigit(numero.charAt(i))))
		return true;
	}
	return false;
    }

    /**
     * Metodo responsavel por verificar se um numero esta na formatacao <br>
     * adequada
     * 
     * @param numeroString
     *            Representa o numero (como uma string) a ser analisado
     * @param tamanho
     *            Representa a quantidade de algarismos que o numero passado
     *            deve ter
     * @return True, se passar no teste basico, conter apenas digitos e conter o
     *         tamanho adequado <br>
     *         False, caso contrario
     */
    public static boolean numeroFormatado(String numeroString, int tamanho) {

	if (basico(numeroString))
	    return true;

	for (int i = 0; i < numeroString.length(); i++) {
	    if (!(Character.isDigit(numeroString.charAt(i))))
		return true;
	}

	if (numeroString.length() != tamanho)
	    return true;

	return false;
    }

    /**
     * Metodo responsavel por verificar se um determinado numero esta entre o
     * intervalo passado
     * 
     * @param numero
     *            Representa o numero (como uma string) a ser analisado
     * @param min
     *            Representa o inicio do intervalo
     * @param max
     *            Representa o final do intervalo
     * @return True, se o numero estiver dentro do dominio passado <br>
     *         False, caso contrario
     */
    public static boolean pertenceAIntervalo(double numero, double min,
	    double max) {

	if (min >= max)
	    return false;

	if (numero < min)
	    return false;

	if (numero > max)
	    return false;

	return true;
    }

    // Metodos de entrada
    // ---------------------------------------------------------------

    public static String recebeString() {
	String string = null;
	Scanner entrada = new Scanner(System.in);

	while (basico(string)) {
	    try {
		string = entrada.nextLine();
	    } catch (Exception e) {
		entrada.next();
		System.out.println("Entrada Invalida");
	    }
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

    public static int recebeInteiro() {
	int numero = 0;
	boolean condicao = true;
	Scanner entrada = new Scanner(System.in);

	while (condicao) {
	    try {
		numero = entrada.nextInt();
		condicao = false;
	    } catch (Exception e) {
		entrada.next();
		System.out.println("Entrada Invalida");
	    }
	}
	return numero;
    }
    
    public static String recebeNome() {
    	String string = null;
    	Scanner entrada = new Scanner(System.in);

    	while (nome(string)) {
    	    try {
    		string = entrada.nextLine();
    	    } catch (Exception e) {
    		entrada.next();
    		System.out.println("Entrada Invalida");
    	    }
    	}
    	return string;
        }
    
    public static Calendar recebeData(){
		Scanner sc = new Scanner(System.in);
		Calendar hoje = new GregorianCalendar();
		Calendar data = null;
		
		if (!sc.hasNextLine()){
			sc.next();
			System.out.println("Entrada invalida");
			return recebeData();
		}
		
		String dataString = sc.nextLine();
		if (dataString.isEmpty() || dataString.length() < 10 || dataString.length() > 10) {
			System.out.println("Entrada invalida");
			return recebeData();
		}
		
		String[] d = dataString.split("/");
		int dia = Integer.parseInt(d[0]);
		int mes = Integer.parseInt(d[1]);
		int ano = Integer.parseInt(d[2]);
		
		if (dia < 1 || dia > 31 || mes < 1 || mes > 12 ){
			System.out.println("Entrada invalida");
			return recebeData();
		}
		
		data = new GregorianCalendar(ano, mes-1, dia);
		if (data.getTime().before(hoje.getTime())){
			System.out.println("Esta data já passou. Tente Novamente.");
			return recebeData();
		}
		return data;
		
	}
}
