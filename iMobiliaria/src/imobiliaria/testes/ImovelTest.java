package imobiliaria.testes;

import imobiliaria.auxiliar.EstadoImovel;
import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Imovel;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ImovelTest {

	private Imovel imovel1;
	private Imovel imovel2;
	private Imovel imovel3;

	@Before
	public void criaImoveis() throws Exception {

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos, num 2831, JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);

		imovel3 = new Imovel("Terreno a venda no Altiplano!",
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB",
				50000, new Area(15, 7), TipoImovel.TERRENO,
				TipoContratual.VENDA);
	}

	@Test
	public void testaExcecoes() {
		try {
			imovel1 = new Imovel("     ",
					"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500,
					new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome invalido\n", e.getMessage());
		}

		try {
			imovel1 = new Imovel("     ", "                                  ",
					3500, new Area(4, 6), TipoImovel.CASA,
					TipoContratual.ALUGUEL);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome invalido\nEndereco invalido\n", e
					.getMessage());
		}

		try {
			imovel1 = new Imovel("     ",
					"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", -8000,
					new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome invalido\nValor invalido\n", e
					.getMessage());
		}

		try {
			imovel1 = new Imovel("Nome do Imovel",
					"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 0,
					new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);			
		} catch (Exception e) {
			Assert.fail("Nao Deveria Cair Nesse catch");
			Assert.assertEquals("Valor invalido\n", e.getMessage());
		}
	}


	@Test
	public void testaImoveis() throws Exception {
		
		/* Teste de toString + Metodos Acessadores */
		
		Assert.assertEquals(
						"4|Casa imobiliada para Alugar|Rua Joaquim Caroca, Bodocongo," +
						" Num 2471, CG/PB|3500.0|4.0x6.0 (PEQUENA)|CASA|ALUGUEL|A_VENDA",
						imovel1.toString());
		
		Assert.assertEquals(
				"5|Apartamento a Venda!!!|Rua Fernando Luiz Henrique dos Santos, num 2831," +
				" JP/PB, Ed. Java, apto 1300|25000.0|4.0x6.0 (PEQUENA)|APARTAMENTO|VENDA|A_VENDA",
				imovel2.toString());
		
		Assert.assertEquals(
				"6|Terreno a venda no Altiplano!|Rua Algumacoisa de algo, no Altiplano," +
				" Num 9999, JP/PB|50000.0|15.0x7.0 (GRANDE)|TERRENO|VENDA|A_VENDA",
				imovel3.toString());
		
		
		Assert.assertEquals(4, imovel1.getRegistroImovel());
		Assert.assertEquals("Casa imobiliada para Alugar", imovel1.getNome());
		Assert.assertEquals("Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", imovel1.getEndereco());
		Assert.assertEquals(new Area(4,6), imovel1.getArea());
		Assert.assertEquals(TipoImovel.CASA, imovel1.getTipoDoImovel());
		Assert.assertEquals(TipoContratual.ALUGUEL, imovel1.getTipoContratual());
		Assert.assertEquals(3500.0, imovel1.getValor());
		Assert.assertEquals(EstadoImovel.A_VENDA ,imovel1.getEstadoDoImovel());
		
		imovel1.setArea(new Area(10,50));
		Assert.assertEquals(new Area(10,50), imovel1.getArea());
		
		imovel1.setNome("Novo nome pro Imovel!!");
		Assert.assertEquals("Novo nome pro Imovel!!", imovel1.getNome());
		
		imovel1.setEndereco("Novo Endereco!");
		Assert.assertEquals("Novo Endereco!", imovel1.getEndereco());
		
		imovel1.vendido();
		Assert.assertEquals(EstadoImovel.VENDIDO, imovel1.getEstadoDoImovel());
		
		imovel1.setTipoContratual(TipoContratual.VENDA);
		Assert.assertEquals(TipoContratual.VENDA, imovel1.getTipoContratual());
		
		imovel1.setTipoDoImovel(TipoImovel.TERRENO);
		Assert.assertEquals(TipoImovel.TERRENO, imovel1.getTipoDoImovel());
		
		imovel1.setValor(2);
		Assert.assertEquals(2.0, imovel1.getValor());
		
		/* Excecoes nos Metodos Modificadores */
		
		try {
			imovel1.setEndereco("        ");
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Endereco invalido", e.getMessage());
		}
		
		try {
			imovel1.setNome(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome invalido", e.getMessage());
		}
		
		try {
			imovel1.setValor(0);
		} catch (Exception e) {
			Assert.fail("Nao Deveria Cair Nesse catch");
			Assert.assertEquals("Valor invalido", e.getMessage());
		}
	}
}
