package imobiliaria.aux;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Classe auxilar de metodos que formata entradas
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class FormataEntrada {

	/**
	 * Retorna uma string onde a letra inicial de cada palavra é passada para
	 * maiúscula. Exemplo: "joão de oliveira" -> "João De Oliveira"
	 * 
	 * {@link http://www.guj.com.br/posts/list/128780.java}
	 * 
	 * @param str
	 *            A string a ser convertida.
	 * @return A string convertida.
	 */
	public static String capitalize(String str) {
		str = str.toLowerCase();
		char[] letras = str.toCharArray();
		for (int i = 0; i < letras.length; ++i) {
			if (i == 0 || !Character.isLetterOrDigit(letras[i - 1])) {
				letras[i] = Character.toUpperCase(letras[i]);
			}
		}
		return new String(letras);
	}

	/**
	 * Metodo que formata data
	 * 
	 * @param data
	 *            A data a ser formatada
	 * @return Data no formato dd/mm/aaaa
	 */
	public static String data(Calendar data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
	}

	/**
	 * Metodo que formata cpf
	 * 
	 * @param cpf
	 *            O cpf a ser formatado com 11 digitos
	 * @return CPF no formato xxx.xxx.xxx-xx
	 */
	public static String cpf(String cpf) {
		return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "."
				+ cpf.substring(6, 9) + "-" + cpf.substring(9);
	}
}
