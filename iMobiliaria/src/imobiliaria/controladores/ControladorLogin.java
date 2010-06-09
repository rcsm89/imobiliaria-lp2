package imobiliaria.controladores;

import imobiliaria.entidades.Login;

import java.util.HashSet;
import java.util.Set;

public class ControladorLogin {

    private Set<Login> loginsDoSistema;

    public ControladorLogin() {
	loginsDoSistema = new HashSet<Login>();
    }

    /**
     * Cadastra o login num repositorio de logins
     * 
     * @param acc
     *            Login a ser cadastrado
     * @return
     */
    public boolean cadastraLogin(Login acc) {
	return loginsDoSistema.add(acc);
    }
}
