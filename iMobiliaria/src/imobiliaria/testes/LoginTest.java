/*
 *  It needs to change the Exceptions in the test for the specific Exception (Like LoginInvalidoException)
 */
package imobiliaria.testes;

import imobiliara.auxiliar.TipoLogin;
import imobiliaria.entidades.Login;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Teste que verifica se o tipo Login foi implementado corretamente<br>
 * Test that verify if the type Login was implemented correctly
 * 
 * @author Jeanderson Barros Candido
 * @version iteracao 2
 * 
 */
public class LoginTest {

    private Login lg1;
    private Login lg2;
    private Login lg3;

    /**
     * Configura tres tipos diferentes de Login<br>
     * Sets up three different types of Login
     * 
     * @throws Exception
     *             se um dos parametros for invalido<br>
     *             if one of those parameters is invalid
     */
    @Before
    public void setUp() throws Exception {

	// Sets up a new cliente's login
	lg1 = new Login("login1", "password1", TipoLogin.CLIENTE);

	// Sets up a new admin's login
	lg2 = new Login("login2", "password2", TipoLogin.ADMINISTRADOR);

	// Sets up a new funcionario's login
	lg3 = new Login("login3", "password3", TipoLogin.FUNCIONARIO);
    }

    /**
     * Verifica se o construtor de Login permite parametros invalidos<br>
     * Verify if the Login's constructor permits invalids parameters
     */
    @Test
    public void constructorTest() {

	// Case 1: Null parameters
	try {
	    new Login(null, null, null);
	    Assert.fail("It should throws an exception for null parameters");
	} catch (Exception error) {
	    Assert.assertEquals("Message", error.getMessage());
	}

	// Case 2: Empty parameters
	try {
	    new Login("", "", TipoLogin.ADMINISTRADOR);
	    Assert.fail("It should thorws an exception for empty parameters");
	} catch (Exception error) {
	    Assert.assertEquals("Message", error.getMessage());
	}

	// Trying to sets up with empty spaces...
	try {
	    new Login("       ", "", TipoLogin.FUNCIONARIO);
	    Assert
		    .fail("It should thorws an exception for empty spaces parameters");
	} catch (Exception error) {
	    Assert.assertEquals("Message", error.getMessage());
	}

	// In this case, for the user-name, the constructor removes all the
	// empty space and let the characters.
	Login x;
	try {
	    x = new Login("    a  ", "     ", TipoLogin.ADMINISTRADOR);
	    Assert.assertEquals("a", x.getUserName());
	    Assert.assertEquals("     ", x.getSenha());
	} catch (Exception erro) {
	    Assert.fail("It shouldn't thrower exception");
	}
	try {
	    x = new Login("    a  c c  ", "     ", TipoLogin.ADMINISTRADOR);
	    Assert.assertEquals("acc", x.getUserName());
	    Assert.assertEquals("     ", x.getSenha());
	} catch (Exception erro) {
	    Assert.fail("It shouldn't thrower exception");
	}

    }

    /**
     * Verifica se cada metodo acessador esta retornando o valor correto<br>
     * Verify if the each access method is returning the correct value
     */
    @Test
    public void getsTest() {

	// Case 1: get for TipoLogin
	Assert.assertEquals(TipoLogin.CLIENTE, lg1.getTipoLogin());
	Assert.assertEquals(TipoLogin.ADMINISTRADOR, lg2.getTipoLogin());
	Assert.assertEquals(TipoLogin.FUNCIONARIO, lg3.getTipoLogin());

	// Case 2: get for UserName
	Assert.assertEquals("login1", lg1.getUserName());
	Assert.assertEquals("login2", lg2.getUserName());
	Assert.assertEquals("login3", lg3.getUserName());

	// Case 3: get for Password
	Assert.assertEquals("password1", lg1.getSenha());
	Assert.assertEquals("password2", lg2.getSenha());
	Assert.assertEquals("password3", lg3.getSenha());

    }

    /**
     * Verifica se o metodo mudarLogin() esta mudando os atributos corretamente<br>
     * Verify if the method mudarLogin() is changing correctly the attributes
     */
    @Test
    public void mudarLoginTest() {

	Assert.assertEquals("login1", lg1.getUserName());
	lg1.mudarLogin("NovoLogin");
	Assert.assertEquals("NovoLogin", lg1.getUserName());

	// Trying to set a invalid parameter: null/empty space
	lg1.mudarLogin("");
	lg1.mudarLogin("      ");
	lg1.mudarLogin(null);

	// Proving that the method doesn't permits those invalids types
	Assert.assertEquals("NovoLogin", lg1.getUserName());

	// Mixing spaces with characters
	lg1.mudarLogin("   acc ");
	Assert.assertEquals("acc", lg1.getUserName());
	lg1.mudarLogin("   b  c c ");
	Assert.assertEquals("bcc", lg1.getUserName());

    }

    /**
     * Verifica se o metodo mudarSenha() esta mudando os atributos corretamente<br>
     * Verify if the method mudarSenha() is changing correctly the attributes
     */
    @Test
    public void mudarSenhaTest() {

	Assert.assertEquals("password2", lg2.getSenha());
	lg2.mudarSenha("     ");
	Assert.assertEquals("     ", lg2.getSenha());

	// Trying to set a invalid parameter: null
	lg2.mudarSenha("");
	lg2.mudarSenha(null);

	// Proving that the method doesn't permits those invalids types
	Assert.assertEquals("     ", lg2.getSenha());

	// Mixing spaces with characters
	lg2.mudarSenha("   acc ");
	Assert.assertEquals("   acc ", lg2.getSenha());
	lg2.mudarSenha("   b  c c ");
	Assert.assertEquals("   b  c c ", lg2.getSenha());

    }

    /**
     * Verifica se as condicoes para logins serem iguais estao corretas<br>
     * Verify if the conditions to have same logins are correct
     */
    @Test
    public void equalsTest() {

	Assert.assertFalse(lg1.equals(lg2));
	Assert.assertFalse(lg1.equals(lg3));
	Assert.assertFalse(lg3.equals(lg2));

	Assert.assertTrue(lg1.equals(lg1));
	Assert.assertTrue(lg2.equals(lg2));
	Assert.assertTrue(lg3.equals(lg3));

	// The condition to have one login equals to another, is having the same
	// user-name, independent of the login's type :)
	lg2.mudarLogin(lg1.getUserName());
	Assert.assertTrue(lg1.equals(lg2));
    }

}
