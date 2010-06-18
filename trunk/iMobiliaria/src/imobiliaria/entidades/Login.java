package imobiliaria.entidades;

import imobiliaria.auxiliar.TipoLogin;
import imobiliaria.util.FormataEntrada;
import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Login de acesso para uma conta do iMobiliaria<br>
 * 
 * @version IT02
 */
public class Login implements Serializable {

	private static final long serialVersionUID = 1L;
	private TipoLogin tipoLogin;
	private String senha;
	private String userName;

	/**
	 * Construtor de Login
	 * 
	 * @param nome
	 *            O user-name do Login
	 * @param password
	 *            A senha do Login
	 * @param tipo
	 *            O tipo do Login (Cliente, Funcionario, Admin)
	 * @throws Exception
	 *             se algum dos parametros forem invalidos
	 */
	public Login(String nome, String password, TipoLogin tipo) throws Exception {

		if ((nome == null) || (password == null) || (tipo == null)) {
			throw new IllegalArgumentException("Message");
		}
		nome = nome.replace(" ", "");
		if (VerificaInvalido.basico(nome) || password.isEmpty()) {
			throw new IllegalArgumentException("Message");
		}
		tipoLogin = tipo;
		senha = password;
		userName = nome;
	}

	/**
	 * Gera uma senha a partir da data de aniversario
	 * 
	 * @param data
	 *            Uma data qualquer
	 * @return String representando uma senha
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
	 * Muda a senha de um Login
	 * 
	 * @param novaSenha
	 *            A nova senha a ser definida para o login
	 */
	public void mudarSenha(String novaSenha) {
		if (!(novaSenha == null)) {
			if (!novaSenha.isEmpty()) {
				senha = novaSenha;
			}
		}
	}

	/**
	 * Muda o Username de um login
	 * 
	 * @param novoLogin
	 *            O novo username do login
	 */
	public void mudarLogin(String novoLogin) {
		if (!(novoLogin == null || VerificaInvalido.basico(novoLogin))) {
			userName = novoLogin.replace(" ", "");
		}
	}

	/**
	 * Retorna a senha do login
	 * 
	 * @return A senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * Retorna o Username de um login
	 * 
	 * @return O username de um login
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Retorna o tipo do login
	 * 
	 * @return O tipo do login
	 */
	public TipoLogin getTipoLogin() {
		return tipoLogin;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Login)) {
			return false;
		}
		Login outroLogin = (Login) obj;
		return userName.equals(outroLogin.getUserName());
	}

	@Override
	public String toString() {
		return "Login: " + userName + "\nSenha: " + senha;
	}
}
