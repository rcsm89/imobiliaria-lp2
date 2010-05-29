package imobiliaria.testes;

import imobiliaria.controladores.ControladorImovel;
import imobiliaria.processamento.Area;
//import imobiliaria.processamento.EstadoImovel;
import imobiliaria.processamento.EstadoImovel;
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

		imovel2 = new Imovel("Apartamento a Venda!!!",
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
	public void testaListaImoveis() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
					+ imovel1.getEndereco() + "\n" + "Valor: "
					+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
					+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
					+ "Tipo Contratual: " + imovel1 + "\n\n", 
					controladorImovel.listaImoveis());
		
		controladorImovel.addImovel(imovel2);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n" +
				"Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n", 
				controladorImovel.listaImoveis());
		
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n" +
				"Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n" +
				"Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis());
	}

	@Test
	public void testaListaImoveis1() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		try{
			controladorImovel.listaImoveis(10, 5);
		}catch(Exception e){
			Assert.assertEquals("Intervalo Invalido", e.getMessage());
		}
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
					+ imovel1.getEndereco() + "\n" + "Valor: "
					+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
					+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
					+ "Tipo Contratual: " + imovel1 + "\n\n", 
					controladorImovel.listaImoveis(3500, 5000));
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n" +
				"Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n", 
				controladorImovel.listaImoveis(3000, 25000));
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n" +
				"Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n" +
				"Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis(1000, 50000));
		
		Assert.assertEquals("Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n", 
				controladorImovel.listaImoveis(24999.0, 25001.0));
		
		Assert.assertEquals("Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n", 
				controladorImovel.listaImoveis(49999, 50001));
	}
	
	@Test
	public void testaListaImoveis2() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
					+ imovel1.getEndereco() + "\n" + "Valor: "
					+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
					+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
					+ "Tipo Contratual: " + imovel1 + "\n\n", 
					controladorImovel.listaImoveis(TipoContratual.ALUGUEL));
		
		Assert.assertEquals("Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n" +
				"Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis(TipoContratual.VENDA));
	}
	
	@Test
	public void testaListaImoveis3() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n", 
				controladorImovel.listaImoveis(TipoImovel.CASA));
		
		Assert.assertEquals("Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n", 
				controladorImovel.listaImoveis(TipoImovel.APARTAMENTO));
		
		Assert.assertEquals("Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n", 
				controladorImovel.listaImoveis(TipoImovel.TERRENO));
	}
	
	@Test
	public void testaListaImoveis4() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n" +
				"Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n" +
				"Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis(EstadoImovel.A_VENDA));
		
		imovel1.setEstadoDoImovel(EstadoImovel.PEDIDO);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n",
				controladorImovel.listaImoveis(EstadoImovel.PEDIDO));
		
		imovel2.setEstadoDoImovel(EstadoImovel.VENDIDO);
		
		Assert.assertEquals("Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n",
				controladorImovel.listaImoveis(EstadoImovel.VENDIDO));
	}
	
	@Test
	public void testaListaImoveis5() throws Exception{
		
		controladorImovel.addImovel(imovel1);
		controladorImovel.addImovel(imovel2);
		controladorImovel.addImovel(imovel3);
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n",
				controladorImovel.listaImoveis("Casa imobiliada"));
		
		Assert.assertEquals("Nome: " + imovel1.getNome() + "\n" + "Endereco: "
				+ imovel1.getEndereco() + "\n" + "Valor: "
				+ imovel1.getValor() + "\n" + "Area: " + imovel1.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel1 + "\n"
				+ "Tipo Contratual: " + imovel1 + "\n\n" +
				"Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n" +
				"Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis("a"));
		
		Assert.assertEquals("Nome: " + imovel2.getNome() + "\n" + "Endereco: "
				+ imovel2.getEndereco() + "\n" + "Valor: "
				+ imovel2.getValor() + "\n" + "Area: " + imovel2.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel2 + "\n"
				+ "Tipo Contratual: " + imovel2 + "\n\n",
				controladorImovel.listaImoveis("Apartamento"));
		
		Assert.assertEquals("Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis("Terreno"));
		
		Assert.assertNotSame("Nome: " + imovel3.getNome() + "\n" + "Endereco: "
				+ imovel3.getEndereco() + "\n" + "Valor: "
				+ imovel3.getValor() + "\n" + "Area: " + imovel3.getArea()
				+ "\n" + "Tipo do Imovel: " + imovel3 + "\n"
				+ "Tipo Contratual: " + imovel3 + "\n\n",
				controladorImovel.listaImoveis("terreno"));//case sensitive
	}
}
