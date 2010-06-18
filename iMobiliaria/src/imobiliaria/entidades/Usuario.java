package imobiliaria.entidades;

import imobiliaria.auxiliar.TipoLogin;
import imobiliaria.util.FormataEntrada;
import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe que descreve uma pessoa abstrata usuaria do sistema
 * 
 * @version IT02
 */
public abstract class Usuario implements Serializable, Comparable<Object> {

	// Atributos

	private static final long serialVersionUID = 1L;

	private String dataNascimento; // data de nascimento da pessoa
	private String endereco; // residencia da pessoa
	private String nome; // nome da pessoa
	private String cpf; // cpf da pessoa
	private Login login;

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
	 * @param tLogin
	 *            Tipo de Acesso do usuario
	 * @throws Exception
	 *             O nome, data de nascimento, endereco e cpf devem ser validos<br>
	 *             (olhar metodos <i>nome</i>, <i>data</i>, <i>maiorIdade</i>,
	 *             <i>basico</i> e <i>numeroFormatado</i> da classe
	 *             <i>VerificaInvalido</i>)
	 */
	public Usuario(String cpf, Calendar dataNascimento, String endereco,
			String nome, TipoLogin tLogin) throws Exception {

		String promptErro = "";
		// Verif Nome
		if (VerificaInvalido.nome(nome))
			promptErro += "Nome invalido\n";
		// Verif Endereco
		if (VerificaInvalido.endereco(endereco))
			promptErro += "Endereco invalido\n";
		// Verif CPF
		final int TAM_CPF_FORMATADO = 11;
		if (VerificaInvalido.numeroFormatado(cpf, TAM_CPF_FORMATADO))
			promptErro += "CPF invalido\n";
		// Verif Data Nascimento
		if ((VerificaInvalido.data(dataNascimento))
				|| VerificaInvalido.maiorIdade(dataNascimento)) {
			promptErro += "Data de nascimento invalida\n";
		}
		final int TAM_PALAVRA_VAZIA = 0;
		if (promptErro.length() != TAM_PALAVRA_VAZIA) {
			throw new IllegalArgumentException(promptErro);
		}

		this.dataNascimento = FormataEntrada.data(dataNascimento);
		this.endereco = FormataEntrada.capitalize(endereco).trim();
		this.nome = FormataEntrada.capitalize(nome).trim();
		this.cpf = FormataEntrada.cpf(cpf).trim();
		this.login = new Login(cpf, this.dataNascimento.replace("/", ""),
				tLogin);
	}

	// Metodos

	/**
	 * Acesso a data de nascimento
	 * 
	 * @return A data de nascimento da pessoa
	 */
	public String getDataNascimento() {
		return dataNascimento;
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
		
		if ((VerificaInvalido.maiorIdade(dataNascimento))
				|| VerificaInvalido.data(dataNascimento)) {
			throw new IllegalArgumentException("Data de nascimento invalida");
		}
		this.dataNascimento = FormataEntrada.data(dataNascimento);
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
		if (VerificaInvalido.endereco(endereco)) {
			throw new IllegalArgumentException("Endereco invalido\n");
		}
		this.endereco = FormataEntrada.capitalize(endereco).trim();
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
	 * @throws Exception
	 *             O nome, deve ser valido<br>
	 *             (Olhar metodo <i>nome</i> da classe <i>VerificaInvalido</i>)
	 */
	public void setNome(String nome) throws Exception {
		if (VerificaInvalido.nome(nome))
			throw new IllegalArgumentException("Nome invalido\n");
		this.nome = FormataEntrada.capitalize(nome);
	}

	/**
	 * Metodo de acesso ao CPF
	 * 
	 * @return O CPF da pessoa
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Metodo modificador do CPF
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
			throw new IllegalArgumentException("CPF invalido\n");
		}
		this.cpf = FormataEntrada.cpf(cpf);
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public String toString() {
		return getNome() + "|" + getCpf() + "|" + getEndereco() + "|"
				+ getDataNascimento();
	}

	public int compareTo(Object obj) {
		if (!(obj instanceof Usuario))
			throw new IllegalArgumentException();

		Usuario usuario = (Usuario) obj;

		return getNome().compareTo(usuario.getNome());
	}

	/**
	 * Retorna o login de um usuario
	 * 
	 * @return O login do usuario
	 */
	public Login getLogin() {
		return login;
	}
}