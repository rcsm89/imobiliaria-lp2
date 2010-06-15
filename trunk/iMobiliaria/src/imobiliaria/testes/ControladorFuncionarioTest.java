package imobiliaria.testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import imobiliaria.controladores.ControladorFuncionario;
import imobiliaria.entidades.Funcionario;

import org.junit.Test;

public class ControladorFuncionarioTest {

    private ControladorFuncionario controlFunc1 = ControladorFuncionario
	    .getInstance();

    @Test
    public void testaModificaFuncionario() throws Exception {
	String cpf = "10120230344";
	Calendar dataNascimento = new GregorianCalendar(1991, 1, 18);
	String endereco = "Rua 12 De Outubro";
	String nome = "Thiago Ferreira";
	String numCreci = "00111";

	controlFunc1.adicionaFuncionario(cpf, dataNascimento, endereco, nome,
		numCreci);
	// Obtendo o funcionario pelo creci

	Funcionario funcMod = controlFunc1.getFuncionarioPorCreci(numCreci);

	Assert.assertEquals("Thiago Ferreira", funcMod.getNome());
	Assert.assertEquals("Rua 12 De Outubro", funcMod.getEndereco());
	Assert.assertEquals("101.202.303-44", funcMod.getCpf());

	// mudando o cpf, endereco e nome
	controlFunc1.modificaFuncionario(funcMod, "12345678910",
		dataNascimento, "Rua outro endereco 12",
		"Thiago Ferreira Patricio", numCreci);

	Assert.assertEquals("Thiago Ferreira Patricio", funcMod.getNome());
	Assert.assertEquals("Rua Outro Endereco 12", funcMod.getEndereco());
	Assert.assertEquals("123.456.789-10", funcMod.getCpf());

	try {
	    controlFunc1.modificaFuncionario(null, "12345678910",
		    dataNascimento, "Rua outro endereco 12",
		    "Thiago Ferreira Patricio", numCreci);
	} catch (Exception e) {
	    Assert.assertEquals("Funcionario invalido", e.getMessage());
	}

    }

    @Test
    public void testaExibeFuncionarioPorCreci() throws Exception {
	String cpf = "10120230344";
	Calendar dataNascimento = new GregorianCalendar(1991, 1, 18);
	String endereco = "Rua 12 De Outubro";
	String nome = "Thiago Ferreira";
	String numCreci = "00111";

	controlFunc1.adicionaFuncionario(cpf, dataNascimento, endereco, nome,
		numCreci);

	Assert.assertEquals("Nome: Thiago Ferreira\n" + "Creci: 00111\n"
		+ "Cpf: 101.202.303-44\n" + "Data de Nascimento: 18/02/1991\n"
		+ "Endereco: Rua 12 De Outubro", controlFunc1
		.exibeFuncionarioPorCreci(numCreci));
    }

    @Test(expected = Exception.class)
    public void testaExibeFuncionarioPorCreciInvalido() throws Exception {
	// colecao vazia
	controlFunc1.exibeFuncionarioPorCreci("00111");

	String cpf = "10120230344";
	Calendar dataNascimento = new GregorianCalendar(1991, 1, 18);
	String endereco = "Rua 12 De Outubro";
	String nome = "Thiago Ferreira";
	String numCreci = "00111";

	controlFunc1.adicionaFuncionario(cpf, dataNascimento, endereco, nome,
		numCreci);
	// creci invalido
	controlFunc1.exibeFuncionarioPorCreci(null);
	controlFunc1.exibeFuncionarioPorCreci("123sa");

    }

 
    @Test
    public void testaExibeFuncionarioPorCpf() throws Exception {
	String cpf = "10120230344";
	Calendar dataNascimento = new GregorianCalendar(1991, 1, 18);
	String endereco = "Rua 12 De Outubro";
	String nome = "Thiago Ferreira";
	String numCreci = "00111";

	controlFunc1.adicionaFuncionario(cpf, dataNascimento, endereco, nome,
		numCreci);

	Assert.assertEquals("Nome: Thiago Ferreira\n" + "Creci: 00111\n"
		+ "Cpf: 101.202.303-44\n" + "Data de Nascimento: 18/02/1991\n"
		+ "Endereco: Rua 12 De Outubro", controlFunc1
		.exibeFuncionarioPorCpf("101.202.303-44"));
    }

    @Test(expected = Exception.class)
    public void testaExibeFuncionarioPorCpfInvalido() throws Exception {
	// colecao vazia
	controlFunc1.exibeFuncionarioPorCpf("12345678910");

	String cpf = "10120230344";
	Calendar dataNascimento = new GregorianCalendar(1991, 1, 18);
	String endereco = "Rua 12 De Outubro";
	String nome = "Thiago Ferreira";
	String numCreci = "00111";

	controlFunc1.adicionaFuncionario(cpf, dataNascimento, endereco, nome,
		numCreci);
	// creci invalido
	controlFunc1.exibeFuncionarioPorCreci(null);
	controlFunc1.exibeFuncionarioPorCreci("123sa");

    }

}
