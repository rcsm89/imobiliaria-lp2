package imobiliaria.testes;

import imobiliara.aux.TipoContratual;
import imobiliara.aux.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Imovel;
import imobiliaria.processamento.ColecaoImoveis;

import java.util.ArrayList;

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
		
		try{
			colecaoImovel.addImovel(imovel1);
			Assert.assertEquals(1, colecaoImovel.getImoveis().size());
		}catch(Exception e){
			Assert.fail("Nao Deveria Lancar Excecao aqui");
		}

		// tentando adicionar imovel ja existente na colecao.
		try{
			colecaoImovel.addImovel(imovel1);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(Exception e){
			Assert.assertEquals("Imovel Existente", e.getMessage());
		}
		
		Assert.assertEquals(1, colecaoImovel.getImoveis().size());
		
		try{
			colecaoImovel.addImovel(imovel2);
			colecaoImovel.addImovel(imovel3);
		}catch(Exception e){
			Assert.fail("Nao Deveria Lancar Excecao aqui");
		}
		
		Assert.assertEquals(3, colecaoImovel.getImoveis().size());
		
		try{
			colecaoImovel.addImovel(imovel2);
			Assert.fail("Nao Deveria Lancar Excecao aqui");
		}catch(Exception e){
			Assert.assertEquals("Imovel Existente", e.getMessage());
		}
		
		Assert.assertEquals(3, colecaoImovel.getImoveis().size());
		
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
				"Terreno"));

		colecaoComparavel.clear();

		colecaoComparavel.add(imovel1);
		colecaoComparavel.add(imovel2);
		colecaoComparavel.add(imovel3);
		Assert.assertEquals(colecaoComparavel, colecaoImovel.getImoveis());
		
		// Testa o getImovelDeRegistro

		Assert.assertEquals(imovel1, colecaoImovel.getImovelDeRegistro(0));

		// Equals

		try{
			colecaoImovelParaCompararEquals.addImovel(imovel1);
			Assert.assertEquals(1, colecaoImovelParaCompararEquals.getImoveis().
					size());
			
			colecaoImovelParaCompararEquals.addImovel(imovel2);
			Assert.assertEquals(2, colecaoImovelParaCompararEquals.getImoveis().
					size());
	
			colecaoImovelParaCompararEquals.addImovel(imovel3);
			Assert.assertEquals(3, colecaoImovelParaCompararEquals.getImoveis().
					size());
		}catch(Exception e){
			Assert.fail("Nao Deveria Lancar Excecao aqui");
		}
		
		try{
			colecaoImovelParaCompararEquals.addImovel(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(Exception e){
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}

		Assert.assertEquals(true, colecaoImovel
				.equals(colecaoImovelParaCompararEquals));

		Assert.assertEquals(true, colecaoImovelParaCompararEquals
				.equals(colecaoImovel));
		
		// Tamanho atual da colecao e 3.
		
		colecaoImovel.removeImovel("2");
		
		Assert.assertEquals(2, colecaoImovel.getImoveis().size());
		
		//Tentando remover imovel com registro impossivel
		
		try{
			colecaoImovel.removeImovel("a");
		}catch(Exception e){
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}
		
		//Imovel com registro nao pertencente a colecao
		Assert.assertEquals(false, colecaoImovel.removeImovel("3"));
		
		try{
			colecaoImovel.removeImovel(null);
		}catch(Exception e){
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}
		
		Assert.assertEquals(false, colecaoImovel
				.equals(colecaoImovelParaCompararEquals));

		Assert.assertEquals(false, colecaoImovelParaCompararEquals
				.equals(colecaoImovel));
		
	}
}
