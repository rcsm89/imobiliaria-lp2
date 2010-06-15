/*
 * Por questões de seguranca, achei conveniente nao implementar ( por em quanto
 * um metodo que modifica o tipo do login.
 */
package imobiliaria.entidades;

import imobiliara.auxiliar.TipoLogin;
import imobiliaria.util.FormataEntrada;
import imobiliaria.util.VerificaInvalido;

import java.util.Calendar;

/**
 * Login de acesso para uma conta do iMobiliaria<br>
 * Access' login for an iMobiliaria's account
 * 
 * @author Jeanderson Barros Candido
 * @version iteracao 2
 * 
 */
public class Login {

    private TipoLogin tipoLogin;
    private String senha;
    private String userName;

    /**
     * Construtor de Login<br>
     * Login's Constructor
     * 
     * @param nome
     *            O user-name do Login<br>
     *            The login's user-name
     * @param password
     *            A senha do Login<br>
     *            The login's password
     * @param tipo
     *            O tipo do Login (Cliente, Funcionario, Admin)<br>
     *            The login's type
     * @throws Exception
     *             se algum dos parametros forem invalidos<br>
     *             if one of those parameters is invalid
     */
    public Login(String nome, String password, TipoLogin tipo) throws Exception {

	if ((nome == null) || (password == null) || (tipo == null)) {
	    throw new Exception("Message");
	}
	nome = nome.replace(" ", "");
	if (VerificaInvalido.basico(nome) || password.isEmpty()) {
	    throw new Exception("Message");
	}
	tipoLogin = tipo;
	senha = password;
	userName = nome;
    }

    /**
     * Gera uma senha a partir da data de aniversario<br>
     * Generates a password by a date
     * 
     * @param data
     *            Uma data qualquer<br>
     *            Any date
     * @return String representando uma senha<br>
     *         String representing the password
     */
    public static String geraSenha(Calendar data) {
	/*
	 * Criacao de uma senha default do usuario do sistema A senha default
	 * sao os digitos da data de nascimento
	 * 
	 * [Exemplo: 08/05/1991] [Senha Default: 08051991]
	 */
	String[] dataApenasNumeros = FormataEntrada.data(data).split("/");
	String senha = "";
	for (String digitos : dataApenasNumeros) {
	    senha += digitos;
	}
	return senha;
    }

    /**
     * Muda a senha de um Login<br>
     * Changes the login's password
     * 
     * @param novaSenha
     *            A nova senha a ser definida para o login<br>
     *            The new login's password
     */
    public void mudarSenha(String novaSenha) {
	if (!(novaSenha == null)) {
	    if (!novaSenha.isEmpty()) {
		senha = novaSenha;
	    }
	}
    }

    /**
     * Muda o Username de um login<br>
     * Changes the login's user-name
     * 
     * @param novoLogin
     *            O novo username do login<br>
     *            The new login's user-name
     */
    public void mudarLogin(String novoLogin) {
	if (!(novoLogin == null || VerificaInvalido.basico(novoLogin))) {
	    userName = novoLogin.replace(" ", "");
	}
    }

    /**
     * Retorna a senha do login<br>
     * Returns the login's password
     * 
     * @return A senha<br>
     *         The password
     */
    public String getSenha() {
	return senha;
    }

    /**
     * Retorna o Username de um login<br>
     * Returns the login's user-name
     * 
     * @return O username de um login<br>
     *         The login's user-name
     */
    public String getLogin() {
	return userName;
    }

    /**
     * Retorna o tipo do login<br>
     * Return the login's type
     * 
     * @return O tipo do login<br>
     *         The login's type
     */
    public TipoLogin getTipoLogin() {
	return tipoLogin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof Login)) {
	    return false;
	}
	Login outroLogin = (Login) obj;
	return userName.equals(outroLogin.getLogin());
    }
}
