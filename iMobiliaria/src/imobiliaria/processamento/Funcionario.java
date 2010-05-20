package imobiliaria.processamento;

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
	private ColecaoClientes listaDeClientes = new ColecaoClientes();
	private String creci;
	private double totalDeVendas;

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
	 * @return O total de vendas do funcionario
	 */
	public double getTotalDeVendas() {
		return totalDeVendas;
	}

	/**
	 * Adiciona um cliente na lista de clientes
	 * 
	 * @param novoCliente
	 *            Novo cliente a ser adicionado
	 * @throws Exception
	 *             Lanca excecao caso o cliente ja exista na lista
	 */
	public void adicionaCliente(Cliente novoCliente) throws Exception {
		if (!(listaDeClientes.adicionaCliente(novoCliente)))
			throw new Exception("Cliente invalido");
	}

	/**
	 * Remove um cliente da lista de clientes
	 * 
	 * @param nome
	 *            Nome do cliente a ser removido
	 * @return True, se o cliente foi removido <br>
	 *         False, caso contrario
	 * @throws Exception
	 *             Lanca excecao caso o cliente ja exista na lista
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
	 * Adiciona um movel na lista de imoveis vendidos
	 * 
	 * @param imovelVendido
	 *            Imovel vendido a ser adicionado
	 * @throws Exception
	 *             Lanca excecao caso imovel ja na lista de vendidos
	 */
	public void addImovelVendido(Imovel imovelVendido) throws Exception {
		if (!(historicoVendido.adicionaImovel(imovelVendido))) {
			throw new Exception("Imovel invalido");
		}
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
	 * @param cliente
	 * 		Cliente que efetuara a compra
	 * @param registroImovel
	 * 		Registro do imovel a ser comprado
	 * @return
	 * 		True, se o imovel for vendido
	 * 		False, se o imovel ja estiver vendido
	 * @throws Exception
	 *		Caso os parametros sejam invalidos	
	 */		
	public boolean efetuaCompra(Cliente cliente, int registroImovel) throws Exception{
		if (registroImovel < 1){
			throw new Exception("Registro de imovel invalido");
		}
		if (cliente == null){
			throw new Exception("Cliente invalido");
		}
		
		ColecaoImoveis listaDePedidos = cliente.getPedidos();
		
		for(Imovel imovel: listaDePedidos.getImoveis()){
			if (imovel.getRegistroImovel() == registroImovel){
				if (imovel.getEstadoDoImovel() == EstadoImovel.VENDIDO){
					return false; //ja foi vendido
				}
				imovel.setEstadoDoImovel(EstadoImovel.VENDIDO);
				listaDePedidos.removeImovel(registroImovel);
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