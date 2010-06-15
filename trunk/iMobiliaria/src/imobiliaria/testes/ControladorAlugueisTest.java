package imobiliaria.testes;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorAlugueis;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorPedidos;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Cliente;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
		
		try {
			cAlugueis.adicionaAluguel("101.202.303-44", "0");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Imovel invalido", e.getMessage());
		}
		
		ControladorPedidos.getInstance().adicionaPedido("0", "101.202.303-44");
		ControladorPedidos.getInstance().efetuaPedido("0", "1234");
		
	}

	@Test
	public final void testRemoveAluguel() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAluguel() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetValorTotalDeAlugueis() {
		fail("Not yet implemented"); // TODO
	}

}
