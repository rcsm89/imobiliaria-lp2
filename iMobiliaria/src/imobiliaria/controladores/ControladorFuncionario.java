package imobiliaria.controladores;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import imobiliaria.processamento.ColecaoFuncionario;
import imobiliaria.processamento.Funcionario;
import imobiliaria.util.VerificaInvalido;

/**
 * 
 * @author thiagofp
 * 
 */
public class ControladorFuncionario extends ColecaoFuncionario {

	/**
	 * Classe que modifica um funcionario
	 * 
	 * @param funcionario
	 *            Funcionario a ser modificado
	 * @param cpf
	 *            Cpf do funcionario
	 * @param dataNascimento
	 *            Data do nascimento do funcionario
	 * @param endereco
	 *            Endereco do funcionario
	 * @param nome
	 *            Nome do funcionario
	 * @param creci
	 *            Creci do funcionario
	 * @throws Exception
	 *             Se um dos parametros for invalido
	 */
	public void modificaFuncionario(Funcionario funcionario, String cpf,
			Calendar dataNascimento, String endereco, String nome, String creci)
			throws Exception {
		if (funcionario == null) {
			throw new IllegalArgumentException("Funcionario invalido");
		}
		int NUMDIGITOSCPF = 11;
		if (VerificaInvalido.numeroFormatado(cpf, NUMDIGITOSCPF)
				|| VerificaInvalido.data(dataNascimento)
				|| VerificaInvalido.endereco(endereco)
				|| VerificaInvalido.nome(nome)
				|| VerificaInvalido.numero(creci)) {
			throw new IllegalArgumentException("Entrada Invalida");
		}
		funcionario.setCpf(cpf);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setEndereco(endereco);
		funcionario.setNome(nome);
	}

	/**
	 * Imprime uma listagem com dados dos funcionarios
	 * 
	 * @param listaFunc
	 *            Lista a ser imprimida
	 */
	public void imprimeListagemFuncionario(List<Funcionario> listaFunc) {

		if (listaFunc.isEmpty()) {
			System.out.println("Nada a ser listado");
			return;
		}

		for (Funcionario func : listaFunc) {
			System.out.println("Cpf: " + func.getCpf() + " - " + func.getNome()
					+ "Creci: " + func.getCreci() + "\n - Data de Nascimento: "
					+ func.getDataNascimento() + "\nEndereco: "
					+ func.getEndereco() + "\n");
		}
	}

	/**
	 * Retorna um mapa com o nome do funcionario e o total de vendas como chave
	 * e valor
	 * @return
	 * 		Mapa retornado
	 */
	public HashMap<String, Double> listaTotaisDeVendas() {
		HashMap<String, Double> funcSalario = new HashMap<String, Double>();
		double valorDeVendas;

		for (Funcionario funcionario : getColecaoFuncionarios()) {
			funcionario.resetaImoveisVendidosMes();
			valorDeVendas = funcionario.getTotalDeVendas();
			funcSalario.put(funcionario.getNome(), valorDeVendas);
		}
		return funcSalario;
	}

	/**
	 * Retorna um string contendo dados de um funcionario obtido pelo seu creci
	 * @param creci
	 * 		Creci no funcionario
	 * @return
	 * 		Dados do funcionario desejado
	 */
	public String exibeFuncPorCreci(String creci) {
		if (VerificaInvalido.numero(creci)) {
			throw new IllegalArgumentException("Creci Invalido");
		}
		if (getColecaoFuncionarios().isEmpty()) {
			throw new IllegalArgumentException("Colecao Funcionarios Vazia");
		}

		String funcRetornado = "";
		for (Funcionario func : getColecaoFuncionarios()) {
			if (func.getCreci().equals(creci)) {
				funcRetornado = "Nome : " + func.getNome() + "\nCRECI: "
						+ func.getCreci() + "\nCpf: " + func.getCpf()
						+ "\nData de Nascimento" + func.getDataNascimento()
						+ "\nEndereco: " + func.getEndereco();
			}
		}
		return funcRetornado;
	}

	/**
	 * Retorna um string contendo dados de um funcionario obtido pelo seu cpf
	 * @param cpf
	 * 		Cpf no funcionario
	 * @return
	 * 		Dados do funcionario desejado
	 */
	public String exibeFuncPorCpf(String cpf) {
		int NUMDIGITOSCPF = 11;
		if (VerificaInvalido.numeroFormatado(cpf, NUMDIGITOSCPF)) {
			throw new IllegalArgumentException("Cpf Invalido");
		}
		if (getColecaoFuncionarios().isEmpty()) {
			throw new IllegalArgumentException("Colecao Funcionarios Vazia");
		}

		String funcRetornado = "";
		for (Funcionario func : getColecaoFuncionarios()) {
			if (func.getCpf().equals(cpf)) {
				funcRetornado = "Nome : " + func.getNome() + "\nCRECI: "
						+ func.getCreci() + "\nCpf: " + func.getCpf()
						+ "\nData de Nascimento" + func.getDataNascimento()
						+ "\nEndereco: " + func.getEndereco();
			}
		}
		return funcRetornado;
	}

}