package imobiliaria.testes;

import static org.junit.Assert.fail;


import imobiliaria.processamento.Area;
import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.EstadoImovel;
import imobiliaria.processamento.Funcionario;
import imobiliaria.processamento.Imovel;
import imobiliaria.processamento.Sistema;
import imobiliaria.processamento.TipoContratual;
import imobiliaria.processamento.TipoImovel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	Sistema sistema;
	Imovel imovel1;
	Imovel imovel2;
	Cliente cliente1;	
	Funcionario func1;


	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831, JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);
		
		cliente1 = new Cliente("12345678910", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Alberto de Brito, 84", "Bruno Paiva",
				TipoImovel.CASA);
		
		func1 = new Funcionario("12345678910", new GregorianCalendar(1991, 2,
				17), "Rua 12 de Outubro", "Yuri Farias", "12345");

		
		
		
	}

	@Test
	public void testEfetuaPagamentoNoMes() throws Exception {
		Assert.assertFalse(sistema.pagouNesseMes());
		sistema.efetuaPagamentoNoMes();
		Assert.assertTrue(sistema.pagouNesseMes());

		try {
			sistema.efetuaPagamentoNoMes();
		} catch (Exception e) {
			Assert.assertEquals("Pagamento ja efetuado esse mes", e
					.getMessage());
		}
	}

	@Test
	public void testAdicionaPedido() throws Exception {
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);
		
		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.adicionaPedido(registroImovel, "123.456.789-10");
		Assert.assertEquals(EstadoImovel.PEDIDO, imovel1.getEstadoDoImovel());

	}
	
	
	@Test
	public void testEfetuaPedido() throws Exception {
		// Adicionando um pedido
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);
		sistema.controladorFuncionarios().adicionaFuncionario(func1);
		
		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.adicionaPedido(registroImovel, "123.456.789-10");
		
		// Verificando o valor do caixa do sistema
		Assert.assertEquals(0.0, sistema.caixa(), 0.005);
		
		// Efetuando um pedido
		sistema.efetuaPedido(registroImovel, func1.getCreci());
		
		// Verificando o valor do caixa do sistema
		Assert.assertEquals(imovel1.getValor(), sistema.caixa(), 0.005);
		
		// Verificando o estado do imovel
		Assert.assertEquals(EstadoImovel.VENDIDO, imovel1.getEstadoDoImovel());
		
		// Testando imovel nao pedido
		try{
			sistema.efetuaPedido(registroImovel, func1.getCreci());
		}catch (Exception e){
			Assert.assertEquals("Imovel nao pedido", e.getMessage());
		}
	}
	

	
	@Test
	public void testRemovePedido() throws Exception {
		// Adicionando um pedido
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);
		sistema.controladorFuncionarios().adicionaFuncionario(func1);
		
		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.adicionaPedido(registroImovel, "123.456.789-10");
		 
		// Removendo um pedido
		registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.removePedido(registroImovel);
		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		
			}
	

	@Test
	public void testListagemDePedido() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

}
