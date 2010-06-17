package imobiliaria.testes;

import java.util.GregorianCalendar;

import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.entidades.Transacao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransacaoTest {
	
	private Transacao transacao1;
	private Transacao transacao2;
	private Transacao transacao3;
	private Transacao transacao4;

	@Before
	public void setUp() throws Exception {
		
		Cliente cliente1 = new Cliente("11022033040", new GregorianCalendar(1991, 06,
				23), "Rua Antonio Joaquim Pequeno", "Jean", TipoImovel.TERRENO);
		
		Cliente cliente2 = new Cliente("12345678910", new GregorianCalendar(
				1991, 2, 1), "endere3co", "Brunaaaaaa", TipoImovel.TERRENO);
		
		Cliente cliente3 = new Cliente("11022576125", new GregorianCalendar(1988, 8,
				29), "Rua Severino de Brito", "Fulano de Tal", TipoImovel.CASA);
		
		Funcionario func1 = new Funcionario("10120230344", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Thiago Ferreira", "00111");

		Funcionario	func2 = new Funcionario("12345678910", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Yuri Farias", "1234325");

		Funcionario	func3 = new Funcionario("12345678910", new GregorianCalendar(1991, 14,
				13), "Rua 12 de Outubro", "Yuri Farias", "1234325");
		
		Imovel imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		Imovel imovel2 = new Imovel("Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB,"
						+ " Ed. Java, apto 1300", 25000, new Area(4, 6),
				TipoImovel.APARTAMENTO, TipoContratual.VENDA);
		
		transacao1 = new Transacao(func1, cliente1, imovel1);
		transacao2 = new Transacao(func1, cliente2, imovel2);
		transacao3 = new Transacao(func2, cliente3, imovel1);
		transacao4 = new Transacao(func3, cliente2, imovel2);
		
		
		// Reseta o Criador de Registro
		Transacao.setCriadorRegistroTransacao(0);
		
	}

	@Test
	public final void testExibeInformacao() {
		
		Assert.assertEquals("Transacao de Registro: 0\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Jean - CPF: 110.220.330-40\n" +
				"Imovel: 0 - Casa imobiliada para Alugar\n" +
				"Valor da Transacao: 3500.0 - Data: 10/06/2010"
				,transacao1.exibeInformacao());
		
		Assert.assertEquals("Transacao de Registro: 1\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 1 - Apartamento a Venda!!!\n" +
				"Valor da Transacao: 25000.0 - Data: 10/06/2010"
				, transacao2.exibeInformacao());
		
		Assert.assertEquals("Transacao de Registro: 2\n" +
				"Vendedor: Yuri Farias - CRECI: 1234325\n" +
				"Comprador: Fulano De Tal - CPF: 110.225.761-25\n" +
				"Imovel: 0 - Casa imobiliada para Alugar\n" +
				"Valor da Transacao: 3500.0 - Data: 10/06/2010"
				,transacao3.exibeInformacao());
		
		Assert.assertEquals("Transacao de Registro: 3\n" +
				"Vendedor: Yuri Farias - CRECI: 1234325\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 1 - Apartamento a Venda!!!\n" +
				"Valor da Transacao: 25000.0 - Data: 10/06/2010"
				,transacao4.exibeInformacao());
		
	}

	@Test
	public final void testGetRegistroTransacao() {
		
		Assert.assertEquals(0, transacao1.getRegistroTransacao());
		Assert.assertEquals(1, transacao2.getRegistroTransacao());
		Assert.assertEquals(2, transacao3.getRegistroTransacao());
		Assert.assertEquals(3, transacao4.getRegistroTransacao());
		
	}

	@Test
	public final void testToString() {
		Assert.assertEquals("0 - 10/06/2010 - Thiago Ferreira - Jean - Imovel: 4 - 3500.0"
				, transacao1.toString());
		
		Assert.assertEquals("1 - 10/06/2010 - Thiago Ferreira - Brunaaaaaa - Imovel: 5 - 25000.0"
				, transacao2.toString());
		
		Assert.assertEquals("2 - 10/06/2010 - Yuri Farias - Fulano De Tal - Imovel: 4 - 3500.0"
				, transacao3.toString());
		
		Assert.assertEquals("3 - 10/06/2010 - Yuri Farias - Brunaaaaaa - Imovel: 5 - 25000.0"
				, transacao4.toString());
		
	}

	@Test
	public final void testGetESetCriadorRegistroTransacao() {
		
		Assert.assertEquals(0, Transacao.getCriadorRegistroTransacao());
		
		Transacao.setCriadorRegistroTransacao(5);
		
		Assert.assertEquals(5, Transacao.getCriadorRegistroTransacao());
		
		Transacao.setCriadorRegistroTransacao(-5);
		
		Assert.assertEquals(-5, Transacao.getCriadorRegistroTransacao());
	}
}
