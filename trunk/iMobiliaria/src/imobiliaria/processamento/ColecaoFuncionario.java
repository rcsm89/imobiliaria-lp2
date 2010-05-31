package imobiliaria.processamento;

import imobiliaria.util.VerificaInvalido;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de colecao de funcionarios
 * 
 * @author thiagofp
 * @version IT01
 */
public class ColecaoFuncionario implements Serializable {

	private static final long serialVersionUID = 1L;
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
	 * Retorna quanto funcionarios uma colecao possui
	 * 
	 * @return Numero de funcionarios
	 */
	public int getNumFuncionarios() {
		return colecaoFuncionarios.size();
	}

	/**
	 * Retorna uma colecao de funcionarios que tenha um mesmo nome dado
	 * 
	 * @param nome
	 *            Nome do funcionario
	 * 
	 * @return Lista contendo os funcionarios de acordo com o nome dado
	 * 
	 * @throws Exception
	 *             Se o nome for invalido
	 */

	public List<Funcionario> getFuncionarioPorNome(String nome)
			throws Exception {
		if (VerificaInvalido.nome(nome)) {
			throw new Exception("Nome Invalido");
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
	 * Retorna um funcionario que tenha um mesmo creci dado
	 * 
	 * @param creci
	 *            Creci do funcionario
	 * 
	 * @return Funcionario procurado
	 * 
	 * @throws Exception
	 *             Se o creci for invalido
	 */
	public Funcionario getFuncionario(String creci) throws Exception {
		if (VerificaInvalido.numero(creci)) {
			throw new Exception("Creci Invalido");
		}

		Funcionario funcRetornado = null;
		for (Funcionario func : colecaoFuncionarios) {
			if (func.getCreci().equals(creci)) {
				funcRetornado = func;
			}
		}
		return funcRetornado;
	}

	/**
	 * Metodo que adiciona um Funcionario na Colecao
	 * 
	 * @param funcionarioASerAdicionado
	 *            Funcionario a ser Adicionado
	 * @return True - Se ele for adicionado<br>
	 *         False - Se ele ja existir na colecao
	 */
	public boolean adicionaFuncionario(Funcionario funcionarioASerAdicionado) {
		if (funcionarioASerAdicionado == null){
			throw new IllegalArgumentException("Funcionario Invalido");
		}
		
		if (colecaoFuncionarios.contains(funcionarioASerAdicionado)) {
			return false;
		}

		return colecaoFuncionarios.add(funcionarioASerAdicionado);
	}

	/**
	 * Remove um funcionario da colecao apartir do seu creci
	 * 
	 * @param creci
	 *            Creci do funcionario a ser removido
	 * @return True, se o funcionario foi removido<br>
	 *         False, caso contrario
	 * @throws Exception
	 *             Caso o creci seja invalido
	 */
	public boolean removeFuncionario(String creci) throws Exception {
		if (VerificaInvalido.numero(creci)) {
			throw new Exception("Creci Invalido");
		}
		for (Funcionario func : colecaoFuncionarios) {
			if (func.getCreci().equals(creci)) {
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
	 * @return True, se o funcionario foi removido<br>
	 *         False, caso contrario
	 * @throws Exception
	 *             Caso o nome seja invalido
	 */
	public boolean removeFuncionarioPorNome(String nome) throws Exception {
		if (VerificaInvalido.nome(nome)) {
			throw new Exception("Nome Invalido");
		}
		for (Funcionario func : colecaoFuncionarios) {
			if (func.getNome().equals(nome)) {
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
