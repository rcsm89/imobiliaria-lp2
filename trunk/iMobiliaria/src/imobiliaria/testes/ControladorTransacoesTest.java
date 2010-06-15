package imobiliaria.testes;

import java.util.GregorianCalendar;

import junit.framework.Assert;
import imobiliara.auxiliar.TipoContratual;
import imobiliara.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorFuncionario;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorTransacoes;
import imobiliaria.entidades.Area;
import imobiliaria.exceptions.TransacaoNaoExistenteException;

import org.junit.Test;

public class ControladorTransacoesTest {

	private ControladorTransacoes controladorTransacoes = ControladorTransacoes
			.getInstance();
	
	@Test
	public final void testCaixa() {

		Assert.assertEquals(0.0, controladorTransacoes.caixa());

		controladorTransacoes.adicionaAoCaixa(500);

		Assert.assertEquals(500.0, controladorTransacoes.caixa());

		controladorTransacoes.removeDoCaixa(230);

		Assert.assertEquals(270.0, controladorTransacoes.caixa());

	}

	@Test
	public final void testPagamentos() throws Exception {

		Assert.assertFalse(controladorTransacoes.pagouNesseMes());

		controladorTransacoes.efetuaPagamentoNoMes();

		Assert.assertTrue(controladorTransacoes.pagouNesseMes());

		try {
			controladorTransacoes.efetuaPagamentoNoMes();
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Pagamento ja efetuado esse mes", e
					.getMessage());
		}

	}

	// Transacoes

	@Test
	public final void testAdicionaTransacao() throws Exception {
		
		ControladorCliente.getInstance().adicionaCliente("12345678910",
				new GregorianCalendar(1991, 2, 1), "endere3co", "Brunaaaaaa",
				TipoImovel.TERRENO);
		
		ControladorFuncionario.getInstance().adicionaFuncionario("10120230344",
				new GregorianCalendar(1991, 14, 13), "Rua 12 de Outubro",
				"Thiago Ferreira", "00111");
		
		ControladorImovel.getInstance().addImovel("Casa imobiliada para Alugar",
				"Rua Joaquim Caroca, Bodocongo, Num 2471, CG/PB", 3500.0,
				new Area(4, 6), TipoImovel.CASA, TipoContratual.ALUGUEL);
		
		
		Assert.assertTrue(
				controladorTransacoes.adicionaTransacao("123.456.789-10",
						"00111", "0"));
		
		Assert.assertFalse(
				controladorTransacoes.adicionaTransacao("numinvalido",
						"00111", "0"));
		
		Assert.assertFalse(
				controladorTransacoes.adicionaTransacao("123.456.789-10",
						"11234", "0"));
		
		Assert.assertFalse(
				controladorTransacoes.adicionaTransacao("123.456.789-10",
						"00111", "8"));
		
	}

	@Test
	public final void testRemoveTransacao() throws TransacaoNaoExistenteException {
		
		// Remove Transacao criada no teste anterior
		
		controladorTransacoes.removeTransacao(0);
		
		try {
			controladorTransacoes.removeTransacao(8);
			Assert.fail();
		} catch (TransacaoNaoExistenteException e) {
			Assert.assertEquals("Transacao nao existente", e.getMessage());
		}
	}

	@Test
	public final void testExibeTransacao() {
		controladorTransacoes.adicionaTransacao("123.456.789-10",
				"00111", "0");
		
		Assert.assertEquals("Transacao de Registro: 1\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 0 - Casa imobiliada para Alugar\n" +
				"Valor da Transacao: 3500.0 - Data: 12/06/2010",
				controladorTransacoes.exibeTransacao(1));
		
	}

	@Test
	public final void testListaTransacoesDoControlador() throws Exception {
		
		Assert.assertEquals("Transacao de Registro: 1\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 0 - Casa imobiliada para Alugar\n" +
				"Valor da Transacao: 3500.0 - Data: 12/06/2010\n\n",
				controladorTransacoes.listaTransacoes());
		
		
		controladorTransacoes.removeTransacao(1);
		
		Assert.assertEquals("", controladorTransacoes.listaTransacoes());
		
		// Adiciona Transacoes
		
		ControladorImovel.getInstance().addImovel("Apartamento a Venda!!!",
				"Rua Fernando Luiz Henrique dos Santos Altiplano, num 2831,"
						+ " JP/PB, Ed. Java, apto 1300", 25000, new Area(4, 6),
				TipoImovel.APARTAMENTO, TipoContratual.VENDA);
		
		ControladorImovel.getInstance().addImovel("Terreno a venda no Altiplano!",
				"Rua Algumacoisa de algo, no Altiplano, Num 9999, JP/PB",
				50000, new Area(15, 7), TipoImovel.TERRENO,
				TipoContratual.VENDA);
		
		controladorTransacoes.adicionaTransacao("123.456.789-10",
				"00111", "1");
		controladorTransacoes.adicionaTransacao("123.456.789-10",
				"00111", "2");
		controladorTransacoes.adicionaTransacao("123.456.789-10",
				"00111", "0");
		
		Assert.assertEquals("Transacao de Registro: 2\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 1 - Apartamento a Venda!!!\n" +
				"Valor da Transacao: 25000.0 - Data: 12/06/2010\n\n" +
				"Transacao de Registro: 3\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 2 - Terreno a venda no Altiplano!\n" +
				"Valor da Transacao: 50000.0 - Data: 12/06/2010\n\n" +
				"Transacao de Registro: 4\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 0 - Casa imobiliada para Alugar\n" +
				"Valor da Transacao: 3500.0 - Data: 12/06/2010\n\n",
				controladorTransacoes.listaTransacoes());
		
		Assert.assertEquals("Transacao de Registro: 2\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 1 - Apartamento a Venda!!!\n" +
				"Valor da Transacao: 25000.0 - Data: 12/06/2010\n\n" +
				"Transacao de Registro: 3\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 2 - Terreno a venda no Altiplano!\n" +
				"Valor da Transacao: 50000.0 - Data: 12/06/2010\n\n" +
				"Transacao de Registro: 4\n" +
				"Vendedor: Thiago Ferreira - CRECI: 00111\n" +
				"Comprador: Brunaaaaaa - CPF: 123.456.789-10\n" +
				"Imovel: 0 - Casa imobiliada para Alugar\n" +
				"Valor da Transacao: 3500.0 - Data: 12/06/2010\n\n",
				controladorTransacoes.listaTransacoesMensais());
	}

	@Test
	public final void testAtualizaControlador() {
		
		// Envolve mes ...
		
		controladorTransacoes.atualizaControlador();
	}

}
