package imobiliaria.controladores;

import imobiliara.aux.TipoContratual;
import imobiliara.aux.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.EstadoImovel;
import imobiliaria.entidades.Imovel;
import imobiliaria.processamento.ColecaoImoveis;
import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.List;

/**
 * Classe com objetivo de controlar as utilidades de um imovel.
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT02
 */
public class ControladorImovel implements Serializable{
	
	//Atributos
	
	private static ColecaoImoveis colecaoImovel;
	
	/**
	 * Construtor privado para nao ocorrer instanciacoes da classe <br>
	 * fora da classe.
	 */
	private ControladorImovel() {};
	
	private static ControladorImovel controladorImovelUnico = new
													ControladorImovel();
	
	/**
	 * Singleton do controlador 
	 * @return Controlador de Imovel
	 */
	public static ControladorImovel getInstance(){
		return controladorImovelUnico;
	}

	private static final long serialVersionUID = 1L;

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

		if (imovel == null) {
			throw new IllegalArgumentException("Imovel Invalido");
		}

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
		if (registro == null)
			throw new IllegalArgumentException("Registro Invalido");

		int registroDoImovel = 0;

		try {
			registroDoImovel = Integer.parseInt(registro);
		} catch (Exception e) {
			return null;
		}

		return colecaoImovel.getImovelDeRegistro(registroDoImovel);
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
		if (registro == null) {
			throw new IllegalArgumentException("Registro Invalido");
		}

		int registroDoImovel = 0;

		try {
			registroDoImovel = Integer.parseInt(registro);
		} catch (Exception e) {
			return null;
		}

		Imovel imovel = colecaoImovel.getImovelDeRegistro(registroDoImovel);

		if (imovel == null) {
			return null;
		}

		return "Registro: " + imovel.getRegistroImovel() + "\n" + "Nome: "
				+ imovel.getNome() + "\n" + "Endereco: " + imovel.getEndereco()
				+ "\n" + "Valor: " + imovel.getValor() + "\n" + "Area: "
				+ "\n\t" + "Comprimento: " + imovel.getArea().getComprimento()
				+ "m\n\t" + "Largura: " + imovel.getArea().getLargura()
				+ "m\n\t" + "Classificacao: "
				+ imovel.getArea().getClassificacao() + "\n"
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
	private String listaImoveis(List<Imovel> listaDeImoveis) {
		String saida = "";

		for (Imovel imovel : listaDeImoveis) {

			saida += "Registro: " + imovel.getRegistroImovel() + "\n"
					+ "Nome: " + imovel.getNome() + "\n" + "Endereco: "
					+ imovel.getEndereco() + "\n" + "Valor: "
					+ imovel.getValor() + "\n" + "Area: " + imovel.getArea()
					+ "\n" + "Tipo do Imovel: " + imovel.getTipoDoImovel()
					+ "\n" + "Tipo Contratual: " + imovel.getTipoContratual()
					+ "\n\n";

		}

		return saida;
	}

	/**
	 * Metodo responsavel por listar todos os imoveis
	 * 
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String listaImoveis() {
		return listaImoveis(colecaoImovel.getImoveis());
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
	public String listaImoveis(double min, double max) {
		return listaImoveis(colecaoImovel.getImoveisDeValor(min, max));
	}

	/**
	 * Metodo responsavel por listar os imoveis com o determinado tipo
	 * contratual
	 * 
	 * @param tipo
	 *            Representa o tipo contratual do imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String listaImoveis(TipoContratual tipo) {
		return listaImoveis(colecaoImovel.getImoveis(tipo));
	}

	/**
	 *Metodo responsavel por listar os imoveis com o determinado tipo (casa,
	 * apartamento, terreno)
	 * 
	 * @param tipo
	 *            Representa o tipo do imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String listaImoveis(TipoImovel tipo) {
		return listaImoveis(colecaoImovel.getImoveis(tipo));
	}

	/**
	 * Metodo responsavel por listar os imoveis com um determinado estado
	 * (comprado, a_venda)
	 * 
	 * @param estado
	 *            Representa o estado de um imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String listaImoveis(EstadoImovel estado) {
		return listaImoveis(colecaoImovel.getImoveis(estado));
	}

	/**
	 * Metodo responsavel por listar os imoveis com um determinado nome
	 * 
	 * @param nome
	 *            Representa algum nome contido no nome de um imovel
	 * @return Uma String contendo a lista dos imoveis
	 */
	public String listaImoveis(String nome) {
		if (VerificaInvalido.basico(nome)) {
			throw new IllegalArgumentException("Nome Invalido");
		}
		return listaImoveis(colecaoImovel.getImoveis(nome));
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
		colecaoImovel.addImovel(ImovelASerAdicionado);
	}
}
