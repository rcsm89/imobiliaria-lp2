/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastrarImovel.java
 *
 * Created on 06/07/2010, 18:49:01
 */

package imobiliaria.gui;

import imobiliaria.auxiliar.TipoContratual;
import imobiliaria.auxiliar.TipoImovel;
import imobiliaria.controladores.ControladorImovel;
import imobiliaria.entidades.Area;
import javax.swing.JOptionPane;

/**
 *
 * @author jeanderson
 */
public class CadastrarImovel extends javax.swing.JFrame {

    /** Creates new form CadastrarImovel */
    public CadastrarImovel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jT_Descricao = new javax.swing.JTextField();
        jT_Endereco = new javax.swing.JTextField();
        jT_Preco = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jR_Casa = new javax.swing.JRadioButton();
        jR_Apartamento = new javax.swing.JRadioButton();
        jR_Terreno = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jR_VENDA = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jT_Comprimento = new javax.swing.JTextField();
        jT_Largura = new javax.swing.JTextField();
        jB_EfetuarCadastro = new javax.swing.JButton();
        jB_Voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("iMobiliaria");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        jLabel1.setText("Cadastramento de Imóvel");
        setLocationRelativeTo(null);

        jLabel2.setText("Descrição:");

        jLabel3.setText("Endereço:");

        jLabel4.setText("Preço:    R$");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Tipo de Imóvel ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup1.add(jR_Casa);
        jR_Casa.setText("Casa");

        buttonGroup1.add(jR_Apartamento);
        jR_Apartamento.setSelected(true);
        jR_Apartamento.setText("Apartamento");

        buttonGroup1.add(jR_Terreno);
        jR_Terreno.setText("Terreno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jR_Apartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jR_Terreno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jR_Casa)
                        .addContainerGap(79, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jR_Apartamento, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jR_Terreno, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jR_Casa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        jLabel5.setText("Preencha os dados abaixo, para cadastrar:");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Tipo do Contrato ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        buttonGroup2.add(jR_VENDA);
        jR_VENDA.setSelected(true);
        jR_VENDA.setText("Venda");

        buttonGroup2.add(jRadioButton2);
        jRadioButton2.setText("Aluguel");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jR_VENDA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jR_VENDA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Dimensões  ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel6.setText("Comprimento: ");

        jLabel7.setText("Largura:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jT_Largura, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                    .addComponent(jT_Comprimento, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jT_Comprimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jT_Largura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jT_Comprimento, jT_Largura});

        jB_EfetuarCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/okIcon.png"))); // NOI18N
        jB_EfetuarCadastro.setText("Efetuar Cadastro");
        jB_EfetuarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_EfetuarCadastroActionPerformed(evt);
            }
        });

        jB_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/backIcon.png"))); // NOI18N
        jB_Voltar.setText("Voltar");
        jB_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_VoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(189, 189, 189))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jT_Endereco, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                            .addComponent(jT_Descricao, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                            .addComponent(jT_Preco, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(78, 78, 78))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jB_Voltar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jB_EfetuarCadastro))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jT_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jT_Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jT_Preco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jB_EfetuarCadastro)
                    .addComponent(jB_Voltar))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jB_EfetuarCadastro, jB_Voltar});

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-640)/2, (screenSize.height-480)/2, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void jB_EfetuarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_EfetuarCadastroActionPerformed
        // TODO add your handling code here:
        String descricao = jT_Descricao.getText();
        String endereco = jT_Endereco.getText();

        double preco = -10;
        double largura = 0;
        double comprimento = 0;
        Area area = null;

        TipoImovel tipoImovel = null;
        if (jR_Apartamento.isSelected())
            tipoImovel = TipoImovel.APARTAMENTO;
        if (jR_Casa.isSelected())
            tipoImovel = TipoImovel.CASA;
        else
            tipoImovel = TipoImovel.TERRENO;

        TipoContratual tipoContratual = null;
        if (jR_VENDA.isSelected())
            tipoContratual = TipoContratual.VENDA;
        else
            tipoContratual = TipoContratual.ALUGUEL;

        try {
            preco = Double.parseDouble(jT_Preco.getText());
            largura = Double.parseDouble(jT_Largura.getText());
            comprimento = Double.parseDouble(jT_Comprimento.getText());
            area = new Area(comprimento, largura);

        } catch (Exception erro) {
            jT_Preco.setText("Valor invalido");
            jT_Largura.setText("Valor invalido");
            jT_Comprimento.setText("Valor invalido");

        }
        try {
            ControladorImovel.getInstance().addImovel(endereco, endereco, preco, area, tipoImovel, tipoContratual);

        } catch (Exception erro) {
                 JOptionPane.showMessageDialog(null, "Erro ao Efetuar Cadastro:\n\n"
                    + erro.getMessage(),
                    "Cadastro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jB_EfetuarCadastroActionPerformed

    private void jB_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_VoltarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jB_VoltarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastrarImovel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jB_EfetuarCadastro;
    private javax.swing.JButton jB_Voltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jR_Apartamento;
    private javax.swing.JRadioButton jR_Casa;
    private javax.swing.JRadioButton jR_Terreno;
    private javax.swing.JRadioButton jR_VENDA;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jT_Comprimento;
    private javax.swing.JTextField jT_Descricao;
    private javax.swing.JTextField jT_Endereco;
    private javax.swing.JTextField jT_Largura;
    private javax.swing.JTextField jT_Preco;
    // End of variables declaration//GEN-END:variables

}
