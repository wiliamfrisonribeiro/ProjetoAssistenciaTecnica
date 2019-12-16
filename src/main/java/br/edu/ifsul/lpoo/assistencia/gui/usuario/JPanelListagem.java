
package br.edu.ifsul.lpoo.assistencia.gui.usuario;

import br.edu.ifsul.lpoo.assistencia.model.Usuario;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class JPanelListagem extends javax.swing.JPanel {
    private JPanelUsuario pnlUsuario;
    private SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    public JPanelListagem(JPanelUsuario usu) {
        this.pnlUsuario = usu;
        initComponents();
    }

    public void populaTabela(){
        try{
            List<Usuario> list = pnlUsuario.getControle().getConexao().listUsuario();
            DefaultTableModel modeloTabela = (DefaultTableModel) tblListagem.getModel();
            modeloTabela.setNumRows(0);
            for(Usuario u : list){
               
                String dataCadastro = new String();
                if(u.getDtCadastro() != null){
                    dataCadastro = formatoData.format(u.getDtCadastro());
                }
                
                Object[] linha = {u.getCpf(),u.getNome(),dataCadastro,u.getCidade().getNome()};
                modeloTabela.addRow(linha);
            }            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(this, "Erro ao listar Usuários : "+e.getLocalizedMessage(), "Listagem", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSuperior = new javax.swing.JPanel();
        lblFiltro = new javax.swing.JLabel();
        txfFiltroNome = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        pnlInferior = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        scpListagem = new javax.swing.JScrollPane();
        tblListagem = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        lblFiltro.setText("Filtrar por Nome:");
        pnlSuperior.add(lblFiltro);

        txfFiltroNome.setColumns(20);
        pnlSuperior.add(txfFiltroNome);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        pnlSuperior.add(btnFiltrar);

        add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        pnlInferior.add(btnNovo);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlInferior.add(btnEditar);

        btnRemover.setText("Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        pnlInferior.add(btnRemover);

        add(pnlInferior, java.awt.BorderLayout.PAGE_END);

        pnlCentro.setLayout(new java.awt.BorderLayout());

        tblListagem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Data de Cadastro", "Cidade"
            }
        ));
        scpListagem.setViewportView(tblListagem);

        pnlCentro.add(scpListagem, java.awt.BorderLayout.CENTER);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        
        //atualiza a lista de cidades no combobox de cidades
        pnlUsuario.getEdicao().populaComboCidades();
        
        //alterar o painel para apresentar a edicao (JPanelEdicacao)
        pnlUsuario.viewCard("edicao");
                
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed

           populaTabela();

        // TODO add your handling code here:
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
      
        //atualiza a lista de cidades no combobox de cidades
       pnlUsuario.getEdicao().populaComboCidades();

       //alterar o painel para apresentar a edicao (JPanelEdicacao)
       pnlUsuario.viewCard("edicao");
            
      
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
         int linhaSel = tblListagem.getSelectedRow();
         if( linhaSel > -1){            
             String cpf = tblListagem.getValueAt(linhaSel, 0).toString();
             Usuario u = new Usuario();
             u.setCpf(cpf);
             try{
                 pnlUsuario.getControle().getConexao().remover(u);
                 
                 //atualiza a listagem
                 populaTabela();
                 
             }catch(Exception e){                 
                 JOptionPane.showMessageDialog(this, "Erro ao remover Usuario : "+e.getLocalizedMessage(), "Remoção", JOptionPane.ERROR_MESSAGE);
             }       
        }else{
            JOptionPane.showMessageDialog(this, "Selecione uma linha para Remover", "Selecione", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_btnRemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel lblFiltro;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JScrollPane scpListagem;
    private javax.swing.JTable tblListagem;
    private javax.swing.JTextField txfFiltroNome;
    // End of variables declaration//GEN-END:variables
}
