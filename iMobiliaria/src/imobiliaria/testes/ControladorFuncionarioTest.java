package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliaria.controladores.ControladorFuncionario;
import imobiliaria.entidades.Funcionario;
import imobiliaria.exceptions.FuncionarioNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class ControladorFuncionarioTest {

	private ControladorFuncionario controlFuncionario;

	@Before
	public void setUp() throws Exception {
		controlFuncionario = ControladorFuncionario.getInstance();

		controlFuncionario.adicionaFuncionario("12345678910",
				new GregorianCalendar(1985, Calendar.MARCH, 25),
				"Rua Rodrigues Alves", "Bruno Fabio", "9872");
	}

	@Test
	public void testaAdicionaFuncionario() throws Exception {
		String cpf = "10120230344";
		Calendar dataNascimento = new GregorianCalendar(1991, Calendar.MAY, 18);
		String endereco = "Rua 12 De Outubro";
		String nome = "Thiago Ferreira";
		String numCreci = "00111";
		Assert.assertTrue(controlFuncionario.adicionaFuncionario(cpf,
				dataNascimento, endereco, nome, numCreci));

		// Adicionando um mesmo funcionario
		Assert.assertFalse(controlFuncionario.adicionaFuncionario(cpf,
				dataNascimento, endereco, nome, numCreci));

		try {
			controlFuncionario
					.adicionaFuncionario(null, null, null, null, null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert
					.assertEquals(
							"Nome invalido\nEndereco invalido\nCPF invalido\nData de nascimento invalida\n",
							e.getMessage());
		}

	}

	@Test
	public void testaRemoveFuncionario() {
		int numeroDeFuncionarios = controlFuncionario.getColecaoFuncionario()
				.getNumFuncionarios();

		Assert.assertEquals(2, numeroDeFuncionarios);
		Assert.assertTrue(controlFuncionario.removeFuncionario("00111"));
		numeroDeFuncionarios = controlFuncionario.getColecaoFuncionario()
				.getNumFuncionarios();
		Assert.assertEquals(1, numeroDeFuncionarios);

		String cpfNaoExistente = "00000";
		Assert.assertFalse(controlFuncionario
				.removeFuncionario(cpfNaoExistente));

		try {
			controlFuncionario.removeFuncionario(" ");
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}

		try {
			controlFuncionario.removeFuncionario("A123svv");
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}

		try {
			controlFuncionario.removeFuncionario(null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}

	}

	@Test
	public void testaModificaFuncionario() throws Exception {
		String cpf = "08924412344";
		Calendar dataNascimento = new GregorianCalendar(1991, 1, 18);
		String endereco = "Rua Rodrigues Alves";
		String nome = "Yuri Farias";
		String numCreci = "00222";

		controlFuncionario.adicionaFuncionario(cpf, dataNascimento, endereco,
				nome, numCreci);
		// Obtendo o funcionario pelo creci

		Funcionario funcMod = controlFuncionario
				.getFuncionarioPorCreci(numCreci);

		Assert.assertEquals("Yuri Farias", funcMod.getNome());
		Assert.assertEquals("Rua Rodrigues Alves", funcMod.getEndereco());
		Assert.assertEquals("089.244.123-44", funcMod.getCpf());

		// mudando o cpf, endereco e nome
		controlFuncionario.modificaFuncionario(numCreci, "08924412340",
				dataNascimento, "Rua outro endereco 12", "Yuri Farias Gomes");

		Assert.assertEquals("Yuri Farias Gomes", funcMod.getNome());
		Assert.assertEquals("Rua Outro Endereco 12", funcMod.getEndereco());
		Assert.assertEquals("089.244.123-40", funcMod.getCpf());

		try {
			controlFuncionario.modificaFuncionario(null, "12345678910",
					dataNascimento, "Rua outro endereco 12",
					"Thiago Ferreira Patricio");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("Creci Invalido", e.getMessage());
		}

		try {
			controlFuncionario.modificaFuncionario(numCreci, null,
					dataNascimento, "Rua outro endereco 12",
					"Thiago Ferreira Patricio");
			Assert.fail("Deveria ter lancado excecao");
		} catch (Exception e) {
			Assert.assertEquals("CPF invalido\n", e.getMessage());
		}

		try {
			controlFuncionario.modificaFuncionario("0000", "12345678901",
					dataNascimento, "Rua outro endereco 12",
					"Thiago Ferreira Patricio");
			Assert.fail("Deveria ter lancado excecao");
		} catch (FuncionarioNotFoundException e) {
			Assert.assertEquals("Funcionario nao existente", e.getMessage());
		}

	}

	@Test
	public void testaGetFuncionario() throws Exception {
		String nomeFuncProcurado = "Bruno Fabio";
		String creciFuncProcurado = "9872";
		Assert.assertEquals(nomeFuncProcurado, controlFuncionario
				.getFuncionarioPorCpf("123.456.789-10").getNome());

		Assert.assertEquals(creciFuncProcurado, controlFuncionario
				.getFuncionarioPorCreci("9872").getCreci());

		String cpfNaoExistente = "777.888.444-22";
		String creciNaoExistente = "00000";

		Assert.assertNull("Funcionario nao existente", controlFuncionario
				.getFuncionarioPorCpf(cpfNaoExistente));
		Assert.assertNull("Funcionario nao existente", controlFuncionario
				.getFuncionarioPorCreci(creciNaoExistente));

		try {
			controlFuncionario.getFuncionarioPorCreci(null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Creci invalido", e.getMessage());
		}

		try {
			controlFuncionario.getFuncionarioPorCreci("as213");
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Creci invalido", e.getMessage());
		}

		try {
			controlFuncionario.getFuncionarioPorCpf(null);
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("CPF invalido", e.getMessage());
		}

		try {
			controlFuncionario.getFuncionarioPorCpf("  ");
			Assert.fail("Deveria ter lancado excecao");
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("CPF invalido", e.getMessage());
		}

	}

	@Test
	public void testaExibeFuncionarioPorCreci() throws Exception {

		String numCreci = "9872";
		Assert.assertEquals("Nome: Bruno Fabio\n" + "Creci: 9872\n"
				+ "Cpf: 123.456.789-10\n" + "Data de Nascimento: 25/03/1985\n"
				+ "Endereco: Rua Rodrigues Alves", controlFuncionario
				.exibeFuncionarioPorCreci(numCreci));
	}

	@Test(expected = Exception.class)
	public void testaExibeFuncionarioPorCreciInvalido() throws Exception {
		controlFuncionario.exibeFuncionarioPorCreci(null);
		controlFuncionario.exibeFuncionarioPorCreci("123sa");
		controlFuncionario.exibeFuncionarioPorCreci("     ");

	}

	@Test
	public void testaExibeFuncionarioPorCpf() throws Exception {
		Assert.assertEquals("Nome: Bruno Fabio\n" + "Creci: 9872\n"
				+ "Cpf: 123.456.789-10\n" + "Data de Nascimento: 25/03/1985\n"
				+ "Endereco: Rua Rodrigues Alves", controlFuncionario
				.exibeFuncionarioPorCpf("123.456.789-10"));
	}

	@Test(expected = Exception.class)
	public void testaExibeFuncionarioPorCpfInvalido() throws Exception {
		controlFuncionario.exibeFuncionarioPorCpf(null);
		controlFuncionario.exibeFuncionarioPorCpf("123sa");
		controlFuncionario.exibeFuncionarioPorCpf("     ");
	}

	@Test
	public void testaListaFuncionarios() {
		Assert.assertEquals("Nome: Bruno Fabio - CPF: 123.456.789-10\n"
				+ "Endereco: Rua Rodrigues Alves\n"
				+ "Data de Nascimento: 25/03/1985 - Creci: 9872\n\n"
				+ "Nome: Yuri Farias Gomes - CPF: 089.244.123-40\n"
				+ "Endereco: Rua Outro Endereco 12\n"
				+ "Data de Nascimento: 18/02/1991 - Creci: 00222\n\n",
				controlFuncionario.listaFuncionarios());
	}

	@Test
	public void testaTotaisDeVendas() throws Exception {
		// Nenhum dos funcionario fizeram uma venda neste mes
		Assert.assertEquals("{Bruno Fabio=0.0, Yuri Farias Gomes=0.0}",
				controlFuncionario.listaTotaisDeVendas().toString());

		Assert.assertEquals("Nome: Bruno Fabio\n" +
				"Valor de Vendas: 0.0\n\n" +
				"Nome: Yuri Farias Gomes\n" +
				"Valor de Vendas: 0.0\n\n", controlFuncionario.exibeTotaisDeVendas());
		
	}
}
