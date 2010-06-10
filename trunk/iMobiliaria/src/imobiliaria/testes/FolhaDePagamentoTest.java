package imobiliaria.testes;

import java.util.HashMap;

import junit.framework.Assert;

import imobiliaria.entidades.FolhaDePagamento;

import org.junit.Before;
import org.junit.Test;

public class FolhaDePagamentoTest {
	
	private FolhaDePagamento folha1;
	private FolhaDePagamento folha2;

	@Before
	public void setUp() throws Exception {
		
		HashMap<String, Double> vendas = new HashMap<String,Double>();
		
		vendas.put("Funcionario 1", 500.0);
		vendas.put("Funcionario 2", 1500.0);
		vendas.put("Funcionario 3", 2000.0);
		vendas.put("Funcionario 4", 4000.0);
		vendas.put("Funcionario 5", 3500.0);
		
		folha1 = new FolhaDePagamento(vendas, 11500); 
		
		HashMap<String, Double> vendas2 = new HashMap<String,Double>();
		
		vendas2.putAll(vendas);
		vendas2.put("Funcionario 6", 10000.0);
		vendas2.put("Funcionario 7", 15000.0);
		vendas2.put("Funcionario 8", 8000.0);
		
		folha2 = new FolhaDePagamento(vendas2, 16500);
		
	}
	
	@Test
	public void excecoesNoConstrutor() {
		
		try {
			folha1 = new FolhaDePagamento(null, 10000);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Parametros invalidos", e.getMessage());
		}
		
		
	}

	@Test
	public void testGetFolhaDePagamentoString() {
		
		Assert.assertEquals("Funcionario 1 - Salario: 500.0\n" +
				"Funcionario 2 - Salario: 1500.0\n" +
				"Funcionario 3 - Salario: 2000.0\n" +
				"Funcionario 4 - Salario: 4000.0\n" +
				"Funcionario 5 - Salario: 3500.0\n" +
				"Saldo Anterior: 23000.0 - Despesas: 11500.0 - Novo Saldo: 11500.0"
				, folha1.getFolhaDePagamentoString());
		
		Assert.assertEquals("Funcionario 1 - Salario: 500.0\n" +
				"Funcionario 2 - Salario: 1500.0\n" +
				"Funcionario 3 - Salario: 2000.0\n" +
				"Funcionario 4 - Salario: 4000.0\n" +
				"Funcionario 5 - Salario: 3500.0\n" +
				"Funcionario 6 - Salario: 10000.0\n" +
				"Funcionario 7 - Salario: 15000.0\n" +
				"Funcionario 8 - Salario: 8000.0\n" +
				"Saldo Anterior: 61000.0 - Despesas: 44500.0 - Novo Saldo: 16500.0"
				, folha2.getFolhaDePagamentoString());
	}

}
