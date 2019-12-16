
package br.edu.ifsul.lpoo.assistencia.gui.cidade;

import br.edu.ifsul.lpoo.assistencia.model.Cidade;
import br.edu.ifsul.lpoo.assistencia.model.dao.PersistenciaJDBC;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
Extends de java.swing.JPanel
           método construtor
           método initComponents
*/

//ActionaListener : adiciona a funcionalidade para escutar eventos
public class JPanelListagem extends JPanel implements ActionListener {
    //Homework: pesquisar o procedimento para adicionar um JPanel em um JFrame
    private BorderLayout layoutBorder;
    
    private JPanel pnlSuperior;
    private FlowLayout layoutFlow;
    
    private JPanel pnlCentro;
    private JLabel lblFiltro;
    private JTextField txfFiltro;
    private JButton btnFiltrar;
    
    private JScrollPane scpPane;
    private JTable tblListagem;
    private DefaultTableModel modeloTabela;
    
    private PersistenciaJDBC conexao;
    
    //construtor que recebe por parâmetro a instancia de PersistenciaJDBC
    public JPanelListagem(PersistenciaJDBC c){
        this.conexao = c;
        initComponents();
    }
    
    private void populaTabela(){
        
        try {
             List<Cidade> lista = null;
            if(txfFiltro.getText().trim().length() > 0){                
                lista = conexao.listCidade(txfFiltro.getText().trim());
            }else{
                 lista = conexao.listCidade();
            }
            
            modeloTabela.setNumRows(0);//limpa a tabela
            for(Cidade c: lista){
                //adiciona linhas conforme a ordem das colunas
                modeloTabela.addRow(new Object[]{c.getId(), c.getNome(), c.getEstado().getNome()});
            }
            
            //chamar o metodo listCidade da classe PersistenciaJDBC !!!
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao listar Cidades", "Listar", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    private void initComponents(){
        
        layoutBorder = new BorderLayout();
        
        this.setLayout(layoutBorder);//seta o gerenciador para o JPanelListagem
         
        pnlSuperior = new JPanel();
        layoutFlow = new FlowLayout();
        
        lblFiltro = new JLabel("Filtrar por nome:");
        txfFiltro = new JTextField(20);
        
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(this);//a propria classe fará o tratamento
        btnFiltrar.setActionCommand("comando_filtrar");//seta o comando de acao
        
        
        pnlSuperior.setLayout(layoutFlow);//seta no pnlSuperior o gerenciador
        pnlSuperior.add(lblFiltro);//adiciona em linha, pois está utilizando o flow
        pnlSuperior.add(txfFiltro);
        pnlSuperior.add(btnFiltrar);
        
        pnlCentro = new JPanel();
        scpPane = new JScrollPane();
        tblListagem = new JTable();
        modeloTabela = new DefaultTableModel(new Object[]{"Código","Nome","Estado"},0);
        tblListagem.setModel(modeloTabela);
        scpPane.setViewportView(tblListagem);
        pnlCentro.add(scpPane); // qual layout manager será aplicado nesse caso ? 
        
        this.add(pnlSuperior, BorderLayout.NORTH);//adiciona o pnlSuperior no topo
        this.add(pnlCentro, BorderLayout.CENTER);//adiciona o pnlCentro no espaço restante
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      
        //testa para descobrir o componente que gerou o evento.
        if(e.getActionCommand().equals(btnFiltrar.getActionCommand())){
            
            populaTabela();
        }
        
    }
}
