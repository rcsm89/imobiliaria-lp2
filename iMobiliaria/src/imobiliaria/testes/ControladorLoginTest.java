package imobiliaria.testes;

import imobiliara.auxiliar.TipoLogin;
import imobiliaria.controladores.ControladorLogin;
import imobiliaria.entidades.Login;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Teste que verifica se ControladorLogin foi implementado corretamente<br>
 * Test that verify if ControladorLogin was implemented correctly
 * 
 * @author Jeanderson Barros Candido
 * @version iteracao 2
 * 
 */
public class ControladorLoginTest {

    private Login lg1, lg2, lg3, lg4;
    private ControladorLogin c1;

    /**
     * Configura dois controladores de login (um vazio e outro com tres logins
     * cadastrados) e alguns logins para testes
     */
    @Before
    public void setUp() throws Exception {

	// YOU CAN'T REGISTER lg4 AND lg1 IN THE SAME CONTROLLER
	lg4 = new Login("acc1", "password", TipoLogin.CLIENTE);
	lg1 = new Login("acc1", "password", TipoLogin.ADMINISTRADOR);
	lg2 = new Login("acc2", "password", TipoLogin.FUNCIONARIO);
	lg3 = new Login("acc3", "password", TipoLogin.CLIENTE);

	// c1 = new ControladorLogin();
	c1 = ControladorLogin.getInstance();
	Assert.assertTrue(lg4.equals(lg1));
    }

    /**
     * Verifica se o metodo adicionaLogin() foi implementado corretamente<br>
     */
    @Test
    public void testAdicionaLogin() throws Exception {
	Assert.assertTrue(c1.adicionaLogin(lg1));
	Assert.assertTrue(c1.adicionaLogin(lg2));
	Assert.assertTrue(c1.adicionaLogin(lg3));

	// Invalid cases:
	Assert.assertFalse(c1.adicionaLogin(lg1));
	Assert.assertFalse(c1.adicionaLogin(lg4));
	Assert.assertFalse(c1.adicionaLogin(null));

	final int EXPECTED_SIZE = 3;
	Assert.assertEquals(EXPECTED_SIZE, c1.numLoginsCadastrados());
    }

    /**
     * Verifica se o metodo removeLogin() foi implementado corretamente<br>
     * Verify if the method removeLogin() was implemented correctly
     */
    @Test
    public void testRemoveLogin() throws Exception {

	Assert.assertFalse(c1.adicionaLogin(lg1));
	Assert.assertFalse(c1.adicionaLogin(null));
	Assert.assertTrue(c1.verificaLogin(lg1.getUserName()));

	// Now I'm trying to remove:
	Assert.assertTrue(c1.removeLogin(lg1.getUserName()));
	Assert.assertFalse(c1.removeLogin(lg1.getUserName()));

	final int EXPETCTED_SIZE = 2;
	Assert.assertEquals(EXPETCTED_SIZE, c1.numLoginsCadastrados());

    }

    /**
     * Verifica se o metodo verificaLogin() foi implementado corretamente<br>
     */
    @Test
    public void testVerificaLogin() {

	final int EXPECTED_SIZE = 2;
	Assert.assertEquals(EXPECTED_SIZE, c1.numLoginsCadastrados());

	Assert.assertFalse(c1.verificaLogin(lg1.getUserName()));
	Assert.assertTrue(c1.verificaLogin(lg2.getUserName()));
	Assert.assertTrue(c1.verificaLogin(lg3.getUserName()));
	Assert.assertFalse(c1.verificaLogin(lg4.getUserName()));
    }
}
