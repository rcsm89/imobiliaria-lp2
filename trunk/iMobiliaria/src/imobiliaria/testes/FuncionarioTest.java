package imobiliaria.testes;

import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class FuncionarioTest {

	Funcionario func1;
	Funcionario func2;
	Funcionario func3;
	Imovel imovel1;
	Imovel imovel2;

	@Before
	public void setUp() throws Exception {
		func1 = new Funcionario("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira", "00111");

		func2 = new Funcionario("12345678910", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Yuri Farias", "1234325");

		func3 = new Funcionario("12345678910", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Yuri Farias", "1234325");

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel("Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB,"
						+ " Ed. Java, apto 1300", 25000, new Area(4, 6),
				TipoImovel.APARTAMENTO, TipoContratual.VENDA);

	}

	@Test
	public void testaConstrutor() throws Exception {
		try {
			new Funcionario("12345678910", new GregorianCalendar(1991, 14, 13),
					"Rua 12 de Outubro", "Yuri Farias", "   ");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("Creci invalido\n", e.getMessage());
		}
		try {
			new Funcionario("12345678910", new GregorianCalendar(1991, 14, 13),
					"Rua 12 de Outubro", "Yuri Farias", "as111sT234");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("Creci invalido\n", e.getMessage());
		}
		try {
			new Funcionario("12345678910", new GregorianCalendar(1991, 14, 13),
					"Rua 12 de Outubro", "Yuri Farias", null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("Creci invalido\n", e.getMessage());
		}
		
		try {
			new Funcionario(null, null, "Rua Antonio Joaquim Pequeno", null,"00112");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("Nome invalido\nCPF invalido\nData de nascimento invalida\n",
							e.getMessage());
		}

		try {
			new Funcionario(null, new GregorianCalendar(1991, 06, 23), null,
					"Thiago", "123");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("Endereco invalido\nCPF invalido\n", e
					.getMessage());
		}
	}
	
	@Test
	public void testaGetCreci(){
		Assert.assertEquals("00111", func1.getCreci());
		Assert.assertNotSame("12345", func1.getCreci());
		Assert.assertEquals("1234325", func2.getCreci());
		
	}

	@Test
	public void testaAdicionaVenda() throws Exception {
		func1.addImovelVendidoMes(imovel1);
		func1.addImovelVendidoMes(imovel2);
		Assert.assertEquals(28500.0, func1.getTotalDeVendas(), 0.005);
		
		try{
			func1.addImovelVendidoMes(imovel1);
			Assert.fail("Deveria ter lancado excecao");
		}catch (Exception e) {
			Assert.assertEquals("Imovel ja Existente", e.getMessage());
		}

		try{
			func1.addImovelVendidoMes(null);
			Assert.fail("Deveria ter lancado excecao");
		}catch (Exception e) {
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}

	}
	
	@Test
	public void testaResetaVendidosNoMes() throws Exception {
		func1.addImovelVendidoMes(imovel1);
		func1.addImovelVendidoMes(imovel2);
		
		int numDeImoveisVendidos = func1.getImoveisVendidosMes().getImoveis().size();
		Assert.assertEquals(2, numDeImoveisVendidos);
		
		func1.resetaImoveisVendidosMes();
		
		numDeImoveisVendidos = func1.getImoveisVendidosMes().getImoveis().size();
		Assert.assertEquals(0, numDeImoveisVendidos);
	}

	@Test
	public void testaEquals() {
		Assert.assertFalse(func1.equals(func2));
		Assert.assertTrue(func2.equals(func3));
	}
}
