/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InterfaceOpClientes.java
 *
 * Created on 04/07/2010, 16:50:56
 */

package imobiliaria.gui;

import imobiliaria.controladores.*;
import imobiliaria.entidades.*;
import imobiliaria.colecoes.*;
import imobiliaria.entidades.Funcionario;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Thiago Ferreira
 */
public class InterfaceOpFuncionario extends javax.swing.JFrame {
    private Sistema sis;
    private Funcionario funcSelecionado = null;
    private InterfaceAdmin interfaceAdmin;


    /** Creates new form InterfaceOpClientes */
    public InterfaceOpFuncionario(InterfaceAdmin interfaceAdmin) {
        sis = new Sistema();

        this.interfaceAdmin = interfaceAdmin;
	try {
	    sis.atualizaDados();
	} catch (Exception e) {
	    System.out.println("Erro: " + e.getMessage());
	}
        initComponents();
        setLocationRelativeTo(null);
        atualizaFuncionarios();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        JP_VerInfo = new javax.swing.JPanel();
        JB_CadasFunc = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTA_ListagemTotal = new javax.swing.JTextArea();
        JB_Voltar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        JL_NumFuncCad = new javax.swing.JLabel();
        JP_AttCad = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTA_ListFunc = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JTF_Nome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTF_Endereco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        JTF_cpf = new javax.swing.JTextField();
        JTF_Nascimento = new javax.swing.JTextField();
        JL_LoginFunc = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        JL_Creci = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        JTF_ProcuraFuncInfo = new javax.swing.JTextField();
        JB_ProcuraFuncInfo = new javax.swing.JButton();
        JB_SelectFuncionario = new javax.swing.JButton();
        JB_SalvaDadosFunc = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        JCB_ListFunc = new javax.swing.JComboBox();
        JB_ExcluirFunc = new javax.swing.JButton();
        JB_VerHistoricoFunc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Opções Funcionarios");
        setResizable(false);

        JP_VerInfo.setLayout(null);

        JB_CadasFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/addIcon.png"))); // NOI18N
        JB_CadasFunc.setText("Cadastrar Funcionario");
        JB_CadasFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_CadasFuncActionPerformed(evt);
            }
        });
        JP_VerInfo.add(JB_CadasFunc);
        JB_CadasFunc.setBounds(420, 350, 220, 37);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Listagem Total"));
        jPanel1.setLayout(null);

        JTA_ListagemTotal.setColumns(20);
        JTA_ListagemTotal.setEditable(false);
        JTA_ListagemTotal.setRows(5);
        jScrollPane1.setViewportView(JTA_ListagemTotal);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 20, 369, 390);

        JP_VerInfo.add(jPanel1);
        jPanel1.setBounds(20, 20, 390, 420);

        JB_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/backIcon.png"))); // NOI18N
        JB_Voltar.setText("Voltar");
        JB_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_VoltarActionPerformed(evt);
            }
        });
        JP_VerInfo.add(JB_Voltar);
        JB_Voltar.setBounds(420, 400, 220, 35);

        jLabel4.setText("Funcionarios cadastrados:");
        JP_VerInfo.add(jLabel4);
        jLabel4.setBounds(420, 40, 230, 15);

        JL_NumFuncCad.setText("XX");
        JP_VerInfo.add(JL_NumFuncCad);
        JL_NumFuncCad.setBounds(420, 60, 50, 15);

        jTabbedPane1.addTab("Listagem de Funcionarios", JP_VerInfo);

        JP_AttCad.setLayout(null);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Listagem de Funcionarios"));
        jPanel2.setLayout(null);

        JTA_ListFunc.setColumns(20);
        JTA_ListFunc.setEditable(false);
        JTA_ListFunc.setRows(5);
        jScrollPane2.setViewportView(JTA_ListFunc);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(10, 20, 410, 140);

        JP_AttCad.add(jPanel2);
        jPanel2.setBounds(10, 11, 430, 170);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacao"));
        jPanel3.setLayout(null);

        jLabel1.setText("Nome:");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(10, 30, 60, 15);
        jPanel3.add(JTF_Nome);
        JTF_Nome.setBounds(110, 30, 310, 25);

        jLabel2.setText("Endereço:");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(10, 60, 90, 15);
        jPanel3.add(JTF_Endereco);
        JTF_Endereco.setBounds(110, 60, 310, 25);

        jLabel3.setText("Cpf:");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(10, 90, 40, 15);

        jLabel5.setText("Nascimento:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(10, 120, 100, 15);

        jLabel8.setText("Login:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 150, 90, 20);

        JTF_cpf.setText("000.000.000-00");
        jPanel3.add(JTF_cpf);
        JTF_cpf.setBounds(110, 90, 310, 25);

        JTF_Nascimento.setText("00/00/000");
        jPanel3.add(JTF_Nascimento);
        JTF_Nascimento.setBounds(110, 120, 310, 25);

        JL_LoginFunc.setText(" ");
        jPanel3.add(JL_LoginFunc);
        JL_LoginFunc.setBounds(110, 150, 150, 15);

        jLabel9.setText("CRECI:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(280, 150, 70, 15);

        JL_Creci.setText("000000");
        jPanel3.add(JL_Creci);
        JL_Creci.setBounds(343, 150, 70, 15);

        JP_AttCad.add(jPanel3);
        jPanel3.setBounds(10, 240, 430, 190);

        jLabel7.setText("Procurar por nome:");
        JP_AttCad.add(jLabel7);
        jLabel7.setBounds(450, 30, 140, 15);
        JP_AttCad.add(JTF_ProcuraFuncInfo);
        JTF_ProcuraFuncInfo.setBounds(450, 50, 190, 25);

        JB_ProcuraFuncInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/procuraIcon.png"))); // NOI18N
        JB_ProcuraFuncInfo.setText("Procurar");
        JB_ProcuraFuncInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ProcuraFuncInfoActionPerformed(evt);
            }
        });
        JP_AttCad.add(JB_ProcuraFuncInfo);
        JB_ProcuraFuncInfo.setBounds(450, 80, 190, 40);

        JB_SelectFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/okIcon.png"))); // NOI18N
        JB_SelectFuncionario.setText("Selecionar");
        JB_SelectFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_SelectFuncionarioActionPerformed(evt);
            }
        });
        JP_AttCad.add(JB_SelectFuncionario);
        JB_SelectFuncionario.setBounds(450, 200, 180, 31);

        JB_SalvaDadosFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/saveIcon.png"))); // NOI18N
        JB_SalvaDadosFunc.setText("Salvar");
        JB_SalvaDadosFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_SalvaDadosFuncActionPerformed(evt);
            }
        });
        JP_AttCad.add(JB_SalvaDadosFunc);
        JB_SalvaDadosFunc.setBounds(450, 300, 180, 33);

        jLabel10.setText("Funcionario:");
        JP_AttCad.add(jLabel10);
        jLabel10.setBounds(10, 200, 100, 15);

        JCB_ListFunc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JP_AttCad.add(JCB_ListFunc);
        JCB_ListFunc.setBounds(110, 200, 320, 25);

        JB_ExcluirFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/exitIcon.png"))); // NOI18N
        JB_ExcluirFunc.setText("Excluir");
        JB_ExcluirFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_ExcluirFuncActionPerformed(evt);
            }
        });
        JP_AttCad.add(JB_ExcluirFunc);
        JB_ExcluirFunc.setBounds(450, 350, 180, 34);

        JB_VerHistoricoFunc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imobiliaria/images/homeIcon.png"))); // NOI18N
        JB_VerHistoricoFunc.setText("Ver historico");
        JB_VerHistoricoFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JB_VerHistoricoFuncActionPerformed(evt);
            }
        });
        JP_AttCad.add(JB_VerHistoricoFunc);
        JB_VerHistoricoFunc.setBounds(450, 250, 180, 34);

        jTabbedPane1.addTab("Informacoes", JP_AttCad);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JB_VerHistoricoFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_VerHistoricoFuncActionPerformed
        if (funcSelecionado != null) {
            new InterfaceDadosFuncionario(funcSelecionado).setVisible(true);
        } else {
            MostraErro(new Exception("Funcionario nao selecionado"));
        }
}//GEN-LAST:event_JB_VerHistoricoFuncActionPerformed

    private void JB_ExcluirFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ExcluirFuncActionPerformed
                
        if (funcSelecionado != null) {
            if (ControladorFuncionario.getInstance().removeFuncionario(
                    funcSelecionado.getCreci())) {

                ControladorLogin.getInstance().removeLogin(
                        funcSelecionado.getLogin().getUserName());

                JOptionPane.showMessageDialog(null, "Funcionario removido"
                        + " com Sucesso!",
                    "Remocao", JOptionPane.INFORMATION_MESSAGE);
                funcSelecionado = null;
                atualizaFuncionarios();
            }
        } else {
            MostraErro(new Exception("Funcionario nao selecionado"));
        }
}//GEN-LAST:event_JB_ExcluirFuncActionPerformed

    private void JB_SalvaDadosFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_SalvaDadosFuncActionPerformed


        if (funcSelecionado == null) {
            MostraErro(new Exception("Funcionario nao selecionado"));
            return;
        }

        try {

            Calendar data;

            try {
            String[] nascimento = JTF_Nascimento.getText().split("/");
            data = new GregorianCalendar(Integer.parseInt(nascimento[2]),
                    Integer.parseInt(nascimento[1]) - 1,
                    Integer.parseInt(nascimento[0]));
            } catch (Exception e) {
                throw new Exception("Data Invalida!");
            }

        ControladorFuncionario.getInstance().modificaFuncionario(funcSelecionado.getCreci(),
                JTF_cpf.getText().replace(".", "").replace("-", ""),
                data, JTF_Endereco.getText(), JTF_Nome.getText());
        
        atualizaFuncionarios();

        } catch (Exception e) {
            MostraErro(e);
        }

        
}//GEN-LAST:event_JB_SalvaDadosFuncActionPerformed

    private void JB_SelectFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_SelectFuncionarioActionPerformed


        if (JCB_ListFunc.getSelectedItem() == null)
            return;

        Funcionario func = ControladorFuncionario.getInstance().
                getFuncionarioPorCpf(String.valueOf(JCB_ListFunc.getSelectedItem())
                .split(" ")[0]);

        funcSelecionado = func;

        JTF_Nome.setText(func.getNome());
        JTF_cpf.setText(func.getCpf());
        JTF_Endereco.setText(func.getEndereco());
        JTF_Nascimento.setText(func.getDataNascimento());
        JL_LoginFunc.setText(func.getLogin().getUserName());
        JL_Creci.setText(func.getCreci());
}//GEN-LAST:event_JB_SelectFuncionarioActionPerformed

    private void JB_ProcuraFuncInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_ProcuraFuncInfoActionPerformed
        atualizaFuncionarios();
}//GEN-LAST:event_JB_ProcuraFuncInfoActionPerformed

    private void JB_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_VoltarActionPerformed
        dispose();
}//GEN-LAST:event_JB_VoltarActionPerformed

    private void JB_CadasFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JB_CadasFuncActionPerformed
        new CadastraFuncionario(this).setVisible(true);
}//GEN-LAST:event_JB_CadasFuncActionPerformed



    /*
     * --- ABA: Listagem de clientes
     */    // --- Fim de ABA: Listagem de clientes


    /*
     * --- ABA: Informacoes do cliente
     */
    private String[] mostraImoveis(Cliente cliente){
        String[] imoveis;
        ColecaoImoveis historicoCliente = cliente.getHistoricoCompras();

        // Se nao tiver imoveis, deixar mensagem default
        if( historicoCliente.getImoveis().isEmpty()){
            imoveis = new String[1];
            imoveis[0] = "< Não possui nenhum imóvel adicionado >";
            return imoveis;
        }

        imoveis = new String[historicoCliente.getImoveis().size()];
        for (int i = 0; i < historicoCliente.getImoveis().size(); i++) {
            imoveis[i] = historicoCliente.getImoveis().get(i).toString();
        }

        return imoveis;
    }

    
    /*
     * Metodo de Busca por nomes
     */
    /*private String[] mostraClientes(String nomeProcurado) {
        TreeSet<Cliente> conjCliente = ControladorCliente.getInstance().getColecaoClientes().getClientes();
        ArrayList<String> clientesProcurados = new ArrayList<String>();
        String[] clientes;

        // Se nao tiver clientes, deixar mensagem default
        if( conjCliente.isEmpty()){
            clientes = new String[1];
            clientes[0] = "< Você não possui nenhum cliente adicionado >";
            return clientes;
        }

        // Procura pela entrada do usuario (sem case sensitive)
        for (Cliente cliente : conjCliente) {
            if (cliente.getNome().toLowerCase().contains(nomeProcurado.toLowerCase())) {
                    clientesProcurados.add(cliente.getNome() + " - CPF: " + cliente.getCpf());
            }
        }
        // Adiciona no array que ira ser usado no JList
        clientes = new String[clientesProcurados.size()];

        if (clientes.length == 0){
            clientes = new String[1];
            clientes[0] = "< Nenhum cliente encontrado >";
            return clientes;
        }

        for (int i = 0; i < clientesProcurados.size(); i++) {
		clientes[i] = clientesProcurados.get(i);
	}
	return clientes;
    }*/

    public void atualizaFuncionarios(){

        interfaceAdmin.atualizaInterface();

        JL_NumFuncCad.setText(ControladorFuncionario.getInstance()
                .getColecaoFuncionario().getNumFuncionarios() + "");

        JTA_ListagemTotal.setText(ControladorFuncionario.getInstance().
                    listaFuncionarios());

        if (!JTF_ProcuraFuncInfo.getText().equals("")) {
            // Gerando STRING PARA NOMES!
            try {
            String info = "";
            for (Funcionario f : ControladorFuncionario.getInstance()
                .getColecaoFuncionario().getFuncionarioPorNome(JTF_ProcuraFuncInfo
                .getText())) {
                info += f.exibeInformacao() + "\n";
                }

        JTA_ListFunc.setText(info);
        JCB_ListFunc.removeAllItems();

        for (String fInfo : listaParaCBox(ControladorFuncionario.getInstance()
                .getColecaoFuncionario().getFuncionarioPorNome(
                JTF_ProcuraFuncInfo.getText()))) {
            JCB_ListFunc.addItem(fInfo);
        }

        } catch (Exception e) {
            MostraErro(e);
        }
        } else {
        try {
            JTA_ListFunc.setText(ControladorFuncionario.getInstance().
                    listaFuncionarios());
            JCB_ListFunc.removeAllItems();

            for (String fInfo : listaParaCBox(ControladorFuncionario.getInstance()
                .getColecaoFuncionario().getColecaoFuncionarios())) {
            JCB_ListFunc.addItem(fInfo);
        }

        } catch (Exception e) {
            MostraErro(e);
        }
        }

        sis.salvarDados();

    }
    
    private String[] listaParaCBox(Collection<Funcionario> funcs) {
        String[] funcionarios = new String[funcs.size()];

        int cont = 0;
        for (Funcionario f : funcs) {
            funcionarios[cont] = f.getCpf() + " - " + f.getNome() + " - " +
                    f.getCreci();
            cont++;
        }

        return funcionarios;
        
    }

    private void MostraErro(Exception e) {
        JOptionPane.showMessageDialog(null, "Um erro aconteceu: \n"
                + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
    }


    /*

        JTA_ListFunc.setText(null);

        final String nome = JTF_ProcuraNome.getText();
        final String nomeInfo = JTF_ProcuraFuncInfo.getText();
        final String nomeHist = JTF_ProcuraFuncIm.getText();
        //mostraClientes(nome);
        JList_Listagem.setModel(new javax.swing.AbstractListModel() {
            String[] strings = mostraClientes(nome);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });

        JList_ListInfo.setModel(new javax.swing.AbstractListModel() {
            String[] strings = mostraClientes(nomeInfo);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });

        JList_FuncHistVendas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = mostraClientes(nomeHist);
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
    }*/


   
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceOpFuncionario(new InterfaceAdmin()).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JB_CadasFunc;
    private javax.swing.JButton JB_ExcluirFunc;
    private javax.swing.JButton JB_ProcuraFuncInfo;
    private javax.swing.JButton JB_SalvaDadosFunc;
    private javax.swing.JButton JB_SelectFuncionario;
    private javax.swing.JButton JB_VerHistoricoFunc;
    private javax.swing.JButton JB_Voltar;
    private javax.swing.JComboBox JCB_ListFunc;
    private javax.swing.JLabel JL_Creci;
    private javax.swing.JLabel JL_LoginFunc;
    private javax.swing.JLabel JL_NumFuncCad;
    private javax.swing.JPanel JP_AttCad;
    private javax.swing.JPanel JP_VerInfo;
    private javax.swing.JTextArea JTA_ListFunc;
    private javax.swing.JTextArea JTA_ListagemTotal;
    private javax.swing.JTextField JTF_Endereco;
    private javax.swing.JTextField JTF_Nascimento;
    private javax.swing.JTextField JTF_Nome;
    private javax.swing.JTextField JTF_ProcuraFuncInfo;
    private javax.swing.JTextField JTF_cpf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

}
