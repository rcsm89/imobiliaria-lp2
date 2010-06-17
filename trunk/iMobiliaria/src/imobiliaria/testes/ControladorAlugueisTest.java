package imobiliaria.testes;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorAlugueis;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorPedidos;
import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Area;
import imobiliaria.exceptions.ClienteNotFoundException;
import imobiliaria.exceptions.ImovelInvalidoException;

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
			Assert.fail("Nao Deveria Chegar aqui");
		} catch (ImovelInvalidoException e) {
			Assert.assertEquals("Imovel invalido", e.getMessage());
		}
		
		ControladorPedidos.getInstance().adicionaPedido("0", "101.202.303-44");
		
		try {
			cAlugueis.adicionaAluguel("101.000.000-44", "0");
			Assert.fail("Nao Deveria Chegar aqui");
		} catch (ClienteNotFoundException e) {
			Assert.assertEquals("Cliente invalido", e.getMessage());
		}
		
		Assert.assertTrue(cAlugueis.adicionaAluguel("101.202.303-44", "0"));
		
		// Imovel ja pedido
		try {
			cAlugueis.adicionaAluguel("101.202.303-44", "0");
			Assert.fail("Nao Deveria Chegar aqui");
		} catch (ImovelInvalidoException e) {
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
			Assert.fail("Nao Deveria Chegar aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Registro invalido", e.getMessage());
		}
		
		Assert.assertTrue(cAlugueis.removeAluguel("0"));
		
	}
	
	@Test
	public final void testListaAlugueis() throws Exception {
		
		Assert.assertEquals("Aluguel de Thiago Ferreira (101.202.303-44)\n" +
				"Imovel Alugado: (1) Terreno para alugar no Altiplano! - Valor: 2500.0\n\n"
				, cAlugueis.listaAlugueisDeCliente("101.202.303-44"));
		
		// Adiciona novo pedido para outro cliente
		
		ControladorCliente.getInstance().adicionaCliente(
				"11022033040", new GregorianCalendar(1991, 06,
						23), "Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO);
		
		ControladorPedidos.getInstance().adicionaPedido("0", "110.220.330-40");
		cAlugueis.adicionaAluguel("110.220.330-40", "0");
		
		Assert.assertEquals("Aluguel de Jean (110.220.330-40)\n" +
				"Imovel Alugado: (0) Casa imobiliada para Alugar - Valor: 800.0\n\n",
				cAlugueis.listaAlugueisDeCliente("110.220.330-40"));
		
		Assert.assertEquals("Aluguel de Thiago Ferreira (101.202.303-44)\n" +
				"Imovel Alugado: (1) Terreno para alugar no Altiplano! - Valor: 2500.0\n\n" +
				"Aluguel de Jean (110.220.330-40)\n" +
				"Imovel Alugado: (0) Casa imobiliada para Alugar - Valor: 800.0\n\n"
				, cAlugueis.listaAlugueis());
		
	}

	@Test
	public final void testGetAluguel() throws Exception {
		
		Aluguel aluguel1 = new Aluguel(
				ControladorCliente.getInstance().getCliente("110.220.330-40"),
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
