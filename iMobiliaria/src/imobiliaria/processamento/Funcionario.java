
/*
 * Lembrando que essa classe pode ser alterada apos a 
 * criação das classes Cliente e ColecaoImoveis
 */

package imobiliaria.processamento;

import java.util.Calendar;

/**
 * Classe que cria um funcionario.
 * @author Thiago Ferreira
 * @version IT01
 * 
 */
public class Funcionario extends Pessoa {

	private ColecaoImoveis historicoVendido;
	private ColecaoImoveis historicoVendidoMes;
	private ColecaoClientes listaDeClientes;
	private String creci;
	private double totalDeVendas;

	/**
	 * Classe que cria um funcionario
	 * @param creci
	 * 		Numero do creci
	 * @throws Exception
	 * 		Lanca Exception caso algum parametro seja invalido
	 */
	public Funcionario(String cpf, Calendar dataNascimento, String endereco,
			String nome, String creci) throws Exception {
		super(cpf, dataNascimento, endereco, nome);
		if (VerificaInvalido.numero(creci))
			throw new Exception("Creci invalido");
		this.creci = creci;
	}

	/**
	 * Acessa o creci
	 * @return
	 * 		O numero do creci do funcionario
	 */
	public String getCreci() {
		return creci;
	}

	/**
	 * Adiciona uma nova venda no total de vendas
	 * @param valorDaVenda
	 * 		O valor da venda a ser adicionada
	 * @throws Exception
	 * 		Lanca excecao se o valor passado for menor que zero
	 */
	public void adicionaVenda(double valorDaVenda) throws Exception {
		if (valorDaVenda < 0) {
			throw new Exception("Valor da venda invalido");
		}
		totalDeVendas += valorDaVenda;
	}

	/**
	 * Acessa o total de vendas
	 * @return
	 * 		O total de vendas do funcionario
	 */
	public double getTotalDeVendas() {
		return totalDeVendas;
	}

	/**
	 * Adiciona um cliente na lista de clientes
	 * @param novoCliente
	 * 		Novo cliente a ser adicionado
	 * @throws Exception
	 * 		Lanca excecao caso o cliente ja exista na lista
	 */
	public void adicionaCliente(Cliente novoCliente) throws Exception {
		if (!(listaDeClientes.adicionaCliente(novoCliente)))
			throw new Exception("Cliente ja existente na lista");
	}

	/**
	 * Remove um cliente da lista de clientes
	 * @param nome
	 * 		Nome do cliente a ser removido
	 * @return
	 * 		True, se o cliente foi removido <br>
	 * 		False, caso contrario 
	 * @throws Exception
	 * 		Lanca excecao caso o cliente ja exista na lista 		
	 */
	public boolean removeCliente(String nome) throws Exception {
		if (VerificaInvalido.nome(nome))
			throw new Exception("Nome invalido");
			
		for (Cliente c : listaDeClientes.getClientes()) {
			if (c.getNome() == nome)
				listaDeClientes.removeCliente(nome);
				return true;
			}
		return false;
	}

	/**
	 * Acessa a lista de imoveis vendidos
	 * @return
	 * 		Lista de imoveis vendidos
	 */
	public ColecaoImoveis getImoveisVendidos() {
		return historicoVendido;
	}

	/**
	 * Acessa a lista de imoveis vendidos no mes
	 * @return
	 * 		Lista de imoveis vendidos no mes
	 */
	public ColecaoImoveis getImoveisVendidosMes() {
		return historicoVendidoMes;
	}

	/**
	 * Adiciona um movel na lista de imoveis vendidos
	 * @param imovelVendido
	 * 		Imovel vendido a ser adicionado
	 * @throws Exception
	 * 		Lanca excecao caso imovel ja na lista de vendidos 
	 */
	public void addImovelVendido(Imovel imovelVendido) throws Exception{
		 if(!(historicoVendido.adicionaImovel(imovelVendido))){
			 throw new Exception("Imovel ja existente na lista");
		 }
	}
	
	/**
	 * Adiciona um movel na lista de imoveis vendidos num mes
	 * @param imovelVendido
	 * 		Imovel vendido a ser adicionado 
	 * @throws Exception
	 * 		Lanca excecao caso imovel ja na lista de vendidos num mes 
	 */
	public void addImovelVendidoMes(Imovel imovelVendido) throws Exception{
		 if(!(historicoVendidoMes.adicionaImovel(imovelVendido))){
			 throw new Exception("Imovel ja existente na lista");
		 }
	}
	
	/**
	 * Reseta a lista de imoveis vendidos num mes
	 */
	public void resetaImoveisVendidosMes(){
		historicoVendidoMes.getImoveis().clear();
	}

	/**
	 * Verifica se dois funcionarios sao iguais
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Funcionario)) {
			return false;
		}
		Funcionario other = (Funcionario) obj;
		if (creci == null) {
			if (other.creci != null) {
				return false;
			}
		} else if (!creci.equals(other.creci)) {
			return false;
		}
		return true;
	}

}
