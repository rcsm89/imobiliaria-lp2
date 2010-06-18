package imobiliaria.entidades;

import imobiliaria.auxiliar.TipoLogin;
import imobiliaria.colecoes.ColecaoImoveis;
import imobiliaria.exceptions.ImovelInvalidoException;
import imobiliaria.util.VerificaInvalido;

import java.util.Calendar;

/**
 * Classe que cria um funcionario.
 * 
 * @version IT02
 */
public class Funcionario extends Usuario {

	private static final long serialVersionUID = 1L;
	private ColecaoImoveis historicoVendido = new ColecaoImoveis();
	private ColecaoImoveis historicoVendidoMes = new ColecaoImoveis();
	private String creci;
	private double totalDeVendas = 0;

	/**
	 * Classe que cria um funcionario
	 * 
	 * @param cpf
	 *            CPF do FUncionario
	 * @param dataNascimento
	 *            Data de Nascimento
	 * @param endereco
	 *            Endereco do Funcionario
	 * @param nome
	 *            Nome do Funcionario
	 * @param creci
	 *            Numero do creci
	 * @throws Exception
	 *             Lanca Exception caso algum parametro seja invalido
	 */
	public Funcionario(String cpf, Calendar dataNascimento, String endereco,
			String nome, String creci) throws Exception {
		super(cpf, dataNascimento, endereco, nome, TipoLogin.FUNCIONARIO);
		if (VerificaInvalido.numero(creci))
			throw new IllegalArgumentException("Creci invalido\n");
		this.creci = creci;
	}

	/**
	 * Metodo de acesso ao creci
	 * 
	 * @return O numero do creci do funcionario
	 */
	public String getCreci() {
		return creci;
	}

	/**
	 * Metodo de acesso ao total de vendas
	 * 
	 * @return O total de vendas do funcionario
	 */
	public double getTotalDeVendas() {
		for (Imovel imovel : historicoVendidoMes.getImoveis()) {
			totalDeVendas += imovel.getValor();
		}
		return totalDeVendas;
	}

	/**
	 * Acessa a lista de imoveis vendidos
	 * 
	 * @return Lista de imoveis vendidos
	 */
	public ColecaoImoveis getImoveisVendidos() {
		return historicoVendido;
	}

	/**
	 * Acessa a lista de imoveis vendidos no mes
	 * 
	 * @return Lista de imoveis vendidos no mes
	 */
	public ColecaoImoveis getImoveisVendidosMes() {
		return historicoVendidoMes;
	}

	/**
	 * Adiciona um imovel na lista de imoveis vendidos
	 * 
	 * @param imovelVendido
	 *            Representa o Imovel vendido que sera adicionado
	 * @return True, se o imovel foi adicionado False, caso contrario
	 */
	public boolean addImovelVendido(Imovel imovelVendido) {
		if (historicoVendido.getImoveis().contains(imovelVendido)) {
			return false;
		}
		return historicoVendido.getImoveis().add(imovelVendido);
	}

	/**
	 * Adiciona um movel na lista de imoveis vendidos num mes
	 * 
	 * @param imovelVendido
	 *            Imovel vendido a ser adicionado
	 * @throws ImovelInvalidoException 
	 * @throws Exception
	 *             Lanca excecao caso imovel ja na lista de vendidos num mes
	 */
	public void addImovelVendidoMes(Imovel imovelVendido) throws ImovelInvalidoException {
		historicoVendidoMes.addImovel(imovelVendido);
	}

	/**
	 * Reseta a lista de imoveis vendidos num mes
	 */
	public void resetaImoveisVendidosMes() {
		historicoVendidoMes.getImoveis().clear();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Funcionario)) {
			return false;
		}
		Funcionario outro = (Funcionario) obj;
		if (creci == null) {
			if (outro.creci != null) {
				return false;
			}
		} else if (!creci.equals(outro.creci)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return super.getNome() + "|" + super.getCpf() + "|"
				+ super.getEndereco() + "|" + super.getDataNascimento() + "|"
				+ getCreci();
	}

}