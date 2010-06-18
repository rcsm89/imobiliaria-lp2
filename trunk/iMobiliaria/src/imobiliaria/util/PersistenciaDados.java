package imobiliaria.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe que grava e ler objetos a partir de arquivos
 * 
 * @version IT02
 */
public class PersistenciaDados {

	/**
	 * Metodo para gravar um objeto em um dado arquivo
	 * 
	 * @param objeto
	 *            Objeto a ser armazenado
	 * @param arquivo
	 *            Arquivo em que o objeto sera gravado
	 * @throws IOException
	 *             Lanca IOException caso tenha algum problema no arquivo
	 */
	public static void gravar(Object objeto, String arquivo) throws IOException {

		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(fos);

			oos.writeObject(objeto);

			oos.close();
		} catch (Exception e) {
			fos.close();
			System.out.println("Erro de Arquivo: " + e.getMessage());
		}
	}

	/**
	 * Metodo que le um objeto a partir de um dado arquivo
	 * 
	 * @param arquivo
	 *            Arquivo a ser lido
	 * @return Objeto a partir do arquivo
	 * @throws IOException
	 *             Lanca IOException caso tenha algum problema no arquivo
	 * @throws ClassNotFoundException
	 *             Caso o objeto nao seja encontrado
	 */
	public static Object ler(String arquivo) throws IOException,
			ClassNotFoundException {

		FileInputStream fis = new FileInputStream(arquivo);
		ObjectInputStream ois;
		Object objetoRetornado;

		try {
			ois = new ObjectInputStream(fis);

			objetoRetornado = ois.readObject();
		} finally {
			fis.close();
		}
		ois.close();
		return objetoRetornado;
	}
}