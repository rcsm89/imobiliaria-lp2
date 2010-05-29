package imobiliaria.testes;

import imobiliaria.controladores.ControladorImovel;
import imobiliaria.processamento.Area;
//import imobiliaria.processamento.EstadoImovel;
import imobiliaria.processamento.Imovel;
import imobiliaria.processamento.TipoContratual;
import imobiliaria.processamento.TipoImovel;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ControladorImovelTest {

	private ControladorImovel controladorImovel;
	private Imovel imovel1;
	private Imovel imovel2;
	private Imovel imovel3;

	@Before
	public void setUp() throws Exception {
		controladorImovel = new ControladorImovel();
		
		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(30, 20), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831," +
				" JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);

		imovel3 = new Imovel("Terreno a venda no Altiplano!",
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB",
				50000, new Area(15, 7), TipoImovel.TERRENO,
				TipoContratual.VENDA);
	}

	@Test
	public void testaModificaImovel() throws Exception {

		String nome = "Casa imobiliada para Vender";
		String endereco = imovel1.getEndereco();
		double preco = imovel1.getValor();
		Area area = imovel1.getArea();
		TipoImovel tipoDoImovel = imovel1.getTipoDoImovel();
		TipoContratual tipoDeContrato = imovel1.getTipoContratual();

		controladorImovel.modificaImovel(imovel1, nome, endereco, preco, area,
				tipoDoImovel, tipoDeContrato);

		Assert.assertFalse("Casa imobiliada para Alugar".equals(imovel1.
				getNome()));
		
		Assert.assertEquals("Casa imobiliada para Vender", imovel1.getNome());
		
		controladorImovel.modificaImovel(imovel2, "Apartamento para Vender",
				"Rua Antonio Joaquim de Oliveira, num 230", 20.000,
				new Area(20, 30), TipoImovel.CASA, TipoContratual.ALUGUEL);
		
		Assert.assertFalse("Apartamento a Venda!!!".equals(imovel2.
				getNome()));
		
		Assert.assertEquals("Apartamento para Vender", imovel2.getNome());
		
		try {
			controladorImovel.modificaImovel(null, imovel1.getNome(),
					imovel1.getEndereco(), imovel1.getValor(),
					imovel1.getArea(), imovel1.getTipoDoImovel(),
					imovel1.getTipoContratual());
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}
		
		Assert.assertEquals("Casa imobiliada para Vender", imovel1.getNome());
		Assert.assertEquals("Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB",
				imovel1.getEndereco());
		Assert.assertEquals(3500.0, imovel1.getValor());
		Assert.assertEquals(new Area(30, 20), imovel1.getArea());
		Assert.assertEquals(TipoImovel.CASA, imovel1.getTipoDoImovel());
		Assert.assertEquals(TipoContratual.ALUGUEL, 
				imovel1.getTipoContratual());

		//Testando o metodo getImovel(String registro)
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals(imovel1, controladorImovel.getImovel("0"));
		Assert.assertEquals(imovel2, controladorImovel.getImovel("1"));
		Assert.assertEquals(imovel3, controladorImovel.getImovel("2"));

	}
	
	@Test
	public void testaGetImovelPorRegistro() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals(imovel1, controladorImovel.getImovel("3"));
		Assert.assertEquals(imovel2, controladorImovel.getImovel("4"));
		Assert.assertEquals(imovel3, controladorImovel.getImovel("5"));
		
		try{
			controladorImovel.getImovel(null);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}
		
	}
	
	@Test
	public void testaExibeImovel() throws Exception{

		controladorImovel.addImovel(imovel1);
		
		try{
			controladorImovel.exibeImovel(null);
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n"
				+ "Endereco: " + imovel1.getEndereco() + "\n"
				+ "Valor: " + imovel1.getValor()
				+ "\n" + "Area: " + "\n\t" +
				"Comprimento: " + imovel1.getArea().getComprimento() + "m\n\t" +
				"Largura: " + imovel1.getArea().getLargura() + "m\n\t" +
				"Classificacao: " + imovel1.getArea().getClassificacao() + "\n"
				+ "Tipo do Imovel: " + imovel1.getTipoDoImovel() + "\n"
				+ "Tipo Contratual: " + imovel1.getTipoContratual(),
				controladorImovel.exibeImovel("6"));
	}
	
	@Test
	public void testaAddImovel() throws Exception {
		
		try{
			controladorImovel.addImovel(null);
		}catch(Exception e){
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}
		
	}
	
	@Test
	public void testaListaImoveis(){
		
	}
/*	
	@Test
	public void testaListaImoveis(double min, double max){	
	}
	
	@Test
	public void testaListaImoveis(TipoContratual tipoContratual){	
	}
	
	@Test
	public void testaListaImoveis(TipoImovel tipoImovel){	
	}
	
	@Test
	public void testaListaImoveis(EstadoImovel estadoImovel){	
	}
	
	@Test
	public void testaListaImoveis(String registro){	
	}*/

}
