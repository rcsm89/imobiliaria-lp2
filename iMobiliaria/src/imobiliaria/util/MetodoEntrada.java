/*
 * OS METODOS DESSA CLASSE TRATAM A EXCESSAO COM A ABORTAGEM DO PROGRAMA
 * POREM DEVE HAVER UMA ACAO DE TRATAMENTO COMO UM POPUP
 */

package imobiliaria.util;

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
}
