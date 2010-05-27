package imobiliaria.controladores;

import java.util.List;

import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.ColecaoClientes;
import imobiliaria.processamento.TipoImovel;

public class ControladorCliente extends ColecaoClientes {

	public Cliente getCliente(String cpf) {
		for (Cliente c : getClientes()) {
			if (c.getCpf().equals(cpf))
				return c;
		}
		return null;
	}
	
	public String exibeCliente(String cpf) {
		Cliente c = getCliente(cpf);
		
		return "Nome: " + c.getNome() + "\n" +
		"CPF: " + c.getCpf() + "\n" +
		"Endereco: " + c.getEndereco() +
		"Data de Nascimento: " + c.getDataNascimento() +
		"Preferencia: " + c.getPreferencia();
	}
	
	public String listaClientes() {
		return listaClientes(getClientesPorOrdemAlfabetica());
	}
	
	public String listaClientes(TipoImovel preferencia) {
		return listaClientes(getClientes(preferencia));
	}
	
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
