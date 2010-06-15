package imobiliaria.entidades;

import imobiliara.auxiliar.TipoLogin;
import imobiliaria.util.FormataEntrada;
import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe que descreve uma pessoa abstrata usuaria do sistema
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
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
	    throw new Exception(promptErro);
	}

	this.dataNascimento = FormataEntrada.data(dataNascimento);
	this.endereco = FormataEntrada.capitalize(endereco).trim();
	this.nome = FormataEntrada.capitalize(nome).trim();
	this.cpf = FormataEntrada.cpf(cpf).trim();
	this.login = new Login(this.cpf, this.dataNascimento, tLogin);
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
	// Verif Data Nascimento
	if ((VerificaInvalido.maiorIdade(dataNascimento))
		|| VerificaInvalido.data(dataNascimento)) {
	    throw new Exception("Data de nascimento invalida");
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
	    throw new Exception("Endereco invalido\n");
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
	// Verif Nome
	if (VerificaInvalido.nome(nome))
	    throw new Exception("Nome invalido\n");
	this.nome = FormataEntrada.capitalize(nome);
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
	    throw new Exception("CPF invalido\n");
	}
	this.cpf = FormataEntrada.cpf(cpf);
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
     * Retorna o login de um usuario<br>
     * Returns the user's login
     * 
     * @return O login do usuario<br>
     *         The user's login
     */
    public Login getLogin() {
	return login;
    }

}
