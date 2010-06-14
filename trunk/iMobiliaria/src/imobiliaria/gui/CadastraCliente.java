/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CadastraCliente.java
 *
 * Created on 06/06/2010, 23:05:21
 */

package imobiliaria.gui;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

import imobiliara.auxiliar.TipoImovel;
import imobiliaria.processamento.Sistema;
   

/**
 * Gui para Cadastramento de clientes
 * @author Thiago Ferreira Patricio
 */
public class CadastraCliente extends javax.swing.JFrame {
    

    /** Creates new form CadastraCliente */
    public CadastraCliente() {
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

        bGroupPreferencia = new javax.swing.ButtonGroup();
        lbNome = new javax.swing.JLabel();
        lbEndereco = new javax.swing.JLabel();
        lbCpf = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        txtFieldNome = new javax.swing.JTextField();
        txtFieldCpf1 = new javax.swing.JTextField();
        txtFieldEndereco = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        rdbCasa = new javax.swing.JRadioButton();
        rdbApart = new javax.swing.JRadioButton();
        rdbTerreno = new javax.swing.JRadioButton();
        lbPonto1 = new javax.swing.JLabel();
        lbPonto2 = new javax.swing.JLabel();
        lbPonto3 = new javax.swing.JLabel();
        txtFieldCpf2 = new javax.swing.JTextField();
        txtFieldCpf3 = new javax.swing.JTextField();
        txtFieldCpf4 = new javax.swing.JTextField();
        txtFieldData1 = new javax.swing.JTextField();
        lbBarra1 = new javax.swing.JLabel();
        txtFieldData2 = new javax.swing.JTextField();
        lbBarra2 = new javax.swing.JLabel();
        txtFieldData3 = new javax.swing.JTextField();
        lbTituloCad = new javax.swing.JLabel();
        lbTexto1 = new javax.swing.JLabel();
        butCadastrar = new javax.swing.JButton();
        butVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iMobiliaria - Cadastramento de Cliente");
        setResizable(false);

        lbNome.setText("Nome:");

        lbEndereco.setText("Endereço:");

        lbCpf.setText("CPF:");

        lbData.setText("Nascimento:");

        txtFieldNome.setToolTipText("Digite aqui seu nome");

        txtFieldCpf1.setToolTipText("Digite seu cpf");

        txtFieldEndereco.setToolTipText("Digite seu endereço");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Preferencia"));

        bGroupPreferencia.add(rdbCasa);
        rdbCasa.setSelected(true);
        rdbCasa.setText("Casa");

        bGroupPreferencia.add(rdbApart);
        rdbApart.setText("Apartamento");

        bGroupPreferencia.add(rdbTerreno);
        rdbTerreno.setText("Terreno");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbCasa)
                    .addComponent(rdbApart)
                    .addComponent(rdbTerreno))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdbCasa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbApart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdbTerreno)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        lbPonto1.setText(".");

        lbPonto2.setText(".");

        lbPonto3.setText("-");

        txtFieldCpf2.setToolTipText("Digite seu cpf");

        txtFieldCpf3.setToolTipText("Digite seu cpf");

        txtFieldCpf4.setToolTipText("Digite seu cpf");

        txtFieldData1.setToolTipText("Digite sua data de nascimento");

        lbBarra1.setText("/");

        txtFieldData2.setToolTipText("Digite sua data de nascimento");

        lbBarra2.setText("/");

        txtFieldData3.setToolTipText("Digite sua data de nascimento");

        lbTituloCad.setFont(new java.awt.Font("DejaVu Sans", 1, 18));
        lbTituloCad.setText("Cadastramento de Cliente");

        lbTexto1.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        lbTexto1.setText("Preencha os dados abaixo, para se cadastrar:");

        butCadastrar.setText("Cadastrar-se");
        butCadastrar.setToolTipText("Clique aqui para se cadastrar");
        butCadastrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCadastrarActionPerformed(evt);
            }
        });

        butVoltar.setText("Voltar");
        butVoltar.setToolTipText("Clique aqui para voltar à tela de login");
        butVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        butVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                                .addComponent(butCadastrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(butVoltar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNome)
                                    .addComponent(lbCpf))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(txtFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(txtFieldCpf1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPonto1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFieldCpf2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPonto2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFieldCpf3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbPonto3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFieldCpf4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbData)
                                .addGap(18, 18, 18)
                                .addComponent(txtFieldData1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(lbBarra1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFieldData2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(lbBarra2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFieldData3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbEndereco)
                                .addGap(35, 35, 35)
                                .addComponent(txtFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(lbTituloCad))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbTexto1)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFieldEndereco, txtFieldNome});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtFieldCpf1, txtFieldCpf2, txtFieldCpf3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(butCadastrar)
                        .addComponent(butVoltar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbTituloCad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(lbTexto1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbNome))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbCpf)
                            .addComponent(txtFieldCpf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFieldCpf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPonto1)
                            .addComponent(lbPonto2)
                            .addComponent(txtFieldCpf3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbPonto3)
                            .addComponent(txtFieldCpf4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFieldEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbEndereco))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbData)
                                    .addComponent(txtFieldData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbBarra2)
                                    .addComponent(txtFieldData3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFieldData2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbBarra1)
                                .addGap(16, 16, 16)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lbBarra1, lbBarra2});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butVoltarActionPerformed
        new TelaPrincipal().setVisible(true);
        dispose();
    }//GEN-LAST:event_butVoltarActionPerformed
 
    private void butCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCadastrarActionPerformed
        
        String cpf = txtFieldCpf1.getText() +
                txtFieldCpf2.getText() +
                txtFieldCpf3.getText() +
                txtFieldCpf4.getText();
        String diaString = txtFieldData1.getText();
        String mesString = txtFieldData2.getText();
        String anoString = txtFieldData3.getText();
        int dia = Integer.parseInt(diaString);
	int mes = Integer.parseInt(mesString);
	int ano = Integer.parseInt(anoString);
        
        Calendar dataNascimento = new GregorianCalendar(ano, mes-1, dia);
        String endereco = txtFieldEndereco.getText();
        String nome = txtFieldNome.getText();

        TipoImovel preferencia = null;
        if(rdbApart.isSelected()){
            preferencia = TipoImovel.APARTAMENTO;
        } else if( rdbCasa.isSelected()){
            preferencia = TipoImovel.CASA;
        } else if ( rdbTerreno.isSelected()){
            preferencia = TipoImovel.TERRENO;
        }
        
        try {
            sistema.controladorClientes().adicionaCliente(cpf, dataNascimento,
                    endereco, nome, preferencia);
            JOptionPane.showMessageDialog(null, CADASTROEFETUADO,
                    "Cadastro Efetuado", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            //Logger.getLogger(CadastraCliente.class.getName()).log(Level.SEVERE, null, ex);
            
        }

        

    }//GEN-LAST:event_butCadastrarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastraCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGroupPreferencia;
    private javax.swing.JButton butCadastrar;
    private javax.swing.JButton butVoltar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbBarra1;
    private javax.swing.JLabel lbBarra2;
    private javax.swing.JLabel lbCpf;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbPonto1;
    private javax.swing.JLabel lbPonto2;
    private javax.swing.JLabel lbPonto3;
    private javax.swing.JLabel lbTexto1;
    private javax.swing.JLabel lbTituloCad;
    private javax.swing.JRadioButton rdbApart;
    private javax.swing.JRadioButton rdbCasa;
    private javax.swing.JRadioButton rdbTerreno;
    private javax.swing.JTextField txtFieldCpf1;
    private javax.swing.JTextField txtFieldCpf2;
    private javax.swing.JTextField txtFieldCpf3;
    private javax.swing.JTextField txtFieldCpf4;
    private javax.swing.JTextField txtFieldData1;
    private javax.swing.JTextField txtFieldData2;
    private javax.swing.JTextField txtFieldData3;
    private javax.swing.JTextField txtFieldEndereco;
    private javax.swing.JTextField txtFieldNome;
    // End of variables declaration//GEN-END:variables
    // Atributos
    private Sistema sistema;
    private final String CADASTROEFETUADO = "Seu cadastro foi efetuado" +
    " com sucesso!\nLembre-se:\n" +
    "Seu login é os 6 primeiros digitos do seu cpf\n" +
    "Sua senha é sua data de nascimento\n\n" +
    "Você pode mudar seu login/senha quando desejar";
    // Fim de declaracao de atributos
}
