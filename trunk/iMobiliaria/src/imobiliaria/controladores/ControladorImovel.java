package imobiliaria.controladores;

import imobiliaria.processamento.Area;
import imobiliaria.processamento.ColecaoImoveis;
import imobiliaria.processamento.EstadoImovel;
import imobiliaria.processamento.Imovel;
import imobiliaria.processamento.TipoContratual;
import imobiliaria.processamento.TipoImovel;

import java.util.List;

/**
 * Classe com objetivo de controlar as utilidades de um imovel.
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT01
 */
public class ControladorImovel extends ColecaoImoveis {

	/**
	 * Metodo responsavel por modificar um Imovel ja existente
	 * 
	 * @param imovel
	 *            Representa o imovel a ser modificado
	 * @param nome
	 *            Representa o novo nome do imovel
	 * @param endereco
	 *            Representa o novo endereco do imovel
	 * @param valor
	 *            Representa o novo valor do imovel
	 * @param area
	 *            Representa a nova area do imovel
	 * @param tipoDoImovel
	 *            Representa o novo tipo do imovel
	 * @param tipoContratual
	 *            Representa o novo tipo contratual
	 * @throws Exception
	 *             Caso algum dos parametros que forem passados seja invalido
	 * 
	 */
	public void modificaImovel(Imovel imovel, String nome, String endereco,
			double valor, Area area, TipoImovel tipoDoImovel,
			TipoContratual tipoContratual) throws Exception {

		imovel.setNome(nome);
		imovel.setEndereco(endereco);
		imovel.setValor(valor);
		imovel.setArea(area);
		imovel.setTipoDoImovel(tipoDoImovel);
		imovel.setTipoContratual(tipoContratual);

	}

	/**
	 * Metodo responsavel por retornar um imovel apartir de seu registro
	 * 
	 * @param registro
	 *            Representa o numero de registro do imovel
	 * @return Um imovel cujo registro e igual ao passado
	 */
	public Imovel getImovel(String registro) {
		int registroDoImovel = 0;

		try {
			registroDoImovel = Integer.parseInt(registro);
		} catch (Exception e) {
			return null;
		}

		return getImovelDeRegistro(registroDoImovel);
	}

	/**
	 * Metodo responsavel por retornar uma string estruturada apartir de seu
	 * registro
	 * 
	 * @param registro
	 *            Representa o numero de registro do imovel
	 * @return Uma String do imovel cujo registro e igual ao passado
	 */
	public String exibeImovel(String registro) {
		int registroDoImovel = 0;
		Imovel imovel = getImovelDeRegistro(registroDoImovel);

		try {
			registroDoImovel = Integer.parseInt(registro);
		} catch (Exception e) {
			return null;
		}

		return "Nome: " + imovel.getNome() + "\n" + "Endereco: "
				+ imovel.getEndereco() + "\n" + "Valor: " + imovel.getValor()
				+ "\n" + "Area: " + imovel.getArea() + "\n"
				+ "Tipo do Imovel: " + imovel.getTipoDoImovel() + "\n"
				+ "Tipo Contratual: " + imovel.getTipoContratual();

	}

	/**
	 * Metodo responsavel por listar um array de imoveis
	 * 
	 * @param listaDeImoveis
	 *            Representa o array que contem os imoveis que seram listados
	 * @return Uma String contendo a lista dos imoveis
	 */
	private String ListaImoveis(List<Imovel> listaDeImoveis) {
		String saida = "";

		for (Imovel imovel : listaDeImoveis) {

			saida += "Nome: " + imovel.getNome() + "\n" + "Endereco: "
					+ imovel.getEndereco() + "\n" + "Valor: "
					+ imovel.getValor() + "\n" + "Area: " + imovel.getArea()
					+ "\n" + "Tipo do Imovel: " + imovel + "\n"
					+ "Tipo Contratual: " + imovel + "\n\n";

		}

		return saida;
	}

	/**
	 * Metodo responsavel por listar todos os imoveis
	 * 
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String ListaImoveis() {
		return ListaImoveis(getImoveis());
	}

	/**
	 * Metodo responsavel por listar os imoveis com valor pertencento ao
	 * intervalo
	 * 
	 * @param min
	 *            Representa um valor minimo de preco para o imovel
	 * @param max
	 *            Representa um valor maximo de preco para o imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String ListaImoveis(double min, double max) {
		return ListaImoveis(getImoveisDeValor(min, max));
	}

	/**
	 * Metodo responsavel por listar os imoveis com o determinado tipo
	 * contratual
	 * 
	 * @param tipo
	 *            Representa o tipo contratual do imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String ListaImoveis(TipoContratual tipo) {
		return ListaImoveis(getImoveis(tipo));
	}

	/**
	 *Metodo responsavel por listar os imoveis com o determinado tipo (casa,
	 * apartamento, terreno)
	 * 
	 * @param tipo
	 *            Representa o tipo do imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String ListaImoveis(TipoImovel tipo) {
		return ListaImoveis(getImoveis(tipo));
	}

	/**
	 * Metodo responsavel por listar os imoveis com um determinado estado
	 * (comprado, a_venda)
	 * 
	 * @param estado
	 *            Representa o estado de um imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String ListaImoveis(EstadoImovel estado) {
		return ListaImoveis(getImoveis(estado));
	}

	/**
	 * Metodo responsavel por listar os imoveis com um determinado nome
	 * 
	 * @param nome
	 *            Representa algum nome contido no nome de um imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String ListaImoveis(String nome) {
		return ListaImoveis(getImoveis(nome));
	}

	/* Modificacao que eu falei (Yuri) */

	/**
	 * Metodo que adiciona um imovel no Controlador a partir de suas informacoes
	 * 
	 * @param nome
	 *            Nome
	 * @param endereco
	 *            Endereco
	 * @param preco
	 *            Preco
	 * @param area
	 *            Area
	 * @param tipoDoImovel
	 *            Tipo do Imovel
	 * @param tipoContratual
	 *            Tipo Contratual
	 * @throws Exception
	 *             Lanca Excecao se algum parametro estiver errado ou o Imovel
	 *             ja existir
	 */
	public void addImovel(String nome, String endereco, double preco,
			Area area, TipoImovel tipoDoImovel, TipoContratual tipoContratual)
			throws Exception {

		Imovel ImovelASerAdicionado = new Imovel(nome, endereco, preco, area,
				tipoDoImovel, tipoContratual);

		// SuperClasse (ColecaoImovel)
		addImovel(ImovelASerAdicionado);
	}
}
