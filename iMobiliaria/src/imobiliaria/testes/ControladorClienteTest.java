package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.controladores.*;
import imobiliaria.entidades.Cliente;
import imobiliaria.exceptions.ClienteNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class ControladorClienteTest {

	private ControladorCliente controladorCliente;

	@Before
	public void setUp() throws Exception {
		controladorCliente = ControladorCliente.getInstance();
	}

	@Test
	public void criaControlador() throws Exception {

		Assert.assertTrue(controladorCliente.adicionaCliente("12345678910",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Alberto de Brito, 84", "Bruno Paiva", TipoImovel.CASA));

		Assert
				.assertTrue(controladorCliente.adicionaCliente("10120230344",
						new GregorianCalendar(1991, Calendar.MARCH, 13),
						"Rua 12 de Outubro", "Thiago Ferreira",
						TipoImovel.APARTAMENTO));

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

		try {
			controladorCliente.adicionaCliente(null, null, null, null, null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals(
					"Nome invalido\nEndereco invalido\nCPF invalido\n"
							+ "Data de nascimento invalida\n", e.getMessage());
		}

	}

	@Test
	public void testGetCliente() throws Exception {
		Cliente clienteProcurado = new Cliente("12345678910",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Alberto de Brito, 84", "Bruno Paiva", TipoImovel.CASA);

		Assert.assertEquals(clienteProcurado, controladorCliente
				.getCliente("123.456.789-10"));

		try {
			controladorCliente.getCliente(null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("CPF invalido", e.getMessage());
		}
		try {
			controladorCliente.getCliente("  ");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("CPF invalido", e.getMessage());
		}

	}

	@Test
	public void testExibeCliente() throws Exception {

		Assert.assertEquals("Nome: Bruno Paiva - CPF: 123.456.789-10\n"
				+ "Endereco: Rua Alberto De Brito, 84 - "
				+ "Data de Nascimento: 04/04/1991 - Preferencia: CASA",
				controladorCliente.exibeCliente("123.456.789-10"));

		Assert.assertEquals("Nome: Yuri Farias - CPF: 123.456.789-12\n"
				+ "Endereco: Rua Argemiro De Figueiredo, 207 - "
				+ "Data de Nascimento: 04/04/1991 - Preferencia: TERRENO",
				controladorCliente.exibeCliente("123.456.789-12"));

		try {
			controladorCliente.exibeCliente("109.876.543-21");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception ex) {
			Assert.assertEquals("Cliente nao existente", ex.getMessage());

		}

	}

	@Test
	public void testListaClientes() throws Exception {

		Assert
				.assertEquals(
						"Nome: Bruno Paiva - CPF: 123.456.789-10 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Preferencia: CASA\n\n"
								+ "Nome: Daniel Farias - CPF: 123.456.789-01 - Data de Nascimento: 07/08/1991\n"
								+ "Endereco: Rua Tocatins, 929 - Preferencia: TERRENO\n\n"
								+ "Nome: Jean - CPF: 110.220.330-40 - Data de Nascimento: 23/07/1991\n"
								+ "Endereco: Rua Antonio Joaquim Pequeno - Preferencia: TERRENO\n\n"
								+ "Nome: Thiago Ferreira - CPF: 101.202.303-44 - Data de Nascimento: 13/03/1991\n"
								+ "Endereco: Rua 12 De Outubro - Preferencia: APARTAMENTO\n\n"
								+ "Nome: Yuri Farias - CPF: 123.456.789-12 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Argemiro De Figueiredo, 207 - Preferencia: TERRENO\n\n",
						controladorCliente.listaClientes());
	}

	@Test
	public void testListaClientesTipoImovel() {

		Assert
				.assertEquals(
						"Nome: Daniel Farias - CPF: 123.456.789-01 - Data de Nascimento: 07/08/1991\n"
								+ "Endereco: Rua Tocatins, 929 - Preferencia: TERRENO\n\n"
								+ "Nome: Jean - CPF: 110.220.330-40 - Data de Nascimento: 23/07/1991\n"
								+ "Endereco: Rua Antonio Joaquim Pequeno - Preferencia: TERRENO\n\n"
								+ "Nome: Yuri Farias - CPF: 123.456.789-12 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Argemiro De Figueiredo, 207 - Preferencia: TERRENO\n\n",

						controladorCliente.listaClientes(TipoImovel.TERRENO));

		Assert
				.assertEquals(
						"Nome: Thiago Ferreira - CPF: 101.202.303-44 - Data de Nascimento: 13/03/1991\n"
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

		try {
			controladorCliente.listaClientes("1");
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
						"Nome: Bruna Vasconcelos - CPF: 098.765.432-10 - Data de Nascimento: 05/03/1990\n"
								+ "Endereco: Rua Oliveira - Preferencia: APARTAMENTO\n\n"
								+ "Nome: Bruno Paiva - CPF: 123.456.789-10 - Data de Nascimento: 04/04/1991\n"
								+ "Endereco: Rua Alberto De Brito, 84 - Preferencia: CASA\n\n",
						controladorCliente.listaClientes("b"));
	}
	
	@Test
	public void testRemoveCliente(){
		int numeroDeClientes = controladorCliente.getColecaoClientes().numeroTotalDeClientes();
		
		Assert.assertEquals(6, numeroDeClientes);
		Assert.assertTrue(controladorCliente.removeCliente("123.456.789-10"));
		
		numeroDeClientes = controladorCliente.getColecaoClientes().numeroTotalDeClientes();
		Assert.assertEquals(5, numeroDeClientes);
		
		String cpfDeNaoExistente = "098.143.124-21"; 
		Assert.assertFalse(controladorCliente.removeCliente(cpfDeNaoExistente));
		String cpfInvalido = "098.1431212322421"; 
		Assert.assertFalse(controladorCliente.removeCliente(cpfInvalido));
		String cpfNull = null; 
		Assert.assertFalse(controladorCliente.removeCliente(cpfNull));
		
	}
	
	@Test
	public void testGetClientePorUserName() throws ClienteNotFoundException {
		
		Assert.assertEquals(
				controladorCliente.getCliente("123.456.789-12"),
				controladorCliente.getClientePorUsername("12345678912"));
		
		// esse CPF a seguir foi removido do controlador no teste passado,
		// por isso, esta retornando null
		Assert.assertNull(controladorCliente.
				getClientePorUsername("123.456.789-10"));
		
		try{
			controladorCliente.getClientePorUsername(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Username invalido", e.getMessage());
		}
		
		try{
			controladorCliente.getClientePorUsername("     ");
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Username invalido", e.getMessage());
		}
		
		try{
			controladorCliente.getClientePorUsername("");
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Username invalido", e.getMessage());
		}
	}
}
