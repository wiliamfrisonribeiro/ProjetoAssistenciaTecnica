
package br.edu.ifsul.lpoo.assistencia.gui.usuario;

import br.edu.ifsul.lpoo.assistencia.model.Cidade;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class JPanelEdicao extends javax.swing.JPanel {
    
    private JPanelUsuario pnlUsuario; 
    
    public JPanelEdicao(JPanelUsuario painel) {
        this.pnlUsuario = painel;
        initComponents();
        
    }
   
    
    public void populaComboCidades(){
        
        try{
            
            DefaultComboBoxModel modelo = (DefaultComboBoxModel) cbxCidade.getModel();
          
            modelo.removeAllElements();//reseta
            modelo.addElement("Selecione");//elemento fixo na primeira posicao
         
            java.util.List<Cidade> lista = pnlUsuario.getControle().getConexao().listCidade();
            for(Cidade c : lista){
                
                modelo.addElement(c);
            }
          
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Erro ao carregar Cidades !", "Cidades", JOptionPane.ERROR_MESSAGE);
        }
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel3 = new javax.swing.JLabel();
        pnlInferior = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txfCPF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfCep = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txfNumero = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        psfSenha = new javax.swing.JPasswordField();
        lblCidade = new javax.swing.JLabel();
        cbxCidade = new javax.swing.JComboBox<>();

        jLabel3.setText("jLabel3");

        setLayout(new java.awt.BorderLayout());

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        pnlInferior.add(btnSalvar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlInferior.add(btnCancelar);

        add(pnlInferior, java.awt.BorderLayout.PAGE_END);

        pnlCentro.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("CPF:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlCentro.add(jLabel1, gridBagConstraints);

        txfCPF.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(txfCPF, gridBagConstraints);

        jLabel2.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlCentro.add(jLabel2, gridBagConstraints);

        txtNome.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(txtNome, gridBagConstraints);

        jLabel4.setText("CEP:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlCentro.add(jLabel4, gridBagConstraints);

        txfCep.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(txfCep, gridBagConstraints);

        jLabel5.setText("Numero:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlCentro.add(jLabel5, gridBagConstraints);

        txfNumero.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(txfNumero, gridBagConstraints);

        lblSenha.setText("Senha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlCentro.add(lblSenha, gridBagConstraints);

        psfSenha.setColumns(5);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(psfSenha, gridBagConstraints);

        lblCidade.setText("Cidade:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 2);
        pnlCentro.add(lblCidade, gridBagConstraints);

        cbxCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(cbxCidade, gridBagConstraints);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        pnlUsuario.viewCard("listagem");

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        
        //atualiza a listagem para mostrar o registro alterado/incluso
        pnlUsuario.getListagem().populaTabela();
    
        pnlUsuario.viewCard("listagem");
        
    }//GEN-LAST:event_btnSalvarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxCidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblCidade;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPasswordField psfSenha;
    private javax.swing.JTextField txfCPF;
    private javax.swing.JTextField txfCep;
    private javax.swing.JTextField txfNumero;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
