/*
 * ATENCAO, AINDA EH NECESSARIO TRATAR AS VERIFICACOES DE ENTRADA
 */

/**
 * Package com as classes usadas pelo sistema 
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
	 * 
	 * @param endereco
	 *            O novo endereco a ser definido
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Acesso ao nome
	 * 
	 * @return O nome da pessoa
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Modifica o nome
	 * 
	 * @param nome
	 *            O nome a ser definido
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Acesso ao CPF
	 * 
	 * @return O CPF da pessoa
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Modifica o CPF
	 * 
	 * @param cpf
	 *            O CPF a ser definido
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public abstract boolean equals(Object obj);

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome() + "|" + getCpf() + "|" + getEndereco() + "|"
				+ getDataNascimento();
	}

}
