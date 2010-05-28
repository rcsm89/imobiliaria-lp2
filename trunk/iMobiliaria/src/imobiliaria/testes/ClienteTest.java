package imobiliaria.testes;

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
	private Imovel imovel1;
	private Imovel imovel2;


	@Before
	public void criaClientes() throws Exception {
		Calendar nascimento = new GregorianCalendar(1991, Calendar.APRIL, 4);
		String endereco = "Rua alBerto de brito, 844";
		TipoImovel preferencia = TipoImovel.CASA;

		cliente1 = new Cliente("12345678910", nascimento, endereco, "Bruno",
				preferencia);

		Assert.assertEquals("Rua Alberto De Brito, 844",
				cliente1.getEndereco());
		Assert.assertEquals("123.456.789-10", cliente1.getCpf());
		cliente2 = new Cliente("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira",
				TipoImovel.APARTAMENTO); // Apartamento

		cliente3 = new Cliente("11022033040", new GregorianCalendar(1991, 06,
				23), "Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO); // Terreno

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel("Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB,"
						+ " Ed. Java, apto 1300", 25000, new Area(4, 6),
				TipoImovel.APARTAMENTO, TipoContratual.VENDA);

	}

	@Test
	public void testaGetHistoricoCompras() throws Exception {

		ColecaoImoveis compras = new ColecaoImoveis();

		Assert.assertEquals(compras, cliente1.getHistoricoCompras());

	}

	@Test
	public void testaFazPedido() throws Exception {

		ColecaoImoveis pedidos = new ColecaoImoveis();

		Assert.assertEquals(pedidos, cliente1.getPedidos());
		Assert.assertEquals(0, cliente1.getPedidos().getImoveis().size());

		cliente1.fazPedido(imovel1);
		pedidos.addImovel(imovel1);
		Assert.assertEquals(pedidos, cliente1.getPedidos());

		Assert.assertEquals(1, cliente1.getPedidos().getImoveis().size());

		cliente1.fazPedido(imovel2);
		pedidos.addImovel(imovel2);
		Assert.assertEquals(pedidos, cliente1.getPedidos());

		Assert.assertEquals(2, cliente1.getPedidos().getImoveis().size());

		try {
			cliente3.fazPedido(null);
		} catch (Exception e) {
			Assert.assertEquals("Pedido Invalido", e.getMessage());
		}

	}

	@Test
	public void testaGetPedidos() throws Exception {

		Assert.assertTrue(cliente1.getPedidos().equals(new ColecaoImoveis()));

		cliente2.fazPedido(imovel1);
		cliente2.fazPedido(imovel2);

		Assert.assertEquals(2, cliente2.getPedidos().getImoveis().size());

		cliente3.fazPedido(imovel1);

		Assert.assertEquals(1, cliente3.getPedidos().getImoveis().size());

	}

	@Test
	public void testaGetPreferencia() {

		Assert.assertEquals(TipoImovel.CASA, cliente1.getPreferencia());
		Assert.assertEquals(TipoImovel.APARTAMENTO, cliente2.getPreferencia());
		Assert.assertEquals(TipoImovel.TERRENO, cliente3.getPreferencia());

	}

	@Test
	public void testaSetPreferencia() {

		cliente1.setPreferencia(TipoImovel.APARTAMENTO);

		Assert.assertEquals(TipoImovel.APARTAMENTO, cliente1.getPreferencia());

		cliente1.setPreferencia(TipoImovel.TERRENO);

		Assert.assertEquals(TipoImovel.TERRENO, cliente1.getPreferencia());

		cliente1.setPreferencia(TipoImovel.CASA);

		Assert.assertEquals(TipoImovel.CASA, cliente1.getPreferencia());

	}

	@Test
	public void testaEquals() {

		Assert.assertTrue(cliente1.equals(cliente1));
		Assert.assertFalse(cliente1.equals(cliente2));
		Assert.assertTrue(cliente2.equals(cliente2));
		Assert.assertFalse(cliente2.equals(cliente3));
		Assert.assertTrue(cliente3.equals(cliente3));
		Assert.assertFalse(cliente3.equals(cliente1));

	}

}
