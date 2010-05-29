package imobiliaria.userInterface;

import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.Sistema;
import imobiliaria.processamento.TipoImovel;
import imobiliaria.util.FormataEntrada;
import imobiliaria.util.MetodoEntrada;

import java.util.Calendar;

public class OperacoesInterfaceTextual {

    private String lineSep;
    private Sistema sis;

    public OperacoesInterfaceTextual(Sistema sis) {
	this.lineSep = System.getProperty("line.separator");
	this.sis = sis;
    }

    // METODOS DA INTERFACE DE CLIENTE

    protected void verificarDadosPessoais(String cpf) {
	sis.controladorClientes().exibeCliente(cpf);
    }

    protected void historicoCompras() {
    }

    protected void listarImoveis() {
    }

    protected void fazerPedido() {
    }

    // Metodos da Interface

    protected void cadastroDeClientes() {

	String nome, cpf, endereco;
	Calendar dataNascimento;
	TipoImovel preferencia;

	boolean repeteCadastro;
	int opcaoImovel = 0;
	do {
	    System.out.println(lineSep
		    + "----------------- Cadastro de Cliente -----------------"
		    + lineSep);

	    nome = MetodoEntrada.recebeString("Digite o Nome do Cliente: ");

	    cpf = MetodoEntrada.recebeString("CPF (Apenas os 11 digitos): ");

	    System.out.print("Data de Nascimento (dd/MM/AAAA): ");
	    dataNascimento = MetodoEntrada.recebeData();

	    endereco = MetodoEntrada.recebeString("Endereco: ");

	    System.out.print("\nQual sua preferência de imóvel?\n"
		    + "1. Casa\n" + "2. Apartamento\n" + "3. Terreno\n"
		    + "---------------\n");

	    opcaoImovel = MetodoEntrada.recebeInt();

	    try {
		preferencia = TipoImovel.values()[opcaoImovel];
	    } catch (IndexOutOfBoundsException erro) {
		preferencia = null;
	    }
	    try {

		sis.controladorClientes().adicionaCliente(cpf, dataNascimento,
			endereco, nome, preferencia);

	    } catch (Exception erro) {
		System.out.println("\n=========== AVISO =============\n"
			+ erro.getMessage());
		repeteCadastro = true;
	    }
	    repeteCadastro = false;

	} while (repeteCadastro);

	Cliente novoCliente = sis.controladorClientes().getCliente(
		FormataEntrada.cpf(cpf));

	System.out.println(lineSep
		+ "=========== Cadastro Efetuado com Sucesso ============="
		+ lineSep + lineSep + "Login default: "
		+ novoCliente.getLogin() + lineSep + "Senha default: "
		+ novoCliente.getSenha() + lineSep + lineSep
		+ "        Voce podera redefinir suas preferencias\n"
		+ "        posteriormente quando acessar sua conta\n");
    }

}
