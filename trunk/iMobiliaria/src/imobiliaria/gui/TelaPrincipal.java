/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelaPrincipal.java
 *
 * Created on 06/06/2010, 21:20:25
 */

package imobiliaria.gui;

import imobiliaria.auxiliar.*;
import imobiliaria.controladores.*;
import imobiliaria.entidades.*;

/**
 * GUI para tela principal
 * @author Thiago Ferreira 
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /** Creates new form TelaPrincipal */
    public TelaPrincipal() {

        initComponents();
        setLocationRelativeTo(null);
        Sistema sis = new Sistema();
	try {
	    sis.atualizaDados();
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	}
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butGroupLogin = new javax.swing.ButtonGroup();
        JL_ImobLogo = new javax.swing.JLabel();
        JL_Login = new javax.swing.JLabel();
        JL_Senha = new javax.swing.JLabel();
        JPF_senha = new javax.swing.JPasswordField();
        JTF_Login = new javax.swing.JTextField();
        JB_CadCliente = new javax.swing.JButton();
        JB_Sair = new javax.swing.JButton();
        JB_LogIn = new javax.swing.JButton();
        JL_Erro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("iMobiliaria");
        setResizable(false);

        JL_ImobLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/iMobLogo.png"))); // NOI18N

        JL_Login.setText("Login:");

        JL_Senha.setText("Senha:");

        JPF_senha.setToolTipText("Digite sua senha aqui");

        JTF_Login.setToolTipText("Digite seu login aqui");

        JB_CadCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/addIcon.png"))); // NOI18N
        JB_CadCliente.setText("Cadastrar cliente");
        JB_CadCliente.setToolTipText("Ir para cadastro de cliente");
        JB_CadCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_CadCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CadClienteActionPerformed(evt);
            }
        });

        JB_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/exitIcon.png"))); // NOI18N
        JB_Sair.setText("Sair");
        JB_Sair.setToolTipText("Sai do programa");
        JB_Sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_SairActionPerformed(evt);
            }
        });

        JB_LogIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/loginIcon.png"))); // NOI18N
        JB_LogIn.setText("Logar");
        JB_LogIn.setToolTipText("Clique aqui para entrar na sua conta");
        JB_LogIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        JB_LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_LogInActionPerformed(evt);
            }
        });

        JL_Erro.setFont(new java.awt.Font("Arial", 0, 13));
        JL_Erro.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JB_Sair, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 401, Short.MAX_VALUE)
                .addComponent(JB_CadCliente)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JL_ImobLogo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Login)
                                .addGap(25, 25, 25)
                                .addComponent(JTF_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JL_Senha)
                                .addGap(18, 18, 18)
                                .addComponent(JPF_senha, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(JB_LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(211, 211, 211))
            .addGroup(layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(JL_Erro)
                .addContainerGap(460, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(JL_ImobLogo)
                .addGap(4, 4, 4)
                .addComponent(JL_Erro)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Login)
                    .addComponent(JTF_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JL_Senha)
                    .addComponent(JPF_senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(JB_LogIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JB_CadCliente)
                    .addComponent(JB_Sair))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_CadClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CadClienteActionPerformed
     // Opcao para CADASTRAR CLIENTE
        new CadastraCliente().setVisible(true);
        dispose();

    }//GEN-LAST:event_JB_CadClienteActionPerformed

    private void JB_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_SairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JB_SairActionPerformed

    private void JB_LogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_LogInActionPerformed
        String login = JTF_Login.getText();
        String senha = JPF_senha.getText();
        

        boolean loga = false;
        
        if (ControladorLogin.getInstance().verificaLogin(login)) {
            if (ControladorLogin.getInstance().getLogin(login).getSenha().equals(senha)) {
                loga = true;
            }
        }
        
        if (loga) {
            JL_Erro.setText("");
            TipoLogin tipoLoginUsuario = ControladorLogin.getInstance().getLogin(login).getTipoLogin();
            
            if (tipoLoginUsuario == TipoLogin.ADMINISTRADOR) {
                new InterfaceAdmin().setVisible(true);
                dispose();

            } else if (tipoLoginUsuario == TipoLogin.CLIENTE) {
            	new InterfaceCliente().setVisible(true);
                dispose();

            } else if (tipoLoginUsuario == TipoLogin.FUNCIONARIO) {
                // Opcao para FUNCIONARIO
                dispose();
            }

        } else {
            JL_Erro.setText("Nome de login ou senha digitados são incorretos.");
        }

    }//GEN-LAST:event_JB_LogInActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_CadCliente;
    private javax.swing.JButton JB_LogIn;
    private javax.swing.JButton JB_Sair;
    private javax.swing.JLabel JL_Erro;
    private javax.swing.JLabel JL_ImobLogo;
    private javax.swing.JLabel JL_Login;
    private javax.swing.JLabel JL_Senha;
    private javax.swing.JPasswordField JPF_senha;
    private javax.swing.JTextField JTF_Login;//VAI PRECISAR SER STATIC
    private javax.swing.ButtonGroup butGroupLogin;
    // End of variables declaration//GEN-END:variables
    // Atributos
    
/*    public static String getLoginAtual(){
    	return JTF_Login.getText(); 
    	METODO NECESSARIO PARA PEGAR OS DADOS QUE SERAO PRINTADOS
    	NA TELA DOS DADOS DE CLIENTE; - PEGAR O CLIENTE PELO LOGIN DELE. 
    }
*/    
 // Fim de declaracao de atributos
}
