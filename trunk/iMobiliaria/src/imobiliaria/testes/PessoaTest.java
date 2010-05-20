/* TERMINAR DE IMPLEMENTAR
 */
/**
 * Package com as classes de Teste dos modulos presentes em processamento
 */
package imobiliaria.testes;

import static org.junit.Assert.fail;
import imobiliaria.processamento.Pessoa;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Verifica implementacao da classe abstrata Pessoa
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class PessoaTest {

	private PessoaConcreta p1;

	/**
	 * Modela um objeto concreto de Pessoa
	 * 
	 * @author Jeanderson Barros Candido
	 * 
	 */
	public class PessoaConcreta extends Pessoa {

		public PessoaConcreta(String cpf, Calendar dataNascimento,
				String endereco, String nome) throws Exception {
			super(cpf, dataNascimento, endereco, nome);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see imobiliaria.processamento.Pessoa#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof PessoaConcreta))
				return false;

			PessoaConcreta outraPessoa = (PessoaConcreta) obj;
			return (outraPessoa.getCpf().equals(getCpf()));
		}

	}

	/**
	 * Cria um objeto PessoaConcreta que sera usado nos testes
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		Calendar maiorDeIdade = new GregorianCalendar(1991, Calendar.MAY, 8);
		String end1 = "Av EmTalLugar AquiPerto, 142";
		p1 = new PessoaConcreta("12345678910", maiorDeIdade, end1, "Jean");

	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#Pessoa(java.lang.String, java.util.Calendar, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testPessoa() {
		fail("Not yet implemented");
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#getDataNascimento()}.
	 */
	@Test
	public void testGetDataNascimento() {
		Assert.assertEquals("Retornou datas diferentes", "08/05/1991", p1
				.getDataNascimento());
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#setDataNascimento(java.util.Calendar)}
	 * .
	 */
	@Test
	public void testSetDataNascimento() {
		fail("Not yet implemented");
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#getEndereco()}.
	 */
	@Test
	public void testGetEndereco() {
		Assert.assertEquals("Retornou enderecos diferentes",
				"Av Emtallugar Aquiperto, 142", p1.getEndereco());
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#setEndereco(java.lang.String)}.
	 */
	@Test
	public void testSetEndereco() {
		fail("Not yet implemented");
	}

	/**
	 * Teste de metodo para {@link imobiliaria.processamento.Pessoa#getNome()}.
	 */
	@Test
	public void testGetNome() {
		Assert.assertEquals("Retornou nomes diferentes", "Jean", p1.getNome());
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#setNome(java.lang.String)}.
	 */
	@Test
	public void testSetNome() {
		fail("Not yet implemented");
	}

	/**
	 * Teste de metodo para {@link imobiliaria.processamento.Pessoa#getCpf()}.
	 */
	@Test
	public void testGetCpf() {
		Assert.assertEquals("Retornou CPF diferente", "123.456.789-10", p1
				.getCpf());
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#setCpf(java.lang.String)}.
	 */
	@Test
	public void testSetCpf() {
		fail("Not yet implemented");
	}

	/**
	 * Teste de metodo para
	 * {@link imobiliaria.processamento.Pessoa#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

	/**
	 * Teste de metodo para {@link imobiliaria.processamento.Pessoa#toString()}.
	 */
	@Test
	public void testToString() {
		Assert.assertEquals("toStrings diferentes",
				"Jean|123.456.789-10|Av Emtallugar Aquiperto, 142|08/05/1991", p1
						.toString());
	}
}
