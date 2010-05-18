/**
 * Classes com 
 */
package imobiliaria.processamento;

import java.util.Calendar;

/**
 * Classe que descreve uma pessoa abstrata
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public abstract class Pessoa {

	// Atributos

	private Calendar dataNascimento; // data de nascimento da pessoa
	private String endereco; // residencia da pessoa
	private String nome; // nome da pessoa
	private String cpf; // cpf da pessoa

	// Construtor

	/**
	 * @param cpf
	 *            O cpf da pessoa
	 * @param dataNascimento
	 *            A data de nascimento
	 * @param endereco
	 *            O endereco de residencia da pessoa
	 * @param nome
	 *            O nome da pessoa
	 */
	public Pessoa(String cpf, Calendar dataNascimento, String endereco,
			String nome) {

		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.nome = nome;
		this.cpf = cpf;
	}

	// Metodos

	/**
	 * Acesso a data de nascimento
	 * 
	 * @return A dataNascimento da pessoa
	 */
	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Modifica a data de nascimento
	 * 
	 * @param dataNascimento
	 *            A nova data de nascimento a definir
	 */
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Acesso ao endereco
	 * 
	 * @return O endereco de residencia da pessoa
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * Modifica o endereco
	 * @param endereco
	 *            O novo endereco a ser definido
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
