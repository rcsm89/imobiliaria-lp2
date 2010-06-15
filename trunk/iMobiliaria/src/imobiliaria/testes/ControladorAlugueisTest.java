package imobiliaria.testes;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorAlugueis;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorPedidos;
import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Area;
import org.junit.Test;

public class ControladorAlugueisTest {

	private ControladorAlugueis cAlugueis = ControladorAlugueis.getInstance();

	@Test
	public final void testAdicionaAluguel() throws Exception {
		ControladorCliente.getInstance().adicionaCliente("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", TipoImovel.APARTAMENTO);

		ControladorImovel.getInstance().addImovel(
				"Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 800.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
		
		ControladorImovel.getInstance().addImovel(
				"Terreno para alugar no Altiplano!",
						"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB",
						2500, new Area(15, 7), TipoImovel.TERRENO,
						TipoContratual.ALUGUEL);
		
		try {
			cAlugueis.adicionaAluguel("101.202.303-44", "0");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Imovel invalido", e.getMessage());
		}
		
		ControladorPedidos.getInstance().adicionaPedido("0", "101.202.303-44");
		
		try {
			cAlugueis.adicionaAluguel("101.000.000-44", "0");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Cliente invalido", e.getMessage());
		}
		
		Assert.assertTrue(cAlugueis.adicionaAluguel("101.202.303-44", "0"));
		
		// Imovel ja pedido
		try {
			cAlugueis.adicionaAluguel("101.202.303-44", "0");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Imovel invalido", e.getMessage());
		}
		
		ControladorPedidos.getInstance().adicionaPedido("1", "101.202.303-44");
		
		Assert.assertTrue(cAlugueis.adicionaAluguel("101.202.303-44", "1"));
		
	}

	@Test
	public final void testRemoveAluguel() {
		// Tentando remover Aluguel inexistente
		
		Assert.assertFalse(cAlugueis.removeAluguel("2"));
		
		try {
			cAlugueis.removeAluguel("OITUDOBOM?");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Registro invalido", e.getMessage());
		}
		
		Assert.assertTrue(cAlugueis.removeAluguel("0"));
		
	}

	@Test
	public final void testGetAluguel() throws Exception {
		ControladorPedidos.getInstance().adicionaPedido("0", "101.202.303-44");
		cAlugueis.adicionaAluguel("101.202.303-44", "0");
		
		Aluguel aluguel1 = new Aluguel(
				ControladorCliente.getInstance().getCliente("101.202.303-44"),
				ControladorImovel.getInstance().getImovel("0"));
		
		Aluguel aluguel2 = new Aluguel(
				ControladorCliente.getInstance().getCliente("101.202.303-44"),
				ControladorImovel.getInstance().getImovel("1"));
		
		
		Assert.assertEquals(aluguel1, cAlugueis.getAluguel("0"));
		Assert.assertNotSame(aluguel1, cAlugueis.getAluguel("1"));
		Assert.assertEquals(aluguel2, cAlugueis.getAluguel("1"));
		Assert.assertNotSame(aluguel2, cAlugueis.getAluguel("0"));
		
		Assert.assertNull(cAlugueis.getAluguel("9999"));
		
		try {
			cAlugueis.getAluguel("Registro Invalido");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Registro invalido", e.getMessage());
		}
	}

	@Test
	public final void testGetValorTotalDeAlugueis() {
		
		Assert.assertEquals(800+2500 , cAlugueis.getValorTotalDeAlugueis(), 0.005);
		
		cAlugueis.removeAluguel("1");
		
		Assert.assertEquals(800 , cAlugueis.getValorTotalDeAlugueis(), 0.005);
		
		cAlugueis.removeAluguel("0");
		
		Assert.assertEquals(0 , cAlugueis.getValorTotalDeAlugueis(), 0.005);
		
	}

}
