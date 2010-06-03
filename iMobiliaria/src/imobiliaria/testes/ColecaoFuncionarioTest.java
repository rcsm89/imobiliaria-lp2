package imobiliaria.testes;

import java.util.GregorianCalendar;
import junit.framework.Assert;
import imobiliaria.entidades.Funcionario;
import imobiliaria.processamento.*;
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

		Assert.assertEquals("12345", colFunc1.getFuncionario("12345")
				.getCreci());

		String creciInexistente = "12321345";
		Assert.assertEquals(null, colFunc1.getFuncionario(creciInexistente));

		Assert.assertEquals(2, colFunc1.getFuncionarioPorNome("Thiago").size());
		Assert.assertEquals(1, colFunc1.getFuncionarioPorNome("Yuri").size());
		Assert.assertEquals(0, colFunc1.getFuncionarioPorNome("Fulano").size());
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
