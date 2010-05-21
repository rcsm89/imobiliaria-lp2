package imobiliaria.processamento;
/*
 *Cadastrar imovel
 * 
 */
import imobiliaria.aux.VerificaInvalido;

import java.util.Calendar;

/**
 * Classe que cria um funcionario.
 * 
 * @author Thiago Ferreira
 * @version IT01
 * 
 */
public class Funcionario extends Pessoa {

	private ColecaoImoveis historicoVendido = new ColecaoImoveis();
	private ColecaoImoveis historicoVendidoMes = new ColecaoImoveis();
	private ColecaoImoveis listaDeImoveis = new ColecaoImoveis();
	private ColecaoClientes listaDeClientes = new ColecaoClientes();
	
	private String creci;
	private double totalDeVendas = 0;

	/**
	 * Classe que cria um funcionario
	 * 
	 * @param creci
	 *            Numero do creci
	 * @throws Exception
	 *             Lanca Exception caso algum parametro seja invalido
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
	 * 
	 * @return O numero do creci do funcionario
	 */
	public String getCreci() {
		return creci;
	}
	
	/**
	 * Adiciona uma nova venda no total de vendas
	 * 
	 * @param valorDaVenda
	 *            O valor da venda a ser adicionada
	 * @throws Exception
	 *             Lanca excecao se o valor passado for menor que zero
	 */
	public void adicionaVenda(double valorDaVenda) throws Exception {
		if (valorDaVenda < 0) {
			throw new Exception("Valor da venda invalido");
		}
		totalDeVendas += valorDaVenda;
	}

	/**
	 * Acessa o total de vendas
	 * 
	 * @return 
	 * 		O total de vendas do funcionario
	 */
	public double getTotalDeVendas() {
		return totalDeVendas;
	}

	/**
	 * Adiciona um novo cliente na lista de clientes
	 * 
	 * @param novoCliente
	 *            Cliente a ser adicionado
	 * @return True, se o cliente foi adicionado <br>
	 *  		False, caso contrario
	 */
	public boolean adicionaCliente(Cliente novoCliente) {
		if (listaDeClientes.getClientes().contains(novoCliente)) {
			return false;
		}
		return listaDeClientes.adicionaCliente(novoCliente);
	}
	
	
	/**
	 * Remove um cliente da lista de clientes por nome
	 * @param nome
	 * 		Nome do cliente a ser removido
	 * @return
	 * 		True, se o cliente foi removido <br>
	 *      False, caso contrario
	 * @throws Exception
	 * 		Caso o nome seja invalido
	 */
	
	public boolean removeClientePorNome(String nome) throws Exception {
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
	 * Remove um cliente da lista de clientes  por cpf
	 * 
	 * @param cpf
	 *         Cpf do cliente a ser removido
	 * @return True, se o cliente foi removido <br>
	 *         False, caso contrario
	 * @throws Exception
	 *         Caso o cpf seja invalido
	 */
	public boolean removeClientePorCpf(String cpfCliente) throws Exception {
		if (VerificaInvalido.numeroFormatado(cpfCliente, 11))
			throw new Exception("Cpf invalido");

		for (Cliente c : listaDeClientes.getClientes()) {
			if (c.getCpf() == cpfCliente)
				listaDeClientes.removeCliente(cpfCliente);
			return true;
		}
		return false;
	}
	
	/**
	 * Acessa a lista de clientes
	 * @return
	 * 		Lista de clientes
	 */
	public ColecaoClientes getClientes(){
		return listaDeClientes;
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
	 * Adiciona um imovel na lista de imoveis 
	 * @param imovel
	 * 		Imovel a ser adicionado
	 * @return
	 * 		True, se o imovel foi adicionado
	 * 		False, caso contrario
	 */
	public boolean addImovel(Imovel imovel) {
		if (listaDeImoveis.getImoveis().contains(imovel)) {
			return false;
		}
		return listaDeImoveis.adicionaImovel(imovel);
	}
	
	/**
	 * Acessa a lista de imoveis
	 * 
	 * @return
	 * 		Lista de imoveis
	 */
	public ColecaoImoveis getImoveis() {
		return listaDeImoveis;
	}
	
	/**
	 * Remove um imovel da lista de imovel
	 * @param imovel
	 * 		Imovel a ser removido
	 * @return
	 * 		True, se o imovel foi removido
	 * 		False, caso contrario
	 */
	public boolean removeImovel(Imovel imovel){
		if (!(listaDeImoveis.getImoveis().contains(imovel))) {
			return false;
		}
		return listaDeImoveis.removeImovel(imovel.getRegistroImovel());
	}
	

	/**
	 * Adiciona um imovel na lista de imoveis vendidos
	 * @param imovelVendido
	 *      Imovel vendido a ser adicionado		
	 * @return
	 * 		True, se o imovel foi adicionado
	 * 		False, caso contrario
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
	 * @throws Exception
	 *             Lanca excecao caso imovel ja na lista de vendidos num mes
	 */
	public void addImovelVendidoMes(Imovel imovelVendido) throws Exception {
		if (!(historicoVendidoMes.adicionaImovel(imovelVendido))) {
			throw new Exception("Imovel invalido");
		}
	}

	/**
	 * Reseta a lista de imoveis vendidos num mes
	 */
	public void resetaImoveisVendidosMes() {
		historicoVendidoMes.getImoveis().clear();
	}

	/**
	 * Efetua compra de um imovel
	 * 
	 * @param cliente
	 *            Cliente que efetuara a compra
	 * @param registroImovel
	 *            Registro do imovel a ser comprado
	 * @return True, se o imovel for vendido False, se o imovel ja estiver
	 *         vendido
	 * @throws Exception
	 *             Caso os parametros sejam invalidos
	 */
	public boolean efetuaCompra(Cliente cliente, int registroImovel)
			throws Exception {
		if (registroImovel < 1) {
			throw new Exception("Registro de imovel invalido");
		}

		ColecaoImoveis listaDePedidos = cliente.getPedidos();

		for (Imovel imovel : listaDePedidos.getImoveis()) {
			if (imovel.getRegistroImovel() == registroImovel) {
				if (imovel.getEstadoDoImovel() == EstadoImovel.VENDIDO) {
					return false; // pois ja foi vendido
				}
				imovel.setEstadoDoImovel(EstadoImovel.VENDIDO);
				listaDePedidos.removeImovel(registroImovel);
				addImovelVendido(imovel);
				totalDeVendas += imovel.getValor();
				//caixa += imovel.getValor();
				return true;
			}
		}
		return false;
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