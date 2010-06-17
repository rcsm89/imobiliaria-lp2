package imobiliaria.testes;

import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.entidades.Cliente;
import imobiliaria.processamento.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;

	@Before
	public void setUp() throws Exception {
		cliente1 = new Cliente("12345678910", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua alBerto de brito, 844", "Bruno",
				TipoImovel.CASA);

		cliente2 = new Cliente("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira",
				TipoImovel.APARTAMENTO);

		cliente3 = new Cliente("11022033040", new GregorianCalendar(1991, 06,
				23), "Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO);

	}

	@Test
	public void testaConstrutor() throws Exception {
		try {
			new Cliente("11022033040", new GregorianCalendar(1991, 06, 23),
					"Rua Antonio Joaquim Pequeno", "Jean", null);
			Assert.fail("Deveria ter lancado");
		} catch (Exception e) {
			Assert.assertEquals("Preferencia invalida!", e.getMessage());
		}

		try {
			new Cliente(null, null, "Rua Antonio Joaquim Pequeno", null,
					TipoImovel.CASA);
			Assert.fail("Deveria ter lancado");
		} catch (Exception e) {
			Assert
					.assertEquals(
							"Nome invalido\nCPF invalido\nData de nascimento invalida\n",
							e.getMessage());
		}

		try {
			new Cliente(null, new GregorianCalendar(1991, 06, 23), null,
					"Thiago", TipoImovel.CASA);
			Assert.fail("Deveria ter lancado");
		} catch (Exception e) {
			Assert.assertEquals("Endereco invalido\nCPF invalido\n", e
					.getMessage());
		}

	}

	@Test
	public void testaGetHistoricoCompras() throws Exception {

		ColecaoImoveis compras = new ColecaoImoveis();
		Assert.assertEquals(compras, cliente1.getHistoricoCompras());

	}

	@Test
	public void testaPreferencia() throws Exception {

		Assert.assertEquals(TipoImovel.CASA, cliente1.getPreferencia());
		Assert.assertEquals(TipoImovel.APARTAMENTO, cliente2.getPreferencia());
		Assert.assertEquals(TipoImovel.TERRENO, cliente3.getPreferencia());

		cliente1.setPreferencia(TipoImovel.APARTAMENTO);

		Assert.assertEquals(TipoImovel.APARTAMENTO, cliente1.getPreferencia());

		cliente1.setPreferencia(TipoImovel.TERRENO);

		Assert.assertEquals(TipoImovel.TERRENO, cliente1.getPreferencia());

		cliente1.setPreferencia(TipoImovel.CASA);

		Assert.assertEquals(TipoImovel.CASA, cliente1.getPreferencia());

	}

	@Test
	public void testaEquals() throws Exception {

		Cliente cliente = new Cliente("12345678910", new GregorianCalendar(
				1991, 2, 1), "endere3co", "Brunaaaaaa", TipoImovel.TERRENO);

		Assert.assertTrue(cliente1.equals(cliente1));
		Assert.assertFalse(cliente1.equals(cliente2));
		Assert.assertTrue(cliente2.equals(cliente2));
		Assert.assertFalse(cliente2.equals(cliente3));
		Assert.assertTrue(cliente3.equals(cliente3));
		Assert.assertFalse(cliente3.equals(cliente1));
		Assert.assertTrue(cliente1.equals(cliente));
		Assert.assertTrue(cliente.equals(cliente1));

	}

}
