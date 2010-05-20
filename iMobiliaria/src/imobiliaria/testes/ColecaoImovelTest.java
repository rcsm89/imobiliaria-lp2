package imobiliaria.testes;

import java.util.ArrayList;
import imobiliaria.processamento.*;
import junit.framework.Assert;
import org.junit.Test;

public class ColecaoImovelTest {

	private ColecaoImoveis colecaoImovel = new ColecaoImoveis();
	private ArrayList<Imovel> colecaoComparavel = new ArrayList<Imovel>();
	private ColecaoImoveis colecaoImovelParaCompararEquals = new ColecaoImoveis();

	@Test
	public void testaColecoes() throws Exception {
		
		// Criacao dos Imoveis

		Imovel imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		Imovel imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831, JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);

		Imovel imovel3 = new Imovel("Terreno a venda no Altiplano!",
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB",
				50000, new Area(15, 7), TipoImovel.TERRENO,
				TipoContratual.VENDA);
		
		// Adiciona Imoveis e testa adicionar imoveis repetidos

		Assert.assertEquals(true, colecaoImovel.adicionaImovel(imovel1));
		Assert.assertEquals(false, colecaoImovel.adicionaImovel(imovel1));
		Assert.assertEquals(true, colecaoImovel.adicionaImovel(imovel2));
		Assert.assertEquals(true, colecaoImovel.adicionaImovel(imovel3));
		Assert.assertEquals(false, colecaoImovel.adicionaImovel(imovel2));

		
		// Teste de Filtros
		
		colecaoComparavel.add(imovel2);
		colecaoComparavel.add(imovel3);
		Assert.assertEquals(colecaoComparavel, colecaoImovel
				.getImoveis(TipoContratual.VENDA));
		

		colecaoComparavel.clear();

		colecaoComparavel.add(imovel1);
		Assert.assertEquals(colecaoComparavel, colecaoImovel
				.getImoveis(TipoContratual.ALUGUEL));

		colecaoComparavel.clear();

		colecaoComparavel.add(imovel1);
		colecaoComparavel.add(imovel2);
		Assert.assertEquals(colecaoComparavel, colecaoImovel.getImoveisDeValor(
				0, 30000));

		colecaoComparavel.clear();

		colecaoComparavel.add(imovel3);
		Assert.assertEquals(colecaoComparavel, colecaoImovel.getImoveis(
				"Terreno", true));

		colecaoComparavel.clear();

		colecaoComparavel.add(imovel2);
		colecaoComparavel.add(imovel3);
		Assert.assertEquals(colecaoComparavel, colecaoImovel.getImoveis(
				"Altiplano", false));

		colecaoComparavel.clear();
		
		colecaoComparavel.add(imovel1);
		colecaoComparavel.add(imovel2);
		colecaoComparavel.add(imovel3);
		Assert.assertEquals(colecaoComparavel, colecaoImovel.getImoveis());
		
		// Testa o getImovelDeRegistro

		Assert.assertEquals(imovel1, colecaoImovel.getImovelDeRegistro(0));
		
		

		// Equals

		Assert.assertEquals(true, colecaoImovelParaCompararEquals
				.adicionaImovel(imovel1));
		Assert.assertEquals(true, colecaoImovelParaCompararEquals
				.adicionaImovel(imovel2));
		Assert.assertEquals(true, colecaoImovelParaCompararEquals
				.adicionaImovel(imovel3));

		Assert.assertEquals(true, colecaoImovel
				.equals(colecaoImovelParaCompararEquals));

		Assert.assertEquals(true, colecaoImovelParaCompararEquals
				.equals(colecaoImovel));
		
		Assert.assertEquals(true, colecaoImovel.removeImovel(2));
		Assert.assertEquals(false, colecaoImovel.removeImovel(2));
		
		Assert.assertEquals(false, colecaoImovel
				.equals(colecaoImovelParaCompararEquals));

		Assert.assertEquals(false, colecaoImovelParaCompararEquals
				.equals(colecaoImovel));
		
	}
}
