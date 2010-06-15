/*
 * I need a way to work with files!
 * Suggestions about it?
 */
package imobiliaria.controladores;

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
    private Collection<Login> loginsDoSistema;
    private static final long serialVersionUID = 1L;

    // Constructor
    /**
     * Cria um Controlador de Logins<br>
     * Creates a new Login's Controller
     */
    public ControladorLogin() {
	loginsDoSistema = new HashSet<Login>();
    }

    // Methods

    /**
     * Retorna o numero de logins cadastrados<br>
     * Returns how many logins was registered
     * 
     * @return Numero de logins cadastrados<br>
     *         Number of registered logins
     */
    public int numLoginsCadastrados() {
	return loginsDoSistema.size();
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
	    return loginsDoSistema.add(login);
	}
	return false;
    }

    // Removes
    /**
     * Remove um login em ColecaoLogin<br>
     * Removes a login from ColecaoLogin
     * 
     * @param login
     *            A ser removido<br>
     *            To be removed
     * @return True se ColecaoLogin contem o login especificado<br>
     *         True if ColecaoLogin contained the specified login
     */
    public boolean removeLogin(Login login) {
	if (!(login == null)) {
	    return loginsDoSistema.remove(login);
	}
	return false;
    }

    // Verify
    /**
     * Veririca se o login esta cadastrado<br>
     * Verify if the login is registered
     * 
     * @param lg
     *            O login a ser verificado<br>
     *            The Login to be verified
     * 
     * @return True se o login estiver cadastradro<br>
     *         True if the login has been registered
     */
    public boolean verificaLogin(Login lg) {
	if (!(lg == null)) {
	    return loginsDoSistema.contains(lg);
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

}
