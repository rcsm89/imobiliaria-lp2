package imobiliaria.controladores;

import java.util.List;

import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.ColecaoClientes;
import imobiliaria.processamento.TipoImovel;

/**
 * Classe ControladorCliente que ira controlar e listar algumas opcoes sobre
 * Cliente
 * 
 * @author Yuri
 * @IT 1
 */
public class ControladorCliente extends ColecaoClientes {

	/**
	 * Metodo Acessador de um Cliente
	 * 
	 * @param cpf
	 *            CPF do Cliente a ser retornado
	 * @return Cliente ou null, caso nao exista
	 */
	public Cliente getCliente(String cpf) {
		for (Cliente c : getClientes()) {
			if (c.getCpf().equals(cpf))
				return c;
		}
		return null;
	}

	/**
	 * Metodo que exibe informacoes de um Cliente
	 * 
	 * @param cpf
	 *            CPF do Cliente a ser exibido
	 * @return String contendo informacoes do Cliente
	 */
	public String exibeCliente(String cpf) {
		Cliente c = getCliente(cpf);

		return "Nome: " + c.getNome() + "\n" + "CPF: " + c.getCpf() + "\n"
				+ "Endereco: " + c.getEndereco() + "Data de Nascimento: "
				+ c.getDataNascimento() + "Preferencia: " + c.getPreferencia();
	}

	/**
	 * Metodo Lista Clientes
	 * 
	 * @return String contendo uma listagem dos clientes
	 */
	public String listaClientes() {
		return listaClientes(getClientesPorOrdemAlfabetica());
	}

	/**
	 * Metodo que lista clientes de uma preferencia
	 * 
	 * @param preferencia
	 *            Preferencia dos clientes a serem listados
	 * @return String contendo listagem de clientes
	 */
	public String listaClientes(TipoImovel preferencia) {
		return listaClientes(getClientes(preferencia));
	}

	/**
	 * Metodo que lista clientes de uma letra inicial
	 * 
	 * @param letraInicial
	 *            Letra Inicial dos clientes a serem listados
	 * @return String contendo listagem de clientes iniciados pela letra
	 */

	public String listaClientes(String letraInicial) {
		return listaClientes(getClientesPorLetraInicial(letraInicial));
	}

	private String listaClientes(List<Cliente> listaDeClientes) {
		String saida = "";

		for (Cliente c : listaDeClientes) {
			saida += "Nome: " + c.getNome() + " - CPF: " + c.getCpf()
					+ " - Data de Nascimento: " + c.getDataNascimento() + "\n"
					+ "Endereco: " + c.getEndereco() + " - Preferencia: "
					+ c.getPreferencia() + "\n\n";
		}

		return saida;
	}
}
