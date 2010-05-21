package imobiliaria.testes;

import imobiliaria.processamento.*;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class FuncionarioTest {
	
	Funcionario func1;
	Funcionario func2;
	Funcionario func3;
	Funcionario func4;
	Cliente cliente1;
	Cliente cliente2;
	Cliente cliente3;
	Imovel imovel1;
	Imovel imovel2;
	
	@Before
	public void setUp() throws Exception {
		func1 = new Funcionario("10120230344",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
				"Thiago Ferreira", "00111"); 
		
		func2 = new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
				"Yuri Farias", "1234325"); 
		
		func3 = new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
				"Yuri Farias", "1234325"); 
		
; 
		
		cliente1 = new Cliente("10120230344",new GregorianCalendar(1991, 14, 13),"Rua Rodrigues Alves",
				"Thiago Ferreira", TipoImovel.APARTAMENTO);
		
		cliente2 = new Cliente("11022033040",new GregorianCalendar(1991, 06, 23),"Rua Antonio Joaquim",
				"Jean", TipoImovel.TERRENO);

		cliente3 = new Cliente("11022033040",new GregorianCalendar(1991, 06, 23),"Rua Antonio Joaquim",
				"Jean", TipoImovel.TERRENO);
		
		imovel1 = new Imovel("Mariana Macedo",
				"Rua Rodrigues Alves, Num 1125, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
		
		imovel2 = new Imovel("Residencial Flamingo",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB," +
				" Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);
	}
	
	@Test
	public void testaConstrutor() throws Exception{
		try {
			new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
					"Yuri Farias", "   "); 
		} catch (Exception e) {
			Assert.assertEquals("Creci invalido\n", e.getMessage());
		}
		try {
			new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
					"Yuri Farias", "as111sT234"); 
		} catch (Exception e) {
			Assert.assertEquals("Creci invalido\n", e.getMessage());
		}
		try {
			new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
					"Yuri Farias", null); 
		} catch (Exception e) {
			Assert.assertEquals("Creci invalido\n", e.getMessage());
		}
	}
	
	@Test
	public void testaAdicionaVenda() throws Exception{
		
		try{
			func1.adicionaVenda(-1);
		} catch (Exception e) {
			Assert.assertEquals("Valor invalido\n", e.getMessage());
		}
		func1.adicionaVenda(1000.0);
		Assert.assertEquals(1.000, func1.getTotalDeVendas(), 0.005);
		func1.adicionaVenda(1500.0);
		func1.adicionaVenda(1500.0);
		Assert.assertEquals(4.000, func1.getTotalDeVendas(), 0.005);
	}
	
	@Test
	public void testaAdicionaCliente() {
		func1.adicionaCliente(cliente1);
		func1.adicionaCliente(cliente2);
		Assert.assertEquals(2, func1.getClientes().numeroTotalDeClientes());
		func1.adicionaCliente(cliente2);
		func1.adicionaCliente(cliente3);
		Assert.assertEquals(2, func1.getClientes().numeroTotalDeClientes());
	}
	
	@Test
	public void testaRemoveCliente() throws Exception {
		try{
			func1.adicionaCliente(cliente1);
			func1.removeClientePorCpf("");
		} catch (Exception e) {
			Assert.assertEquals("Cpf invalido\n", e.getMessage());
		}
		try{
			func1.adicionaCliente(cliente1);
			func1.removeClientePorCpf("121aaaa");
		} catch (Exception e) {
			Assert.assertEquals("Cpf invalido\n", e.getMessage());
		}
		try{
			func1.adicionaCliente(cliente1);
			func1.removeClientePorNome("");
		} catch (Exception e) {
			Assert.assertEquals("Cpf invalido\n", e.getMessage());
		}
		try{
			func1.adicionaCliente(cliente1);
			func1.removeClientePorNome("121aaaa");
		} catch (Exception e) {
			Assert.assertEquals("Cpf invalido\n", e.getMessage());
		}
		
		func1.adicionaCliente(cliente1);
		func1.adicionaCliente(cliente2);
		Assert.assertEquals(2, func1.getClientes().numeroTotalDeClientes());
		func1.removeClientePorCpf(cliente1.getCpf());
		Assert.assertEquals(1, func1.getClientes().numeroTotalDeClientes());
		func1.removeClientePorNome(cliente1.getNome());
		Assert.assertEquals(0, func1.getClientes().numeroTotalDeClientes());
	}
	
	
	@Test 
	public void testaAdicionaImovel() throws Exception {
		func1.addImovel(imovel1);
		func1.addImovel(imovel2);
		Assert.assertEquals(2, func1.getClientes().numeroTotalDeClientes());
		func1.addImovel(imovel2);
		Assert.assertEquals(2, func1.getClientes().numeroTotalDeClientes());
	}
	
	@Test
	public void testaRemoveImovel() {
		func1.addImovel(imovel1);
		func1.addImovel(imovel2);
		Assert.assertEquals(2, func1.getClientes().numeroTotalDeClientes());
		func1.removeImovel(imovel1);
		Assert.assertEquals(1, func1.getClientes().numeroTotalDeClientes());
	}
	
	@Test
	public void testaEfetuaCompra() throws Exception {
		try{
			func1.efetuaCompra(cliente1, -1);
		} catch (Exception e) {
			Assert.assertEquals("Registro invalido\n", e.getMessage());
		}
		
		func1.addImovel(imovel1);
		func1.addImovel(imovel2);
		cliente1.fazPedido(imovel1);
		func1.efetuaCompra(cliente1, imovel1.getRegistroImovel());
		Assert.assertEquals(EstadoImovel.VENDIDO, imovel1.getEstadoDoImovel());
		Assert.assertEquals(3500.0, imovel1.getValor(),0.005);
	}
	
	@Test
	public void testaEquals() {
		Assert.assertFalse(func1.equals(func2));
		Assert.assertTrue(func2.equals(func3));
	}
}
