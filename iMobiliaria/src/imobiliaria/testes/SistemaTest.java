package imobiliaria.testes;

import imobiliara.aux.TipoContratual;
import imobiliara.aux.TipoImovel;
import imobiliara.aux.TipoLogin;
import imobiliaria.entidades.Area;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.EstadoImovel;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;
import imobiliaria.processamento.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SistemaTest {

	Sistema sistema;
	Imovel imovel1;
	Imovel imovel2;
	Cliente cliente1;
	Funcionario func1;

	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();

		imovel1 = new Imovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);

		imovel2 = new Imovel(
				"Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831, JP/PB, Ed. Java, apto 1300",
				25000, new Area(4, 6), TipoImovel.APARTAMENTO,
				TipoContratual.VENDA);

		cliente1 = new Cliente("12345678910", new GregorianCalendar(1991,
				Calendar.APRIL, 4), "Rua Alberto de Brito, 84", "Bruno Paiva",
				TipoImovel.CASA);

		func1 = new Funcionario("12345678910", new GregorianCalendar(1991, 2,
				17), "Rua 12 de Outubro", "Yuri Farias", "12345");

	}

	@Test
	public void testEfetuaPagamentoNoMes() throws Exception {
		Assert.assertFalse(sistema.pagouNesseMes());
		sistema.efetuaPagamentoNoMes();
		Assert.assertTrue(sistema.pagouNesseMes());

		try {
			sistema.efetuaPagamentoNoMes();
		} catch (Exception e) {
			Assert.assertEquals("Pagamento ja efetuado esse mes", e
					.getMessage());
		}
	}

	@Test
	public void testAdicionaPedido() throws Exception {
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);

		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());

		sistema.adicionaPedido(registroImovel, "123.456.789-10");
		Assert.assertEquals(EstadoImovel.PEDIDO, imovel1.getEstadoDoImovel());

	}

	@Test
	public void testEfetuaPedido() throws Exception {
		// Adicionando um pedido
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);
		sistema.controladorFuncionarios().adicionaFuncionario(func1);

		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.adicionaPedido(registroImovel, "123.456.789-10");

		// Verificando o valor do caixa do sistema
		Assert.assertEquals(0.0, sistema.caixa(), 0.005);

		// Efetuando um pedido
		sistema.efetuaPedido(registroImovel, func1.getCreci());

		// Verificando o valor do caixa do sistema
		Assert.assertEquals(imovel1.getValor(), sistema.caixa(), 0.005);

		// Verificando o estado do imovel
		Assert.assertEquals(EstadoImovel.VENDIDO, imovel1.getEstadoDoImovel());

		// Testando imovel nao pedido
		try {
			sistema.efetuaPedido(registroImovel, func1.getCreci());
		} catch (Exception e) {
			Assert.assertEquals("Imovel nao pedido", e.getMessage());
		}
	}

	@Test
	public void testRemovePedido() throws Exception {
		// Adicionando um pedido
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);
		sistema.controladorFuncionarios().adicionaFuncionario(func1);

		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.adicionaPedido(registroImovel, "123.456.789-10");

		// Removendo um pedido
		registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.removePedido(registroImovel);
		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());

	}

	@Test
	public void testListagemDePedido() throws Exception {
		// Adicionando um pedido
		sistema.controladorClientes().adicionaCliente(cliente1);
		sistema.controladorImoveis().addImovel(imovel1);
		sistema.controladorFuncionarios().adicionaFuncionario(func1);

		Assert.assertEquals(EstadoImovel.A_VENDA, imovel1.getEstadoDoImovel());
		String registroImovel = String.valueOf(imovel1.getRegistroImovel());
		sistema.adicionaPedido(registroImovel, "123.456.789-10");

		// Listando o pedido
		Assert.assertEquals("8 - Casa imobiliada para Alugar Valor: 3500.0\n"
				+ "Cliente que pediu: Bruno Paiva - CPF: 123.456.789-10\n\n",
				sistema.listagemDePedido());

	}

	@Test
	public void testLogin() throws Exception {
		String loginAdmin = "admin";
		String senhaAdmin = "admin";
		Assert.assertTrue(sistema.login(loginAdmin, senhaAdmin,
				TipoLogin.ADMINISTRADOR));
		Assert.assertFalse(sistema.login("login errado", senhaAdmin,
				TipoLogin.ADMINISTRADOR));
		Assert.assertFalse(sistema.login(loginAdmin, "senha errada",
				TipoLogin.ADMINISTRADOR));

		// testando com cliente
		sistema.controladorClientes().adicionaCliente("12345678910",
				new GregorianCalendar(1991, Calendar.APRIL, 4),
				"Rua Alberto de Brito, 84", "Bruno Paiva", TipoImovel.CASA);

		Assert.assertTrue(sistema.login(cliente1.getLogin(), cliente1
				.getSenha(), TipoLogin.CLIENTE));
		Assert.assertFalse(sistema.login("login errado", "senha errada",
				TipoLogin.CLIENTE));

		// testando com funcionario
		sistema.controladorFuncionarios().adicionaFuncionario("12345678910",
				new GregorianCalendar(1991, 2, 17), "Rua 12 de Outubro",
				"Yuri Farias", "12345");
		
		Assert.assertTrue(sistema.login(func1.getLogin(), func1.getSenha(),
				TipoLogin.FUNCIONARIO));
		Assert.assertFalse(sistema.login("login errado", "senha errada",
				TipoLogin.FUNCIONARIO));

	}
}