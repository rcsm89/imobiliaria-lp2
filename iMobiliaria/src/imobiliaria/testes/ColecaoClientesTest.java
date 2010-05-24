package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import imobiliaria.processamento.*;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class ColecaoClientesTest {

	private ColecaoClientes clientes1;
	private ColecaoClientes clientes2;
	private ColecaoClientes clientes3;
	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private Cliente cliente4;
	private Cliente cliente5;
	private Imovel imovel1;
	private Imovel imovel2;

	@Before
	public void criaColecaoClientes() throws Exception {

		clientes1 = new ColecaoClientes();
		clientes2 = new ColecaoClientes();
		clientes3 = new ColecaoClientes();

		cliente1 = new Cliente("12345678910", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Alberto de Brito, 84", "Bruno Paiva",
				TipoImovel.CASA);

		cliente2 = new Cliente("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira",
				TipoImovel.APARTAMENTO); // Apartamento

		cliente3 = new Cliente("11022033040", new GregorianCalendar(1991, 06,
				23), "Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO);
		// Terreno

		cliente4 = new Cliente("12345678912", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Argemiro de Figueiredo, 207",
				"Yuri Farias", TipoImovel.TERRENO);

		cliente5 = new Cliente("12345678901", new GregorianCalendar(1991,
				Calendar.AUGUST, 7), "Rua Tocatins, 929", "Daniel Farias",
				TipoImovel.TERRENO);

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel("Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB,"
						+ " Ed. Java, apto 1300", 25000, new Area(4, 6),
				TipoImovel.APARTAMENTO, TipoContratual.VENDA);

	}

	@Test
	public void testaAdicionaCliente() {

		Assert.assertFalse(clientes1.adicionaCliente(null));
		Assert.assertTrue(clientes1.adicionaCliente(cliente1));
		Assert.assertFalse(clientes1.adicionaCliente(cliente1));
		Assert.assertTrue(clientes2.adicionaCliente(cliente2));
		Assert.assertTrue(clientes3.adicionaCliente(cliente3));

	}

	@Test
	public void testaRemoveCliente() {

		clientes1.adicionaCliente(cliente1);
		clientes2.adicionaCliente(cliente2);
		clientes3.adicionaCliente(cliente3);

		Assert.assertFalse(clientes1.removeCliente(""));
		Assert.assertTrue(clientes1.removeCliente(cliente1.getCpf()));
		Assert.assertFalse(clientes2.removeCliente("   "));
		Assert.assertTrue(clientes2.removeCliente(cliente2.getCpf()));
		Assert.assertFalse(clientes3.removeCliente("123"));
		Assert.assertTrue(clientes3.removeCliente(cliente3.getCpf()));
		Assert.assertFalse(clientes3.removeCliente(null));

	}

	@Test
	public void testaGetClientesANDnumeroTotalDeClientes() {

		Assert.assertEquals(0, clientes1.getClientes().size());
		Assert.assertEquals(0, clientes1.numeroTotalDeClientes());

		clientes1.adicionaCliente(cliente1);
		clientes1.adicionaCliente(cliente2);

		Assert.assertEquals(2, clientes1.getClientes().size());
		Assert.assertEquals(2, clientes1.numeroTotalDeClientes());

		clientes1.adicionaCliente(cliente3);
		clientes1.adicionaCliente(cliente4);
		clientes1.adicionaCliente(cliente5);

		Assert.assertEquals(5, clientes1.getClientes().size());
		Assert.assertEquals(5, clientes1.numeroTotalDeClientes());

	}

	@Test
	public void testaGetClientesPorPreferencia() {

		clientes1.adicionaCliente(cliente1); // pref = CASa
		clientes1.adicionaCliente(cliente2); // pref = APARTAMENTO
		clientes1.adicionaCliente(cliente3); // pref = TERRENO (ok)
		clientes1.adicionaCliente(cliente4); // pref = TERRENO (ok)
		clientes1.adicionaCliente(cliente5); // pref = TERRENO (ok)

		Assert.assertEquals(3, clientes1.getClientes(
				TipoImovel.TERRENO).size());

	}

	@Test
	public void testaGetClientesPorPedido() throws Exception {

		cliente1.fazPedido(imovel1); // possui imovel1
		cliente1.fazPedido(imovel2);
		cliente2.fazPedido(imovel1); // possui imovel1
		cliente3.fazPedido(imovel1); // possui imovel1
		cliente5.fazPedido(imovel2);

		clientes1.adicionaCliente(cliente1);
		clientes1.adicionaCliente(cliente2);
		clientes1.adicionaCliente(cliente3);
		clientes1.adicionaCliente(cliente4);
		clientes1.adicionaCliente(cliente5);

		Assert.assertTrue(clientes1.getClientes(imovel1).contains(
				cliente1));
		Assert.assertTrue(clientes1.getClientes(imovel1).contains(
				cliente2));
		Assert.assertTrue(clientes1.getClientes(imovel1).contains(
				cliente3));
		Assert.assertFalse(clientes1.getClientes(imovel1).contains(
				cliente4));
		Assert.assertFalse(clientes1.getClientes(imovel1).contains(
				cliente5));

		Assert.assertTrue(clientes1.getClientes(imovel2).contains(
				cliente1));
		Assert.assertFalse(clientes1.getClientes(imovel2).contains(
				cliente3));
		Assert.assertTrue(clientes1.getClientes(imovel2).contains(
				cliente5));

	}

	@Test
	public void testaGetClientesPorLetraInicial() throws Exception {

		Cliente cliente6 = new Cliente("12345678915", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua Alberto de Brito, 614",
				"Bruno Fabio", TipoImovel.CASA);

		Cliente cliente7 = new Cliente("12345678914", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua Vasco Da Gama, 801",
				"Bruno Farias", TipoImovel.CASA);

		Cliente cliente8 = new Cliente("12335678911", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua Alberto de Brito, 234", "Bruna",
				TipoImovel.CASA);

		clientes1.adicionaCliente(cliente1);
		clientes1.adicionaCliente(cliente2);
		clientes1.adicionaCliente(cliente3);
		clientes1.adicionaCliente(cliente4);
		clientes1.adicionaCliente(cliente5);
		clientes1.adicionaCliente(cliente6);
		clientes1.adicionaCliente(cliente7);
		clientes1.adicionaCliente(cliente8);

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

		Cliente cliente6 = new Cliente("12345678915", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua Floriano Peixoto, 867",
				"Bruno Farias", TipoImovel.CASA);

		Cliente cliente7 = new Cliente("12345678914", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua Joseph Aquino, 124",
				"Henrique Farias", TipoImovel.CASA);

		Cliente cliente8 = new Cliente("12335678911", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua PedroPauloDiniz, 344",
				"Jean Cobra", TipoImovel.CASA);

		Cliente cliente9 = new Cliente("23356789345", new GregorianCalendar(
				1991, Calendar.APRIL, 4), "Rua QualquerLugar, 856",
				"Bruna Vasconcelos", TipoImovel.CASA);

		clientes1.adicionaCliente(cliente1);
		clientes1.adicionaCliente(cliente2);
		clientes1.adicionaCliente(cliente3);
		clientes1.adicionaCliente(cliente3);
		clientes1.adicionaCliente(cliente4);
		clientes1.adicionaCliente(cliente5);
		clientes1.adicionaCliente(cliente6);
		clientes1.adicionaCliente(cliente7);
		clientes1.adicionaCliente(cliente8);
		clientes1.adicionaCliente(cliente9);

		Assert.assertEquals(0, clientes1.getClientes("w").size());
		Assert.assertEquals(4, clientes1.getClientes("Farias").size());
		Assert.assertEquals(3, clientes1.getClientes("Brun").size());
		Assert.assertEquals(5, clientes1.getClientes("u").size());
		Assert.assertEquals(1, clientes1.getClientes("Thi").size());

	}

	@Test
	public void testaGetClientesPorOrdemAlfabetica() {

		clientes3.adicionaCliente(cliente1);
		clientes3.adicionaCliente(cliente2);
		clientes3.adicionaCliente(cliente3);
		clientes3.adicionaCliente(cliente4);
		clientes3.adicionaCliente(cliente5);

		Assert
			.assertEquals(5, clientes3.getClientesPorOrdemAlfabetica()
						.size());
		System.out.println(clientes3.getClientesPorOrdemAlfabetica().toString());
		Assert
			.assertEquals(
					"[Bruno Paiva|123.456.789-10|Rua Alberto De Brito, 84|04/04/1991,"
				  + " Daniel Farias|123.456.789-01|Rua Tocatins, 929|07/08/1991,"
		          + " Jean|110.220.330-40|Rua Antonio Joaquim Pequeno|23/07/1991,"
				  + " Thiago Ferreira|101.202.303-44|Rua 12 De Outubro|13/03/1992,"
				  + " Yuri Farias|123.456.789-12|Rua Argemiro De Figueiredo,"
				  + " 207|04/04/1991]", clientes3
						.getClientesPorOrdemAlfabetica().toString());
	}
}
