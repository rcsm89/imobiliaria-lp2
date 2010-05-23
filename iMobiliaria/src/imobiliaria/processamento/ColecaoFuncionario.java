package imobiliaria.processamento;

import imobiliaria.util.VerificaInvalido;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe de colecao de funcionarios
 * 
 * @author thiagofp
 * @version IT01
 */
public class ColecaoFuncionario {

	private List<Funcionario> colecaoFuncionarios = new ArrayList<Funcionario>();;

	/**
	 * Retorna uma colecao de funcionarios
	 * 
	 * @return A colecao de funcionarios
	 */
	public List<Funcionario> getColecaoFuncionarios() {
		return colecaoFuncionarios;
	}

	/**
	 * Retorna uma colecao de funcionarios que tenha o mesmo nome dado
	 * 
	 * @param nome
	 *            Nome do funcionario
	 * @return Lista contendo os funcionarios de acordo com o nome dado
	 * @throws Exception
	 *             Se o nome for invalido
	 */
	public List<Funcionario> getFuncionarioPorNome(String nome)
			throws Exception {
		if (VerificaInvalido.nome(nome)) {
			throw new Exception("Nome invalido");
		}
		List<Funcionario> colecaoRetornada = new ArrayList<Funcionario>();

		for (Funcionario func : colecaoFuncionarios) {

			if (func.getNome().contains(nome)) {
				colecaoRetornada.add(func);
			}
		}
		return colecaoRetornada;
	}

	/**
	 * Retorna quanto funcionarios uma colecao possui
	 * 
	 * @return Numero de funcionarios
	 */
	public int getNumFuncionarios() {
		return colecaoFuncionarios.size();
	}

	/**
	 * Adiciona um novo funcionario na colecao
	 * 
	 * @param func
	 *            Funcionario a ser adicionado na colecao
	 * @return True, se o funcionario foi adicionado False, caso contrario
	 * @throws Exception
	 *             Caso funcionario seja invalido
	 */
	public boolean addFuncionario(Funcionario func) throws Exception {
		if (func == null) {
			throw new Exception("Funcionario invalido");
		}
		if (colecaoFuncionarios.contains(func)) {
			return false;
		}
		return colecaoFuncionarios.add(func);
	}

	/**
	 * Remove um funcionario da colecao apartir do seu creci
	 * 
	 * @param creci
	 *            Creci do funcionario a ser removido
	 * @return True, se o funcionario foi removido False, caso contrario
	 * @throws Exception
	 *             Caso o creci seja invalido
	 */
	public boolean removeFuncionario(String creci) throws Exception {
		if (VerificaInvalido.numero(creci)) {
			throw new Exception("Creci invalido");
		}
		for (Funcionario func : colecaoFuncionarios) {
			if (func.getCreci() == creci) {
				return colecaoFuncionarios.remove(func);
			}
		}
		return false;
	}

	/**
	 * Remove um funcionario da colecao apartir do seu nome
	 * 
	 * @param nome
	 *            Nome do funcionario a ser removido
	 * @return True, se o funcionario foi removido False, caso contrario
	 * @throws Exception
	 *             Caso o nome seja invalido
	 */
	public boolean removeFuncionarioPorNome(String nome) throws Exception {
		if (VerificaInvalido.nome(nome)) {
			throw new Exception("Nome invalido");
		}
		for (Funcionario func : colecaoFuncionarios) {
			if (func.getNome() == nome) {
				return colecaoFuncionarios.remove(func);
			}
		}
		return false;
	}

	/**
	 * Verifica se duas colecoes sao iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ColecaoFuncionario)) {
			return false;
		}
		ColecaoFuncionario outro = (ColecaoFuncionario) obj;

		return outro.getColecaoFuncionarios().containsAll(colecaoFuncionarios)
				&& colecaoFuncionarios.containsAll(outro
						.getColecaoFuncionarios());
	}

}
