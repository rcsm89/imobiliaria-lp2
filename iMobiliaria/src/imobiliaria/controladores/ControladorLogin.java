/*
 * I need a way to work with files!
 * Suggestions about it?
 */
package imobiliaria.controladores;

import imobiliaria.auxiliar.TipoLogin;
import imobiliaria.entidades.Login;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

/**
 * Gerenciamento de Logins do sistema<br>
 * Manager the system's logins
 * 
 * @author Jeanderson Barros Candido
 * @version version 02
 */
public class ControladorLogin implements Serializable {

    // Attribute
    private static ControladorLogin controladorLoginsUnico = new ControladorLogin();
    private Collection<Login> loginsDoSistema;
    private static final long serialVersionUID = 1L;

    // Constructor
    /**
     * Cria um Controlador de Logins<br>
     * Creates a new Login's Controller
     */
    private ControladorLogin() {
	loginsDoSistema = new HashSet<Login>();
	try {
	    loginsDoSistema.add(new Login("admin", "admin",
		    TipoLogin.ADMINISTRADOR));
	} catch (Exception erro) {
	    System.out.println("Login adminstrativo invalido");
	}
    }

    // Methods
    /**
     * Singleton do controlador
     * 
     * @return Controlador de Logins
     */
    public static ControladorLogin getInstance() {
	return controladorLoginsUnico;
    }

    /**
     * Metodo que modifica a instancia unica do Controlador de Logins<br>
     * Method that modified the unique instance of Login's Controller
     * 
     * @param controlador
     *            Nova Instancia para o Controlador<br>
     *            New controller's instance
     */
    public static void setInstance(ControladorLogin controlador) {
	if (controlador == null) {
	    throw new IllegalArgumentException("Controlador de Logins invalido");
	}
	controladorLoginsUnico = controlador;
    }

    /**
     * Retorna o numero de logins cadastrados<br>
     * Returns how many logins was registered
     * 
     * @return Numero de logins cadastrados<br>
     *         Number of registered logins
     */
    public int numLoginsCadastrados() {
	return getCollection().size();
    }

    // Register
    /**
     * Adiciona um novo login em ColecaoLogin<br>
     * Adds a new login in ColecaoLogin
     * 
     * @param login
     *            O login<br>
     *            The login
     * @return True se ColecaoLogins nao conter o login especificado<br>
     *         True if the ColecaoLogins did not already contain the specified
     *         login
     */
    public boolean adicionaLogin(Login login) {
	if (!(login == null)) {
	    for (Login lg : loginsDoSistema) {
		if (lg.equals(login))
		    return false;
	    }
	    return getCollection().add(login);
	}
	return false;
    }

    // Removes
    /**
     * Remove um login em ColecaoLogin<br>
     * 
     * @param userName
     *            Do login a ser removido
     * @return True se ColecaoLogin contem o login especificado
     */
    public boolean removeLogin(String userName) {
	if (!(userName == null)) {
	    for (Login lg : loginsDoSistema) {
		if (lg.getUserName().equals(userName))
		    return loginsDoSistema.remove(lg);
	    }
	}
	return false;
    }

    // Verify
    /**
     * Veririca se o login esta cadastrado<br>
     * 
     * @param userName
     *            O userName do login a ser verificado<br>
     * 
     * @return True se o login estiver cadastradro<br>
     */
    public boolean verificaLogin(String userName) {
	if (!(userName == null)) {
	    for (Login lg : loginsDoSistema) {
		if (lg.getUserName().equals(userName))
		    return loginsDoSistema.contains(lg);
	    }
	}
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof ControladorLogin))
	    return false;
	ControladorLogin outro = (ControladorLogin) obj;
	return (outro.getCollection().containsAll(loginsDoSistema) && loginsDoSistema
		.containsAll(outro.getCollection()));
    }

    /*
     * Private access method. It isn't a good idea let this method public
     * because we won't everyone using, and have total access to manager the
     * logins
     */

    /**
     * Retorna a colecao de login<br>
     * Returns the login's collection
     * 
     * @return Uma colecao de logins<br>
     *         A logins' collection
     */
    private Collection<Login> getCollection() {
	return loginsDoSistema;
    }

    public Login getLogin(String userName) {
	if (verificaLogin(userName)) {
	    for (Login lg : getCollection()) {
		if (lg.getUserName().equals(userName))
		    return lg;
	    }
	}
	return null;
    }

    public boolean checkPassword(Login login, String password) {
	if (!(login == null) || (password == null)) {
	    return login.getSenha().equals(password);
	}
	return false;
    }
}
