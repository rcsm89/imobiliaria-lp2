package imobiliaria.userInterface;

/**
 * Main do Sistema
 * 
 * @author Jeanderson Barros Candido
 * @version IT01
 * 
 */
public class Main {

    /*
     * Posteriormente sera criada uma classe GUI onde tudo ocorrera na mesma
     * maneira. O main chamara um metodo principal, e tudo ficara a cargo da
     * classe de interface
     */
    public static void main(String[] args) {

	InterfaceTextual ui = new InterfaceTextual();
	ui.interfaceComUsuario();
    }
}
