//FALTANDO TESTAR O HISTORICO DE COMPRAS!
package imobiliaria.testes;

import imobiliaria.processamento.Area;
import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.ColecaoImoveis;
import imobiliaria.processamento.Funcionario;
import imobiliaria.processamento.Imovel;
import imobiliaria.processamento.TipoContratual;
import imobiliaria.processamento.TipoImovel;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;

public class ClienteTest {
	
	private Cliente cliente1;
	private Cliente cliente2;
	private Cliente cliente3;
	private Imovel imovel1;
	private Imovel imovel2;
	private Funcionario func;
	
	@Before
	public void criaClientes() throws Exception {
		cliente1 = new Cliente("10020030040",
				new GregorianCalendar(1991, 04, 03),
				"Rua Antonio Joaquim Pequeno",
				"Bruno Fabio", TipoImovel.CASA); // Casa
		
		cliente2 = new Cliente("10120230344",
				new GregorianCalendar(1991, 04, 03),
				"Rua 12 de Outubro",
				"Thiago Ferreira", TipoImovel.APARTAMENTO); // Apartamento
		
		cliente3 = new Cliente("11022033040",
				new GregorianCalendar(1991, 04, 03),
				"Rua Antonio Joaquim Pequeno",
				"Jean", TipoImovel.TERRENO); // Terreno
		
		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
		
		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB," +
				" Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);
		
		func = new Funcionario("40030020010",
				new GregorianCalendar(1991, 04, 03),
				"Rua 1º de Maio",
				"Bigode", "20921"); 
	}
	
	public void testaGetHistoricoCompras() throws Exception {
		
		ColecaoImoveis compras = new ColecaoImoveis();
		
		Assert.assertEquals(compras, cliente1.getHistoricoCompras());
		/*So pode testar depois que o funcionario
		 * puder efetuar a compra dos pedidos do cliente. 
		 */
	}
	
	public void testaFazPedidos() throws Exception {
		
		ColecaoImoveis pedidos = new ColecaoImoveis();
		
		Assert.assertEquals(pedidos, cliente1.getPedidos());
		
		cliente1.fazPedidos(imovel1);
		pedidos.adicionaImovel(imovel1);
		Assert.assertEquals(pedidos, cliente1.getPedidos());
		
		cliente1.fazPedidos(imovel2);
		pedidos.adicionaImovel(imovel2);
		Assert.assertEquals(pedidos, cliente1.getPedidos());
	}
	
	public void testaGetPedidos() {
		
		Assert.assertTrue(cliente1.getPedidos().equals(new ColecaoImoveis()));
		
	}
	
	public void testaGetPreferencia() {
		
		Assert.assertEquals(TipoImovel.CASA,
				cliente1.getPreferencia());
		Assert.assertEquals(TipoImovel.APARTAMENTO,
				cliente2.getPreferencia());
		Assert.assertEquals(TipoImovel.TERRENO,
				cliente3.getPreferencia());

	}
	
	public void testaSetPreferencia() {
		
		cliente1.setPreferencia(TipoImovel.APARTAMENTO);
		
		Assert.assertEquals(TipoImovel.APARTAMENTO,
				cliente1.getPreferencia());
		
		cliente1.setPreferencia(TipoImovel.TERRENO);
		
		Assert.assertEquals(TipoImovel.TERRENO,
				cliente1.getPreferencia());
		
		cliente1.setPreferencia(TipoImovel.CASA);
		
		Assert.assertEquals(TipoImovel.CASA,
				cliente1.getPreferencia());
		
	}
	
	public void testaEquals() {
		
		Assert.assertTrue(cliente1.equals(cliente1));
		Assert.assertFalse(cliente1.equals(cliente2));
		Assert.assertTrue(cliente2.equals(cliente2));
		Assert.assertFalse(cliente2.equals(cliente3));
		Assert.assertTrue(cliente3.equals(cliente3));
		Assert.assertFalse(cliente3.equals(cliente1));
		
	}

}
