package imobiliaria.userInterface;

import imobiliaria.processamento.Area;
import imobiliaria.processamento.Cliente;
import imobiliaria.processamento.EstadoImovel;
import imobiliaria.processamento.Funcionario;
import imobiliaria.processamento.Imovel;
import imobiliaria.processamento.Sistema;
import imobiliaria.processamento.TipoContratual;
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

	protected void verificarDadosPessoais(Cliente cliente) {
		System.out.println(sis.controladorClientes().exibeCliente(
				cliente.getCpf()));
	}

	protected void historicoCompras(Cliente cl) {
		int num = 1;
		System.out.println(lineSep);
		for (Imovel imovel : cl.getHistoricoCompras().getImoveis()) {
			System.out.println(String.format("%2d. %s", num, imovel));
			num++;
		}
		System.out.println(lineSep);
	}

	protected void listarImoveis() {
		System.out.println(lineSep
				+ "======================= Listagem de Imoveis ======================="
				+ lineSep + sis.controladorImoveis().listaImoveis(EstadoImovel.A_VENDA));
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

			System.out.print("\nQual sua preferência de imovel?\n"
					+ "1. Casa\n" + "2. Apartamento\n" + "3. Terreno\n"
					+ "---------------\n");

			opcaoImovel = MetodoEntrada.recebeInt();

			try {
				preferencia = TipoImovel.values()[opcaoImovel];
			} catch (IndexOutOfBoundsException erro) {
				preferencia = null;
			}
			try {

				if (sis.controladorClientes().adicionaCliente(cpf, dataNascimento,
						endereco, nome, preferencia)) {
					repeteCadastro = false;
				} else {
					throw new Exception("Cliente ja existente");
				}
				
			} catch (Exception erro) {
				System.out.println("\n=========== AVISO =============\n"
						+ erro.getMessage());
				repeteCadastro = true;
			}

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

	protected void cadastroDeFuncionario() {
		
		Funcionario novoFunc = null;

		String nome, cpf, endereco, creci;
		Calendar dataNascimento;

		boolean repeteCadastro;
		do {
			System.out.println(lineSep
					+ "--------------- Cadastro de Funcionario ---------------"

					+ lineSep);

			nome = MetodoEntrada.recebeString("Nome do Funcionario: ");

			cpf = MetodoEntrada.recebeString("CPF (Apenas os 11 digitos): ");

			System.out.print("Data de Nascimento (dd/MM/AAAA): ");
			dataNascimento = MetodoEntrada.recebeData();

			endereco = MetodoEntrada.recebeString("Endereco: ");
			creci = MetodoEntrada.recebeString("Numero do CRECI: ");

			try {
				if (sis.controladorFuncionarios().adicionaFuncionario(cpf,
						dataNascimento, endereco, nome, creci)) {
					
					novoFunc = sis.controladorFuncionarios().getFuncionario(creci);

					repeteCadastro = false;
				} else {
					throw new Exception("Funcionario ja existente");
				}

			} catch (Exception erro) {
				System.out.println("\n=========== AVISO =============\n"
						+ erro.getMessage());
				repeteCadastro = true;
			}

		} while (repeteCadastro);
		

		System.out.println(lineSep
				+ "=========== Cadastro Efetuado com Sucesso ============="
				+ lineSep + lineSep + "Login default: " + novoFunc.getLogin()
				+ lineSep + "Senha default: " + novoFunc.getSenha() + lineSep
				+ lineSep + "        Voce podera redefinir suas preferencias\n"
				+ "        posteriormente quando acessar sua conta\n");
	}

	protected void cadastroDeImovel() {

		Imovel novoImovel = null;
		String nome, endereco;

		TipoImovel tipoDoImovel;
		TipoContratual tipoContratual;
		Area area = null;
		double valor, comprimento, largura;

		boolean repeteCadastro;

		int opcaoImovel;
		int opcaoContrato;

		do {
			System.out.println(lineSep
					+ "------------------ Cadastro de Imovel -----------------"
					+ lineSep);

			nome = MetodoEntrada.recebeString("Descricao: ");
			endereco = MetodoEntrada.recebeString("Endereco: ");

			// TIPO DO IMOVEL
			System.out
					.print("\nQual eh o tipo do imovel?\n" + "1. Casa\n"
							+ "2. Apartamento\n" + "3. Terreno\n"
							+ "---------------\n");

			opcaoImovel = MetodoEntrada.recebeInt();

			try {
				tipoDoImovel = TipoImovel.values()[opcaoImovel];
			} catch (IndexOutOfBoundsException erro) {
				tipoDoImovel = null;
			}

			// TIPO DO CONTRATO
			System.out.print("\nQual eh o tipo do contrato?\n" + "1. Aluguel\n"
					+ "2. Venda\n" + "---------------\n");

			opcaoContrato = MetodoEntrada.recebeInt();

			try {
				tipoContratual = TipoContratual.values()[opcaoContrato];
			} catch (IndexOutOfBoundsException erro) {
				tipoContratual = null;
			}

			// DIMENSOES DO IMOVEL
			System.out.print("\nQuais as dimensoes da area?\n"
					+ "Comprimento: ");
			comprimento = MetodoEntrada.recebeDouble();
			System.out.print("Largura: ");
			largura = MetodoEntrada.recebeDouble();

			// PRECO DO IMOVEL
			System.out.print("\nQual o preco do imovel? R$ ");
			valor = MetodoEntrada.recebeDouble();

			// CRIACAO/CADASTRO DO IMOVEL
			try {
				area = new Area(comprimento, largura);
				novoImovel = new Imovel(nome, endereco, valor, area,
						tipoDoImovel, tipoContratual);
				sis.controladorImoveis().addImovel(novoImovel);
				repeteCadastro = false;

			} catch (Exception erro) {
				System.out.println("\n=========== AVISO =============\n"
						+ erro.getMessage());
				repeteCadastro = true;
			}

		} while (repeteCadastro);

		System.out.println(lineSep
				+ "======= Cadastro De Imovel Efetuado com Sucesso ======="
				+ lineSep);
	}
}
