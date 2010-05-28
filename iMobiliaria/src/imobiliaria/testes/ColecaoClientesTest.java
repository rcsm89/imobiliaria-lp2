package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import imobiliaria.processamento.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColecaoClientesTest {

	private ColecaoClientes clientes1;

	@Before
	public void criaColecaoClientes() throws Exception {

		clientes1 = new ColecaoClientes();

		// Adicionando Clientes

		Assert.assertTrue(clientes1.adicionaCliente(new Cliente("12345678910",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Alberto de Brito, 84", "Bruno Paiva", TipoImovel.CASA)));

		Assert.assertTrue(clientes1.adicionaCliente(new Cliente("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", TipoImovel.APARTAMENTO)));

		Assert.assertTrue(clientes1.adicionaCliente(new Cliente("11022033040",
				new GregorianCalendar(1991, 06, 23),
				"Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO)));

		Assert.assertTrue(clientes1.adicionaCliente(new Cliente("12345678912",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Argemiro de Figueiredo, 207", "Yuri Farias",
				TipoImovel.TERRENO)));

		Assert.assertTrue(clientes1.adicionaCliente(new Cliente("12345678901",
				new GregorianCalendar(1991, Calendar.AUGUST, 7),
				"Rua Tocatins, 929", "Daniel Farias", TipoImovel.TERRENO)));

	}

	@Test
	public void testaAdicionaCliente() throws Exception {

		Assert.assertFalse(clientes1.adicionaCliente(new Cliente("12345678910",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Alberto de Brito, 84", "Bruno Paiva", TipoImovel.CASA)));

		Assert.assertFalse(clientes1.adicionaCliente(new Cliente("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", TipoImovel.APARTAMENTO)));

		Assert.assertFalse(clientes1.adicionaCliente(new Cliente("11022033040",
				new GregorianCalendar(1991, 06, 23),
				"Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO)));

		Assert.assertFalse(clientes1.adicionaCliente(new Cliente("12345678912",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Argemiro de Figueiredo, 207", "Yuri Farias",
				TipoImovel.TERRENO)));

		Assert.assertFalse(clientes1.adicionaCliente(new Cliente("12345678901",
				new GregorianCalendar(1991, Calendar.AUGUST, 7),
				"Rua Tocatins, 929", "Daniel Farias", TipoImovel.TERRENO)));

	}

	@Test
	public void testaRemoveCliente() {

		Assert.assertTrue(clientes1.removeCliente("123.456.789-10"));
		Assert.assertFalse(clientes1.removeCliente("UHASHUSAU"));
		Assert.assertTrue(clientes1.removeCliente("123.456.789-01"));
		Assert.assertFalse(clientes1.removeCliente(null));
	}

	@Test
	public void testaGetClientesANDnumeroTotalDeClientes() throws Exception {

		Assert.assertEquals(5, clientes1.numeroTotalDeClientes());
		Assert.assertEquals(5, clientes1.getClientes().size());

		clientes1.removeCliente("123.456.789-10");
		Assert.assertEquals(4, clientes1.numeroTotalDeClientes());
		Assert.assertEquals(4, clientes1.getClientes().size());

		clientes1.adicionaCliente(new Cliente("12345678910", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Alberto de Brito, 84", "Bruno Paiva",
				TipoImovel.CASA));

		Assert.assertEquals(5, clientes1.getClientes().size());
		Assert.assertEquals(5, clientes1.numeroTotalDeClientes());

	}

	@Test
	public void testaGetClientesPorPreferencia() {

		Assert
				.assertEquals(3, clientes1.getClientes(TipoImovel.TERRENO)
						.size());

	}

	@Test
	public void testaGetClientesPorLetraInicial() throws Exception {
		
		try {
			clientes1.getClientesPorLetraInicial(null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Letra Invalida", e.getMessage());
		}
		
		try {
			clientes1.getClientesPorLetraInicial("2");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Letra Invalida", e.getMessage());
		}
		
		try {
			clientes1.getClientesPorLetraInicial("UASHDUASD");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Letra Invalida", e.getMessage());
		}

		clientes1.adicionaCliente(new Cliente("12345678915", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Alberto de Brito, 614", "Bruno Fabio",
				TipoImovel.CASA));

		clientes1.adicionaCliente(new Cliente("12345678914", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Vasco Da Gama, 801", "Bruno Farias",
				TipoImovel.CASA));

		clientes1.adicionaCliente(new Cliente("12335678911", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Alberto de Brito, 234", "Bruna",
				TipoImovel.CASA));

		Assert
				.assertEquals(4, clientes1.getClientesPorLetraInicial("b")
						.size());

		Assert
				.assertEquals(1, clientes1.getClientesPorLetraInicial("j")
						.size());

		Assert
				.assertEquals(0, clientes1.getClientesPorLetraInicial("l")
						.size());

	}

	@Test
	public void testaGetClientesPorNome() throws Exception {

		clientes1.adicionaCliente(new Cliente("12345678915", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Floriano Peixoto, 867",
				"Bruno Farias", TipoImovel.CASA));

		clientes1.adicionaCliente(new Cliente("12345678914", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Joseph Aquino, 124",
				"Henrique Farias", TipoImovel.CASA));

		clientes1.adicionaCliente(new Cliente("12335678911", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua PedroPauloDiniz, 344", "Jean Cobra",
				TipoImovel.CASA));

		clientes1.adicionaCliente(new Cliente("23356789345", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua QualquerLugar, 856",
				"Bruna Vasconcelos", TipoImovel.CASA));

		Assert.assertEquals(0, clientes1.getClientes("w").size());
		Assert.assertEquals(4, clientes1.getClientes("Farias").size());
		Assert.assertEquals(3, clientes1.getClientes("Brun").size());
		Assert.assertEquals(5, clientes1.getClientes("u").size());
		Assert.assertEquals(1, clientes1.getClientes("Thi").size());

	}

	@Test
	public void testaGetClientesPorOrdemAlfabetica() throws Exception {

		Assert
				.assertEquals(5, clientes1.getClientesPorOrdemAlfabetica()
						.size());
		
		Assert
				.assertEquals(
						"[Bruno Paiva|123.456.789-10|Rua Alberto De Brito, 84|04/04/1991,"
								+ " Daniel Farias|123.456.789-01|Rua Tocatins, 929|07/08/1991,"
								+ " Jean|110.220.330-40|Rua Antonio Joaquim Pequeno|23/07/1991,"
								+ " Thiago Ferreira|101.202.303-44|Rua 12 De Outubro|13/03/1992,"
								+ " Yuri Farias|123.456.789-12|Rua Argemiro De Figueiredo,"
								+ " 207|04/04/1991]", clientes1
								.getClientesPorOrdemAlfabetica().toString());
	}
}
