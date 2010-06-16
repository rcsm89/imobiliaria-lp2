package imobiliaria.testes;

import static org.junit.Assert.*;
import junit.framework.Assert;
import imobiliaria.controladores.ControladorAlugueis;
import imobiliaria.controladores.ControladorCliente;
import imobiliaria.controladores.ControladorFuncionario;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.controladores.ControladorPedidos;
import imobiliaria.controladores.ControladorTransacoes;
import imobiliaria.processamento.Sistema;
import org.junit.Test;

public class SistemaTest {
	
	private Sistema sis = new Sistema();

	@Test
	public final void testLogin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testControladorAlugueis() {
		
		Assert.assertEquals(
				ControladorAlugueis.getInstance(),
				sis.controladorAlugueis());
		
	}

	@Test
	public final void testControladorImoveis() {
		
		Assert.assertEquals(
				ControladorImovel.getInstance(),
				sis.controladorImoveis());
		
	}

	@Test
	public final void testControladorClientes() {
		
		Assert.assertEquals(
				ControladorCliente.getInstance(),
				sis.controladorClientes());
		
	}

	@Test
	public final void testControladorPedidos() {
		
		Assert.assertEquals(
				ControladorPedidos.getInstance(),
				sis.controladorPedidos());
		
	}

	@Test
	public final void testControladorFuncionarios() {
		
		Assert.assertEquals(
				ControladorFuncionario.getInstance(),
				sis.controladorFuncionarios());
		
	}

	@Test
	public final void testControladorTransacoes() {
		
		Assert.assertEquals(
				ControladorTransacoes.getInstance(),
				sis.controladorTransacoes());
		
	}

	@Test
	public final void testAtualizaDados() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSalvarDados() {
		fail("Not yet implemented"); // TODO
	}

}
