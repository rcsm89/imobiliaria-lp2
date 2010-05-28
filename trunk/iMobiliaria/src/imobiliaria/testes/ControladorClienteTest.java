package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;
import imobiliaria.controladores.*;
import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.TipoImovel;
import static org.junit.Assert.*;
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
		
		Assert.assertEquals("Nome: Bruno Paiva - CPF: 123.456.789-10\n" +
				"Endereco: Rua Alberto De Brito, 84 - Data de Nascimento: 04/04/1991 - Preferencia: CASA", 
				controladorCliente.exibeCliente("123.456.789-10"));
		
		
		Assert.assertEquals("Nome: Yuri Farias - CPF: 123.456.789-12\n" +
				"Endereco: Rua Argemiro De Figueiredo, 207 - Data de Nascimento: 04/04/1991 - Preferencia: TERRENO", 
				controladorCliente.exibeCliente("123.456.789-12"));
	}

	@Test
	public void testListaClientes() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaClientesTipoImovel() {
		fail("Not yet implemented");
	}

	@Test
	public void testListaClientesString() {
		fail("Not yet implemented");
	}

}
