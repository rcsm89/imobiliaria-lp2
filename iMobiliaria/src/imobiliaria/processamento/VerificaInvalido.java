package imobiliaria.processamento;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Classe que verifica entradas invalidas
 * 
 * @author Bruno Fabio, Jeanderson Barros
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
	int idadeVerificada = dataAtual.get(0) - dataNascimento.get(0);

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

	for (int i = 0; i < texto.length(); i++) {
	    if (!(Character.isLetter(texto.charAt(i))))
		return true;
	}

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
}
