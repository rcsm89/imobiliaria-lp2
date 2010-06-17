package imobiliaria.testes;

import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Imovel;

import org.junit.Before;
import org.junit.Test;

public class AluguelTest {

	private Aluguel aluguel1;
	private Aluguel aluguel2;

	private Cliente cliente1;
	private Cliente cliente2;

	private Imovel imovel1;
	private Imovel imovel2;

	@Before
	public void setUp() throws Exception {

		cliente1 = new Cliente("11022033040", new GregorianCalendar(1991, 06,
				23), "Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO);

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		aluguel1 = new Aluguel(cliente1, imovel1);

		cliente2 = new Cliente("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira",
				TipoImovel.APARTAMENTO);

		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);

		aluguel2 = new Aluguel(cliente2, imovel2);

	}

	@Test
	public final void testaConstrutor() {

		try {
			aluguel1 = new Aluguel(null, imovel2);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel ou Cliente invalidos", e.getMessage());
		}

		try {
			aluguel1 = new Aluguel(cliente1, null);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Imovel ou Cliente invalidos", e.getMessage());
		}

	}

	@Test
	public final void testExibeInformacao() {
		
		Assert.assertEquals("Informacoes do Aluguel de Jean\n" +
				"Imovel: (2) Casa imobiliada para Alugar - Valor: 3500.0\n" +
				"Cliente: Jean (110.220.330-40)",
						aluguel1.exibeInformacao());
		
		

		Assert.assertEquals("Informacoes do Aluguel de Thiago Ferreira\n" +
				"Imovel: (3) Apartamento a Venda!!! - Valor: 25000.0\n" +
				"Cliente: Thiago Ferreira (101.202.303-44)",
						aluguel2.exibeInformacao());
		
	}

	@Test
	public final void testToString() {

		Assert.assertEquals(
						"Alugante: Jean (110.220.330-40) Imovel: Casa imobiliada para Alugar (4)",
						aluguel1.toString());

		Assert.assertEquals(
						"Alugante: Thiago Ferreira (101.202.303-44) Imovel: Apartamento a Venda!!! (5)",
						aluguel2.toString());
	}

}
