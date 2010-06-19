package imobiliaria.testes;

import imobiliaria.auxiliar.TipoLogin;
import imobiliaria.entidades.Login;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Teste que verifica se o tipo Login foi implementado corretamente
 * 
 * @version IT02
 */
public class LoginTest {

	private Login lg1;
	private Login lg2;
	private Login lg3;

	/**
	 * Configura tres tipos diferentes de Login
	 * 
	 * @throws Exception
	 *             se um dos parametros for invalido
	 */
	@Before
	public void setUp() throws Exception {

		lg1 = new Login("login1", "password1", TipoLogin.CLIENTE);

		lg2 = new Login("login2", "password2", TipoLogin.ADMINISTRADOR);

		lg3 = new Login("login3", "password3", TipoLogin.FUNCIONARIO);
	}

	/**
	 * Verifica se o construtor de Login permite parametros invalidos
	 */
	@Test
	public void constructorTest() {

		try {
			new Login(null, null, null);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception error) {
			Assert.assertEquals("Message", error.getMessage());
		}

		try {
			new Login("", "", TipoLogin.ADMINISTRADOR);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception error) {
			Assert.assertEquals("Message", error.getMessage());
		}

		try {
			new Login("       ", "", TipoLogin.FUNCIONARIO);
			Assert
					.fail("Deveria Lancar Excecao aqui");
		} catch (Exception error) {
			Assert.assertEquals("Message", error.getMessage());
		}

		Login x;
		try {
			x = new Login("    a  ", "     ", TipoLogin.ADMINISTRADOR);
			Assert.assertEquals("a", x.getUserName());
			Assert.assertEquals("     ", x.getSenha());
		} catch (Exception erro) {
			Assert.fail("Nao Deveria Cair Nesse catch");
		}

		try {
			x = new Login("    a  c c  ", "     ", TipoLogin.ADMINISTRADOR);
			Assert.assertEquals("acc", x.getUserName());
			Assert.assertEquals("     ", x.getSenha());
		} catch (Exception erro) {
			Assert.fail("Nao Deveria Cair Nesse catch");
		}

	}

	/**
	 * Verifica se cada metodo acessador esta retornando o valor correto<br>
	 */
	@Test
	public void getsTest() {

		Assert.assertEquals(TipoLogin.CLIENTE, lg1.getTipoLogin());
		Assert.assertEquals(TipoLogin.ADMINISTRADOR, lg2.getTipoLogin());
		Assert.assertEquals(TipoLogin.FUNCIONARIO, lg3.getTipoLogin());

		Assert.assertEquals("login1", lg1.getUserName());
		Assert.assertEquals("login2", lg2.getUserName());
		Assert.assertEquals("login3", lg3.getUserName());

		Assert.assertEquals("password1", lg1.getSenha());
		Assert.assertEquals("password2", lg2.getSenha());
		Assert.assertEquals("password3", lg3.getSenha());

	}

	/**
	 * Verifica se o metodo mudarLogin() esta mudando os atributos corretamente<br>
	 */
	@Test
	public void mudarLoginTest() {

		Assert.assertEquals("login1", lg1.getUserName());
		lg1.mudarLogin("NovoLogin");
		Assert.assertEquals("NovoLogin", lg1.getUserName());

		lg1.mudarLogin("");
		lg1.mudarLogin("      ");
		lg1.mudarLogin(null);

		Assert.assertEquals("NovoLogin", lg1.getUserName());

		lg1.mudarLogin("   acc ");
		Assert.assertEquals("acc", lg1.getUserName());
		lg1.mudarLogin("   b  c c ");
		Assert.assertEquals("bcc", lg1.getUserName());

	}

	/**
	 * Verifica se o metodo mudarSenha() esta mudando os atributos corretamente<br>
	 */
	@Test
	public void mudarSenhaTest() {

		Assert.assertEquals("password2", lg2.getSenha());
		lg2.mudarSenha("     ");
		Assert.assertEquals("     ", lg2.getSenha());

		lg2.mudarSenha("");
		lg2.mudarSenha(null);

		Assert.assertEquals("     ", lg2.getSenha());

		lg2.mudarSenha("   acc ");
		Assert.assertEquals("   acc ", lg2.getSenha());
		lg2.mudarSenha("   b  c c ");
		Assert.assertEquals("   b  c c ", lg2.getSenha());

	}

	/**
	 * Verifica se as condicoes para logins serem iguais estao corretas<br>
	 */
	@Test
	public void equalsTest() {

		Assert.assertFalse(lg1.equals(lg2));
		Assert.assertFalse(lg1.equals(lg3));
		Assert.assertFalse(lg3.equals(lg2));

		Assert.assertTrue(lg1.equals(lg1));
		Assert.assertTrue(lg2.equals(lg2));
		Assert.assertTrue(lg3.equals(lg3));

		lg2.mudarLogin(lg1.getUserName());
		Assert.assertTrue(lg1.equals(lg2));
	}

}