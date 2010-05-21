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
	
	@Before
	public void setUp() throws Exception {
		func1 = new Funcionario("10120230344",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
				"Thiago Ferreira", "00111"); 
		
		func2 = new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
				"Yuri Farias", "1234325"); 
		
		func3 = new Funcionario("12345678910",new GregorianCalendar(1991, 14, 13),"Rua 12 de Outubro",
				"Yuri Farias", "1234325"); 
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
			Assert.assertEquals("Valor da venda invalido\n", e.getMessage());
		}
		func1.adicionaVenda(1000.0);
		Assert.assertEquals(1000.0, func1.getTotalDeVendas(), 0.005);
		func1.adicionaVenda(1500.0);
		func1.adicionaVenda(1500.0);
		Assert.assertEquals(4000.0, func1.getTotalDeVendas(), 0.005);
	}
	
	@Test
	public void testaEquals() {
		Assert.assertFalse(func1.equals(func2));
		Assert.assertTrue(func2.equals(func3));
	}
}
