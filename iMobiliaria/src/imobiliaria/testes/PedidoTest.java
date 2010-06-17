package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
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
	
	private Calendar dataCriacao1;
	private Calendar dataCriacao2;
	private Calendar dataCriacao3;

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
		dataCriacao1 = new GregorianCalendar();
		
		// Aguarda um tempo
		
		Thread.sleep(200);
		pedido2 = new Pedido(imovel2, comprador1);
		dataCriacao2 = new GregorianCalendar();
		
		// Aguarda um tempo
		Thread.sleep(200);
		pedido3 = new Pedido(imovel3, comprador2);
		dataCriacao3 = new GregorianCalendar();
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
		try {
			pedido3 = new Pedido(null, null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel invalido\n" +
					"Comprador invalido", e.getMessage());
		}
	}

	@Test
	public final void testGetDataDoPedido() throws InterruptedException {
		
		Assert.assertTrue(pedido1.getDataDoPedido().equals(dataCriacao1));
		Assert.assertTrue(pedido2.getDataDoPedido().equals(dataCriacao2));
		Assert.assertTrue(pedido3.getDataDoPedido().equals(dataCriacao3));
		
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
		
		// Pedido foi criado primeiro que o pedido2
		Assert.assertTrue(pedido1.compareTo(pedido2) < 0);
		
		// Pedido 2 foi criado primeiro que pedido3
		Assert.assertTrue(pedido2.compareTo(pedido3) < 0);
		
		// Pedido 3 foi criado depois de pedido1
		Assert.assertTrue(pedido3.compareTo(pedido1) > 0);
		
	}
	
	@Test
	public final void testExibeInformacao() throws Exception {
		
		Assert.assertEquals("Imovel: (15) Casa imobiliada para Alugar - Valor: 3500.0\n" +
				"Cliente: Bruno (123.456.789-10)", pedido1.exibeInformacao());
		
		Assert.assertEquals("Imovel: (16) Apartamento a Venda!!! - Valor: 25000.0\n" +
				"Cliente: Bruno (123.456.789-10)", pedido2.exibeInformacao());
		
		Assert.assertEquals("Imovel: (17) Terreno a venda no Altiplano! - Valor: 50000.0\n" +
				"Cliente: Thiago Ferreira (101.202.303-44)", pedido3.exibeInformacao());
	}

}
