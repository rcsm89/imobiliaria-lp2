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
	    return (getCpf().equals(outraPessoa.getCpf()));
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

	try {
	    new PessoaConcreta("", new GregorianCalendar(1995,
		    Calendar.NOVEMBER, 1), "", "  ");
	    fail("Deveria ter lancado excessao");
	} catch (Exception e) {
	    Assert
		    .assertEquals(
			    "Nome invalido\nEndereco invalido\nCPF invalido"
				    + "\nData de nascimento invalida\n", e
				    .getMessage());
	}
	try {
	    new PessoaConcreta("$%", null, "(!*@&#9873", "   ");
	    fail("Deveria ter lancado excessao");
	} catch (Exception e) {
	    Assert
		    .assertEquals(
			    "Nome invalido\nEndereco invalido\nCPF invalido"
				    + "\nData de nascimento invalida\n", e
				    .getMessage());
	}
	try {
	    new PessoaConcreta("12345678901", null, "Endereco qualquer", "");
	    fail("Deveria ter lancado excessao");
	} catch (Exception e) {
	    Assert.assertEquals("Nome invalido\nData de nascimento invalida\n",
		    e.getMessage());
	}
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
	try {
	    p1.setDataNascimento(new GregorianCalendar(1985, Calendar.JANUARY,
		    15));
	    Assert.assertEquals("15/01/1985", p1.getDataNascimento());
	} catch (Exception e) {
	    fail("Nao deveria receber excessao");
	}
	try {
	    p1.setDataNascimento(new GregorianCalendar(2000, Calendar.JANUARY,
		    15));
	    fail("Nao deveria receber excessao");
	} catch (Exception e) {
	    Assert.assertEquals("Data de nascimento invalida", e.getMessage());
	}
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
	try {
	    p1.setEndereco("La aonde o vento faz a curva, s/n     ");
	} catch (Exception e) {
	    fail("Nao lancou excessao");
	}
	Assert.assertEquals("La Aonde O Vento Faz A Curva, S/N", p1
		.getEndereco());
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
	String[] casosNome = { null, "", "   ", "Jean #$%*", "Jean 1234" };
	for (String nome : casosNome) {
	    try {
		p1.setNome(nome);
		fail("Deveria ter lancado excessao");
	    } catch (Exception e) {
		Assert.assertEquals("Nome invalido\n", e.getMessage());
	    }
	}
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
    public void testSetCpf() throws Exception {
	String[] casosCPF = { null, "123", "1234562626234234", "uahauh" };
	for (String cpf : casosCPF) {
	    try {
		p1.setCpf(cpf);
		fail("Deveria ter lancado excessao");
	    } catch (Exception e) {
		Assert.assertEquals("CPF invalido\n", e.getMessage());
	    }
	}
    }

    /**
     * Teste de metodo para
     * {@link imobiliaria.processamento.Pessoa#equals(java.lang.Object)}.
     * 
     */
    @Test
    public void testEqualsObject() throws Exception {
	PessoaConcreta p2 = new PessoaConcreta("12345678910",
		new GregorianCalendar(1985, Calendar.DECEMBER, 10), "Sem rua",
		"Fulano");
	PessoaConcreta p3 = new PessoaConcreta("12245678910",
		new GregorianCalendar(1985, Calendar.DECEMBER, 10), "Sem rua",
		"Fulano");

	Assert.assertTrue(p2.equals(p1));
	Assert.assertEquals(p3.equals(p1), p3.equals(p2));
    }

    /**
     * Teste de metodo para {@link imobiliaria.processamento.Pessoa#toString()}.
     */
    @Test
    public void testToString() {
	Assert.assertEquals("toStrings diferentes",
		"Jean|123.456.789-10|Av Emtallugar Aquiperto, 142|08/05/1991",
		p1.toString());
    }

    /**
     * Teste que verifica os metodos de login e a senha do usuario do sistema
     */
    @Test
    public void testLoginSenha() {
	// Verifica login e senha default
	Assert.assertEquals("123456", p1.getLogin());
	Assert.assertEquals("08051991", p1.getSenha());

	// Tentar mudar login (invalido)
	String[] loginsInvalidos = { null, "", "   " };
	for (String loginSugerido : loginsInvalidos) {
	    try {
		p1.setLogin(loginSugerido);
		fail("Deveria ter lancado excessao");
	    } catch (Exception e) {
		Assert.assertEquals("Login invalido\n", e.getMessage());
	    }
	}
	// Tentar mudar login (valido)
	try {
	    p1.setLogin("MeuNovoLogin");
	} catch (Exception e) {
	    fail("Nao deveria ter lancado excessao");
	}
	Assert.assertEquals("MeuNovoLogin", p1.getLogin());
	
	// Tentar mudar senha (invalido)
	String[] senhasInvalidas = { null, "", "   " };
	for (String senhaSugerida : senhasInvalidas) {
	    try {
		p1.setSenha(senhaSugerida);
		fail("Deveria ter lancado excessao");
	    } catch (Exception e) {
		Assert.assertEquals("Senha invalida\n", e.getMessage());
	    }
	}
	// Tentar mudar senha (valida)
	try {
	    p1.setSenha("MinhaNovaSenha1234@");
	} catch (Exception e) {
	    fail("Nao deveria ter lancado excessao");
	}
	Assert.assertEquals("MinhaNovaSenha1234@", p1.getSenha());
    }
}
