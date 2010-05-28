package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;
import imobiliaria.controladores.*;
import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.TipoImovel;
import org.junit.Before;
import org.junit.Test;

public class ControladorClienteTest {

	private ControladorCliente controladorCliente;

	@Before
	public void criaControlador() throws Exception {
		controladorCliente = new ControladorCliente();

		Assert.assertTrue(controladorCliente.adicionaCliente("12345678910",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Alberto de Brito, 84", "Bruno Paiva", TipoImovel.CASA));

		Assert.assertTrue(controladorCliente.adicionaCliente("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", TipoImovel.APARTAMENTO));

		Assert.assertTrue(controladorCliente.adicionaCliente("11022033040",
				new GregorianCalendar(1991, 06, 23),
				"Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO));

		Assert.assertTrue(controladorCliente.adicionaCliente("12345678912",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Argemiro de Figueiredo, 207", "Yuri Farias",
				TipoImovel.TERRENO));

		Assert.assertTrue(controladorCliente.adicionaCliente("12345678901",
				new GregorianCalendar(1991, Calendar.AUGUST, 7),
				"Rua Tocatins, 929", "Daniel Farias", TipoImovel.TERRENO));
	}

	@Test
	public void testGetCliente() throws Exception {

		Assert.assertEquals(new Cliente("12345678910", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua Alberto de Brito, 84",
				"Bruno Paiva", TipoImovel.CASA), controladorCliente
				.getCliente("123.456.789-10"));
	}

	@Test
	public void testExibeCliente() {

		Assert
				.assertEquals(
						"Nome: Bruno Paiva - CPF: 123.456.789-10\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Data de Nascimento: 04/04/1991 - Preferencia: CASA",
						controladorCliente.exibeCliente("123.456.789-10"));

		Assert
				.assertEquals(
						"Nome: Yuri Farias - CPF: 123.456.789-12\n"
								+ "Endereco: Rua Argemiro De Figueiredo, 207 - Data de Nascimento: 04/04/1991 - Preferencia: TERRENO",
						controladorCliente.exibeCliente("123.456.789-12"));
	}

	@Test
	public void testListaClientes() {

		Assert
				.assertEquals(
						"Nome: Bruno Paiva - CPF: 123.456.789-10 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Preferencia: CASA\n\n"
								+ "Nome: Daniel Farias - CPF: 123.456.789-01 - Data de Nascimento: 07/08/1991\n"
								+ "Endereco: Rua Tocatins, 929 - Preferencia: TERRENO\n\n"
								+ "Nome: Jean - CPF: 110.220.330-40 - Data de Nascimento: 23/07/1991\n"
								+ "Endereco: Rua Antonio Joaquim Pequeno - Preferencia: TERRENO\n\n"
								+ "Nome: Thiago Ferreira - CPF: 101.202.303-44 - Data de Nascimento: 13/03/1992\n"
								+ "Endereco: Rua 12 De Outubro - Preferencia: APARTAMENTO\n\n"
								+ "Nome: Yuri Farias - CPF: 123.456.789-12 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Argemiro De Figueiredo, 207 - Preferencia: TERRENO\n\n",
						controladorCliente.listaClientes());
	}

	@Test
	public void testListaClientesTipoImovel() {

		Assert
				.assertEquals(
						"Nome: Jean - CPF: 110.220.330-40 - Data de Nascimento: 23/07/1991\n"
								+ "Endereco: Rua Antonio Joaquim Pequeno - Preferencia: TERRENO\n\n"
								+ "Nome: Yuri Farias - CPF: 123.456.789-12 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Argemiro De Figueiredo, 207 - Preferencia: TERRENO\n\n"
								+ "Nome: Daniel Farias - CPF: 123.456.789-01 - Data de Nascimento: 07/08/1991\n"
								+ "Endereco: Rua Tocatins, 929 - Preferencia: TERRENO\n\n",
						controladorCliente.listaClientes(TipoImovel.TERRENO));

		Assert
				.assertEquals(
						"Nome: Thiago Ferreira - CPF: 101.202.303-44 - Data de Nascimento: 13/03/1992\n"
								+ "Endereco: Rua 12 De Outubro - Preferencia: APARTAMENTO\n\n",
						controladorCliente
								.listaClientes(TipoImovel.APARTAMENTO));

		Assert
				.assertEquals(
						"Nome: Bruno Paiva - CPF: 123.456.789-10 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Preferencia: CASA\n\n",
						controladorCliente.listaClientes(TipoImovel.CASA));

	}

	@Test
	public void testListaClientesPorInicial() throws Exception {
		
		try {
			controladorCliente.listaClientes("1823828");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Letra Invalida", e.getMessage());
		}
		
		try {
			controladorCliente.listaClientes("UAHSHUSAHUUASH");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Letra Invalida", e.getMessage());
		}

		Assert
				.assertEquals(
						"Nome: Bruno Paiva - CPF: 123.456.789-10 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Preferencia: CASA\n\n",
						controladorCliente.listaClientes("b"));

		controladorCliente.adicionaCliente("09876543210",
				new GregorianCalendar(1990, 2, 5), "Rua Oliveira",
				"Bruna Vasconcelos", TipoImovel.APARTAMENTO);

		Assert
				.assertEquals(
						"Nome: Bruno Paiva - CPF: 123.456.789-10 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Preferencia: CASA\n\n"
								+ "Nome: Bruna Vasconcelos - CPF: 098.765.432-10 - Data de Nascimento: 05/03/1990\n"
								+ "Endereco: Rua Oliveira - Preferencia: APARTAMENTO\n\n",
						controladorCliente.listaClientes("b"));
	}

}
