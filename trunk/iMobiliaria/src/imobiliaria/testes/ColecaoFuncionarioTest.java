package imobiliaria.testes;

import java.util.GregorianCalendar;
import junit.framework.Assert;
import imobiliaria.colecoes.ColecaoFuncionario;
import imobiliaria.entidades.Funcionario;
import org.junit.Before;
import org.junit.Test;

public class ColecaoFuncionarioTest {

	private ColecaoFuncionario colFunc1;
	private ColecaoFuncionario colFunc2;
	private Funcionario func1;

	@Before
	public void setUp() throws Exception {
		colFunc1 = new ColecaoFuncionario();
		colFunc2 = new ColecaoFuncionario();
		func1 = new Funcionario("12345678910", new GregorianCalendar(1991, 2,
				17), "Rua 12 de Outubro", "Yuri Farias", "12345");
	}

	@Test
	public void testaAdicionaFuncionario() throws Exception {
		colFunc1.adicionaFuncionario(new Funcionario("10120230344",
				new GregorianCalendar(1991, 1, 18), "Rua 12 de Outubro",
				"Thiago Ferreira", "00111"));
		colFunc1.adicionaFuncionario(new Funcionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua 12 de Outubro",
				"Yuri Farias", "12345"));
		Assert.assertEquals(2, colFunc1.getNumFuncionarios());
		// Nao adiciona um mesmo funcionario
		Assert.assertFalse(colFunc1.adicionaFuncionario(func1));
		Assert.assertEquals(2, colFunc1.getNumFuncionarios());
	}

	@Test(expected = Exception.class)
	public void testaAdicionaNull() {
		colFunc1.adicionaFuncionario(null);
	}

	@Test
	public void testaRemoveFuncionario() throws Exception {
		colFunc1.adicionaFuncionario(new Funcionario("10120230344",
				new GregorianCalendar(1991, 1, 18), "Rua Rodrigues Alves",
				"Thiago Ferreira", "00111"));
		colFunc1.adicionaFuncionario(new Funcionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua Antonio Pequeno",
				"Yuri Farias", "12345"));
		colFunc1.adicionaFuncionario(new Funcionario("11223344556",
				new GregorianCalendar(1991, 3, 18), "Rua 12 de Outubro",
				"Bruno Fabio", "11111"));

		Assert.assertEquals(3, colFunc1.getNumFuncionarios());

		String numCreci = "00111";
		String numCreciInexistente = "00000";
		Assert.assertTrue(colFunc1.removeFuncionario(numCreci));
		Assert.assertFalse(colFunc1.removeFuncionario(numCreciInexistente));
		Assert.assertEquals(2, colFunc1.getNumFuncionarios());

		String nomeInexistente = "Joao Ninguem";
		Assert.assertTrue(colFunc1.removeFuncionarioPorNome("Yuri Farias"));
		Assert.assertFalse(colFunc1.removeFuncionarioPorNome(nomeInexistente));
		Assert.assertEquals(1, colFunc1.getNumFuncionarios());

		try {
			String creciInvalido = "0a12sa";
			colFunc1.removeFuncionario(creciInvalido);
			Assert.fail("Esperava creci invalido");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}

		try {
			String creciInvalido = null;
			colFunc1.removeFuncionario(creciInvalido);
			Assert.fail("Esperava creci invalido");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}
		
		try {
			String creciInvalido = "";
			colFunc1.removeFuncionario(creciInvalido);
			Assert.fail("Esperava creci invalido");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}
		
		try {
			String nomeInvalido = "Th14g0";
			colFunc1.removeFuncionarioPorNome(nomeInvalido);
			Assert.fail("Esperava nome invalido");
		} catch (Exception e) {
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}

		try {
			String nomeInvalido = null;
			colFunc1.removeFuncionarioPorNome(nomeInvalido);
			Assert.fail("Esperava nome invalido");
		} catch (Exception e) {
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}

		try {
			String nomeInvalido = "";
			colFunc1.removeFuncionarioPorNome(nomeInvalido);
			Assert.fail("Esperava nome invalido");
		} catch (Exception e) {
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
	}

	@Test
	public void testaGetFuncionario() throws Exception {
		colFunc1.adicionaFuncionario(new Funcionario("10120230344",
				new GregorianCalendar(1991, 1, 18), "Rua 12 de Outubro",
				"Thiago Ferreira", "00111"));

		colFunc1.adicionaFuncionario(new Funcionario("08928883474",
				new GregorianCalendar(1991, 3, 18), "Rua Francisco Brandao",
				"Thiago Neves", "111"));

		colFunc1.adicionaFuncionario(new Funcionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua Rodrigues Alves",
				"Yuri Farias", "12345"));

		Assert.assertEquals("12345", colFunc1.getFuncionario("12345").getCreci());

		String creciFuncInexistente = "12321345";
		Assert.assertEquals(null, colFunc1.getFuncionario(creciFuncInexistente));

		Assert.assertEquals(2, colFunc1.getFuncionarioPorNome("Thiago").size());
		Assert.assertEquals(1, colFunc1.getFuncionarioPorNome("Yuri").size());
		Assert.assertEquals(0, colFunc1.getFuncionarioPorNome("Fulano").size());

		try {
			colFunc1.getFuncionario(null);
			Assert.fail("Deveria ter lancado excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}
		try {
			colFunc1.getFuncionario("12AB");
			Assert.fail("Deveria ter lancado excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}
		try {
			colFunc1.getFuncionario(" ");
			Assert.fail("Deveria ter lancado excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}
		try {
			colFunc1.getFuncionarioPorNome(" ");
			Assert.fail("Deveria ter lancado excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
		try {
			colFunc1.getFuncionarioPorNome("Th14g0");
			Assert.fail("Deveria ter lancado excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
		try {
			colFunc1.getFuncionarioPorNome(null);
			Assert.fail("Deveria ter lancado excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
	}

	@Test
	public void testaFuncionarioAddPorOdemAlfabetica() throws Exception {
		colFunc1.adicionaFuncionario(new Funcionario("10120230344",
				new GregorianCalendar(1991, 1, 18), "Rua Rodrigues Alves",
				"Thiago Ferreira", "00111"));
		colFunc1.adicionaFuncionario(new Funcionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua Antonio Pequeno",
				"Yuri Farias", "12345"));
		colFunc1.adicionaFuncionario(new Funcionario("11223344556",
				new GregorianCalendar(1991, 3, 18), "Rua 12 de Outubro",
				"Bruno Fabio", "11111"));

		Assert
				.assertEquals(
						"[Bruno Fabio|112.233.445-56|Rua 12 De Outubro|18/04/1991|11111,"
								+ " Thiago Ferreira|101.202.303-44|Rua Rodrigues Alves|18/02/1991|00111,"
								+ " Yuri Farias|123.456.789-10|Rua Antonio Pequeno|17/03/1991|12345]",
						colFunc1.getColecaoFuncionarios().toString());

	}

	@Test
	public void testaEquals() throws Exception {
		colFunc1.adicionaFuncionario(new Funcionario("10120230344",
				new GregorianCalendar(1991, 1, 18), "Rua Rodrigues Alves",
				"Thiago Ferreira", "00111"));
		colFunc1.adicionaFuncionario(new Funcionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua Antonio Pequeno",
				"Yuri Farias", "12345"));
		colFunc2.adicionaFuncionario(new Funcionario("10120230344",
				new GregorianCalendar(1991, 1, 18), "Rua Rodrigues Alves",
				"Thiago Ferreira", "00111"));

		Assert.assertFalse(colFunc1.equals(colFunc2));

		colFunc2.adicionaFuncionario(new Funcionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua Antonio Pequeno",
				"Yuri Farias", "12345"));

		Assert.assertTrue(colFunc1.equals(colFunc2));
	}
}
