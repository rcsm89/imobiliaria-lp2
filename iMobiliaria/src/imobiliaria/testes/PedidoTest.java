package imobiliaria.testes;

import java.util.GregorianCalendar;

import junit.framework.Assert;
import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Pedido;

import org.junit.Before;
import org.junit.Test;

/*
 * As vezes o teste falha se nao for executado muito rapidamente
 * pois tratamos de tempo e comparacao entre os mesmos...
 */

public class PedidoTest {
	
	private Pedido pedido1;
	private Pedido pedido2;
	private Pedido pedido3;
	
	private Imovel imovel1;
	private Imovel imovel2;
	private Imovel imovel3;
	
	private Cliente comprador1;
	private Cliente comprador2;

	@Before
	public void setUp() throws Exception {
		
		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);

		imovel3 = new Imovel("Terreno a venda no Altiplano!",
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB",
				50000, new Area(15, 7), TipoImovel.TERRENO,
				TipoContratual.VENDA);
		
		comprador1 = new Cliente("12345678910", new GregorianCalendar(1991, 10,
				10), "Endereco de Bruno", "Bruno",
				TipoImovel.CASA);

		comprador2 = new Cliente("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira",
				TipoImovel.APARTAMENTO);
		
		
		pedido1 = new Pedido(imovel1, comprador1);
		pedido2 = new Pedido(imovel2, comprador1);
		pedido3 = new Pedido(imovel3, comprador2);
	}
	
	@Test
	public final void testConstrutor() {
		
		try {
			pedido3 = new Pedido(imovel1, comprador2);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel invalido\n", e.getMessage());
		}
		
		try {
			pedido3 = new Pedido(imovel1, null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel invalido\n" +
					"Comprador invalido", e.getMessage());
		}
	}

	@Test
	public final void testGetDataDoPedido() {
		
		Assert.assertTrue(pedido1.getDataDoPedido().equals(new GregorianCalendar()));
		Assert.assertTrue(pedido2.getDataDoPedido().equals(new GregorianCalendar()));
		Assert.assertTrue(pedido3.getDataDoPedido().equals(new GregorianCalendar()));
		
	}

	@Test
	public final void testGetImovel() {
		
		Assert.assertEquals(imovel1, pedido1.getImovel());
		
		Assert.assertEquals(imovel2, pedido2.getImovel());
		
		Assert.assertEquals(imovel3, pedido3.getImovel());
		
	}

	@Test
	public final void testGetComprador() {
		
		Assert.assertEquals(comprador1, pedido1.getComprador());
		
		Assert.assertEquals(comprador1, pedido2.getComprador());
		
		Assert.assertEquals(comprador2, pedido3.getComprador());
		
	}

	@Test
	public final void testCompareTo() throws Exception {
		
		// Foram criados na mesma hora
		
		Assert.assertTrue(pedido1.compareTo(pedido2) == 0);
		
		Assert.assertTrue(pedido2.compareTo(pedido3) == 0);
		
		Assert.assertTrue(pedido3.compareTo(pedido1) == 0);
		
	}

}
