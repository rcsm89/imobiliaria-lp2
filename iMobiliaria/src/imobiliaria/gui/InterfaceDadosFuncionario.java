/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InterfaceDadosFuncionario.java
 *
 * Created on 05/07/2010, 21:00:41
 */

package imobiliaria.gui;
import imobiliaria.entidades.*;
import imobiliaria.controladores.*;
/**
 *
 * @author thiagofp
 */
public class InterfaceDadosFuncionario extends javax.swing.JFrame {
    Funcionario func;
    /** Creates new form InterfaceDadosFuncionario */
    public InterfaceDadosFuncionario(Funcionario func) {
        this.func = func;
        initComponents();
        if (func != null){
            JL_TotVendas.setText( String.valueOf(func.getTotalDeVendas()));
            JTA_InfoFunc.setText( ControladorFuncionario.getInstance().
                    exibeFuncionarioPorCpf(func.getCpf()));
            JTA_ImoveisV.setText(  ControladorImovel.getInstance().listaImoveis(func.getImoveisVendidos()));
            JTA_ImoveisVM.setText( ControladorImovel.getInstance().listaImoveis(func.getImoveisVendidosMes()));
        }

        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JTA_InfoFunc = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        JB_OK = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JL_TotVendas = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTA_ImoveisV = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTA_ImoveisVM = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dados do Funcionário");
        setResizable(false);

        JTA_InfoFunc.setColumns(20);
        JTA_InfoFunc.setEditable(false);
        JTA_InfoFunc.setRows(5);
        JTA_InfoFunc.setText("< Dados do Funcionario >");
        jScrollPane1.setViewportView(JTA_InfoFunc);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel1.setText("Dados do Funcionário");

        JB_OK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/okIcon.png"))); // NOI18N
        JB_OK.setText("OK");
        JB_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_OKActionPerformed(evt);
            }
        });

        jLabel2.setText("Total de Vendas:         R$");

        JL_TotVendas.setText("0");

        JTA_ImoveisV.setColumns(20);
        JTA_ImoveisV.setRows(5);
        JTA_ImoveisV.setText("< Nenhum imóvel vendido >");
        jScrollPane2.setViewportView(JTA_ImoveisV);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Imovéis Vendidos", jPanel1);

        JTA_ImoveisVM.setColumns(20);
        JTA_ImoveisVM.setRows(5);
        JTA_ImoveisVM.setText("< Nenhum imóvel vendido neste mês >");
        jScrollPane3.setViewportView(JTA_ImoveisVM);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Imovéis Vendidos no Mês", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(33, 33, 33)
                                .addComponent(JL_TotVendas))
                            .addComponent(jTabbedPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(JB_OK)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JL_TotVendas))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(JB_OK)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_OKActionPerformed
        dispose();
    }//GEN-LAST:event_JB_OKActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceDadosFuncionario(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_OK;
    private javax.swing.JLabel JL_TotVendas;
    private javax.swing.JTextArea JTA_ImoveisV;
    private javax.swing.JTextArea JTA_ImoveisVM;
    private javax.swing.JTextArea JTA_InfoFunc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
