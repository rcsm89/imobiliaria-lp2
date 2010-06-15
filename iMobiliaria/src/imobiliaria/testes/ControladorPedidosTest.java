package imobiliaria.testes;

import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliara.auxiliar.EstadoImovel;
import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorFuncionario;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorPedidos;
import imobiliaria.controladores.ControladorTransacoes;
import imobiliaria.entidades.Area;

import org.junit.Before;
import org.junit.Test;

public class ControladorPedidosTest {

	private ControladorPedidos cPedidos = ControladorPedidos.getInstance();

	@Test
	public final void testAdicionaPedido() throws Exception {

		ControladorCliente.getInstance().adicionaCliente("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", TipoImovel.APARTAMENTO);

		ControladorImovel.getInstance().addImovel(
				"Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 800.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		ControladorImovel.getInstance().addImovel(
				"Terreno para alugar no Altiplano!",
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB", 2500,
				new Area(15, 7), TipoImovel.TERRENO, TipoContratual.VENDA);

		cPedidos.adicionaPedido("0", "101.202.303-44");

		try {
			cPedidos.adicionaPedido("0", "101.202.303-44");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel ja pedido", e.getMessage());
		}

		cPedidos.adicionaPedido("1", "101.202.303-44");

		try {
			cPedidos.adicionaPedido("1", "101.202.303-44");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel ja pedido", e.getMessage());
		}
	}

	@Test
	public final void testRemovePedidoEListagemDePedido() throws Exception {

		try {
			cPedidos.removePedido("3");
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Parametros invalidos", e.getMessage());
		}

		Assert
				.assertEquals(
						"Imovel: (0) Casa imobiliada para Alugar - Valor: 800.0\n"
								+ "Cliente: Thiago Ferreira (101.202.303-44)\n\n"
								+ "Imovel: (1) Terreno para alugar no Altiplano! - Valor: 2500.0\n"
								+ "Cliente: Thiago Ferreira (101.202.303-44)\n\n",
						cPedidos.listagemDePedido());

		cPedidos.removePedido("0");

		Assert.assertEquals(
				"Imovel: (1) Terreno para alugar no Altiplano! - Valor: 2500.0\n"
						+ "Cliente: Thiago Ferreira (101.202.303-44)\n\n",
				cPedidos.listagemDePedido());

		cPedidos.adicionaPedido("0", "101.202.303-44");
	}

	@Test
	public final void testListaPedidosDeCliente() throws Exception {

		Assert
				.assertEquals(
						"Imovel: (1) Terreno para alugar no Altiplano! - Valor: 2500.0\n"
								+ "Cliente: Thiago Ferreira (101.202.303-44)\n\n"
								+ "Imovel: (0) Casa imobiliada para Alugar - Valor: 800.0\n"
								+ "Cliente: Thiago Ferreira (101.202.303-44)\n\n",
						cPedidos.listaPedidosDeCliente("101.202.303-44"));

		Assert.assertEquals("", cPedidos
				.listaPedidosDeCliente("123.456.789-44"));

		// Adicionando outro cliente

		ControladorCliente.getInstance().adicionaCliente("11022033040",
				new GregorianCalendar(1991, 06, 23),
				"Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO);
		
		cPedidos.removePedido("1");
		cPedidos.adicionaPedido("1", "110.220.330-40");
		
		Assert.assertEquals("Imovel: (1) Terreno para alugar no Altiplano! - Valor: 2500.0\n"
								+ "Cliente: Jean (110.220.330-40)\n\n",
								cPedidos.listaPedidosDeCliente("110.220.330-40"));
		
		Assert.assertEquals("Imovel: (0) Casa imobiliada para Alugar - Valor: 800.0\n"
								+ "Cliente: Thiago Ferreira (101.202.303-44)\n\n",
								cPedidos.listaPedidosDeCliente("101.202.303-44"));
		
	}

	@Test
	public final void testEfetuaPedido() throws Exception {

		ControladorFuncionario.getInstance().adicionaFuncionario("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", "00111");

		/*
		 * Vamos tentar efetuar os 2 pedidos adicionados anteriormente e
		 * verificar o estado deles no Controlador de Imoveis
		 */

		cPedidos.efetuaPedido("0");
		Assert.assertEquals(ControladorImovel.getInstance().getImovel("0")
				.getEstadoDoImovel(), EstadoImovel.ALUGADO);

		try {
			cPedidos.efetuaPedido("1", "99");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Funcionario invalido", e.getMessage());
		}

		cPedidos.efetuaPedido("1", "00111");

		Assert.assertEquals(ControladorImovel.getInstance().getImovel("1")
				.getEstadoDoImovel(), EstadoImovel.VENDIDO);

		// Valor dos 2 foram adicionados ao caixa
		Assert.assertEquals(3300, ControladorTransacoes.getInstance().caixa(),
				0.005);
	}
}
