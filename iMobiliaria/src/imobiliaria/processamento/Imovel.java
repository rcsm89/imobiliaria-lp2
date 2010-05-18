package imobiliaria.processamento;

/**
 * Classe Imovel que guarda informacoes de um Imovel
 * 
 * @version IT 1.1
 */
public class Imovel {

	// Atributos
	private static int criadorDeRegistro = 0;

	private String nome;
	private String endereco;
	private double valor;
	private Area area;
	private int registroImovel;
	private EstadoImovel estadoDoImovel; // bruno
	private TipoImovel tipoDoImovel;
	private TipoContratual tipoContratual;

	/**
	 * Construtor da Classe
	 * 
	 * @param nome
	 *            Nome do Imovel (Identificador)
	 * @param endereco
	 *            Localizacao do Imovel
	 * @param valor
	 *            Valor do Imovel
	 * @param area
	 *            Area do Imovel
	 * @param tipoDoImovel
	 *            Tipo do Imovel
	 * @param tipoContratual
	 *            Tipo Contratual do Imovel
	 * @param estadoDoImovel
	 *            Estado do Imovel
	 * @throws Exception
	 *             Lanca Exception caso algum parametro seja invalido
	 */
	public Imovel(String nome, String endereco, double valor, Area area,
			TipoImovel tipoDoImovel, TipoContratual tipoContratual)
			throws Exception {

		if (VerificaInvalido.nome(nome)) {
			throw new Exception("Nome invalido.");
		}

		if (VerificaInvalido.basico(endereco)) {
			throw new Exception("Endereco invalido.");
		}

		if (VerificaInvalido.pertenceAIntervalo(valor, 0.0, Double.MAX_VALUE)) {
			throw new Exception("Valor invalido.");
		}

		this.nome = nome;
		this.endereco = endereco;
		this.valor = valor;
		this.area = area;
		this.tipoDoImovel = tipoDoImovel;
		this.tipoContratual = tipoContratual;

		registroImovel = criadorDeRegistro;
		criadorDeRegistro += 1;
	}

	/**
	 * Metodo acessador para Nome do Imovel
	 * 
	 * @return Nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo Modificador para Nome do Imovel
	 * 
	 * @param nome
	 *            Novo nome do Imovel
	 */
	public void setNome(String nome) throws Exception {
		if (VerificaInvalido.nome(nome)) {
			throw new Exception("Nome invalido");
		}
		this.nome = nome;
	}

	/**
	 * Metodo Acessador para o Endereco do Imovel
	 * 
	 * @return Endereco do Imovel
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Metodo Modificador para o Endereco do Imovel
	 * 
	 * @param endereco
	 *            Novo endereco do Imovel
	 */
	public void setEndereco(String endereco) throws Exception {
		if (VerificaInvalido.basico(endereco)) {
			throw new Exception("Endereco invalido");
		}
		this.endereco = endereco;
	}

	/**
	 * Metodo Acessador para Valor do Imovel
	 * 
	 * @return Valor do Imovel
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * Metodo Acessador para Area do Imovel
	 * 
	 * @return Area do Imovel
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * Metodo Modificador para Area
	 * 
	 * @param area
	 *            Nova Area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * Metodo Acessador para Registro
	 * 
	 * @return Registro
	 */
	public int getRegistroImovel() {
		return registroImovel;
	}

	/**
	 * Metodo Acessador para Tipo do Imovel
	 * 
	 * @return Tipo de Imovel
	 */
	public TipoImovel getTipoDoImovel() {
		return tipoDoImovel;
	}

	/**
	 * Metodo Modificador para Tipo do Imovel
	 * 
	 * @param tipoDoImovel
	 *            Novo Tipo do Imovel
	 */
	public void setTipoDoImovel(TipoImovel tipoDoImovel) {
		this.tipoDoImovel = tipoDoImovel;
	}

	/**
	 * Metodo Acessador para Tipo Contratual
	 * 
	 * @return Tipo Contratual
	 */
	public TipoContratual getTipoContratual() {
		return tipoContratual;
	}

	/**
	 * Metodo Modificador para Tipo Contratual
	 * 
	 * @param tipoContratual
	 *            Novo Tipo Contratual
	 */
	public void setTipoContratual(TipoContratual tipoContratual) {
		this.tipoContratual = tipoContratual;
	}

	/**
	 * Metodo Acessador para o estado atual do imovel
	 * 
	 * @return Estado do Imovel
	 */
	public EstadoImovel getEstadoDoImovel() {
		return estadoDoImovel;
	}

	/**
	 * Metodo modificador para o estado atual do imovel
	 * 
	 * @param estadoDoImovel
	 *            Representa o estado do imovel
	 */
	public void setEstadoDoImovel(EstadoImovel estadoDoImovel) {
		this.estadoDoImovel = estadoDoImovel;
	}

	/*
	 * equals da Classe<br> Dois Imoveis sao iguais se possuem o mesmo registro
	 */
	@Override
	public boolean equals(Object objeto) {
		if (!(objeto instanceof Imovel)) {
			return false;
		}

		Imovel outroImovel = (Imovel) objeto;

		if (registroImovel == outroImovel.getRegistroImovel()) {
			return true;
		}
		return false;
	}

	/**
	 * toString da Classe contendo informacoes do Imovel
	 */

	@Override
	public String toString() {
		return registroImovel + "|" + nome + "|" + endereco + "|" + valor + "|"
				+ area + "|" + tipoDoImovel + "|" + tipoContratual;
	}
}
