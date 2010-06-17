package imobiliaria.testes;

import imobiliara.auxiliar.EstadoImovel;
import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Imovel;
import imobiliaria.exceptions.ImovelInvalidoException;
import imobiliaria.exceptions.ValorInvalidoException;
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
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831," +
				" JP/PB, Ed. Java, apto 1300",
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
		}catch(IllegalArgumentException e){
			Assert.fail("Nao Deveria Cair Nesse catch");
		}

		// tentando adicionar imovel ja existente na colecao.
		try{
			colecaoImovel.addImovel(imovel1);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(ImovelInvalidoException e){
			Assert.assertEquals("Imovel ja Existente", e.getMessage());
		}
		
		Assert.assertEquals(1, colecaoImovel.getImoveis().size());
		
		try{
			colecaoImovel.addImovel(imovel2);
			colecaoImovel.addImovel(imovel3);
		}catch(IllegalArgumentException e){
			Assert.fail("Nao Deveria Cair Nesse catch");
		}
		
		Assert.assertEquals(3, colecaoImovel.getImoveis().size());
		
		try{
			colecaoImovel.addImovel(imovel2);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(ImovelInvalidoException e){
			Assert.assertEquals("Imovel ja Existente", e.getMessage());
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
		
		//testando o getImoveisDeValor que recebe dois doubles representando
		//o intervalo de preco.
		Assert.assertEquals(colecaoComparavel, colecaoImovel.getImoveisDeValor(
				0, 30000));
		
		try{
			colecaoImovel.getImoveisDeValor(10, 0);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(ValorInvalidoException e){
			Assert.assertEquals("Intervalo Invalido", e.getMessage());
		}
		
		try{
			colecaoImovel.getImoveisDeValor(10, 10);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(ValorInvalidoException e){
			Assert.assertEquals("Intervalo Invalido", e.getMessage());
		}
		
		//Testando o getImoveis por EstadoDeImovel
		Assert.assertEquals("[0|Casa imobiliada para Alugar|Rua Joaquim" +
				" Caroca, Bodocongo, Num 2471, CG/PB|3500.0|4.0x6.0 (PEQUENA)|" +
				"CASA|ALUGUEL|A_VENDA, 1|Apartamento a Venda!!!|" +
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831," +
				" JP/PB, Ed. Java, apto 1300|25000.0|4.0x6.0 (PEQUENA)|" +
				"APARTAMENTO|VENDA|A_VENDA, 2|Terreno a venda no Altiplano!|" +
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB|50000.0|" +
				"15.0x7.0 (GRANDE)|TERRENO|VENDA|A_VENDA]",
				colecaoImovel.getImoveis(EstadoImovel.A_VENDA).toString());
		
		Assert.assertEquals("[]", colecaoImovel.
				getImoveis(EstadoImovel.ALUGADO).toString());
		Assert.assertEquals("[]", colecaoImovel.
				getImoveis(EstadoImovel.PEDIDO).toString());
		Assert.assertEquals("[]", colecaoImovel.
				getImoveis(EstadoImovel.VENDIDO).toString());
		
		imovel1.vendido();
		
		Assert.assertEquals("[0|Casa imobiliada para Alugar|Rua Joaquim Caroca," +
				" Bodocongo, Num 2471, CG/PB|3500.0|4.0x6.0 (PEQUENA)|CASA|" +
				"ALUGUEL|VENDIDO]",
				colecaoImovel.getImoveis(EstadoImovel.VENDIDO).toString());
		
		imovel1.alugado();
		
		Assert.assertEquals("[0|Casa imobiliada para Alugar|Rua Joaquim Caroca," +
				" Bodocongo, Num 2471, CG/PB|3500.0|4.0x6.0 (PEQUENA)|CASA|" +
				"ALUGUEL|ALUGADO]",
				colecaoImovel.getImoveis(EstadoImovel.ALUGADO).toString());
		
		imovel1.pedido();
		
		Assert.assertEquals("[0|Casa imobiliada para Alugar|Rua Joaquim Caroca," +
				" Bodocongo, Num 2471, CG/PB|3500.0|4.0x6.0 (PEQUENA)|CASA|" +
				"ALUGUEL|PEDIDO]",
				colecaoImovel.getImoveis(EstadoImovel.PEDIDO).toString());
		
		imovel1.a_venda();
		
		//Testando o getImoveis por Nome
		
		Assert.assertEquals("[0|Casa imobiliada para Alugar|Rua Joaquim" +
				" Caroca, Bodocongo, Num 2471, CG/PB|3500.0|4.0x6.0 (PEQUENA)|" +
				"CASA|ALUGUEL|A_VENDA, 1|Apartamento a Venda!!!|" +
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831," +
				" JP/PB, Ed. Java, apto 1300|25000.0|4.0x6.0 (PEQUENA)|" +
				"APARTAMENTO|VENDA|A_VENDA, 2|Terreno a venda no Altiplano!|" +
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB|50000.0|" +
				"15.0x7.0 (GRANDE)|TERRENO|VENDA|A_VENDA]",
				colecaoImovel.getImoveis("a").toString());
		
		try{
			colecaoImovel.getImoveis("");
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
		
		try{
			colecaoImovel.getImoveis("     ");
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
		
		try{
			Assert.assertEquals("[0|Casa imobiliada para Alugar|Rua Joaquim" +
					" Caroca, Bodocongo, Num 2471, CG/PB|3500.0|4.0x6.0 " +
					"(PEQUENA)|CASA|ALUGUEL|A_VENDA]", colecaoImovel.
					getImoveis("Casa imobiliada para Alugar").toString());
		}catch(Exception e){
			Assert.fail("Nao Deveria Cair Nesse catch");
		}
		
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

		try{
			colecaoImovelParaCompararEquals.addImovel(imovel1);
			
			Assert.assertEquals(1, colecaoImovelParaCompararEquals.
					getImoveis().size());
			
			colecaoImovelParaCompararEquals.addImovel(imovel2);
			
			Assert.assertEquals(2, colecaoImovelParaCompararEquals.
					getImoveis().size());
	
			colecaoImovelParaCompararEquals.addImovel(imovel3);
			Assert.assertEquals(3, colecaoImovelParaCompararEquals.
					getImoveis().size());
			
		}catch(Exception e){
			Assert.fail("Nao Deveria Cair Nesse catch");
		}
		
		try{
			colecaoImovelParaCompararEquals.addImovel(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}

		Assert.assertEquals(true, colecaoImovel
				.equals(colecaoImovelParaCompararEquals));

		Assert.assertEquals(true, colecaoImovelParaCompararEquals
				.equals(colecaoImovel));
		
		// Tamanho atual da colecao e 3.
		Assert.assertEquals(3, colecaoImovel.getImoveis().size());
		
		colecaoImovel.removeImovel("2");
		
		//apos a remocao de um imovel ficou 2.
		Assert.assertEquals(2, colecaoImovel.getImoveis().size());
		
		//Tentando remover imovel com registro impossivel
		
		try{
			colecaoImovel.removeImovel("a");
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(Exception e){
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}
		
		//Imovel com registro nao pertencente a colecao
		Assert.assertFalse(colecaoImovel.removeImovel("3"));
		Assert.assertTrue(colecaoImovel.removeImovel("1"));
		
		try{
			colecaoImovel.removeImovel(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(Exception e){
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}
		
		//Testando os getImoveis
		
		
		//Testando equals das Colecoes
		Assert.assertFalse(colecaoImovel.
				equals(colecaoImovelParaCompararEquals));

		Assert.assertFalse(colecaoImovelParaCompararEquals.
				equals(colecaoImovel));
		
		Assert.assertTrue(colecaoImovel.equals(colecaoImovel));
		Assert.assertTrue(colecaoImovelParaCompararEquals.
				equals(colecaoImovelParaCompararEquals));
		
		colecaoImovel.addImovel(imovel2);
		colecaoImovel.addImovel(imovel3);		
		
		Assert.assertTrue(colecaoImovel.equals(colecaoImovelParaCompararEquals));
		
	}
}