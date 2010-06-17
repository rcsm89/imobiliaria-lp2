package imobiliaria.entidades;

import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.auxiliar.TipoLogin;
import imobiliaria.processamento.ColecaoImoveis;

import java.util.Calendar;

/**
 * Objetos desta classe sao clientes da imobiliaria
 * 
 * @author Bruno Fabio de Farias Paiva
 * @version IT01
 */
public class Cliente extends Usuario {

	private static final long serialVersionUID = 1L;
	private ColecaoImoveis historicoCompras = new ColecaoImoveis();
	private TipoImovel preferencia;

	/**
	 * Construtor responsavel por criar um novo cliente
	 * 
	 * @param cpf
	 *            Representa o cadastro de pessoa fisica de um cliente
	 * @param dataNascimento
	 *            Representa a data de nascimento de um cliente
	 * @param endereco
	 *            Representa o endereco de um cliente
	 * @param nome
	 *            Representa o nome de um cliente
	 * @param preferencia
	 *            Preferencia do Imovel do Cliente
	 * @throws Exception
	 *             Caso algum parametro seja incorreto
	 */
	public Cliente(String cpf, Calendar dataNascimento, String endereco,
			String nome, TipoImovel preferencia) throws Exception {
		super(cpf, dataNascimento, endereco, nome, TipoLogin.CLIENTE);

		if (preferencia == null) {
			throw new IllegalArgumentException("Preferencia invalida!");
		}
		this.preferencia = preferencia;
	}

	/**
	 * Metodo acessador ao historico de compras de um cliente
	 * 
	 * @return O historico das compras de um cliente
	 */
	public ColecaoImoveis getHistoricoCompras() {
		return historicoCompras;
	}

	/**
	 * Metodo acessador a preferencia do cliente
	 * 
	 * @return Tipo de imovel que o cliente prefere
	 */
	public TipoImovel getPreferencia() {
		return preferencia;
	}

	/**
	 * Metodo modificador da preferencia de um cliente
	 * 
	 * @param preferencia
	 *            Representa a nova preferencia do cliente
	 */
	public void setPreferencia(TipoImovel preferencia) {
		this.preferencia = preferencia;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see imobiliaria.processamento.Pessoa#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object objeto) {

		if (!(objeto instanceof Cliente)) {
			return false;
		}

		Cliente outroCliente = (Cliente) objeto;

		return getCpf().equals(outroCliente.getCpf());

	}
}
