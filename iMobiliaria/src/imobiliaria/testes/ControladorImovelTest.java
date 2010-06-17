package imobiliaria.testes;

import imobiliaria.auxiliar.EstadoImovel;
import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.entidades.Area;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ControladorImovelTest {

	private ControladorImovel controladorImovel;
	
	//Atributos Imovel1
	String nomeIm1;
	private String enderecoIm1;
	double precoIm1;
	private Area areaIm1;
	private TipoImovel tipoDoImovel1;
	private TipoContratual tipoDeContrato1;
	
	//Atributos Imovel2
	String nomeIm2;
	private String enderecoIm2;
	double precoIm2;
	private Area areaIm2;
	private TipoImovel tipoDoImovel2;
	private TipoContratual tipoDeContrato2;
	
	//Atributos Imovel3
	String nomeIm3;
	private String enderecoIm3;
	double precoIm3;
	private Area areaIm3;
	private TipoImovel tipoDoImovel3;
	private TipoContratual tipoDeContrato3;

	@Before
	public void setUp() throws Exception {
		controladorImovel = ControladorImovel.getInstance();

		nomeIm1 = "Casa imobiliada para Vender";
		enderecoIm1 = "Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB"; 
		precoIm1 = 3500.0;
		areaIm1 = new Area(30, 20);
		tipoDoImovel1 = TipoImovel.CASA;
		tipoDeContrato1 = TipoContratual.VENDA;
		
		nomeIm2 = "Apartamento a Venda!!!";
		enderecoIm2 = "Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300"; 
		precoIm2 = 2500.0;
		areaIm2 = new Area(4, 6);
		tipoDoImovel2 = TipoImovel.APARTAMENTO;
		tipoDeContrato2 = TipoContratual.VENDA;
		
		nomeIm3 = "Terreno a venda no Altiplano!";
		enderecoIm3 = "Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB"; 
		precoIm3 = 50000.0;
		areaIm3 = new Area(15, 7);
		tipoDoImovel3 = TipoImovel.TERRENO;
		tipoDeContrato3 = TipoContratual.VENDA;
	}

	@Test
	public void testaModificaImovel() throws Exception {		
		
		try{
			controladorImovel.addImovel(nomeIm1, enderecoIm1, precoIm1, areaIm1,
					tipoDoImovel1, tipoDeContrato1);
			
			controladorImovel.addImovel(nomeIm2, enderecoIm2, precoIm2, areaIm2,
					tipoDoImovel2, tipoDeContrato2);
			
			controladorImovel.addImovel(nomeIm3, enderecoIm3, precoIm3, areaIm3,
					tipoDoImovel3, tipoDeContrato3);
		}catch(IllegalArgumentException e){
			Assert.fail("Nao Deveria Chegar aqui");
		}

		controladorImovel.modificaImovel("0", "Casa imobiliada para Alugar",
				enderecoIm1, precoIm1, areaIm1,	tipoDoImovel1, TipoContratual.
				ALUGUEL);

		Assert.assertNotSame("Casa imobiliada para Vender", controladorImovel
				.getImovel("0").getNome());

		Assert.assertEquals("Casa imobiliada para Alugar", controladorImovel
				.getImovel("0").getNome());
		
		Assert.assertNotSame(tipoDeContrato1, controladorImovel.getImovel("0").
				getTipoContratual());
		
		Assert.assertEquals(TipoContratual.ALUGUEL, controladorImovel.
				getImovel("0").getTipoContratual());
		
		try{
			controladorImovel.modificaImovel("0", null, enderecoIm1, precoIm1,
					areaIm1, tipoDoImovel1, tipoDeContrato1);
			Assert.fail("Nao Deveria Chegar aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Nome invalido", e.getMessage());
		}
		
		//provando que nao alterou nada, apos o modificaImovel passando null.
		Assert.assertEquals("Casa imobiliada para Alugar",
				 controladorImovel.getImovel("0").getNome());
		
		controladorImovel.modificaImovel("1", nomeIm2, enderecoIm2, 26000.0,
				areaIm2, tipoDoImovel2, tipoDeContrato2);

		Assert.assertNotSame(25000,	controladorImovel.getImovel("1").getValor());

		Assert.assertEquals(26000.0, controladorImovel.getImovel("1")
				.getValor());

		try {
			controladorImovel.modificaImovel(null, nomeIm1, enderecoIm1, 
					precoIm1, areaIm1, tipoDoImovel1, tipoDeContrato1);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}

		// Testando o metodo getImovel(String registro)
		
		try{
			controladorImovel.addImovel(nomeIm1, enderecoIm1, precoIm1, areaIm1,
					tipoDoImovel1, tipoDeContrato1);
			
			controladorImovel.addImovel(nomeIm2, enderecoIm2, precoIm2, areaIm2,
					tipoDoImovel2, tipoDeContrato2);
			
			controladorImovel.addImovel(nomeIm3, enderecoIm3, precoIm3, areaIm3,
					tipoDoImovel3, tipoDeContrato3);
		}catch(IllegalArgumentException e){
			Assert.fail("Nao Deveria Chegar aqui");
		}
		

		Assert.assertEquals(nomeIm2, controladorImovel.getImovel("1").
				getNome());
		Assert.assertEquals(nomeIm3, controladorImovel.getImovel("2").
				getNome());
		Assert.assertEquals(nomeIm1, controladorImovel.getImovel("3").
				getNome());
		Assert.assertEquals(nomeIm2, controladorImovel.getImovel("4").
				getNome());
		Assert.assertEquals(nomeIm3, controladorImovel.getImovel("5").
				getNome());
		Assert.assertEquals("Casa imobiliada para Alugar",
				controladorImovel.getImovel("0").getNome());
	}

	@Test
	public void testaGetImovelPorRegistro() throws Exception {
		
		Assert.assertEquals(nomeIm2, controladorImovel.getImovel("1").
				getNome());
		
		try{
			controladorImovel.addImovel("imovel1 adicionado para teste",
					enderecoIm1, precoIm1, areaIm1, tipoDoImovel1,
					tipoDeContrato1);
			
			controladorImovel.addImovel("imovel2 adicionado para teste",
					enderecoIm2, precoIm2, areaIm2, tipoDoImovel2,
					tipoDeContrato2);
			
			controladorImovel.addImovel("imovel3 adicionado para teste",
					enderecoIm3, precoIm3, areaIm3,	tipoDoImovel3,
					tipoDeContrato3);
		}catch(IllegalArgumentException e){
			Assert.fail("Nao Deveria Chegar aqui");
		}
		
		Assert.assertEquals("imovel1 adicionado para teste",
				controladorImovel.getImovel("6").getNome());
		Assert.assertEquals("imovel2 adicionado para teste",
				controladorImovel.getImovel("7").getNome());
		Assert.assertEquals("imovel3 adicionado para teste",
				controladorImovel.getImovel("8").getNome());

		try {
			controladorImovel.getImovel(null);
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}
	}

	@Test
	public void testaExibeImovel() throws Exception {
		
		try {
			controladorImovel.exibeImovel(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Registro Invalido", e.getMessage());
		}

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: \n" +
				"	Comprimento: 30.0m\n" +
				"	Largura: 20.0m\n" +
				"	Classificacao: GRANDE\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL",
				controladorImovel.exibeImovel("0"));
	}

	@Test
	public void testaAddImovel() throws Exception {

		try {
			controladorImovel.addImovel(null, enderecoIm1, 20000,
					areaIm1, tipoDoImovel1, tipoDeContrato1);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Nome invalido\n", e.getMessage());
		}
		
		try {
			controladorImovel.addImovel(nomeIm1, null, 20000,
					areaIm1, tipoDoImovel1, tipoDeContrato1);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (Exception e) {
			Assert.assertEquals("Endereco invalido\n", e.getMessage());
		}
		
		try {
			controladorImovel.addImovel(nomeIm1, enderecoIm1, -1,
					areaIm1, tipoDoImovel1, tipoDeContrato1);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Valor invalido\n", e.getMessage());
		}
		
		try {
			controladorImovel.addImovel(nomeIm1, enderecoIm1, 20000,
					null, tipoDoImovel1, tipoDeContrato1);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Area invalida\n", e.getMessage());
		}
		
		try {
			controladorImovel.addImovel(nomeIm1, enderecoIm1, 20000,
					areaIm1, null, tipoDeContrato1);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Tipo de Imovel invalido\n", e.getMessage());
		}
		
		try {
			controladorImovel.addImovel(nomeIm1, enderecoIm1, 20000,
					areaIm1, tipoDoImovel1, null);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Tipo Contratual invalido\n", e.getMessage());
		}
		
		try {
			controladorImovel.addImovel(null, null, -1,	null, null, null);
			Assert.fail("Deveria Lancar Excecao aqui");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Nome invalido\nEndereco invalido\n" +
					"Valor invalido\nArea invalida\nTipo de Imovel invalido\n" +
					"Tipo Contratual invalido\n", e.getMessage());
		}

	}

	@Test
	public void testaListaImoveis() throws Exception {		

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n" +
				"Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 3\n" +
				"Nome: Casa imobiliada para Vender\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 4\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 2500.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 5\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 6\n" +
				"Nome: imovel1 adicionado para teste\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 7\n" +
				"Nome: imovel2 adicionado para teste\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 2500.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 8\n" +
				"Nome: imovel3 adicionado para teste\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",controladorImovel.listaImoveis());
		
		controladorImovel.removeImovel("8");
		controladorImovel.removeImovel("7");
		controladorImovel.removeImovel("6");
		controladorImovel.removeImovel("5");
		controladorImovel.removeImovel("4");
		controladorImovel.removeImovel("3");
		
		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n" +
				"Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",controladorImovel.listaImoveis());
	}

	@Test
	public void testaListaImoveisNoIntervalo() throws Exception {

		try {
			controladorImovel.listaImoveis(10, 5);
		} catch (Exception e) {
			Assert.assertEquals("Intervalo Invalido", e.getMessage());
		}

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n" +
				"Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(3500, 26000));
		
		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n",
				controladorImovel.listaImoveis(3400, 3600));
		
		Assert.assertEquals("",controladorImovel.listaImoveis(0, 1000));
	}

	@Test
	public void testaListaImoveisPorTipoContratual() throws Exception {

		Assert.assertEquals("Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(TipoContratual.VENDA));
		
		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n",
				controladorImovel.listaImoveis(TipoContratual.ALUGUEL));
	}

	@Test
	public void testaListaImoveisPorTipoDeImovel() throws Exception {

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n",
				controladorImovel.listaImoveis(TipoImovel.CASA));

		Assert.assertEquals("Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(TipoImovel.APARTAMENTO));

		Assert.assertEquals("Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(TipoImovel.TERRENO));
	}

	@Test
	public void testaListaImoveisPorEstado() throws Exception {

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n" +
				"Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(EstadoImovel.A_VENDA));
		
		controladorImovel.getImovel("2").vendido();
		
		Assert.assertEquals("Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(EstadoImovel.VENDIDO));
		
		controladorImovel.getImovel("1").alugado();
		
		Assert.assertEquals("Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis(EstadoImovel.ALUGADO));
		
		controladorImovel.getImovel("0").pedido();
		
		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n",
				controladorImovel.listaImoveis(EstadoImovel.PEDIDO));
	}

	@Test
	public void testaListaImoveisPorNome() throws Exception {

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n",
				controladorImovel.listaImoveis("Casa imobiliada"));

		Assert.assertEquals("Registro: 0\n" +
				"Nome: Casa imobiliada para Alugar\n" +
				"Endereco: Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB\n" +
				"Valor: 3500.0\n" +
				"Area: 30.0x20.0 (GRANDE)\n" +
				"Tipo do Imovel: CASA\n" +
				"Tipo Contratual: ALUGUEL\n\n" +
				"Registro: 1\n" +
				"Nome: Apartamento a Venda!!!\n" +
				"Endereco: Rua Fernando Luiz Henrique dos Santos Altiplano," +
				" num 2831, JP/PB, Ed. Java, apto 1300\n" +
				"Valor: 26000.0\n" +
				"Area: 4.0x6.0 (PEQUENA)\n" +
				"Tipo do Imovel: APARTAMENTO\n" +
				"Tipo Contratual: VENDA\n\n" +
				"Registro: 2\n" +
				"Nome: Terreno a venda no Altiplano!\n" +
				"Endereco: Rua Algumacoisa de algo, no Altiplano, Num 9999," +
				" JP/PB\n" +
				"Valor: 50000.0\n" +
				"Area: 15.0x7.0 (GRANDE)\n" +
				"Tipo do Imovel: TERRENO\n" +
				"Tipo Contratual: VENDA\n\n",
				controladorImovel.listaImoveis("a"));
		
		try{
			Assert.assertEquals("", controladorImovel.listaImoveis(" "));
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Nome Invalido", e.getMessage());
		}
	}
	
	@Test
	public void testaRemoveImoveis() throws Exception {
		
		Assert.assertNull(controladorImovel.getImovel("3"));
		//null, porque ja foi anteriormente removido
		
		Assert.assertNotNull(controladorImovel.getImovel("0"));
		Assert.assertNotNull(controladorImovel.getImovel("1"));
		Assert.assertNotNull(controladorImovel.getImovel("2"));
		
		controladorImovel.removeImovel("0");
		controladorImovel.removeImovel("1");
		controladorImovel.removeImovel("2");
		
		Assert.assertNull(controladorImovel.getImovel("0"));
		Assert.assertNull(controladorImovel.getImovel("1"));
		Assert.assertNull(controladorImovel.getImovel("2"));
		
		try{
			controladorImovel.removeImovel(null);
			Assert.fail("Deveria Lancar Excecao aqui");
		}catch(IllegalArgumentException e){
			Assert.assertEquals("Imovel Invalido", e.getMessage());
		}
	}
}
