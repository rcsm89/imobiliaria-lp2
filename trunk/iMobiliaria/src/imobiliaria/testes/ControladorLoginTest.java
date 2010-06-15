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
    private ControladorLogin c1, c2;

    /**
     * Configura dois controladores de login (um vazio e outro com tres logins
     * cadastrados) e alguns logins para testes<br>
     * Sets up two login's controllers (one empty, and other with three logins
     * registered) and some logins for tests
     */
    @Before
    public void setUp() throws Exception {

	// Remember:
	// YOU CAN'T REGISTER lg4 AND lg1 IN THE SAME CONTROLLER
	lg4 = new Login("acc1", "password", TipoLogin.CLIENTE);
	lg1 = new Login("acc1", "password", TipoLogin.ADMINISTRADOR);
	lg2 = new Login("acc2", "password", TipoLogin.FUNCIONARIO);
	lg3 = new Login("acc3", "password", TipoLogin.CLIENTE);

	c1 = new ControladorLogin();
	c2 = new ControladorLogin();

	Assert.assertNotSame(c1, c2);
	Assert.assertTrue(lg4.equals(lg1));
    }

    /**
     * Verifica se o metodo adicionaLogin() foi implementado corretamente<br>
     * Verify if the method adicionaLogin() was implemented correctly
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
	Assert.assertFalse(c1.equals(c2));
    }

    /**
     * Verifica se o metodo removeLogin() foi implementado corretamente<br>
     * Verify if the method removeLogin() was implemented correctly
     */
    @Test
    public void testRemoveLogin() throws Exception {

	c1.adicionaLogin(lg1);
	Assert.assertFalse(c1.adicionaLogin(null));
	Assert.assertTrue(c1.verificaLogin(lg1));

	// Check this out:
	// This new Login isn't registered yet. Even if there is one equals.
	Assert.assertFalse(c1.removeLogin(new Login("acc1", "password",
		TipoLogin.ADMINISTRADOR)));

	// Now I'm trying to remove the correct login:
	Assert.assertTrue(c1.removeLogin(lg1));
	Assert.assertFalse(c1.removeLogin(lg1));

	final int EXPETCTED_SIZE = 0;
	Assert.assertEquals(EXPETCTED_SIZE, c1.numLoginsCadastrados());

	/*
	 * Cool isn't it? Even if I try to remove the SAME login, if it isn't
	 * registered yet, you can't remove it! The Object's REFERENCE must be
	 * registered. That is the only way to modified, access, or do something
	 * else with a Login.
	 */
    }

    /**
     * Verifica se o metodo verificaLogin() foi implementado corretamente<br>
     * Verify if the method verificaLogin() was implemented correctly
     */
    @Test
    public void testVerificaLogin() {

	c2.adicionaLogin(lg1);
	c2.adicionaLogin(lg2);
	c2.adicionaLogin(lg3);

	Login x = lg1;
	Assert.assertTrue(c2.verificaLogin(x));

	final int EXPECTED_SIZE = 3;
	Assert.assertEquals(EXPECTED_SIZE, c2.numLoginsCadastrados());

	Assert.assertTrue(c2.verificaLogin(lg1));
	Assert.assertTrue(c2.verificaLogin(lg2));
	Assert.assertTrue(c2.verificaLogin(lg3));
	Assert.assertFalse(c2.verificaLogin(lg4));
    }

    /**
     * Verifica se o metodo equals() foi implementado corretamente<br>
     * Verify if the method equals() was implemented correctly
     */
    @Test
    public void testEqualsObject() {

	final int EXPECTED_SIZE = 0;
	Assert.assertEquals(EXPECTED_SIZE, c1.numLoginsCadastrados());
	Assert.assertEquals(EXPECTED_SIZE, c2.numLoginsCadastrados());

	Assert.assertTrue(c1.equals(c1));
	Assert.assertTrue(c2.equals(c2));
	Assert.assertTrue(c1.equals(c2));

	Login[] colecao = { lg1, lg2, lg3, lg4 };
	for (Login lg : colecao) {
	    c1.adicionaLogin(lg);
	    c2.adicionaLogin(lg);
	}
	Assert.assertTrue(c1.equals(c2));

    }

}
