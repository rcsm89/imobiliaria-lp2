package imobiliaria.entidades;

import imobiliara.auxiliar.TipoLogin;

public class Login {

    private TipoLogin tipoLogin;
    private String senha;
    private String login;

    public Login(String usrName, String password, TipoLogin tipo) {
	tipoLogin = tipo;
	senha = password;
	login = usrName;
    }

    public void mudarSenha(String novaSenha) {
	senha = novaSenha;
    }

    public void mudarLogin(String novoLogin) {
	login = novoLogin;
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
	return login.equals(outroLogin);
    }
}
