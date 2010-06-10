package imobiliaria.controladores;

import imobiliaria.entidades.Aluguel;
import imobiliaria.entidades.Cliente;
import imobiliaria.entidades.Funcionario;
import imobiliaria.entidades.Imovel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ControladorAlugueis implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private static ControladorAlugueis controladorAlugueisUnico = new ControladorAlugueis();
	
	private ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
	
	
	private ControladorAlugueis() { }
	
	public static ControladorAlugueis getInstance() {
		return controladorAlugueisUnico;
	}
	
	/* Metodos de Aluguel */

	public boolean adicionaAluguel(Cliente alugante, Funcionario vendedor, Imovel imovelAlugado) {
		if (alugante == null || vendedor == null || imovelAlugado == null)
			throw new IllegalArgumentException("Parametros invalidos");
		
		Aluguel aluguel = new Aluguel(alugante, vendedor, imovelAlugado);
		
		return alugueis.add(aluguel);
	}
	
	public boolean removeAluguel(Imovel imovelDoAluguel) {
		
		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado() == imovelDoAluguel)
				return alugueis.remove(a);
		}
		return false;
	}
	
	public Aluguel getAluguel(Imovel imovelAlugado) {
		for (Aluguel a : alugueis) {
			if (a.getImovelAlugado().equals(imovelAlugado)) {
				return a;
			}
		}
		return null;
	}

	public void adquireAlugueis() {

		Iterator<Aluguel> itAluguel = alugueis.iterator();
		
		ControladorTransacoes controladorTransacoes = ControladorTransacoes.getInstance();
		
		while (itAluguel.hasNext()) {
			
			Aluguel aluguel = itAluguel.next();
			
			controladorTransacoes.adicionaAoCaixa(aluguel.getImovelAlugado().getValor());
			
			controladorTransacoes.adicionaTransacao(aluguel.getAlugante().getCpf(),
					aluguel.getVendedor().getCreci(),
						aluguel.getImovelAlugado().getValor());
			
			aluguel.getVendedor().addImovelVendido(aluguel.getImovelAlugado());
			
		}
	}

}
