/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InterfaceCliente.java
 *
 * Created on 04/07/2010, 14:30:19
 */

package imobiliaria.gui;

import imobiliaria.entidades.*;
import imobiliaria.controladores.*;

/**
 * @author bruno
 */
public class InterfaceCliente extends javax.swing.JFrame {

	//Atributos
	Cliente cliente;
    /** Creates new form InterfaceCliente */
    public InterfaceCliente(Cliente cliente) {
    	this.cliente = cliente;
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Bem Vindo Cliente");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

    	final String formataNomeInicio = "- ";
    	final String formataNomeFim = " -";
    	String nomeFormatado = formataNomeInicio + cliente.getNome() + formataNomeFim;
        JL_Cliente = new javax.swing.JLabel();
        JL_NomeDeCliente = new javax.swing.JLabel();
        JB_VerificaDadosCliente = new javax.swing.JButton();
        JB_Desloga = new javax.swing.JButton();
        JTP_PainelPedidAlugCompras = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        JLI_PedidosCliente = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        JLI_AlugueisCliente = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        JLI_HistComprasCliente = new javax.swing.JList();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JL_Cliente.setText("CLIENTE");
        
        JL_NomeDeCliente.setText(nomeFormatado);

        JB_VerificaDadosCliente.setText("Verificar Seus Dados");
        JB_VerificaDadosCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/procuraIcon.png")));
        JB_VerificaDadosCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_VerificaDadosClienteActionPerformed(evt);
            }
        });

        JB_Desloga.setText("Deslogar");
        JB_Desloga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/backIcon.png")));
        JB_Desloga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_DeslogaActionPerformed(evt);
            }
        });

        JLI_PedidosCliente.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Não há pedidos na sua conta" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(JLI_PedidosCliente);

        JTP_PainelPedidAlugCompras.addTab("Pedidos", jScrollPane3);

        JLI_AlugueisCliente.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(JLI_AlugueisCliente);

        JTP_PainelPedidAlugCompras.addTab("Alugueis", jScrollPane2);

        JLI_HistComprasCliente.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(JLI_HistComprasCliente);

        JTP_PainelPedidAlugCompras.addTab("Histórico De Compras", jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(JB_Desloga, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addComponent(JB_VerificaDadosCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addGap(54, 54, 54))
            .addGroup(layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(JL_Cliente)
                .addContainerGap(323, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JTP_PainelPedidAlugCompras, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addGap(31, 31, 31))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(223, Short.MAX_VALUE)
                .addComponent(JL_NomeDeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JL_Cliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JL_NomeDeCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(JTP_PainelPedidAlugCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_Desloga, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JB_VerificaDadosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void JB_VerificaDadosClienteActionPerformed(java.awt.event.ActionEvent evt) {
        setVisible(false);
        new InterfaceDadosCliente(cliente).setVisible(true);

    }

    private void JB_DeslogaActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaPrincipal().setVisible(true);
        dispose();
    }

    private Imovel[] mostraPedidos(Cliente cliente){
        Imovel[] pedidos = ControladorPedidos.getInstance().listaDePedidosGUI(cliente.getCpf());
        return pedidos;
    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceCliente(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton JB_Desloga;
    private javax.swing.JButton JB_VerificaDadosCliente;
    private javax.swing.JList JLI_AlugueisCliente;
    private javax.swing.JList JLI_HistComprasCliente;
    private javax.swing.JList JLI_PedidosCliente;
    private javax.swing.JLabel JL_Cliente;
    private javax.swing.JLabel JL_NomeDeCliente;
    private javax.swing.JTabbedPane JTP_PainelPedidAlugCompras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration
    
}
