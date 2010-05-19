/**
 * Package com as classes usadas pelo sistema 
 */
package imobiliaria.processamento;

import java.text.SimpleDateFormat;
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
	 * @throws Exception
	 *             O nome, data de nascimento, endereco e cpf devem ser validos<br>
	 *             (olhar metodos <i>nome</i>, <i>data</i>, <i>maiorIdade</i>,
	 *             <i>basico</i> e <i>numeroFormatado</i> da classe
	 *             <i>VerificaInvalido</i>)
	 */
	public Pessoa(String cpf, Calendar dataNascimento, String endereco,
			String nome) throws Exception {

		String promptErro = "";
		// Verif Nome
		if (VerificaInvalido.nome(nome))
			promptErro += "Nome invalido\n";
		// Verif Endereco
		if (VerificaInvalido.basico(endereco))
			promptErro += "Endereco invalido\n";
		// Verif CPF
		final int TAM_CPF_FORMATADO = 11;
		if (VerificaInvalido.numeroFormatado(cpf, TAM_CPF_FORMATADO))
			promptErro += "CPF invalido\n";
		// Verif Data Nascimento
		if ((VerificaInvalido.data(dataNascimento))
				|| VerificaInvalido.maiorIdade(dataNascimento)) {
			promptErro += "Data de nascimento invalida";
		}
		if (promptErro.length() != 0) {
			throw new Exception(promptErro);
		}

		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
		this.nome = nome;
		this.cpf = cpf;
	}

	// Metodos

	/**
	 * Acesso a data de nascimento
	 * 
	 * @return A data de nascimento da pessoa
	 */
	public String getDataNascimento() {
		return new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento
				.getTime());
	}

	/**
	 * Modifica a data de nascimento
	 * 
	 * @param dataNascimento
	 *            A nova data de nascimento a definir
	 * @throws Exception
	 *             Nao pode ser uma data invalida<br>
	 *             (Olhar metodos <i>maiorIdade</i> e <i>idade</i> da classe
	 *             <i>VerificaInvalido</i>)
	 */
	public void setDataNascimento(Calendar dataNascimento) throws Exception {
		// Verif Data Nascimento
		if ((VerificaInvalido.maiorIdade(dataNascimento))
				|| VerificaInvalido.data(dataNascimento)) {
			throw new Exception("Data de nascimento invalida");
		}
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
	 * @throws Exception
	 *             Nao pode ser um endereco invalido<br>
	 *             (Olhar metodo <i>basico</i> da classe
	 *             <i>VerificaInvalido</i>)
	 */
	public void setEndereco(String endereco) throws Exception {
		// Verif Endereco
		if (VerificaInvalido.basico(endereco)) {
			throw new Exception("Endereco invalido");
		}
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
	 * Modifccica o nome
	 * 
	 * @param nome
	 *            O nome a ser definido
	 * @throws Exception
	 *             O nome, deve ser valido<br>
	 *             (Olhar metodo <i>nome</i> da classe <i>VerificaInvalido</i>)
	 */
	public void setNome(String nome) throws Exception {
		// Verif Nome
		if (VerificaInvalido.nome(nome))
			throw new Exception("Nome invalido");
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
	 * @throws Exception
	 *             O cpf devem ser validos<br>
	 *             (Olhar metodo <i>numeroFormatado</i> da classe
	 *             <i>VerificaInvalido</i>)
	 */
	public void setCpf(String cpf) throws Exception {
		// Verif CPF
		final int TAM_CPF_FORMATADO = 11;
		if (VerificaInvalido.numeroFormatado(cpf, TAM_CPF_FORMATADO)) {
			throw new Exception("CPF invalido");
		}
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
